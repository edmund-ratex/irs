import csv
from datetime import datetime, timedelta
import configparser
import time
import openai
from libs.logger import get_logger
import os
import re
import docx
import sys
import youtube_caption
from enum import Enum


logger = get_logger(__name__)
regex = re.compile(r'[\\\\\/:*?"<>|#,. ()]')
openai.api_key = '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'  # la
# openai.api_key = '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' #ma
# openai.api_key = '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'
CAPTION_URL_STR = 'caption_url:'
KEYWORD_STR = "Keywords:"
DATE_FORMAT = "%Y-%m-%d"
# SPECIFIED_CAPTION_DIR = 'caption_CONCEPT/NEWS_2023-08-17_23_34_28'
SPECIFIED_CAPTION_DIR = ""

class CaptionType(Enum):
    NEWS = 1
    CONCEPT = 2

class CaptionTypeInfo:
    def __init__(self, configPath, captionType, firstRow, csvHandler, promptTemplate, promptTemplateSection, promptDescTemplate = ''):
        self.configPath = configPath
        self.captionType = captionType
        self.firstRow = firstRow
        self.csvHandler = csvHandler
        self.promptTemplate = promptTemplate
        self.promptTemplateSection = promptTemplateSection
        self.promptDescTemplate = promptDescTemplate

def start(nDays,captionTypeInfo):
    while True:
        try:
            local_now_date = time.localtime()
            current_day_str = time.strftime(f"{DATE_FORMAT}", local_now_date)
            utc_now = datetime.utcnow()
            tomorrow = utc_now.replace(
                hour=0, minute=9, second=0, microsecond=0) + timedelta(days=nDays)
            wait_seconds = (tomorrow - utc_now).total_seconds()
            specified_caption_dir = get_specified_caption_dir(captionTypeInfo.configPath)
            logger.info(f'specified_caption_dir:{specified_caption_dir}')
            if len(specified_caption_dir) > 0:
                wait_seconds = 0.1

            caption_dir = f'caption_{captionTypeInfo.captionType.name}'
            if not is_csv_exsit(caption_dir, current_day_str):
                wait_seconds = 1

            logger.info(
                f"next awake UTC time:{tomorrow}, wait seconds:{wait_seconds}, now:{utc_now}") 
            
            time.sleep(wait_seconds)

            local_now_date = time.localtime()
            generate_article(local_now_date, nDays, caption_dir, captionTypeInfo,specified_caption_dir)
        except Exception as e:
            logger.error(f"Unknown exception ({e})")
            time.sleep(600)
            continue

def generate_article(local_now_date,nDays, caption_dir, captionTypeInfo,specified_caption_dir):
    try:         
        current_time_str = time.strftime(
            f"{DATE_FORMAT} %H:%M:%S", local_now_date)
        logger.info(f'{current_time_str} begin exec -------------------------')
        current_time_str = regex.sub("_", current_time_str)

        utc_now = datetime.utcnow()
        today_0_hour = utc_now.replace(hour=0, minute=2, second=0, microsecond=0)
        publishedBefore = today_0_hour.strftime("%Y-%m-%dT%H:%M:%SZ")
        publishedAfter = (today_0_hour - timedelta(days=nDays)).strftime("%Y-%m-%dT%H:%M:%SZ")

        dirPath = specified_caption_dir
        if dirPath == "":
            dirPath = f'{caption_dir}/{captionTypeInfo.captionType.name}_{current_time_str}'
            succ = youtube_caption.save_youtube_caption(
                publishedBefore, publishedAfter, dirPath, captionTypeInfo.configPath)
            if succ == False:
                return

        csvPath = f"./{dirPath}.csv"                
        append_to_csv(captionTypeInfo.firstRow, csvPath)                               

        for subdir, dirs, files in os.walk(dirPath):
            index = 0
            try_times = 0
            totalLen = len(files)
            while index < totalLen:
                try:
                    logger.info(
                        f'--------------------proress:{index+1} / {totalLen} ---------------')
                    file = files[index]
                    filepath = subdir + os.sep + file
                    if filepath.endswith(".txt"):
                        fileContent = read_file(filepath)
                        article = fileContent
                        caption_url = ''
                        contentSplits = fileContent.split("\n")
                        if len(contentSplits) > 1 and contentSplits[0].startswith(CAPTION_URL_STR):
                            article = fileContent.replace(
                                contentSplits[0], '').strip()
                            caption_url = contentSplits[0].replace(
                                CAPTION_URL_STR, '').strip()
                        else:
                            logger.error(
                                f"caption content must start with 'caption_url:'----{file}")
                            continue                        

                        prompt = captionTypeInfo.promptTemplate.replace(
                            'TEXT HERE', article)

                        arti = request_chatGPT(prompt, [])
                        splits = arti.split("\n")                        
                        title = ""
                        keywords = ""
                        for item in splits:                            
                            if item.startswith(KEYWORD_STR):
                                keywords = item.replace(
                                    KEYWORD_STR, "").strip()
                            if item.startswith("Title:") or item.startswith('title:'):
                                title = item
                                break
                        
                        if title.startswith("Title:") or title.startswith('title:'):
                            title = title.lstrip("Title:")
                            title = title.lstrip("title:")
                            title = title.strip()
                        if title == '':
                            log_err(f"竟然没有生成titile: {file}")
                            continue

                        gpt_msgs = [{"role": "user", "content": f"{prompt}"}, {
                            "role": "assistant", "content": f"{arti}"}]
                        sections = split_sectoin_by_roman_numeral_line(arti)
                        final_result = ""
                        for section in sections:
                            roman_num = extract_roman_number(section)
                            if roman_num == "":
                                continue
                            prompt_section = captionTypeInfo.promptTemplateSection.replace(
                                '####', roman_num)
                            section_resp = request_chatGPT(
                                prompt_section, gpt_msgs)
                            final_result += f"\n\n{section_resp}"
                            logger.info(
                                f'--------------------sub proress:{index+1} / {totalLen} ---------------')
                            
                        final_result = final_result.strip()
                        if captionTypeInfo.captionType == CaptionType.CONCEPT:
                            promptDesc = captionTypeInfo.promptDescTemplate.replace('TEXT HERE', final_result)
                            categoryAndDesc = request_chatGPT(promptDesc, [])
                            category, desc = extract_category_desc(categoryAndDesc)
                            slug = genSlug(title)
                            row = [title, final_result, desc, category, slug, keywords, file, caption_url]
                        elif captionTypeInfo.captionType == CaptionType.NEWS:
                            # row = [title, final_result, keywords, file, caption_url]
                            promptDesc = captionTypeInfo.promptDescTemplate.replace('TEXT HERE', final_result)
                            categoryAndDesc = request_chatGPT(promptDesc, [])
                            category, desc = extract_category_desc(categoryAndDesc)
                            slug = genSlug(title)
                            row = [title, final_result, desc, category, slug, keywords, file, caption_url]
                        append_to_csv(row, csvPath)                             
                        index += 1
                        try_times = 0
                except Exception as e:
                    log_err(f"exec err_msg:{e}, index:{index}, file:{filepath}")
                    if try_times >= 3:
                        index += 1
                        try_times = 0
                    else:
                        try_times += 1
                    logger.info("start sleep 120s")
                    time.sleep(120)
        captionTypeInfo.csvHandler(csvPath)        
    except Exception as e:
        errMsg = f"发生错误:{e}"
        log_err(errMsg)

def genSlug(title):
    temp = regex.sub("-", title).lower().replace('--', '-')
    slug = f'/{regex.sub("", temp)}'
    return slug

def extract_category_desc(s):
    # 'category: Cryptocurrency\ndesc: 99Bitcoins celebrates 100,000 YouTube subscribers by giving away 100 TREZOR One hardware wallets to participants who share and comment on their favorite video from the channel.'        
    if ('category: ' not in s and 'Category: ' not in s) or ('desc: ' not in s and 'Desc: ' not in s) or '\n' not in s:
        raise ValueError(f'cant extract_category_desc:{s}')

    if 'category: ' in s:
        cat_idx = s.index('category:') + len('category:')
    else:
        cat_idx = s.index('Category:') + len('Category:')

    if 'desc: ' in s:
        desc_idx = s.index('desc:') + len('desc:')
    else:
        desc_idx = s.index('Desc:') + len('Desc:')

    category = s[cat_idx:s.index('\n', cat_idx)].strip()
    desc = s[desc_idx:].strip()
    return category, desc

def is_csv_exsit(dir, file_part_name):
    for subdir, dirs, files in os.walk(dir):
        for file_name in files:
            if file_part_name in file_name:
                return True
    return False


def pre_processing2(article):
    return split_article(article)


def extract_roman_number(text):
    regex = r"\b([IVX]+)\.\s"
    match = re.search(regex, text)
    if match:
        return match.group(1)
    else:
        print("No match found.")
        return ""


def split_sectoin_by_roman_numeral_line(article):
    # Define a regular expression to match lines starting with a Roman numeral and a period
    regex = r"^[IVX]+\."
    lines = article.strip().split("\n")

    result = []
    section = ""
    # Loop through the lines to find the matching line
    for line in lines:
        if re.match(regex, line):
            if section != "":
                result.append(section)
            section = line
        elif section != "":
            section += f'\n{line}'
    result.append(section)
    return result


def split_article(article_text):
    sections = []
    section = ''
    section_length = 0

    # 分割文章为段落
    paragraphs = article_text.split('\n\n')

    for para in paragraphs:
        # 检查段落是否为小标题（Heading），如果是，则将当前部分添加到sections列表中，并创建新部分
        if is_heading(para):
            if section_length > 0:
                sections.append(section)
            section = para + '\n'
            section_length = len(section)
        else:
            para_length = len(para)
            # 如果添加该段落后，部分长度超过3500个字符，则将当前部分添加到sections列表中，并创建新部分
            if section_length + para_length > 3500:
                sections.append(section)
                section = para + '\n'
                section_length = len(section)
            else:
                section += para + '\n'
                section_length += para_length + 1

    # 将最后一个部分添加到sections列表中
    if section_length > 0:
        sections.append(section)

    return sections


def is_heading(paragraph):
    return len(paragraph) < 40


def get_args():
    if len(sys.argv) == 2:
        return sys.argv[1]
    else:
        log_err("Please provide current dir name as command line argument.")


def read_config(dirName):
    config = configparser.ConfigParser()
    config.read(f'../{dirName}/promptConfig.ini')
    promptTemplate = config.get('prompt', 'promptTemplate')
    max_token_size = config.getint('param', 'max_token_size')
    return promptTemplate, max_token_size


def genFullPrompt(promptTemplate, keywordArr, removeDoubleQuotes):
    # 一对一替换
    for keyword in keywordArr:
        if removeDoubleQuotes:
            promptTemplate = promptTemplate.replace('""', f'{keyword}', 1)
        else:
            promptTemplate = promptTemplate.replace('""', f'"{keyword}"', 1)
    return promptTemplate


def log_err(errMsg):
    openai.util.log_info(f'Log_Error: {errMsg}')


def append_to_csv(row, filePath):
    dir = os.path.dirname(filePath)
    if not os.path.exists(dir):
        os.makedirs(dir)

    # with open(filePath, "a", encoding='utf-8') as file:
    #     file.write(row)

    with open(filePath, 'a', newline='', encoding='utf-8') as file:
        writer = csv.writer(file)
        writer.writerow(row)


def read_key_words(dirName):
    content = read_file(f"../{dirName}/key_words.csv")
    content = content.strip('\n')
    keywordsList = content.split("\n")
    return keywordsList


def read_file(fileName):
    content = ''
    with open(fileName, "r", encoding='utf-8') as file:
        content = file.read()
    return content


def request_chatGPT(prompt, history_msgs):
    msgs = history_msgs.copy()
    msgs.append({"role": "user", "content": f"{prompt}"})
    completion = openai.ChatCompletion.create(
        # model="gpt-3.5-turbo",
        model="gpt-4",
        temperature=0.3,
        messages=msgs
    )

    content = completion.choices[0].message.content
    content = content.strip('\n')
    return content


def get_rubydex_glossary():
    doc = docx.Document('./RubyDex Glossary 2.docx')
    rgb_bold_contents = []
    for para in doc.paragraphs:
        for run in para.runs:
            if run.bold and run.font.color.rgb[0] == 51 and run.font.color.rgb[1] == 112 and run.font.color.rgb[2] == 255:
                rgb_bold_contents.append(run.text)

    return rgb_bold_contents

def get_specified_caption_dir(config_path):
    config = configparser.ConfigParser()
    config.read(config_path)
    specified_caption_dir = ""
    if config.has_option('youtube', 'specified_caption_dir'):
        specified_caption_dir = config.get('youtube', 'specified_caption_dir')
    return specified_caption_dir

import csv
import configparser
import docx
import time
import openai
from libs.logger import get_logger
import os
import re
import docx
import sys
import json

logger = get_logger(__name__)
regex = re.compile(r'[\\\\\/:*?"<>|#, ()]')
# regex2 = re.compile(r'[()]')
openai.api_key = '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'
# concept_csv = "captions_news_3.28_2023-03-28 09_35_10.csv"
concept_csv = "captions_50_test_2023-06-13_18_45_11.csv"

def start():
    try:    
        csv_rows = read_csv()        
        jsonContent = []
        
        current_time = time.strftime("%Y-%m-%d %H:%M:%S", time.localtime())
        current_time = regex.sub("_", current_time)
        outputFile = f'./concept_{current_time}.json'
        for keywords in csv_rows:
            if len(keywords) == 0 or (keywords[0]=="title" and keywords[1]=="content"):
                continue
            
            
            new_dict = {"title": keywords[0], "content": keywords[1],"desc":keywords[2], "slug":genSlug(keywords[0])}            
            jsonContent.append(new_dict)
        result_json = json.dumps(jsonContent)     
        save_to_file(result_json, outputFile)   
            
            
    except Exception as e:
        errMsg = f"发生错误:{e}"
        print(errMsg)
        log_err(errMsg)
        input()
        
def genSlug(title):
    temp = regex.sub("-", title).lower()
    slug = f'/{regex.sub("", temp)}'
    return slug
        
def save_to_file(content, filePath):
    dir = os.path.dirname(filePath)
    if not os.path.exists(dir):
        os.makedirs(dir)

    with open(filePath, "w", encoding='utf-8') as file:
        file.write(content)
        
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
    # 
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


def read_csv():    
    with open(concept_csv, newline='',encoding='utf-8') as csvfile:        
        reader = csv.reader(csvfile, delimiter=',', quotechar='"')
        
        # 
        result = []
        
        # 
        for row in reader:
            # 
            result.append(row)
            
    return result


def read_file(fileName):
    content = ''
    current_path = os.getcwd()
    print(current_path)
    with open(fileName, "r", encoding='utf-8') as file:
        content = file.read()
    return content


def request_chatGPT(prompt, max_token_size):
    completion = openai.ChatCompletion.create(
        model="gpt-3.5-turbo",
        # model="gpt-3.5-turbo-0301",
        temperature=0.3,
        max_tokens=max_token_size,
        messages=[
            {"role": "user", "content": f"{prompt}"}
        ]
    )

    content = completion.choices[0].message.content
    content = content.strip('\n')
    return content


def get_rubydex_glossary():
    doc = docx.Document('./tool/misc/RubyDex Glossary 2 _alreadygened')
    rgb_bold_contents = []
    for para in doc.paragraphs:
        for run in para.runs:
            if run.bold and run.font.color.rgb[0] == 51 and run.font.color.rgb[1] == 112 and run.font.color.rgb[2] == 255:
                rgb_bold_contents.append(run.text)

    return rgb_bold_contents


start()
input("")

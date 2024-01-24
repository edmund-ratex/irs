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
regex = re.compile(r'[\\\\\/:*?"<>|#, -]')
regex2 = re.compile(r'[()]')
openai.api_key = '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'
glossary_csv_file_path = "./glossary_gpt4.csv"

def start():
    try:    
        # keywordsList = get_rubydex_glossary()
        keywordsList = read_glossary(glossary_csv_file_path)        
        jsonContent = []
        for keywords in keywordsList:
            if len(keywords) == 0:
                continue
            
            new_dict = {"title": keywords[0], "content": keywords[1],"slug":genSlug(keywords[0])}            
            jsonContent.append(new_dict)
        result_json = json.dumps(jsonContent)     
        save_to_file(result_json, "./jsonGlossary.json")   
            
            
    except Exception as e:
        errMsg = f":{e}"
        print(errMsg)
        log_err(errMsg)
        input()
        
def genSlug(title):
    temp = regex.sub("-", title).lower()
    slug = f'/glossary/{regex2.sub("", temp)}'
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


def read_glossary(csv_file_path):    
    with open(csv_file_path, newline='',encoding='utf-8') as csvfile:        
        reader = csv.reader(csvfile, delimiter=',', quotechar='"')
        
        # 
        result = []
        
        # 
        for row in reader:
            # 
            result.append(row)
            
    return result
    # content = read_file("./tool/misc/rubydex_glossary_2023-03-08.csv")
    # content = content.strip('\n')
    # keywordsList = content.split("\n")
    # return keywordsList


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
input("请按回车键退出")

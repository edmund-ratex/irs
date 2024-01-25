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
import matplotlib.pyplot as plt

logger = get_logger(__name__)
# csvFiles = ['article_phemex.com_2023-04-03 10_14_23.csv','article_phemex.com_2023-04-02 22_28_13.csv','article_phemex.com_2023-04-01 09_58_44.csv']


def start():
    try:     
        size2Count = {}
        sizeGap = 50
        pattern = r'(?<!#)##\s'  # Use forward lookup to match the position after '# #'
        dir = './' #debug
        # dir = '../'  #release
        print('---------------------------------------------------')
        for subdir, dirs, files in os.walk(dir):               
            for file in files:    
                filepath = subdir + os.sep + file
                # file = 'article_phemex.com_2023-04-01 09_58_44.csv'          
                filepath =  file                
                if file == "article_phemex.com_2023-04-03 10_43_32.csv":
                    continue
                    
                if file.endswith('.csv') and file.startswith("article_phemex.com") :                    
                    lines = read_csv(filepath)                    
                    for line in lines:
                        if line[0] == "title" and line[1] == "content":
                            continue                                            
                        
                        content = line[1].strip('"').strip('"').strip()     
                        sections = re.split(pattern, content)                                           
                        for section in sections:
                            if section == "":
                                continue
                            size = len(section)                                 
                            key = (size//sizeGap)*sizeGap
                            if key in size2Count:
                                size2Count[key] += 1
                            else:
                                size2Count[key] = 1
            
                            
        
        # size2Count[7150] = 200
        # print('fff:',size2Count)
        x = list(size2Count.keys())
        y = list(size2Count.values())

        # Draw a bar chart
        plt.bar(x, y)
        
        print('tttttttttttt')
        print(max(x))
        print(max(y))
        plt.xticks(fontsize=6)
        plt.xticks(range(0, max(x) + 100, 100))
        plt.yticks(range(0, max(y) + 5, 2))        

        plt.title('Section Length Distribution')
        plt.xlabel('Section Length')
        plt.ylabel('Number of Sections')

        plt.show()            
                        
                
                    
                
                
                        
                        
                            
        
    except Exception as e:
        errMsg = f"error:{e}"
        print(errMsg)
        log_err(errMsg)
        input()
        
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


def read_csv(filePath):
    rows = []
    # print('read csv:',filePath)
    with open(filePath, 'r',encoding='utf-8') as csvfile:
        csvreader = csv.reader(csvfile)
        for row in csvreader:
            rows.append(row)
    return rows    


def read_file(fileName):
    content = ''
    with open(fileName, "r", encoding='utf-8') as file:
        content = file.read()
    return content


def request_chatGPT(prompt, max_token_size):
    completion = openai.ChatCompletion.create(
        model="gpt-3.5-turbo",        
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
    doc = docx.Document('./RubyDex Glossary 2.docx')
    rgb_bold_contents = []
    for para in doc.paragraphs:
        for run in para.runs:
            if run.bold and run.font.color.rgb[0] == 51 and run.font.color.rgb[1] == 112 and run.font.color.rgb[2] == 255:
                rgb_bold_contents.append(run.text)

    return rgb_bold_contents


start()
input("Please press enter to exit")

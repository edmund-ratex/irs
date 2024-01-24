import csv
import subprocess
from gen_article_by_caption import CaptionTypeInfo, CaptionType, start
import json
from libs.logger import get_logger
import os

logger = get_logger(__name__)

promptTemplate = '''Read this unformatted video script and generate 5 keywords at the 1st line and an educational article outline (I., II. etc) with a title, based only on content that fits an educational article. Ignore anything promoting other services, anything unclear because it references the original video that's unavailable, and anything that seems like casual chatter:"TEXT HERE"'''
promptTemplateSection = '''We will go section by section. Follow your outline and generate the content for Part ####. Output should be in html format without including Roman numerals, letters, or numbers, with first level being H2'''
promptDescTemplate = '''Generate a category word start with "category:" at first line and a one sentence preview description start with "desc:" at second line for this article:"TEXT HERE"'''


def main():
    conceptCaption = CaptionTypeInfo("./tool/caption_concept_config.ini", CaptionType.CONCEPT, ["title", "content", "desc", "category", "slug", "keywords", "raw_file_name", "vedio_url"],
                                     conceptCSVHandler, promptTemplate, promptTemplateSection, promptDescTemplate )
    start(1, conceptCaption)


def conceptCSVHandler(csvPath):
    # 'E:/firefly/seo/ff_chatGPT/concept_2023-06-20_01_19_23.json'
    jsonFilePath = f'../../{csv2json(csvPath)}'        
    print('current:',os.getcwd())
    result = exec_js('js_code/cms-data-import/import-concepts.mjs', jsonFilePath)
    logger.info(f'exec js code result: {result}')
    
def exec_js(js_path, jsonFilePath):
    result = subprocess.run(['node', js_path, jsonFilePath], capture_output=True, text=True)    
    return result.stdout

def read_csv(csv_path):
    with open(csv_path, newline='', encoding='utf-8') as csvfile:
        reader = csv.reader(csvfile, delimiter=',', quotechar='"')

        # 
        result = []

        # 
        for row in reader:
            # 
            result.append(row)

    return result


def save_to_file(content, filePath):
    dir = os.path.dirname(filePath)
    if not os.path.exists(dir):
        os.makedirs(dir)

    with open(filePath, "w", encoding='utf-8') as file:
        file.write(content)
    logger.info(f"save file succ: {filePath}")


def csv2json(csv_path):
    try:
        csv_rows = read_csv(csv_path)
        jsonContent = []
        jsonPath = os.path.splitext(csv_path)[0] + ".json"        
        for keywords in csv_rows:
            if len(keywords) == 0 or (keywords[0] == "title" and keywords[1] == "content"):
                continue
            new_dict = {"title": keywords[0], "content": keywords[1], "desc": keywords[2],
                        "category": keywords[3], "slug": keywords[4], "keywords": keywords[5]}
            jsonContent.append(new_dict)
        result_json = json.dumps(jsonContent)
        save_to_file(result_json, jsonPath)
        return jsonPath

    except Exception as e:
        errMsg = f"csv2json :{e}"
        logger.error(errMsg)


main()

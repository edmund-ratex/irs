from gen_article_by_caption import CaptionTypeInfo, CaptionType, start
import sendEmail
import csv
import os
import json
from libs.logger import get_logger

logger = get_logger(__name__)

promptTemplate = '''Read this unformatted video script and generate 5 keywords at the 1st line and a news article outline(I., II. etc) with a title, based only on content that fits a news article. Ignore anything promoting other services, anything unclear because it references the original video that's unavailable, and anything that seems like casual chatter:​"TEXT HERE"'''
promptTemplateSection = '''We will go section by section. Follow your outline and generate the content for Part ####. Output should be in html format without including Roman numerals, letters, or numbers, with first level being H2'''
promptDescTemplate = '''Generate a category word start with "category:" at first line and a one sentence preview description start with "desc:" at second line for this article:"TEXT HERE"'''

def main():
    newsCaption = CaptionTypeInfo("./tool/caption_news_config.ini", CaptionType.NEWS, ["title", "content", "desc", "category", "slug",  "keywords", "raw_file_name", "vedio_url"],
                                  newsCsvHandler, promptTemplate, promptTemplateSection, promptDescTemplate)
    start(1, newsCaption)

def newsCsvHandler(csvPath):
    jsonFilePath = f'../../{csv2json(csvPath)}'
    logger.info(f'jsonFilePath: {jsonFilePath}')
    return sendEmail.send_file(f"{CaptionType.NEWS.name} CSV", csvPath)


def read_csv(csv_path):
    with open(csv_path, newline='', encoding='utf-8') as csvfile:
        reader = csv.reader(csvfile, delimiter=',', quotechar='"')

        result = []

        for row in reader:
            result.append(row)

    return result


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
        errMsg = f"csv2json error:{e}"
        logger.error(errMsg)


def save_to_file(content, filePath):
    dir = os.path.dirname(filePath)
    if not os.path.exists(dir):
        os.makedirs(dir)

    with open(filePath, "w", encoding='utf-8') as file:
        file.write(content)
    logger.info(f"save file succ: {filePath}")


main()

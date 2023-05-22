# -*- encoding:utf-8 -*-
import re
import sys

from baidu_api import *
from textrank4zh import TextRank4Sentence
from transformers_api import *


def abstract(text, baidu_api_key, baidu_SECRET_KEY):
    result = []

    # baidu
    try:
        baidu = Baidu_api(API_KEY=baidu_api_key, SECRET_KEY=baidu_SECRET_KEY)
        result_baidu = baidu.get_text(text)
        # result_baidu = result_baidu[1:len(result_baidu)-1].split(',')[1].split(':')[1]
        # result_baidu = result_baidu.replace("")
        result_baidu = re.search(r'summary\": \"(.*?)\"', result_baidu)
        result.append(result_baidu.group(1))
    except:
        result.append("百度云发生错误")

    # textrank
    try:
        tr4s = TextRank4Sentence()
        tr4s.analyze(text=text, lower=True, source='all_filters')
        for item in tr4s.get_key_sentences(num=3):
            result.append(item.sentence)
    except:
        result.append("TextRank发生错误")

    # transformers
    try:
        trans = transformers_api()
        result_trans = trans.get_text(text)
        result_trans = result_trans[0]['summary_text']
        result.append(result_trans)
    except:
         print.append("transform发生错误")


    return result

if __name__ == '__main__':
    # 只是拿我自己的api举例
    baidu_api_key = "GLaQsNGkeXDyk40Sq5rtfqj3"
    baidu_SECRET_KEY = "dluYAELA7nQpuv1MmL76V8edqoNZypxM"
    gpt_api_key = "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"
    result = abstract(sys.argv[1], baidu_api_key, baidu_SECRET_KEY)
    print(result)  # -*- encoding:utf-8 -*-

from K_OCR import *
from CH_keywords import *
from EN_keywords import *
import sys


def keywords(TEXT):
    ch = 0
    en = 0
    for c in TEXT:
        if '\u4e00' <= c <= '\u9fff':
            ch += 1
        elif c.encode('utf-8').isalpha():
            en += 1

    if ch >= en:
        chk = CH_keywords()
        return chk.ch_keywords(TEXT)
    else:
        enk = EN_keywords()
        return enk.en_keywords(TEXT)


if __name__ == '__main__':

    # print(keywords('47_脑中“糖密码”的化学解析.png'))
    # print(keywords('83.png'))
    print(keywords(sys.argv[1]))


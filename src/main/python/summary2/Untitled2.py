import sys

from textrank4zh import TextRank4Sentence

text = sys.argv[1]
tr4s = TextRank4Sentence()
tr4s.analyze(text=text, lower=True, source='all_filters')

print('摘要：')
for item in tr4s.get_key_sentences(num=3):
    print(item.sentence)

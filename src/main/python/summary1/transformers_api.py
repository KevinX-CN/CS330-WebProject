# -*- coding: utf-8 -*-

from transformers import AutoTokenizer, AutoModelForSeq2SeqLM, pipeline


class transformers_api(object):
    tokenizer = AutoTokenizer.from_pretrained("csebuetnlp/mT5_multilingual_XLSum")
    model = AutoModelForSeq2SeqLM.from_pretrained("csebuetnlp/mT5_multilingual_XLSum")

    def __init__(self):
        self

    def get_text(self, text):
        # 摘要模型
        classifier = pipeline("summarization", model=self.model, tokenizer=self.tokenizer, max_length=50)
        re = classifier(text)
        return re

# -*- encoding:utf-8 -*-
import openai


class Gpt_api(object):

    def __init__(self, api_key):
        self.api_key = api_key

    def get_text(self, text):
        openai.api_key = self.api_key
        prompt = "下面的文本介绍了一个演讲内容，请为这个文本生成一个摘要，要求不超过50个词，这是文本：" + text
        response = openai.Completion.create(
            engine="text-davinci-002",
            prompt=prompt,
            max_tokens=1024,
            n=1,
            stop=None,
            temperature=0.5,
        )
        text = response.choices[0].text
        return text

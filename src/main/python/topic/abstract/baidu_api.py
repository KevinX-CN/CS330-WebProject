# -*- encoding:utf-8 -*-
import requests
import json


class Baidu_api(object):

    def __init__(self, API_KEY, SECRET_KEY):
        self.API_KEY = "GLaQsNGkeXDyk40Sq5rtfqj3"
        self.SECRET_KEY = "dluYAELA7nQpuv1MmL76V8edqoNZypxM"

    def get_text(self,content):
        url = "https://aip.baidubce.com/rpc/2.0/nlp/v1/news_summary?charset=UTF-8&access_token=" + self.get_access_token()

        payload = json.dumps({
            "content": content,
            "max_summary_len": 50
        })
        headers = {
            'Content-Type': 'application/json',
            'Accept': 'application/json'
        }

        response = requests.request("POST", url, headers=headers, data=payload)

        return response.text

    def get_access_token(self):
        """
        使用 AK，SK 生成鉴权签名（Access Token）
        :return: access_token，或是None(如果错误)
        """
        url = "https://aip.baidubce.com/oauth/2.0/token"
        params = {"grant_type": "client_credentials", "client_id": self.API_KEY, "client_secret": self.SECRET_KEY}
        return str(requests.post(url, params=params).json().get("access_token"))

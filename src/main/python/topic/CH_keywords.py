# -*- encoding:utf-8 -*-

import requests
import json


class CH_keywords(object):
    def __init__(self, CHK_API_KEY="oWPkxu7ihRRdpZWajFwnS4VP", CHK_SECRET_KEY="MPSCetszXxk7eZZzX03RMe9MkaKhuCyD"):
        self.CHK_API_KEY = CHK_API_KEY
        self.CHK_SECRET_KEY = CHK_SECRET_KEY

    def ch_keywords(self, ori_text):
        url = "https://aip.baidubce.com/rpc/2.0/nlp/v1/txt_keywords_extraction?access_token=" + self.get_access_token()

        payload = "{\"text\":[\"" + ori_text + "\"]" + ",\"num\":10}"
        payload = payload.encode('utf-8')
        headers = {
            'Content-Type': 'application/x-www-form-urlencoded'
        }

        response = requests.request("POST", url, headers=headers, data=payload).text
        jsonobj = json.loads(response)
        if 'results' not in jsonobj.keys():
            return None
        res_keywords = []
        for t in jsonobj['results']:
            res_keywords.append(t['word'])
        return res_keywords

    def get_access_token(self):
        """
        使用 AK，SK 生成鉴权签名（Access Token）
        :return: access_token，或是None(如果错误)
        """
        url = "https://aip.baidubce.com/oauth/2.0/token"
        params = {"grant_type": "client_credentials", "client_id": self.CHK_API_KEY,
                  "client_secret": self.CHK_SECRET_KEY}
        return str(requests.post(url, params=params).json().get("access_token"))


if __name__ == '__main__':
    chk = CH_keywords()
    print(chk.ch_keywords(
        "山完食品和勺兑酒在景颇社区中有矛眉含义,对中老年人和一二十岁的年轻人各自意味着两个相反的世界,也透露出献鬼村民和基督徒的不同生活方式。一方面,山蹊食品价格低廉、质量低劣,却给年轻人品尝、接触、和想象都市生活的慈望和可能性。它们打破社区饮食和信仰禅忌,让年轻人不再干净,没法承载祭祀传统。而酒连接人神,承载祭祀传统的老一萧杜绝山寥食品却必须喝酒,种植经济林以来人们不再酿酒,市场勾兑酒给老一辉带来严重的健康问题。另一方面,虏诚的基督徒应该心思单纯,而献鬼祭师必须精通说服、沟通、甚至是诱骗(对不好的鬼神,如口舌鬼和琵琼鬼)的艺术。这两种生活伦理导致基督信和献鬼村民对山寥食品和勾兑酒的不同态度:对基督徒,酒污染人的单纯,让人兴奋,谕发不该有的心思和行为;对献鬼村民,酒能助兴,让人面对鬼神时即兴发拂、游刃有余。"))

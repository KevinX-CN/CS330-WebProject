import base64
import io
import json
import re
import urllib
import requests
from PIL import Image
import pytesseract


def get_file_content_as_base64(path, urlencoded=False):
    """
    获取文件base64编码
    :param path: 文件路径
    :param urlencoded: 是否对结果进行urlencoded
    :return: base64编码信息
    """
    with open(path, "rb") as f:
        content = base64.b64encode(f.read()).decode("utf8")
        if urlencoded:
            content = urllib.parse.quote_plus(content)
    return content


class K_OCR(object):
    def __init__(self, picPath, OCR_API_KEY="ta1gmQy7m4hsZsElYGCvGHlp",
                 OCR_SECRET_KEY="lI6uZBN9YcR5UyZwuYth99M1MHp99i0Q"):
        self.OCR_API_KEY = OCR_API_KEY
        self.OCR_SECRET_KEY = OCR_SECRET_KEY
        self.picPath = picPath

    def extract_text(self):
        res_text = self.BD_extract_text()
        if res_text is None:
            res_text = self.Ori_extract_text()
        return res_text

    def Ori_extract_text(self):
        img = Image.open(self.picPath)
        text = pytesseract.image_to_string(img, lang='chi_sim')  # 图片转字符串
        ch = 0
        en = 0
        for c in text:
            if '\u4e00' <= c <= '\u9fff':
                ch += 1
            elif c.encode('utf-8').isalpha():
                en += 1
        if ch >= en:
            text = text.replace("\n", "").replace(" ", "")  # 去掉杂质，提纯
            text = re.sub('[a-zA-Z]', '', text)
        else:
            text = text.replace("\n", " ")  # 去掉杂质，提纯
        return text

    def BD_extract_text(self):
        url = "https://aip.baidubce.com/rest/2.0/ocr/v1/general_basic?access_token=" + self.get_access_token()

        payload = 'image=' + get_file_content_as_base64(self.picPath, True)
        headers = {
            'Content-Type': 'application/x-www-form-urlencoded',
            'Accept': 'application/json'
        }

        response = requests.request("POST", url, headers=headers, data=payload).text
        jsonobj = json.loads(response)
        if 'words_result' not in jsonobj.keys():
            return None
        res_text = ''
        for t in jsonobj['words_result']:
            if t['words'][0].encode('utf-8').isalpha():
                res_text += ' '
            res_text += t['words']
        return res_text

    def get_access_token(self):
        """
        使用 AK，SK 生成鉴权签名（Access Token）
        :return: access_token，或是None(如果错误)
        """
        url = "https://aip.baidubce.com/oauth/2.0/token"
        params = {"grant_type": "client_credentials", "client_id": self.OCR_API_KEY,
                  "client_secret": self.OCR_SECRET_KEY}
        return str(requests.post(url, params=params).json().get("access_token"))


if __name__ == '__main__':
    ocr = K_OCR('47_脑中“糖密码”的化学解析.png')
    print(ocr.extract_text())

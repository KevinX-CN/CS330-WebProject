# 从图片中提取文字

from PIL import Image
import pytesseract


class OCR(object):

    def __init__(self, picturePath):
        self.picturePath = picturePath

    def extract_text(self):
        img = Image.open(self.picturePath)  # 打开图片
        text = pytesseract.image_to_string(img, lang='chi_sim')  # 图片转字符串
        text = text.replace("\n", "").replace("'","")  # 去掉杂质，提纯
        return text


if __name__ == '__main__':
    ocr1 = OCR(r'data/47_脑中“糖密码”的化学解析.png')
    ocr2 = OCR(r'data/94_Quantum many-body sensors.png')
    print(ocr1.extract_text())
    print(ocr2.extract_text())

from multi_rake import Rake


class EN_keywords(object):

    def __init__(self):
        self.rake = Rake()

    def en_keywords(self, ori_text):
        text_en = ori_text

        keywords = self.rake.apply(text_en)

        res_keywords = []
        for key in keywords[:10]:
            res_keywords.append(key[0])

        return res_keywords


if __name__ == '__main__':
    ehk = EN_keywords()
    print(ehk.en_keywords(
        "Massive access, also known as massive connectivity or massive machine-type communication (mMTC), is one of the three main use cases of the fifth-generation (5G) and beyond 5G (B5G) wireless networks defined by the lnternationa Telecommunication Union. Different from conventional human-type communication,massive access aims at realizing efficient and reliable communications for a massive number of Internet of Things (loT) devices. The main challenge of mMTC is that the BS can efficiently and reliably detect the active qdevices based on the superposition of their unique signatures from a large pool of uplink devices, among which only a small fraction is active. In this talk, we shall present some recent results of massive access from an optimization perspective. In particular, we shall present optimization formulations and algorithms as well as some phase transition analysis results."))

# # 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
#
# import openai
#
#
# class EN_keywords(object):
#
#     def __init__(self, ENK_API_KEY="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"):
#         self.EHK_API_KEY = ENK_API_KEY
#
#     def en_keywords(self, ori_text):
#         openai.api_key = self.EHK_API_KEY
#
#         completion = openai.ChatCompletion.create(
#             model="gpt-3.5-turbo",
#             messages=[{"role": "user", "content": "Extract the key words of\"" + ori_text + "\""}]
#         )
#
#         return f"\U0001f47b: {completion.choices[0].message['content']}".split(':')[-1].split(',')[:10]

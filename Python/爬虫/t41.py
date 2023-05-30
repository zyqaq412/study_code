import requests

res = requests.get('http://47.109.90.13:7777/article/19')
with open('t4.txt','w',encoding='utf-8') as file:
    file.write(res.text)
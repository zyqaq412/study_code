import requests
import parsel
import csv

f = open('Top250.csv', mode='a', encoding='utf-8-sig', newline='')
csv_writer = csv.DictWriter(f, fieldnames=[
    '电影',
    '导演',
    '演员',
    '年份',
    '国家',
    '类型',
    '评分',
    '人数',
    '短语',
    '链接',
])
csv_writer.writeheader()
for i in range(10):
    url = 'https://movie.douban.com/top250?start=' + '0+25*i' + '&filter='

    headers = {
        'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/'
                      '537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Safari/537.36 Edg/111.0.1661.54'
    }
    respons = requests.get(url, headers=headers)
    selector = parsel.Selector(respons.text)
    Lis = selector.css('.grid_view li')
    for li in Lis:
        title = li.css('.hd a span:nth-child(1)::text').get()
        # print(title)
        info_list = li.css('.bd p:nth-child(1)::text').getall()
        # print(info_list)
        director = info_list[0].strip().split(' ')[1]
        performer = info_list[0].strip().split(' ')[4]
        year = info_list[1].strip().split('/')[0]
        country = info_list[1].strip().split('/')[1]
        film_type = info_list[1].strip().split('/')[2]
        fraction = li.css('.star .rating_num::text').get()
        evaluate = li.css('.star span:nth-child(4)::text').get().replace('人评价', '')
        word = li.css('.quote span.ing::text').get()
        href = li.css('.hd a::attr(href)').get()
        dit = {
            '电影': title,
            '导演': director,
            '演员': performer,
            '年份': year,
            '国家': country,
            '类型': film_type,
            '评分': fraction,
            '人数': evaluate,
            '短语': word,
            '链接': href,
        }
        csv_writer.writerow(dit)

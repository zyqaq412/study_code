import jieba
from wordcloud import WordCloud
from collections import Counter
import csv

# path 文件路径
# 统计词频
def count_word_frequency(path):
    fn = open(path,"r",encoding='utf-8')
    string_data = fn.read()
    fn.close()
    # 使用jieba分词将文本切分成词语
    words = jieba.cut(string_data)
    # 使用Counter计算词频
    word_count = Counter(words)

    return word_count

# 保存词频统计结果为csv文件
def save_word_frequency_to_csv(word_count, filename):
    # 将词频统计结果保存为CSV文件
    with open(filename, 'w', newline='', encoding='utf-8-sig') as file:
        writer = csv.writer(file)
        writer.writerow(['Word', 'Frequency'])  # 写入表头
        writer.writerows(word_count.items())  # 写入词频统计结果

def generate_wordcloud(text):
    new_txt = " ".join(text)
    wc = WordCloud(
        background_color='white',
        font_path='C:/Windows/Fonts/FZSTK.TTF',
        max_words=200,
        max_font_size=100,
        scale=32,
        # stopwords 停用词 生成图云时会过滤这些词
        stopwords={'的', '，', '和', '是', '随着', '对于', '对', '等', '能', '都', '。',
                    ' ', ',', '、', ',', '中', '在', '了', '通常', '如果', '我们', '需要'}
    )
    wc.generate(new_txt)
    wc.to_file("2022231550黄哲宇a.png")

# 统计词频
word_count = count_word_frequency("a.txt")
# 保存为CSV文件
filename = '2022231550黄哲宇a.csv'
save_word_frequency_to_csv(word_count, filename)
# 生成词云图
generate_wordcloud(word_count)



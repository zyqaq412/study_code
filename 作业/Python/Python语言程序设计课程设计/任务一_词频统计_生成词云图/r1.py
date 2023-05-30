import jieba
from wordcloud import WordCloud
import csv


# path：文件路径
# return：词频统计结果
def count_word(path):
    fn = open(path, "r", encoding='utf-8')
    string_data = fn.read()
    fn.close()
    # 使用jieba分词将文本切分成词语
    words = jieba.cut(string_data)
    # 空字典 字典键唯一
    counts = {}
    for word in words:
        # 以 词语为键 出现次数为值 就可以实现词频统计
        counts[word] = counts.get(word, 0) + 1
    return counts


# 保存词频统计结果为csv文件
def save_to_csv(counts, filename):
    # 将词频统计结果保存为CSV文件
    with open(filename, 'w', newline='', encoding='utf-8-sig') as file:
        writer = csv.writer(file)
        writer.writerow(['词语', '出现次数'])  # 写入表头
        writer.writerows(counts.items())  # 写入词频统计结果


# 传入词频统计结果生成词云图
def generate_wordcloud(counts):
    # 将counts列表的元素用空格连起来
    new_txt = " ".join(counts)
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
    wc.to_file("r1.png")


def main():
    counts = count_word("1.txt")
    # print(counts)
    save_to_csv(counts, 'r1.csv')
    generate_wordcloud(counts)


if __name__ == '__main__':
    main()
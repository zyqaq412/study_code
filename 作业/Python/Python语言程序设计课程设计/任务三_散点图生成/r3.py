import numpy as np
import matplotlib.pyplot as plt
import pandas as pd

# 读取NPZ文件
data = np.load('国民经济核算季度数据.npz', allow_pickle=True)

# 查看NPZ文件中的键（数组名）
# print(data.files)
# print(data['columns'])
# print(data['values'])

# 获取时间和第一到第三产业生成总值当季值数据
plt.rcParams['font.sans-serif'] = ['SimHei']  # 设置为黑体字体
time = data['values'][:, 1]
gdp1 = data['values'][:, 3].astype(float)
gdp2 = data['values'][:, 4].astype(float)
gdp3 = data['values'][:, 5].astype(float)
# print(time)
# print(gdp1)
# print(gdp2)
# print(gdp3)

# 创建散点图
plt.scatter(time, gdp1, label='第一产业生产总值')
plt.scatter(time, gdp2, label='第二产业生产总值')
plt.scatter(time, gdp3, label='第三产业生产总值')
plt.xlabel('时间')
plt.ylabel('生产总值(亿元)')
plt.title('2000-2017年各产业生产总值散点图')
plt.xticks(rotation=45, fontsize=4)

# 添加图例 左上角小图
plt.legend(loc='upper left')

# 保存散点图
plt.savefig('r3.png')

# 创建DataFrame并保存为Excel文件
df = pd.DataFrame({'时间': time, '第一产业生产总值': gdp1, '第二产业生产总值': gdp2, '第三产业生产总值': gdp3})
df.to_excel('r3.xlsx', index=False)

# 显示散点图
plt.show()

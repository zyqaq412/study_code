
import numpy as np
import pandas as pd


# 生成100个随机的二维坐标点 范围在0-10
np.random.seed(1)  # 设置随机数种子
points = np.random.uniform(low=0, high=10, size=(100, 2))
# print(points)

# 计算任意两点之间的欧式距离
dist_matrix = np.zeros((100, 100))  # 创建一个100行100列的全零数组
for i in range(100):
    for j in range(i+1, 100):
        # distance = math.sqrt((points[i][0] - points[j][0]) ** 2
        #                      + (points[i][1] - points[j][1]) ** 2)
        distance = np.linalg.norm(points[i] - points[j])
        dist_matrix[i, j] = distance
        dist_matrix[j, i] = distance

# 将距离矩阵保存到Excel文件
df = pd.DataFrame(dist_matrix)
filename = "r2.xlsx"
df.to_excel(filename, index=False)
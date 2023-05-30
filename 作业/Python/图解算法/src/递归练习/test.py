
# 4.1练习实现递归计算列表的和
def sum(list):
    # 如果列表为空返回 0
    if len(list) == 0:
        return 0
    a = list[0]
    # 删除列表第一个元素 del必须指定索引
    del list[0]
    return a+sum(list)

# 4.2 编写一个递归函数来计算列表包含的元素数
def countList(arr):
    if len(arr) == 0:
        return 0
    # 不指定索引默认删除最后一个元素
    arr.pop()
    return 1+countList(arr)

# 4.3 找出列表中最大的数
def findMax(arr):
    if len(arr) == 0:
        return 0
    a=arr[0]
    arr.pop(0)
    b = findMax(arr)
    # python 的三目运算符 == a>b?a:b
    return a if a>b else b
    # return max(a,findMax(arr))

# 4.8 根据数组元素创建乘法表
def creatCFB(arr):
    a = 0
    for i in arr:
        for j in arr[a:]:
            ++a
            print(f"{i}*{j}={i * j}\t",end="")
        print()

def main():
    creatCFB([2,3,7,8,10])
    # print(findMax([1,23,43,2,6,8])) # 43
    # print(countList([1,23,4,5,6,5])) # 6
    # print(sum([1,2,3,4,5])) # 15

if __name__ == "__main__":
        main()

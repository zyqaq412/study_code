from 排序与查找 import search, sort


# 二分查找
def test01():
    list = [1,2,3,4,5,6,7,8,9,10]
    ans = search.binarySearch(list, 2)
    print(ans)

# 选择排序
def test02():
    print()
    list = [1,4,5,7,9,4,2,5,99,56,34]
    print(list)
    list = sort.selectionSort(list)
    print(list)

# 二分查找 递归版
def test03():
    list = [1,2,3,4,5,6,7,8,9,10]
    ans = search.binarySearch2(list,323,0,len(list)-1)
    print(ans)

# 快速排序
def test04():
    arr = [1,45,8,3,8,3,9,54,98,234,989]
    ans = sort.quickSort(arr)
    print(ans)
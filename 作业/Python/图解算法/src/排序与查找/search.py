# 查找算法
# 二分查找
def binarySearch(list,item):
    low = 0
    high = len(list)-1
    while low<=high:
        mid = (low+high) // 2
        guess = list[mid]
        if guess == item:
            return mid
        if guess>item:
            high = mid-1
        else:
            low = mid+1
    return None
# 二分查找 递归版
def binarySearch2(arr,item,low,high):
    if low>high:
        return None
    mid = (low+high) // 2
    if arr[mid] == item:
        return mid
    if arr[mid] > item:
        return binarySearch2(arr,item,low,mid-1)
    else:
        return binarySearch2(arr, item, mid+1, high)
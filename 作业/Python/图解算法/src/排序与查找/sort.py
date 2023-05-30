# 排序算法
# 选择排序
def selectionSort(arr):
    # n个数比较n-1次
     for i in range(0,len(arr)):
         # 每次拿未排序的第一个数与未排序的其他数比较
         minIndex = i
         # 前面的数已经排序了 所以从 i+1 开始
         for j in range(i+1,len(arr)):
             if arr[minIndex] > arr[j]:
                 minIndex = j

         if i != minIndex:
            # tmp = arr[minIndex]
            # arr[minIndex] = arr[i]
            # arr[i] = tmp
            arr[minIndex] += arr[i]
            arr[i] = arr[minIndex] - arr[i]
            arr[minIndex] -= arr[i]

     return arr

# 快速排序
def quickSort(arr):
    if len(arr) <2:
        return arr
    pivot = arr[0]
    # 列表快速生成
    left = [i for i in arr[1:] if i<=pivot]
    right = [i for i in arr[1:] if i>pivot]
    return quickSort(left) + [pivot] + quickSort(right)


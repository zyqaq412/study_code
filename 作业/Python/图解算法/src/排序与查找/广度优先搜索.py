# 导入队列
from collections import deque


def search(graph,name):
    # 创建队列
    searchQueue = deque()
    searchQueue += graph[name]
    # 记录已经检查过的数据
    searched =[]
    while searchQueue:
        # pop 弹出顶部  popleft 弹出尾部
        person = searchQueue.popleft()
        # person 不在已检查数组里才判断
        if person not in searched:
            if personIsApple(person):
                print(person + "是苹果商人")
                return True
            else:
                searchQueue += graph[person]
                searched += person
    return False


def personIsApple(name):
    return True if name[-1] == 'm' else False
def main():
    graph = {}
    graph["you"] = ["dj","df","fg"]
    graph["dj"] = ["pp"]
    graph["pp"] = []
    graph["df"] = ["mm"]
    graph["mm"] = []
    graph["fg"] = []
    flag = search(graph,"you")
    print(flag)


if __name__ == "__main__":
        main()


def printlist(arr):
    for i in arr:
        print(f"\t{i}=>",end="")
        print(arr[i])
        
        
# 查找开销最低的节点 costs 开销表 ,processed 已遍历节点
def find_lowest_cost_node(costs,processed):
    lowest_cost = float("inf")
    lowest_cost_node = None
    for node in costs:
        cost = costs[node]
        if cost < lowest_cost and node not in processed:
            lowest_cost = cost
            lowest_cost_node = node
    return lowest_cost_node

def dijk(graph,parents,costs,processed):
    node = find_lowest_cost_node(costs,processed)
    while node is not None:
        # 当前节点到起点的开销
        cost = costs[node]
        # 当前节点相邻节点集
        neighbors = graph[node]
        for n in neighbors.keys():
            # neighbors[n]节点n到node的开销
            # new_cost 节点n通过node到起点的开销
            new_cost = cost+neighbors[n]
            if costs[n]>new_cost: # 节点n找到开销更小到起点的路径 更新开销表
                costs[n] = new_cost
                parents[n] = node
        processed.append(node)
        node = find_lowest_cost_node(costs,processed)            


def main():
    # 路线图 案例.png
    graph = {}
    graph["start"] = {}
    graph["start"]["a"] = 6
    graph["start"]["b"] = 2
    
    graph["a"] = {}
    graph["a"]["fin"]=1
    
    graph["b"] ={}
    graph["b"]["a"] =3
    graph["b"]["fin"] =5
    
    graph["fin"] ={}
    
    infinity = float("inf")
    costs={}
    costs["a"]=6
    costs["b"]=2
    costs["fin"]=infinity
    
    parents = {}
    parents["a"] = "start"
    parents["b"] = "start"
    parents["fin"] = None
    
    processed =[]

    print("前")
    print("开销表")
    printlist(costs)
    print("父节点表")
    printlist(parents)
    
    # 第一次选出b 将costs表中 a更新为5父节点更新为b fin 7父节点更新为b
    # 第二次选出a 将costs表中 fin更新为6 父节点更新为a
    # 最终开销最小路径 start->b->a->fin 3+2+1 = 6
    dijk(graph,parents,costs,processed)
    
    print("后")
    print("开销表")
    printlist(costs)
    print("父节点表")
    printlist(parents)
    """ 
    开销表
        a=>6
        b=>2
        fin=>inf
    父节点表
        a=>start
        b=>start
        fin=>None
    开销表
        a=>5
        b=>2
        fin=>6
    父节点表
        a=>b
        b=>start
        fin=>a 
    """
    
if __name__ == "__main__":
    main()
    
    
    
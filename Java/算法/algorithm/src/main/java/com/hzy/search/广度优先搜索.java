package com.hzy.search;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @title: 广度优先搜索
 * @Author zxwyhzy
 * @Date: 2023/4/8 15:26
 * @Version 1.0
 */
public class 广度优先搜索 {

}
/**
 * 邻接表方式，存储无向图
 * 使用连表的数组结构进行图信息的保存
 * 数组的下标代表的是图顶点本身，下标位置的连表，分别代表相连接的顶点信息
 */
class UndiGraph {
    /**
     * 标识图中顶点的个数
     */
    private int point;

    /**
     * 定义邻接表
     */
    private LinkedList<Integer> adjacencyList[];

    /**
     * 构造方法，初始化数据
     * @param point
     */
    public UndiGraph(int point){
        this.point = point;
        // 初始化连接表数组
        adjacencyList = new LinkedList[point];
        for (int i = 0; i < point; i++) {
            // 初始化邻接表的每一个槽位的连表
            adjacencyList[i] = new LinkedList<>();
        }
    }

    /**
     * 向图中，添加顶点和边的信息
     * @param s 源节点
     * @param t 目标节点
     */
    public void addPoint(int s,int t) {
        // 数组的下标，代表当前顶点，连表的数据代表的临边数据
        adjacencyList[s].add(t);
        // 因为没有方向，两个节点是互联关系，因此目标节点连线出也有源节点
        adjacencyList[t].add(s);
    }

    /**
     * bfs 广度优先算法实现，选择一条线路
     * 地毯式搜索，层层递进，直到找到为止
     * @param f 开始顶点
     * @param t 目标顶点
     */
    public void bfs(int f,int t){
        if (f == t){
            return;
        }
        // 定义一个boolean类型的数组，用来记录顶点是否被访问过
        boolean[] visited = new boolean[this.point];
        // 表明起始顶点已经被访问
        visited[f] = true;
        /**
         * 定义一个队列，存储已经被访问过的顶点，但是还有相邻顶点未被访问
         */
        Queue<Integer> queue = new LinkedList<>();
        queue.add(f);
        /**
         * 定义数组，用来存储s到t的线路,
         * 原理：每一个下标，代表的当前节点的值，而数组下标位置对应的数，就是指向自己的节点
         * 初始化为-1，代表每个节点，没有来源
         */
        int[] prev = new int[this.point];
        // 初始化线路为-1
        initRouter(prev);
        /**
         * 循环访问队列中没有被访问的顶点
         * 当队列不为空的时候，进行循环
         */
        while (!queue.isEmpty()){
            // 取出队列中的顶点，获取相邻顶点进行访问
            Integer p = queue.poll();
            // 遍历顶点的相邻顶点
            for (int i = 0; i < this.adjacencyList[p].size(); i++) {
                // 获取当前节点所连接的点
                Integer sibiling = this.adjacencyList[p].get(i);
                // 判断节点是否有被访问过，未访问过，则进行访问操作
                if (!visited[sibiling]){
                    prev[sibiling] = p;
                    if (sibiling==t){
                        printRouterST(prev,f,t);
                        return;
                    }
                    // 标记
                    visited[sibiling] = true;
                    // 相邻顶点存入队列
                    queue.add(sibiling);
                }
            }
        }
    }
    /**
     * 初始化路线
     * @param router
     */
    public void initRouter(int[] router){
        for (int i = 0; i < router.length; i++) {
            router[i] = -1;
        }
    }


    /**
     * 打印s到t的路线
     * @param prev
     * @param s
     * @param t
     */
    public void printRouterST(int[] prev,int s,int t){
        if (prev[t] != -1 && s != t){
            // prev记录的是当前路线，因此向前追溯，一直到开始节点依次打印
            printRouterST(prev,s,prev[t]);
        }
        System.out.print(t+">>");
    }

    public static void main(String[] args) {
        UndiGraph undiGraph = new UndiGraph(8);
        undiGraph.addPoint(0,1);
        undiGraph.addPoint(0,3);
        undiGraph.addPoint(1,2);
        undiGraph.addPoint(1,4);
        undiGraph.addPoint(2,5);
        undiGraph.addPoint(3,4);
        undiGraph.addPoint(4,5);
        undiGraph.addPoint(4,6);
        undiGraph.addPoint(5,7);
        undiGraph.addPoint(6,7);
        undiGraph.bfs(0,7);
    }
}


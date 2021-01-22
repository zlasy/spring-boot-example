package com.example.springbootcommon.quiz;

import java.util.LinkedList;
import java.util.Stack;

class Graph1 {
    private LinkedList[] adjLists;
    private final boolean[] visited;

    private Graph1(int vertices) {
        adjLists = new LinkedList[vertices];
        visited = new boolean[vertices];
        for (int i = 0; i < vertices; i++)
            adjLists[i] = new LinkedList<Integer>();
    }

    void addEdge(int src, int dest) {
        adjLists[src].add(dest);
    }

    void DFS(int vertex) {
        if (visited[vertex]) {//该节点已被访问
            return;
        }
        Stack<Integer> stack = new Stack<>();
        //该节点未被访问，或者，栈不为空
        while (!stack.isEmpty() || !visited[vertex]) {
            if(!visited[vertex]){
                //访问该结点
                System.out.println(vertex);
                visited[vertex] = true;
            }

            //找到从该节点出发的下一个未访问的结点
            boolean flag = false;
            for (int i = 0; i < adjLists[vertex].size(); i++) {
                int next = (int) adjLists[vertex].get(i);
                //找到了一个未访问的结点
                if (!visited[next]) {
                    //将该节点加入到栈中
                    stack.push(vertex);
                    vertex = next;
                    flag = true;
                    break;
                }
            }
            //如果该结点可以到达的下一个结点都访问过了，从栈中弹出一个结点
            if (!flag) {
                vertex = stack.pop();
            }
        }
    }

    public static void main(String[] args) {
        Graph1 g = new Graph1(5);
        g.addEdge(0, 2);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(1, 4);
        g.addEdge(2, 3);
        System.out.println("Following is Depth First Traversal");
        g.DFS(0);
    }
}
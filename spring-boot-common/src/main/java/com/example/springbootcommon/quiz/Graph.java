package com.example.springbootcommon.quiz;

import java.util.LinkedList;
class Graph{
    private LinkedList[] adjLists;
    private final boolean[] visited;

    private Graph(int vertices) {
        adjLists = new LinkedList[vertices];
        visited = new boolean[vertices];
        for (int i = 0; i < vertices; i++)
            adjLists[i] = new LinkedList<Integer>();
    }

    void addEdge(int src, int dest) {
        adjLists[src].add(dest);
    }

    void DFS(int vertex) {
        // TODO 补全深度优先遍历代码并打印
        visited[vertex] = true;
        System.out.print(vertex + " ");
        for (Object o : adjLists[vertex]) {
            int adj = (int) o;
            if (!visited[adj])
                DFS(adj);
        }
    }

    public static void main(String args[]) {
        Graph g = new Graph(5);
        g.addEdge(0, 2);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(1, 4);
        g.addEdge(2, 3);
        System.out.println("Following is Depth First Traversal");
        g.DFS(0);
    }
}
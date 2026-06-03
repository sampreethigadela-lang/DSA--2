import java.util.*;

class EdmondsKarp {

    static final int V = 6;

    boolean bfs(int[][] residualGraph, int s, int t, int[] parent) {

        boolean[] visited = new boolean[V];

        Queue<Integer> queue = new LinkedList<>();

        queue.add(s);
        visited[s] = true;
        parent[s] = -1;

        while (!queue.isEmpty()) {

            int u = queue.poll();

            for (int v = 0; v < V; v++) {

                if (!visited[v] && residualGraph[u][v] > 0) {

                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }

        return visited[t];
    }

    int edmondsKarp(int[][] graph, int source, int sink) {

        int u, v;

        int[][] residualGraph = new int[V][V];

        for (u = 0; u < V; u++)
            for (v = 0; v < V; v++)
                residualGraph[u][v] = graph[u][v];

        int[] parent = new int[V];

        int maxFlow = 0;

        while (bfs(residualGraph, source, sink, parent)) {

            int pathFlow = Integer.MAX_VALUE;

            for (v = sink; v != source; v = parent[v]) {
                u = parent[v];
                pathFlow = Math.min(pathFlow, residualGraph[u][v]);
            }

            for (v = sink; v != source; v = parent[v]) {
                u = parent[v];
                residualGraph[u][v] -= pathFlow;
                residualGraph[v][u] += pathFlow;
            }

            maxFlow += pathFlow;

            System.out.println("Flow Added: " + pathFlow);
            System.out.println("Current Total Flow: " + maxFlow);
            System.out.println();
        }

        return maxFlow;
    }

    public static void main(String[] args) {

        int[][] graph = {
            //S  A  B  C  D  T
            { 0,10,10, 0, 0, 0}, // S
            { 0, 0, 0, 4, 8, 0}, // A
            { 0, 0, 0, 9, 0, 0}, // B
            { 0, 0, 0, 0, 6,10}, // C
            { 0, 0, 0, 0, 0,10}, // D
            { 0, 0, 0, 0, 0, 0}  // T
        };

        EdmondsKarp maxFlow = new EdmondsKarp();

        int result = maxFlow.edmondsKarp(graph, 0, 5);

        System.out.println("Maximum Flow = " + result + " KL/hour");
    }
}
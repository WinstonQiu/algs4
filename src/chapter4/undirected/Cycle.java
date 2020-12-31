package chapter4.undirected;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Cycle {
    private boolean[] marked;
    private boolean hasCycle = false;

    public Cycle(Graph G) {
        marked = new boolean[G.V()];
        for (int s = 0; s < G.V(); ++s) {
            if (!marked[s]) {
                dfs(G, s, s);
            }
        }
    }

    // 深度优先搜索，v为当前顶点，u为前一个顶点，u属于v的相邻顶点，
    // 如果对v的相邻顶点的dfs碰到了非u的标记顶点，就说明遇到了环
    private void dfs(Graph G, int v, int u) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w, v);
            } else if (w != u) {
                hasCycle = true;
            }
        }
    }

    public boolean hasCycle() {
        return hasCycle;
    }

    public static void main(String[] args) {
        Graph G = new Graph(new In(args[0]));
        Cycle cycle = new Cycle(G);

        if (cycle.hasCycle()) {
            StdOut.println("This graph has cycle!");
        } else {
            StdOut.println("This graph has no cycle!");
        }
    }
}

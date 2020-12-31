package chapter4;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class DepthFirstSearch extends Search {

    public DepthFirstSearch(Graph G, int s) {
        super(G);
        dfs(G, s);
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        ++count;
        for (int w : G.adj(v)) {
            if (!marked[w]) dfs(G, w);
        }
    }

    public static void main(String[] args) {
        Graph G = new Graph(new In(args[0]));
        int s = Integer.parseInt(args[1]);
        Search search = new DepthFirstSearch(G, s);

        for (int v = 0; v < G.V(); ++v) {
            if (search.marked(v)) StdOut.print(v + " ");
        }
        StdOut.println();

        if (search.count() != G.V()) {
            StdOut.print("NOT ");
        }
        StdOut.println("connected: " + search.count());
    }
}

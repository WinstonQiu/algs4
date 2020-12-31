package chapter4;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch extends Search {

    protected BreadthFirstSearch(Graph G, int s) {
        super(G);
        bfs(G, s);
    }

    private void bfs(Graph G, int s) {
        Queue<Integer> queue = new LinkedList<>();
        marked[s] = true;
        ++count;
        queue.add(s);
        while (!queue.isEmpty()) {
            int v = queue.remove();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    marked[w] = true;
                    ++count;
                    queue.add(w);
                }
            }
        }
    }

    public static void main(String[] args) {
        Graph G = new Graph(new In(args[0]));
        int s = Integer.parseInt(args[1]);
        Search search = new BreadthFirstSearch(G, s);

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

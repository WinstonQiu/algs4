package chapter4.undirected;

import chapter4.undirected.Graph;

public abstract class Search {
    protected boolean[] marked;
    protected int count = 0;

    protected Search(Graph G) {
        marked = new boolean[G.V()];
    }

    // v和s是否连通
    public boolean marked(int v) {
        return marked[v];
    }

    // 与s连通的顶点总数
    public int count() {
        return count;
    }
}

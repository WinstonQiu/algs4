package chapter4.shortestPath;

public class DirectedEdge {
    private final int v;        // 边的起点
    private final int w;        // 边的终点
    private final double weight;// 边的权重

    public DirectedEdge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    // 边的权重
    public double weight() {
        return weight;
    }

    // 指出这条边的顶点
    public int from() {
        return v;
    }

    // 这条边指向的顶点
    public int to() {
        return w;
    }

    public String toString() {
        return String.format("%d->%d %.2f", v, w, weight);
    }
}

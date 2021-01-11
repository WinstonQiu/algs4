package chapter4.mst;

public class Edge implements Comparable<Edge> {
    private final int v;        // 顶点之一
    private final int w;        // 另一个顶点
    private final double weight;// 边的权重

    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    // 边的权重
    public double weight() {
        return weight;
    }

    // 边两端的一个顶点
    public int either() {
        return v;
    }

    // 另一个顶点
    public int other(int v) {
        if      (v == this.v) return this.w;
        else if (v == this.w) return this.v;
        else throw new RuntimeException("Inconsistent edge.");
    }

    @Override
    public int compareTo(Edge that) {
        return Double.compare(this.weight, that.weight);
    }

    @Override
    public String toString() {
        return String.format("%d-%d~%.2f", v, w, weight);
    }
}

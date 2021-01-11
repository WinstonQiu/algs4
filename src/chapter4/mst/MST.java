package chapter4.mst;

public interface MST {
    // 最小生成树的所有边
    Iterable<Edge> edges();
    // 最小生成树的权重
    double weight();
}

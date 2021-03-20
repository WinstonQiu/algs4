package chapter4.shortestPath;

public interface SP {
    // 构造函数需给定起点s
    // 从顶点s到v的距离，如果不存在则路径为无穷大
    double distTo(int v);
    // 是否存在从顶点s到v的路径
    boolean hasPathTo(int v);
    // 从顶点s到v的路径，如果不存在则为null
    Iterable<DirectedEdge> pathTo(int v);
}

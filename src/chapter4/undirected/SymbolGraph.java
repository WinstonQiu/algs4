package chapter4.undirected;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.HashMap;
import java.util.Map;

public class SymbolGraph {
    private Map<String, Integer> st;
    private String[] keys;
    private Graph G;

    // 根据stream指定的流构造图，使用delim分隔顶点名
    public SymbolGraph(String stream, String delim) {
        st = new HashMap<String, Integer>();
        In in = new In(stream);
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(delim);

            for (String s : a) {
                if (!st.containsKey(s)) {
                    st.put(s, st.size());
                }
            }
        }
        keys = new String[st.size()];

        for (String name : st.keySet()) {
            keys[st.get(name)] = name;
        }

        G = new Graph(st.size());
        in = new In(stream);
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(delim);
            int v = st.get(a[0]);
            for (String s : a) {
                G.addEdge(v, st.get(s));
            }
        }
    }

    // key是否为一顶点
    public boolean contains(String key) {
        return st.containsKey(key);
    }

    // key的索引
    public int index(String key) {
        return st.get(key);
    }

    // 索引v的顶点名
    public String name(int v) {
        return keys[v];
    }

    // 隐藏的Graph对象
    public Graph G() {
        return G;
    }

    public static void main(String[] args) {
        SymbolGraph sg = new SymbolGraph(args[0], args[1]);

        Graph G = sg.G();

        while (StdIn.hasNextLine()) {
            String source = StdIn.readLine();
            StdOut.println(source);
            for (int w : G.adj(sg.index(source))) {
                StdOut.println("    " + sg.name(w));
            }
        }
    }
}

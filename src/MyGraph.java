import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class MyGraph implements DirectedWeightedGraph {
    Map<Integer, NodeData> nodes;
    Map<String, EdgeData> edgesMap;
    int MC;

    public MyGraph() {
        this.nodes = new HashMap<>();
        edgesMap = new HashMap<>();
    }

    public void MapEdgesToNodes() {
        try {
            for (EdgeData edge : this.edgesMap.values()) {
                ((MyNode) nodes.get(edge.getSrc())).EdgesOut.put(edge.getDest(), edge);
                ((MyNode) nodes.get(edge.getDest())).EdgesIn.put(edge.getSrc(), edge);
            }
        } catch (Exception e) {
            System.out.println("The graph might not initialized");
        }
    }

    @Override
    public NodeData getNode(int key) {
        return this.nodes.get(key);
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
        return ((MyNode) this.nodes.get(src)).EdgesOut.get(dest);
    }

    @Override
    public void addNode(NodeData n) {
        nodes.put(n.getKey(), new MyNode(n));
    }

    @Override
    public void connect(int src, int dest, double w) {
        MyEdge temp_edge = new MyEdge(src, dest, w);
        ((MyNode) nodes.get(dest)).connectInEdges(temp_edge);
        ((MyNode) nodes.get(src)).connectOutEdges(temp_edge);
        this.edgesMap.put(src + "," + dest, temp_edge);
    }

    @Override
    public Iterator<NodeData> nodeIter() {
        return nodes.values().iterator();
    }

    @Override
    public Iterator<EdgeData> edgeIter() {
        return edgesMap.values().iterator();
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        return ((MyNode) (this.nodes.get(node_id))).EdgesOut.values().iterator();
    }

    @Override
    public NodeData removeNode(int key) {
        NodeData node = nodes.get(key); //note: to assert key == m.getSrc
        // iterate over all edges coming out and delete:
        for (EdgeData m : ((MyNode) nodes.get(key)).EdgesOut.values()) {
            ((MyNode) nodes.get(m.getDest())).EdgesIn.remove(key);
            this.edgesMap.remove(m.getSrc()+","+m.getDest());
        }
        // iterate over all edges coming in and delete:
        for (EdgeData m : ((MyNode) nodes.get(key)).EdgesIn.values()) {
            ((MyNode) nodes.get(m.getSrc())).EdgesOut.remove(key);
            this.edgesMap.remove(m.getSrc()+","+m.getDest());
        }

        nodes.remove(key);
        return node;
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        EdgeData temp = ((MyNode) this.nodes.get(src)).EdgesOut.get(dest);
        ((MyNode) this.nodes.get(src)).EdgesOut.remove(dest);
        ((MyNode) this.nodes.get(dest)).EdgesIn.remove(src);
        edgesMap.remove(src+","+dest);
        return temp;
    }

    @Override
    public int nodeSize() {
        return nodes.size();
    }

    @Override
    public int edgeSize() {
        return edgesMap.size();
    }

    @Override
    public int getMC() {
        return MC;
    }

    @Override
    public String toString() {
        return "MyGraph{" +
                "nodes=" + nodes +
                ", edgesMap=" + edgesMap +
                ", MC=" + MC +
                '}';
    }
}

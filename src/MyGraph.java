import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class MyGraph implements DirectedWeightedGraph {
    Map<Integer,NodeData> nodes;
    Map<Integer, EdgeData> edgesMap;
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
        nodes.put(n.getKey(),new MyNode(n));
    }

    @Override
    public void connect(int src, int dest, double w) {
        MyEdge temp_edge = new MyEdge(src, dest, w);
        ((MyNode) this.nodes.get(dest)).connectInEdges(temp_edge);
        ((MyNode) this.nodes.get(src)).connectOutEdges(temp_edge);
        this.edgesMap.put(src, temp_edge);
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
        NodeData temp = nodes.get(key); //note: to assert key == m.getSrc
        while (edgeIter(key).hasNext()) { // iterate the all edges going out
            EdgeData m = edgeIter(key).next();
            ((MyNode) nodes.get(m.getDest())).EdgesIn.remove(m.getSrc()); // delete the out edges in each dest node hashmap
            this.edgesMap.remove(m.getDest());// delete the income edges from the Graph hashmap
        }
        // iterate the all edges coming in each source node hashmap and delete  :
        ((MyNode) nodes.get(key)).EdgesIn.forEach((k, v) -> ((MyNode) nodes.get(v.getDest())).EdgesOut.remove(v.getDest()));
        this.edgesMap.remove(key);  // delete the outcome edge from the Graph hashmap

        nodes.remove(key);
        return temp;
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        EdgeData temp = ((MyNode) this.nodes.get(src)).EdgesOut.get(dest);
        ((MyNode) this.nodes.get(src)).EdgesOut.remove(dest);
        ((MyNode) this.nodes.get(dest)).EdgesIn.remove(src);
        edgesMap.remove(src);
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

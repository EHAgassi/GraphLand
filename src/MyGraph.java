import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class MyGraph implements DirectedWeightedGraph {
    ArrayList<NodeData> nodes;
    Map<Integer, EdgeData> edgesMap;
    int MC;

    public MyGraph(){
       this.nodes = new ArrayList<>();
       edgesMap = new HashMap<>();
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
        nodes.add(new MyNode(n));
    }

    @Override
    public void connect(int src, int dest, double w) {
        MyEdge temp_edge = new MyEdge(src,dest,w);
        ((MyNode)this.nodes.get(dest)).connectInEdges(temp_edge);
        ((MyNode)this.nodes.get(src)).connectOutEdges(temp_edge);

    }

    @Override
    public Iterator<NodeData> nodeIter() {
        return nodes.iterator();
    }

    @Override
    public Iterator<EdgeData> edgeIter() {
        return edgesMap.values().iterator();
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
       return ((MyNode)(this.nodes.get(node_id))).EdgesOut.values().iterator();
    }

    @Override
    public NodeData removeNode(int key) {
        NodeData temp = nodes.get(key);
        nodes.remove(key);
        while (edgeIter(key).hasNext()){
            edgesMap.remove(key);
            }
        return temp;
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        EdgeData temp = ((MyNode)this.nodes.get(src)).EdgesOut.get(dest);
        edgesMap.remove(src,temp);
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
}

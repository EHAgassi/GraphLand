import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.EdgeData;
import api.NodeData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class MyAlgorithm implements DirectedWeightedGraphAlgorithms {

    MyGraph graph;

    @Override
    public void init(DirectedWeightedGraph g) {
        this.graph = (MyGraph) g;
    }

    @Override
    public DirectedWeightedGraph getGraph() {
        return this.graph;
    }

    @Override
    public DirectedWeightedGraph copy() {
        MyGraph new_graph = new MyGraph();
        new_graph.nodes = this.graph.nodes.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        new_graph.edgesMap = this.graph.edgesMap.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        new_graph.MC = this.graph.MC;
        return new_graph;
    }

    @Override
    public boolean isConnected() {

        for (NodeData node1 : graph.nodes.values())
            for (NodeData node2 : graph.nodes.values()) {
                if (node1 != node2) {
                    if (!DFS((MyNode) node1, (MyNode) node2))
                        return false;
                }
            }
        return true;
    }

    public void resetTag() {
        for (NodeData nd : this.graph.nodes.values()) { // initialize the nodes tag to 0
            nd.setTag(0);
        }
    }

    public boolean DFS(MyNode src, MyNode dest) {
        Map<Integer, NodeData> nodes = this.graph.nodes;
        resetTag();
        Gray(src.node_key);
        if (src == dest) return true;

        Stack<NodeData> stack = new Stack<>();
        stack.push(nodes.get(src.node_key));

        while (!stack.isEmpty()) {
            NodeData nd = stack.pop();
            for (EdgeData edge : ((MyNode) nd).EdgesOut.values()) {
                NodeData node = nodes.get(edge.getDest());
                if (node.getKey() == dest.node_key) return true;
                if (node.getTag() != 1) {
                    Gray(node.getKey());
                    stack.push(node);
                }
            }
        }
        return false;
    }

    void Gray(int n) {
        graph.nodes.get(n).setTag(1);
    }

    void Black(int n) {
        graph.nodes.get(n).setTag(2);
    }

    @Override
    public double shortestPathDist(int src, int dest) {
        return 0;
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        return null;
    }

    public List<NodeData> DijkstraAlgo(int src, int dest) {
        int Infinity = Integer.MAX_VALUE;
        resetTag();
        List<NodeData> shortPath = new LinkedList<>();


        HashMap<Integer, Integer> distFromSrc = new HashMap<>(); //load the nodes to length hashmap and label as Infinity
        for (NodeData nd : graph.nodes.values()) {
            distFromSrc.put(nd.getKey(), Infinity);
        }

        distFromSrc.replace(0,0);
        for  (NodeData nd : graph.nodes.values()) {
            for  (EdgeData edgeOut :((MyNode)nd).EdgesOut.values()) {
                //update the minimal path to current node:
                distFromSrc.replace(edgeOut.getDest(), minimalDist(distFromSrc,graph.nodes.get(edgeOut.getDest())));

            }



        }

        return shortPath;
    }

    private int minimalDist(HashMap<Integer, Integer> distMap,NodeData node) {
        int index = -1, min = Integer.MAX_VALUE;
//        for (NodeData nd : graph.nodes.values()) {
//            if (nd.getTag() == 0 && dist.get(nd.getKey()) <= min) {
//                min = dist.get(nd.getKey());
//                index = nd.getKey();
//            }
//        }
        return index;
    }

    @Override
    public NodeData center() {
        return null;
    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        return null;
    }

    @Override
    public boolean save(String file) {
        return false;
    }

    @Override
    public boolean load(String file) {
        try {
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(MyGraph.class, new MyGraphDeserialize());
            Gson gson = builder.setPrettyPrinting().create();

            Reader reader = Files.newBufferedReader(Paths.get(file));
            graph = gson.fromJson(reader, MyGraph.class);

            System.out.println(graph);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public static void main(String[] args) {

    }
}

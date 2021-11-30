import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.NodeData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

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
        return null;
    }

    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public double shortestPathDist(int src, int dest) {
        return 0;
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        return null;
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
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(MyGraph.class,new MyGraphDeserialize());
        Gson gson = builder.setPrettyPrinting().create();

        graph = gson.fromJson("./data/G1.json",MyGraph.class);
        System.out.println(graph);
        if(graph.nodes.get(0)!=null) {
            return true;
        }
        else return false;
    }

}

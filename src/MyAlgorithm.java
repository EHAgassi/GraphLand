import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.NodeData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
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
        new_graph.nodes = this.graph.nodes.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
//        new_graph.edgesMap = this.graph.edgesMap.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
        new_graph.MC = this.graph.MC;
       return new_graph;
    }

    @Override
    public boolean isConnected() {
//        for (NodeData.) {
//
//        }

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

}

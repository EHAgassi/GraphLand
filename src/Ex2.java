import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.NodeData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * This class is the main class for Ex2 - your implementation will be tested using this class.
 */
public class Ex2 {
    /**
     * This static function will be used to test your implementation
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraph getGrapg(String json_file) {
        DirectedWeightedGraph ans = new MyGraph();
        try {
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(MyGraph.class, new MyGraphDeserialize());
            Gson gson = builder.setPrettyPrinting().create();

            Reader reader = Files.newBufferedReader(Paths.get(json_file));
            ans = gson.fromJson(reader, MyGraph.class);

            System.out.println(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ans;
    }

    /**
     * This static function will be used to test your implementation
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraphAlgorithms getGrapgAlgo(String json_file) {
        DirectedWeightedGraphAlgorithms ans = null;
        // ****** Add your code here ******
        //
        // ********************************
        return ans;
    }

    /**
     * This static function will run your GUI using the json fime.
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     */
    public static void runGUI(String json_file) {
        DirectedWeightedGraphAlgorithms alg = getGrapgAlgo(json_file);
        // ****** Add your code here ******
        //
        // ********************************
    }

    public static void main(String[] args) {
        MyGraph mg = (MyGraph) getGrapg("data\\G3.json");

//        MyNode my = new MyNode(134, new Location(3, 4, 5));
//        mg.addNode(my);
//        mg.connect(1, 3, 5.88888);
//        System.out.println(mg.edgeSize());
//        System.out.println(mg.getMC());
//        mg.removeEdge(1, 3);
//        mg.removeNode(3);
//        mg.removeNode(0);
//        mg.removeNode(8);
//        Iterator<EdgeData> iter = mg.edgeIter();
//        while (iter.hasNext()){
//            System.out.println(iter.next());
//        }
        MyGraph tempGraph = new MyGraph();
        tempGraph.nodes.put(0,new MyNode(0,new Location(1,2,0)));
        tempGraph.nodes.put(1,new MyNode(1,new Location(3,4,0)));
        tempGraph.nodes.put(2,new MyNode(2,new Location(0,5,0)));
        for (NodeData mn : tempGraph.nodes.values()) {
            for (NodeData omn : tempGraph.nodes.values()) {
                if (mn != omn) {
                    tempGraph.connect(mn.getKey(), omn.getKey(), omn.getKey() + 3);
                    tempGraph.connect(omn.getKey(), mn.getKey(), omn.getKey() + 2);
                }
            }
        }
       tempGraph.removeEdge(0,1);
        tempGraph.removeEdge(1,0);
       tempGraph.removeEdge(0,2);
//        tempGraph.removeEdge(2,0);
//        System.out.println(tempGraph);

        MyAlgorithm ma = new MyAlgorithm();
        ma.graph = tempGraph;
        System.out.println(ma.isConnected());

    }
}
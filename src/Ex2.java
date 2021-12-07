import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.swing.*;
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
//        MyNode my = new MyNode(134, new Location(3, 4, 5));
//        ans.addNode(my);
//        ans.connect(1, 3, 5.88888);
//        System.out.println(ans.edgeSize());
//        System.out.println(ans.getMC());
//        ans.removeEdge(1, 3);
//        ans.removeNode(3);
//        ans.removeNode(0);
        System.out.println(ans);
        while (ans.edgeIter().hasNext()){
            System.out.println(ans.edgeIter().next());
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
        DirectedWeightedGraphAlgorithms ans = new MyAlgorithm();
        ((MyAlgorithm)ans).graph =  ((MyGraph)getGrapg(json_file));
        return ans;
    }

    /**
     * This static function will run your GUI using the json fime.
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     */
    public static void runGUI(String json_file) {
        DirectedWeightedGraphAlgorithms alg = getGrapgAlgo(json_file);
        MyGraph ma = (MyGraph) alg.getGraph();
        class MyFrames extends JFrame {
            public void printg(MyGraph mg) {
                this.setSize(500, 500);


            }
        }


    }

    public static void main(String[] args) {
        getGrapg("data\\G2.json");

    }
}
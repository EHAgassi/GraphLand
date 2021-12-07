import com.google.gson.*;
import java.lang.reflect.Type;

public class MyGraphDeserialize implements JsonDeserializer<MyGraph> {
    @Override
    public MyGraph deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
       MyGraph theGraph = new MyGraph();
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        JsonArray jNodeArr = jsonObject.getAsJsonArray("Nodes"); //read Nodes from Json
        for (int i = 0; i <jNodeArr.size() ; i++) {
            JsonObject jasonObj = (JsonObject) jNodeArr.get(i);
            Location location = new Location(jasonObj.get("pos").getAsString());
            MyNode me = new MyNode(jasonObj.get("id").getAsInt(),location);
            theGraph.nodes.put(me.node_key,me);
        }

        JsonArray jEdgeArr = jsonObject.getAsJsonArray("Edges"); // read Edges from Json
        for (int i = 0; i <jEdgeArr.size() ; i++) {
            JsonObject jasonObj = (JsonObject) jEdgeArr.get(i);
            MyEdge me = new MyEdge(jasonObj.get("src").getAsInt(),jasonObj.get("dest").getAsInt(),jasonObj.get("w").getAsDouble());
            ((MyNode)theGraph.nodes.get(me.src)).EdgesOut.put(me.dest,me);
            ((MyNode)theGraph.nodes.get(me.dest)).EdgesIn.put(me.src,me);
            theGraph.edgesNum++;
        }


        return theGraph;
    }
}

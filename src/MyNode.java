import api.EdgeData;
import api.GeoLocation;
import api.NodeData;

import java.util.HashMap;
import java.util.Map;

public class MyNode implements NodeData {
    int node_key;
    Location position;
    int tag;
    String info;

    public Map<Integer, EdgeData> EdgesIn; // key = node it's going to
    public Map<Integer, EdgeData> EdgesOut; // key = node it's came from

    public MyNode(int node_key, Location position) {
        this.node_key = node_key;
        this.position = position;
        EdgesIn = new HashMap<>();
        EdgesOut = new HashMap<>();
    }

    public MyNode(NodeData n) {
        this.node_key = n.getKey();
        this.position = new Location(n.getLocation().x(), n.getLocation().y(), n.getLocation().z());
    }

    public void connectOutEdges(EdgeData edge) {
        EdgesOut.put(edge.getDest(), edge);
    }

    public void connectInEdges(EdgeData edge) {
        EdgesIn.put(edge.getSrc(), edge);
    }

    @Override
    public int getKey() {
        return node_key;
    }

    @Override
    public GeoLocation getLocation() {
        return position;
    }

    @Override
    public void setLocation(GeoLocation p) {
        this.position.x_pos = p.x();
        this.position.y_pos = p.y();
        this.position.z_pos = p.z();
    }


    @Override   //irrelevant
    public double getWeight() {
        return 0;
    }

    @Override //irrelevant
    public void setWeight(double w) {
    }

    @Override
    public String getInfo() {
        return null;
    }

    @Override
    public void setInfo(String s) {

    }

    @Override
    public int getTag() {
        return this.tag;
    }

    @Override
    public void setTag(int t) {
        this.tag = t;
    }

    @Override
    public String toString() {
        return "MyNode{" +
                "node_key=" + node_key +
                ", position=" + position +
                ", EdgesIn=" + EdgesIn +
                ", EdgesOut=" + EdgesOut +
                '}';
    }

}

import api.EdgeData;
import api.GeoLocation;
import api.NodeData;

import java.util.Map;

public class MyNode implements NodeData {
    int node_key;
    Location position;
    int tag;
    String info;

    Map<Integer,EdgeData> inEdgesSrcIndex;
    Map<Integer,EdgeData> outEdgesDestIndex;

    public MyNode(int node_key, Location position) {
        this.node_key = node_key;
        this.position = position;

    }

    public MyNode(NodeData n) {
        this.node_key = n.getKey();
        this.position = new Location(n.getLocation().x(),n.getLocation().y(),n.getLocation().z());

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
        return 0;
    }

    @Override
    public void setTag(int t) {

    }
}

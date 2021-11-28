import api.GeoLocation;
import api.NodeData;

public class MyNode implements NodeData {
    int node_key;
    int x_pos;
    int y_pos;
    int z_pos;

    public MyNode(int node_key, int x_pos, int y_pos, int z_pos) {
        this.node_key = node_key;
        this.x_pos = x_pos;
        this.y_pos = y_pos;
        this.z_pos = z_pos;
    }


    @Override
    public int getKey() {
        return 0;
    }

    @Override
    public GeoLocation getLocation() {
        return null;
    }

    @Override
    public void setLocation(GeoLocation p) {

    }

    @Override
    public double getWeight() {
        return 0;
    }

    @Override
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

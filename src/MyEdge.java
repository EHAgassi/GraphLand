import api.EdgeData;

public class MyEdge implements EdgeData {

    int src;
    int dest;
    double weight;


    public MyEdge(int src, int dest, double weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public int getSrc() {
        return  this.src ;
    }

    @Override
    public int getDest() {
        return this.dest;
    }

    @Override
    public double getWeight() {
        return this.weight;
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
    public void setTag(int num) {

    }

    @Override
    public String toString() {
        return "MyEdge{" +
                "src=" + src +
                ", dest=" + dest +
                ", weight=" + weight +
                '}';
    }
}

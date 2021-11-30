import api.GeoLocation;

import java.util.Scanner;

public class Location implements GeoLocation {
    double x_pos;
    double y_pos;
    double z_pos;

    public Location(double x_pos, double y_pos, double z_pos) {
        this.x_pos = x_pos;
        this.y_pos = y_pos;
        this.z_pos = z_pos;
    }

    public Location(String s) {
        Scanner sr = new Scanner(s);
        sr.useDelimiter(",");
        this.x_pos = Double.parseDouble(sr.next());
        this.y_pos = Double.parseDouble(sr.next());
        this.z_pos = Double.parseDouble(sr.next());
    }

    @Override
    public double x() {
        return this.x_pos;
    }

    @Override
    public double y() {
        return this.y_pos;
    }

    @Override
    public double z() {
        return this.z_pos;
    }

    @Override
    public double distance(GeoLocation g) {
        return (Math.pow(this.x_pos - g.x(), 2) + Math.pow(this.y_pos - g.y(), 2) + Math.pow(this.z_pos - g.z(), 2));
    }
}

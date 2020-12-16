package models;

import kg2019examples_task4threedimensions.math.Vector3;
import kg2019examples_task4threedimensions.third.IModel;
import kg2019examples_task4threedimensions.third.PolyLine3D;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Pyramid implements IModel {

   private List<PolyLine3D> planes = new ArrayList<>();
    private Vector3 LF;

    public Pyramid (Vector3 LF, float r, int step, float h){
this.LF = LF;
Vector3 centerCircle = new Vector3(LF.getX()+r/2, LF.getY(), LF.getZ()-r/2);
Vector3 vertex = new Vector3(centerCircle.getX(), centerCircle.getY()+h, centerCircle.getZ());

planes.add(new PolyLine3D(turnCircle(createFirstCircle(r, step), 1, 1, centerCircle),true, new Color(0x5700F7FF, true)));
        for (int i = 0; i < planes.get(0).getPoints().size()-1; i++) {
            List<Vector3> points = new ArrayList<>();
            points.add(planes.get(0).getPoints().get(i));
            points.add(planes.get(0).getPoints().get(i+1));
            points.add(vertex);
            planes.add(new PolyLine3D(points, true, new Color(0x5700F7FF, true)));
        }
        List<Vector3> points = new ArrayList<>();
        points.add(planes.get(0).getPoints().get(0));
        points.add(planes.get(0).getPoints().get(planes.get(0).getPoints().size()-1));
        points.add(vertex);
        planes.add(new PolyLine3D(points, true, new Color(0x5700F7FF, true)));
        System.out.println("countPlanes = " + planes.size());
    }

    private List<Vector3> createFirstCircle(double r, int step) {
        List<Vector3> points = new ArrayList<>();
        double rad = 2 * Math.PI / step;
        for (int i = 0; i < step; i++) {
            double dx1 = r * Math.cos(rad * i);
            double dy1 = r * Math.sin(rad * i);
            points.add(new Vector3((float) dx1, (float) dy1, 0));
        }
        return points;
    }
    private List<Vector3> turnCircle(List<Vector3> pointCircle, int step, int i, Vector3 centreSphere) {
        double rad = 2 * i * Math.PI / step;
        List<Vector3> points = new ArrayList<>();

        for (Vector3 p : pointCircle) {
            points.add(new Vector3(p.getX() + centreSphere.getX(), (float) (p.getY() * Math.sin(rad)) + centreSphere.getY(),
                    (float) (p.getY() * Math.cos(rad)) + centreSphere.getZ()));
//            for (int j = 0; j < points.size(); j++) {
//                System.out.println("p" +points.get(j).getX() + " "+
//                        points.get(j).getY() +" "+
//                        points.get(j).getZ());
//            }
        }
        return points;
    }

    @Override
    public List<PolyLine3D> getLines() {
        return planes;
    }

    @Override
    public Vector3 getMin() {
        Vector3 currMin = planes.get(0).getPoints().get(0);
        float minX = currMin.getX();
        float minY = currMin.getY();
        float minZ = currMin.getZ();

        for (PolyLine3D line : planes) {
            for (Vector3 v : line.getPoints()) {
                if (v.getX() <= minX) {
                    minX = v.getX();
                }
                if (v.getY() <= minY) {
                    minY = v.getY();
                }
                if (v.getZ() <= minZ) {
                    minZ = v.getZ();
                }
            }
        }
        return new Vector3(minX, minY, minZ);
    }

    @Override
    public Vector3 getMax() {
        Vector3 currMax = planes.get(0).getPoints().get(0);
        float maxX = currMax.getX();
        float maxY = currMax.getY();
        float maxZ = currMax.getZ();

        for (PolyLine3D line : planes) {
            for (Vector3 v : line.getPoints()) {
                if (v.getX() >= maxX) {
                    maxX = v.getX();
                }
                if (v.getY() >= maxY) {
                    maxY = v.getY();
                }
                if (v.getZ() >= maxZ) {
                    maxZ = v.getZ();
                }
            }
        }
        return new Vector3(maxX, maxY, maxZ);
    }
}

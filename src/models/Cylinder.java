package models;

import kg2019examples_task4threedimensions.math.Vector3;
import kg2019examples_task4threedimensions.third.IModel;
import kg2019examples_task4threedimensions.third.PolyLine3D;
import kg2019examples_task4threedimensions.third.Scene;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Cylinder implements IModel {
    private List<PolyLine3D> planes = new ArrayList<>();
    private Vector3 centerBottom;
    private Vector3 RBN;
    private Color color;

    public Cylinder(Vector3 centerBottom, float r, float h, int step, Color color) {
        this.centerBottom = centerBottom;
        this.color = color;
        List<Vector3> listBottomCircle = createFirstCircle(centerBottom.getY(), r, step);
        List<Vector3> listTopCircle = createFirstCircle(centerBottom.getY()+h, r, step);
        System.out.println("SIZE = " + listBottomCircle.size());



        for (int i = 0; i < step; i++) {

            if (i > 0) {
                List<Vector3> pointBottomCircle = new ArrayList<>();
                pointBottomCircle.add(listBottomCircle.get(i));
                pointBottomCircle.add(listBottomCircle.get(i-1));
                pointBottomCircle.add(centerBottom);
                planes.add(new PolyLine3D(pointBottomCircle, true, color));

                List<Vector3> pointTopCircle = new ArrayList<>();
                pointTopCircle.add(listTopCircle.get(i));
                pointTopCircle.add(listTopCircle.get(i-1));
                pointTopCircle.add(new Vector3(centerBottom.getX(), centerBottom.getY()+h, centerBottom.getZ()));
                planes.add(new PolyLine3D(pointTopCircle, true, color));

                List<Vector3> points = new ArrayList<>();
                points.add(listBottomCircle.get(i-1));
                points.add(listTopCircle.get(i));
                points.add(listBottomCircle.get(i));
                planes.add(new PolyLine3D(points, true, color));
                points.remove(2);
                points.add(listTopCircle.get(i-1));
                planes.add(new PolyLine3D(points, true, color));
            }
        }

        List<Vector3> pointCircle = new ArrayList<>();
        pointCircle.add(listBottomCircle.get(0));
        pointCircle.add(listBottomCircle.get(listBottomCircle.size()-1));
        pointCircle.add(centerBottom);
        planes.add(new PolyLine3D(pointCircle, true, color));

        List<Vector3> pointTopCircle = new ArrayList<>();
        pointTopCircle.add(listTopCircle.get(0));
        pointTopCircle.add(listTopCircle.get(listBottomCircle.size()-1));
        pointTopCircle.add(new Vector3(centerBottom.getX(), centerBottom.getY()+h, centerBottom.getZ()));
        planes.add(new PolyLine3D(pointTopCircle, true, color));

        List<Vector3> points = new ArrayList<>();
        points.add(listBottomCircle.get(listBottomCircle.size()-1));
        points.add(listTopCircle.get(0));
        points.add(listBottomCircle.get(0));
        planes.add(new PolyLine3D(points, true, color));
        points.remove(2);
        points.add(listTopCircle.get(listBottomCircle.size()-1));
        planes.add(new PolyLine3D(points, true, color));
    }

//    private void createSphere(int step) {
//        Parallelepiped cube = new Parallelepiped(LTF, RBN, color);
//
//
//
//        Vector3 centreSphere = new Vector3(
//                (LTF.getX() + RBN.getX()) / 2,
//                (LTF.getY() + RBN.getY()) / 2,
//                (LTF.getZ() + RBN.getZ()) / 2);
//        Vector3 centreEdge1 = new Vector3(LTF.getX()+RBN.getX()/2, LTF.getY(), LTF.getZ()+RBN.getZ()/2);
//        Vector3 vect1 = new Vector3(centreEdge1.getX() - centreSphere.getX(),
//                centreEdge1.getY() - centreSphere.getY(),
//                centreEdge1.getZ() - centreSphere.getZ());
////        Vector3 vect2 = new Vector3(-vect1.getX(), -vect1.getY(), -vect1.getZ());
//        double r = Math.sqrt(Math.pow(vect1.getX(), 2) + Math.pow(vect1.getY(), 2) + Math.pow(vect1.getZ(), 2));
//
//        List<Vector3> pointsCircle = createFirstCircle(r, step);
//
//
//        List<Vector3> prevPoints = turnCircle(pointsCircle, step, 0, centreSphere);
//        planes.add(new PolyLine3D(prevPoints, true, color));
////
////        for (int i = 0; i < step + 1; i++) {
////            List<Vector3> points = turnCircle(pointsCircle, step, i, centreSphere);
////            for (int j = 0; j < step / 2 + 1; j++) {
////                if ((i > 0) && (j > 0)) {
////                    List<Vector3> p = new ArrayList<>();
////                    p.add(points.get(j));
////                    p.add(prevPoints.get(j - 1));
////                    if (j == 1) {
////                        p.add(prevPoints.get(j));
////                    } else {
////                        if (j == step / 2) {
////                            p.add(points.get(j - 1));
////                        } else {
////                            p.add(prevPoints.get(j));
////                            planes.add(new PolyLine3D(p, true, color));
////                            p.remove(2);
////                            p.add(points.get(j - 1));
////                        }
////                    }
////                    planes.add(new PolyLine3D(p, true, color));
////                }
////            }
////            prevPoints = turnCircle(pointsCircle, step, i, centreSphere);
////        }
////        System.out.println("planes = " + planes.size());
//    }

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

    private List<Vector3> createFirstCircle(float y, double r, int step) {
        List<Vector3> points = new ArrayList<>();
        double rad = 2 * Math.PI / step;
        for (int i = 0; i < step; i++) {
            double dx1 = r * Math.cos(rad * i);
            double dz1 = r * Math.sin(rad * i);
            points.add(new Vector3((float) dx1, y, (float) dz1));
        }
        return points;
    }

    private Vector3 findCentreEdge(List<Vector3> p) {
        double max = -1;
        Vector3 pFar = p.get(0);
        for (int i = 0; i < 3; i++) {
            double len = Math.sqrt((p.get(i + 1).getX() - p.get(0).getX()) * (p.get(i + 1).getX() - p.get(0).getX())
                    + (p.get(i + 1).getY() - p.get(0).getY()) * (p.get(i + 1).getY() - p.get(0).getY())
                    + (p.get(i + 1).getZ() - p.get(0).getZ()) * (p.get(i + 1).getZ() - p.get(0).getZ()));
            if (max < len) {
                max = len;
                pFar = p.get(i + 1);
            }
        }
        return new Vector3((pFar.getX() + p.get(0).getX()) / 2, (pFar.getY() + p.get(0).getY()) / 2, (pFar.getZ() + p.get(0).getZ()) / 2);
    }

    @Override
    public List<PolyLine3D> getLines() {
        System.out.println("planes = " + planes.size());
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
                System.out.println(maxX + " " + maxY + " " + maxZ);
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

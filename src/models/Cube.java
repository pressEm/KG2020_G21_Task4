package models;

import kg2019examples_task4threedimensions.math.Matrix4Factories;
import kg2019examples_task4threedimensions.math.Vector3;
import kg2019examples_task4threedimensions.third.IModel;
import kg2019examples_task4threedimensions.third.PolyLine3D;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Cube implements IModel {

    List<PolyLine3D> planes = new ArrayList<>();

    public Cube(Vector3 LTF, Vector3 RBN, Color color, double alpha) {
        Parallelepiped parallelepiped = new Parallelepiped(LTF, RBN, color);
        for (PolyLine3D line3D : parallelepiped.getLines()) {
            for (Vector3 v : line3D.getPoints()) {
                Matrix4Factories.rotationXYZ(2, 0);
            }
        }

//        for (PolyLine3D line3D :)

    }

    public Cube() {
//        Vector3[] vectors = new Vector3[8];
        List<Vector3> vectors = new ArrayList<>();
        vectors.add(new Vector3(1, 2, 1));
        vectors.add(new Vector3(5, 0, 1));
        vectors.add(new Vector3(3, -4, 1));
        vectors.add(new Vector3(-1, -2, 1));
        vectors.add(new Vector3(1, 2, 2));
        vectors.add(new Vector3(5, 0, 2));
        vectors.add(new Vector3(3, -4, 2));
        vectors.add(new Vector3(-1, -2, 2));
//        vectors[0] = new Vector3(1, 2, 1);
//        vectors[1] = new Vector3(5, 0, 1);
//        vectors[2] = new Vector3(3, -4, 1);
//        vectors[3] = new Vector3(-1, -2, 1);
//        vectors[0] = new Vector3(1, 2, 2);
//        vectors[1] = new Vector3(5, 0, 2);
//        vectors[2] = new Vector3(3, -4, 2);
//        vectors[3] = new Vector3(-1, -2, 2);
        List<Vector3> points = new ArrayList<>();
        points.add(vectors.get(0));
        points.add(vectors.get(1));
        points.add(vectors.get(1));
        points.add(vectors.get(3));
        planes.add(new PolyLine3D(points, true, new Color(0xDA375D)));
        points = new ArrayList<>();
        points.add(vectors.get(4));
        points.add(vectors.get(5));
        points.add(vectors.get(6));
        points.add(vectors.get(7));
        planes.add(new PolyLine3D(points, true, new Color(0xDA375D)));
        points = new ArrayList<>();
        points.add(vectors.get(5));
        points.add(vectors.get(1));
        points.add(vectors.get(2));
        points.add(vectors.get(6));
        planes.add(new PolyLine3D(points, true, new Color(0xDA375D)));
        points = new ArrayList<>();
        points.add(vectors.get(4));
        points.add(vectors.get(0));
        points.add(vectors.get(3));
        points.add(vectors.get(7));
        planes.add(new PolyLine3D(points, true, new Color(0xDA375D)));
        points = new ArrayList<>();
        points.add(vectors.get(0));
        points.add(vectors.get(1));
        points.add(vectors.get(5));
        points.add(vectors.get(5));
        planes.add(new PolyLine3D(points, true, new Color(0xDA375D)));
        points = new ArrayList<>();
        points.add(vectors.get(3));
        points.add(vectors.get(2));
        points.add(vectors.get(6));
        points.add(vectors.get(7));
        planes.add(new PolyLine3D(points, true, new Color(0xDA375D)));
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
            System.out.println(line.getPoints().size());
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

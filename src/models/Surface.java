package models;

import kg2019examples_task4threedimensions.math.Matrix4;
import kg2019examples_task4threedimensions.math.Vector3;
import kg2019examples_task4threedimensions.third.IModel;
import kg2019examples_task4threedimensions.third.PolyLine3D;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Surface implements IModel {

    Vector3[][] matrixXY;
    List<PolyLine3D> planes = new ArrayList<>();

    public Surface(Vector3 vStart, int size) {
        matrixXY = new Vector3[size][size];
        findZ(vStart);
    }

    private void findZ(Vector3 vStart) {
        for (int i = 0; i < matrixXY.length; i++) {
            for (int j = 0; j < matrixXY.length; j++) {
                float z = (float) (Math.sin(i) + Math.cos(j));
                Vector3 v = new Vector3(vStart.getX() + i, vStart.getY() + j, z);
                matrixXY[i][j] = v;
                if ((i > 0) && (j > 0)) {
                    List<Vector3> points = new ArrayList<>();
                    points.add(matrixXY[i - 1][j]);
                    points.add(matrixXY[i][j - 1]);
                    points.add(matrixXY[i - 1][j - 1]);
                    planes.add(new PolyLine3D(points, true, new Color(0x9E16C7)));
                    points.remove(2);
                    points.add(matrixXY[i][j]);
                    planes.add(new PolyLine3D(points, true, new Color(0x9E16C7)));
                }
            }
        }
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

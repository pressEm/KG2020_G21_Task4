package kg2019examples_task4threedimensions.voxelization;

import kg2019examples_task4threedimensions.math.Vector3;
import kg2019examples_task4threedimensions.third.IModel;
import kg2019examples_task4threedimensions.third.PolyLine3D;
import kg2019examples_task4threedimensions.third.Scene;
import models.Parallelepiped;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class VoxelizationSurface {
    private List<Voxel> voxels = new ArrayList<>();

    public VoxelizationSurface(IModel model, Scene scene, float a) {
        List<Voxel> allVoxels = createBoxOnScene(a, model);
        for (Voxel v : allVoxels) {
            if (isInsideModel(v, model)) {
                voxels.add(v);
            }
        }
    }

    public List<Voxel> getVoxels() {
        return voxels;
    }

    private boolean isInsideModel(Voxel v, IModel model) {
        int count = 0;
        for (Vector3 point : v.getControlPoints()) {
            if (isPointInModel(point, model)) {
                count++;
            }
        }
        return count > 2;
    }

    private boolean isPointInModel(Vector3 v, IModel model) {
        final double EPS = 1E-10;
        int count = 0;
        for (PolyLine3D line3D : model.getLines()) {

            float x1 = line3D.getPoints().get(0).getX();
            float y1 = line3D.getPoints().get(0).getY();
            float z1 = line3D.getPoints().get(0).getZ();
            float x2 = line3D.getPoints().get(1).getX();
            float y2 = line3D.getPoints().get(1).getY();
            float z2 = line3D.getPoints().get(1).getZ();
            float x3 = line3D.getPoints().get(2).getX();
            float y3 = line3D.getPoints().get(2).getY();
            float z3 = line3D.getPoints().get(2).getZ();

            float a1 = x2 - x1;
            float b1 = y2 - y1;
            float c1 = z2 - z1;
            float a2 = x3 - x1;
            float b2 = y3 - y1;
            float c2 = z3 - z1;

//            Коэфффициенты уравнения плоскости
            float a = b1 * c2 - b2 * c1;
            float b = a2 * c1 - a1 * c2;
            float c = a1 * b2 - b1 * a2;
            float d = (-a * x1 - b * y1 - c * z1);

            float t = -((a * v.getX() + b * v.getY() + c * v.getZ() + d) / c);
            Vector3 pointCross = new Vector3(v.getX(), v.getY(), v.getZ() + t);

            if ((Math.abs(b) > EPS)) {
                if ((isPointInTriangle(v, line3D) && ((v.getY()) <= pointCross.getY()))) {
                    count++;
                }
            }
        }
        return count % 2 != 0;
    }

    private static boolean isPointInTriangle(Vector3 v, PolyLine3D p) {
        double q1 = func(p.getPoints().get(0), p.getPoints().get(1), v);
        double q2 = func(p.getPoints().get(1), p.getPoints().get(2), v);
        double q3 = func(p.getPoints().get(2), p.getPoints().get(0), v);
        return ((q1 > 0 && q2 > 0 && q3 > 0) || (q1 < 0 && q2 < 0 && q3 < 0));
    }

    private static double func(Vector3 v1, Vector3 v2, Vector3 v3) {
        return v3.getX() * (v2.getZ() - v1.getZ())
                + v3.getZ() * (v1.getX() - v2.getX())
                + v1.getZ() * v2.getX() - v1.getX() * v2.getZ();
    }

    private List<Voxel> createBoxOnScene(float a, IModel model) {

        Vector3 min = model.getMin();
        System.out.println("min = " + min.getX() + " " + min.getY()+ " " + min.getZ());
        Vector3 minVParallelepiped = new Vector3(min.getX(), min.getY(), min.getZ());
        Vector3 maxVParallel = model.getMax();
        System.out.println("max = " + maxVParallel.getX() + " " + maxVParallel.getY()+ " " + maxVParallel.getZ());

//        Создание коробки, в которую помещается модель и разбиение нa пиксели
        int count1VX = Math.round((maxVParallel.getX() - minVParallelepiped.getX()) / a);
        int count1VY = Math.round((maxVParallel.getY() - minVParallelepiped.getY()) / a);
        int count1VZ = Math.round((maxVParallel.getZ() - minVParallelepiped.getZ()) / a);
        Box b2 = new Box(minVParallelepiped, count1VX+1, count1VY+1, count1VZ+1, a);
        return b2.getVoxels();
    }


    //    public Parallelepiped createBoxOnScene (IModel model) {
    public List<Voxel> createBoxOnScene (IModel model) {

        Vector3 min = model.getMin();
        Vector3 minVParallelepiped = new Vector3(min.getX(), min.getY(), min.getZ());
        Vector3 maxVParallel = model.getMax();

        float a =9;

//        Создание коробки, в которую помещается модель и разбиение нa пиксели
        int count1VX = Math.round((maxVParallel.getX() - minVParallelepiped.getX()) / a);
        int count1VY = Math.round((maxVParallel.getY() - minVParallelepiped.getY()) / a);
        int count1VZ = Math.round((maxVParallel.getZ() - minVParallelepiped.getZ()) / a);
        Vector3 LTF = new Vector3(minVParallelepiped.getX(),
                minVParallelepiped.getY()+a*count1VY,
                minVParallelepiped.getZ()+a*count1VZ);
        Vector3 RBN = new Vector3(minVParallelepiped.getX()+a*count1VX,
                minVParallelepiped.getY(),
                minVParallelepiped.getZ());
        Parallelepiped parallelepiped = new Parallelepiped(LTF, RBN, new Color(0x852160));
        Box b2 = new Box(minVParallelepiped, count1VX+1, count1VY+1, count1VZ+1, a);
        return b2.getVoxels();

//        return parallelepiped;
    }
}

package kg2019examples_task4threedimensions.voxelization;

import kg2019examples_task4threedimensions.math.Vector3;
import kg2019examples_task4threedimensions.third.PolyLine3D;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Voxel {
    private Vector3 LTF;
    private Vector3 centre;
    private List<Vector3> vertexes = new ArrayList<>();

    private float size;

    public Voxel(Vector3 vertex, float size) {
        this.LTF = vertex;
        this.size = size;
        this.centre = new Vector3(LTF.getX() + size / 2, LTF.getY() - size / 2, LTF.getZ() - size / 2);
        findVertexes();
    }

    public float getSize() {
        return size;
    }

    public Vector3 getCentre() {
        return centre;
    }

    public List<Vector3> getControlPoints() {
        return vertexes;
    }

    public PolyLine3D[] getFacesVoxel(){
        return findPolylines();
    }

    private PolyLine3D[] findPolylines(){
        PolyLine3D[] planes = new PolyLine3D[6];
        Color color =  new Color(0x350000FF, true);
        List points = new ArrayList();
        points.add(vertexes.get(4));
        points.add(vertexes.get(2));
        points.add(vertexes.get(1));
        points.add(vertexes.get(3));
        planes[0] = new PolyLine3D(points, true, color);
        points = new ArrayList();
        points.add(vertexes.get(5));
        points.add(vertexes.get(6));
        points.add(vertexes.get(8));
        points.add(vertexes.get(7));
        planes[1] = new PolyLine3D(points, true, color);
        points = new ArrayList();
        points.add(vertexes.get(3));
        points.add(vertexes.get(7));
        points.add(vertexes.get(8));
        points.add(vertexes.get(4));
        planes[2] = new PolyLine3D(points, true,  color);
        points = new ArrayList();
        points.add(vertexes.get(1));
        points.add(vertexes.get(5));
        points.add(vertexes.get(6));
        points.add(vertexes.get(2));
        planes[3] = new PolyLine3D(points, true,  color);
        points = new ArrayList();
        points.add(vertexes.get(1));
        points.add(vertexes.get(5));
        points.add(vertexes.get(7));
        points.add(vertexes.get(3));
        planes[4] = new PolyLine3D(points, true, color);
        points = new ArrayList();
        points.add(vertexes.get(2));
        points.add(vertexes.get(6));
        points.add(vertexes.get(8));
        points.add(vertexes.get(4));
        planes[5] = new PolyLine3D(points, true, color);
        return planes;
    }

    private void findVertexes(){
        vertexes.add(centre);
        vertexes.add(new Vector3(LTF.getX() + size, LTF.getY(), LTF.getZ()));
        vertexes.add(new Vector3(LTF.getX() + size, LTF.getY() - size, LTF.getZ()));
        vertexes.add(new Vector3(LTF.getX(), LTF.getY(), LTF.getZ()));
        vertexes.add(new Vector3(LTF.getX(), LTF.getY() - size, LTF.getZ()));
        vertexes.add(new Vector3(LTF.getX() + size, LTF.getY(), LTF.getZ() - size));
        vertexes.add(new Vector3(LTF.getX() + size, LTF.getY() - size, LTF.getZ() - size));
        vertexes.add(new Vector3(LTF.getX(), LTF.getY(), LTF.getZ() - size));
        vertexes.add(new Vector3(LTF.getX(), LTF.getY() - size, LTF.getZ() - size));
    }

    public Vector3 getLTF() {
        return LTF;
    }
}

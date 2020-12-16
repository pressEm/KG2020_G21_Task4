package models;

import kg2019examples_task4threedimensions.math.Vector3;
import kg2019examples_task4threedimensions.third.IModel;
import kg2019examples_task4threedimensions.third.PolyLine3D;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Plane implements IModel {
    @Override
    public List<PolyLine3D> getLines() {
        List<PolyLine3D> list = new ArrayList<>();
        Vector3 a = new Vector3(0,0,0);
        Vector3 b = new Vector3(10,0,0);
        Vector3 c = new Vector3(0,10,10);
        Vector3 d = new Vector3(10,10,10);
        List<Vector3> listV = new ArrayList<>();
        listV.add(a);
        listV.add(b);
        listV.add(c);
        PolyLine3D l1 = new PolyLine3D(listV, true, Color.blue);
        listV = new ArrayList<>();
        listV.add(d);
        listV.add(b);
        listV.add(c);
        PolyLine3D l2 = new PolyLine3D(listV, true, Color.red);
        list.add(l1);
        list.add(l2);
        return list;
    }

    @Override
    public Vector3 getMin() {
        return null;
    }

    @Override
    public Vector3 getMax() {
        return null;
    }
}

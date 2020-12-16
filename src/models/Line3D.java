/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import kg2019examples_task4threedimensions.math.Vector3;
import kg2019examples_task4threedimensions.third.IModel;
import kg2019examples_task4threedimensions.third.PolyLine3D;

/**
 * Описывает трёхмерный отрезок
 * @author Alexey
 */
public class Line3D implements IModel {
    private Vector3 p1, p2;
    private Color color;

    public Line3D(Vector3 p1, Vector3 p2, Color color) {
        this.p1 = p1;
        this.p2 = p2;
        this.color = color;
    }

    @Override
    public List<PolyLine3D> getLines() {
        return Arrays.asList(new PolyLine3D(
                Arrays.asList(p1, p2)
            , false, color));
    }

    @Override
    public Vector3 getMin() {
        float minX = Math.min(p1.getX(), p2.getX());
        float minY = Math.min(p1.getY(), p2.getY());
        float minZ = Math.min(p1.getZ(), p2.getZ());
        return new Vector3(minX, minY, minZ);
    }

    @Override
    public Vector3 getMax() {
        float maxX = Math.max(p1.getX(), p2.getX());
        float maxY = Math.max(p1.getY(), p2.getY());
        float maxZ = Math.max(p1.getZ(), p2.getZ());
        return new Vector3(maxX, maxY, maxZ);
    }

}

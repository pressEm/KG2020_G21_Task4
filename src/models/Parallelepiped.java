/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.awt.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import kg2019examples_task4threedimensions.math.Vector3;
import kg2019examples_task4threedimensions.third.IModel;
import kg2019examples_task4threedimensions.third.PolyLine3D;

/**
 * Описывает параллелипипед по двум диагональным точкам
 *
 * @author Alexey
 */
public class Parallelepiped implements IModel {
    private Vector3 LTF, RBN;
    private Color color;

    /**
     * Создаёт экземпляр параллелипипеда
     *
     * @param LTF Левая Верхняя Дальняя точка (Left Top Far)
     * @param RBN Правая Нижняя Ближняя точка (Right Bottom Near)
     */

    public Parallelepiped(Vector3 LTF, Vector3 RBN, Color color) {
        this.LTF = LTF;
        this.RBN = RBN;
        this.color = color;
    }

    //
//
//  Метод задания куба по длине, ширине, высоте
//
//
    public Parallelepiped(Vector3 LTF, float a, Color color) {
        this.LTF = LTF;
        this.RBN = new Vector3(LTF.getX() + a, LTF.getY() - a, LTF.getZ() - a);
        this.color = color;
    }



    @Override
    public List<PolyLine3D> getLines() {
        LinkedList<PolyLine3D> lines = new LinkedList<>();
        /*Дальняя сторона (Z фиксирован и вязт у LTF)*/

        lines.add(new PolyLine3D(Arrays.asList(new Vector3[]{
                new Vector3(LTF.getX(), LTF.getY(), LTF.getZ()),
                new Vector3(LTF.getX(), RBN.getY(), LTF.getZ()),
                new Vector3(RBN.getX(), RBN.getY(), LTF.getZ()),
                new Vector3(RBN.getX(), LTF.getY(), LTF.getZ())
        }), true, color));
        /*Ближняя сторона  (Z фиксирован и вязт у RBN)*/
        lines.add(new PolyLine3D(Arrays.asList(new Vector3[]{
                new Vector3(LTF.getX(), LTF.getY(), RBN.getZ()),
                new Vector3(LTF.getX(), RBN.getY(), RBN.getZ()),
                new Vector3(RBN.getX(), RBN.getY(), RBN.getZ()),
                new Vector3(RBN.getX(), LTF.getY(), RBN.getZ())
        }), true, color));

        /*Верхняя сторона (Y фиксирован и вязт у LTF)*/
        lines.add(new PolyLine3D(Arrays.asList(new Vector3[]{
                new Vector3(LTF.getX(), LTF.getY(), LTF.getZ()),
                new Vector3(LTF.getX(), LTF.getY(), RBN.getZ()),
                new Vector3(RBN.getX(), LTF.getY(), RBN.getZ()),
                new Vector3(RBN.getX(), LTF.getY(), LTF.getZ())
        }), true, color));
        /*Нижняя сторона (Y фиксирован и вязт у RBN)*/
        lines.add(new PolyLine3D(Arrays.asList(new Vector3[]{
                new Vector3(LTF.getX(), RBN.getY(), LTF.getZ()),
                new Vector3(LTF.getX(), RBN.getY(), RBN.getZ()),
                new Vector3(RBN.getX(), RBN.getY(), RBN.getZ()),
                new Vector3(RBN.getX(), RBN.getY(), LTF.getZ())
        }), true, color));

        /*Левая сторона (X фиксирован и вязт у LTF)*/
        lines.add(new PolyLine3D(Arrays.asList(new Vector3[]{
                new Vector3(LTF.getX(), LTF.getY(), LTF.getZ()),
                new Vector3(LTF.getX(), LTF.getY(), RBN.getZ()),
                new Vector3(LTF.getX(), RBN.getY(), RBN.getZ()),
                new Vector3(LTF.getX(), RBN.getY(), LTF.getZ())
        }), true, color));
        /*Правая сторона (X фиксирован и вязт у RBN)*/
        lines.add(new PolyLine3D(Arrays.asList(new Vector3[]{
                new Vector3(RBN.getX(), LTF.getY(), LTF.getZ()),
                new Vector3(RBN.getX(), LTF.getY(), RBN.getZ()),
                new Vector3(RBN.getX(), RBN.getY(), RBN.getZ()),
                new Vector3(RBN.getX(), RBN.getY(), LTF.getZ())
        }), true, color));

        return lines;
    }

    @Override
    public Vector3 getMin() {
        Vector3 currMin = this.getLines().get(0).getPoints().get(0);
        float minX = currMin.getX();
        float minY = currMin.getY();
        float minZ = currMin.getZ();

        for (PolyLine3D line : this.getLines()) {
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
        Vector3 currMax = this.getLines().get(0).getPoints().get(0);
        float maxX = currMax.getX();
        float maxY = currMax.getY();
        float maxZ = currMax.getZ();

        for (PolyLine3D line : this.getLines()) {
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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kg2019examples_task4threedimensions.third;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import kg2019examples_task4threedimensions.math.Vector3;

/**
 * Полилиния в трёхмерном пространстве.
 * Описывает ломанную в трёхмерном пространстве по опорным точкам
 *
 * @author Alexey
 */
public class PolyLine3D {
    private Color color;
    private List<Vector3> points;
    private boolean closed;

    /**
     * Создаёт новую полилинию на основе трёхмерных точек.
     *
     * @param points список точек-вершин ломанной
     * @param closed признак замкнутостит линии
     */
    public PolyLine3D(Collection<Vector3> points, boolean closed, Color color) {
        this.points = new LinkedList<Vector3>(points);
        this.closed = closed;
        this.color = color;
    }

    /**
     * Признак закрытости
     *
     * @return возвращает истину, если линия замкнута, иначе - ложь.
     */
    public boolean isClosed() {
        return closed;
    }

    /**
     * Список вершин линии
     *
     * @return возвращает список точек.
     */
    public List<Vector3> getPoints() {
        return points;
    }

    public Color getColor() {
        return color;
    }

    /**
     * Вычисляет среднее арифметическое по оси Z.
     *
     * @return среднее по Z для полилинии.
     */
    public float avgZ() {
        if (points == null || points.size() == 0)
            return 0;
        float sum = 0;
        for (Vector3 v : points)
            sum += v.getZ();
        return sum / points.size();
    }

    public boolean equals(PolyLine3D line) {
        if (this.getPoints().size() == line.getPoints().size()) {
//            if (((this.getPoints().get(0).equals(line.getPoints().get(0))) ||
//                    (this.getPoints().get(0).equals(line.getPoints().get(1))) ||
//                    (this.getPoints().get(0).equals(line.getPoints().get(2))) ||
//                    (this.getPoints().get(0).equals(line.getPoints().get(3)))) &&
//                    ((this.getPoints().get(1).equals(line.getPoints().get(0))) ||
//                            (this.getPoints().get(1).equals(line.getPoints().get(1))) ||
//                            (this.getPoints().get(1).equals(line.getPoints().get(2))) ||
//                            (this.getPoints().get(1).equals(line.getPoints().get(3)))) &&
//                    ((this.getPoints().get(2).equals(line.getPoints().get(0))) ||
//                            (this.getPoints().get(2).equals(line.getPoints().get(1))) ||
//                            (this.getPoints().get(2).equals(line.getPoints().get(2))) ||
//                            (this.getPoints().get(2).equals(line.getPoints().get(3)))) &&
//                    ((this.getPoints().get(3).equals(line.getPoints().get(0))) ||
//                            (this.getPoints().get(3).equals(line.getPoints().get(1))) ||
//                            (this.getPoints().get(3).equals(line.getPoints().get(2))) ||
//                            (this.getPoints().get(3).equals(line.getPoints().get(3))))){
            if ((this.getPoints().get(0).equals(line.getPoints().get(0))) ||
                    (this.getPoints().get(0).equals(line.getPoints().get(1))) ||
                    (this.getPoints().get(0).equals(line.getPoints().get(2))) ||
                    (this.getPoints().get(0).equals(line.getPoints().get(3)))) {
                if ((this.getPoints().get(1).equals(line.getPoints().get(0))) ||
                        (this.getPoints().get(1).equals(line.getPoints().get(1))) ||
                        (this.getPoints().get(1).equals(line.getPoints().get(2))) ||
                        (this.getPoints().get(1).equals(line.getPoints().get(3)))) {
                    if ((this.getPoints().get(2).equals(line.getPoints().get(0))) ||
                            (this.getPoints().get(2).equals(line.getPoints().get(1))) ||
                            (this.getPoints().get(2).equals(line.getPoints().get(2))) ||
                            (this.getPoints().get(2).equals(line.getPoints().get(3)))) {
                        if ((this.getPoints().get(3).equals(line.getPoints().get(0))) ||
                                (this.getPoints().get(3).equals(line.getPoints().get(1))) ||
                                (this.getPoints().get(3).equals(line.getPoints().get(2))) ||
                                (this.getPoints().get(3).equals(line.getPoints().get(3)))) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean isOnePlane(PolyLine3D line) {
        final double EPS = 1E-10;
        List<PolyLine3D> list = new ArrayList<>();
        List<Float> parameters = new ArrayList<>();
        list.add(line);
        list.add(this);
        for (PolyLine3D p : list) {
            float x1 = p.getPoints().get(0).getX();
            float y1 = p.getPoints().get(0).getY();
            float z1 = p.getPoints().get(0).getZ();
            float x2 = p.getPoints().get(1).getX();
            float y2 = p.getPoints().get(1).getY();
            float z2 = p.getPoints().get(1).getZ();
            float x3 = p.getPoints().get(2).getX();
            float y3 = p.getPoints().get(2).getY();
            float z3 = p.getPoints().get(2).getZ();

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
            parameters.add(a);
            parameters.add(b);
            parameters.add(c);
            parameters.add(d);
        }
        for (int i = 0; i < 4; i++) {
            if ((parameters.get(i) - parameters.get(i+4)) >= EPS){
                return false;
            }
        }
        return true;
    }

}

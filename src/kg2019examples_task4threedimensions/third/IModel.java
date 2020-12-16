/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kg2019examples_task4threedimensions.third;

import kg2019examples_task4threedimensions.math.Vector3;

import java.util.List;

/**
 * Описывает в общем виде любую модель
 * @author Alexey
 */
public interface IModel {
    /**
     * Любая модель - это набор полилиний.
     * @return Списко полилиний модели.
     */
    List<PolyLine3D> getLines();
    Vector3 getMin();
    Vector3 getMax();
}

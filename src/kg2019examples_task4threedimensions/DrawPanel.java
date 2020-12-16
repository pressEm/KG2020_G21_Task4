/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kg2019examples_task4threedimensions;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;

import kg2019examples_task4threedimensions.draw.IDrawer;
import kg2019examples_task4threedimensions.draw.SimpleEdgeDrawer;
import kg2019examples_task4threedimensions.math.Vector3;
import kg2019examples_task4threedimensions.screen.ScreenConverter;
import kg2019examples_task4threedimensions.third.Camera;
import kg2019examples_task4threedimensions.third.IModel;
import kg2019examples_task4threedimensions.third.Scene;
import kg2019examples_task4threedimensions.voxelization.Voxel;
import kg2019examples_task4threedimensions.voxelization.Voxelization;
import kg2019examples_task4threedimensions.voxelization.VoxelizationSurface;
import models.*;

/**
 * @author Alexey
 */
public class DrawPanel extends JPanel
        implements CameraController.RepaintListener {
    private Scene scene;
    private ScreenConverter sc;
    private Camera cam;
    private CameraController camController;

    public DrawPanel() {
        super();
        sc = new ScreenConverter(-10, 10, 20, 20, 10, 10);
        cam = new Camera();
        camController = new CameraController(cam, sc);
        scene = new Scene(Color.WHITE.getRGB());
        scene.showAxes();

        testPaint();

        camController.addRepaintListener(this);
        addMouseListener(camController);
        addMouseMotionListener(camController);
        addMouseWheelListener(camController);
    }



    private void testPaint() {
      testPaintSphere();
//testPaintPyramid();
//        testPaintSurface();
//        testPaintCylinder();
    }

    private void testPaintSphere() {
        float a = 10f;
        Vector3 LTF = new Vector3(-0.5f * a, 0.5f * a, 0.5f * a);
//        Добавление сферы на сцену
        Sphere sphere = new Sphere(LTF, a, 20, scene, new Color(230, 20, 230, 30));
//        Добавление поверхности сферы на сцену
//        scene.getModelsList().add(sphere);

        //        ВОКСЕЛИЗАЦИЯ
        Voxelization voxelization = new Voxelization(sphere, scene, 0.5f);
//        //        ВОКСЕЛИЗАЦИЯ СПИСОК ВОКСЕЛЕЙ
//        for (Voxel v : voxelization.getVoxels()) {
//            Parallelepiped p = new Parallelepiped(
//                    new Vector3(v.getLTF().getX(), v.getLTF().getY(), v.getLTF().getZ()),
//                    v.getSize(), new Color(100, 100, 100, 100));
//            scene.getModelsList().add(p);
////            voxels.add(p);
//        }
        //        ВОКСЕЛИЗАЦИЯ НАРУЖНЯЯ ГРАНЬ
        VoxelizedModel vm = new VoxelizedModel(voxelization.getVoxels());
        scene.getModelsList().add(vm);
    }

    private void testPaintCylinder() {
        Cylinder cylinder = new Cylinder(new Vector3(0, -4, 0), 6, 8, 10, new Color(0x46B349));
        scene.getModelsList().add(cylinder);
        Voxelization voxelization = new Voxelization(cylinder, scene, 1f);
//        for (Voxel v : voxelization.createBoxOnScene(cylinder)) {
//            Parallelepiped p = new Parallelepiped(
//                    new Vector3(v.getLTF().getX(), v.getLTF().getY(), v.getLTF().getZ()),
//                    v.getSize(), new Color(100, 100, 100, 100));
//            scene.getModelsList().add(p);
//        }
//        for (Voxel v : voxelization.getVoxels()) {
//            Parallelepiped p = new Parallelepiped(
//                    new Vector3(v.getLTF().getX(), v.getLTF().getY(), v.getLTF().getZ()),
//                    v.getSize(), new Color(100, 100, 100, 100));
//            scene.getModelsList().add(p);
//        }
        VoxelizedModel vm = new VoxelizedModel(voxelization.getVoxels());
        scene.getModelsList().add(vm);
    }

    private void testCube() {
        Cube cube = new Cube();
        scene.getModelsList().add(cube);
        Voxelization voxelization = new Voxelization(cube, scene, 0.4f);
//        VoxelizedModel vm = new VoxelizedModel(voxelization.getVoxels());
//        scene.getModelsList().add(vm);
    }

    private void testPaintSurface() {
        Surface surface = new Surface(new Vector3(0, 0, 0), 20);
        scene.getModelsList().add(surface);
        VoxelizationSurface voxelization = new VoxelizationSurface(surface, scene, 1f);
//        for (Voxel v : voxelization.createBoxOnScene(surface)){
//            Parallelepiped p = new Parallelepiped(
//                    new Vector3(v.getLTF().getX(), v.getLTF().getY(), v.getLTF().getZ()),
//                    v.getSize(), new Color(100, 100, 100, 100));
//            scene.getModelsList().add(p);
//        }
        VoxelizedModel vm = new VoxelizedModel(voxelization.getVoxels());
        scene.getModelsList().add(vm);
    }

    private void testPaintPyramid() {
        Pyramid pyramid = new Pyramid(new Vector3(-3, -3, 3), 6, 6, 13);
        scene.getModelsList().add(pyramid);
        Voxelization voxelization = new Voxelization(pyramid, scene, 0.5f);
        VoxelizedModel vm = new VoxelizedModel(voxelization.getVoxels());
        scene.getModelsList().add(vm);
    }

    @Override
    public void paint(Graphics g) {
        sc.setScreenSize(getWidth(), getHeight());
        BufferedImage bi = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = (Graphics2D) bi.getGraphics();
        IDrawer dr = new SimpleEdgeDrawer(sc, graphics);
        scene.drawScene(dr, cam);
        g.drawImage(bi, 0, 0, null);
        graphics.dispose();
    }

    @Override
    public void shouldRepaint() {
        repaint();
    }
}

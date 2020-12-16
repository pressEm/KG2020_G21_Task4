package models;

import kg2019examples_task4threedimensions.math.Vector3;
import kg2019examples_task4threedimensions.third.IModel;
import kg2019examples_task4threedimensions.third.PolyLine3D;
import kg2019examples_task4threedimensions.voxelization.Voxel;

import java.util.ArrayList;
import java.util.List;

public class VoxelizedModel implements IModel {

    private List<PolyLine3D> polyLines = new ArrayList<>();

    public VoxelizedModel(List<Voxel> voxels) {
        createPolyline(voxels);
    }

    private void createPolyline(List<Voxel> voxels) {
        List<PolyLine3D> allPolyLines = new ArrayList<>();
        PolyLine3D[] listLines = new PolyLine3D[voxels.size() * 6];
        int k = 0;
        for (Voxel vCurr : voxels) {
            for (int j = 0; j < vCurr.getFacesVoxel().length; j++) {
                listLines[k * 6 + j] = vCurr.getFacesVoxel()[j];
            }
            k++;
        }
        for (int i = 0; i < listLines.length; i++) {
            for (int j = 0; j < listLines.length; j++) {
                if ((listLines[j] != null) && (listLines[i] != null) && (i != j)) {
                    if (isFacesAdj(listLines[i], listLines[j])) {
                        listLines[i] = null;
                        listLines[j] = null;
                    }
                }
            }
            if (listLines[i] != null) {
                allPolyLines.add(listLines[i]);
                polyLines.add((listLines[i]));
            }
        }
        connectLinesOnePlane(allPolyLines);
    }

    //объединить все полилинии в одну полилинию и создать одну модель
    private void connectLinesOnePlane(List<PolyLine3D> allPolylines){

    }

    private boolean isFacesAdj(PolyLine3D l1, PolyLine3D l2) {
        if (l1.equals(l2)) {
            return true;
        }
        return false;
    }



    @Override
    public List<PolyLine3D> getLines() {
        return polyLines;
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

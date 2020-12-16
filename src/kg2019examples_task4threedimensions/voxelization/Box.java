package kg2019examples_task4threedimensions.voxelization;

import kg2019examples_task4threedimensions.math.Vector3;
import kg2019examples_task4threedimensions.voxelization.Voxel;

import java.util.ArrayList;
import java.util.List;

public class Box {
    private Vector3 vertex;
    private List<Voxel> voxels = new ArrayList<>();
    private List<Integer> countV = new ArrayList<>();

    public Box(Vector3 vertex, int countVX, int countVY, int countVZ, float sizeVoxel) {
        this.vertex = new Vector3(vertex.getX(), vertex.getY() + sizeVoxel, vertex.getZ() + sizeVoxel);
        this.countV.add(countVX);
        this.countV.add(countVY);
        this.countV.add(countVZ);
        createVoxels(sizeVoxel);
    }
    public Box(Vector3 vertex, int countVX, int countVY, int countVZ) {
        float sizeVoxel = 0;
        this.vertex = new Vector3(vertex.getX(), vertex.getY() + sizeVoxel, vertex.getZ() + sizeVoxel);
        this.countV.add(countVX);
        this.countV.add(countVY);
        this.countV.add(countVZ);
        createVoxels(sizeVoxel);
    }

    private void createVoxels(float size) {
        int a = 0;
        for (int i = 0; i < countV.get(0); i++) {
            if (a == 0) {
                Vector3 currV = new Vector3(vertex.getX() + i * size, vertex.getY(), vertex.getZ());
                voxels.add(new Voxel(currV, size));
                a++;
            }
            int b = 0;
            for (int j = 0; j < countV.get(1); j++) {
                if ((a == 0) && (b == 0)) {
                    Vector3 currVY = new Vector3(vertex.getX() + i * size, vertex.getY() + j * size, vertex.getZ());
                    voxels.add(new Voxel(currVY, size));
                    b++;

                }
                for (int k = 0; k < countV.get(2); k++) {
                    Vector3 currVYZ = new Vector3(vertex.getX() + i * size, vertex.getY() + j * size, vertex.getZ() + k * size);
                    voxels.add(new Voxel(currVYZ, size));
                }
            }
        }
        voxels.remove(0);
    }

    public List<Voxel> getVoxels() {
        return voxels;
    }
}

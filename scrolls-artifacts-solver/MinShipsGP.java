import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class MinShipsGP {
    private final ArrayList<Integer> artifactsFound = new ArrayList<>();

    public ArrayList<Integer> getArtifactsFound() {
        return artifactsFound;
    }

    MinShipsGP(ArrayList<Integer> artifactsFound) {
        this.artifactsFound.addAll(artifactsFound);
    }

    public OptimalShipSolution optimalArtifactCarryingAlgorithm() throws FileNotFoundException {
        ArrayList<Integer> artifactSort = new ArrayList<>(artifactsFound);
        artifactSort.sort(Collections.reverseOrder());

        final int CAPACITY_MAX = 100;
        ArrayList<Integer> remainingCapacities = new ArrayList<>();

        for (int weight : artifactSort) {
            int index = findFittingShip(remainingCapacities, weight);
            if (index != -1) {
                remainingCapacities.set(index, remainingCapacities.get(index) - weight);
            } else {
                remainingCapacities.add(CAPACITY_MAX - weight);
            }
        }

        return new OptimalShipSolution(artifactsFound, remainingCapacities.size());
    }

    private int findFittingShip(ArrayList<Integer> capacities, int weight) {
        for (int i = 0; i < capacities.size(); i++) {
            if (capacities.get(i) >= weight) {
                return i;
            }
        }
        return -1;
    }

}
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MaxScrollsDP {
    private ArrayList<ArrayList<Integer>> safesDiscovered = new ArrayList<>();

    public MaxScrollsDP(ArrayList<ArrayList<Integer>> safesDiscovered) {
        this.safesDiscovered = safesDiscovered;
    }

    public ArrayList<ArrayList<Integer>> getSafesDiscovered() {
        return safesDiscovered;
    }

    public OptimalScrollSolution optimalSafeOpeningAlgorithm() throws FileNotFoundException {
        int totalMinutes = safesDiscovered.size();
        int knowledgeLimit = totalMinutes * 5;

        int[][] dpTable = new int[totalMinutes + 1][knowledgeLimit + 1];

        for (int i = 0; i <= totalMinutes; i++) {
            for (int j = 0; j <= knowledgeLimit; j++) {
                dpTable[i][j] = Integer.MIN_VALUE;
            }
        }

        dpTable[0][0] = 0;

        for (int i = 1; i <= totalMinutes; i++) {
            ArrayList<Integer> currentList = safesDiscovered.get(i - 1);
            int complexity = currentList.get(0);
            int scrollCount = currentList.get(1);

            for (int j = 0; j <= knowledgeLimit; j++) {

                if (dpTable[i - 1][j] != Integer.MIN_VALUE) {

                    dpTable[i][j] = Math.max(dpTable[i][j], dpTable[i - 1][j]);

                    if (j + 5 <= knowledgeLimit) {
                        dpTable[i][j + 5] = Math.max(dpTable[i][j + 5], dpTable[i - 1][j]);
                    }

                    if (j >= complexity) {
                        int remainingInformation = j - complexity;
                        dpTable[i][remainingInformation] = Math.max(dpTable[i][remainingInformation], dpTable[i - 1][j] + scrollCount);
                    }
                }
            }
        }

        int maxScroll = 0;
        for (int j = 0; j <= knowledgeLimit; j++) {
            if (dpTable[totalMinutes][j] != Integer.MIN_VALUE) {
                maxScroll = Math.max(maxScroll, dpTable[totalMinutes][j]);
            }
        }

        return new OptimalScrollSolution(safesDiscovered, maxScroll);
    }
}
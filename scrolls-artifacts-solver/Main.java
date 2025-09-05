import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String safesDat = args[0];
        String artifacsDat = args[1];

        System.out.println("##Initiate Operation Safe-lock##");

        OptimalScrollSolution scrollResult = new MaxScrollsDP(parseSafes(safesDat)).optimalSafeOpeningAlgorithm();
        scrollResult.printSolution(scrollResult);

        System.out.println("##Operation Safe-lock Completed##");
        System.out.println("##Initiate Operation Artifact##");

        OptimalShipSolution shipResults = new MinShipsGP(parseArtifact(artifacsDat)).optimalArtifactCarryingAlgorithm();
        shipResults.printSolution(shipResults);

        System.out.print("##Operation Artifact Completed##");
    }

    private static ArrayList<ArrayList<Integer>> parseSafes(String file) throws FileNotFoundException {
        try (Scanner scanner1 = new Scanner(new File(file))) {
            int totalMinute = Integer.parseInt(scanner1.nextLine());
            ArrayList<ArrayList<Integer>> safes = new ArrayList<>();

            while (scanner1.hasNextLine() && safes.size() < totalMinute) {
                String[] values = scanner1.nextLine().replace("[", "").replace("]", "").split(",");
                ArrayList<Integer> safe = new ArrayList<>();
                for (String v : values) {
                    safe.add(Integer.parseInt(v.trim()));
                }
                safes.add(safe);
            }

            return safes;
        }
    }

    private static ArrayList<Integer> parseArtifact(String file) throws FileNotFoundException {
        try (Scanner scanner2 = new Scanner(new File(file))) {
            String[] variables = scanner2.nextLine().replace("[", "").replace("]", "").split(",");
            ArrayList<Integer> artifacts = new ArrayList<>();
            for (String variable : variables) {
                artifacts.add(Integer.parseInt(variable.trim()));
            }
            return artifacts;
        }
    }
}

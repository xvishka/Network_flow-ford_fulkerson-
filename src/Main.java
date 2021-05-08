import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Main {

    static int[][] graph;
    static int vertices = -1;
    static ArrayList<int[]> sample_data = new ArrayList<int[]>();


    public static void main(String[] args) {

        // Store names of the test data files
        // All the files in TestData dir put into an Arraylist
        // Split them into two different arrays accordingly
        File folder = new File("src/sample data files");
        File[] listOfFiles = folder.listFiles();

        assert listOfFiles != null;
        for (File file : listOfFiles) {
            System.out.println("Test File - " + file.getName());
            findMaxFlow(file.getName());
        }
    }

    public static void findMaxFlow(String testDatFile) {
        String fileName = "src/sample data files/" + testDatFile;

        try {
            // clear sample_data and graph before populating again
            if (!sample_data.isEmpty()) {
                sample_data.clear();
            }
            if (graph != null) {
                Arrays.fill(graph, null);
                graph = null;
            }

            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            Scanner sc = new Scanner(bufferedReader);

            // Holds the number of vertices in VERTICES variable
            vertices = Integer.parseInt(sc.nextLine());

            // Number of characters per line (row)
            int width = 3;

            while (sc.hasNextLine()) {
                // Setup current row
                int[] row = new int[width];
                // For each character
                for (int i = 0; i < width; i++) {
                    // Read the char and add it to the current row
                    row[i] = sc.nextInt();
                }
                // Add the row to the sample_data
                sample_data.add(row);
                // Go to the next line if it has one
                if (sc.hasNextLine()) {
                    sc.nextLine();
                }
            }
            sc.close();

        } catch (FileNotFoundException ex) {
            System.out.println("\nUnable to open file: " + fileName);
        }

        // Create 2d graph from the loaded data
        graph = new int[vertices][vertices];
        for (int[] ints : sample_data) {
            graph[ints[0]][ints[1]] = ints[2];
        }


        // System time before execution
        long startTime = System.currentTimeMillis();

        // Computes max flow and store it in variable
        int maxFlow = new MaxFlow().fordFulkerson(vertices, graph, 0, (vertices - 1));

        // System time after execution
        long endTime = System.currentTimeMillis();

        // System time difference
        long timeElapsed = endTime - startTime;

        System.out.println("Max Flow - " + maxFlow+"\nExecution Time - "+ timeElapsed + "ms\n");
    }
}

import java.util.*;
import java.io.*;
public class BubbleSortProgram {
    public static void main(String[] args) {
            ArrayList<Integer> numbers = readNumbers("numbers.txt");

            long startTime = System.nanoTime();
            bubbleSort(numbers);
            long endTime = System.nanoTime();
            double seconds = (endTime - startTime) / 1_000_000_000.0;

            System.out.printf("Bubble Sort Time: %.8f seconds%n", seconds);
            writeToFile("bubble_sorted.txt", numbers);
            System.out.println("Bubble sorted numbers written to bubble_sorted.txt");
        }

        public static ArrayList<Integer> readNumbers(String filename) {
            ArrayList<Integer> list = new ArrayList<>();
            try (Scanner fileScanner = new Scanner(new File(filename))) {
                while (fileScanner.hasNextLine()) {
                    list.add(Integer.parseInt(fileScanner.nextLine()));
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found.");
            }
            return list;
        }

        public static void bubbleSort(ArrayList<Integer> list) {
            int n = list.size();
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    if (list.get(j) > list.get(j + 1)) {
                        int temp = list.get(j);
                        list.set(j, list.get(j + 1));
                        list.set(j + 1, temp);
                    }
                }
            }
        }

        public static void writeToFile(String filename, ArrayList<Integer> list) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
                for (int num : list) {
                    writer.println(num);
                }
            } catch (IOException e) {
                System.out.println("Error writing to file: " + filename);
            }
        }
}




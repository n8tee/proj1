import java.io.*;
import java.util.*;

public class QuickSortProgram {
    public static void main(String[] args) {
        ArrayList<Integer> numbers = readNumbers("numbers.txt");

        long startTime = System.nanoTime();
        quickSort(numbers, 0, numbers.size() - 1);
        long endTime = System.nanoTime();
        double seconds = (endTime - startTime) / 1_000_000_000.0;

        System.out.printf("Quick Sort Time: %.8f seconds%n", seconds);
        writeToFile("quick_sorted.txt", numbers);
        System.out.println("Quick sorted numbers written to quick_sorted.txt");
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

    public static void quickSort(ArrayList<Integer> list, int low, int high) {
        if (low < high) {
            int pi = partition(list, low, high);
            quickSort(list, low, pi - 1);
            quickSort(list, pi + 1, high);
        }
    }

    public static int partition(ArrayList<Integer> list, int low, int high) {
        int pivot = list.get(low);
        int i = low + 1;
        int j = high;

        while (true) {
            while (i <= j && list.get(i) <= pivot) i++;
            while (i <= j && list.get(j) > pivot) j--;

            if (i > j) break;

            int temp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, temp);
        }

        list.set(low, list.get(j));
        list.set(j, pivot);

        return j;
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

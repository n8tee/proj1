import java.io.*;
import java.util.*;

public class MergeSortProgram {
    public static void main(String[] args) {
        ArrayList<Integer> numbers = readNumbers("numbers.txt");

        long startTime = System.nanoTime();
        numbers = mergeSort(numbers);
        long endTime = System.nanoTime();
        double seconds = (endTime - startTime) / 1_000_000_000.0;

        System.out.printf("Merge Sort Time: %.8f seconds%n", seconds);
        writeToFile("merge_sorted.txt", numbers);
        System.out.println("Merge sorted numbers written to merge_sorted.txt");
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

    public static ArrayList<Integer> mergeSort(ArrayList<Integer> list) {
        if (list.size() <= 1) return list;
        int mid = list.size() / 2;
        ArrayList<Integer> left = new ArrayList<>(list.subList(0, mid));
        ArrayList<Integer> right = new ArrayList<>(list.subList(mid, list.size()));
        left = mergeSort(left);
        right = mergeSort(right);
        return merge(left, right);
    }

    public static ArrayList<Integer> merge(ArrayList<Integer> left, ArrayList<Integer> right) {
        ArrayList<Integer> merged = new ArrayList<>();
        int i = 0, j = 0;
        while (i < left.size() && j < right.size()) {
            if (left.get(i) <= right.get(j)) {
                merged.add(left.get(i++));
            } else {
                merged.add(right.get(j++));
            }
        }
        while (i < left.size()) merged.add(left.get(i++));
        while (j < right.size()) merged.add(right.get(j++));
        return merged;
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

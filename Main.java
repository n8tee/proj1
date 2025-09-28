

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList();

        try {
            Scanner fileScanner = new Scanner(new File("numbers.txt"));

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                numbers.add(Integer.parseInt(line));
            }

            fileScanner.close();
        } catch (FileNotFoundException var11) {
            System.out.println("File not found.");
        }

        Scanner scnr = new Scanner(System.in);
        int target = scnr.nextInt();
        long startTime = System.nanoTime();
        int index = linearSearch(numbers, target);
        long endTime = System.nanoTime();
        double seconds = (double) (endTime - startTime) / (double) 1.0E9F;
        System.out.printf("Time taken %.8f seconds", seconds);
        System.out.println("");
        if (index != -1) {
            System.out.println("Number found at " + index);
        } else {
            System.out.println("Number not found");
        }

        ArrayList<Integer> bubbleList = new ArrayList<>(numbers);
        ArrayList<Integer> mergeList = new ArrayList<>(numbers);
        ArrayList<Integer> quickList = new ArrayList<>(numbers);

        //bubble Sort
        startTime = System.nanoTime();
        bubbleSort(bubbleList);
        endTime = System.nanoTime();
        seconds = (endTime - startTime) / 1_000_000_000.0;
        System.out.printf("Bubble Sort Time: %.8f seconds\n", seconds);

        //merge Sort
        startTime = System.nanoTime();
        mergeList = mergeSort(mergeList);
        endTime = System.nanoTime();
        seconds = (endTime - startTime) / 1_000_000_000.0;
        System.out.printf("Merge Sort Time: %.8f seconds\n", seconds);

        //quick Sort
        startTime = System.nanoTime();
        quickSort(quickList, 0, quickList.size() - 1);
        endTime = System.nanoTime();
        seconds = (endTime - startTime) / 1_000_000_000.0;
        System.out.printf("Quick Sort Time: %.8f seconds\n", seconds);

        writeToFile("sorted_numbers.txt", quickList);

    }

    public static int linearSearch(ArrayList<Integer> list, int target) {
        for (int i = 0; i < list.size(); ++i) {
            if ((Integer) list.get(i) == target) {
                return i;
            }
        }
        return -1;
    }

    //bubble sort

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

    //merge sort
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

    //quick sort
    public static void quickSort(ArrayList<Integer> list, int low, int high) {
        if (low < high) {
            int pi = partition(list, low, high);
            quickSort(list, low, pi - 1);
            quickSort(list, pi + 1, high);
        }
    }

    public static int partition(ArrayList<Integer> list, int low, int high) {
        int pivot = list.get(low);  // use first element as pivot
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
    public static void writeToFile (String filename, ArrayList<Integer> list){
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (int num : list) {
                writer.println(num);
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + filename);
        }
    }
}

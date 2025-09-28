import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class LinearSearchProgram {
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>();

        try {
            Scanner fileScanner = new Scanner(new File("numbers.txt"));

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                numbers.add(Integer.parseInt(line));
            }

            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

        Scanner scnr = new Scanner(System.in);
        System.out.print("Enter a number to search: "); // 👈 added prompt
        int target = scnr.nextInt();

        long startTime = System.nanoTime();
        int index = linearSearch(numbers, target);
        long endTime = System.nanoTime();
        double seconds = (double) (endTime - startTime) / (double) 1.0E9F;

        System.out.printf("Time taken %.8f seconds%n", seconds);

        if (index != -1) {
            System.out.println("Number found at " + index);
        } else {
            System.out.println("Number not found");
        }
    }
    public static int linearSearch(ArrayList<Integer> list, int target) {
        for (int i = 0; i < list.size(); ++i) {
            if ((Integer)list.get(i) == target) {
                return i;
            }
        }
        return -1;
    }
}

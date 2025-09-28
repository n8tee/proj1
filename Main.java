package bin;

import java.util.*;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.*;


public class Main {
	
	public static void main(String args[]) {
	
	List<Integer> nums = new ArrayList<>();
	
	try {
		Scanner scnr = new Scanner(new File("src/sorted_numbers.txt"));
		
		while (scnr.hasNextLine()) {
			
			String line = scnr.nextLine().trim();
			
			if (!line.isEmpty()) {
				nums.add(Integer.parseInt(line));
			}
		}
		scnr.close();
			
	}  catch(FileNotFoundException e) {
		System.out.println("File not found");
	}
	
	Scanner scnr = new Scanner(System.in);
	System.out.println("Targeted number: ");
	int target = scnr.nextInt();
	long start = System.nanoTime();
	int num = binarySearch(nums, target);
	long end = System.nanoTime();
	double secs = (double) (end - start) / 1_000_000_000.0;
	System.out.printf("Time taken %.8f seconds%n", secs);
	
	if (num != -1) {
		System.out.println("Number found at: " + num);
	}
	else {
		System.out.println("Number not found.");
	}
	
scnr.close();
}
	
	public static int binarySearch(List<Integer> arr, int target) {
        int min = 0;
        int max = arr.size() - 1;
        
        while (min <= max) {
            int mid = min + (max - min) / 2;
            
            if (arr.get(mid) == target) {
                return mid;
            } 
            else if (arr.get(mid) < target) {
                min = mid + 1;
            } 
            else {
                max = mid - 1;
            }
        }
        
        return -1;
    }
}



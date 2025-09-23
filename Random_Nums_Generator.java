package ran;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.Random;
import java.io.IOException;

public class Random_Nums_Generator {
	
	public static void main(String[] args) {
		int[] numbers = new int[1000];
        
        // Fill array with numbers 0-999
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = i;
        }
        
        // Shuffle the array
        Random random = new Random();
        for (int i = numbers.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int temp = numbers[i];
            numbers[i] = numbers[j];
            numbers[j] = temp;
        }
        
        // Save to file
        String filename = "numbers.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int i = 0; i < numbers.length; i++) {
                writer.write(String.valueOf(numbers[i]));
                writer.newLine();
            }
            System.out.println("Numbers save to: " + filename);
            System.out.println("✓ File location: " + System.getProperty("user.dir") + "/" + filename);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

}

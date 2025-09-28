package bin;
import java.util.ArrayList;

public class BinarySearch {
	
	public static int binarySearch(ArrayList<Integer> arr, int target) {
        int min = 0;
        int max = arr.size() - 1;
        
        while (min <= max) {
            int mid = min + (max - min) / 2;
            
            if (arr.get(mid) == target) {
                return mid;
            } else if (arr.get(mid) < target) {
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }
        
        return -1;
    }

}

import java.util.ArrayList;

public class Arrays {
	
	public static void main (String[] args){
		
		int[] number = {8, 3, 5, 2, 6, 9, 4, 10, 7, 1};
		
		int start, index, minIndex, minValue;
		
		for (start = 0; start < number.length - 1; start++){
			minIndex = start;
			minValue = number[start];
			for (index = start + 1; index < number.length; index++){
				if(number[index] < minValue){
					minValue = number[index];
					minIndex = index;
				}
			}
			number[minIndex] = number[start];
			number[start] = minValue;
		}
		
		//Binary Search
		int value = 7;
		int first, last, mid;
		boolean found = false;
		first = 0;
		last = number.length -1;
		while(!found && first <= last){
			mid = (first + last) / 2;
			if (number[mid] == value){
				found = true;
			}
			else if (number[mid] > value){
				last = mid - 1;
			}
			else first = mid + 1;
		}
		
		if(found){
			System.out.println("Found it!!");
		}
		else System.out.println("Not Found!");
	}
}

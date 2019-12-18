package cpsc331.assignment3;

import cpsc331.assignment3.Array;
import java.util.*;

public class ArrayUtils<T extends Comparable<T>> {
	public static void main (String[] args){
		ArrayUtils<Integer> a = new ArrayUtils<Integer>();
		
		for (int i =0; i <10; i++){
			int size = i;
			Array<Integer> array = new Array(size);
			for(int j =0; j<size; j++){
				array.set(j, -1000000+(int)(Math.random()*((1000000+1000000)+1)));
			}
			a.printArray(array);
			a.printArray(array);
			a.printArray(array);
			for(int j =1; j<array.length();j++){
				if (-array.get(j-1).compareTo(array.get(j))<0){
					System.out.println("uhh probably an error -- last 2 things are result and output");
					break;
				}
			}
 		}
	}
	
	public void printArray(Array<T> array){
		for(int i =0; i<array.length(); i++)
			System.out.println(array.get(i));
	}
	
	public void sort( Array<T> A, int space ){
		int space = A.length()-1;
		heapTheArray(A);
		while(space>=0)
		T largest = deleteMax(A, space);
		A.set(space,largest);
		space--;		

	}
	private T deleteMax(Array<T> A, int space)
	{
		T max = A.get(0); 
		A.set(0, A.get(space)); 
		bubbleDown(A, 0, space);

		return max;
	}
	
	private void heapTheArray(Array<T> A)
	{
		int space = A.length();
		if (space > 0) {
		int i = space/2 ;
		while (i >= 0) {
			bubbleDown(A, i, space - 1);
			i--;
			}
		}
	}
	private void bubbleDown(Array<T> A , int number, int space){
		T temporal = A.get(number);
		while (number*2+1 <= space){
			int toSwap = number*2+1;
			if(toSwip +1 <= space && A.get(toSwap+1).compareTo(A.get(toSwap))>0){
				toSwap++;
			}
			if(temporal.compareTo(A.get(toSwap))>0){
				break;
			}
			A.set(number,A.get(toSwap));
			number = toSwap;
		}
		A.get(number, temporal);
	}
}
package cpsc331.assignment1;
import java.util.*;
import java.io.*;

public class FGrindelwald {
	//An recursive algoritm which made for assignment1
	//It expect the user types one number as augument in the command line
	//The first number that user types is expected to be an positive intger 
	//This recursive algorithm will take that positive integer as the input.
	//
	//The result for this algorithm will be printed as an output.
	//
	//Author: Sijia Yin 30049836; Chunyu Li 30056553; Boxiao Li 30069613
	public static void main(String[] args){
		try{
			if (args.length != 1) {
				throw new NumberFormatException();
		}
			System.out.println(fGrin(Integer.parseInt(args[0])));
		} catch (NumberFormatException e) {
			System.out.println("Gadzooks! One integer input is required.");
		} catch (IllegalArgumentException e) {
			System.out.println("Gadzooks! The integer input cannot be negative.");
		}
	}

	//An recursive algorithm for "Grindelwald Gaggle Computation" problem.
	//Precondition: An integer n is given as input.
	//Postcondition:If n is greater than or equal to 0, then the nth Grindelwald number, G_n, is returned as out-put. 
	//An IllegalArgumentException is thrown if input n is an negative number.
	//An NumberFormatException will be thrown if input n is not an number.
	//
	//Bound function:n-i
	
	public static int fGrin(int n){
	//Assertion:n is an integer input whose value is greater than
    //or equal to 0.
		if (n < 0) {
			throw new IllegalArgumentException();
			}
		//Assertion: An integer n is given as an input
		//n should be greater or equal to 0;
		//An IllegalArgumentException will be thrown otherwise.

		if(n == 0){ 
		//Assertion: n is an integer input with the value 0.
			return 1;
		//Assertion: 
  		//1. n is an integer input with the value 0.
  		//2. The nth Grindelwald number,G_n=G_0=1 will be printed as an output.
		}else if(n == 1){ 
		//Assertion: n is an integer input with the value 1.
			return 2;
		//Assertion: 
  		//1. n is an integer input with the value 1.
  		//2. The nth Grindelwald number, G_n=G_1=2 will be printed as an output.
		}else if(n == 2){ 
		//Assertion: n is an integer input with the value 2.
			return 3;
		//Assertion: 
  		//1. n is an integer input with the value 2.
  		//2. The nth Grindelwald number, G_n=G_2=3 will be printed as an output.
		}else if(n == 3){ 
		//Assertion: n is an integer input with the value 3.
			return 4;
		//Assertion: 
  		//1. n is an integer input with the value 0.
  		//2. The nth Grindelwald number, G_n=T_G_3=4 will be printed as an output.
		} else {
			int[] G = new int[n+1];
			G[0]=1;
			G[1]=2;
			G[2]=3;
			G[3]=4;
			int i = 4;
		// Loop Invariant:
		// 1. n is an integer input whose value is greater than or equal to 4.
		// 2. G is an integer array who lenth is n+1. 
		// 3. variable i is an integer whose valueis greater than or equal to 4
		//    and less than or equal to n.
		// 4.G[j] is Gj, the jth Grindelwald number, for every integer j whose value
		// is betwen 0 and i (inclusive).
			while (i <= n) {
				if ((i % 2 ) == 0) {
					G[i] = 2*G[i-1]-2*G[i-3]+G[i-4];
					//Assertion:
					//1. n is an integer input whose value is greater than 3;
					//2. if n is an even number(n divides 2 have no remainder), 
					//then algorithm will given 2*G[i-1]-2*G[i-3]+G[i-4]
					//printed as an output.

				}else{
					G[i] = G[i-1]+3*G[i-2]-5*G[i-3]+2*G[i-4];
					//Assertion:
					//1. n is an integer input whose value is greater than 3;
					//2. if n is an odd number(n divides 2 have a remainder), 
					//then algorithm will given G[i-1]+3*G[i-2]-5*G[i-3]+2*G[i-4]
					//printed as an output.
				}
				i++;
			}
			return G[n];
		}
	}
}
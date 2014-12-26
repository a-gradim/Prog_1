package functions;

import java.util.InputMismatchException;
import java.util.Scanner;

public class generalFunctions {
	private static Scanner sc = new Scanner(System.in);
	
	
	public int readRangedInt(int min, int max) {
		int temp = 0;
		while(temp < min || temp > max){
			try{
				temp = sc.nextInt();
			}catch(InputMismatchException e){
				System.out.println("Insert numbers only!");
				temp = 0;
			}			
		}
		
		return temp;	
	}

}

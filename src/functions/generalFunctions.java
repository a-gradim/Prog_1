package functions;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class generalFunctions {
	private static Scanner sc = new Scanner(System.in);
	
	public void orderArray(int[] array, boolean isCrescent){
		int temp;
		for(int i = 0; i < array.length-1; i++){
			for(int j = i+1; j < array.length; j++){
				if(isCrescent){
					if(array[i] > array[j]){
						temp = array[i];
						array[i] = array[j];
						array[j] = temp;
					}
				}else{
					if(array[i] < array[j]){
						temp = array[i];
						array[i] = array[j];
						array[j] = temp;
					}
				}
			}
		}
		
	}
	
	public String [] readFile(String path) throws IOException{
		File fIn = new File(path);
		Scanner reader = new Scanner(fIn);
		
		int lineNumber = 0;
		while(reader.hasNextLine()){
			reader.nextLine();
			lineNumber++;
		}
		String[] temp = new String[lineNumber];
		
		for(int i = 0; i < lineNumber; i++){
			temp[i] = reader.nextLine();
		}
		
		
		return temp;
	}
	public int readRangedInt(int min, int max) {
		int temp = 0;
		do{
			try{
				temp = sc.nextInt();
			}catch(InputMismatchException e){
				System.out.println("Insert numbers only!");
			}			
		}while(temp < min || temp > max);
		
		return temp;	
	}
	
	public int insertInt(int min, int max) {
		return insertInt("", min, max);	
	}
	
	public int insertInt(String prompt) {
		return insertInt(prompt, Integer.MIN_VALUE , Integer.MAX_VALUE);	
	}
	
	public int insertInt(String prompt, int min, int max) {
		int temp = 0;
		
		do{
			System.out.println(prompt);
			try{
				temp = sc.nextInt();
			}catch(InputMismatchException e){
				System.out.println("INSERT NUMBERS ONLY!");
			}
		}while(temp < min || temp > max);
		
		
		return temp;
	}

}

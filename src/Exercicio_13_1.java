import java.util.Scanner;
import java.io.*;

public class Exercicio_13_1 {
	
	public static void main(String[] args){
		
		menu();
		
	}
	
	public static void menu(){
			System.out.println("Estacao meteorologica:\n"
					+ "1 - Ler ficheiro de dados\n"
					+ "2 - Acrescentar medida\n"
					+ "3 - Listar valores de temperatura e humidade\n"
					+ "4 - Listar medidas ordenadas por valor de temperatura\n"
					+ "5 - Listar medidas ordenadas por valor de humidade\n"
					+ "6 - Calcular valores medios de temperatura e humidade\n"
					+ "7 - Calcular valores maximos e mınimos de temperatura e humidade\n"
					+ "8 - Calcular histograma das temperaturas e humidade\n"
					+ "9 - Terminar o programa\n"
					+ "Opcao ->");
	}
	
	public static void readDataFile(String path) throws IOException{
		File fIn = new File(path);
		Scanner reader = new Scanner(fIn);
		
		
		
		//m
		
	}

}

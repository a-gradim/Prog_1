package aula_13;

import functions.generalFunctions;

public class Exercise_1 {
	//public static Scanner sc = new Scanner(System.in);
	public static generalFunctions fList = new generalFunctions();
	MeteoData [] mes1 = new MeteoData[31];
	

	public static void run(){
		int choice = 0;
		do{
			menu();
			choice = fList.readRangedInt(1,9);
			switch(choice){
			case 1:
				
				break;
			case 2:
				
				break;
			case 3:
				
				break;
			case 4:
				
				break;
			case 5:
				
				break;
			case 6:
				
				break;
			case 7:
				
				break;
			case 8:
				
				break;
			case 9:
				System.out.println("Good-bye. See you next time!");
				break;
			default:
				System.out.println("Not a valid option. Try again!");
			}
			
		}while(choice != 9);
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

	
	
}

class MeteoData{
	double temp, hum;
}

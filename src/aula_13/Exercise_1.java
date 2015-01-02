package aula_13;

import java.io.IOException;

import functions.generalFunctions;

public class Exercise_1 {
	public static String path = "meteoData";
	
	public static generalFunctions fList = new generalFunctions();
	public static MeteoData [] mes1 = new MeteoData[31];
	

	public static void run() throws IOException{
		int choice = 0, pos = 0;
		do{
			menu();
			choice = fList.insertInt(1, 9);
			switch(choice){
			case 1:
				pos = read();
				break;
			case 2:
				pos = addMeasure(pos);
				break;
			case 3:
				printData(pos);
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
	
	public static void printData(int pos) {
		System.out.println("Dados da metereologicos: ");
		for(int i = 0; i < pos; i ++){
			System.out.printf("Temp: %6.2d ::: Hum: %6.2d", mes1[i].temp, mes1[i].hum);
		}
		
	}

	public static int addMeasure(int pos) {
			mes1[pos].temp = fList.insertInt("Insira o valor da temperatura");
			mes1[pos].hum = fList.insertInt("Insira o valor da humidade", 0, 100);
			pos++;
		return pos;
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

	public static int read() throws IOException{
		String [] data = fList.readFile(path);
		for(int i = 0; i < data.length; i++){
			mes1[i] = new MeteoData();
			int j;
			for(j = 0; j < data[i].length(); j++){
				if( !(Character.isDigit(data[i].charAt(j)))){
					break;
				}
			}
			String firstNum = data[i].substring(0, j);
			String seconfNum = data[i].substring(j+1);
			mes1[i].hum = Integer.parseInt(firstNum); ///////////////Problema dados meteo
			mes1[i].temp = Integer.parseInt(seconfNum);////////////// sao double
		}
		return data.length;
	}
	
	
}

class MeteoData{
	int temp, hum;
}

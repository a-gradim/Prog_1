import java.util.Scanner;
import java.io.*;

public class Exercicio_13_1 {
	public static Scanner sc = new Scanner(System.in); 
	
	public static void main(String[] args) throws IOException{
		MeteoData[] mes = new MeteoData[31];
		MeteoData [] orderedTemp = new MeteoData[mes.length];
		MeteoData [] orderedHum = new MeteoData[mes.length];
		
		int[] tempArray, humArray;
		
		tempArray = new int[mes.length];
		humArray = new int[mes.length];
		
		MeteoData averageData, minTemp, maxTemp, minHum, maxHum;
				
		int pos = 0, choice = 0;
		boolean allowRun = false;

		
		minTemp = new MeteoData();
		maxTemp = new MeteoData();
		minHum = new MeteoData();
		maxHum = new MeteoData();
		
		do{
			menu();
			choice = sc.nextInt();
			switch(choice){
			case 1:
				pos = readDataFile(mes);
				allowRun = true;
				break;
			case 2:
				if(!allowRun){
					System.out.println("Opção nao disponivel!\nIntroduza valores.");
				}else{
					pos = addMeasure(pos, mes);
				}
				break;
			case 3:
				if(!allowRun){
					System.out.println("Opção nao disponivel!\nIntroduza valores.\n");
				}else{
					printData(pos, mes);
				}
				
				break;
			case 4:
				if(!allowRun){
					System.out.println("Opção nao disponivel!\nIntroduza valores.\n");
				}else{
					orderBy(orderedTemp, orderedHum, pos, mes); //Esta funçao faz tanto o 4 como o 5 apenas se mostra o que queremos.
					printData(pos, orderedTemp);
				}
				break;
			case 5:
				if(!allowRun){
					System.out.println("Opção nao disponivel!\nIntroduza valores.\n");
				}else{
					orderBy(orderedTemp, orderedHum, pos, mes); //Esta funçao faz tanto o 4 como o 5 apenas se mostra o que queremos.
					printData(pos, orderedHum);
				}
				break;
			case 6:
				if(!allowRun){
					System.out.println("Opção nao disponivel!\nIntroduza valores.\n");
				}else{
					averageData = calculateAverage(pos, mes);
					System.out.printf("A temp. media e %3d e a hum. media e %3d\n", averageData.temp, averageData.hum);
				}
				break;
			case 7:
				if(!allowRun){
					System.out.println("Opção nao disponivel!\nIntroduza valores.\n");
				}else{
					calculateXtremes(minTemp, maxTemp, minHum, maxHum, pos, mes);
					System.out.printf("MinTemp::\n T: %3d :: H: %3d\n", minTemp.temp, minTemp.hum);
					System.out.printf("MaxTemp::\n T: %3d :: H: %3d\n", maxTemp.temp, maxTemp.hum);
					System.out.printf("MinHum::\n T: %3d :: H: %3d\n", minHum.temp, minHum.hum);
					System.out.printf("MaxHum::\n T: %3d :: H: %3d\n", maxHum.temp, maxHum.hum);
				}
				break;
			case 8:
				if(!allowRun){
					System.out.println("Opção nao disponivel!\nIntroduza valores.\n");
				}else{
					extractArray(tempArray, humArray, pos, mes);
					makeHistogram("temperatura",pos, tempArray, -10, 40);
					makeHistogram("humidade",pos, humArray, 0, 100);
				
				}
				break;
			case 9:
				System.out.println("Adeus! Ate a proxima");
				break;
			default:
				System.out.println("Opçao nao valida. Introduza outra!");
			
			}
			
			
		}while(choice != 9);
				
	}
	
	public static void extractArray(int[] tempArray,int[] humArray, int pos, MeteoData[] array){
		for(int i = 0; i < pos; i ++){
			tempArray[i] = array[i].temp;
			humArray[i] = array[i].hum;
		}
		return;
	}
	
	public static void makeHistogram(String prompt,int pos, int[] array, int min, int max){
		int[] count = new int[max - min +1];
		int counter = min;
		
		for(int i = 0; i < pos; i ++){
			count[array[i]-counter]++;
		}
		
		System.out.printf("Histograma da %s", prompt);
		System.out.println("---------------------------");
		for(int i = 0; i < count.length; i++){
		//	if(count[i]!=0){							////Mostra apenas os preenchidos
			System.out.printf("%3d | ", counter + i);
			for(int j = 0; j < count[i]; j++){
				System.out.print("*");
			}
			System.out.println();
			//}
		}
	}
	
	public static void orderBy(MeteoData[] orderedTemp, MeteoData[] orderedHum, int pos, MeteoData[] array) {
		int temp, hum;
		
		copyArray(pos, orderedTemp, array);  //Faz-se copias do array de dados para nao alterar a informaçao inicial
		copyArray(pos, orderedHum, array);
						
		for(int i = 0; i < pos-1; i++){
			for(int j = i+1; j < pos; j++){
				if(orderedTemp[i].temp > orderedTemp[j].temp){
					temp = orderedTemp[i].temp;
					hum = orderedTemp[i].hum;
					orderedTemp[i].temp = orderedTemp[j].temp;
					orderedTemp[i].hum = orderedTemp[j].hum;
					orderedTemp[j].temp = temp;
					orderedTemp[j].hum = hum;
					
				}
				if(orderedHum[i].hum < orderedHum[j].hum){
					temp = orderedHum[i].temp;
					hum = orderedHum[i].hum;
					orderedHum[i].temp = orderedHum[j].temp;
					orderedHum[i].hum = orderedHum[j].hum;
					orderedHum[j].temp = temp;
					orderedHum[j].hum = hum;
				}
			}
		}
		
	}

	public static void copyArray(int pos, MeteoData[] ordered, MeteoData[] array) {
		for(int i = 0; i < pos; i++){
			ordered[i] = new MeteoData();
			ordered[i].temp = array[i].temp;
			ordered[i].hum= array[i].hum;
		}
	}

	public static void calculateXtremes(MeteoData minTemp, MeteoData maxTemp, MeteoData minHum, MeteoData maxHum,int pos, MeteoData[] array){
		minTemp.temp = array[0].temp;
		minTemp.hum = array[0].hum;
		minHum.temp = array[0].temp;
		minHum.hum = array[0].hum;
		
		for(int i = 0 ; i < pos; i ++){
			if(minTemp.temp > array[i].temp){
				minTemp.temp = array[i].temp;
				minTemp.hum = array[i].hum;
			}
			if(maxTemp.temp < array[i].temp){
				maxTemp.temp = array[i].temp;
				maxTemp.hum = array[i].hum;
			}
			if(minHum.hum > array[i].hum){
				minHum.temp = array[i].temp;
				minHum.hum = array[i].hum;
			}
			if(maxHum.hum < array[i].hum){
				maxHum.temp = array[i].temp;
				maxHum.hum = array[i].hum;
			}
		}
		
	}
	
	public static MeteoData calculateAverage(int pos, MeteoData[] array) {
		MeteoData tmp = new MeteoData();
		int tempSum = 0, humSum = 0;
		for(int i = 0; i < pos; i++){
			tempSum += array[i].temp;
			humSum += array[i].hum;
		}
		tmp.temp = tempSum/pos;
		tmp.hum = humSum/pos;
		
		return tmp;
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
	
	public static int readDataFile( MeteoData [] array) throws IOException{
		String path;
		int pos = 0;
		
		System.out.println("Introduzao nome do ficheiro: ");
		do{
			path = sc.nextLine();
		}while(path.length() == 0);
		
		File fIn = new File(path);
		Scanner reader = new Scanner(fIn);
		while(reader.hasNextLine()){			
			array[pos] = new MeteoData();
			array[pos].temp =reader.nextInt();
			array[pos].hum = reader.nextInt();
			reader.nextLine();
			pos++;
		}		
		return pos;		
	}

	public static int addMeasure(int pos, MeteoData[] array) {
		array[pos] = new MeteoData();
		array[pos].temp = getIntValue("Introduza o valor da temperatura ",-10, 40);
		array[pos].hum = getIntValue("Introduza o valor da humidade ",0, 100);
		
		return pos + 1;
	}

	public static int getIntValue(String prompt, int min, int max) {
		int tmp;
		do{
			System.out.print(prompt);
			tmp = sc.nextInt();
		}while(tmp < min || tmp > max);	
		return tmp;
	}

	public static void printData(int pos, MeteoData[] array){
		for(int i = 0; i < pos; i++){
			System.out.printf("Temp: %3d -- Hum: %3d\n", array[i].temp, array[i].hum);
		}	
	}
}

class MeteoData{
	int temp;
	int hum;
}
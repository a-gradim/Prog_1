
import java.util.Scanner;


public class Exercicio_13_3 {
	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args){
		Team[] concurso = new Team[15];
		Team winner ;
		int choice = 0, pos = 0, averageTime, averageElements;	
		
		
		do{
			menu();
			choice = sc.nextInt();
			
			switch(choice){
			case 1:
				pos = addTeam(pos, concurso);
				break;
			case 2:
				printDataArray(pos, concurso);
				break;	
			case 3:
				averageTime = calcAverageTime(pos, concurso);
				winner = getWinner(pos, concurso);
				System.out.printf("O vencedor é: \n");
				printData(winner);
				String averageTimeString = String.format("%02d:%02d:%02d", averageTime/3600, averageTime%3600/60 , averageTime%60);
				System.out.println("A media de tempos foi " + averageTimeString);
				break;
			case 4:
				averageElements = calcAverageElements(pos, concurso);
				System.out.println("A media de elementos foi " + averageElements);
				break;
			case 5://///////////////////////////////////////////////////////////////////completar daqui para baixo
				for(int i = 0; i < pos; i++){
					System.out.printf("%s\n", concurso[i].robotName.toUpperCase());
				}
				break;
			case 6:
				changeData(pos, concurso);
				
				break;
			case 7:
				removeRobot(pos, concurso); /////////////////////usar indexOf(String str);
				break;
			case 8:
				
				break;
			}
			
			
		}while(choice != 9);
	}
	
	
	public static void removeRobot(int pos, Team[] array) {
		int id = getIntRanged("Inserir posicao do robot", 0, pos);
		for(int i = id; i < pos-1; i++){
			array[i] = array[i+1];
		}
		array[pos] = new Team();
		pos--;
	}


	public static int getIntRanged(String prompt, int min, int max) {
		int tmp;
		do{
			System.out.println(prompt);
			tmp = sc.nextInt();
		}while(tmp < min|| tmp >= max);
		return tmp;
	}


	public static void changeData(int pos, Team[] concurso) {
		int selection = 0, id;
		do{
			System.out.println("Selecione a opcao a alterar: \n"
					+"1-Nome do robot\n"
					+"2-Tempo da prova1\n"
					+"3-Numero de elementos\n"
					+"4-Cancelar\n");
			selection = sc.nextInt();
			//id = getRobotId(pos, concurso);
			id = getIntRanged("Inserir posicao do robot", 0, pos);
			switch(selection){
			case 1:
				System.out.println("Inserir novo nome: ");
				do{
					concurso[id].robotName = "";
					concurso[id].robotName = sc.nextLine();
				}while(concurso[id].robotName == "");
				selection = 4;
				break;
			case 2:
				System.out.println("Inserir novo tempo: ");
				concurso[id].testTime = sc.nextInt();
				selection = 4;						
				break;
			case 3:
				System.out.println("Inserir novo numero de elementos: ");
				concurso[id].elementsNum = sc.nextInt();
				selection = 4;
				break;
			}
			
		}while(selection != 4);
	}


	public static int getRobotId(int pos, Team[] array) {
		
		String data = sc.nextLine();
		int data_pos;
		System.out.println("Inserir a posição do robot ou o nome do robot: ");

		//ainda nao demos isto, por isso podem ignorar, foi apenas uma experiencia
		try{ //serve para apanhar as exceçoes e trata-las para que o programa nao de erros na execuçao
			do{
				data_pos = Integer.parseInt(data);
			}while(data_pos < 0 || data_pos > pos);
			return data_pos;
		}catch(Exception e){
			for(int i = 0; i < pos; i++){
				if(array[i].robotName.equals(data)){
					return i;
				}
			}
			System.out.println("Robot name not found");
			return getRobotId(pos, array);
		}
	}


	private static int calcAverageElements(int pos, Team[] array) {
		int sum = 0;
		for(int i = 0; i < pos; i++){
			sum += array[i].elementsNum;
		}
		return sum/pos;
	}


	public static int calcAverageTime(int pos, Team[] array) {
		int sum = 0;
		for(int i = 0; i < pos; i++){
			sum += array[i].testTime;
		}
		return sum/pos;
	}
	public static Team getWinner(int pos, Team[] array){
		Team tmp = new Team();
		int winner = 0;
		tmp.testTime = array[0].testTime;
		for(int i = 1; i < pos;  i++){
			if(tmp.testTime > array[i].testTime){
				winner = i;
			}
		}
		tmp.testTime = array[winner].testTime;
		tmp.elementsNum = array[winner].elementsNum;
		tmp.robotName = array[winner].robotName;
		
		return tmp;
	}


	public static void printDataArray(int pos, Team[] array) {
		for(int i = 0; i < pos; i++){
			System.out.printf("---------------Equipa %3d---------------\n", i+1);
			printData(array[i]);
		}
		System.out.print("----------------------------------------\n");
		
	}


	public static void printData(Team team) {
		String time = String.format("%02d:%02d:%02d", team.testTime/3600, team.testTime%3600/60 ,team.testTime%60);
		System.out.printf("Nome do robot: %s\nTempo de prova: %s\nNumero de elementos: %2d\n", team.robotName, time, team.elementsNum);;
			
		
	}


	public static void menu(){
	System.out.println("Micro-Rato 2013 - Gestao da prova:\n"
			+ "1 - Adicionar informacao relativa a um robo\n"
			+ "2 - Imprimir informacao dos robos em prova\n"
			+ "3 - Vencedor da prova e tempos medios de prova\n"
			+ "4 - Media do numero de elementos por equipa\n"
			+ "5 - Mostrar o nome dos robos em maiusculas\n"
			+ "6 - Alterar informacao de um robo\n"
			+ "7 - Remover robos da competicao\n"
			+ "8 - Gravar informacao da prova num ficheiro\n"
			+ "9 - Terminar o programa\n"
			+ "Opcao ->\n");
	}
	
	public static int addTeam(int pos, Team[] concurso) {
		//expandArray(pos,array);
		
		//É usada para expandir o array ate a posiçao inserida
		concurso[pos] = new Team();
		System.out.println("Inserir o nome do robot: ");
		do{
			concurso[pos].robotName = sc.nextLine();
		}while(concurso[pos].robotName.length() == 0);
		System.out.println("Inserir o tempo de prova: ");
		concurso[pos].testTime = sc.nextInt();
		System.out.println("Inserir o numero de elementos da equipa ");
		concurso[pos].elementsNum = sc.nextInt();
		System.out.println("Success!!!");
		pos++;
		return pos;
	}
	
	public static void expandArray(int pos, Team[] array) {
			// E por fim apontamos o indicador do array inicial par o novo
	
	}
	
}

class Team{
	String robotName;
	int elementsNum, testTime;
	
}

import java.util.ArrayList;
import java.util.Scanner;

/**@author �scar G�mez Fern�ndez
 * @version 1.1 - Aquesta versi� incorpora JavaDoc.
 * 
 * Mitjan�ant una �nica classe, executo tot el programa del Memory.
 * 
*/

public class main {
	
	static int midatauler = 4, opcio = 1, fila, fila2, columna, columna2, punts1 = 0, punts2 = 0;
	static boolean casella2 = false, jugador2 = false, totescaselles = false, encert = false;
	static Scanner sc = new Scanner(System.in);
	
	
	/** El main es limita a inicialitzar el generador de nombres aleatoris, 
	 * i cridar a joc. 
	 * <p>
	 * Quan el joc acaba, pregunta si es vol fer una altra partida o no.
	 * 
	 * @return void
	 */
	public static void main(String[] args) {
		
		System.out.println("Benvingut al joc del memory!");
		
		while(opcio == 1) {
			
			punts1 = 0;
			punts2 = 0;
			jugador2 = false;
			totescaselles = false;
			
			joc();
			
			System.out.println("\nVols jugar una altre partida? (1- S� / 2 - No)");
			opcio = sc.nextInt();
		}
		
		System.out.println("\nAdeu, que tingui un bon dia!");

	}

	
	/** M�tode que gestiona una partida sencera, i no rep ni retorna cap valor. 
	 * Com a variables locals crearem les matrius del tauler i del secret, que 
	 * inicialitzarem cridant les funcions corresponents. 
	 * <p>
	 * El joc continuar� fins que s'hagin trobat totes les parelles. 
	 * Per cada torn, es dir� a quin dels dos jugadors toca tirar i es cridar� 
	 * la funci� torn. 
	 * <p>
	 * Si torn torna cert, vol dir que el jugador ha encertat una parella.
	 * En aquest cas, se li suma un punt, i se l'informa que pot tornar a tirar. 
	 * <p>
	 * Si no encerta, se li diu que ha fallat, i es canvia de jugador.
	 * Quan la partida hagi acabat, es mirar� quin dels dos jugadors t� m�s punts, 
	 * i es dir� qui ha guanyat.
	 * 
	 *  @return void
	 */
	private static void joc() {
		
		char[][] tauler = new char[midatauler][midatauler];
		char[][] secret = new char[midatauler][midatauler];
		
		incialitzar_tauler(tauler);
		inicialitzar_secret(secret);
		
		while(totescaselles == false) {
			
			if(!torn(tauler, secret)) {
				System.out.println("\nLes dues caselles no son iguals, es tornaran a ocultar");
				System.out.println();
				mostrar_tauler(tauler);
				encert = false;
			}
			
			else {
				System.out.println();
				mostrar_tauler(tauler);
				System.out.println("\nHas encertat! Guanyes un punt!");
				
				if(jugador2 == false) {
					punts1++;
				}
				
				else {
					punts2++;
				}
				
				System.out.println("\nPunts jugador 1: "+punts1+" || Punts jugador 2: "+punts2);
				System.out.println("\nCom has encertat, tornes a jugar!");
				encert = true;
			}
			
			
			if(encert == true) {
				
			}
			
			else if(jugador2 == true) {
				jugador2 = false;
			}
			
			else {
				jugador2 = true;
			}
		}
		
		System.out.println("\nPunts jugador 1: "+punts1+" || Punts jugador 2: "+punts2);
		
		if(punts1 > punts2)
			System.out.println("\nGuanya el jugador 1!");
		
		else if (punts2 > punts1)
			System.out.println("\nGuanya el jugador 2!");
		
		else
			System.out.println("\nEmpat!");
		
	}
	
	
	/** M�tode que rep el tauler i el secret i retorna si s'ha trobat una parella
	 *  o no. Cada torn consisteix en destapar dues peces. 
	 *  <p>
	 *  Per la primera, es demanar� la posici� a l'usuari (utilitzant la funci� 
	 *  anterior), es copiar� el contingut de la casella des de secret cap a tauler, 
	 *  i es mostrar� el tauler. 
	 *  <p>
	 *  Per la segona tirada, es faran els mateixos tres passos. Despr�s, es
	 *  comprovar� si les dues caselles descobertes coincideixen. Si coincideixen, 
	 *  la funci� retornar� cert. Si no coincideixen, es tornaran a posar interrogants
	 *  a les dues posicions descobertes del tauler, i es tornar� fals.
	 *  
	 * @param tauler
	 * @param secret
	 * @return boolean
	 */
	private static boolean torn(char[][] tauler, char[][] secret) {
		
		boolean encert = false;
		
		if(jugador2 == false) {
			System.out.println("\nTorn del jugador 1.");
		}
		
		else {
			System.out.println("\nTorn del jugador 2.");
		}
		
		demanar_posicio(tauler, fila, columna);
		tauler[fila][columna] = secret[fila][columna];
		System.out.println();
		mostrar_tauler(tauler);
		casella2 = true;
		
		demanar_posicio(tauler, fila2, columna2);
		tauler[fila2][columna2] = secret[fila2][columna2];
		System.out.println();
		mostrar_tauler(tauler);
		casella2 = false;
		
		if(tauler[fila][columna] != tauler[fila2][columna2]) {
			tauler[fila][columna] = '?';
			tauler[fila2][columna2] = '?';
		}
		
		else if(tauler[fila][columna] == tauler[fila2][columna2]) {
			encert = true;
		}
		
		if(encert == true) {
			return true;
		}
		
		else {
			return false;
		}
		
		
	}
	

	/** 
	 * M�tode que rebr� el tauler i el mostrar� per pantalla.	
	 * @param tauler
	 * @return void
	 */
	private static void mostrar_tauler(char[][] tauler) {
		
		int cont = 1;
		int cobert = 0;
		
		System.out.println("  1 2 3 4");
		for(int i = 0; i < midatauler; i++) {
			System.out.print(cont+" ");
			for(int j = 0; j < midatauler; j++) {
				System.out.print(tauler[i][j]+" ");
				if(tauler[i][j] == '?') {
					cobert++;
				}
			}
			System.out.println();
			cont++;
		}
		
		if(cobert == 0)
			totescaselles = true;
	}

	
	/** 
	 * M�tode que rep una matriu tauler, i una fila i columna per refer�ncia. 
	 * <p>
	 * Demana a l'usuari la fila i columna de la casella que vol destapar. 
	 * La funci� s'assegura que les coordenades s�n v�lides, �s a dir, que
	 * s�n dins del tauler i que la casella encara no est� destapada 
	 * (�s a dir, que al tauler hi ha un interrogant).
	 * <p>
	 * Si les comprovacions fallen, es tornen a demanar les coordenades a 
	 * l'usuari. Un cop siguin correctes, es tornen els dos nombres a 
	 * trav�s de la fila i columna que s'han passat com a par�metres.
	 * 
	 * @param tauler
	 * @param fil
	 * @param col
	 * @return void
	 */
	private static void demanar_posicio(char[][] tauler, int fil, int col) {

		boolean ok = false;
		
		do {
			System.out.println("\nIntrodueix una fila (1-4):");
			fil = sc.nextInt();
			
			fil = fil -1;
			
			if(casella2 == false) {
				fila = fil;
			}
			
			else {
				fila2 = fil;
			}
			
			if(fil < 0 || fil > 3) {
				while(fil < 0 || fil > 3) {
					System.out.println("\nValor fora de rang.");
					System.out.println("\nIntrodueix una fila (1-4):");
					fil = sc.nextInt();
					
					fil = fil -1;
					
					if(casella2 == false) {
						fila = fil;
					}
					
					else {
						fila2 = fil;
					}
					
				}
			}
			
			System.out.println("\nIntrodueix una columna (1-4):");
			col = sc.nextInt();
			
			col = col -1;
			
			if(casella2 == false) {
				columna = col;
			}
			
			else {
				columna2 = col;
			}
			
			if(col < 0 || col > 3) {
				while(col < 0 || col > 3) {
					System.out.println("\nValor fora de rang.");
					System.out.println("\nIntrodueix una columna (1-4):");
					col = sc.nextInt();
					
					col = col -1;
					
					if(casella2 == false) {
						columna = col;
					}
					
					else {
						columna2 = col;
					}
				}
			}
			
			if(tauler[fil][col] != '?') {
				System.out.println("\nCasella destapada, has d'introduir una altre posici�.");
			}
			
			else {
				ok = true;
			}
			
		}while(ok == false);
		
	}

	/**
	 * M�tode que rep la matriu "secret" i es limita a cridar les funcions 
	 * posa_peces i remena_peces.

	 * @param secret
	 * @return void
	 */
	private static void inicialitzar_secret(char[][] secret) {
		
		posar_peces(secret);
		remenar_peces(secret);
	}
	
	
	/**
	 * M�tode que rep una matriu secret ja inicialitzada, i remena les peces.
	 * @param secret
	 * @return void
	 */
	private static void remenar_peces(char[][] secret) {

		ArrayList<Integer> posicions1 = new ArrayList<Integer>();
		ArrayList<Integer> posicions2 = new ArrayList<Integer>();
		
		for (int i = 0; i < 30; i++) {
			int random1 = (int) (Math.random() * midatauler);
			int random2 = (int) (Math.random() * midatauler);
		
			if (!posicions1.contains(random1)) {
				posicions1.add(random1);
			}
			
			if (!posicions2.contains(random2)) {
				posicions2.add(random2);
			}

		}

		for (int i = 0; i < midatauler; i++) {
			for (int j = 0; j < midatauler; j++) {
				int fila = posicions1.get(j);
				int col = posicions2.get(i);
				char aux = secret[i][j];
				
				secret[i][j] = secret[fila][col];
				secret[fila][col] = aux;
			}
		}
	}


	/**
	 * M�tode que rebr� la matriu secret i la inicialitzar�, posant les lletres
	 * de la A a la H dues vegades cadascuna, en posicions consecutives.
	 * @param secret
	 * @return void
	 */
	private static void posar_peces(char[][] secret) {
		
		char lletra = 65;
		
		for(int i = 0; i < midatauler; i++) {
			for(int j = 0; j < midatauler; j++) {
				secret[i][j] = lletra;
				secret[i][++j] = lletra;
				lletra++;
			}
		}
	}

	/**
	 * M�tode que rebr� una matriu de car�cters que representa el tauler de 
	 * joc i la inicialitzar� tota amb interrogants.
	 * @param tauler
	 * @return void
	 */
	private static void incialitzar_tauler(char[][] tauler) {

		for(int i = 0; i < midatauler; i++) {
			for(int j = 0; j < midatauler; j++) {
				tauler[i][j] = '?';
			}
		}
	}

}

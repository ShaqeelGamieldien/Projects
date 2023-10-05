
//Shaqeel Gamieldien
// javac SU23120509.java

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.awt.Event;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import javax.swing.JOptionPane;

public class Connect4 {

	// Board size
	static int X = 6;
	static int Y = 7;
	// This variable can be used to turn your debugging output on and off.
	// Preferably use
	static boolean  DEBUG = true;
	static int K = 0, P = 0, Turn = 1, win = 0;


	public static void main(String[] args) {
		// TODO: Read in a command line argument that states whether the program
		// will be in either terminal
		// mode (T) or in GUI mode (G) [Hint: use args and the variable below]
		// Sets whether the game is in terminal mode or GUI mode

		boolean player1 = true, gui = false;
		String Lin= args[0];
		char GUI = Lin.charAt(0);
		int[] current= new int[3];



		if (GUI == 'G') {
			gui = true;
		} else {
			if (GUI == 'T') {
				gui = false;
			}
		}

		int input = 0;
		int pos = -1;
		// The 6-by-7 grid that represents the game-board, it is initially empty
		int[][] grid = new int[X][Y];
		// Shows which player's turn it is, true for player 1 and false for
		// player 2
		// Shows the number of special tokens a player has left
		int[] p1powers = { 2, 2, 2 };
		int[] p2powers = { 2, 2, 2 };

		if (!gui) {
			// Terminal mode

			// TODO: Display 10 line title
			StdOut.println("****************************************************************************");
			StdOut.println("*  _______  _______  __    _  __    _  _______  _______  _______  _   ___  *"
					+ "\n* |       ||       ||  |  | ||  |  | ||       ||       ||       || | |   | *"
					+ "\n* |       ||   _   ||   |_| ||   |_| ||    ___||       ||_     _|| |_|   | *"
					+ "\n* |       ||  | |  ||       ||       ||   |___ |       |  |   |  |       | *"
					+ "\n* |      _||  |_|  ||  _    ||  _    ||    ___||      _|  |   |  |___    | *"
					+ "\n* |     |_ |       || | |   || | |   ||   |___ |     |_   |   |      |   | *"
					+ "\n* |_______||_______||_|  |__||_|  |__||_______||_______|  |___|      |___| *");
			StdOut.println("*                                                                          *");
			StdOut.println("****************************************************************************");
			// Array for current player's number of power storage
			int[] curppowers = new int[3];
			while (true) {

				win = 0;
				if (player1) {
					StdOut.println("Player 1's turn (You are Red):");
					curppowers = p1powers;
				} else {
					StdOut.println("Player 2's turn (You are Yellow):");
					curppowers = p2powers;
				}
				StdOut.println("Choose your move: \n 1. Play Normal \n 2. Play Bomb (" + curppowers[0]
						+ " left) \n 3. Play Teleportation (" + curppowers[1] + " left) \n 4. Play Colour Changer ("
						+ curppowers[2] + " left)\n 5. Display Gameboard \n 6. Load Test File \n 0. Exit");
				// TODO: Read in chosen move, check that the data is numeric

				try {
					input = StdIn.readInt();
				} catch (Exception e) {
					StdOut.println("Invalid input.");
					input = 9;
				}
				if (input == 5) {
					player1 = !player1;
				}

				switch (input) {
				case 0:
					Exit();
					break;

				case 1:
					StdOut.println("Choose a column to play in:");
					// TODO: Read in chosen column
					// TODO: Check that value is within the given bounds, check
					// that the data is numeric
					// TODO: Play normal disc in chosen column

					try{K = StdIn.readInt()-1;}
					catch(Exception e){	StdOut.println("Invalid input."); }

	P = 5;
					if ((K > -1) && (K < 8)) {
						Play(K, grid, player1);
					} else {
						StdOut.println("Illegal move please try again player " + Turn);
						break;
					}
				Display(grid);
					break;

				case 2:
					StdOut.println("Choose a column to play in:");


	P = 5;


					if ( curppowers[0]>=1){
						curppowers[0]--;
						if(player1){
						p1powers=curppowers;
						}else{
			p2powers=curppowers;
						}



						try{K = StdIn.readInt()-1;}
					catch(Exception e){	StdOut.println("Invalid input."); }


					if ((K > -1) && (K < 8)) {
						Bomb(K, grid, player1);
						gravity(grid);
					} else {
						StdOut.println("Illegal move please try again player " + Turn);

					}
}else{ StdOut.println("Out of Bombs Player " + Turn);
break;
}
					gravity(grid);
					Display(grid);


					// TODO: Read in chosen column
					// TODO: Check that value is within the given bounds, check
					// that the data is numeric
					// TODO: Play bomb disc in chosen column and reduce the
					// number of bombs left
					// TODO: Check that player has bomb discs left to play,
					// otherwise print out an error message
					break;

				case 3:
					StdOut.println("Choose a column to play in:");
					// TODO: Read in chosen column
					// TODO: Check that value is within the given bounds, check
					// that the data is numeric
					// TODO: Play teleport disc in chosen column and reduce the
					// number of teleporters left
					// TODO: Check that player has teleport discs left to play,
					// otherwise print out an error message

						P = 5;


										if ( curppowers[1]>=1){
											curppowers[1
										]--;
											if(player1){
											p1powers=curppowers;
											}else{
								p2powers=curppowers;
											}



											try{K = StdIn.readInt()-1;}
										catch(Exception e){	StdOut.println("Invalid input."); }


										if ((K > -1) && (K < 8)) {
											Teleport(K, grid, player1);
											gravity(grid);
										} else {
											StdOut.println("Illegal move please try again player " + Turn);

										}
					}else{ StdOut.println("Out of Teleporters Player " + Turn);
					break;
					}
										gravity(grid);
										Display(grid);


					break;

				case 4:
					StdOut.println("Choose a column to play in:");
					// TODO: Read in chosen column
					// TODO!=: Check that value is within the given bounds,
					// check
					// that the data is numeric
					// TODO: Play Colour Change disc in chosen column and reduce
					// the number of colour changers left
					// TODO: Check that player has Colour Change discs left to
					// play, otherwise print out an error message

						P = 5;


										if ( curppowers[2]>=1){
											curppowers[2]--;
											if(player1){
											p1powers=curppowers;
											}else{
								p2powers=curppowers;
											}



											try{K = StdIn.readInt()-1;}
										catch(Exception e){	StdOut.println("Invalid input."); }


										if ((K > -1) && (K < 8)) {
											Colour_Changer(K, grid, player1);
											gravity(grid);
										} else {
											StdOut.println("Illegal move please try again player " + Turn);

										}
					}else{ StdOut.println("Out of Colour Changers Player " + Turn);
					break;
					}
										gravity(grid);
										Display(grid);

				// This part will be used during testing, please DO NOT change
				// it. This will result in loss of marks
				case 5:
				gravity(grid);
					Display(grid);
					// Displays the current gameboard again, doesn't count as a
					// move, so the player stays the same

					break;

				// This part will be used during testing, please DO NOT change
				// it. This will result in loss of marks
				case 6:
					grid = Test(StdIn.readString());
					// Reads in a test file and loads the gameboard from it.
					player1 = !player1;
					break;
				// This part will be used during testing, please DO NOT change
				// it. This will result in loss of marks
				case 7:
					Save(StdIn.readString(), grid);
					player1 = !player1;
					break;

				default: // TODO: Invalid choice was made, print out an error
							// message but do not switch player turns
							gravity(grid);
					if ((K < 0) || (K > 7))
						System.out.println("Please choose a valid option");
					player1 = !player1;

					// TODO: Checks whether a winning condition was met

				}
gravity(grid);


      win = Check_Win(grid);
				if (win != 0) {
					if (win == 1) {
						StdOut.println("Player " + Turn + " wins!");
						StdOut.println("Do you want to play again? " + "\n 1. Yes" + "\n 2.No");
						for (int i = 0; i < 6; i++) {
							for (int k = 0; k < 7; k++) {
								grid[i][k] = 0;

							}
						}
						char Choice = StdIn.readChar();
						if (Choice == '1') {
							player1 = !player1;

						} else if (StdIn.readChar() == '2') {
							Exit();

						}

					} else if (win == 2) {
						StdOut.println("Draw!");
						StdOut.print("Do you want to play again? " + "\n 1. Yes" + "\n 2. No");


				}}

				// TODO: Switch the players turns
				player1 = !player1;
				Turn = (Turn % 2) + 1;

		}
		}


		//GUI
		else{
			win=0;
boolean B=false,T=false,CC=false;
StdDraw.setCanvasSize();


			StdDraw.setPenColor(StdDraw.BOOK_BLUE);
			StdDraw.filledRectangle(1, 1, 1, 1);
			StdDraw.setPenColor();
			StdDraw.text(0.5, 0.01 , "CONNECT 4!");

			StdDraw.setPenColor(StdDraw.WHITE);
				StdDraw.setPenRadius(0.0000001);
			StdDraw.filledSquare(0.1, 0.1, 0.04);
			StdDraw.text(0.1, 0.01 , "B");
			StdDraw.filledSquare(0.2, 0.1, 0.04);
			StdDraw.text(0.2, 0.01 , "T");
			StdDraw.filledSquare(0.3, 0.1, 0.04);
			StdDraw.text(0.3, 0.01 , "C");

			for(int y=0; y < 6; y++){
			for(int x=0;x<7;x++){
				StdDraw.setPenColor();
				StdDraw.filledCircle(0.142*x+0.071, 0.142*y+0.142+0.071, 0.071);

			}
			}
			//Normal
			while(true){
				int IN=0;

				if(player1==true){
					current=p1powers;
										StdDraw.setPenColor(StdDraw.RED);

					StdDraw.filledCircle(0.5,0.1,0.04);
								StdDraw.setPenColor();
					StdDraw.text(0.5,0.1,"1");
				}else{
					current=p2powers;
					StdDraw.setPenColor(StdDraw.YELLOW);
StdDraw.filledCircle(0.5,0.1,0.04);
			StdDraw.setPenColor();
StdDraw.text(0.5,0.1,"2");
				}

				StdDraw.enableDoubleBuffering();
				StdDraw.show();
				if(StdDraw.isMousePressed()){
					if(StdDraw.mouseX()>0.096 && StdDraw.mouseX()<0.104 && StdDraw.mouseY()<0.104 && StdDraw.mouseY()>0.096  ){
	      StdDraw.setPenColor(StdDraw.GREEN);
				StdDraw.filledSquare(0.1, 0.1, 0.04);
				StdDraw.setPenColor();

								StdDraw.text(0.1, 0.1, Integer.toString(current[0]));
								StdDraw.setPenColor(StdDraw.WHITE);
									StdDraw.filledSquare(0.3, 0.1, 0.04);
										StdDraw.filledSquare(0.2, 0.1, 0.04);
										B=true;
										T=false;
										CC=false;

					}else
					if(StdDraw.mouseX()>0.196 && StdDraw.mouseX()<0.204 && StdDraw.mouseY()<0.204 && StdDraw.mouseY()<0.196  ){
	      			StdDraw.setPenColor(StdDraw.GREEN);
							StdDraw.filledSquare(0.2, 0.1, 0.04);
							StdDraw.setPenColor();

											StdDraw.text(0.2, 0.1, Integer.toString(current[1]));
								StdDraw.setPenColor(StdDraw.WHITE);
									StdDraw.filledSquare(0.3, 0.1, 0.04);
										StdDraw.filledSquare(0.1, 0.1, 0.04);
										B=false;
										T=true;
										CC=false;
									}else
									if(StdDraw.mouseX()>0.296 && StdDraw.mouseX()<0.304 && StdDraw.mouseY()<0.304 && StdDraw.mouseY()<0.296  ){
								StdDraw.setPenColor(StdDraw.GREEN);
											StdDraw.filledSquare(0.3, 0.1, 0.04);
											StdDraw.setPenColor();

															StdDraw.text(0.3, 0.1, Integer.toString(current[2]));
												StdDraw.setPenColor(StdDraw.WHITE);
													StdDraw.filledSquare(0.2, 0.1, 0.04);
														StdDraw.filledSquare(0.1, 0.1, 0.04);
														B=false;
														T=false;
														CC=true;
													}else{ 		StdDraw.setPenColor(StdDraw.WHITE);
																	StdDraw.filledSquare(0.3, 0.1, 0.04);
																			StdDraw.filledSquare(0.2, 0.1, 0.04);
																				StdDraw.filledSquare(0.1, 0.1, 0.04);
																				B=false;
																				T=false;
																				CC=false;
				}}
		if (StdDraw.hasNextKeyTyped() ){
			if(B){





						IN =  StdDraw.nextKeyTyped()-49;

							if(IN>=0 &&  IN<8 && current[0]>0){
									if(grid[0][IN]==0){
								Bomb(IN,grid,player1);
								current[0]--;
								if(player1=true){
									p1powers=current;
								}else{
									p2powers=current;
								}
								StdAudio.play("Explosion+1.wav");
								gravity(grid);
									player1=!player1;
									GDis(grid);

				}else if(grid[0][IN]!=0){
					double hz = 220.00;
					double seconds = 0.1;
					int SAMPLE_RATE = 44100;
					int N = (int) (seconds * SAMPLE_RATE);
					double[] a = new double[N+1];
					for (int i = 0; i <= N; i++) {
						 a[i] = Math.sin(2 * Math.PI * i * hz / SAMPLE_RATE);
					}
					StdAudio.play(a);
				player1=!player1;
				}



			}else if(IN<0 || IN>7){
					double hz = 220.00;
					double seconds = 0.1;
					int SAMPLE_RATE = 44100;
					int N = (int) (seconds * SAMPLE_RATE);
					double[] a = new double[N+1];
					for (int i = 0; i <= N; i++) {
						 a[i] = Math.sin(2 * Math.PI * i * hz / SAMPLE_RATE);
					}
					StdAudio.play(a);
				player1=!player1;
			}else if(current[0]<=0){
				StdDraw.setPenColor(StdDraw.RED);
				StdDraw.filledSquare(0.1, 0.1, 0.04);
			}
			}else if(T){
				IN =  StdDraw.nextKeyTyped()-49;
				if(grid[0][IN]==0 && current[1]>0){
					if(IN>=0 &&  IN<=7 ){
						current[1]--;
						if(player1=true){
							p1powers=current;
						}else{
							p2powers=current;
						}
						Teleport(IN,grid,player1);
						StdAudio.play("Arrow+Swoosh+1.wav");
						gravity(grid);
							player1=!player1;
				GDis(grid);

				}else{
				double hz = 220.00;
				double seconds = 0.1;
				int SAMPLE_RATE = 44100;
				int N = (int) (seconds * SAMPLE_RATE);
				double[] a = new double[N+1];
				for (int i = 0; i <= N; i++) {
				 a[i] = Math.sin(2 * Math.PI * i * hz / SAMPLE_RATE);
				}
				StdAudio.play(a);
				player1=!player1;
				}



				}else if(IN<0 || IN>7){
						double hz = 220.00;
						double seconds = 0.1;
						int SAMPLE_RATE = 44100;
						int N = (int) (seconds * SAMPLE_RATE);
						double[] a = new double[N+1];
						for (int i = 0; i <= N; i++) {
							 a[i] = Math.sin(2 * Math.PI * i * hz / SAMPLE_RATE);
						}
						StdAudio.play(a);
					player1=!player1;
				}else if(current[0]<=0){
					StdDraw.setPenColor(StdDraw.RED);
					StdDraw.filledSquare(0.1, 0.1, 0.04);

			}}else if(CC){
				IN =  StdDraw.nextKeyTyped()-49;
				if(grid[0][IN]==0){
					if(IN>=0 &&  IN<=7 && ){
					Colour_Changer(IN,grid,player1);
						player1=!player1;
						StdAudio.play("Chomp+1.wav");
						gravity(grid);

				GDis(grid);

				}else{
					Colour_Changer(IN,grid,player1);
						player1=!player1;
						StdAudio.play("Chomp+1.wav");
						gravity(grid);

				}



				}else{
				double hz = 220.00;
				double seconds = 0.1;
				int SAMPLE_RATE = 44100;
				int N = (int) (seconds * SAMPLE_RATE);
				double[] a = new double[N+1];
				for (int i = 0; i <= N; i++) {
				 a[i] = Math.sin(2 * Math.PI * i * hz / SAMPLE_RATE);
				}
				StdAudio.play(a);
				player1=!player1;
			}}
			else{

		IN =  StdDraw.nextKeyTyped()-49;
		if(grid[0][IN]==0){
			if(IN>=0 &&  IN<=7 ){
	P = 5;
				Play(IN,grid,player1);

				if(player1){
					player1=!player1;
				//StdDraw.setPenColor(StdDraw.RED);
				//	StdDraw.filledCircle(0.142*(IN)+0.071, 0.142+(5-P)*0.142+0.071, 0.071);
					double hz = 440.0;
					double seconds = 0.1;
					int SAMPLE_RATE = 44100;
					int N = (int) (seconds * SAMPLE_RATE);
					double[] a = new double[N+1];
					for (int i = 0; i <= N; i++) {
						 a[i] = Math.sin(2 * Math.PI * i * hz / SAMPLE_RATE);
					}
					StdAudio.play(a);
				}else{
					double hz = 880.00;
					double seconds = 0.1;
					int SAMPLE_RATE = 44100;
					int N = (int) (seconds * SAMPLE_RATE);
					double[] a = new double[N+1];
					for (int i = 0; i <= N; i++) {
					   a[i] = Math.sin(2 * Math.PI * i * hz / SAMPLE_RATE);
					}
					StdAudio.play(a);
					player1=!player1;
					//StdDraw.setPenColor(StdDraw.YELLOW);
				//	StdDraw.filledCircle(0.142*(IN)+0.071, 0.142+(5-P)*0.142+0.071, 0.071);

			}
			gravity(grid);
				GDis(grid);
}else{
	double hz = 220.00;
	double seconds = 0.1;
	int SAMPLE_RATE = 44100;
	int N = (int) (seconds * SAMPLE_RATE);
	double[] a = new double[N+1];
	for (int i = 0; i <= N; i++) {
		 a[i] = Math.sin(2 * Math.PI * i * hz / SAMPLE_RATE);
	}
	StdAudio.play(a);
player1=!player1;
}



}else{
	double hz = 220.00;
	double seconds = 0.1;
	int SAMPLE_RATE = 44100;
	int N = (int) (seconds * SAMPLE_RATE);
	double[] a = new double[N+1];
	for (int i = 0; i <= N; i++) {
		 a[i] = Math.sin(2 * Math.PI * i * hz / SAMPLE_RATE);
	}
	StdAudio.play(a);
player1=!player1;
}}}




			//Bomb

			// Teleport


			//Colour Shift


			//Check Win
win=Check_Win(grid);
if(win==1){
	if(!player1){
	StdDraw.setPenColor(StdDraw.RED);
	StdDraw.filledRectangle(1, 1, 1, 1);
	StdDraw.setPenColor();
	StdDraw.text(0.5, 0.5 , "Player 1 wins");
}else{
	StdDraw.setPenColor(StdDraw.YELLOW);
	StdDraw.filledRectangle(1, 1, 1, 1);
	StdDraw.setPenColor();
	StdDraw.text(0.5, 0.5 , "Player 2 wins");
}
	StdDraw.text(0.5, 0.4 , "Do you want to play again? " + "\n 1. Yes" + "\n 2.No");

for (int i = 0; i < 6; i++) {
	for (int k = 0; k < 7; k++) {
		grid[i][k] = 0;

}
	}

	if (IN == '1') {
		player1 = !player1;


		for(int y=0; y < 6; y++){
		for(int x=0;x<7;x++){
			StdDraw.setPenColor();
			StdDraw.filledCircle(0.142*x+0.071, 0.142*y+0.142+0.071, 0.071);
			StdDraw.setPenColor(StdDraw.BOOK_BLUE);
			StdDraw.filledRectangle(1, 1, 1, 1);
			StdDraw.setPenColor();
			StdDraw.text(0.5, 0.01 , "CONNECT 4!");

		}
		}

	} else if (IN == '2') {
		Exit();

	}



		}



}
	}}

public static void GDis(int grid[][]){
	for(int y=0; y < 6; y++){
	for(int x=0;x<7;x++){

		if(grid[y][x]==1){
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.filledCircle(0.142*x+0.071, 0.142*(5-y)+0.142+0.071, 0.071);
		}else if(grid[y][x]==2){
			StdDraw.setPenColor(StdDraw.YELLOW);
			StdDraw.filledCircle(0.142*x+0.071, 0.142*(5-y)+0.142+0.071, 0.071);
		}else{
			StdDraw.setPenColor();
			StdDraw.filledCircle(0.142*x+0.071, 0.142*(5-y)+0.142+0.071, 0.071);
		}


	}
	}
}

	/**
	 * Displays the grid, empty spots as *, player 1 as R and player 2 as Y.
	 * Shows column and row numbers at the top.
	 *
	 * @param grid
	 *            The current board state
	 */
	public static void Display(int[][] grid) {
		// TODO: Display the given board state with empty spots as *, player 1
		// as R and player 2 as Y. Shows column and row numbers at the top.
		int k = 0;
		int i = 0;
		StdOut.println();
		StdOut.print(' ');
		StdOut.print(' ');

		for (k = 0; k < 7; k++) {

			StdOut.print(k);
			StdOut.print(' ');
		}

		StdOut.println();

		for (i = 0; i < 6; i++) {

			StdOut.print(i);
			StdOut.print(' ');

			for (k = 0; k < 7; k++) {
				if (grid[i][k] == 1) {
					StdOut.print("R ");
				} else if (grid[i][k] == 2) {
					StdOut.print("Y ");
				}

				else {
					StdOut.print("* ");
				}
			}

			StdOut.println();
		}
		StdOut.println();

	}

	public static void Exit() {
		// TODO: Exit the game
		System.exit(0);
	}

	/**
	 * Plays a normal disc in the specified position (i) in the colour of the
	 * player given. Returns the modified grid or prints out an error message if
	 * the column is full.
	 *
	 * @param i
	 *            Column that the disc is going to be played in
	 * @param grid
	 *            The current board state
	 * @param player1
	 *            The current player
	 * @return grid The modified board state
	 */
	public static int[][] Play(int i, int[][] grid, boolean player1) {
		// TODO: Play a normal disc of the given colour, if the column is full,
		// print out the message: "Column is full.""


		if (grid[0][i] != 0) {
			System.out.println("Column is full");
		} else {
			while (grid[P][i] == 1 || grid[P][i] == 2) {
				P = P - 1;
			}

			if (player1 == true) {
				grid[P][i] = 1;
			} else {
				grid[P][i] = 2;
			}

		}
		return grid;
	}

	/**
	 * Checks whether a player has 4 tokens in a row or if the game is a draw.
	 *
	 * @param grid
	 *            The current board state in a 2D array of integers
	 */
	public static int Check_Win(int[][] grid) {
		boolean isFull = false;
		int winner = 0;
		int Count = 0;
		int  i = 0;

		// TODO: Check for all the possible win conditions as well as for a
		for (i = 0; i < 7; i++) {
			if (grid[0][i] != 0) {
				isFull = true;
			} else {
				isFull = false;
				break;
			}}
//Vertical
		for(i=0;i<4;i++){
			if (P < 3
					&& grid[P+i][K]==grid[P][K] && grid[P][K]!=0 && i<7  ) {

				winner = 1;
			}else{winner=0;
					break;}}

			//Horizontal
		Count=-1;

			for (i = 0; i <= 4; i++) {
				if( K+i<=6){
					if(grid[P][K+i]== grid[P][K]  && grid[P][K]!=0 ){
						Count++;
					}else{break;}}else{break;}

			}
			for (i = 0; i <= 4; i++) {
				if( K-i>=0 && i<7){
				if(grid[P][K-i]== grid[P][K] && grid[P][K]!=0  ){
					Count++;
				}else{break;}}else{break;}


		}
		if (Count >= 4) {
			winner = 1;
		}else{
			Count=0;}




		//Diag  1


		Count=-1;
		for (i = 0; i <= 4; i++) {
			if( K+i<=6 && P-i>=0){
				if(grid[P-i][K+i]== grid[P][K]  && grid[P][K]!=0 ){
					Count++;
				}else{break;}}else{break;}

		}	for (i = 0; i <= 4; i++) {
			if( K-i>=0 && P+i<=5){
			if(grid[P+i][K-i]== grid[P][K] && grid[P][K]!=0  ){
				Count++;
			}else{break;}}else{break;}


	}
	if (Count >= 4) {
		winner = 1;
	}

	//Diag  2


	Count=-1;
	for (i = 0; i <= 4; i++) {
		if( K+i<=6 && P+i<=5){
			if(grid[P+i][K+i]== grid[P][K] && grid[P][K]!=0 ){
				Count++;
			}else{break;}}else{break;}

	}	for (i = 0; i <= 4; i++) {
		if( K-i>=0 && P-i>=0){
		if(grid[P-i][K-i]== grid[P][K]  && grid[P][K]!=0  ){
			Count++;
		}else{break;}}else{break;}


}
if (Count >= 4) {
	winner = 1;
}






		if (isFull == true && winner == 0) {
			winner = 2;
		}
		return winner;
	}

	/**
	 * Plays a bomb disc that blows up the surrounding tokens and drops down
	 * tokens above it. Should not affect the board state if there's no space in
	 * the column. In that case, print the error message: "Column is full."
	 *
	 * @param i
	 *            Column that the disc is going to be played in
	 * @param grid
	 *            The current board state
	 * @param player1
	 *            The current player
	 * @return grid The modified board state
	 */
	public static int[][] Bomb(int i, int[][] grid, boolean player1) {
		// TODO: Play a bomb in the given column and make an explosion take
		// place. Discs should drop down afterwards. Should not affect the
		// board state if there's no space in the column. In that case, print
		// the error message: "Column is full."
		// Leaves behind a normal disc of the player's colour
		i = i ;
		P = 5;
		if (grid[0][i] != 0) {
			System.out.println("Column is full");
		} else {
			while (grid[P][i] == 1 || grid[P][i] == 2) {
				P = P - 1;
			}

			if (player1 == true) {
				grid[P][i] = 1;
			} else {
				grid[P][i] = 2;
			}

if(P>0 && P<5  ){
 if(i==0){
grid[P-1][i] = 0;
grid[P+1][i] = 0;
grid[P+1][i+1] = 0;
grid[P-1][i+1] = 0;
grid[P][i+1] = 0;
}else if(i==6){
grid[P-1][i] = 0;
grid[P+1][i] = 0;
grid[P][i-1] = 0;
grid[P+1][i-1] = 0;
grid[P-1][i-1] = 0;
}
else{
grid[P+1][i+1] = 0;
grid[P+1][i-1] = 0;
grid[P+1][i] = 0;
grid[P-1][i-1] = 0;
grid[P-1][i+1] = 0;
grid[P-1][i] = 0;
grid[P+1][i] = 0;
grid[P][i+1] = 0;
grid[P][i-1] = 0;
}

}else if(P==5){
if(i==0){
grid[P-1][i+1] = 0;
grid[P-1][i] = 0;
grid[P][i+1] = 0;
}else if(i==6){
grid[P][i-1] = 0;
grid[P-1][i-1] = 0;
grid[P-1][i] = 0;
	}else{
grid[P][i-1] = 0;
grid[P-1][i-1] = 0;
grid[P-1][i] = 0;
grid[P-1][i+1] = 0;
grid[P][i+1] = 0;
}}

else if (P==0){
if(i==0){
grid[P+1][i+1] = 0;
grid[P+1][i] = 0;
grid[P][i+1] = 0;
}else if(i==6){
grid[P][i-1] = 0;
grid[P+1][i] = 0;
grid[P+1][i-1] = 0;
	}
else{
grid[P][i-1] = 0;
grid[P+1][i-1] = 0;
grid[P+1][i] = 0;
grid[P+1][i+1] = 0;
grid[P][i+1] = 0;
}
}


		}

		return grid;
	}
	public static int[][] gravity(int[][] grid){
  for( int i =0 ; i<grid.length ;i++){
 for( int k =0 ; k<grid[i].length; k ++){
if(grid[i][k]!= 0){
	if( i+1<6){
 if (grid[i+1][k]==0){
 grid[i+1][k]=grid[i][k];
grid[i][k]=0;


}}}}


}return grid;}


	/**
	 * Plays a teleporter disc that moves the targeted disc 3 columns to the
	 * right. If this is outside of the board boundaries, it should wrap back
	 * around to the left side. If the column where the targeted disc lands is
	 * full, destroy that disc. If the column where the teleporter disc falls is
	 * full, play as normal, with the teleporter disc replacing the top disc.
	 *
	 * @param i
	 *            Column that the disc is going to be played in
	 * @param grid
	 *            The current board state
	 * @param player1
	 *            The current player
	 * @return grid The modified board state
	 */
	public static int[][] Teleport(int i, int[][] grid, boolean player1) {
		// TODO: Play a teleporter disc that moves the targeted disc 3 columns
		// to the right. If this is outside of the board
		// boundaries, it should wrap back around to the left side. If the
		// column where the targeted disc lands is full,
		// destroy that disc. If the column where the teleporter disc falls is
		// full, play as normal, with the teleporter
		// disc replacing the top disc.
		// No error message is required.
		// If the colour change disc lands on the bottom row, it must leave a
		// disc of the current player’s colour.

		P = 5;
		if (grid[0][i] != 0) {
			System.out.println("Column is full");
		} else {
			while (grid[P][i] == 1 || grid[P][i] == 2) {
				P = P - 1;
			}

	if (i<=3){
		if (player1 == true) {
			grid[P][i+3] = 1;
		} else {
			grid[P][i+3] = 2;
		}

	}else{
		if (player1 == true) {
			grid[P][i-4] = 1;
		} else {
			grid[P][i-4] = 2;
		}

	}
		}
		return grid;
	}





	/**
	 * Plays the colour changer disc that changes the affected disc's colour to
	 * the opposite colour
	 *
	 * @param i
	 *            Column that the disc is going to be played in
	 * @param grid
	 *            The current board state
	 * @param player1
	 *            The current player
	 * @return grid The modified board state
	 */
	public static int[][] Colour_Changer(int i, int[][] grid, boolean player) {
		// TODO: Colour Change: If the colour change disc lands on top of
		// another disc, it changes the colour of that
		// disc to the opposite colour. The power disc does not remain.
		// If the colour change disc lands on the bottom row, it must leave a
		// disc of the current player’s colour.

		P = 5;
		if (grid[0][i] != 0) {
			if (player == true) {
				grid[P+1][i] = 1;
			} else {
				grid[P+1][i] = 2;
			}
		} else {
			while (grid[P][i] == 1 || grid[P][i] == 2) {
				P = P - 1;
			}




			if (player == true ) {
				if(P!=5){
					grid[P+1][i] = 1;
				}else if(P==5){
					grid[P][i] = 1;
				}

			} else if (player==false) {
				if(P!=5){
					grid[P+1][i] = 2;
				}else if (P==5){
					grid[P][i] = 2;
				}
			}

		}





		return grid;
	}

	/**
	 * Reads in a board from a text file.
	 *
	 * @param name
	 *            The name of the given file
	 * @return
	 */
	// Reads in a game state from a text file, assumes the file is a txt file
	public static int[][] Test(String name) {
		int[][] grid = new int[6][7];
		try {
			File file = new File(name + ".txt");
			Scanner sc = new Scanner(file);

			for (int i = 0; i < X; i++) {
				for (int j = 0; j < Y; j++) {
					grid[i][j] = sc.nextInt();
				}

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return grid;
	}

	/**
	 * Saves the current game board to a text file.
	 *
	 * @param name
	 *            The name of the given file
	 * @param grid
	 *            The current game board
	 * @return
	 */
	// Used for testing
	public static int[][] Save(String name, int[][] grid) {
		try {
			FileWriter fileWriter = new FileWriter(name + ".txt");
			for (int i = 0; i < X; i++) {
				for (int j = 0; j < Y; j++) {
					fileWriter.write(Integer.toString(grid[i][j]) + " ");
				}
				fileWriter.write("\n");
			}
			fileWriter.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return grid;
	}

	/**
	 * Debugging tool, preferably use this since we can then turn off your
	 * debugging output if you forgot to remove it. Only prints out if the DEBUG
	 * variable is set to true.
	 *
	 * @param line
	 *            The String you would like to print out.
	 */
	public static void Debug(String line) {
		if (DEBUG)
			System.out.println(line);
	}
}

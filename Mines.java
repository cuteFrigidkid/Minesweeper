package mines;

import java.util.Random;
import java.util.Scanner;

public class Mines {

	/*
	 * Minesweeper is a game where the goal is to flag all of the mines.
	 * 
	 * The first step is choosing the height and width of the board. Then The
	 * number of mines is chosen.
	 * 
	 * The mines are placed randomly on the board. [Note need way of not placing
	 * mine on top of mine, efficiency]
	 * 
	 * Now the game can be played.
	 * 
	 * The player can flag, uncover or question a square. [if they select an
	 * uncovered square which is surrounded by a number of flags equal to the
	 * value of itself, it will uncover all the hidden square surrounding
	 * itself. It is possible to uncover a mine by doing this]
	 * 
	 * If all the mines are flagged, and all squares revealed then you win! :)
	 */

	/*
	 * 
	 * 
	 * the board will be represented with
	 * 
	 * player board
	 * 
	 * numbers being squares the player has uncovered ? being squares the player
	 * suspects is a bomb F being squares the player has flagged - being unknown
	 * squares
	 * 
	 * 
	 * 
	 * 
	 * 
	 * master board
	 * 
	 * 
	 * b - being a bomb - being not a bomb
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */

	/*
	static int width;
	static int height;
	static int mines;

	static char board[][][];
	*/

	static int x = 0;
	static int y = 0;

	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		
		// Asking dimensions.
		System.out.println("Please enter the Width of the Board");
		int width = scan.nextInt();

		System.out.println("Please enter the Height of the Board");
		int height = scan.nextInt();

		System.out.println("Please enter the Number of Mines");
		int mineCount = scan.nextInt();

		char[][] board = new char[height][width];
		int[][] mineCoordinates = populateMines(mineCount, height, width);
	
		printBoard();

		while (true) {
			play();
			win();
		}
	}
	
	public static int[][] populateMines(int mineCount, int height, int width) {
		int[][] mines = new int[mineCount][2];
		Random rand = new Random();
		
		for(int i = 0; i < mineCount; i++) {
			mines[i] = generateMine(width, height, mines);
		}
		
		return mines;
	}
	
	public static int[] generateMine(int x, int y, int[] existingCoords) {
		let coords = [rand.nextInt(width - 1) + 1, rand.nextInt(height - 1) + 1];
		
		if(Arrays.asList(existingCoords).contains(coords)) {
			coords = generateCoordinates(x, y, exitingCoords);
		}
		
		return coords;
	}

	public static void printBoard() {

		System.out.println();
		
		for (int i = 0; i < height; i++) {

			if (i == 0 || i == height - 1) {
				System.out.print(" ");
			} else {
				System.out.print(i);
			}

			for (int j = 0; j < width; j++) {
				System.out.print(board[n][j][i]);
			}

			System.out.println();

		}
		System.out.print("  ");
		for (int i = 1; i < width - 1; i++) {
			System.out.print(i);
		}
		System.out.println();

	}

	public static void populateBoardAuto() {

		// now the mines are placed randomly on the board

		// for (int i = 0; i < mines; i++) {
		/* code */
		// }

	}

	

	
	public static void minesF(int m, int n) {

		if (board[1][m][n] == 'B') {
			System.out.println("UH OH, you have activated a mine\nGame Over");
			
			playAgain();
		}

		char counter = '0';

		if (board[1][m + 1][n + 1] == 'B') {
			counter++;
		}
		if (board[1][m + 1][n] == 'B') {
			counter++;
		}
		if (board[1][m + 1][n - 1] == 'B') {
			counter++;
		}
		if (board[1][m][n + 1] == 'B') {
			counter++;
		}
		if (board[1][m][n - 1] == 'B') {
			counter++;
		}
		if (board[1][m - 1][n + 1] == 'B') {
			counter++;
		}
		if (board[1][m - 1][n] == 'B') {
			counter++;
		}
		if (board[1][m - 1][n - 1] == 'B') {
			counter++;
		}

		board[0][m][n] = counter;

		if (counter == '0') {
			zero(m, n);
		}

	}

	public static void shot() {


		System.out.println("Now we are uncovering a square");
		System.out.println("Please enter the x coordinate");
		x = scan.nextInt();

		System.out.println("Please enter the y coordinate");
		y = scan.nextInt();

		
		if (x < 1 || x > width - 2 || y < 1 || y > height - 2) {
			System.out.println("Your coordinates are out of bounds");
			return;
		}

		if (Character.isDigit(board[0][x][y])) {
			System.out.println("You have already revealed this square");
			return;
		}


		minesF(x, y);
		
		System.out.println("the number of mines surrounding " + x + " " + y + " " + board[0][x][y]);

		printBoard(0);

	}

	public static void flag() {

		System.out.println("Now we are flagging a square");
		System.out.println("Please enter the x coordinate");
		x = scan.nextInt();

		System.out.println("Please enter the y coordinate");
		y = scan.nextInt();

		

		board[x][y] = 'F';

		printBoard(0);

	}
	
	public static boolean isFlagable(int x, int y, int[] board) {
		if (x < 1 || x > width || y < 1 || y > height) {
			System.out.println("Your coordinates are out of bounds");
			return;
		}
		
		if (board[x][y] == 'F') {
			System.out.println("You have already flagged this square.");
			return;
		}
	}

	public static void play() {

		String play;
		System.out.println("Would you like to Flag(f) or Reveal(r)\nPlease enter f or r");
		
		play = scan.next();
				
		if (play.equals("f")) {
			flag();
		}

		if (play.equals("r")) {
			shot();
		}

	}

	public static void playAgain() {

		String play;
		System.out.println("Would you like to play again?\ny or n");

		play = scan.next();

		if (play.equals("y")) {
			System.out.println("You have selected to play again");
			playAgain();
		} else {
			System.exit(0);
		}
	}

	public static void zero(int m, int n) {

		if (board[0][m + 1][n + 1] == '-') {
			minesF(m + 1, n + 1);
		}
		if (board[0][m + 1][n] == '-') {
			minesF(m + 1, n);
		}
		if (board[0][m + 1][n - 1] == '-') {
			minesF(m + 1, n - 1);
		}
		if (board[0][m][n + 1] == '-') {
			minesF(m, n + 1);
		}
		if (board[0][m][n - 1] == '-') {
			minesF(m, n - 1);
		}
		if (board[0][m - 1][n + 1] == '-') {
			minesF(m - 1, n + 1);
		}
		if (board[0][m - 1][n] == '-') {
			minesF(m - 1, n);
		}
		if (board[0][m - 1][n - 1] == '-') {
			minesF(m - 1, n - 1);
		}
	}

	public static void win() {

		for (int i = 1; i < height - 1; i++) {
			for (int j = 1; j < width - 1; j++) {
				
				if (board[1][i][j] == '-') {
					continue;
				}
				
				if ( !( board[0][i][j] == 'F' && board[1][i][j] == 'B') ) {
					return;
				}

			}
		}

		System.out.println("All the squares have been checked and\nYou Win!!!\nCongratulations!!!");

		playAgain();

	}

}

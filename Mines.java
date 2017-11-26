package mines;

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

	static int width;
	static int height;
	static int mines;

	static char board[][][];

	static int x = 0;
	static int y = 0;

	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {

		initBoard();
		populateBoardManual();
		printBoard(0);

		while (true) {
			play();
			win();
		}

	}

	public static void initBoard() {

		// asking for the board dimensions, and number of mines

		System.out.println("Please enter the Width of the Board");
		width = scan.nextInt();

		System.out.println("Please enter the Height of the Board");
		height = scan.nextInt();

		System.out.println("Please enter the Number of Mines");
		mines = scan.nextInt();

		height += 2;
		width += 2;

		board = new char[2][height][width];

		// initialising the board
	
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				board[0][i][j] = '-';
				board[1][i][j] = '-';
				if (i == 0 || j == 0 || i == height - 1 || j == height - 1) {
					board[0][i][j] = 'X';
					board[1][i][j] = 'X';
				}
			}
		}
	}

	public static void printBoard(int n) {

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

	public static void populateBoardManual() {

		for (int i = 0; i < mines; i++) {
			System.out.println("Populating mine number " + (i + 1));

			System.out.println("Please enter the x coordinate");
			x = scan.nextInt();

			System.out.println("Please enter the y coordinate");
			y = scan.nextInt();

			board[1][x][y] = 'B';

			System.out.println("the bomb has been placed at " + x + " " + y + " and has value " + board[1][x][y]);

			printBoard(1);
		}

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

		if (x < 1 || x > width - 2 || y < 1 || y > height - 2) {
			System.out.println("Your coordinates are out of bounds");
			return;
		}
		
		if (board[0][x][y] == 'F') {
			System.out.println("You have already flagged this square.");
			return;
		}

		board[0][x][y] = 'F';

		printBoard(0);

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

package mines;

import java.util.Random;
import java.util.Scanner;

public class Mines {

	

	public static void main(String[] args) {

		
		char[][][] board;
		Scanner scan = new Scanner(System.in);
		
		
		System.out.println("Please enter the Width of the Board");
		int width = scan.nextInt();

		System.out.println("Please enter the Height of the Board");
		int height = scan.nextInt();

		System.out.println("Please enter the Number of Mines");
		int mineCount = scan.nextInt();
		
		board = new char[2][height + 2][width + 2];
		
		
		initBoard(height, width, board);
		//printBoard(height, width, board, 0);
		//populateBoardManual(height, width, board, scan, mineCount);
		populateBoardAuto(height, width, board, mineCount);
		printBoard(height, width, board, 0);

		while (true) {
			play(height, width, board, scan);
			win(height, width, board, scan);
		}

	}

	public static void initBoard(int height, int width, char[][][] board) {

		// asking for the board dimensions, and number of mines

		

		// Height and Width are increased because the board needs
		// to be 2 larger then what the player specifies.
		//height += 2;
		//width += 2;

		

		// initialising the board
	
		for (int i = 0; i < height + 2; i++) {
			for (int j = 0; j < width + 2; j++) {
				board[0][i][j] = '-';
				board[1][i][j] = '-';
				if (i == 0 || j == 0 || i == height + 1 || j == height + 1) {
					board[0][i][j] = 'X';
					board[1][i][j] = 'X';
				}
			}
		}
	}

	public static void printBoard(int height, int width, char[][][] board, int n) {

		System.out.println();
		
		for (int i = 0; i < height + 2; i++) {

			if (i == 0 || i == height + 1) {
				System.out.print(" ");
			} else {
				System.out.print(i);
			}

			for (int j = 0; j < width + 2; j++) {
				System.out.print(board[n][j][i]);
			}

			System.out.println();

		}
		System.out.print("  ");
		for (int i = 1; i < width + 1; i++) {
			System.out.print(i);
		}
		System.out.println();

	}

	public static void populateBoardManual(int height, int width, char[][][] board, Scanner scan, int mineCount) {
	
		
		//This method is useful when you want to debug the program
		
		for (int i = 0; i < mineCount; i++) {
			System.out.println("Populating mine number " + (i + 1));
	
			System.out.println("Please enter the x coordinate");
			int x = scan.nextInt();
	
			System.out.println("Please enter the y coordinate");
			int y = scan.nextInt();
	
			board[1][x][y] = 'B';
	
			System.out.println("the bomb has been placed at " + x + " " + y + " and has value " + board[1][x][y]);
	
			printBoard(height, width, board, 1);
		}
	
	}

	public static void populateBoardAuto(int height, int width, char[][][] board, int mineCount) {

		// now the mines are placed randomly on the board
		
		Random rand = new Random();
				
		for (int i = 0; i < mineCount; i++) {
			placeMine(height, width, rand, mineCount, board);			
		}

		printBoard(height, width, board, 1);
		
	}
	
	public static void placeMine(int height, int width, Random rand, int mineCount, char[][][] board) {
				
		
			int a = rand.nextInt(height) + 1;
			int b = rand.nextInt(width) + 1;
			
			 
			if (board[1][a][b] == 'B') {
				placeMine(height, width, rand, mineCount, board);
			}
			
			board[1][a][b] ='B';
		
	}

	public static void flag(int height, int width, char[][][] board, Scanner scan) {
	
		System.out.println("Now we are flagging a square");
		System.out.println("Please enter the x coordinate");
		int x = scan.nextInt();
	
		System.out.println("Please enter the y coordinate");
		int y = scan.nextInt();
	
		if (x < 1 || x > width || y < 1 || y > height ) {
			System.out.println("Your coordinates are out of bounds");
			return;
		}
		
		if (board[0][x][y] == 'F') {
			System.out.println("You have already flagged this square.");
			return;
		}
	
		board[0][x][y] = 'F';
	
		printBoard(height, width, board, 0);
	
	}

	public static void shot(int height, int width, char[][][] board, Scanner scan) {
	
	
		System.out.println("Now we are uncovering a square");
		System.out.println("Please enter the x coordinate");
		int x = scan.nextInt();
	
		System.out.println("Please enter the y coordinate");
		int y = scan.nextInt();
	
		
		if (x < 1 || x > width || y < 1 || y > height ) {
			System.out.println("Your coordinates are out of bounds");
			return;
		}
	
		if (Character.isDigit(board[0][x][y])) {
			System.out.println("You have already revealed this square");
			return;
		}
	
	
		minesF(x, y, board, scan);
		
		System.out.println("the number of mines surrounding " + x + " " + y + " " + board[0][x][y]);
	
		printBoard(height, width, board, 0);
	
	}

	public static void minesF(int m, int n, char[][][] board, Scanner scan) {

		if (board[1][m][n] == 'B') {
			System.out.println("UH OH, you have activated a mine\nGame Over");
			
			playAgain(scan);
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
			zero(m, n, board, scan);
		}

	}

	public static void zero(int m, int n, char[][][] board, Scanner scan) {
	
		if (board[0][m + 1][n + 1] == '-') {
			minesF(m + 1, n + 1, board, scan);
		}
		if (board[0][m + 1][n] == '-') {
			minesF(m + 1, n, board, scan);
		}
		if (board[0][m + 1][n - 1] == '-') {
			minesF(m + 1, n - 1, board, scan);
		}
		if (board[0][m][n + 1] == '-') {
			minesF(m, n + 1, board, scan);
		}
		if (board[0][m][n - 1] == '-') {
			minesF(m, n - 1, board, scan);
		}
		if (board[0][m - 1][n + 1] == '-') {
			minesF(m - 1, n + 1, board, scan);
		}
		if (board[0][m - 1][n] == '-') {
			minesF(m - 1, n, board, scan);
		}
		if (board[0][m - 1][n - 1] == '-') {
			minesF(m - 1, n - 1, board, scan);
		}
	}

	public static void play(int height, int width, char[][][] board, Scanner scan) {

		String p;
		System.out.println("Would you like to Flag(f) or Reveal(r)\nPlease enter f or r");
		
		p = scan.next();
				
		if (p.equals("f")) {
			flag(height, width, board, scan);
		}

		if (p.equals("r")) {
			shot(height, width, board, scan);
		}

	}

	public static void playAgain(Scanner scan) {

		String p;
		System.out.println("Would you like to play again?\ny or n");

		p = scan.next();

		if (p.equals("y")) {
			System.out.println("You have selected to play again");
			playAgain(scan);
		} else {
			System.exit(0);
		}
	}

	public static void win(int height, int width, char[][][] board, Scanner scan) {

		for (int i = 1; i < height + 1; i++) {
			for (int j = 1; j < width + 1; j++) {
				
				//If there is no bomb then we check the next square.
				if (board[1][i][j] == '-') {
					continue;
				}
				
				//If there is a bomb, that is not flagged we RETURN
				//If there is a bomb, that is flagged	  we CONTINUE (although we dont use continue keyword because it's unneccsassry)
				if ( !( board[0][i][j] == 'F' && board[1][i][j] == 'B') ) {
					return;
				}

			}
		}

		//If we reach this point, then we checked every square and determined that you have won.
		
		System.out.println("All the squares have been checked and\nYou Win!!!\nCongratulations!!!");

		playAgain(scan);

	}

}

package entity;

import entity.chess.Chess;

//Author 在线疯狂
//Homepage http://bookshadow.com
public class Board {

	public static final char SELF = 's';
	public static final char OPPONENT = 'o';

	public static final int UP = 0;
	public static final int DOWN = 1;

	public Board() {
	}

	public Board(Chess[][] board, char upColor) {
		this.board = board;
		this.upColor = upColor;
	}

	private Chess[][] board;
	private char upColor; // Chess color in upper board

	public Chess[][] getBoard() {
		return board;
	}

	public void setBoard(Chess[][] board) {
		this.board = board;
	}

	public char getUpColor() {
		// System.out.println("upColor = " + upColor);
		return upColor;
	}

	public void setUpColor(char upColor) {
		this.upColor = upColor;
	}
	
	public static Chess findGeneral(Chess board[][], char color) {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j] != null && board[i][j].getCode() == Chess.GENERAL && board[i][j].getColor() == color) {
					return board[i][j];
				}
			}
		}
		return null;
	}
	
	public Chess findGeneral(char color) {
		return findGeneral(this.board, color);
	}
	
	public int countChess(Coordinate start, Coordinate end) {
		return countChess(start, end, Chess.RED) + countChess(start, end, Chess.BLACK);
	}
	
	public static int countChess(Chess board[][], Coordinate start, Coordinate end) {
		return countChess(board, start, end, Chess.RED) + countChess(board, start, end, Chess.BLACK);
	}
	
	public int countChess(Coordinate start, Coordinate end, char color) {
		return countChess(board, start, end, color);
	}
	
	public static int countChess(Chess board[][], Coordinate start, Coordinate end, char color) {
		int sx = start.getX();
		int sy = start.getY();
		int ex = end.getX();
		int ey = end.getY();
		int minX = Math.min(sx, ex);
		int maxX = Math.max(sx, ex);
		int minY = Math.min(sy, ey);
		int maxY = Math.max(sy, ey);
		int cnt = 0;
		for (int i = minX; i <= maxX; i++) {
			for (int j = minY; j <= maxY; j++) {
				if (board[i][j] != null && board[i][j].getColor() == color)
					cnt++;
			}
		}
		return cnt;
	}
	
	public int getBottom(char color) {
		return color == upColor ? 0 : 9;
	}
	
	public int getRiver(char color) {
		return color == upColor ? 4 : 5;
	}
	
	public static boolean isEdge(int y) {
		return y == 0 || y == 8; 
	}
}

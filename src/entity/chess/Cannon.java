package entity.chess;

import java.util.ArrayList;

import entity.Board;
import entity.Coordinate;

public class Cannon extends Chess {
	
	public final static int VALUE = 90;
	private final static int deltaX[] = { 1, 0, -1, 0 };
	private final static int deltaY[] = { 0, 1, 0, -1 };

	public Cannon() {
		super.setCode(Chess.CANNON);
	}

	@Override
	public ArrayList<Coordinate> getPossibleLocations(Board chessBoard) {
		Chess[][] board = chessBoard.getBoard();
		ArrayList<Coordinate> al = new ArrayList<Coordinate>();
		Coordinate sourceCoo = getCoordinate();
		int x = sourceCoo.getX();
		int y = sourceCoo.getY();
		for (int i = 0; i < deltaX.length; i++) {
			int dx = deltaX[i];
			int dy = deltaY[i];
			int j = 1;
			while (isValid(x + j * dx, y + j * dy)
					&& board[x + j * dx][y + j * dy] == null) {
				al.add(new Coordinate(x + j * dx, y + j * dy));
				j++;
			}
			j++;
			while (isValid(x + j * dx, y + j * dy)
					&& board[x + j * dx][y + j * dy] == null) {
				j++;
			}
			if (isValid(x + j * dx, y + j * dy)) {
				Chess targetChess = board[x + j * dx][y + j * dy];
				if (targetChess != null && targetChess.getColor() != getColor()) {
					al.add(new Coordinate(x + j * dx, y + j * dy));
				}
			}
		}

		/*
		 * for (int i = 0; i <= 9; i++) { int x = i; if (i == sourceCoo.getX())
		 * continue; int y = sourceCoo.getY(); Coordinate targetCoo = new
		 * Coordinate(x,y); if (checkMove(board, targetCoo)) {
		 * al.add(targetCoo); } }
		 */
		return al;
	}
	
	@Override
	public int getValue(Board chessBoard) {
		int bonus = 0;
		if (getCoordinate().getY() == 4)
			bonus += 3;
		if (getCoordinate().getX() == chessBoard.getBottom(Chess.oppositeColor(getColor())))
			bonus += 3;
		return VALUE + bonus;
	}
	/*
	 * @Override public boolean isValidMove(Chess[][] board, Coordinate
	 * targetCoo) { Coordinate sourceCoo = getCoordinate(); int sx =
	 * sourceCoo.getX(); int sy = sourceCoo.getY(); int tx = targetCoo.getX();
	 * int ty = targetCoo.getY(); int barrier = 0; int min, max; if (sx == tx) {
	 * min = Math.min(sy, ty); max = Math.max(sy, ty); for (int y = min + 1; y <
	 * max; y++) { if (board[sx][y] != null) barrier++; } } else { min =
	 * Math.min(sx, tx); max = Math.max(sx, tx); for (int x = min + 1; x < max;
	 * x++) { if (board[x][sy] != null) barrier++; } } if (board[tx][ty] ==
	 * null) { return barrier == 0; } else { Chess targetChess = board[tx][ty];
	 * return barrier == 1 && getColor() != targetChess.getColor(); } }
	 */
}

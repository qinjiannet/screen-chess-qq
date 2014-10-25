package entity.chess;

import java.util.ArrayList;

import entity.Board;
import entity.Coordinate;
//Author 在线疯狂
//Homepage http://bookshadow.com
public class Assistant extends Chess {
	public final static int VALUE = 20;
	private final static int deltaX[] = { 1, -1, 1, -1 };
	private final static int deltaY[] = { 1, -1, -1, 1 };

	public Assistant() {
		super.setCode(Chess.ASSISTANT);
	}

	@Override
	public ArrayList<Coordinate> getPossibleLocations(Board chessBoard) {
		Chess[][] board = chessBoard.getBoard();
		ArrayList<Coordinate> al = new ArrayList<Coordinate>();
		Coordinate sourceCoo = getCoordinate();
		for (int i = 0; i < deltaX.length; i++) {
			int x = sourceCoo.getX() + deltaX[i];
			int y = sourceCoo.getY() + deltaY[i];
			if (!isValid(x, y)) {
				continue;
			}
			if (board[x][y] != null && (board[x][y].getColor() == getColor())) {
				continue;
			}
			al.add(new Coordinate(x, y));
		}
		return al;
	}

	@Override
	public boolean isValid(int x, int y) {
		return ((x <= 2 && x >= 0) || (x >= 7 && x <= 9)) && (y >= 3 && y <= 5);
	}

	@Override
	public int getValue(Board chessBoard) {
		return (int)(VALUE * (isRing(chessBoard) ? 1.3 : 1));
	}
	
	private boolean isRing(Board chessBoard) {
		Chess[][] board = chessBoard.getBoard();
		Coordinate sourceCoo = getCoordinate();
		int x = sourceCoo.getX();
		int y = sourceCoo.getY();
		for (int i = 0; i < deltaX.length; i++) {
			int nx = deltaX[i] + x;
			int ny = deltaY[i] + y;
			if (isValid(nx, ny)) {
				Chess targetChess = board[nx][ny];
				if (targetChess != null && targetChess.getCode() == getCode() && targetChess.getColor() == getColor())
					return true;
			}
		}
		return false;
	}
}

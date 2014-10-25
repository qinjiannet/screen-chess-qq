package entity.chess;

import java.util.ArrayList;

import entity.Board;
import entity.Coordinate;
//Author 在线疯狂
//Homepage http://bookshadow.com
public class Minister extends Chess {
	public final static int VALUE = 20;
	private final static int deltaX[] = { 2, -2, 2, -2 };
	private final static int deltaY[] = { 2, 2, -2, -2 };

	public Minister() {
		super.setCode(Chess.MINISTER);
	}

	@Override
	public ArrayList<Coordinate> getPossibleLocations(Board chessBoard) {
		Chess[][] board = chessBoard.getBoard();
		ArrayList<Coordinate> al = new ArrayList<Coordinate>();
		Coordinate sourceCoo = getCoordinate();
		int x = sourceCoo.getX();
		int y = sourceCoo.getY();
		for (int i = 0; i < deltaX.length; i++) {
			int nx = deltaX[i] + x;
			int ny = deltaY[i] + y;
			if (isValid(nx, ny) && !isBlocked(board, deltaX[i], deltaY[i])) {
				Chess targetChess = board[nx][ny];
				if (targetChess == null) {
					al.add(new Coordinate(nx, ny));
				} else if (targetChess.getColor() != getColor()) {
					al.add(new Coordinate(nx, ny));
				}
			}
		}

		return al;
	}

	private boolean isBlocked(Chess[][] board, int dx, int dy) {
		Coordinate sourceCoo = getCoordinate();
		int x = sourceCoo.getX();
		int y = sourceCoo.getY();
		return board[x + dx / 2][y + dy / 2] != null;
	}

	@Override
	public boolean isValid(int x, int y) {
		switch (y) {
		case 0:
		case 4:
		case 8:
			return x == 2 || x == 7;
		case 2:
		case 6:
			return x == 0 || x == 4 || x == 5 || x == 9;
		}
		return false;
	}
	
	@Override
	public int getValue(Board chessBoard) {
		int penalty = 0;
		int x = getCoordinate().getX();
		int y = getCoordinate().getY();
		if (Board.isEdge(y))
			penalty = (int)(Pawn.VALUE * 1.7);
		else if (chessBoard.getRiver(getColor()) == x)
			penalty = Pawn.VALUE;
		return (int)(VALUE * (isRing(chessBoard) ? 1.7 : 1)) - penalty;
	}
	
	private boolean isRing(Board chessBoard) {
		Chess[][] board = chessBoard.getBoard();
		Coordinate sourceCoo = getCoordinate();
		int x = sourceCoo.getX();
		int y = sourceCoo.getY();
		for (int i = 0; i < deltaX.length; i++) {
			int nx = deltaX[i] + x;
			int ny = deltaY[i] + y;
			if (isValid(nx, ny) && !isBlocked(board, deltaX[i], deltaY[i])) {
				Chess targetChess = board[nx][ny];
				if (targetChess != null && targetChess.getCode() == getCode() && targetChess.getColor() == getColor())
					return true;
			}
		}
		return false;
	}
}

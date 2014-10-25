package entity.chess;

import java.util.ArrayList;

import entity.Board;
import entity.Coordinate;
//Author 在线疯狂
//Homepage http://bookshadow.com
public class Knight extends Chess {
	public final static int VALUE = 80;
	private final static int deltaX[] = { 2, 2, 1, 1, -1, -1, -2, -2 };
	private final static int deltaY[] = { 1, -1, 2, -2, 2, -2, 1, -1 };

	public Knight() {
		super.setCode(Chess.KNIGHT);
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
		if (Math.abs(dx) == 2) {
			return board[x + dx / 2][y] != null;
		}
		return board[x][y + dy / 2] != null;
	}
	
	@Override
	public int getValue(Board chessBoard) {
		int bonus = 0;
		int x = getCoordinate().getX();
		int y = getCoordinate().getY();
		int bottom = chessBoard.getBottom(getColor());
		if (bottom != x)
			bonus = 7;
		else {
			bonus = -4;
			if (Math.abs(y - 4) <= 1)
				bonus -= 4;
		}
		if (!Board.isEdge(y))
			bonus += 4;
		else
			bonus -= 5;
		bonus += (isRing(chessBoard) ? Pawn.VALUE / 2 : 0);
		if (Math.abs(bottom - x) == 1 && y == 4)
			bonus = -8;
		return bonus + VALUE;
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

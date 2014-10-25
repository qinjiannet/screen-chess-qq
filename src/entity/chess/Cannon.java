package entity.chess;

import java.util.ArrayList;

import entity.Board;
import entity.Coordinate;
//Author 在线疯狂
//Homepage http://bookshadow.com
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

}

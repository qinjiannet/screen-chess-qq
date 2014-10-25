package entity.chess;

import java.util.ArrayList;

import entity.Board;
import entity.Coordinate;
//Author 在线疯狂
//Homepage http://bookshadow.com
public class Rook extends Chess {
	public final static int VALUE = 180;
	private final static int deltaX[] = { 1, 0, -1, 0 };
	private final static int deltaY[] = { 0, 1, 0, -1 };

	public Rook() {
		super.setCode(Chess.ROOK);
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
		int x = getCoordinate().getX();
		int y = getCoordinate().getY();
		int delta = Math.abs(y - 4);
		int bonus = delta < 4 ? 5 : 0;
		//int bonus = 0;
		if (x != chessBoard.getBottom(getColor()) || !Board.isEdge(y))
			bonus += Pawn.VALUE - 2;
		if (x == chessBoard.getRiver(getColor()))
			bonus += Pawn.VALUE - 4;
		if (x == 2 || x == 7)
			bonus = 0;
		if (y % 2 == 1)
			bonus += 3;
		else if (y == 2 || y == 6)
			bonus -= 6;
		int columnChess = chessBoard.countChess(new Coordinate(0,y), new Coordinate(9,y), getColor());
		int rowChess = chessBoard.countChess(new Coordinate(x,0), new Coordinate(x,8), getColor());
		//Penalty when touching the bottom line
		return bonus + VALUE + (int)Math.max((8 - columnChess) / 4 , (7 - rowChess) / 4);
	}
}

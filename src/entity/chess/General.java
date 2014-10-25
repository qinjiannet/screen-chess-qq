package entity.chess;

import java.util.ArrayList;

import entity.Board;
import entity.Coordinate;
//Author 在线疯狂
//Homepage http://bookshadow.com
public class General extends Chess {
	public final static int VALUE = 100000;
	private final static int deltaX[] = { 1, 0, -1, 0 };
	private final static int deltaY[] = { 0, 1, 0, -1 };

	public General() {
		super.setCode(Chess.GENERAL);
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
		Coordinate enemyGeneralCoo = generalMeet(board);
		if (enemyGeneralCoo != null)
			al.add(enemyGeneralCoo);
		return al;
	}

	@Override
	public boolean isValid(int x, int y) {
		return ((x <= 2 && x >= 0) || (x >= 7 && x <= 9)) && (y >= 3 && y <= 5);
	}
	
	public Coordinate generalMeet(Chess board[][]) {
		char color = getColor();
		
		Chess opponentGeneral = Board.findGeneral(board, Chess.oppositeColor(color));
		if (opponentGeneral == null)
			return null;
		
		int sy = getCoordinate().getY();
		int oy = opponentGeneral.getCoordinate().getY();
		if (sy == oy) {
			if (Board.countChess(board, getCoordinate(), opponentGeneral.getCoordinate()) == 2) {
				return opponentGeneral.getCoordinate();
			}
		}
		return null;
	}
	
	@Override
	public int getValue(Board chessBoard) {
		return VALUE;
	}
}

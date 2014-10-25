package entity.chess;

import helper.DistanceHelper;

import java.util.ArrayList;

import entity.Board;
import entity.Coordinate;
//Author 在线疯狂
//Homepage http://bookshadow.com
public class Pawn extends Chess {
	public final static int VALUE = 6;
	private final static int deltaX[] = { -1, 1, 0, 0 };
	private final static int deltaY[] = { 0, 0, -1, 1 };

	public Pawn() {
		super.setCode(Chess.PAWN);
	}

	@Override
	public ArrayList<Coordinate> getPossibleLocations(Board chessBoard) {
		Chess[][] board = chessBoard.getBoard();
		ArrayList<Coordinate> al = new ArrayList<Coordinate>();
		Coordinate sourceCoo = getCoordinate();
		int x = sourceCoo.getX();
		int y = sourceCoo.getY();
		int forward = (chessBoard.getUpColor() == getColor()) ? Board.DOWN
				: Board.UP;
		int fx = x + deltaX[forward];
		int fy = y + deltaY[forward];
		if (isValid(fx, fy)
				&& (board[fx][fy] == null || board[fx][fy].getColor() != getColor())) {
			al.add(new Coordinate(fx, fy));
		}
		if ((forward == Board.DOWN && x > 4) || (forward == Board.UP && x < 5)) {
			for (int i = 2; i < 4; i++) {
				int nx = x + deltaX[i];
				int ny = y + deltaY[i];
				if (isValid(nx, ny)
						&& (board[nx][ny] == null || board[nx][ny].getColor() != getColor())) {
					al.add(new Coordinate(nx, ny));
				}
			}
		}
		return al;
	}
	
	@Override
	public int getValue(Board chessBoard) {
		//Add cross-river-bonus
		int bonus = isCrossRiver(chessBoard) ? (int)(1.8 * VALUE) : 0;
		//Add bonus when drawing near to the middle
		int x = getCoordinate().getX();
		int y = getCoordinate().getY();
		int delta = Math.abs(y - 4);
		bonus += (int)((4 - delta) * bonus * 0.4);
		if (y == 2 || y == 6)
			bonus += (chessBoard.getRiver(getColor()) == x) ? 3 : 0;
		//Penalty when touching the bottom line
		int dist = Math.min(9 - x, x);
		int penalty = (dist == 0) ? VALUE / 2 : (dist == 1 ? VALUE / 4 : 0);
		if (chessBoard.getRiver(getColor()) == x)
			penalty = (y == 4) ? 4 : 0;
		int distBonus = 0;
		Chess opponentGeneral = chessBoard.findGeneral(Chess.oppositeColor(getColor()));
		if (opponentGeneral != null) {
			int finalDist = DistanceHelper.calcDist(opponentGeneral.getCoordinate(), getCoordinate());
			if (finalDist <= 7)
				distBonus += ((7 - finalDist) * 1.3);
		}
			
		return VALUE + bonus - penalty + distBonus;
	}
	
	public boolean isCrossRiver(Board chessBoard) {
		int x = getCoordinate().getX();
		int forward = (chessBoard.getUpColor() == getColor()) ? Board.DOWN
				: Board.UP;
		return (forward == Board.DOWN && x > 4) || (forward == Board.UP && x < 5);
	}
}

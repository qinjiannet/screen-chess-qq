package move;

import java.util.ArrayList;

import entity.Board;
import entity.Coordinate;
import entity.Move;
import entity.chess.Chess;
//Author 在线疯狂
//Homepage http://bookshadow.com
public class MoveGenerator {
	public MoveGenerator() {
	}

	public MoveGenerator(Board chessBoard, char color) {
		this.chessBoard = chessBoard;
		this.color = color;
	}

	private Board chessBoard;
	private char color;

	public ArrayList<Move> getPossibleMoves() {
		ArrayList<Move> al = new ArrayList<Move>();
		Chess board[][] = chessBoard.getBoard();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] != null && board[i][j].getColor() == color) {
					ArrayList<Coordinate> cooList = board[i][j]
							.getPossibleLocations(chessBoard);
					for (Coordinate coo : cooList) {
						al.add(new Move(board[i][j], coo));
					}
				}
			}
		}
		return al;
	}
}

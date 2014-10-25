package ai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import move.MoveGenerator;
import entity.Board;
import entity.Move;
import entity.Pair;
import entity.chess.Chess;
import entity.chess.General;
//Author 在线疯狂
//Homepage http://bookshadow.com
public class Thinker {
	
	public final static int MAX_THOUGHT = 1000000;
	private static int getMark(Chess chess) {
		switch (chess.getCode()) {
		case Chess.ROOK:
			return 5;
		case Chess.CANNON:
			return 3;
		case Chess.KNIGHT:
			return 4;
		case Chess.ASSISTANT:
		case Chess.MINISTER:
			return 2;
		case Chess.PAWN:
			return 1;
		default:
			return 0;
		}
	}
	private final static Comparator<Move> COMPARATOR = new Comparator<Move>() {
		@Override
		public int compare(Move a, Move b) {
			return getMark(b.getChess()) - getMark(a.getChess());
		}
	}; 
	
	public final static int THRESHOLD = General.VALUE / 2;
	private Board chessBoard;
	private char color;

	public Thinker() {
	}

	public Thinker(Board chessBoard, char color) {
		this.chessBoard = chessBoard;
		this.color = color;
	}

	public Board getChessBoard() {
		return chessBoard;
	}

	public void setChessBoard(Board chessBoard) {
		this.chessBoard = chessBoard;
	}

	public char getColor() {
		return color;
	}

	public void setColor(char color) {
		this.color = color;
	}

	public Move getRandomMove() {
		MoveGenerator mg = new MoveGenerator(chessBoard, color);
		ArrayList<Move> moveList = mg.getPossibleMoves();
		int size = moveList.size();
		if (size == 0)
			return null;

		int idx = (int) (Math.random() * size);
		Move randomMove = moveList.get(idx);
		return randomMove;
	}

	public Move getBestMove() {
		Pair<Move, Integer> pair = search(MAX_THOUGHT, chessBoard, color);
		if (pair != null) {
			System.out.println(pair.getValue());
			return pair.getKey();
		}
		return null;
	}
	
	private Pair<Move,Integer> search(int thought, Board chessBoard, char color) {
		Pair<Move, Integer> bestAns = new Pair<Move,Integer>(null,-Integer.MAX_VALUE);
		if (thought == 0)
			return bestAns;
		Chess[][] board = chessBoard.getBoard();
		MoveGenerator mg = new MoveGenerator(chessBoard, color);
		ArrayList<Move> moveList = mg.getPossibleMoves();
		Collections.shuffle(moveList);
		Collections.sort(moveList,COMPARATOR);

		for (Move move : moveList) {
			Chess source = move.getChess();
			int beforeVal = source.getValue(chessBoard);
			int sx = source.getCoordinate().getX();
			int sy = source.getCoordinate().getY();
			int tx = move.getTargetCoo().getX();
			int ty = move.getTargetCoo().getY();
			Chess newTarget = move.makeTargetChess();
			Chess oldTarget = board[tx][ty];
			int gain = (oldTarget == null) ? 0 : (oldTarget.getValue(chessBoard));
			
			board[sx][sy] = null;
			board[tx][ty] = newTarget;
			General general = (General)Board.findGeneral(board, color);
			if (general != null && general.generalMeet(board) == null) {
				int benefit = newTarget.getValue(chessBoard) - beforeVal + gain;
				if (benefit < THRESHOLD && thought > 0) {
					Pair<Move, Integer> ans = search(thought / moveList.size(), chessBoard, Chess.oppositeColor(color));
					if (ans.getKey() != null) {
						benefit -= ans.getValue();
					}
				}
				if (benefit > bestAns.getValue()) {
					bestAns.setKey(move);
					bestAns.setValue(benefit);
				}
			}
			board[sx][sy] = source;
			board[tx][ty] = oldTarget;

		}
		
		return bestAns;
	}
}

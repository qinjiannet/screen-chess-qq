package entity;

import java.awt.Point;

import recognition.RecognitionHelper;

import entity.chess.Chess;
import entity.chess.ChessFactory;

//Author 在线疯狂
//Homepage http://bookshadow.com
public class Move {
	private Chess chess;
	private Coordinate targetCoo;

	public Move() {
	}

	public Move(Chess chess, Coordinate targetCoo) {
		this.chess = chess;
		this.targetCoo = targetCoo;
	}

	public Chess getChess() {
		return chess;
	}

	public void setChess(Chess chess) {
		this.chess = chess;
	}

	public Coordinate getTargetCoo() {
		return targetCoo;
	}

	public void setTargetCoo(Coordinate targetCoo) {
		this.targetCoo = targetCoo;
	}

	public Point calcTargetPoint() {
		Coordinate sourceCoo = chess.getCoordinate();
		Point sourcePoint = chess.getLocation();
		int dx = targetCoo.getX() - sourceCoo.getX();
		int dy = targetCoo.getY() - sourceCoo.getY();
		int targetX = (int) sourcePoint.getX() + RecognitionHelper.gridSize
				* dx;
		int targetY = (int) sourcePoint.getY() + RecognitionHelper.gridSize
				* dy;
		return new Point(targetX, targetY);
	}
	
	public Chess makeTargetChess() {
		Chess targetChess = ChessFactory.newChess(chess.getCode());
		targetChess.setColor(chess.getColor());
		targetChess.setCoordinate(targetCoo);
		targetChess.setLocation(calcTargetPoint());
		targetChess.setName(chess.getName());
		return targetChess;
	}
	
	@Override
	public String toString() {
		return getChess() + " -> "
		+ getTargetCoo();
	}
}

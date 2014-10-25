package entity.chess;

//Author 在线疯狂
//Homepage http://bookshadow.com
public class ChessFactory {
	public static Chess newChess(char code) {
		switch (code) {
		case Chess.ROOK: // Rooks
			return new Rook();
		case Chess.KNIGHT: // Knights
			return new Knight();
		case Chess.MINISTER: // Minister
			return new Minister();
		case Chess.ASSISTANT: // Assistants
			return new Assistant();
		case Chess.GENERAL: // Generals
			return new General();
		case Chess.CANNON: // Cannons
			return new Cannon();
		case Chess.PAWN: // Pawns
			return new Pawn();
		default:
			return null; // Error
		}
	}
	
}

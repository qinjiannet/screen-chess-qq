package recognition;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import color.ColorHelper;

import entity.Board;
import entity.Coordinate;
import entity.chess.Chess;
import entity.chess.ChessFactory;
//Author 在线疯狂
//Homepage http://bookshadow.com
public class RecognitionHelper {

	public static final int gridSize = 48;

	public static final int chessSize = 32; // 42

	public static final int orderSize = 42;

	public static final char CHESS_NAME[] = { '车', '马', '炮', '砲', '象', '相',
			'士', '仕', '将', '帅', '兵', '卒' };
	public static final int CHESS_VAL[] = { 96, 110, 101, 81, 112, 124, 55, 70,
			97, 122, 88, 82 };

	public static final HashMap<Integer, Character> CHESS_NAME_MAP = new HashMap<Integer, Character>();

	static {
		for (int i = 0; i < CHESS_VAL.length; i++) {
			CHESS_NAME_MAP.put(CHESS_VAL[i], CHESS_NAME[i]);
		}
	}

	// Screen Coordinate
	public static int[] getStartXY(BufferedImage bi) {
		int width = (int) bi.getWidth();
		int height = (int) bi.getHeight();
		int startX = -1, startY = -1;
		for (int i = 0; i < height; i++) {
			int cnt = 0;
			for (int j = 0; j < width; j++) {
				int rgb = bi.getRGB(j, i);
				if (ColorHelper.PATTERN_SET.contains(rgb)) {
					cnt++;
				}
			}
			if (cnt > 100) {
				startX = i;
				break;
			}
		}

		for (int i = 0; i < width; i++) {
			int cnt = 0;
			for (int j = 0; j < height; j++) {
				int rgb = bi.getRGB(i, j);
				if (ColorHelper.PATTERN_SET.contains(rgb)) {
					cnt++;
				}
			}
			if (cnt > 400) {
				startY = i - 2;
				break;
			}
		}

		return new int[] { startX, startY };
	}

	public static HashMap<Integer, Integer> getChess(BufferedImage bi) {
		HashMap<Integer, Integer> hmap = new HashMap<Integer, Integer>();
		int width = bi.getWidth();
		int height = bi.getHeight();
		for (int ii = 0; ii < width; ii++) {
			for (int jj = 0; jj < height; jj++) {
				int rgb = bi.getRGB(ii, jj);
				Integer val = hmap.get(rgb);
				
				if (val == null) {
					val = 0;
				}
				hmap.put(rgb, val + 1);
			}
		}
		return hmap;
	}

	public static char getSelfColor(BufferedImage bi) {
		int startXY[] = getStartXY(bi);
		if (startXY[0] < 0 || startXY[1] < 0)
			return 0;
		int startLX = startXY[0] + 338;
		int startY = 0;
		BufferedImage lowerImage = bi.getSubimage(startY, startLX, startXY[1],
				orderSize);
		return getColor(lowerImage);
	}

	/**
	 * getOrder
	 * 
	 * @param bi
	 * @return null:error,"sr":self red,"or":opponent red, "sb":self black,
	 *         "ob":opponent black
	 */
	public static String getOrder(BufferedImage bi) {
		int startXY[] = getStartXY(bi);
		if (startXY[0] < 0 || startXY[1] < 0)
			return null;

		int startUX = startXY[0] + 90;
		int startLX = startXY[0] + 338;
		int startY = 0;
		BufferedImage upperImage = bi.getSubimage(startY, startUX, startXY[1],
				orderSize);
		BufferedImage lowerImage = bi.getSubimage(startY, startLX, startXY[1],
				orderSize);
		
		char uc = getColor(upperImage);
		char lc = getColor(lowerImage);
		String ret = null;
		if (uc != Chess.WHITE) {
			ret = "" + Board.OPPONENT + uc;
		} else if (lc != Chess.WHITE) {
			ret = "" + Board.SELF + lc;
		}
		return ret;
	}

	/**
	 * getColor
	 * 
	 * @param bi
	 * @return 'w':white,'r':red,'b':black
	 */
	public static char getColor(BufferedImage bi) {
		int blackCnt = 0;
		int redCnt = 0;
		int width = bi.getWidth();
		int height = bi.getHeight();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int rgb = bi.getRGB(i, j);
				if (ColorHelper.BLACK == rgb) {
					blackCnt++;
				} else if (ColorHelper.RED == rgb) {
					redCnt++;
				}
			}
		}
		
		if (blackCnt > 80) {
			return 'b';
		}
		if (redCnt > 110) {
			return 'r';
		}
		return 'w';
	}

	/**
	 * GetChessStatus By BufferedImage
	 * 
	 * @param bi
	 *            Screenshot
	 * @return
	 */
	public static Board getChessBoard(BufferedImage bi) {
		if (bi == null)
			return null;

		int startXY[] = getStartXY(bi);
		if (startXY[0] < 0 || startXY[1] < 0)
			return null;

		int chessStartX = startXY[0] + 30;
		int chessStartY = startXY[1] + 30;
		
		Chess[][] ans = new Chess[10][9];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 9; j++) {
				int x = chessStartX + i * RecognitionHelper.gridSize;
				int y = chessStartY + j * RecognitionHelper.gridSize;
				int sx = x - RecognitionHelper.chessSize / 2;
				int sy = y - RecognitionHelper.chessSize / 2;
				BufferedImage subImage = bi.getSubimage(sy, sx,
						RecognitionHelper.chessSize,
						RecognitionHelper.chessSize);
				HashMap<Integer, Integer> chessMap = RecognitionHelper
						.getChess(subImage);
				
				Character color = null;
				Character name = null;
				Character redName = null;
				Character blackName = null;
				Integer redNum = chessMap.get(ColorHelper.RED);
				Integer blackNum = chessMap.get(ColorHelper.BLACK);
				if (redNum != null) {
					redName = RecognitionHelper.CHESS_NAME_MAP.get(redNum);
				} else if (chessMap.get(ColorHelper.BLACK) != null) {
					blackName = RecognitionHelper.CHESS_NAME_MAP.get(blackNum);
				}
				Point point = new Point(x, y);
				Chess chess = null;
				if (redName != null || blackName != null) {
					if (redName != null) {
						color = '红';
						name = redName;
					} else {
						color = '黑';
						name = blackName;
					}
					char code = Chess.name2Code(name);
					chess = ChessFactory.newChess(code);
					
					chess.setLocation(point);
					chess.setColor(Chess.color2Alphabet(color));
					chess.setCoordinate(new Coordinate(i, j));
					
					chess.setName(color + "" + name);
				}
				ans[i][j] = chess;
				
			}
			
		}
		// Identify upper generals's color
		char upColor = Chess.WHITE;
		outer: for (int i = 0; i <= 2; i++) {
			for (int j = 3; j <= 5; j++) {
				if (ans[i][j] != null && ans[i][j].getCode() == Chess.GENERAL) {
					upColor = ans[i][j].getColor();
					break outer;
				}
			}
		}
		return new Board(ans, upColor);
	}
}

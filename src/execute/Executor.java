package execute;

import java.awt.AWTException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import move.ChessMover;
import recognition.RecognitionHelper;
import recognition.ScreenshotGetter;
import ai.Thinker;
import entity.Board;
import entity.Move;
import entity.chess.Chess;
//Author 在线疯狂
//Homepage http://bookshadow.com
public class Executor {

	public static void main(String[] args) throws InterruptedException,
			AWTException, IOException {

		ScreenshotGetter sg = new ScreenshotGetter();
		while (true) {
			BufferedImage bi = sg.getScreenshot();

			String orderStr = RecognitionHelper.getOrder(bi);
			System.out.println("order=" + orderStr);
			if (orderStr != null) {
				char order = orderStr.charAt(0);
				char color = orderStr.charAt(1);

				Board board = RecognitionHelper.getChessBoard(bi);
				if (order == 's') {
					print(board.getBoard());
					Thinker thinker = new Thinker(board, color);
					Move move = thinker.getRandomMove();
					System.out.println(move);
					ChessMover cm = new ChessMover(move);
					cm.move();
				}
			}

			Thread.sleep(3000);
		}
	}

	public static void print(Chess[][] status) {
		if (status == null)
			System.out.println("null");
		else
			for (int i = 0; i < status.length; i++) {
				for (int j = 0; j < status[i].length; j++) {
					String output = "  ";
					if (status[i][j] != null) {
						output = status[i][j].getName();
					}
					System.out.print(output + "\t");
				}
				System.out.println("");
			}
		System.out.println("");
	}

}

package test;

import java.awt.AWTException;
import java.awt.Point;

import move.ChessMover;

import org.junit.Test;

public class TestChessMover {

	@Test
	public void test() throws AWTException, InterruptedException {
		Point start = new Point(447, 481);
		Point end = new Point(450, 450);
		ChessMover chessMover = new ChessMover(start, end);
		chessMover.move();
	}

}

package move;

import java.awt.AWTException;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;

import entity.Move;
//Author 在线疯狂
//Homepage http://bookshadow.com
public class ChessMover {

	private Point start;
	private Point end;

	public ChessMover() {
	}

	public ChessMover(Move move) {
		Point sourcePoint = move.getChess().getLocation();
		Point targetPoint = move.calcTargetPoint();
		this.start = sourcePoint;
		this.end = targetPoint;
	}

	public ChessMover(Point start, Point end) {
		this.start = start;
		this.end = end;
	}

	public void move() throws AWTException, InterruptedException {
		final Robot robot = new Robot();
		robot.mouseMove((int) start.getY(), (int) start.getX());
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);

		Thread.sleep(1000);

		robot.mouseMove((int) end.getY(), (int) end.getX());
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);

	}

	public Point getStart() {
		return start;
	}

	public void setStart(Point start) {
		this.start = start;
	}

	public Point getEnd() {
		return end;
	}

	public void setEnd(Point end) {
		this.end = end;
	}

}

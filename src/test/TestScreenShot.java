package test;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import org.junit.Test;
//Author 在线疯狂
//Homepage http://bookshadow.com
public class TestScreenShot {

	@Test
	public void testScreenshot() throws InterruptedException {
		try {
			Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
			int width = (int) dimension.getWidth();
			int height = (int) dimension.getHeight();
			System.out.println(width + " " + height);
			final Robot robot = new Robot();

			robot.mouseMove(500, 400);
			while (true) {
				BufferedImage screenshot = (robot)
						.createScreenCapture(new Rectangle(0, 0, width, height));

				for (int i = 0; i < width; i++) {
					for (int j = 0; j < height; j++) {
						int rgb = screenshot.getRGB(i, j);
						int R = (rgb & 0xff0000) >> 16;
						int G = (rgb & 0xff00) >> 8;
						int B = (rgb & 0xff);
					}
				}
				Thread.sleep(20000);
			}

		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

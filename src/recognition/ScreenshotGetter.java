package recognition;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
//Author 在线疯狂
//Homepage http://bookshadow.com
public class ScreenshotGetter {
	public BufferedImage getScreenshot() throws AWTException {
		final Robot robot = new Robot();
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) dimension.getWidth();
		int height = (int) dimension.getHeight();
		BufferedImage screenshot = robot.createScreenCapture(new Rectangle(0,
				0, width, height));
		return screenshot;
	}
}

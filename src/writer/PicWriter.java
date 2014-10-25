package writer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
//Author 在线疯狂
//Homepage http://bookshadow.com
public class PicWriter {
	private BufferedImage bi;
	private File file;
	private final static String format = "png";

	public PicWriter() {

	}

	public PicWriter(BufferedImage bi, File file) {
		this.bi = bi;
		this.file = file;
	}

	public void writeToFile() throws IOException {
		ImageIO.write(bi, format, file);
	}

	public BufferedImage getBi() {
		return bi;
	}

	public void setBi(BufferedImage bi) {
		this.bi = bi;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
}

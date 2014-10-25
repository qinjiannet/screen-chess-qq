package color;

import java.util.HashSet;
//Author 在线疯狂
//Homepage http://bookshadow.com
public class ColorHelper {

	public static final int PATTERN_ARR[] = { -2569056, -2571120, -2571112,
			-3099528, -1516360, -2573176, -2044752, -2046816, -2571104,
			-1516352, -2044760, -2573168, -3099512, -2042696, -3101576,
			-1516344, -1518408, -2573160, -3099520, -2044768, -2042704 };
	public static final HashSet<Integer> PATTERN_SET = new HashSet<Integer>();

	static {
		for (int rgb : PATTERN_ARR) {
			PATTERN_SET.add(rgb);
		}
	}

	public static final int RED_RGB[] = { 200, 8, 0 };
	public static final int RED = -3667968;
	public static final int BLACK_RGB[] = { 56, 56, 56 };
	public static final int BLACK = -13092808;

	public static int[] getRGB(int rgb) {
		int R = (rgb & 0xff0000) >> 16;
		int G = (rgb & 0xff00) >> 8;
		int B = (rgb & 0xff);
		return new int[] { R, G, B };
	}

}

package helper;

import entity.Coordinate;
//Author 在线疯狂
//Homepage http://bookshadow.com
public class DistanceHelper {
	public static int calcDist(Coordinate a, Coordinate b) {
		return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
	}
}

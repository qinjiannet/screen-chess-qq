package helper;

import entity.Coordinate;

public class DistanceHelper {
	public static int calcDist(Coordinate a, Coordinate b) {
		return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
	}
}

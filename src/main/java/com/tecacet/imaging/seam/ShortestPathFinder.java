package com.tecacet.imaging.seam;

/**
 * Find the shortest seam in a picture
 * 
 * @author dimitri
 *
 */
public class ShortestPathFinder {
	
	/**
	 * Given the energy return the seam of least energy 
	 * A seam is represented as an array of length equal to the height of the picture
	 * the element int[i] represents the x location of the seam  at height i 
	 * 
	 * @param energy
	 * @return
	 */
	public int[] findShortestPath(double[][] energy) {
		double[][] lookup = computeShortestPaths(energy);
		return lookupShortestPath(lookup);
	}

	private int[] lookupShortestPath(double[][] lookup) {
		int width = lookup.length;
		// find the x location of the shortest path
		double shortest = lookup[0][0];
		int minX = 0;
		for (int x = 1; x < width; x++) {
			if (lookup[x][0] < shortest) {
				minX = x;
				shortest = lookup[x][0];
			}
		}
		return findPathStartingAt(lookup, minX);
	}

	private int[] findPathStartingAt(double[][] lookup, int x) {
		int height = lookup[0].length;
		int[] path = new int[height];
		path[0] = x;
		for (int y = 1; y < height; y++) {
			path[y] = min(lookup, y, path[y - 1]);
		}
		return path;
	}

	private int min(double[][] lookup, int y, int x) {
		int width = lookup.length;
		double min = Double.MAX_VALUE;
		int best = 0;
		for (int i = x - 1; i <= x + 1; i++) {
			if (i < 0 || i >= width) {
				continue;
			}
			if (lookup[i][y] < min) {
				min = lookup[i][y];
				best = i;
			}
		}
		return best;
	}

	/*
	 * Compute shortest paths from each point at the top lookup[x][y] : length of
	 * shortest path to the top of the picture from x,y
	 */
	private double[][] computeShortestPaths(double[][] energy) {

		int width = energy.length;
		int height = energy[0].length;
		double[][] paths = new double[width][height];

		// The last value is just the value of the energy
		for (int x = 0; x < width; x++) {
			paths[x][height - 1] = energy[x][height - 1];
		}
		for (int y = height - 2; y >= 0; y--) {
			for (int x = 0; x < width; x++) {
				double min = Double.MAX_VALUE;
				for (int i = x - 1; i <= x + 1; i++) { // explore neighbors
					if (i < 0 || i >= width) {
						continue;
					}
					if (paths[i][y + 1] < min) {
						min = paths[i][y + 1];
					}
				}
				paths[x][y] = min + energy[x][y];
			} // for x
		} // for y
		return paths;
	}
}

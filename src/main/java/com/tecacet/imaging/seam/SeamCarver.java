package com.tecacet.imaging.seam;

import java.awt.Color;
import java.util.Stack;

/**
 * Uses the seam carving algorith to resize an image
 * 
 * @author dimitri
 *
 */
public class SeamCarver {

	private final ShortestPathFinder shortestPathFinder = new ShortestPathFinder();
	private final EnergyCalculator energyCalculator = new EnergyCalculator();
	
	private Stack<int[]> verticalSeams = new Stack<>();
	private Stack<int[]> horizontalSeams = new Stack<>();
	
	/**
	 * Reduced the size of the image by the required columns and rows
	 * 
	 * @param initialPicture the initial image
	 * @param removeColumns number of columns to remove 
	 * @param removeRows number of rows to remove
	 * 
	 * @return an image with the lowest energy seams removed
	 */
	public Picture resize(Picture initialPicture, int removeColumns, int removeRows) {
		verticalSeams = new Stack<>();
		horizontalSeams = new Stack<>();
		Picture picture = removeColumns(initialPicture, removeColumns, true);
		picture = removeRows(picture, removeRows);
		Picture bufferedPicture = new BufferedImagePicture(picture.getWidth(), picture.getHeight());
		for (int x = 0; x < picture.getWidth(); x++) {
			for (int y = 0; y < picture.getHeight(); y++) {
				bufferedPicture.set(x, y, picture.get(x, y));
			}
		}
		return bufferedPicture;
	}
	
	private Picture removeRows(Picture picture, int removeRows) {
		Picture transpose = new TransposePicture(picture);
		transpose = removeColumns(transpose, removeRows, false);
		return new TransposePicture(transpose);
	}
	
	private Picture removeColumns(Picture picture, int removeColumns, boolean isVertical) {
		for (int i = 0; i < removeColumns; i++) {
			double[][] energy = energyCalculator.computeEnergy(picture);
			int[] seam = findSeam(energy);
			picture = removeSeam(picture, seam);
			if (isVertical) {
				verticalSeams.push(seam);
			} else {
				horizontalSeams.push(seam);
			}
		}
		return picture;
	}

	private int[] findSeam(double[][] energy) {
		return shortestPathFinder.findShortestPath(energy);
	}

	private Picture removeSeam(Picture picture, int[] seam) {
		Picture newPicture = new ArrayPicture(picture.getWidth() - 1, picture.getHeight());
		for (int y = 0; y < picture.getHeight(); y++) {
			int i = 0;
			for (int x = 0; x < picture.getWidth(); x++) {
				if (x != seam[y]) {
					newPicture.set(i++, y, picture.get(x, y));
				}
			}
		}
		return newPicture;
	}
	
	/**
	 * Add a seam back to the picture and display it a specific color
	 * This method is used to display the seams that were removed
	 * 
	 * @param picture
	 * @param seam
	 * @param seamColor the color that the seam will appear in 
	 * @return
	 */
	public Picture addSeam(Picture picture, int[] seam, Color seamColor) {
		Picture newPicture = new ArrayPicture(picture.getWidth() + 1, picture.getHeight());
		for (int y = 0; y < newPicture.getHeight(); y++) {
			for (int x = 0; x < newPicture.getWidth(); x++) {
				if (x == seam[y]) {
					newPicture.set(seam[y], y, seamColor);
				} else if (x < seam[y]) {
					newPicture.set(x, y, picture.get(x, y));
				} else {
					newPicture.set(x, y, picture.get(x-1, y));
				}
			}
		}
		return newPicture;
	}

	/**
	 * Get the vertical seams that were removed
	 * @return
	 */
	public Stack<int[]> getVerticalSeams() {
		return verticalSeams;
	}

	/**
	 * Get the horizontal seams that were removed
	 * @return
	 */
	public Stack<int[]> getHorizontalSeams() {
		return horizontalSeams;
	}
	
}

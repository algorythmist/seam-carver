package com.tecacet.imaging.seam;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Stack;

import javax.imageio.ImageIO;

public class SeamCarvingDemo {

	private final EnergyCalculator energyCalculator = new EnergyCalculator();

	public void runDemo(String filename, int removeColumns, int removeRows) throws IOException {
		// load in a picture
		Picture picture = BufferedImagePicture.readFromFile(filename);

		// show the initial energy of the picture
		double[][] energy = energyCalculator.computeEnergy(picture);
		BufferedImage energyImage = EnergyCalculator.toBufferedImage(energy);
		ImageIO.write(energyImage, "png", new File("energy.png"));

		// resize the image with seam carving
		long startTime = System.currentTimeMillis();
		SeamCarver seamCarver = new SeamCarver();
		Picture output = seamCarver.resize(picture, removeColumns, removeRows);
		long endTime = System.currentTimeMillis();
		System.out.printf("Time to resize = %d milliseconds.\n", (endTime - startTime));
		ImageIO.write(output.getImage(), "png", new File("compressed.png"));

		// Now create a picture with the seams showing
		Picture withSeams = addSeams(seamCarver, output);
		ImageIO.write(withSeams.getImage(), "png", new File("with_seams.png"));
	}

	private Picture addSeams(SeamCarver seamCarver, final Picture picture) {
		Picture pictureWithSeams = picture;
		Stack<int[]> horizontalSeams = seamCarver.getHorizontalSeams();
		while (!horizontalSeams.isEmpty()) {
			int[] seam = horizontalSeams.pop();
			pictureWithSeams = seamCarver.addHorizontalSeam(pictureWithSeams, seam, Color.BLUE);
		}
		Stack<int[]> verticalSeams = seamCarver.getVerticalSeams();
		while (!verticalSeams.isEmpty()) {
			int[] seam = verticalSeams.pop();
			pictureWithSeams = seamCarver.addVerticalSeam(pictureWithSeams, seam, Color.BLUE);
		}

		return pictureWithSeams;
	}

	public static void main(String[] args) throws IOException {
		new SeamCarvingDemo().runDemo("apple.jpg", 80, 13);

	}
}

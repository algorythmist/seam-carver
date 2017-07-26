package com.tecacet.imaging.seam;

import java.awt.Color;

public class EnergyCalculator {

	// maximal energy = 3*(255^2)
	private final double BORDER_ENERGY = 195075;

	public double[][] energy(Picture picture) {
		double[][] energy = new double[picture.getWidth()][picture.getHeight()];
		for (int j = 0; j < picture.getHeight(); j++) {
			for (int i = 0; i < picture.getWidth(); i++) {
				energy[i][j] = energy(picture, i, j);
			}
		}
		return energy;
	}
	
	private double energy(Picture picture, int x, int y) {
		if (isBorder(x, y, picture)) {
			return BORDER_ENERGY;
		}
		Color left = picture.get(x - 1, y);
		Color right = picture.get(x + 1, y);
		Color up = picture.get(x, y - 1);
		Color down = picture.get(x, y + 1);
		return norm(left, right) + norm(up, down);
	}

	private int norm(Color c1, Color c2) {
		return square(c1.getRed() - c2.getRed()) + square(c1.getGreen() - c2.getGreen())
				+ square(c1.getBlue() - c2.getBlue());
	}

	private int square(int x) {
		return x * x;
	}

	private boolean isBorder(int x, int y, Picture picture) {
		return x == 0 || x == picture.getWidth() - 1 || y == 0 || y == picture.getHeight() - 1;
	}

	
}

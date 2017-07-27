package com.tecacet.imaging.seam;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * A simple implementation of Picture as a 2D array
 * 
 * @author dimitri
 *
 */
public class ArrayPicture implements Picture {

	private final Color[][] pixels;
	private final int width;
	private final int height;

	public ArrayPicture(BufferedImage image) {
		this.width = image.getWidth();
		this.height = image.getHeight();
		this.pixels = new Color[width][height];
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				pixels[x][y] = new Color(image.getRGB(x, y));
			}
		}
	}

	public ArrayPicture(int width, int height) {
		this.width = width;
		this.height = height;
		this.pixels = new Color[width][height];
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public Color get(int x, int y) {
		return pixels[x][y];
	}

	@Override
	public void set(int x, int y, Color color) {
		pixels[x][y] = color;
	}

	@Override
	public BufferedImage getImage() {
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, get(x,y).getRGB());
			}
		}
		return image;
	}

}

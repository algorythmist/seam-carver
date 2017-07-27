package com.tecacet.imaging.seam;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Implementation of the Picture interface using a BufferedImage
 * 
 */
public final class BufferedImagePicture implements Picture {

	private final BufferedImage image;
	private final int width;
	private final int height; 

	/**
	 * Initializes a blank <tt>width</tt>-by-<tt>height</tt> picture, where each pixel is
	 * black.
	 */
	public BufferedImagePicture(int width, int height) {
		this.width = width;
		this.height = height;
		this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	}
	
	public BufferedImagePicture(BufferedImage image) {
		this.image = image;
		this.width = image.getWidth();
		this.height = image.getHeight();
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
		if (x < 0 || x >= getWidth()) {
			throw new IndexOutOfBoundsException("x must be between 0 and " + (getWidth() - 1));
		}
		if (y < 0 || y >= getHeight()) {
			throw new IndexOutOfBoundsException("y must be between 0 and " + (getHeight() - 1));
		}
		return new Color(image.getRGB(x, y));
	}
	
	@Override
	public void set(int x, int y, Color color) {
		if (x < 0 || x >= getWidth()) {
			throw new IndexOutOfBoundsException("x must be between 0 and " + (getWidth() - 1));
		}
		if (y < 0 || y >= getHeight()) {
			throw new IndexOutOfBoundsException("y must be between 0 and " + (getHeight() - 1));
		}
		image.setRGB(x, y, color.getRGB());
	}

	@Override
	public BufferedImage getImage() {
		return image;
	}
	
	/**
	 * Read an image from the file system
	 * 
	 * @param filename
	 * @return a BufferedImagePicture implementation
	 * @throws IOException if the read fails
	 */
	public static BufferedImagePicture readFromFile(String filename) throws IOException {
		File file = new File(filename);
		BufferedImage image = ImageIO.read(file);
		return new BufferedImagePicture(image);
	}

}

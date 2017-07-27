package com.tecacet.imaging.seam;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * A view of the transpose of an image. 
 * It does not make a copy but manipulates the underlying data structure
 * 
 * @author dimitri
 *
 */
public class TransposePicture implements Picture {

	private final Picture originalPicture;
	
	public TransposePicture(Picture originalPicture) {
		super();
		this.originalPicture = originalPicture;
	}

	@Override
	public int getHeight() {
		return originalPicture.getWidth();
	}

	@Override
	public int getWidth() {
		return originalPicture.getHeight();
	}

	@Override
	public Color get(int x, int y) {
		return originalPicture.get(y, x);
	}

	@Override
	public void set(int x, int y, Color color) {
		originalPicture.set(y, x, color);
	}

	@Override
	public BufferedImage getImage() {
		throw new UnsupportedOperationException();
	}

}

package com.tecacet.imaging.seam;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * View of an image as a 2D array of Color values
 * 
 * @author dimitri
 *
 */
public interface Picture {

	/**
	 * Returns the height of the picture (in pixels).
	 */
	int getHeight();

	/**
	 * Returns the width of the picture (in pixels).
	 */
	int getWidth();

	/**
	 * Returns the color of pixel (<em>x</em>, <em>y</em>).
	 */
	Color get(int x, int y);

	/**
	 * Sets the color of pixel (<em>x</em>, <em>y</em>) to given color.
	 */
	void set(int x, int y, Color color);

	/**
	 * Get a buffered image compatible with this representation
	 * 
	 * @return a Buffered Image view of this image
	 */
	BufferedImage getImage();

}
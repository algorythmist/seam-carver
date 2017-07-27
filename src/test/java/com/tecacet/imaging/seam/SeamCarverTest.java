package com.tecacet.imaging.seam;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Stack;

import org.junit.Test;

public class SeamCarverTest {

	@Test
	public void test() throws IOException {
		Picture picture = BufferedImagePicture.readFromFile("apple.jpg");
		assertEquals(263, picture.getHeight());
		assertEquals(350, picture.getWidth());
		
		SeamCarver seamCarver = new SeamCarver();
		Picture resized = seamCarver.resize(picture, 50, 13);
		assertEquals(250, resized.getHeight());
		assertEquals(300, resized.getWidth());
		
		Stack<int[]> verticalSeams = seamCarver.getVerticalSeams();
		assertEquals(50, verticalSeams.size());
		int[] seam = verticalSeams.peek();
		assertEquals(263, seam.length);
		Stack<int[]> horizontalSeams = seamCarver.getHorizontalSeams();
		assertEquals(13, horizontalSeams.size());
		seam = horizontalSeams.peek();
		assertEquals(300, seam.length);
		
	}

}

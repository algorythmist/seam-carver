package com.tecacet.imaging.seam;

import static org.junit.Assert.assertEquals;

import java.awt.image.BufferedImage;
import java.io.IOException;

import org.junit.Test;

public class EnergyCalculatorTest {

	@Test
	public void testEnergy() throws IOException {
		// read a 10x10 image
		Picture picture = BufferedImagePicture.readFromFile("exclamation.jpg");
		assertEquals(10, picture.getHeight());
		assertEquals(10, picture.getWidth());

		EnergyCalculator energyCalculator = new EnergyCalculator();
		double[][] energy = energyCalculator.computeEnergy(picture);
		assertEquals(10, energy.length);
		assertEquals(10, energy[0].length);
		// verify that boundaries have boundary value
		for (int i = 0; i < 10; i++) {
			assertEquals(195075, energy[0][i], 0.001);
			assertEquals(195075, energy[9][i], 0.001);
			assertEquals(195075, energy[i][0], 0.001);
			assertEquals(195075, energy[i][9], 0.001);
		}
		
		BufferedImage energyPicture = EnergyCalculator.toBufferedImage(energy);
		assertEquals(10, energyPicture.getHeight());
		assertEquals(10, energyPicture.getWidth());
	}

}

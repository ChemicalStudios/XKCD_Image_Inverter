package net.chemicalstudios.xkcdinvert;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class XKCDInvert {

	private static Color newColor;

	public static int getRGB(int oldRGB) {
		newColor = new Color(oldRGB);
		int r = 255 - (newColor.getRed());
		int g = 255 - (newColor.getGreen());
		int b = 255 - (newColor.getBlue());
		newColor = new Color(r, g, b);
		newColor = newColor.brighter(); // Without this the comic is really
										// blurry and dark

		return newColor.getRGB();
	}

	public static void main(String[] args) {
		// Take input
		File inputFile = new File("input.png");

		BufferedImage input = null;
		try {
			input = ImageIO.read(inputFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		int width = input.getWidth();
		int height = input.getHeight();

		BufferedImage outputImage = input;

		// Change all pixels to opposite value
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				outputImage.setRGB(x, y, getRGB(input.getRGB(x, y)));
			}
		}

		File output = new File("output.png");

		// Save new, changed image
		try {
			ImageIO.write(input, "PNG", output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

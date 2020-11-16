package decorator;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;

public class Hue extends ImageDecorator {

	public Hue(BufferedImage image) {
		super(image);
	}

	public BufferedImage change(float incVal) {
		int imgWidth = image.getWidth();
		int imgHeight = image.getHeight();
		BufferedImage result = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB);
		Raster raster = image.getData();
		WritableRaster wraster = raster.createCompatibleWritableRaster();

		for (int y = 0; y < imgHeight; ++y) {
			for (int x = 0; x < imgWidth; ++x) {
				float[] hsb = getHSBFromRGB(raster, x, y);
				hsb[0] += incVal;
				if (hsb[0] < 0f)
					hsb[0] = 0f;
				if (hsb[0] > 1f)
					hsb[0] = 1f;
				int[] rgb = getRGBFromHSB(hsb);
				wraster.setPixel(x, y, rgb);
			}
		}
		result.setData(wraster);
		return result;
	}

	@Override
	public BufferedImage change() {
		// TODO Auto-generated method stub
		return null;
	}
}

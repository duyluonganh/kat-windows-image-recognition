package com.katalon.keywords.windows

import java.awt.Rectangle
import java.awt.image.BufferedImage;

import org.sikuli.api.DefaultScreenLocation
import org.sikuli.api.DesktopScreenRegion;
import org.sikuli.api.ImageTarget;
import org.sikuli.api.ScreenLocation
import org.sikuli.api.ScreenRegion;
import org.sikuli.api.Target;
import org.sikuli.api.robot.Keyboard;
import org.sikuli.api.robot.Mouse;
import org.sikuli.api.robot.desktop.DesktopKeyboard;
import org.sikuli.api.robot.desktop.DesktopMouse;

import com.kms.katalon.core.webui.constants.StringConstants;

public class DesktopScreenUtil {

	private ScreenRegion mainScreen;

	private double similarity = 0.75; // Default value

	public DesktopScreenUtil() {
		mainScreen = new DesktopScreenRegion();
	}

	public DesktopScreenUtil(double similarity) {
		this();
		this.similarity = similarity;
	}

	public void clickImage(String imagePath) throws Exception {
		ScreenRegion reg = findImage(imagePath);
		if (reg == null) {
			throw new Exception(StringConstants.COMM_EXC_CANNOT_RECOGNIZE_IMG_ON_SCREEN);
		}
		Mouse mouse = new DesktopMouse();
		mouse.click(reg.getCenter());
	}

	public void clickImageInImage(String parentImagePath, String childImagePath) throws Exception {
		ScreenRegion regParent = findImage(parentImagePath);
		if (regParent == null) {
			throw new Exception("Image: " + parentImagePath + " not found");
		}
		ScreenRegion regChild = findImage(childImagePath, regParent);
		if (regChild == null) {
			throw new Exception("Image: " + childImagePath + " not found");
		}
		Mouse mouse = new DesktopMouse();
		mouse.click(regChild.getCenter());
	}

	public void clickImageWithCoorinates(String imagePath, int x, int y) throws Exception {
		ScreenRegion reg = findImage(imagePath);
		if (reg == null) {
			throw new Exception(StringConstants.COMM_EXC_CANNOT_RECOGNIZE_IMG_ON_SCREEN);
		}
		Mouse mouse = new DesktopMouse();
		Rectangle rect = reg.getBounds()
		ScreenLocation location = new DefaultScreenLocation(reg.getScreen(), rect.getX().intValue(), rect.getY().intValue())
		mouse.click(location);
	}

	public boolean isImageExist(String imagePath) throws Exception {
		ScreenRegion reg = findImage(imagePath);
		return reg != null;
	}

	public void type(String string) {
		Keyboard keyboard = new DesktopKeyboard();
		keyboard.type(string);
	}

	public void typeOnImage(String imagePath, String text) throws Exception {
		clickImage(imagePath);
		type(text);
	}

	public boolean waitForImagePresent(String imagePath, int seconds) throws Exception {
		File imgFile = new File(imagePath);
		if (imgFile.exists()) {
			Target imageTarget = new ImageTarget(imgFile);
			imageTarget.setMinScore(this.similarity);
			ScreenRegion reg = mainScreen.wait(imageTarget, seconds * 1000);
			return reg != null;
		} else {
			throw new Exception(StringConstants.COMM_EXC_IMG_FILE_DOES_NOT_EXIST);
		}
	}

	public double getSimilarity() {
		return similarity;
	}

	public void setSimilarity(double similarity) {
		this.similarity = similarity;
	}

	private ScreenRegion findImage(String imagePath) throws Exception {
		File imgFile = new File(imagePath);
		if (imgFile.exists()) {
			Target target = new ImageTarget(imgFile);
			target.setMinScore(this.similarity);
			ScreenRegion reg = this.mainScreen.find(target);
			return reg;
		} else {
			throw new Exception(StringConstants.COMM_EXC_IMG_FILE_DOES_NOT_EXIST);
		}
	}

	private ScreenRegion findImage(String imagePath, ScreenRegion screen) throws Exception {
		File imgFile = new File(imagePath);
		if (imgFile.exists()) {
			Target target = new ImageTarget(imgFile);
			target.setMinScore(this.similarity);
			ScreenRegion reg = screen.find(target)
			return reg;
		} else {
			throw new Exception(StringConstants.COMM_EXC_IMG_FILE_DOES_NOT_EXIST);
		}
	}

	/**
	 * Call {@link DesktopScreenRegion#capture()} to
	 * capture the screenshot of the current screen
	 * 
	 * @return
	 */
	public BufferedImage getScreenRegionImage() {
		return mainScreen.capture();
	}

	/**
	 * Get all {@link ScreenRegion} on the screen matching the image specified
	 * in imagePath. The returned array is sorted by matched score in descending
	 * order
	 * 
	 * @param imagePath
	 * Path to target image
	 * @return A list of matched {@link ScreenRegion}
	 * @throws Exception
	 */
	public List<ScreenRegion> findImages(String imagePath) throws Exception {
		File imgFile = new File(imagePath);
		if (imgFile.exists()) {
			Target target = new ImageTarget(imgFile);
			target.setMinScore(this.similarity);
			List<ScreenRegion> regions = this.mainScreen.findAll(target);
			regions.sort(new Comparator<ScreenRegion>() {
						@Override
						public int compare(ScreenRegion o1, ScreenRegion o2) {
							double reg1Score = o1.getScore();
							double reg2Score = o2.getScore();
							if (reg1Score > reg2Score)
								return -1;
							if (reg1Score < reg2Score)
								return 1;
							return 0;
						}
					});
			return regions;
		} else {
			throw new Exception(StringConstants.COMM_EXC_IMG_FILE_DOES_NOT_EXIST);
		}
	}
}

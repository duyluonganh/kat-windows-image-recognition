package com.katalon.keywords.windows
import com.kms.katalon.core.annotation.Keyword


class WindowsImageKeywords {
	/**
	 * Finds the desktop region that matches with the image then click on the center of the found region
	 * @param imagePath Absolute path to image
	 */
	@Keyword
	def static clickImage(String imagePath) {
		DesktopScreenUtil screen = new DesktopScreenUtil()
		screen.clickImage(imagePath)
	}

	/**
	 * Finds the desktop region that matches with the image then click on the given relative coordinate of the found region
	 * @param imagePath Absolute path to image
	 */
	@Keyword
	def static clickImageWithCoordinates(String imagePath, int x, int y) {
		DesktopScreenUtil screen = new DesktopScreenUtil()
		screen.clickImageWithCoorinates(imagePath, x, y)
	}

	/**
	 * Finds the desktop region that matches with the given parentImage then continue finding a small region inside the parent region,
	 * then click on center of the found region.
	 * @param parentImagePath Absolute path to parent image
	 * @param childImagePath Absolute path to child image
	 */
	@Keyword
	def static clickImageInImage(String parentImagePath, String childImagePath) {
		DesktopScreenUtil screen = new DesktopScreenUtil()
		screen.clickImageInImage(parentImagePath, childImagePath)
	}

	/**
	 * Finds the desktop region that matches with the image then type on the center of the found region
	 * @param imagePath Absolute path to image
	 * @param text text to set
	 */
	@Keyword
	def static typeOnImage(String imagePath, String text) {
		DesktopScreenUtil screen = new DesktopScreenUtil()
		screen.typeOnImage(imagePath, text)
	}
}
# kat-windows-image-recognition
Demonstrate how to use Image Recognition in Katalon Windows Test on Microsoft Calculators app.

Please view the video in Demo/capture-image-elements-guide.mp4 to understand the way to capture image elements.

### Custom Keywords

**WindowsImageKeywords.clickImage(imagePath)** - Finds the desktop region that matches with the image then click on the center of the found region

**WindowsImageKeywords.clickImageWithCoordinates(imagePath, x, y)** - Finds the desktop region that matches with the image then click on the given relative coordinate of the found region

**WindowsImageKeywords.clickImageInImage(parentImage, childImage)** - Finds the desktop region that matches with the given parentImage then continue finding a small region inside the parent region, then click on center of the found region.

### Sample of usage

Open 'New Test Suite' test suite then run the test

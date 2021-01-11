import com.katalon.keywords.windows.WindowsImageKeywords
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

Windows.startApplicationWithTitle('Microsoft.WindowsCalculator_8wekyb3d8bbwe!App', '')

String parentImagePath = new File(RunConfiguration.getProjectDir(), "Images/Parent.PNG").getAbsolutePath()
String childImagePath = new File(RunConfiguration.getProjectDir(), "Images/Child.PNG").getAbsolutePath()

WindowsImageKeywords.clickImageInImage(parentImagePath, childImagePath)
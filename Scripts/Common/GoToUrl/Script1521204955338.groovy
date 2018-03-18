import com.kms.katalon.core.exception.StepFailedException as StepFailedException
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

try {
    WebUI.getUrl()
}
catch (StepFailedException e) {
    // browser not open	
    WebUI.openBrowser(GlobalVariable.loginUrl)

    WebUI.maximizeWindow()

    WebUI.setText(findTestObject('Login/input_txtUsername'), GlobalVariable.loginUsername)

    WebUI.setText(findTestObject('Login/input_txtPassword'), GlobalVariable.loginPassword)

    WebUI.click(findTestObject('Login/input_Submit'))
} 

if (WebUI.getUrl() != url) {
    WebUI.navigateToUrl(url)
}


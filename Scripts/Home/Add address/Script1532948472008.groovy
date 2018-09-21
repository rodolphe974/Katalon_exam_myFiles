import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as MobileBuiltInKeywords
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW

boolean alias_exists = false

WebUI.callTestCase(findTestCase('Common/GoToUrl'), [('url') : GlobalVariable.homeUrl + '?controller=addresses'], FailureHandling.OPTIONAL)

'Chekc if a frame with the same alias exist on the "Adresses page". If so, a delete button normaly also exists. And in this case, we will expect an error message after clicking the add address button'
deleteButton = WebUI.modifyObjectProperty(findTestObject('Object Repository/Page_Addresses - My Store/span_Delete'), 'xpath', 
    'equals', ('//a[@title=\'Delete\' and ../../li[1]/h3/text()=\'' + myAlias) + '\']', true)

if (WebUI.waitForElementPresent(deleteButton, 2)) {
    not_run: return deleteButton
    
    alias_exists = true
} else {
}

WebUI.click(findTestObject('Page_Addresses - My Store/span_Add a new address'))

WebUI.setText(findTestObject('Page_Address - My Store/input__address1'), myAddress)

WebUI.setText(findTestObject('Page_Address - My Store/input__city'), myCity)

WebUI.selectOptionByValue(findTestObject('Page_Address - My Store/select_state'), myState, true)

WebUI.setText(findTestObject('Page_Address - My Store/input__postcode'), myZip)

WebUI.setText(findTestObject('Page_Address - My Store/input__phone_mobile'), myMobile)

WebUI.setText(findTestObject('Page_Address - My Store/input__alias'), myAlias)

WebUI.click(findTestObject('Page_Address - My Store/span_Save'))

if (alias_exists) {
    WebUI.waitForElementVisible(findTestObject('Page_Address - My Store/div_alias_error'), 0)
} else {
}

WebUI.closeBrowser()


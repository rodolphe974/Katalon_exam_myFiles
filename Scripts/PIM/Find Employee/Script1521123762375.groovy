import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.exception.StepFailedException as StepFailedException
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
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.logging.KeywordLogger as KeywordLogger
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory

assert employeeName.trim()

WebUI.callTestCase(findTestCase('Common/GoToUrl'), [('url') : GlobalVariable.urlViewEmployeeList])

WebUI.waitForElementClickable(findTestObject('PIM/Employee List/input_btnReset'), 5)

WebUI.click(findTestObject('PIM/Employee List/input_btnReset'))

WebUI.waitForElementVisible(findTestObject('PIM/Employee List/input_empsearchemployee_nameem'), 5)

// WebUI.click(findTestObject('PIM/Employee List/input_empsearchemployee_nameem'))

WebUI.sendKeys(findTestObject('PIM/Employee List/input_empsearchemployee_nameem'), employeeName + Keys.ENTER)

WebUI.waitForElementVisible(findTestObject('PIM/Employee List/input_empsearchemployee_nameem'), 5)

assert false

WebUI.waitForElementClickable(findTestObject('PIM/Employee List/input_btnSearch'), 5)

WebUI.click(findTestObject('PIM/Employee List/input_btnSearch'))

WebUI.waitForElementClickable(findTestObject('PIM/Employee List/input_chkSelectAll'), 5)

assert false

WebDriver driver = DriverFactory.getWebDriver()

WebElement table = driver.findElement(By.xpath('id("resultTable")/tbody[1]'))

List<WebElement> rows = table.findElements(By.tagName('tr'))

String firstCell = rows.get(0).findElements(By.tagName('td')).get(0).getText()

println('firstCell: ' + firstCell + '; # rows: ' + rows.size())

return firstCell == 'No Records Found' ? 0 : rows.size()


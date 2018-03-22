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

import org.junit.After
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import org.stringtemplate.v4.compiler.STParser.exprTag_return as exprTag_return
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory

if (GlobalVariable.debug) {
	println('Find Employee("' + employeeName + '")')
}

// ensure that employeeName is not empty (which would select all employees)
assert employeeName.trim()

WebUI.callTestCase(findTestCase('Common/GoToUrl'), [('url') : GlobalVariable.viewEmployeeListUrl])

WebUI.waitForElementVisible(findTestObject('PIM/EmployeeList/input_empsearchemployee_nameem'), GlobalVariable.timeOut)

WebUI.click(findTestObject('PIM/EmployeeList/input_empsearchemployee_nameem'))

WebUI.sendKeys(findTestObject('PIM/EmployeeList/input_empsearchemployee_nameem'), employeeName)

WebUI.click(findTestObject('PIM/EmployeeList/input_empsearchid'))

WebUI.waitForElementClickable(findTestObject('PIM/EmployeeList/input_btnSearch'), GlobalVariable.timeOut)

WebUI.click(findTestObject('PIM/EmployeeList/input_btnSearch'))

WebDriver driver = DriverFactory.getWebDriver()

// Get all rows where the third and fourth table cell match the employee name.
//
// NOTE: the first and last names are part of an anchor

int found = 0

String xpathEmployeeName = 'concat(td[3]/a/text(), " ",td[4]/a/text())'

String xpathExpr = ''

if (matchExact) {
	xpathExpr = 'id("resultTable")/tbody/tr[' + xpathEmployeeName + '="' + employeeName + '"]'
} else {
    xpathExpr = 'id("resultTable")/tbody/tr[contains(' + xpathEmployeeName + ', "' + employeeName + '")]'
}

if (GlobalVariable.debug) {
	println('xpathExpr: ' + xpathExpr)
}

try {
	List<WebElement> rows = driver.findElements(By.xpath(xpathExpr))

	for (int row = 0; row < rows.size(); row++) {
		List<WebElement> columns = rows.get(row).findElements(By.tagName('td'))

		// <input type="checkbox" id="ohrmList_chkSelectRecord_15" name="chkSelectRow[]" value="15" />
		columns.get(0).findElement(By.tagName('input')).click()
		
		found = found + 1

		// some logging
		if (GlobalVariable.debug) {
			Integer employeeId = columns.get(1).findElement(By.tagName('a')).getText() as Integer
			String employeeFirstName = columns.get(2).findElement(By.tagName('a')).getText()
			String employeeLastName = columns.get(3).findElement(By.tagName('a')).getText()

			println('row #: ' + row + '; employeeId: ' + employeeId + '; employeeFirstName: ' + employeeFirstName + '; employeeLastName: ' + employeeLastName)
		}	
	}

	return found
} catch(Exception e) {
    if (GlobalVariable.debug) {
		println(e.getMessage())
    }
	return 0
}

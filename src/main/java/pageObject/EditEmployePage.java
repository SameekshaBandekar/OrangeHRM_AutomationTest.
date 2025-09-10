package pageObject;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utils.WaitUtils;

public class EditEmployePage {
	private WebDriver driver;
	private WaitUtils wait;

	public EditEmployePage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WaitUtils(driver, 20);
	}

	@FindBy(name = "firstName")
	private WebElement firstName;

	@FindBy(xpath = "//label[contains(.,'Male')]//span[@class='oxd-radio-input']")
	private WebElement genderRadioBtn;

	@FindBy(xpath = "//div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']//button[@type='submit'][normalize-space()='Save']")
	private WebElement saveBtn;

	@FindBy(xpath = "//p[@class='oxd-text oxd-text--p oxd-text--toast-message oxd-toast-content-text']")
	private WebElement toastMessage;

	@FindBy(css = (".oxd-text--toast-message"))
	private WebElement noRecordFoundMsg;

	// Renaming employee name
	public void editEmployeeName(String newName) {
		wait.waitForVisibility(firstName);
		firstName.click();
		firstName.sendKeys(Keys.CONTROL + "a");
		firstName.sendKeys(Keys.DELETE);
		firstName.sendKeys(newName);
		wait.waitForVisibility(saveBtn);
		saveBtn.click();
	}

	public String getUpdatedName() {
		wait.waitForVisibility(firstName);
		return firstName.getAttribute("value");
	}

	public String getToastMessage() {
		wait.waitForVisibility(toastMessage);
		return toastMessage.getText();
	}

	public String getNoRecordFound() {
		wait.waitForVisibility(noRecordFoundMsg);
		return noRecordFoundMsg.getText();
	}

}

package pageObject;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utils.WaitUtils;

public class LeavePage {
	private WebDriver driver;
	private WaitUtils wait;

	public LeavePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WaitUtils(driver, 5);

	}

	@FindBy(xpath = "//a[contains(text(),'Apply')]")
	private WebElement applyTab;

	@FindBy(xpath = "//i[@class='oxd-icon bi-caret-down-fill oxd-select-text--arrow']")
	private WebElement leaveTypeDropDown;

	By leaveTypeOptions = By.xpath("//div[@class='oxd-select-dropdown --positon-bottom']//div[@class='oxd-select-option']//span");

	@FindBy(xpath = "//input[@placeholder='yyyy-mm-dd'][1]")
	private WebElement fromDate;

	@FindBy(xpath = "//input[@placeholder='yyyy-mm-dd'][2]")
	private WebElement toDate;

	@FindBy(xpath = "//textarea")
	private WebElement commentBox;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement saveBtn;

	@FindBy(xpath="//p[@class='oxd-text oxd-text--p oxd-text--subtitle-2']")
	private WebElement NoLeaveBalanceMessage;

	public void selectLeaveOption() {
		wait.waitForVisibility(applyTab);
		applyTab.click();
	}

	public String getNoLeaveBalanceMessage()
	{
		wait.waitForVisibility(NoLeaveBalanceMessage);
		return NoLeaveBalanceMessage.getText();
	}

	public void applyLeave(String optionText, String from, String to, String comment) throws InterruptedException {

		wait.waitForVisibility(leaveTypeOptions);
		leaveTypeDropDown.click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(leaveTypeOptions));

		for (WebElement option : options) {
			if (option.getText().trim().equalsIgnoreCase(optionText)) {
				option.click();
				break;
			}
		}

		fromDate.clear();
		fromDate.sendKeys(from);

		toDate.clear();
		toDate.sendKeys(to);

		commentBox.sendKeys(comment);

		saveBtn.click();
	}
}

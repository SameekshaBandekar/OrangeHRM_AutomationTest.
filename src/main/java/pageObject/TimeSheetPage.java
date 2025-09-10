package pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utils.WaitUtils;

public class TimeSheetPage extends DashBoardPage {
	private WebDriver driver;
	private WaitUtils wait;

	public TimeSheetPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WaitUtils(driver, 15);

	}

	@FindBy(xpath = "//h6[@class='oxd-text oxd-text--h6 oxd-topbar-header-breadcrumb-level']")
	private WebElement headerTitle;

	@FindBy(xpath = "//button[normalize-space()='Edit']")
	private WebElement editButton;

	@FindBy(xpath = "//i[@class='oxd-icon bi-plus']")
	private WebElement rowPlusIcon;

	@FindBy(xpath = "//input[@placeholder='Type for hints...']")
	private WebElement projectsearchBox;

	By projectOptions = By.xpath("//div[@role='listbox']//div[@role='option']//span");

	@FindBy(xpath = "//i[@class='oxd-icon bi-caret-down-fill oxd-select-text--arrow']")
	private WebElement activityArrow;

	By activityOptions = By
			.xpath("//div[@class='oxd-select-dropdown --positon-bottom']//div[@class='oxd-select-option']//span");

	@FindBy(css = "button[type='submit']")
	private WebElement saveBtn;

	By timesheetRows = By.xpath("//tr[@class='orangehrm-timesheet-table-body-row']");

	@FindBy(css = ".oxd-text--toast-message")
	private WebElement toastMessage;

	public String waitForPageToLoad() {
		wait.waitForVisibility(headerTitle);
		return headerTitle.getText();
	}

	public void editTimeSheet() {
		wait.waitForElementClickable(editButton);
		editButton.click();

	}

	public void selectProject(String initialLetters, String projectName) {
		wait.waitForVisibility(projectsearchBox);

		projectsearchBox.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		projectsearchBox.sendKeys(Keys.DELETE);
		projectsearchBox.sendKeys(Keys.TAB);
		wait.waitForVisibility(projectsearchBox);
		projectsearchBox.sendKeys(initialLetters);

		List<WebElement> options = wait.waitOfAllElementsLocated(projectOptions);

		for (WebElement option : options) {
			String text = option.getText().trim();
			if (text.equalsIgnoreCase(projectName)) {
				wait.waitForElementClickable(option);
				option.click();
				break;
			}
		}
	}

	public void activitySelection(String activityName) {

		WebElement dropDown = wait.waitForElementClickable(activityArrow);
		dropDown.click();

		List<WebElement> options = wait.waitOfAllElementsLocated(activityOptions);
		for (WebElement option : options) {
			if (option.getText().equalsIgnoreCase(activityName)) {
				option.click();
				break;
			}
		}
	}

	public void clearTimeLog() {
		List<WebElement> hoursInputs = driver.findElements(By.cssSelector("input.oxd-input.oxd-input--active"));

		for (WebElement inputBox : hoursInputs) {
			WebElement box = wait.waitForVisibility(inputBox);
			box.sendKeys(Keys.chord(Keys.CONTROL, "a")); // select all
			box.sendKeys(Keys.DELETE); // delete
		}
	}

	public void enterHoursForWeek(String hours) {
		clearTimeLog();
		List<WebElement> hoursInputs = driver.findElements(By.cssSelector("input.oxd-input.oxd-input--active"));

		// Loop through first 5 inputs (Mon–Fri)
		for (int i = 0; i < 5; i++) {
			WebElement inputBox = hoursInputs.get(i);
			wait.waitForVisibility(inputBox).clear();
			inputBox.sendKeys(hours);
		}
	}

	public void saveTimeSheet() {
		saveBtn.click();
	}

	public boolean isTimesheetEntryPresent(String projectName, String activityName, String hours) {
		wait.waitForVisibility(timesheetRows);
		List<WebElement> rows = driver.findElements(timesheetRows);

		if (rows.isEmpty()) {
			System.out.println("⚠ No rows found in the timesheet table.");
			return false;
		}

		for (WebElement row : rows) {
			String rowText = row.getText();
			if (rowText.contains(projectName) && rowText.contains(activityName) && rowText.contains(hours)) {
				return true;
			}
		}
		return false;
	}

	public String getToastMessage() throws InterruptedException {
		wait.waitForVisibility(toastMessage);
		// Thread.sleep(3000);
		return toastMessage.getText();
	}

}

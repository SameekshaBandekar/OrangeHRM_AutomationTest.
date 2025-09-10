package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Utils.WaitUtils;

public class DashBoardPage {

	private WebDriver driver;
	public PIMPage pim;
	private WaitUtils wait;
	public LeavePage leave;

	public DashBoardPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WaitUtils(driver, 20);

	}

	@FindBy(css = ".oxd-text--h6")
	private WebElement headerTitle;

	@FindBy(css = "a.oxd-main-menu-item[href='/web/index.php/pim/viewPimModule']")
	private WebElement pimMenu;

	@FindBy(css = "button[title='My Timesheet'] svg")
	private WebElement myTimeSheetIcon;

	@FindBy(css = "a.oxd-main-menu-item[href='/web/index.php/leave/viewLeaveModule']")
	private WebElement leaveMenu;

	public String getDashboardTitle() {
		wait.waitForVisibility(headerTitle);
		return headerTitle.getText();
	}

	public PIMPage goToPIM() {
		pimMenu.click();
		PIMPage pim = new PIMPage(driver);
		return pim;
	}

	public TimeSheetPage goToMyTimeSheet() {
		wait.waitForVisibility(myTimeSheetIcon);
		myTimeSheetIcon.click();
		TimeSheetPage timeSheet = new TimeSheetPage(driver);
		timeSheet.waitForPageToLoad();
		return timeSheet;
	}

	public LeavePage goToLeaveMenu() {
		wait.waitForVisibility(leaveMenu);
		leaveMenu.click();

		LeavePage leave = new LeavePage(driver);
		return leave;
	}
}

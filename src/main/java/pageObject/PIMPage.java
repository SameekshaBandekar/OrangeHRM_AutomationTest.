package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utils.WaitUtils;

public class PIMPage {
	private WebDriver driver;
	public EditEmployePage editPage;
	private WaitUtils wait;

	public PIMPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WaitUtils(driver, 5);
	}

	@FindBy(xpath = "//label[text()='Employee Name']/following::input[1]")
	private WebElement empNameSearch;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement submitBtn;

	@FindBy(css = "button.oxd-icon-button.oxd-table-cell-action-space")
	private WebElement editClick;

//	@FindBy(css = "div.oxd-table-row--with-border")
//	private WebElement eDetails;

	@FindBy(xpath="//div[@class=\"oxd-table-cell oxd-padding-cell\"][3]")
	private WebElement eFirstName;
	
//Search Employee
	public EditEmployePage searchEmployee(String firstName) {
		wait.waitForVisibility(empNameSearch);
		empNameSearch.clear();
		empNameSearch.sendKeys(firstName);
		wait.waitForVisibility(submitBtn);
		submitBtn.click();
		return new EditEmployePage(driver);
		
	}

//Get employee details
	public String getEmployeeDetails() {
		wait.waitForVisibility(eFirstName);
		return eFirstName.getText();
		
	}

//Edit employee details
	public EditEmployePage clickEdit() {
		wait.waitForVisibility(editClick);
		Actions actions = new Actions(driver);
		actions.moveToElement(editClick).click().perform();
		editPage = new EditEmployePage(driver);
		return editPage;
	}

}

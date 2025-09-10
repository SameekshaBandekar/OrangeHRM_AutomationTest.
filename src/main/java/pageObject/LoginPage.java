package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Utils.WaitUtils;

public class LoginPage {
	private WebDriver driver;
	public DashBoardPage dashBoard;
	private WaitUtils wait;

	public LoginPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WaitUtils(driver, 5);
	}

	@FindBy(css = "input[name='username']")
	private WebElement username;

	@FindBy(name = "password")
	private WebElement password;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement submitBtn;

	@FindBy(xpath = "//p[contains(@class, 'oxd-alert-content-text') and text()='Invalid credentials']")
	private WebElement errorMessage;

	public DashBoardPage signUp(String user, String pass) {

		wait.waitForVisibility(username);
		username.sendKeys(user);
		password.sendKeys(pass);
		submitBtn.click();
		dashBoard = new DashBoardPage(driver);
		return dashBoard;
	}

	@FindBy(xpath = "//p[@class='oxd-text oxd-text--p orangehrm-login-forgot-header']")
	private WebElement forgotPswrd;

	public void forgotPassword() {
		forgotPswrd.click();
	}

	public String getErrorMessage() {
		wait.waitForVisibility(errorMessage);
		return errorMessage.getText();

	}
}

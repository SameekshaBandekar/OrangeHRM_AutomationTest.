package TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObject.LoginPage;

public class BaseTest {

	public WebDriver driver;
	public Properties prop;
	public LoginPage login;

	public WebDriver initializeBrowser() throws IOException {
		prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\Resources\\GlobalData.properties");
		prop.load(fis);
		String browserName = prop.getProperty("browser");
		if (browserName.equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();
			 options.addArguments("--headless=new"); // Headless for Jenkins
			 options.addArguments("--no-sandbox");
			 options.addArguments("--disable-dev-shm-usage");
			options.addArguments("--window-size=1920,1080");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);

		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		} else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		driver.manage().window().setSize(new Dimension(1920, 1080));

		return driver;
	}

	@BeforeMethod(alwaysRun = true)
	public void launchApplication() throws IOException {
		driver = initializeBrowser();
		String url = prop.getProperty("baseUrl");
		driver.get(url);
		login = new LoginPage(driver);
		// return login;
	}

	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
		FileUtils.copyFile(source, new File(path));
		return path;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}
}

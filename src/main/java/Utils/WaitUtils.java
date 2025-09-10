package Utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {
	private WebDriver driver;
    private WebDriverWait wait;

	public WaitUtils(WebDriver driver, int timeoutSeconds) {
		this.driver=driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
	}
	
	
	
	// Wait for WebElement
    public WebElement waitForVisibility(WebElement element) {
       
       return wait.until(ExpectedConditions.visibilityOf(element));
    }

    // Wait for By locator
    public WebElement waitForVisibility(By locator) {
        
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
   // wait for all the element to be located
    public List<WebElement> waitOfAllElementsLocated(By options)
    {
    	
    	return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(options));
    }
    
    public WebElement waitForElementClickable(WebElement element)
    {
    	return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    
    
}

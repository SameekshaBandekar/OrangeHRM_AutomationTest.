package TestComponents;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Resources.ExtentReporter;

public class Listeners extends BaseTest implements ITestListener {

	private static ExtentReports extent = ExtentReporter.getReportObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestStart(result);
		ExtentTest test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
		
	}

	

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSuccess(result);
		  extentTest.get().log(Status.PASS, "Test Passed");
	        captureAndAttachScreenshot(result);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		extentTest.get().fail(result.getThrowable());
        captureAndAttachScreenshot(result);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
		extentTest.get().log(Status.SKIP, "Test Skipped");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
		onTestFailure(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onFinish(context);
		extent.flush();
	}
	
	 // --- Helper Methods ---
    private WebDriver getDriver(ITestResult result) {
        try {
            Object testClass = result.getInstance();
            return ((BaseTest) testClass).driver;
        } catch (Exception e) {
            extentTest.get().log(Status.WARNING, "Unable to fetch WebDriver instance.");
            return null;
        }
    }

    private void captureAndAttachScreenshot(ITestResult result) {
        WebDriver driver = getDriver(result);
        if (driver != null) {
            try {
                String filePath = getScreenshot(result.getMethod().getMethodName(), driver);
                extentTest.get().addScreenCaptureFromPath(filePath);
            } catch (IOException e) {
                e.printStackTrace();
                extentTest.get().log(Status.WARNING, "Screenshot capture failed.");
            }
        } else {
            extentTest.get().log(Status.WARNING, "WebDriver was null. Screenshot not taken.");
        }
    }
}



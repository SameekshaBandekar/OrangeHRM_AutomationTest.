package orangeHRMTests;

import org.testng.annotations.Test;

import TestComponents.BaseTest;
import pageObject.DashBoardPage;
import pageObject.LeavePage;
import pageObject.LoginPage;

public class ApplyLeaveForNoLeaveBalance extends BaseTest {
	 @Test(groups = {"negative"})
	    public void applyLeaveTest() throws InterruptedException {
	        // Login
	        
		 DashBoardPage dashboard  = login.signUp("Admin", "admin123");

	        // Dashboard -> Leave
	       
		 LeavePage leavePage =   dashboard.goToLeaveMenu();
		 leavePage.selectLeaveOption();
	    String message1 = leavePage.getNoLeaveBalanceMessage();
		System.out.println(message1);
		Assert.assertEquals(message1, "No Leave Types with Leave Balance", "No Leave Balance");;
	    }
	}


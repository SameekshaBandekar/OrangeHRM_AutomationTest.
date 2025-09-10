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
	        // Apply Leave
	        
	        leavePage.applyLeave("CAN - Vacation", "2025-09-10", "2025-09-12", "Family function leave");
	    }
	}


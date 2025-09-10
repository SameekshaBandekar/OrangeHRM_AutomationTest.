package orangeHRMTests;

import org.testng.Assert;
import org.testng.annotations.Test;

import TestComponents.BaseTest;
import pageObject.DashBoardPage;
import pageObject.PIMPage;
import pageObject.EditEmployePage;

public class InvalidEmployeeSearchTest extends BaseTest {

	@Test(groups = { "negative" })
	public void employeeSearch() {
		String username = "Admin";
		String password = "admin123";
		String empName = "abc";
		// Login
		DashBoardPage dashBoard = login.signUp(username, password);
		// Verify DashBoard
		Assert.assertEquals(dashBoard.getDashboardTitle(), "Dashboard");
		PIMPage pim = dashBoard.goToPIM();
		// Search Employee from PIM
		EditEmployePage editPage = pim.searchEmployee(empName);
		String toastMessage = editPage.getNoRecordFound();
		System.out.println(toastMessage);
		Assert.assertEquals(toastMessage, "No Records Found");

	}
}

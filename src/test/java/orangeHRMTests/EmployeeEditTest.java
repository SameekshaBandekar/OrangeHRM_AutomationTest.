package orangeHRMTests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import TestComponents.BaseTest;
import pageObject.DashBoardPage;
import pageObject.PIMPage;
import pageObject.EditEmployePage;

public class EmployeeEditTest extends BaseTest {

	@DataProvider(name = "employeeData")

	public Object[][] getData() {
		return new Object[][] { 
			{ "Admin", "admin123", "Emily", "Divs", "Female"}}; 
		    
	}

	@Test(dataProvider = "employeeData", groups = {"positive"})
	public void editEmployeeDetails(String username, String password, String firstName, String newName, String gender) {

		// Login
		DashBoardPage dashBoard = login.signUp(username, password);
		
		// Verify DashBoard
		Assert.assertEquals(dashBoard.getDashboardTitle(), "Dashboard", "Login Failed!");
		
		// Navigate to PIM
		PIMPage pim = dashBoard.goToPIM();
		
		// Search Employee from PIM
		pim.searchEmployee(firstName);
		String empDetails = pim.getEmployeeDetails();
        Assert.assertTrue(empDetails.contains(firstName), "Employee not found in PIM!");
        
		// print employee details
		System.out.println(pim.getEmployeeDetails());
		
		// edit employee details
		EditEmployePage edit = pim.clickEdit();
		edit.editEmployeeName(newName);
		System.out.println(newName);
		
		// validate toast message
		String message = edit.getToastMessage();
		System.out.println(message);
		Assert.assertEquals(message, "Successfully Updated","Update toast message mismatch!");
		
		//  Validate updated name
        String updatedName = edit.getUpdatedName();
        System.out.println(updatedName);
        Assert.assertEquals(updatedName, newName, "Employee name update verification failed!");

	}

}

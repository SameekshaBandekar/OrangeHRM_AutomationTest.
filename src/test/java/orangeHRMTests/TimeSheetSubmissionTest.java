package orangeHRMTests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import TestComponents.BaseTest;
import pageObject.DashBoardPage;
import pageObject.TimeSheetPage;

public class TimeSheetSubmissionTest extends BaseTest {

	@Test(dataProvider = "timesheetData", groups = { "positive" })
	public void submitTimesheet(String projectInitial, String projectName, String activityName, String hours)
			throws InterruptedException {
		//Login
		DashBoardPage dashboard = login.signUp("Admin", "admin123");
		
		//Go to Timesheet
		TimeSheetPage timesheet = dashboard.goToMyTimeSheet();
		
		//Edit Timesheet
		timesheet.editTimeSheet();
		timesheet.selectProject(projectInitial, projectName);	
		timesheet.activitySelection(activityName);
		timesheet.enterHoursForWeek(hours);
		timesheet.saveTimeSheet();
		
		//Verify toastmessage after submission
		String toastMsg = timesheet.getToastMessage();
		System.out.println(toastMsg);
		Assert.assertEquals(toastMsg, "Successfully Saved");
		
		//Verify Timesheet entry
		Assert.assertTrue(timesheet.isTimesheetEntryPresent(projectName, activityName, hours),
		        "Timesheet entry not found after saving!");

	}

	@DataProvider(name = "timesheetData")

	public Object[][] timesheetData() {
		return new Object[][] { { "Apa", "Apache Software Foundation - ASF - Phase 1", "Bug Fixes", "8" } };
	}

}

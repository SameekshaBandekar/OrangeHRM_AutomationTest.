package orangeHRMTests;

import org.testng.Assert;
import org.testng.annotations.Test;

import TestComponents.BaseTest;

public class InvalidCredentialsTest extends BaseTest {

	@Test(groups = { "negative" })
	public void invalidCredentials() {
		String username = "Test";
		String password = "admi123";
		login.signUp(username, password);
		String actualError = login.getErrorMessage();
		String expectedError = "Invalid credentials";
		System.out.println(actualError);
		Assert.assertEquals(actualError, expectedError);

	}
}

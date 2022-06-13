package testCases;

import static commons.Configuration.url;

import org.junit.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import commons.TestBase;
import pages.LoginPage;
import pages.OverviewPage;

public class OverviewTest extends TestBase {

	LoginPage lp;
	OverviewPage op;

	@BeforeMethod(alwaysRun = true)
	public void openPage() {
		driver.get(url.asString());
		lp = new LoginPage(driver);
		op = new OverviewPage(driver);
	}

//	Given a user acc with permissin to create a reward and ensure that the user is 
//	not able to visit the rest of the page sections (loyalties, campaigns, merchants, user lists, bulk actions) 
//	and all the other API endpoints shouldn't be accessible.
	@Test
	public void verifyRewardAdminDoesNotHaveAccessToOtherSectionsTest() {

		SoftAssert sa = new SoftAssert();

		sa.assertTrue(lp.doRewardAdminLogin(), "Reward Admin Login failed");
		sa.assertTrue(op.verifyRewardAdminDoesNotHaveAccessToOtherSections(),
				"Reward user has access to other sections");
		sa.assertAll();

	}

//   A non-authorized user should not have access to 
//	 the reward detail/edit page if he tries to access directly from the URL.
	@Test
	public void VerifyNonAuthorizedUserShouldNotHaveAccessToRewardDetailPageTest() {
		boolean flag = op.VerifyNonAuthorizedUserShouldNotHaveAccessToRewardDetailPage();
		Assert.assertTrue("Non authorized user could access the page using url", flag);

	}

	// Ensure that a logged in user has sufficient permission to create a reward.
	@Test
	public void verifyRewardAdminHasSufficientPermissionToCreateRewardTest() {

		SoftAssert sa = new SoftAssert();
		sa.assertTrue(lp.doRewardAdminLogin(), "Reward admin Login failed");
		sa.assertTrue(op.verifyUserHasSufficientPermissionToCreateReward(),
				"Reward admin do not have permission to create reward");
		sa.assertAll();

	}

//	Ensure that the logged in user has sufficient permission 
//	to visit the builk file upload page and has the ability to upload.
	@Test
	public void verifyAdminHasSufficientPermissionToVisitBulkFileUploadPage() {

		SoftAssert sa = new SoftAssert();
		sa.assertTrue(lp.doAdminLogin(), " admin Login failed");
		sa.assertTrue(op.verifyUserHasSufficientPermissionToVisitBulkFileUploadPage(),
				" admin do not have permission to visit bulk file uploade page");
		sa.assertAll();

	}

}

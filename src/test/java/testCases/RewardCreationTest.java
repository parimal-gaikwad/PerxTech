package testCases;

import static commons.Configuration.url;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import commons.TestBase;
import pages.LoginPage;
import pages.OverviewPage;
import pages.RewardCreationPage;
import pages.RewardsPage;

public class RewardCreationTest extends TestBase {

	LoginPage lp;
	OverviewPage op;
	RewardsPage rp;
	RewardCreationPage rcp;

	@BeforeMethod(alwaysRun = true)
	public void openPage() {
		driver.get(url.asString());
		lp = new LoginPage(driver);
		op = new OverviewPage(driver);
		rp = new RewardsPage(driver);
		rcp = new RewardCreationPage(driver);
	}

	
	//A reward validity period should have both start and end dates.
//	@Test
	public void verifyRewardValidityPeriodHaveBothStartAndEndDatesTest() {
		
		SoftAssert sa = new SoftAssert();
		
		sa.assertTrue(lp.doRewardAdminLogin(), "Reward Admin Login failed");
		sa.assertTrue(op.goToRewardsSection(),"User not navigated to Rewards section");
		sa.assertTrue(rp.clickCreateNewButton(),"user not navigated to create reward page");
		sa.assertTrue(rcp.verifyRewardValidityPeriodHaveBothStartAndEndDates());
		sa.assertAll();

	}
	
	
	//A successful submission only happens when the payload contains all mandatory information.
//	@Test
	public void verifySuccessfulSubmissionHappensOnlyWhenPayloadContainsMandInfoTest() {
		
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(lp.doRewardAdminLogin(), "Reward Admin Login failed");
		sa.assertTrue(op.goToRewardsSection(),"User not navigated to Rewards section");
		sa.assertTrue(rp.clickCreateNewButton(),"user not navigated to create reward page");
		sa.assertTrue(rcp.verifySuccessfulSubmissionHappensOnlyWhenPayloadContainsMandInfo());
		sa.assertAll();
	}
	
	
	
//	If the reward is of private type,
//	All fields related to catalogues, labels, brands, tags and categories should disappaer.
//	It should not be tagged to any catalogues, labels, brands, tags nor categories.
	@Test
	public void verifyBrandsTagsCategoriesLabelsFieldDisappersInPrivateTypeRewardTest() {
		
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(lp.doRewardAdminLogin(), "Reward Admin Login failed");
		sa.assertTrue(op.goToRewardsSection(),"User not navigated to Rewards section");
		sa.assertTrue(rp.clickCreateNewButton(),"user not navigated to create reward page");
		sa.assertTrue(rcp.verifyBrandsTagsCategoriesLabelsFieldDisappersInPrivateTypeReward(),"fields did not disappear on private type of reward");
		sa.assertAll();

		
		
	}
	
}

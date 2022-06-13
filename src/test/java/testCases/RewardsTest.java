package testCases;

import static commons.Configuration.rewardModeratorEmail;
import static commons.Configuration.rewardModeratorPassword;
import static commons.Configuration.url;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import commons.TestBase;
import pages.LoginPage;
import pages.OverviewPage;
import pages.RewardCreationPage;
import pages.RewardsPage;

public class RewardsTest extends TestBase {

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

	//Clicking "+ Create New" button should lead to reward creation page
	@Test
	public void verifyClickingCreateNewButtonLeadsToRewardCreationPageTest() {

		SoftAssert sa = new SoftAssert();

		sa.assertTrue(lp.doRewardAdminLogin(), "Reward Admin Login failed");
		sa.assertTrue(op.goToRewardsSection(), "User not naivated to Rewards section");
		sa.assertTrue(rp.clickCreateNewButton(), "click create new Button test failed");

		sa.assertAll();
	}

	
	//It should show up in the rewards listing.
	@Test
	public void newlyCreatedRewardPresentInListTest() {

		SoftAssert sa = new SoftAssert();

		sa.assertTrue(lp.doRewardAdminLogin(), "Reward Admin Login failed");
		sa.assertTrue(op.goToRewardsSection(), "User not naivated to Rewards section");
		sa.assertTrue(rp.clickCreateNewButton(), "click create new Button test failed");
		sa.assertTrue(rcp.createNewReward("TestNewReward", "30 Jun 2022"), "Reward creation failed");
		sa.assertTrue(op.goToRewardsSection(), "User not naivated to Rewards section");
		sa.assertTrue(rp.newlyCreatedRewardPresentInList("TestNewReward"));
		sa.assertAll();
	}
}
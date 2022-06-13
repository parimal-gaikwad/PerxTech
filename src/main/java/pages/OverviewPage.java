package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import commons.BasePage;

public class OverviewPage extends BasePage {

	private static final Logger log = Logger.getLogger(OverviewPage.class.getName());

	public OverviewPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//li[@data-key='business_intelligence']/a")
	private WebElement dashboard;

	@FindBy(xpath = "//li[@data-key='reports']/a")
	private WebElement reports;

	@FindBy(xpath = "//li[@data-key='rewards']/a")
	private WebElement rewards;

	@FindBy(xpath = "//li[@data-key='catalogs']/a")
	private WebElement catalogs;

	@FindBy(xpath = "//li[@data-key='campaigns']/a")
	private WebElement campaigns;

	@FindBy(xpath = "//li[@data-key='loyalties']/a")
	private WebElement loyalties;

	@FindBy(xpath = "//li[@data-key='transaction_rules']/a")
	private WebElement rules;

	@FindBy(xpath = "//li[@data-key='merchants']/a")
	private WebElement merchants;

	@FindBy(xpath = "//li[@data-key='customer_management']/a")
	private WebElement customers;

	@FindBy(xpath = "//li[@data-key='bulk_actions']/a")
	private WebElement bulkActions;

	@FindBy(xpath = "//li[@data-key='settings']/a")
	private WebElement settings;

	public boolean verifyRewardAdminDoesNotHaveAccessToOtherSections() {

		boolean finalFlag = false;

		boolean flag1 = isDisplayed(loyalties);
		log.info("checking if Loyalities section is accessible to Reward admin : " + flag1);
		boolean flag2 = isDisplayed(campaigns);
		log.info("checking if Campaigns section is accessible to Reward admin : " + flag2);
		boolean flag3 = isDisplayed(merchants);
		log.info("checking if merchants section is accessible to Reward admin : " + flag3);
		boolean flag4 = isDisplayed(customers);
		log.info("checking if user list section is accessible to Reward admin : " + flag4);
		boolean flag5 = isDisplayed(bulkActions);
		log.info("checking if bulk actions section is accessible to Reward admin : " + flag5);

		if (!(flag1 && flag2 && flag3 && flag4 && flag5))
			finalFlag = true;

		return finalFlag;

	}

	public boolean verifyUserHasSufficientPermissionToCreateReward() {

		boolean flag = false;
		if (isDisplayed(rewards)) {
			flag = true;
			log.info("Checking if user has sufficient permission to create reward : " + flag);
		}
		return flag;
	}

	public boolean verifyUserHasSufficientPermissionToVisitBulkFileUploadPage() {

		boolean flag = false;
		if (isDisplayed(bulkActions)) {
			flag = true;
			log.info("Checking if user has sufficient permission to Visit Bulk File Upload Page : " + flag);
		}
		return flag;
	}

	public boolean VerifyNonAuthorizedUserShouldNotHaveAccessToRewardDetailPage() {

		boolean flag = false;
		log.info("trying to access reward page using url ");
		driver.get("https://dashboard.perxtech.io/dashboard/p/rewards/list");
		if (driver.getCurrentUrl().contains("signin")) {
			flag = true;
			log.info("Non Authorised user could not access reward page using url : " + driver.getCurrentUrl());
		} else {
			log.info("Non Authorised user could not access reward page using url : " + driver.getCurrentUrl());
		}
		return flag;
	}

	public boolean goToRewardsSection() {
		boolean flag = false;
		click(rewards);
		log.info("clicked on rewards section ");
		wait.until(ExpectedConditions.urlContains("/rewards/list"));
		if (driver.getCurrentUrl().contains("/rewards/list"))
			flag = true;
		return flag;

	}

	public boolean goToBulkActionsSection() {
		boolean flag = false;
		click(bulkActions);
		log.info("clicked on bulk actions section ");
		wait.until(ExpectedConditions.urlContains("bulk_actions"));
		if (driver.getCurrentUrl().contains("bulk_actions"))
			flag = true;
		return flag;

	}

}

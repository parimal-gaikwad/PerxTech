package pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import commons.BasePage;

public class RewardCreationPage extends BasePage {

	private static final Logger log = Logger.getLogger(RewardCreationPage.class.getName());

	public RewardCreationPage(WebDriver driver) {
		super(driver);

	}

	@FindBy(xpath = "//input[@value='private']")
	private WebElement radioPrivate;

	@FindBy(xpath = "//input[@value='system']")
	private WebElement radioSystem;

	@FindBy(xpath = "//input[@value='public']")
	private WebElement radioPublic;

	@FindBy(xpath = "//input[@name='name_en']")
	private WebElement name;

	@FindBy(xpath = "//input[@name='subtitle_en']")
	private WebElement Subtitle;

	@FindBy(xpath = "//label[contains(text(),'Description')]/parent::div/following::div[1]/div/span/div/div[2]")
	private WebElement description;

	@FindBy(xpath = "//label[contains(text(),'Steps To Redeem')]/parent::div/following::div[1]/div/span/div/div[2]")
	private WebElement stepsToReedem;

	@FindBy(xpath = "//label[contains(text(),'Terms and Conditions')]/parent::div/following::div[1]/div/span/div/div[2]")
	private WebElement termsAndConditions;

	@FindBy(xpath = "//div[contains(text(),'Please select your brands')]")
	private WebElement brands;

	@FindBy(xpath = "//div[contains(text(),'Please select your tags')]")
	private WebElement tags;

	@FindBy(xpath = "//div[contains(text(),'Please select your categories')]")
	private WebElement categories;

	@FindBy(xpath = "//div[contains(text(),'Please select your labels')]")
	private WebElement labels;

	@FindBy(xpath = "//label[contains(text(),'Start Date')]/parent::div/following::div[1]/div/span/div/div/div/span/div/input")
	private WebElement sellingPeriodstartDateCalender;

	@FindBy(xpath = "//label[contains(text(),'Start Date')]/parent::div/following::div[1]/div/span/div/div/div/span[2]/input")
	private WebElement sellingPeriodStartTime;

	@FindBy(xpath = "//label[contains(text(),'End Date')]/parent::div/following::div[1]/div/span/div/div/div/span[2]/input")
	private WebElement sellingPeriodEndTime;

	@FindBy(xpath = "//label[contains(text(),'End Date')]/parent::div/following::div[1]/div/span/div/div/div/span/div/input")
	private WebElement sellingPeriodEndDateCalender;

	@FindBy(xpath = "(//*[@class='sc-iAvgwm eLjODB'])[2]/div/span/div/input")
	private WebElement validityPeriodEndDate;

	@FindBy(xpath = "(//*[@class='sc-iAvgwm eLjODB'])[2]/div/span/div/i[1]")
	private WebElement validityPeriodEndDateCalender;

	@FindBy(xpath = "(//*[@class='sc-iAvgwm eLjODB'])[1]/div/span/div/i[1]")
	private WebElement validityPeriodStartDateCalender;

	@FindBy(xpath = "//input[@class='ant-calendar-input ']")
	private WebElement dateInput;

	@FindBy(xpath = "(//*[@class='sc-iAvgwm eLjODB'])[2]/div/span/div/input")
	private WebElement validityPeriodStartDate;

	@FindBy(xpath = "//div[contains(text(),'Please select a merchant')]")
	private WebElement merchant;

	@FindBy(xpath = "//section[contains(text(),'Map your reward to:')]/parent::section/section[2]/div/label/span/input")
	private WebElement mapYourRewardsToCheckBox;

	@FindBy(xpath = "//span[contains(text(),'Add Selling Price')]")
	private WebElement addSellingPrice;

	@FindBy(xpath = "//input[@name='reward_cost_amount']")
	private WebElement rewardCost;

	@FindBy(xpath = "//span[contains(text(),'Add Custom Field')]")
	private WebElement addCustomField;

	@FindBy(xpath = "//span[contains(text(),'Next')]")
	private WebElement nextBtn;

	@FindBy(xpath = "//span[contains(text(),'Cancel')]")
	private WebElement cancelBtn;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement saveBtn;

	@FindBy(xpath = "(//div[@class='ant-message-notice-content'])[1]/div/span")
	private WebElement mandatoryFieldErrorPopup;

	@FindBy(xpath = "//*[@title='Name']/parent::div/following-sibling::div/div/span/div[2]")
	private WebElement rewardNameError;

	@FindBy(xpath = "(//div[@class='ant-message-notice-content'])[2]/div/span")
	private WebElement successPopup;

	@FindBy(xpath = "//label[@class='Label-sc-6v5l51-0 cgPDTc']")
	private WebElement validityPeriodStartEndDateError;

	// *[@title='Name']/parent::div/following-sibling::div/div/span/div[2]

	/**
	 * Method to verify if reward creation page has validity period start and end
	 * dates
	 * 
	 * @return returns true if date field present else returns false if date fields
	 *         are missing
	 */
	public boolean verifyRewardValidityPeriodHaveBothStartAndEndDates() {

		boolean finalFlag = false;

		sendKeys(name, "TestRewardName");
		log.info("Enterd reward name");
		click(nextBtn);
		log.info("clicked on Next button to naviate to mechanics section");
		boolean flag1 = isDisplayed(validityPeriodStartDate);
		log.info("checking if Validity period has start date : " + flag1);
		boolean flag2 = isDisplayed(validityPeriodEndDate);
		log.info("checking if Validity period has end date : " + flag2);

		if (flag1 && flag2)
			finalFlag = true;

		return finalFlag;
	}

	/**
	 * Method verifies that if any madatory field is kept empty respective error
	 * should popup and when all mandatory fields are filled then reward should get
	 * created with success message
	 * 
	 * @return return true if all required error and success messages popped up
	 *         otherwise returns false
	 */
	public boolean verifySuccessfulSubmissionHappensOnlyWhenPayloadContainsMandInfo() {

		boolean finalFlag = false;

		log.info("clicking on Next button by keeping Reward name field empty to check error");
		click(nextBtn);
		boolean flag1 = getText(mandatoryFieldErrorPopup).equals("Reward name is required");
		sendKeys(name, "TestRewardName ");
		log.info("entered value in Reward name field and clicking on next button");
		click(nextBtn);
		log.info("clicking on Next button by keeping Validity period start and end Date field empty to check error");
		click(nextBtn);
		boolean flag2 = getText(mandatoryFieldErrorPopup).equals("Start date & end date required");
		click(validityPeriodEndDateCalender);
		sendKeys(dateInput, "30 Jun 2022");
		sendKeys(dateInput, Keys.ENTER);
		log.info("Entered validity period dates and clicking on next button");
		click(nextBtn);
		log.info("clicking on save button to create reward");
		click(saveBtn);
		boolean flag3 = getText(successPopup).equals("Success");

		if (flag1 && flag2 && flag3)
			finalFlag = true;

		return finalFlag;
	}

	public boolean verifyBrandsTagsCategoriesLabelsFieldDisappersInPrivateTypeReward() {

		boolean flag = false;

		
		sendKeys(radioPrivate,Keys.SPACE);
		js.scrollDown();
		boolean flag1 = isDisplayed(brands);
		log.info("Brands field present on priavte type of reward : " + flag1);
		boolean flag2 = isDisplayed(tags);
		log.info("tags field present on priavte type of reward : " + flag2);
		boolean flag3 = isDisplayed(categories);
		log.info("categories field present on priavte type of reward : " + flag3);
		boolean flag4 = isDisplayed(labels);
		log.info("lables field present on priavte type of reward : " + flag4);

		if (!(flag1 || flag2 || flag3 || flag4))
			flag = true;

		return flag;
	}

	/**
	 * Method to create new reward
	 * 
	 * @param rewardName      name of reward
	 * @param validityEndDate end date for reward validity
	 * @return returns true when reward is created and success message pops up
	 */
	public boolean createNewReward(String rewardName, String validityEndDate) {

		boolean flag = false;

		sendKeys(name, rewardName);
		log.info("entered value in Reward name field and clicking on next button");
		click(nextBtn);
		click(validityPeriodEndDateCalender);
		sendKeys(dateInput, validityEndDate);
		sendKeys(dateInput, Keys.ENTER);
		log.info("Entered validity period dates and clicking on next button");
		click(nextBtn);
		log.info("clicking on save button to create reward");
		click(saveBtn);
		if (getText(successPopup).equals("Success"))
			flag = true;

		return flag;
	}

}

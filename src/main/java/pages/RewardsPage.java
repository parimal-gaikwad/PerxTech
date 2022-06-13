package pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import commons.BasePage;

public class RewardsPage extends BasePage {

	private static final Logger log = Logger.getLogger(RewardsPage.class.getName());

	public RewardsPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//span[contains(text(),'Rewards')]")
	private WebElement rewards;

	@FindBy(xpath = "//span[text()='Create New']")
	private WebElement createNew;

	@FindBy(xpath = "//tbody[@class='ant-table-tbody']/tr/td[1]")
	private List<WebElement> rewardsPresentInList;

	public boolean clickCreateNewButton() {

		boolean flag = false;
		click(createNew);
		log.info("clicked on Create New button");
		wait.until(ExpectedConditions.urlContains("/create"));
		if (driver.getCurrentUrl().contains("create"))
			flag = true;
		log.info("User navigated to Reward creation page : " + flag);

		return flag;
	}

	public boolean newlyCreatedRewardPresentInList(String rewardName) {

		boolean flag = false;

		List<String> rewardNamesPresentInList = getTextList(rewardsPresentInList);
		log.info("Rewards presnt : " + rewardNamesPresentInList);
		for (String reward : rewardNamesPresentInList) {
			if (reward.contains(rewardName))
				flag = true;
		}
		return flag;
	}

}

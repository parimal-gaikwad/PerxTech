package pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import commons.BasePage;

public class BulkActionsPage extends BasePage {

	private static final Logger log = Logger.getLogger(BulkActionsPage.class.getName());

	public BulkActionsPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "(//button)[1]")
	private WebElement uploadBtn;

	@FindBy(xpath = "//div[@class='ant-upload-drag-container']/p/i")
	private WebElement uploadFileArea;

	@FindBy(xpath = "//div[@class='ant-upload-list-item-info']")
	private WebElement uploadedFileOnForm;

	@FindBy(xpath = "//button[@class='ant-btn ant-btn-primary']")
	private WebElement uploadBtnOnForm;

	@FindBy(xpath = "(//div[@class='ant-message-notice-content'])[1]/div/span")
	private WebElement popupAlert;

	@FindBy(xpath = "//tbody/tr/td[2]")
	private List<WebElement> filesPresentInList;

	@FindBy(xpath = "//span[@class='ant-select-arrow']")
	private WebElement actionTypeDropdown;

	@FindBy(xpath = "//*[@role='listbox']//li")
	private List<WebElement> actionTypeOptions;

	@FindBy(xpath = "//*[@class='ant-select-selection__rendered']/div[2]")
	private WebElement selectedActionType;

	public boolean verifyNewlyUploadedFileReflectsInList(String fileName) {

		boolean flag = false;

		List<String> filesPresent = getTextList(filesPresentInList);
		log.info("Files present in list : " + filesPresent);
		if (filesPresent.contains(fileName))
			flag = true;
		return flag;
	}

	public boolean verifyEachFileShouldBeTiedToOneAction(String fileName, String expectedActionType) {

		boolean flag = false;
		log.info("expected action type present for file "+fileName+" is : "+expectedActionType);

		By by = By.xpath("//td[contains(text(),'" + fileName + "')]/parent::tr/td[1]/div");
		WebElement webelement = driver.findElement(by);
		String actualActionType = getText(webelement);
		log.info("actual action type present for file "+fileName+" is : "+actualActionType);

		boolean flag1 = !(actualActionType.isEmpty());
		boolean flag2 = actualActionType.equals(expectedActionType);

		if (flag1 && flag2)
			flag = true;

		return flag;
	}

	public boolean uploadBulkActionsFile(String path, String popupText, String actionType) {

		boolean flag = false;
		click(uploadBtn);
		log.info("clicked on upload button");
		log.info("Selecting option type as : " + actionType);
		click(actionTypeDropdown);
		
		
		By by = By.xpath("//*[@role='listbox']/li[contains(text(),'"+actionType+"')]");
		WebElement optionToBeSelected = wait.forElementToBeVisible(driver.findElement(by));
		click(optionToBeSelected);
		
		log.info("selected option type as : " + getText(selectedActionType));
		click(uploadFileArea);
		log.info("clicked on file drop area ");
		uploadFile(path);
		log.info("clicked on upload button on form and uploaded file");
		log.info("Uploading file : " + path.substring(path.lastIndexOf("\\") + 1));
		log.info("uploading file of type : " + path.substring(path.lastIndexOf(".") + 1));
		wait.forElementToBeVisible(uploadedFileOnForm);
		click(uploadBtnOnForm);
		String text = getText(popupAlert);
		log.info("Alert poped up as : " + text);

		if (text.equals(popupText))
			flag = true;

		return flag;
	}

}

package pages;


import static commons.Configuration.rewardModeratorEmail;
import static commons.Configuration.rewardModeratorPassword;
import static commons.Configuration.adminEmail;
import static commons.Configuration.adminPassword;



import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.asserts.SoftAssert;

import commons.BasePage;

public class LoginPage extends BasePage {

	private static final Logger log = Logger.getLogger(LoginPage.class.getName());
	SoftAssert sa = new SoftAssert();

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@id='email']")
	private WebElement email;
	@FindBy(xpath = "//input[@id='password']")
	private WebElement password;
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement loginButton;
	@FindBy(xpath= "(//div[@class='ant-message-notice-content'])[1]/div/span")
	private WebElement popupAlert;

	
	
	
	
	
	/**
	 * method for reward admin login
	 * @param emailId  email Id of user 
	 * @param pwd password of user
	 * @return returns boolean value if login is successful returns true if login is failed returns false
	 */
	public boolean doRewardAdminLogin() {
		boolean flag = false;
		sendKeys(email,rewardModeratorEmail.asString());
		log.info("Entered email : "+rewardModeratorEmail.asString());
		sendKeys(password, rewardModeratorPassword.asString());
		log.info("Entered password : "+rewardModeratorPassword.asString());
		click(loginButton);
		log.info("clicked on login button");
		if(getText(popupAlert).equals("Login Success"))		
				flag=true;
		
		return flag;
	}
	
	
	/**
	 * method for admin login
	 * @param emailId  email Id of user 
	 * @param pwd password of user
	 * @return returns boolean value if login is successful returns true if login is failed returns false
	 */
	public boolean doAdminLogin() {
		boolean flag = false;
		sendKeys(email,adminEmail.asString());
		log.info("Entered email : "+adminEmail.asString());
		sendKeys(password, adminPassword.asString());
		log.info("Entered password : "+adminPassword.asString());
		click(loginButton);
		log.info("clicked on login button");
		if(getText(popupAlert).equals("Login Success"))		
			flag=true;	
		return flag;
	}

	
}

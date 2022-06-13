package commons;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

public class ExplicitWait {

	private static Logger log = Logger.getLogger(ExplicitWait.class);
	private WebDriver driver;
	private JavaScriptHelper js;

	public ExplicitWait(WebDriver driver) {
		super();
		this.driver = driver;
		this.js = new JavaScriptHelper(driver);
	}

	private WebDriverWait getWait() {
		return getWait(Timeouts.EXPLICIT, Timeouts.POLLING_INTERVAL);
	}

	private WebDriverWait getWait(int timeOutInSeconds, int pollingEveryInMiliSec) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds, pollingEveryInMiliSec);
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(ElementNotVisibleException.class);
		wait.ignoring(StaleElementReferenceException.class);
		wait.ignoring(NoSuchFrameException.class);
		return wait;
	
	}

	public void setImplicitWait(long timeout, TimeUnit unit) {
//		log.info("Implicit timeout is set to: " + timeout);
		driver.manage().timeouts().implicitlyWait(timeout, unit);
	}

	public void setImplicitWait(long timeout) {
		setImplicitWait(timeout, TimeUnit.SECONDS);
	}

	public List<WebElement> forAllElementsToBePresent(By locator) {
		log.info("Waiting for element to be present: " + locator.findElement(driver).getText());
		return waitFor(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}
	
	public List<WebElement> forNumberOfElementsToBeMoreThan(By locator , Integer i) {  /// added by parimal
		log.info("Waiting for Number of elements to be more than : " + i);
		return waitFor(ExpectedConditions.numberOfElementsToBeMoreThan(locator, i));
	}
	
	public Alert forAlertToBePresent() {
		log.info("Waiting for Alert to be present");

		return waitFor(ExpectedConditions.alertIsPresent());
	}

	
	
	public WebElement forElementToBePresent(By locator) {
		log.info("Waiting for element to be present: " + locator);
		return waitFor(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public WebElement forElementToBeVisible(By locator) {
		return forElementToBeVisible(locator, Timeouts.EXPLICIT, Timeouts.POLLING_INTERVAL);
	}

	public WebElement forElementToBeVisible(By locator, int timeoutInSeconds) {
		return forElementToBeVisible(locator, timeoutInSeconds, Timeouts.POLLING_INTERVAL);
	}

	public WebElement forElementToBeVisible(WebElement webElement) {
		return forElementToBeVisible(webElement, Timeouts.EXPLICIT, Timeouts.POLLING_INTERVAL);
	}

	public List<WebElement> forAllElementToBeVisible(List<WebElement> l) {
		return forElementToBeVisible(l, Timeouts.EXPLICIT, Timeouts.POLLING_INTERVAL);
	}

	
	
	public WebElement forElementToBeVisible(WebElement webElement, int timeoutInSeconds) {
		return forElementToBeVisible(webElement, timeoutInSeconds, Timeouts.POLLING_INTERVAL);
	}

	public WebElement forElementToBeVisible(By locator, int timeOutInSeconds, int pollingEveryInMiliSec) {
		log.info("Waiting for locator to be visible: " + locator.findElement(driver).getText());
		return waitFor(ExpectedConditions.visibilityOfElementLocated(locator), timeOutInSeconds, pollingEveryInMiliSec);
	}

	public List<WebElement> forElementToBeVisible(List<WebElement>l, int timeOutInSeconds, int pollingEveryInMiliSec) {
		log.info("Waiting for locator to be visible: " + l);
		return waitFor(ExpectedConditions.visibilityOfAllElements(l) , timeOutInSeconds, pollingEveryInMiliSec);
	}

	
	public WebElement forElementToBeVisible(WebElement webElement, int timeOutInSeconds, int pollingEveryInMiliSec) {
//			log.info("Waiting for element to be visible: " + webElement);
				return waitFor(ExpectedConditions.visibilityOf(webElement), timeOutInSeconds, pollingEveryInMiliSec);
	}

	public boolean forTextToBePresentInElementValue(WebElement element, String searchedText) {
		log.info("Waiting for text " + searchedText + "to be present in element: " + element);
		return waitFor(ExpectedConditions.textToBePresentInElementValue(element, searchedText));
	}

	public WebElement forElementToBeClickable(By element) {
		return forElementToBeClickable(element, Timeouts.EXPLICIT, Timeouts.POLLING_INTERVAL);
	}

	public WebElement forElementToBeClickable(By element, int timeoutInSeconds) {
		return forElementToBeClickable(element, Timeouts.EXPLICIT, Timeouts.POLLING_INTERVAL);
	}

	public WebElement forElementToBeClickable(WebElement element) {
		return forElementToBeClickable(element, Timeouts.EXPLICIT, Timeouts.POLLING_INTERVAL);
	}

	public WebElement forElementToBeClickable(WebElement element, int timeoutInSeconds) {
		return forElementToBeClickable(element, Timeouts.EXPLICIT, Timeouts.POLLING_INTERVAL);
	}

	public WebElement forElementToBeClickable(WebElement element, int timeOutInSeconds, int pollingEveryInMiliSec) {
		log.info("Waiting for element to be clickable: " + element);
		return waitFor(ExpectedConditions.elementToBeClickable(element), timeOutInSeconds, pollingEveryInMiliSec);
	}

	public WebElement forElementToBeClickable(By element, int timeOutInSeconds, int pollingEveryInMiliSec) {
		log.info("Waiting for element to be clickable: " + element);
//		forPage();
		return waitFor(ExpectedConditions.elementToBeClickable(element), timeOutInSeconds, pollingEveryInMiliSec);
	}

	public boolean forInvisibilityOfElement(WebElement element) {
		return forInvisibilityOfElement(element, Timeouts.EXPLICIT, Timeouts.POLLING_INTERVAL);
	}

	public boolean forInvisibilityOfElement(By locator) {
		return forInvisibilityOfElement(locator, Timeouts.EXPLICIT, Timeouts.POLLING_INTERVAL);
	}

	public boolean forInvisibilityOfElement(WebElement element, int timeOutInSeconds) {
		return forInvisibilityOfElement(element, Timeouts.EXPLICIT, Timeouts.POLLING_INTERVAL);
	}

	public boolean forInvisibilityOfElement(WebElement element, int timeOutInSeconds, int pollingEveryInMiliSec) {
		log.info("Waiting for invisibility of element: " + element);
		return waitFor(ExpectedConditions.invisibilityOf(element), timeOutInSeconds, pollingEveryInMiliSec);
	}

	public boolean forInvisibilityOfElement(By locator, int timeOutInSeconds, int pollingEveryInMiliSec) {
		log.info("Waiting for invisibility of element: " + locator);
		return waitFor(ExpectedConditions.invisibilityOfElementLocated(locator), timeOutInSeconds,
				pollingEveryInMiliSec);
	}
	
	public void until(Function expectedCondition) {
		getWait().until(expectedCondition);
	}
		
	private <V> V waitFor(Function expectedCondition) {
		return waitFor(expectedCondition, Timeouts.EXPLICIT, Timeouts.POLLING_INTERVAL);
	}

	private <V> V waitFor(Function expectedCondition, int timeOutInSeconds, int pollingEveryInMiliSec) {
		setImplicitWait(10);
		WebDriverWait wait = getWait(timeOutInSeconds, pollingEveryInMiliSec);
		V result = (V) wait.until(expectedCondition);
		setImplicitWait(Timeouts.IMPLICIT);
		return result;
	}

}

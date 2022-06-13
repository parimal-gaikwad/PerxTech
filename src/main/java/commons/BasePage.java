package commons;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public abstract class BasePage extends TestBase {

	protected WebDriver driver;

	protected ExplicitWait wait;

	protected JavaScriptHelper js;


	private static final Logger log = Logger.getLogger(BasePage.class);
	public static String fs = FileSystems.getDefault().getSeparator();

	protected BasePage(WebDriver driver) {
		this.driver = driver;
		wait = new ExplicitWait(driver);
		js = new JavaScriptHelper(driver);
		PageFactory.initElements(driver, this);
	}

	public void click(WebElement webElement) {
		click(webElement, Timeouts.EXPLICIT);
	}

	public boolean isDisplayed(WebElement w) {
		boolean flag = false;
		try {
			wait.forElementToBeVisible(w);
			if (w.isDisplayed())
				flag = true;
		} catch (Exception e) {
			log.info("inside catch after exception :" + e.getClass().getSimpleName());
		}
		return flag;
	}

	public void click(WebElement webElement, int timeOutInSeconds) {
		try {
			wait.forElementToBeVisible(webElement);
			webElement.click();
		} catch (Exception e) {
			log.info("inside catch after exception :" + e.getClass().getSimpleName());
			wait.forElementToBeClickable(webElement, timeOutInSeconds, Timeouts.POLLING_INTERVAL);
			js.clickElement(webElement);		}
	}

	public String getText(WebElement webElement) {
		String text = "";
		try {
			wait.forElementToBeVisible(webElement);
			text = webElement.getText();
//			log.info("text present : "+text);

		} catch (Exception e) {
			log.info("inside catch after exception :" + e.getClass().getSimpleName());
			text = webElement.getAttribute("textContent");
//			log.info("text present : "+text);
		}
		return text;
	}

	public String getAttribute(WebElement webElement, String attribute) {
		String value = "";
		try {
			wait.forElementToBeVisible(webElement);
			value = webElement.getAttribute(attribute);
		} catch (Exception e) {
			log.info("inside catch after exception :" + e.getClass().getSimpleName());
			value = webElement.getAttribute(attribute);
		}
		return value;
	}

	public void sendKeys(WebElement webElement, String value, int timeOutInSeconds) {
		clearTextbox(webElement);
		try {
			js.scrollIntoView(webElement);
			webElement.sendKeys(value);
//			log.info("Entered value : " + value);

		} catch (Exception e) {
			log.info("inside catch after exception :" + e.getClass().getSimpleName());
			js.scrollIntoView(webElement);
			webElement.sendKeys(value);
//			log.info("Entered value : " + value);
		}
	}

	public void sendKeys(WebElement webElement, String value) {
		sendKeys(webElement, value, Timeouts.EXPLICIT);
	}

	public void sendKeys(WebElement webElement, Keys k) {
		sendKeys(webElement, k, Timeouts.EXPLICIT);
	}

	public void sendKeys(WebElement webElement, Keys k, int timeOutInSeconds) {
		try {
			webElement.sendKeys(k);
		} catch (Exception e) {
			log.info("Inside catch after exception : " + e.getClass().getSimpleName());
			webElement.sendKeys(k);
		}
	}

	public void executeJavascript(String string) {
		js.executeScript(string);
	}

	public void executeJavascript(String string, WebElement element) {
		js.executeScript(string, element);
	}

	public void selectvisibletext(WebElement element, String text) {
		Select sel = new Select(element);
		sel.selectByVisibleText(text);
	}

	public void selectvisiblevalue(WebElement element, String value) {
		Select sel = new Select(element);
		sel.selectByValue(value);
	}

	public void selectvisibleindex(WebElement element, int index) {
		Select sel = new Select(element);
		sel.selectByIndex(index);
	}

	public void dragAndDrop(WebElement source, WebElement target) {

		Actions act = new Actions(driver);
		act.dragAndDrop(source, target).perform();
	}

	public void dragAndDrop(int x1, int y1, int x2, int y2) {
		String dragAndDrop = System.getProperty("user.dir") + "\\src\\test\\resources\\drag.exe";
		try {
			Runtime.getRuntime().exec(dragAndDrop + " " + x1 + " " + y1 + " " + x2 + " " + y2);
			Thread.sleep(3000);

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	 * Method used AutoIT to upload file in Window based popup it take path of file
	 * to be uploaded
	 * 
	 * @param filePath Path of file to be uploaded
	 */
	public int uploadFile(String filePath) {
		String uploader = System.getProperty("user.dir") + "\\src\\test\\resources\\uploadFile.exe";
		Integer exitValue = 1;
		try {
			Process p = Runtime.getRuntime().exec(uploader + " " + filePath);
//			exitValue = p.exitValue();

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return exitValue;
	}

	public void refreshPage() {

		driver.navigate().refresh();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

	}


	public List<String> getTextList(List<WebElement> list) {
		List<String> text = new ArrayList<String>();
		if (list.size() > 0) {
			wait.forAllElementToBeVisible(list);
			for (WebElement w : list) {
//				wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(w)));
				text.add(getText(w));
			}
		}
		return text;
	}

	public List<String> getAttributeList(List<WebElement> list, String attribute) {
		List<String> values = new ArrayList<String>();
		if (list.size() > 0) {
			wait.forAllElementToBeVisible(list);
			for (WebElement w : list) {
//				wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(w)));
				values.add(getAttribute(w, attribute));
			}
		}
		return values;
	}


	public boolean isSelected(WebElement e) { // method to check if option selected from dropdown or not

		String xpath = getXpathFromWebelement(e);
		int k = 0;
		List<WebElement> list = driver.findElements(By.xpath(xpath + "/option"));
		for (int j = 0; j < list.size(); j++) {
			String s = list.get(j).getAttribute("selected");
			boolean b = Boolean.parseBoolean(s);
			if (b) {
				k = j;
			}
		}
		boolean check = Boolean.parseBoolean(list.get(k).getAttribute("selected"));
		return check;
	}

	public String getSelectedOption(WebElement e) { // method to get selected option from dropdowns

		String xpath = getXpathFromWebelement(e);
		int k = 0; // to store the index of option which is selected
		List<WebElement> list = driver.findElements(By.xpath(xpath + "/option")); // list of options under select tag
		for (int j = 0; j < list.size(); j++) {
			String s = list.get(j).getAttribute("selected"); // stores attribute "selected" of resp option tag
			boolean b = Boolean.parseBoolean(s); // converts string into boolean
			if (b) { // if true then respective option is selected
				k = j;
			}
		}
		String option = list.get(k).getText();
		return option;
	}

	public String getXpathFromWebelement(WebElement e) { // method to fetch xpath from PageFacotry webelement
		String xpath = e.toString();
		xpath = xpath.substring(xpath.indexOf("/"), xpath.lastIndexOf("]"));
		return xpath;
	}

	public void clearTextbox(WebElement w) {

		try {
			while (!w.getAttribute("value").isEmpty())
				sendKeys(w, Keys.BACK_SPACE);
			while (!w.getText().isEmpty())
				sendKeys(w, Keys.BACK_SPACE);
		} catch (Exception e) {
			log.info("Inside catch after exception :" + e.getClass().getSimpleName());
			while (!w.getText().isEmpty())
				sendKeys(w, Keys.BACK_SPACE);
		}
	}

	/**
	 * method to switch to window other than parent window passed as parameter
	 * 
	 * @param parentWindow Id of parent window
	 */
	public void switchToWindow(String parentWindow) {
		log.info("Parent window handle is : " + parentWindow);
		Set<String> handles = driver.getWindowHandles();
		log.info("Presenty opened widows are : " + handles);
		if (handles.size() > 1) {
			for (String handle : handles) {
				if (!handle.equalsIgnoreCase(parentWindow))
					driver.switchTo().window(handle);
			}
		} else
			log.info("only single window present at moment");
	}
}

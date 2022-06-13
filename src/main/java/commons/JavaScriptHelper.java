package commons;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptHelper {

    private final WebDriver driver;

    public static final Logger log = Logger.getLogger(JavaScriptHelper.class);

    public JavaScriptHelper(WebDriver driver) {
        this.driver = driver;
    }

    public Object executeScript(String script) {
        JavascriptExecutor exe = (JavascriptExecutor) driver;
        log.info(script);
        return exe.executeScript(script);
    }

    public Object executeScript(String script, Object... args) {
        JavascriptExecutor exe = (JavascriptExecutor) driver;
        log.info(script);
        return exe.executeScript(script, args);
    }

    public void scrollToElement(WebElement element) {
        executeScript("window.scrollTo(arguments[0],arguments[1])",
                element.getLocation().x, element.getLocation().y);
        log.info(element);
    }

    public void scrollToElement(By locator) {
        scrollToElement(driver.findElement(locator));
        log.info(locator);
    }

    

    public void scrollIntoView(WebElement element) {
        executeScript("arguments[0].scrollIntoView()", element);

        log.info("scrolled into view of : "+element);
    }

    public void scrollIntoViewAndClick(WebElement element) {
        scrollIntoView(element);
        clickElement( element);
        log.info("scrolled into view and clicked : "+element);
    }

    public void clickElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }

    public void scrollDown() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,700)", "");
    }
    
    public void scrollToBottomOfPage() {
    	JavascriptExecutor js = (JavascriptExecutor) driver;
    	   js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void scrollToTopOfPage() {
    	JavascriptExecutor js = (JavascriptExecutor) driver;
 	   js.executeScript("window.scrollTo(0, -document.body.scrollHeight)");
    	
    }
	
}




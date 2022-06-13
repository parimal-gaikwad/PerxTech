package commons;

import java.nio.file.FileSystems;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;


import org.apache.log4j.PropertyConfigurator;

import io.github.bonigarcia.wdm.WebDriverManager;
import listener.ListenerTest;

@Listeners(ListenerTest.class)
public class TestBase {

	public WebDriver driver;
	public static String fs = FileSystems.getDefault().getSeparator();

	@BeforeMethod(alwaysRun = true)
	public void setUp() {
		System.setProperty("configPath", "./env/prod.properties");
		System.setProperty("browserName", "chrome");
		this.driver = createDriver();
		setUpWebDriver(driver);

	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() throws Exception {
		if (driver != null) {

			driver.close();
//			driver.quit();
		}
	}

	private WebDriver createDriver() {

			String browserName = System.getProperty("browserName");
			if (browserName == null) {
				throw new RuntimeException("You must specify a browserName");
			}
			
			if (browserName.equals("chromeH")) {
				ChromeOptions options = new ChromeOptions();

				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver(options.setHeadless(true));
			}

			if (browserName.equals("firefoxH")) {
				FirefoxOptions options = new FirefoxOptions();

				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver(options.setHeadless(true));
			}

			if (browserName.equals("chrome")) {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver((ChromeOptions) getOptions(browserName));
			}

			if (browserName.equals("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver((FirefoxOptions) getOptions(browserName));
			}

			if (browserName.equals("internetexplorer")) {
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver((InternetExplorerOptions) getOptions(browserName));
			}
			return driver;
		}
	

	private MutableCapabilities getOptions(String browserName) {
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setBrowserName(browserName);

		if (browserName.equals("firefox")) {
			FirefoxOptions options = new FirefoxOptions();
			return options.merge(cap);
		}

		if (browserName.equals("internetexplorer")) {
			InternetExplorerOptions options = new InternetExplorerOptions();
			return options.merge(cap);
		}

		if (browserName.equals("chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("disable-gpu");
			options.addArguments("--disable-print-preview");
			return options.merge(cap);
		}

		
		throw new RuntimeException(browserName + " is an invalid browserName.");
	}

	private static void setUpWebDriver(WebDriver driver) {
		
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Timeouts.PAGE, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(Timeouts.PAGE, TimeUnit.SECONDS);
	}

	
	@BeforeSuite
	public void startLogs() {
		PropertyConfigurator.configure(System.getProperty("user.dir") + "//src//test//resources//log4j.properties");

	}
	
}
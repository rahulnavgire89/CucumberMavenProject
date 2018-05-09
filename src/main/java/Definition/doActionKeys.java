package Definition;

import java.io.File;
import org.apache.commons.io.FileUtils;

import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

//import com.sun.jna.platform.FileUtils;

import cucumber.api.Scenario;

public class doActionKeys {
	public static WebDriver driver;
	private static final Long MIN_WAIT_TIME_IN_MS = 1000L;

	public static class wdriver {
		public static void chromeDriver() {
			try {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\Resources\\chromedriver.exe");
				driver = new ChromeDriver();
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
				driver.manage().window().maximize();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		public static void ieDriver() {
			try {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\Resources\\IEDriverServer.exe");
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability("nativeEvents", true);
				capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
				capabilities.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP, true);
				capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
				driver = new InternetExplorerDriver(capabilities);
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		public static void closeDriver() {
			try {
				driver.manage().deleteAllCookies();
				driver.quit();
				driver = null;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public static void key_LaunchUrl(String Value) {
		try {
			driver.get(Value);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void key_Wait(int seconds) {
		try {
			long count = MIN_WAIT_TIME_IN_MS * seconds;
			Thread.sleep(count);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void capture_Screenshot(Scenario logger) {
		try {
			final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			logger.embed(screenshot, "image/png"); // stick it in the report
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}

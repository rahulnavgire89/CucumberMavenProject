package Definition;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class TestDefinition {

	static Scenario logger;
	static Map<String, String> inputdata;
	static String filelocation;

	@Before
	public void setup(Scenario logger) {
		Date folname= new Date();
		this.logger = logger;
		inputdata = ExcelReader.inputTestDataLoad();
		doActionKeys.wdriver.chromeDriver();

	}

	@After
	public void teradown() {
		try {
		doActionKeys.wdriver.closeDriver();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Given("^Userenter URL$")
	public void userenter_URL() {
//		System.setProperty("webdriver.chrome.driver", "C:\\Users\\navgirah\\Desktop\\chromedriver.exe");
//		driver = new ChromeDriver();
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	//driver.get("https://www.freecrm.com/index.html");
//		driver.manage().window().maximize();
		doActionKeys.driver.get("https://www.freecrm.com/index.html");
	}

	@And("^Logs in$")
	public void logs_in() throws InterruptedException {
		Thread.sleep(8000);
		doActionKeys.driver.findElement(By.name("username")).sendKeys("rahulnavgire89");
		doActionKeys.driver.findElement(By.name("password")).sendKeys("Abcd@1234");
		logger.write("User Enters the username and password");
		doActionKeys.capture_Screenshot(logger);
		doActionKeys.driver.findElement(By.xpath("//input[@class='btn btn-small']")).click();
		logger.write("User Logs in to the application");
		doActionKeys.capture_Screenshot(logger);
		//TakeScrnShot(driver);
	}

	@Then("^click on the Deals and new deals$")
	public void click_on_the_Deals_and_new_deals() throws InterruptedException {
		doActionKeys.driver.switchTo().frame("mainpanel");
		Actions a = new Actions(doActionKeys.driver);
		a.moveToElement(doActionKeys.driver.findElement(By.xpath("//a[@title='Deals']"))).build().perform();
		doActionKeys.driver.findElement(By.xpath("//a[@title='New Deal']")).click();
		Thread.sleep(3000);
		logger.write("User Enters the new deal page");
		doActionKeys.capture_Screenshot(logger);
		TakeScrnShot(doActionKeys.driver);
		
	}

	@Then("^fill the details and save it$")
	public void fill_the_details_and_save_it(DataTable input) {
		for (Map<String, String> data : input.asMaps(String.class, String.class)) {
			doActionKeys.driver.findElement(By.id("title")).sendKeys(data.get("Title"));
			doActionKeys.driver.findElement(By.name("client_lookup")).sendKeys(data.get("Company"));
			doActionKeys.driver.findElement(By.name("contact_lookup")).sendKeys(data.get("PrimaryCon"));
			doActionKeys.driver.findElement(By.id("amount")).sendKeys(data.get("Amount"));
			TakeScrnShot(doActionKeys.driver);
			doActionKeys.driver.findElement(By.xpath("//*[@id='prospectForm']/table/tbody/tr[1]/td/input[1]")).click();
			logger.write("Created a deal");
			doActionKeys.capture_Screenshot(logger);
			TakeScrnShot(doActionKeys.driver);
		}

	}

	@Then("^Cilck on the Deals>Products>Enter the below data and create the product$")
	public void cilck_on_the_Deals_Products_Enter_the_below_data_and_create_the_product(DataTable ProductsData)
			throws InterruptedException {
		//Thread.sleep(10000);
		Actions ac = new Actions(doActionKeys.driver);
		ac.moveToElement(doActionKeys.driver.findElement(By.xpath("//a[@title='Deals']"))).build().perform();
		doActionKeys.driver.findElement(By.xpath("//a[@title='Products']")).click();
		logger.write("Entered the products ");
		doActionKeys.capture_Screenshot(logger);
		
		TakeScrnShot(doActionKeys.driver);
		List<WebElement> w = doActionKeys.driver.findElements(By.xpath("//i[@class='fa fa-trash-o']"));
		System.out.println(w.size());

		for (int i = 0; i <= w.size(); i++) {
			w.get(0).click();
			doActionKeys.driver.switchTo().alert().accept();
			Thread.sleep(5000);
			w = doActionKeys.driver.findElements(By.xpath("//i[@class='fa fa-trash-o']"));
		}
		logger.write("Deleted all the produts ");
		doActionKeys.capture_Screenshot(logger);
		TakeScrnShot(doActionKeys.driver);
		doActionKeys.driver.findElement(By.xpath("//input[@value='New Product']")).click();
		for (Map<String, String> Prod : ProductsData.asMaps(String.class, String.class)) {
			doActionKeys.driver.findElement(By.id("name")).clear();
			doActionKeys.driver.findElement(By.id("name")).sendKeys(Prod.get("Name"));

			doActionKeys.driver.findElement(By.id("cost")).clear();
			doActionKeys.driver.findElement(By.id("cost")).sendKeys(Prod.get("Cost"));

			doActionKeys.driver.findElement(By.id("retail_value")).clear();
			doActionKeys.driver.findElement(By.id("retail_value")).sendKeys(Prod.get("Retail_value"));

			doActionKeys.driver.findElement(By.id("wholesale")).clear();
			doActionKeys.driver.findElement(By.id("wholesale")).sendKeys(Prod.get("Wholesale_price"));
			TakeScrnShot(doActionKeys.driver);
			doActionKeys.driver.findElement(By.id("sku")).clear();
			doActionKeys.driver.findElement(By.id("sku")).sendKeys(Prod.get("SKU"));

			doActionKeys.driver.findElement(By.id("inventory_amount")).clear();
			doActionKeys.driver.findElement(By.id("inventory_amount")).sendKeys(Prod.get("Amount"));
			doActionKeys.driver.findElement(By.name("description")).sendKeys(Prod.get("Desc"));
			
			logger.write("Created a product");
			doActionKeys.capture_Screenshot(logger);
			
			TakeScrnShot(doActionKeys.driver);

			// Wait<WebDriver> wait = new FluentWait<WebDriver>(wb)
			// .withTimeout(30, TimeUnit.SECONDS)
			// .pollingEvery(5, TimeUnit.SECONDS)
			// .ignoring(NoSuchElementException.class);
			// WebElement clickseleniumlink = wait.until(new Function<WebDriver,
			// WebElement>(){
			//
			// public WebElement apply(WebDriver driver ) {
			// return
			// driver.findElement(By.xpath("/html/body/div[1]/section/div[2]/div/div[1]/div/div[1]/div/div/div/div[2]/div[2]/div/div/div/div/div[1]/div/div/a/i"));
			// }
			// });
			//
			// clickseleniumlink.click();

			doActionKeys.driver.findElement(By
					.xpath("//input[@id='name']//parent::td//parent::tr//preceding-sibling::tr//input[@value='Save']"))
					.click();
			TakeScrnShot(doActionKeys.driver);
		}

	}

	public static void TakeScrnShot(WebDriver wb) {

		TakesScreenshot tcScr = (TakesScreenshot) wb;
		File scr = tcScr.getScreenshotAs(OutputType.FILE);
		File f = new File("C:\\Users\\navgirah\\Desktop\\ScreenShoot\\" + System.currentTimeMillis() + ".png");
		try {
			FileUtils.copyFile(scr, f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

package stepDefs;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import jdk.internal.org.jline.utils.Log;
import pages.App;
import pages.DriverProvider;

public class IPhoneSearchStepDefs {
	
	WebDriver driver;
	
	public IPhoneSearchStepDefs(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}
	
	@FindBy(name = "q")
    private WebElement searchBox;
	
	@FindBys({
		@FindBy(xpath = "(//div[@class='_1YokD2 _3Mn1Gg'])[2]/div[@class='_1AtVbE col-12-12']")
	})
	private List<WebElement> searchResult;
	
	private By searchResultBy = By.xpath(".//div[@class='_4rR01T']");
	
	@Given("^user is at home page of flipkart website$")
	public void user_is_at_home_page_of_flipkart_website() throws Throwable {
		System.setProperty("webdriver.chrome.driver","./driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.flipkart.com");
		driver.manage().window().maximize();
		Log.info("Home page of flipkart website is displayed");
	}

	@When("^user search for \"([^\"]*)\" in search box$")
	public void user_search_for_in_search_box(String keyword) throws Throwable {
		searchBox.clear();
        searchBox.sendKeys(keyword);
        searchBox.sendKeys(Keys.ENTER);
		Log.info("User has searched for "+searchBox.getText());
		if(searchBox.isDisplayed() && searchBox.getText().equalsIgnoreCase(keyword))
	    	Log.info("Search for "+keyword+" is completed");
	    else
	    	Log.error("Search for "+keyword+" is not completed");
	}

	@Then("^\"([^\"]*)\" are displayed in search result$")
	public void are_displayed_in_search_result(String Keyword) throws Throwable {
		boolean status = false;
		for(WebElement result : searchResult) {
			if(result.findElement(searchResultBy).getText().contains("iPhone")) {
				status = true;
			}
		}
		if(status)
			Log.info(Keyword+ "are displayed in search result");
		else
			Log.error(Keyword+ "are not displayed in search result");
	}

	@Then("^user is able to store the search result in CSV file first sorted by Price then Storage and finally Ratings of the device$")
	public void user_is_able_to_store_the_search_result_in_CSV_file_first_sorted_by_Price_then_Storage_and_finally_Ratings_of_the_device() throws Throwable {
	    
	}

	@Then("^Close the flipkart website$")
	public void close_the_flipkart_website() throws Throwable {
		driver.quit();
	}

}

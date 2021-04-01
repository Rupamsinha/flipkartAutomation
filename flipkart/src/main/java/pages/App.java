package pages;

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

import com.sun.tools.sjavac.Log;

public class App 
{
	WebDriver driver;
	
	public App(WebDriver driver) {
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
	
	public void browserSetup() {

		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.flipkart.com");
		driver.manage().window().maximize();
	}
	
	public boolean search(String keyword) {
		searchBox.clear();
        searchBox.sendKeys(keyword);
        searchBox.sendKeys(Keys.ENTER);
		Log.info("User has searched for "+searchBox.getText());
		return searchBox.isDisplayed() && searchBox.getText().equalsIgnoreCase(keyword);
		}
	
	public boolean searchVerification() {
		boolean status = false;
		for(WebElement result : searchResult) {
			if(result.findElement(searchResultBy).getText().contains("iPhone")) {
				status = true;
			}
		}
		return status;
	}

	public void quit() {
		driver.quit();
		
	}
	
	
}

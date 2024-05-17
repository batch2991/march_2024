package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import base.BasePage;

public class HomePage extends BasePage
{
   
    By searchbox=By.name("q");
    
	public void search(String product)
	{
		driver.findElement(searchbox).sendKeys(product,Keys.ENTER);
		try {Thread.sleep(3000);}catch(Exception e) {}
	}
}

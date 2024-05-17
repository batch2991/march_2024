package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import base.BasePage;

public class SingleProductPage extends BasePage
{
	ArrayList<String> tabs;
	
	By cartbtn=By.xpath("//button[@class='QqFHMw vslbG+ In9uk2']");
	By pname=By.cssSelector("div.gE4Hlh");
	By price=By.cssSelector("div._1Y9Lgu");

	public void addToCart()
	{
		tabs=new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,500)");
		try {Thread.sleep(3000);}catch(Exception e) {}
		driver.findElement(cartbtn).click();
		
		
		try {Thread.sleep(3000);}catch(Exception e) {}
	}
	public List<String> getProductsinCart()
	{
		List<String> seconddata=new ArrayList<String>();
		seconddata.add(driver.findElement(pname).getText());
		seconddata.add(driver.findElement(price).getText());
		driver.close();
		driver.switchTo().window(tabs.get(0));
		return seconddata;
		
	}
}

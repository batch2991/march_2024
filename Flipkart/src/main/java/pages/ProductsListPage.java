package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;


import base.BasePage;

public class ProductsListPage extends BasePage
{
	By heading=By.cssSelector("span.BUOuZu");
	By lowprice=By.xpath("//div[.='Price -- Low to High']");
	By price=By.cssSelector("div.Nx9bqj._4b5DiR");	                           
	By pname=By.cssSelector("div.KzDlHZ");	
	
    public String getTitle()
    {
    	return driver.getTitle().toLowerCase();
    }
    public String getHeading()
    {
    	return driver.findElement(heading).getText();
    }
    public void clickPriceLowToHigh()
    {
    	driver.findElement(lowprice).click();
    	try {Thread.sleep(3000);}catch(Exception e) {}
    }
    public List<Integer> getAllPrices()
    {
    	List<Integer> allprices=driver.findElements(price).stream().map(w->Integer.parseInt(w.getText().substring(1).replace(",",""))).toList();
    	return allprices;
    	
    }
    public List<String> clickOnProduct(int index)
    {
    	List<String> firstdata=new ArrayList<String>();
    	firstdata.add(driver.findElements(pname).get(index).getText());
    	firstdata.add(driver.findElements(price).get(index).getText());   	
    	
    	driver.findElements(pname).get(index).click();
    	try {Thread.sleep(3000);}catch(Exception e) {}
    	return firstdata;
    	
    }
} 

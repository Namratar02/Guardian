package com.example.NewsArticle.PageObject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

public class NewsHomePage {

	WebDriver driver;

	public NewsHomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//ul[@class='pillars']//li[@class='pillars__item']//a[contains(text(),'News')]")
	public WebElement newsLink;

	

	@FindBys({
			@FindBy(xpath = "//h2[text()='Headlines']/./../../following-sibling::div//div[@class='fc-item__header']//a") })
	public List<WebElement> lstOfNews;

	public List<WebElement> getLstOfNews() {
		return lstOfNews;
	}

	public WebElement getNewsLink() {
		return newsLink;
	}
	
	@FindBy(xpath= "//iframe[@title='Iframe title']")
	public WebElement Framelink;

	public WebElement getFramelink() {
		return Framelink;
		
	}
	
    @FindBy(xpath= "//div[@id='notice']//button[ contains(text(),'happy')]")
    public WebElement clickonHappy;

	public WebElement getClickonHappy() {
		return clickonHappy;
	}
    


}

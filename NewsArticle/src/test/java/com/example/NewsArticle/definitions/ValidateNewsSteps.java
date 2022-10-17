package com.example.NewsArticle.definitions;

import java.util.List;
import java.util.concurrent.TimeUnit;

//import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import com.example.NewsArticle.PageObject.GoogleSearchPage;
import com.example.NewsArticle.PageObject.NewsHomePage;
import com.example.NewsArticle.Utility.NewsConstants;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ValidateNewsSteps {

	public WebDriver driver;
	public String newsText = "";
	public NewsHomePage newsPage;
	
	public GoogleSearchPage googlePage;
	

	@Given("launch chrome browser")
	public void launch_chrome_browser() {
		System.setProperty("webdriver.chrome.driver",NewsConstants.chromePath);
		driver = new ChromeDriver();
	}

	@When("opens URl {string}")
	public void opens_URl(String url) throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(NewsConstants.guardianUrl);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		Thread.sleep(3000);
		newsPage = new NewsHomePage(driver);
		
		
		driver.switchTo().frame(newsPage.getFramelink());
		WebElement ele = newsPage.getClickonHappy();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", ele);
	}

	@When("^Click on news button$")
	public void click_on_news_button() {
		
		newsPage.getNewsLink().click();
	}

	@When("get any news from article")
	public void get_any_news_from_article() {

		List<WebElement> lstEle = newsPage.getLstOfNews();
		for (WebElement ele : lstEle) {
			newsText = ele.getText();
			System.out.println(newsText);
			break;
		}
	}

	@Then("Go to google and verify News article exists or not")
	public void go_to_google_and_verify_News_article_exists_or_not() throws InterruptedException {
		googlePage = new GoogleSearchPage(driver);
		driver.get(NewsConstants.resourceUrl);
		Thread.sleep(3000);
		boolean newsExists;
		WebElement ele = googlePage.getSearchTextBox();
		ele.sendKeys(newsText);
		ele.sendKeys(Keys.ENTER);

		String ActualNewsText = googlePage.getSearchNewsItem().getText();
		
		try {
			newsExists = true;
			WebElement guardianNewsElmt = googlePage.getSearchNewsItem();
		} catch (Exception e) {
			newsExists = false;

		}
		Assert.assertTrue(newsExists = true, "News not valid");
	}

	@Then("^close browser$")
	public void close_browser() {
		driver.close();
	}

	@When("open Url {string}")
	public void open_Url(String string) {
		driver.get(NewsConstants.resourceUrl);
	}

	@Then("search for incorrect news")
	public void search_for_incorrect_news() {
		googlePage = new GoogleSearchPage(driver);
		WebElement ele = googlePage.getSearchTextBox();
		ele.sendKeys("abc");
		ele.sendKeys(Keys.ENTER);
	}

	@Then("verify News article does not exists")
	public void verify_News_article_does_not_exists() {
		boolean newsExists;


		try {
			newsExists = true;
			String ActualNewsText = googlePage.getSearchNewsItem().getText();
			WebElement guardianNewsElmt = googlePage.getSearchNewsItem();
		} catch (Exception e) {
			newsExists = false;

		}
		Assert.assertFalse(newsExists = false, "test case failed ");
	}

}

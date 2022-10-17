package com.example.NewsArticle.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features", glue = {
		"com.example.NewsArticle.definitions" }, tags = "@happyPath , @Negative"

)
public class NewsArticleRunner extends AbstractTestNGCucumberTests {

}

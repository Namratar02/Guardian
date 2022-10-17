
Feature: News Validation
@happyPath
Scenario: Validate weather the News exist or not
Given launch chrome browser
When opens URl "Gaurdian URL"
And Click on news button
And get any news from article
Then Go to google and verify News article exists or not
And close browser



@Negative
Scenario: News from guardian URL is not present in google search when searched incorrectly
Given launch chrome browser
When  opens URl "Gaurdian URL"
And   Click on news button
And   get any news from article
When  open Url "google"
Then  search for incorrect news
Then  verify News article does not exists
And   close browser
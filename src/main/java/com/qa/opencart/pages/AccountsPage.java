package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstans;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class AccountsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil; 
	
	private By logoutLink = By.linkText("Logout");
	private By search = By.name("search");
	private By searchButton = By.cssSelector("div#search button");
	private By accSecHeaders = By.xpath("(//div[@id='content'])//h2");
	
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	
	@Step("getAccountPageTitle")
	public String getAccountPageTitle() {
		String title = eleUtil.waitForTitleIs(AppConstans.DEFAULT_TIME_OUT, AppConstans.ACCOUNT_PAGE_TITLE);	
		System.out.println("account page title :" + title);
		return title;
	}
	
	@Step("getAccountPageURL")
	public boolean getAccountPageUrl() {
		eleUtil.waitForUrlContains(AppConstans.DEFAULT_LARGE_TIME_OUT, AppConstans.ACCOUNT_PAGE_URL_PARAM);
		String url = driver.getCurrentUrl();
		System.out.println("account page url: " + url);
		if(url.contains(AppConstans.ACCOUNT_PAGE_URL_PARAM)) {
			return true;
		}
		return false;
	}
	
	@Step("isLogoutLinkExist..")
	public boolean isLogoutLinkExist() {
		return eleUtil.doEleIsDisplayed(logoutLink);	
	}			
	
	@Step("isSearchExist..")
	public boolean isSearchExist() {
		return eleUtil.doEleIsDisplayed(search);
	}
	
	@Step("perform Search.. {0}")
	public SearchResultsPage performSearch(String productKey) {
		System.out.println("product key is :" + productKey);
		if(isSearchExist()) {
			eleUtil.doSendKeys(search, productKey);
			eleUtil.doClick(searchButton);
			return new SearchResultsPage(driver);
		}
		else {
			System.out.println("search field is not present on the page ...");
			return null;
		}
	
	}	
	@Step("getAccSecHaeders..")
	public ArrayList<String> getAccSecHaeders() {
		List<WebElement> secList = eleUtil.waitForElementsToBeVisible(accSecHeaders, AppConstans.DEFAULT_LARGE_TIME_OUT);
	    System.out.println("Total Sections Headers : " + secList.size());
		ArrayList<String> accSecTextList = new ArrayList<>();
		for(WebElement e : secList) {
			String text = e.getText();
			accSecTextList.add(text);
		}
		return accSecTextList;
		
		
	}
	
	
	 

}

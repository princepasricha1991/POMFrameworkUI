package com.qa.opencart.tests;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstans;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic - 200: Open Cart Application account page design")
@Story("user story-201: design account page features")
public class AccountsPageTest extends BaseTest {
	
	@BeforeClass
	public void accSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Description("acc Page Title Test -- Dev Name :@ prince")
	@Severity(SeverityLevel.MINOR)
	@Test(priority=1)
	public void accPageTitleTest() {
		String actAccPageTitle = accPage.getAccountPageTitle();
		Assert.assertEquals(actAccPageTitle, AppConstans.ACCOUNT_PAGE_TITLE);
	}
	
	@Description("acc Page Url Test -- Dev Name :@ prince")
	@Severity(SeverityLevel.MINOR)
	@Test(priority=2)
	public void accPageUrlTest() {
		Assert.assertTrue(accPage.getAccountPageUrl());
	}
	
	@Description("acc Page search bar Test -- Dev Name :@ prince")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=3)
	public void searchExistTest() {
		Assert.assertTrue(accPage.isSearchExist());
	}
	
	@Description("acc Page logout link Test -- Dev Name :@ prince")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=4)
	public void logoutExistTest() {
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}
	
	@Description("acc Page header Test -- Dev Name :@ prince")
	@Severity(SeverityLevel.TRIVIAL)
	@Test(priority=5)
	public void accPageHeadersTest() {
		ArrayList<String>  actHeadersList = accPage.getAccSecHaeders();
		System.out.println("Actual header is: " + actHeadersList);
		Assert.assertEquals(actHeadersList, AppConstans.ACC_PAGE_SECTION_HEADER);
	}
	
	@DataProvider
	public Object[][] getProductKey() {
		return new Object[][] {
			{ "Macbook" },			
			{ "iMac" },
			{ "Samsung" }	
			};
	}
	
	@Description("acc Page search Test -- Dev Name :@ prince")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=6, dataProvider = "getProductKey")
	public void searchCheckTest(String productKey) {
		seacrhResultsPage = accPage.performSearch(productKey);
		Assert.assertTrue(seacrhResultsPage.isSearchSuccessful());
	}
	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] {
			{ "Macbook","MacBook Pro" },
			{ "Macbook","MacBook Air" },
			{ "iMac","iMac" },
			{ "Samsung","Samsung SyncMaster 941BW" },
			{ "Samsung","Samsung Galaxy Tab 10.1" }
			};	
	}
	
	@Description("acc Page product search Test -- Dev Name :@ prince")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority=7, dataProvider = "getProductData")
	public void searchTest(String searchKey, String mainProductName) {
		seacrhResultsPage = accPage.performSearch(searchKey);
		if(seacrhResultsPage.isSearchSuccessful()) {
			productInfoPage = seacrhResultsPage.selectProduct(mainProductName);
			String actPoductHeader = productInfoPage.getProductHeader(mainProductName);
			Assert.assertEquals(actPoductHeader, mainProductName);
			
		}
	}
	
	

}

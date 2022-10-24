package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstans;

public class ProductPageTest extends BaseTest{
	
	@BeforeClass
	public void prodouctInfoSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@DataProvider
	public Object[][] getProductHeaderData() {
		return new Object[][] {
			{ "Macbook","MacBook Pro" },
			{ "Macbook","MacBook Air" },
			{ "iMac","iMac" },
			{ "Samsung","Samsung SyncMaster 941BW" },
			{ "Samsung","Samsung Galaxy Tab 10.1" }
			};	
	}
	
	@Test(dataProvider = "getProductHeaderData")
	public void productHeaderTest(String searchKey, String mainProdName) {
		seacrhResultsPage = accPage.performSearch(searchKey);
		productInfoPage = seacrhResultsPage.selectProduct(mainProdName);
		String accProdHeader = productInfoPage.getProductHeader(mainProdName);
		Assert.assertEquals(accProdHeader, mainProdName);		
	}
	
	@DataProvider
	public Object[][] getProductInfoData() {
		return new Object[][] {
			{ "Macbook","MacBook Pro" , AppConstans.MACBOOK_PRO_IMAGES_COUNT },
			{ "Macbook","MacBook Air" ,AppConstans.MACBOOK_AIR_IMAGES_COUNT },
			{ "iMac","iMac" , AppConstans.IMAC_IMAGES_COUNT},
			};	
	}
	
	@Test(dataProvider = "getProductInfoData")
	public void productImagesCountTest(String searchKey, String mainSearchProduct, int imagesCount) {
		seacrhResultsPage = accPage.performSearch(searchKey);
		productInfoPage = seacrhResultsPage.selectProduct(mainSearchProduct);
		int actProductImages = productInfoPage.getProductCount();
		System.out.println("actuak product images :" + actProductImages);
		Assert.assertEquals(actProductImages, imagesCount);		
	}
	
	@Test
	public void productMetaDatTest() {
		seacrhResultsPage = accPage.performSearch("MacBook");
		productInfoPage = seacrhResultsPage.selectProduct("MacBook Pro");
		Map<String, String> actMetaDataMap = productInfoPage.getProductMetaData();
		Assert.assertEquals(actMetaDataMap.get("Brand"), "Apple");
		Assert.assertEquals(actMetaDataMap.get("Product Code"), "Product 18");
		Assert.assertEquals(actMetaDataMap.get("Reward Points"), "800");
		Assert.assertEquals(actMetaDataMap.get("Availability"), "In Stock");	
	}
}

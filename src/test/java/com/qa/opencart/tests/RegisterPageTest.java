package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstans;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterPageTest extends BaseTest{

	@BeforeClass
	public void regSetup() {
		registerPage = loginPage.navigateToRegisterPage();		
		
	}
	
	@DataProvider
	public Object[][] getRegTestData() {
		Object regData [][] = ExcelUtil.getTestData(AppConstans.REGISTER_SHEET_NAME);
		return regData;
	}
	
	public String getRandomEmail() {
		Random random = new Random();
		String email = "automationTestPrince"+ random.nextInt(10000)+"@gmail.com";
		return email;
	}
	
	@Test(dataProvider = "getRegTestData")
	public void registerUserTest(String fisrtName, String lastName, String telephone, 
																String password ,String subscribe) {
		String actSuccMesg = registerPage.userRegister(fisrtName, lastName, getRandomEmail(), telephone, password , subscribe);
		Assert.assertEquals(actSuccMesg, AppConstans.ACC_CREATE_SUCCESS_MESSAGE);
	}
	
	
	
	
}

package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstans;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmPassword = By.id("input-confirm");
	
	private By subscribeYes = By.xpath("//label[@class='radio-inline']/input[@value='1' and @type= 'radio']");
	private By subscribeNo = By.xpath("//label[@class='radio-inline']/input[@value='0' and @type= 'radio']");
	private By agreeCheckBox = By.name("agree");
	private By continueLink = By.cssSelector("input.btn-primary");
	
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");
	private By registerSuccessMesg = By.cssSelector("div#content h1");
	
	
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	/**
	 * 
	 * @param fisrtName
	 * @param lastName
	 * @param email
	 * @param telephone
	 * @param password
	 * @param subscribe
	 * @return
	 */
	
	public String userRegister(String fisrtName, String lastName, String email, 
			String telephone, String password ,String subscribe  ) {
		
		eleUtil.doSendKeysWithWait(this.firstName, AppConstans.DEFAULT_LARGE_TIME_OUT , fisrtName);
		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(this.telephone, telephone);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(this.confirmPassword, password);
		
		if(subscribe.equalsIgnoreCase("Yes")) {
			eleUtil.doClick(this.subscribeYes);
		}
		else {
			eleUtil.doClick(this.subscribeNo);
		}
		
		eleUtil.doClick(this.agreeCheckBox);
		eleUtil.doClick(this.continueLink);
		
		String succMsg = eleUtil.waitForElementVisible(registerSuccessMesg, AppConstans.DEFAULT_LARGE_TIME_OUT).getText();
		System.out.println(" Success mesggg :>  " + succMsg);
		
		eleUtil.doClick(logoutLink);
		eleUtil.doClick(registerLink);
		
		return succMsg;
		
		
		 
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	

}

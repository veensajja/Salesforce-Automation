package com.selenium.test;


import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.salesforce.Base.BaseReusable;
import com.salesforce.utility.CommonUtilities;


public class SalesForceAutomation extends BaseReusable 
{
	protected static String userName = CommonUtilities.getApplicationProperty("username");
	protected static String password = CommonUtilities.getApplicationProperty("passwordname");
	protected static String usermenu = CommonUtilities.getApplicationProperty("usermenuname");
	static SoftAssert softassert = new SoftAssert();
	
	public static void login(String userName,String password) {
		WebElement email= driver.findElement(By.xpath("//input[@id='username']"));
		waitUntilVisible(email);
		enterText(email, userName);
		WebElement passwordElement=driver.findElement(By.xpath("//input[@id='password']"));
		enterText(passwordElement, password);
		WebElement login=driver.findElement(By.xpath("//input[@id='Login']"));
		clickElement(login);
		WebElement dialog = driver.findElement(By.className("dialogClose"));
		clickElement(dialog);
		}
	
	@Test(enabled=true)
	public static void SalesLoginFull() throws IOException, InterruptedException
	{
		try {
			
			System.out.println("Login to SalesForce Test Case-2");
		setUp();
		login(userName,password);
		getTitle("Salesforce");
		Logout();
		tearDown();	
		System.out.println("Pass: Login to SalesForce Test Case -2");
		} catch (Exception e)
		{
			System.out.println("Fail: Login to SalesForce Test Case -2");
			
		}
		
		
	}
	@Test(enabled=true)
	public static void RememberMe() throws InterruptedException, IOException
	{
		try {
			
			System.out.println("Start of Check Remember Me Test Case-3");
		setUp();
		WebElement username = driver.findElement(By.xpath("//input[@id='username']"));
		clearElement(username);
		enterText(username, userName);
		WebElement pwd = driver.findElement(By.xpath("//input[@id='password']"));
		clearElement(pwd);
		enterText(pwd, password);
		WebElement rem = driver.findElement(By.xpath("//input[@id='rememberUn']"));
		clickElement(rem);
		WebElement loginButton = driver.findElement(By.xpath("//input[@id='Login']"));
		clickElement(loginButton);	
		WebElement dialog = driver.findElement(By.className("dialogClose"));
		clickElement(dialog);
		getTitle("Salesforce");
		WebElement userMenu = driver.findElement(By.xpath("//span[@id='userNavLabel']"));
		mouseOverClick(userMenu);
		WebElement logout = driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));    
		clickElement(logout);
		WebElement rem1 = driver.findElement(By.xpath("//input[@id='rememberUn']"));
		if(rem1.isSelected())
			Assert.assertTrue(true,"Remember Me box is checked");
			//System.out.println("Remember Me box is checked");
		else
			Assert.assertTrue(false,"Remember Me box is not checked");
			//System.out.println("Remember Me box is not checked");				
		WebElement username1 = driver.findElement(By.xpath("//span[@id='idcard-identity']"));
		assertText(username1,"veensajja-hpwj@force.com");	
		tearDown();	
		System.out.println("Pass: Remember Me Test Case -3");
		} catch (Exception e)
		{
			System.out.println("Fail: Remember Me Test Case -3");
		}
	}
	
	@Test(enabled=true)
	public static void ForgotPass() throws InterruptedException, IOException
	{
		try {
			
			System.out.println("Start of Forgot Password Test Case-4A");
		setUp();
		WebElement forgot = driver.findElement(By.xpath("//a[@id='forgot_password_link']"));
		clickElement(forgot);
		WebElement forgotPage = driver.findElement(By.xpath("//h2[@id='header']"));
		waitUntilVisible(forgotPage);
		if(forgotPage.getText().equalsIgnoreCase("Forgot Your Password"))
			System.out.println("Forgot Your Password Page is loaded");
		else
			System.out.println("Forgot Your Password Page is not loaded");	
		WebElement username = driver.findElement(By.xpath("//input[@id='un']\n"));
		enterText(username, userName);	
		WebElement cont = driver.findElement(By.xpath("//input[@id='continue']"));
		clickElement(cont);
		WebElement reset = driver.findElement(By.xpath("//h2[@id='header']\n"));
		assertText(reset,"Check Your Email");
		tearDown();	
		System.out.println("Pass: Forgot Password Test Case -4A");
		} catch (Exception e)
		{
			System.out.println("Fail: Forgot Password Test Case -4A");
			
		}
	}
	
	@Test(enabled=true)
	public static void LoginAttemptFail()
	{
		try {
		setUp();	
		System.out.println("Start of Forgot Password Test Case-4B");
		WebElement username = driver.findElement(By.xpath("//input[@id='username']"));
		clearElement(username);
		enterText(username,"123");
		WebElement pwd = driver.findElement(By.xpath("//input[@id='password']"));
		clearElement(pwd);
		enterText(pwd,"22131");
		WebElement loginButton = driver.findElement(By.xpath("//input[@id='Login']"));
		clickElement(loginButton);
		WebElement wrong = driver.findElement(By.xpath("//div[@id='error']"));
		assertText(wrong,"Please check your username and password. If you still can't log in, contact your Salesforce administrator.");
		tearDown();	
		System.out.println("Pass: Forgot Password Test Case -4B");
		} catch (Exception e)
		{
			System.out.println("Fail: Forgot Password Test Case -4B");
			
		}
	}
	
	@Test(enabled=true)
	public static void UserMenu() throws InterruptedException, IOException
	{
		try {
			
			System.out.println("Start of User Menu Test Case -05");
			setUp();
			login(userName,password);
			
		WebElement userMenu = driver.findElement(By.xpath("//span[@id='userNavLabel']"));
		waitUntilVisible(userMenu);
		mouseOverClick(userMenu);
		if(userMenu.getText().contains(usermenu))
			System.out.println("Usermenu is dispalyed");
		else
			System.out.println("Usermenu is not dispalyed");
		dropDownElements("//div[@id='userNavMenu']");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[@id='userNavLabel']")).click();
		Logout();
		tearDown();	
		System.out.println("Pass: User Menu Test Case -05");
		} catch (Exception e)
		{
			System.out.println("Fail: User Menu Test Case -05");
			
		}
	}
	
	public static void HomeMenu() throws InterruptedException, IOException
	{
		//Testcase 5
		WebElement userMenu = driver.findElement(By.xpath("//span[@id='userNavLabel']"));
		waitUntilVisible(userMenu);
		mouseOverClick(userMenu);
		clickElement(userMenu);
		if(userMenu.getText().contains(usermenu))
			System.out.println("Usermenu is dispalyed");
		else
			System.out.println("Usermenu is not dispalyed");
		//String[] exp = {"My Profile","My Settings","Developer Console","Switch to Lightning Experience","Logout"};

		driver.findElement(By.xpath("//span[@id='userNavLabel']")).click();
	}
	
	public static void ProfileLastNameChange(String last) throws InterruptedException, IOException
	{
		WebElement prof = driver.findElement(By.xpath("//a[contains(text(),'My Profile')]"));    
		clickElement(prof);
		WebElement About = driver.findElement(By.xpath("//tbody/tr[1]/td[1]/div[1]/div[2]/li[1]/div[1]/div[1]/a[1]"));
		waitUntilVisible(About);
		pageLoad(About,"About Me");
		WebElement edit = driver.findElement(By.xpath("//tbody/tr[1]/td[1]/div[1]/div[2]/div[2]/div[1]/h3[1]/div[1]/div[1]/a[1]/img[1]"));
		waitUntilVisible(edit);
		Thread.sleep(1000);
		clickElement(edit);
		WebElement profDiag = driver.findElement(By.xpath("//h2[@id='contactInfoTitle']"));
		dialogBoxCheck(profDiag,"Edit Profile");
		WebElement editProfileFrame = driver.findElement(By.xpath("//*[@id='contactInfoContentId']"));
		frameElementSwitch(editProfileFrame);
		WebElement contact = driver.findElement(By.xpath("//a[contains(text(),'Contact')]"));
		enableCheck(contact);
		WebElement about = driver.findElement(By.xpath("//a[contains(text(),'About')]"));
		enableCheck(about);
		clickElement(about);
		WebElement lastname = driver.findElement(By.xpath("//input[@id='lastName']"));
		clearElement(lastname);
		enterText(lastname,last);
		WebElement save = driver.findElement(By.xpath("//body/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/input[1]"));
		clickElement(save);
		WebElement profDiagcheck = driver.findElement(By.xpath("//h2[@id='contactInfoTitle']"));
		dialogBoxCheck(profDiagcheck,"Edit Profile");
		WebElement changeUsername = driver.findElement(By.xpath("//span[@id='tailBreadcrumbNode']"));
		waitUntilVisible(changeUsername);
		textCheck(changeUsername,last);
	
	}
	@Test(enabled=true)
	public static void MyProfile() throws InterruptedException, AWTException, IOException
	{
		try {
			
			System.out.println("Start of My Profile Test Case -06");
			setUp();
			login(userName,password);
			HomeMenu();
			ProfileLastNameChange("last");
		WebElement post = driver.findElement(By.xpath("//a[@id='publisherAttachTextPost']"));	
		clickElement(post);
		frameIndexSwitch(0);
		WebElement postFrameText = driver.findElement(By.xpath("//body"));
		waitUntilVisible(postFrameText);
		enterText(postFrameText,"Waste text");
		driver.switchTo().defaultContent();
		WebElement shareAll = driver.findElement(By.xpath("//input[@id='publishersharebutton']"));
		clickElement(shareAll);
		WebElement postText = driver.findElement(By.xpath("//span/p"));
		waitUntilVisible(postText);
		textCheck(postText,"Waste");
		Thread.sleep(1000);
		WebElement file = driver.findElement(By.xpath("//span[normalize-space()='File']"));
		clickElement(file);
		WebElement uploadButton = driver.findElement(By.xpath("//a[@id='chatterUploadFileAction']"));
		waitUntilVisible(uploadButton);
		jsExecutor(uploadButton);
		WebElement chooseFile = driver.findElement(By.xpath("//input[@id='chatterFile']"));
		mouseOver(chooseFile);
		chooseFile.sendKeys("/Users/praveenastandard/Downloads/1.jpg");
		//driver.findElement(By.id("j_id0:uploadFileForm:uploadBtn")).click()
		WebElement share = driver.findElement(By.xpath("//input[@id='publishersharebutton']"));
		clickElement(share);
		WebElement ImageFile = driver.findElement(By.xpath("//span[contains(text(),'Download')]"));
		uploadCheck(ImageFile);
		Thread.sleep(3000);
		WebElement addPhoto1 = driver.findElement(By.xpath("//span[@id='displayBadge']"));
		mouseOver(addPhoto1);
		WebElement addPhoto2 = driver.findElement(By.xpath("//a[@id='uploadLink']"));
		mouseOverClick(addPhoto2);
		driver.switchTo().frame(1);
		WebElement photoUpload = driver.findElement(By.xpath("//input[@id='j_id0:uploadFileForm:uploadInputFile']"));
		waitUntilVisible(photoUpload);
		mouseOver(photoUpload);
		photoUpload.sendKeys("/Users/praveenastandard/Downloads/1.jpg");
		WebElement savePhoto = driver.findElement(By.id("j_id0:uploadFileForm:uploadBtn"));
		waitUntilVisible(savePhoto);
		clickElement(savePhoto);
		//Thread.sleep(1000);
		WebElement savePhoto1 = driver.findElement(By.xpath("//input[@id='j_id0:j_id7:save']"));
		savePhoto1.click();
		Thread.sleep(2000);
		WebElement image = driver.findElement(By.xpath("//span[@class='profileImage chatter-avatarFull chatter-avatar']//img[@title='praveena last']"));
		String imgVerify = image.getAttribute("src");
		checkImgAttribute(imgVerify,"7298");
		
		Logout();
		closeAll();	
		System.out.println("Pass: My Profile Test Case -06");
		} catch (Exception e)
		{
			System.out.println("Fail: My Profile Test Case -06");
			
		}
		
	}
	
	@Test(enabled=true)
	public static void MySettings() throws InterruptedException, IOException
	{
		try {
	
		System.out.println("Start of My Settings Test Case -07");
		setUp();
		login(userName,password);
		HomeMenu();
		WebElement prof = driver.findElement(By.xpath("//a[contains(text(),'My Settings')]"));    
		clickElement(prof);
		WebElement mySettings = driver.findElement(By.xpath("//span[contains(text(),'My Settings')]"));
		pageLoad(mySettings,"My Settings");
		WebElement personal = driver.findElement(By.xpath("//span[@id='PersonalInfo_font']"));
		waitUntilVisible(personal);
		clickElement(personal);
		WebElement loginhistory = driver.findElement(By.xpath("//span[contains(text(),'Login History')]"));
		waitUntilVisible(loginhistory);
		clickElement(loginhistory);
		WebElement downloadformat = driver.findElement(By.xpath("//a[(contains(text(),'Download login history'))and(contains(text(),'csv'))]"));
		clickElement(downloadformat);
		if(downloadformat.isDisplayed())
			System.out.println("Downlaod Successin csv format");
		else
			System.out.println("Downlaod Fail");
		WebElement displayformat = driver.findElement(By.xpath("//span[@id='DisplayAndLayout_font']"));
		clickElement(displayformat);
		WebElement customizetab = driver.findElement(By.xpath("//span[contains(text(),'Customize My Tabs')]"));
		clickElement(customizetab);
		WebElement customappdrop = driver.findElement(By.xpath("//select[@id='p4']"));
		mouseOverClick(customappdrop);
		Select select = new Select(customappdrop);
		select.selectByVisibleText("Salesforce Chatter");
		Thread.sleep(1000);
		
		List <WebElement> availableoptions= driver.findElements(By.xpath("//select[@id='duel_select_0']/option"));
		for(WebElement option:availableoptions)
		{
			if(option.getText().equalsIgnoreCase("Reports"))
			{
				mouseOverClick(option);
				WebElement add = driver.findElement(By.xpath("//img[@title='Add']"));
				clickElement(add);
				break;
			}
		}
		
		List <WebElement> selectedoptions = driver.findElements(By.xpath("//select[@id='duel_select_1']"));
		for(WebElement option:selectedoptions)
		{
			if(option.getText().contains("Reports"))
			{
				mouseOverClick(option);
				System.out.println("Reports have been moved to Selected Tab");
				break;
			}
		}
		
		WebElement save = driver.findElement(By.xpath("//input[@title='Save']"));
		clickElement(save);
		WebElement saleschatterreports = driver.findElement(By.xpath("//li[@id='Chatter_Tab']"));
		if(saleschatterreports.isDisplayed())
			System.out.println("Reports is displayed in Page");
		else
			System.out.println("Reports is not displayed in Page");
		
		WebElement email = driver.findElement(By.xpath("//span[@id='EmailSetup_font']"));
		clickElement(email);
		WebElement emailsettings = driver.findElement(By.xpath("//span[@id='EmailSettings_font']"));
		clickElement(emailsettings);
		WebElement emailsign = driver.findElement(By.xpath("//textarea[@id='signature']"));
		enterText(emailsign,"xyz");
		WebElement savebutton = driver.findElement(By.xpath("//input[@title='Save']"));
		clickElement(savebutton);
		WebElement savemsg = driver.findElement(By.xpath("//div[@id='meSaveCompleteMessage']"));
		if(savemsg.isDisplayed())
			System.out.println("Email settings have been saved");
		else
			System.out.println("Email settings have not been saved");
		WebElement calendar = driver.findElement(By.xpath("//span[@id='CalendarAndReminders_font']"));
		clickElement(calendar);
		WebElement reminder = driver.findElement(By.xpath("//span[@id='Reminders_font']"));
		clickElement(reminder);
		WebElement testreminder = driver.findElement(By.xpath("//input[@id='testbtn']"));
		clickElement(testreminder);
		Thread.sleep(5000);
		String currentwindow=driver.getWindowHandle();
		Set <String> windows =driver.getWindowHandles();
		
		for(String window:windows)
		{
			if(!window.equals(currentwindow))
			{
				driver.switchTo().window(window);
				System.out.println("Reminder window opened");
				WebElement dismissall = driver.findElement(By.xpath("//input[@id='dismiss_all']"));
				waitUntilVisible(dismissall);
				clickElement(dismissall);
				
			}
		}
		driver.switchTo().window(currentwindow);
		Logout();
		tearDown();	
		System.out.println("Pass: My Settings Test Case -07");
		} catch (Exception e)
		{
			System.out.println("Fail: My Settings Test Case -07");
			
		}
	}
	
	@Test(enabled=true)
	public static void DeveloperConsole() throws IOException, InterruptedException
	{
		try {
			System.out.println("Start of Developer Console Test Case -08");
			setUp();
			login(userName,password);
		HomeMenu();
		String currentwindow=driver.getWindowHandle();
		WebElement devoption = driver.findElement(By.xpath("//a[contains(text(),'Developer ')]"));    
		clickElement(devoption);
		
		String currentWindow = driver.getWindowHandle();
		Set<String> windows=driver.getWindowHandles();
		for(String window:windows)
		{
			if(!window.equals(currentWindow))
			{
				driver.switchTo().window(window);
				Thread.sleep(1000);
				WebElement log = driver.findElement(By.xpath("//span[contains(text(),'Logs')]"));
				waitUntilVisible(log);
				if(log.isDisplayed())
					System.out.println("Dev Console window is opened");
				else
					System.out.println("Dev Console page is not opened");
			
				driver.close();
			}
		}
		driver.switchTo().window(currentWindow);
		Thread.sleep(1000);
		Logout();
		closeAll();
		System.out.println("Pass: Developer Console Test Case -08");
		} catch (Exception e)
		{
			System.out.println("Fail: Developer Console Test Case -08");
			
		}
		
	
	}
	
	public static void Account()
	{
		WebElement accounts = driver.findElement(By.cssSelector("a[title='Accounts Tab']"));
		clickElement(accounts);
		WebElement accttitle = driver.findElement(By.xpath("//h1[contains(text(),'Accounts')]"));
		pageLoad(accttitle,"Accounts");
	}
	
	@Test(enabled=true)
	public static void CreateAccount() 
	{
		try {
			System.out.println("Start of Create Account Test Case -10");
			setUp();
			login(userName,password);
			HomeMenu();
			Account();
			WebElement newAcc = driver.findElement(By.xpath("//input[@title='New']"));
			clickElement(newAcc);
			WebElement newacctext = driver.findElement(By.xpath("//input[@id='acc2']"));
			enterText(newacctext,"AcctTesting");
			WebElement save = driver.findElement(By.xpath("//td[@id='topButtonRow']//input[@title='Save']"));
			clickElement(save);
			WebElement newacctitle = driver.findElement(By.xpath("//h2[contains(@class,'topName')]"));
			assertText(newacctitle,"AcctTesting");
			Logout();
			closeAll();
			System.out.println("Pass: Create Account Test Case -10");
		} catch (Exception e)
		{
			System.out.println("Fail: Create Account Test Case -10");
			
		}		
			
	}
	
	@Test(enabled=true)
	public static void CreateNewView()  
	{
		try {
			System.out.println("Start of Create New View Test Case -11");
			setUp();
			login(userName,password);
			HomeMenu();
			Account();
			WebElement newview = driver.findElement(By.xpath("//a[contains(text(),'Create New View')]"));
			clickElement(newview);
			WebElement viewname = driver.findElement(By.xpath("//input[@id='fname']"));
			enterText(viewname,"viewtest"+getcurrenttime());
			WebElement unviewname = driver.findElement(By.xpath("//input[@id='devname']"));
			enterText(unviewname,"viewuntest");
			WebElement save = driver.findElement(By.xpath("(//input[@title='Save'])[1]"));
			clickElement(save);
			WebElement viewmenu = driver.findElement(By.xpath("//select[@id='is:islv:inlineSchedulerListView:enhancedList_listSelect']"));
			String view = viewmenu.getText();
			assertTrue(view.contains("viewtest"),"View Test page opened");
			Logout();
			closeAll();
			System.out.println("Pass: Create New View Test Case -11");
		} catch (Exception e)
		{
			System.out.println("Fail: Create New View Test Case -10");
			
		}
	}
	
	@Test(enabled=true)
	public static void EditView() throws IOException, InterruptedException  
	{
		try {
			System.out.println("Start of Edit View Test Case -12");
			setUp();
			login(userName,password);
			HomeMenu();
			Account();
			
			WebElement viewmenu = driver.findElement(By.xpath("//select[@id='fcf']"));
			Select select = new Select(viewmenu);
			String expectedViewName = "viewtest";
			List<WebElement> allViewList = select.getOptions();
			for (WebElement option : allViewList) {
			    String currentViewName = option.getText();
			    if (currentViewName.contains(expectedViewName)) {
			        select.selectByVisibleText(currentViewName);
			        WebElement editview = driver.findElement(By.xpath("//a[normalize-space()='Edit']"));
					clickElement(editview);
					break;
			    }
			}
			WebElement editview = driver.findElement(By.xpath("//h2[contains(text(),'Edit View')]"));
			Assert.assertEquals(true, editview.isDisplayed(),"Edit View Page loaded");
			
			WebElement viewname = driver.findElement(By.xpath("//input[@id='fname']"));
			clearElement(viewname);
			enterText(viewname,"viewtesting");
			
			WebElement field = driver.findElement(By.xpath("//select[@id='fcol1']"));
			clickElement(field);
			Select selectacc = new Select(field);
			selectacc.selectByValue("ACCOUNT.NAME");
			clickElement(field);
			WebElement operator = driver.findElement(By.xpath("//select[@id='fop1']"));
			Select selecopp = new Select(operator);
			String expectedOperator = "c";
			List<WebElement> allOpList = selecopp.getOptions();
			for (WebElement option : allOpList) {
			    String currentOpName = option.getText();
			    if (currentOpName.contains(expectedOperator)) {
			        selecopp.selectByVisibleText(currentOpName);
			        break;
			    }
			}
			WebElement value = driver.findElement(By.xpath("//input[@id='fval1']"));
			enterText(value,"a");
			WebElement availfield = driver.findElement(By.xpath("//select[@id='colselector_select_0']"));
			Select selectfield = new Select(availfield);
			selectfield.selectByValue("ACCOUNT.LAST_ACTIVITY");
			WebElement add = driver.findElement(By.xpath("//img[@title='Add']"));
			clickElement(add);
			WebElement save = driver.findElement(By.xpath("(//input[@title='Save'])[2]"));
			clickElement(save);
			WebElement newview = driver.findElement(By.xpath("//select[@id='is:islv:inlineSchedulerListView:enhancedList_listSelect']"));
			assertTrue(newview.getText().contains("viewtesting"),"New view data");
			WebElement lastact = driver.findElement(By.xpath("//div[@title='Last Activity']"));
			Assert.assertEquals(true, lastact.isDisplayed(),"Last activity is displayed");
			
			
			List<WebElement> AcctList=driver.findElements(By.xpath("//table[@class=\"x-grid3-row-table\"]/tbody/tr/td[4]"));
			
			for(WebElement acc:AcctList)
			{
				System.out.println(acc.getText());
				if(acc.getText().contains("a"))
				{
					System.out.print("Accounts with 'a' are displayed");
					break;
				}
			}
			Logout();
			closeAll();
			System.out.println("Pass: Edit View Test Case -12");
		} catch (Exception e)
		{
			System.out.println("Fail: Create New View Test Case -10");
			
		}
	}
	
	@Test(enabled=true)
	public static void MergeAccounts() throws IOException, InterruptedException  
	{
		try {
			System.out.println("Start of Merge Accounts Test Case -13");
			setUp();
			login(userName,password);
			HomeMenu();
			Account();
			
			WebElement mergeacc = driver.findElement(By.xpath("//a[contains(text(),'Merge Accounts')]"));
			clickElement(mergeacc);
			WebElement searchbox = driver.findElement(By.xpath("//input[@id='srch']"));
			enterText(searchbox, "Acct");
			clickElement(searchbox);
			WebElement findacc = driver.findElement(By.cssSelector("input[title='Find Accounts'][value='Find Accounts']"));
			clickElement(findacc);
			WebElement accrow = driver.findElement(By.xpath("//th[@scope=\"row\"]"));
			System.out.println(accrow.getSize());

			if(!(accrow.getSize().equals(0)))
			{
				driver.findElement(By.xpath("//input[@title=\"Select row 0\"]")).click();
				driver.findElement(By.xpath("//input[@title=\"Select row 1\"]")).click();
				WebElement next = driver.findElement(By.xpath("//div[contains(@class,'pbBottomButtons')]//input[contains(@title,'Next')]"));
				clickElement(next);
				WebElement mergemyacc = driver.findElement(By.xpath("//h1[contains(text(),'Merge My Accounts')]"));
				Assert.assertEquals(true, mergemyacc.isDisplayed(),"Merge my accounts is dispalyed");
			}
			
			WebElement merge = driver.findElement(By.xpath("//div[@class='pbTopButtons']//input[@title='Merge']"));
			clickElement(merge);
			Alert alert = driver.switchTo().alert();
			 String alertMessage= driver.switchTo().alert().getText();
			 if(alertMessage.contains("These records will be merged"))
			 {
			 alert.accept();	
			 }
		
	
			 List<WebElement> AcctList=driver.findElements(By.xpath("//table[@class='list']//tr/th[1]"));
		
			for(WebElement acc:AcctList)
			{
				if(acc.getText().contains("Acct"))
				{
					System.out.print("Merged Account is displayed");
					break;
				}
			}
			Logout();
			closeAll();	
			System.out.println("Pass: Merge Accounts Test Case -13");
		} catch (Exception e)
	{
		System.out.println("Fail: Create Merge Accounts Test Case -13");
		
	}
}
	
	@Test(enabled=true)
	public static void CreateAccountReport() throws IOException, InterruptedException  
	{
		try {
			System.out.println("Start of Create Account Report Test Case -14");
			setUp();
			login(userName,password);
			HomeMenu();
			Account();
			
			WebElement accactivity = driver.findElement(By.xpath("//a[contains(text(),'Accounts with last activity > 30 days')]"));
		clickElement(accactivity);
		
		WebElement unsaved = driver.findElement(By.xpath("//h2[contains(text(),'Unsaved Report')]"));
		Assert.assertEquals(true, unsaved.isDisplayed(),"Unsaved report is loaded");
		WebElement datefield = driver.findElement(By.xpath(" //input[@id='ext-gen20']"));
		clickElement(datefield);
		WebElement todaydate = driver.findElement(By.xpath("//div[contains(text(),'Created Date')]"));
		clickElement(todaydate);
		
	/*	Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		String date1= dateFormat.format(date);  // Now format the date

		// Print the Date
		System.out.println("Current date and time is " +date1);
		WebElement from = driver.findElement(By.xpath("//input[@id='ext-comp-1042']"));
		enterText(from,date1);
		WebElement to = driver.findElement(By.xpath("//input[@id='ext-comp-1045']"));
		enterText(to,date1);*/
		
		
		//For   date
	/*	WebElement dat = driver.findElement(By.xpath("//table[@id='ext-comp-1111']//button[@id='ext-gen255']"));
		 
	//// table[@id='ext-comp-1111']//button[@id='ext-gen255']
	 
	dat.click();
	 
	waitUntilVisible(dat);*/


		
		WebElement fromdate = driver.findElement(By.xpath("//img[@id='ext-gen152']"));
		clickElement(fromdate);
		WebElement currdate = driver.findElement(By.xpath("//button[@id='ext-gen256']"));
		mouseOverClick(currdate);
		WebElement todate = driver.findElement(By.xpath("//img[@id='ext-gen154']"));
		clickElement(todate);
		WebElement currdate1 = driver.findElement(By.xpath("//button[@id='ext-gen271']"));
		mouseOverClick(currdate1);
		WebElement preview = driver.findElement(By.xpath("//div[@class='rb-preview-warning']"));
		Assert.assertEquals(true, preview.isDisplayed(),"List of accounts displayed");
		WebElement save = driver.findElement(By.xpath("//button[@id='ext-gen49']"));
		clickElement(save);
		WebElement repname = driver.findElement(By.xpath("//input[@id='saveReportDlg_reportNameField']"));
		String reportname = "report1"+getcurrenttime();
		enterText(repname,reportname);
		//String savedrepname = repname.getText();
		WebElement urepname = driver.findElement(By.xpath("//input[@id='saveReportDlg_DeveloperName']"));
		clickElement(urepname);
		Thread.sleep(1000);
		WebElement savereport = driver.findElement(By.xpath("//table[@id='dlgSaveAndRun']"));
		waitUntilVisible(savereport);
		clickElement(savereport);
		WebElement reporttitle = driver.findElement(By.xpath("//h1[@class=\"noSecondHeader pageType\"]"));
		pageLoad(reporttitle,reportname);
		Logout();
		closeAll();
		System.out.println("Pass: Create Account Report Test Case -14");
	} catch (Exception e)
	{
		System.out.println("Fail: Create Account Report Test Case -14");
		
	}
	}
	
	public static void Opportuniites()
	{
		WebElement opps = driver.findElement(By.cssSelector("a[title='Opportunities Tab']"));
		mouseOverClick(opps);
		WebElement oppttitle = driver.findElement(By.xpath("//h1[contains(text(),'Opportunities')]"));
		pageLoad(oppttitle,"Opportunities");
	}
	
	@Test(enabled=true)
	public static void OpportunitiesDropDown() throws IOException, InterruptedException  
	{
		try {
			System.out.println("Start of Create Account Report Test Case -15");
			setUp();
			login(userName,password);
			HomeMenu();
			Opportuniites();
		
		    String[] exp = {"All Opportunities","Closing Next Month","Closing This Month","Kanban","My Opportunities","New This Week","Opportunity Pipeline","Recently Viewed Opportunities","Won"};
		    WebElement dropdown = driver.findElement(By.xpath("//select[@id='fcf']"));
		    dropDownValidation(exp,dropdown);

			Logout();
			closeAll();
			System.out.println("Pass: Create Account Report Test Case -15");
	} catch (Exception e)
		{
			System.out.println("Fail: Create Account Report Test Case -15");
			
		}
		}
	
	@Test(enabled=true)
	public static void CreateOpportunity() throws IOException, InterruptedException  
	{
		try {
			System.out.println("Start of Create Account Report Test Case -16");
			setUp();
			login(userName,password);
			HomeMenu();
			Opportuniites();
	
			WebElement newopp = driver.findElement(By.xpath("//input[@title='New']"));
			clickElement(newopp);
			WebElement oppname = driver.findElement(By.xpath("//input[@id='opp3']"));
			enterText(oppname,"opp"+getcurrenttime());
			WebElement accnamesearch = driver.findElement(By.xpath("//img[@title='Account Name Lookup (New Window)']"));
			String currentwindow=driver.getWindowHandle();
			multipleWindows(accnamesearch);
			driver.switchTo().frame(0);
			WebElement acctsearch = driver.findElement(By.xpath("//input[@id='lksrch']"));
			mouseOverClick(acctsearch);
			enterText(acctsearch,"Acct");
			WebElement go = driver.findElement(By.cssSelector("input[name*='go']"));
			mouseOverClick(go);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("resultsFrame");
			WebElement acctpick = driver.findElement(By.xpath("//a[contains(text(),'Acct')]"));
			clickElement(acctpick);
			driver.switchTo().window(currentwindow);
			WebElement accname = driver.findElement(By.xpath("//input[@id='opp4']"));
			clickElement(accname);		
			WebElement campsearch = driver.findElement(By.xpath("//tbody/tr[4]/td[2]/span[1]/a[1]/img[1]"));
			String currentwindowcmap=driver.getWindowHandle();
			multipleWindows(campsearch);
			driver.switchTo().frame(0);
			WebElement campnamesearch = driver.findElement(By.xpath("//input[@id='lksrch']"));
			mouseOverClick(campnamesearch);
			enterText(campnamesearch,"Cust");
			WebElement gogo = driver.findElement(By.cssSelector("input[name*='go']"));
			mouseOverClick(gogo);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("resultsFrame");
			WebElement camppick = driver.findElement(By.xpath("//tr[@class='dataRow even first']/th"));
			clickElement(camppick);
			
			String camptext = camppick.getText();	
			if(camptext.contains("Cust"))
			
				System.out.println("Campaign entered");
			else
				System.out.println("Campaign not entered");
			
			driver.switchTo().window(currentwindow);
			WebElement campname = driver.findElement(By.xpath("//input[@id='opp17']"));
			clickElement(campname);
			enterText(campname,camptext);
			WebElement currdate = driver.findElement(By.xpath("//span[@class=\"dateFormat\"]/a"));
			clickElement(currdate);
			Select selectstage = new Select(driver.findElement(By.xpath("//select[@id='opp11']")));
			selectstage.selectByValue("Proposal");
			WebElement prob = driver.findElement(By.xpath("//input[@id='opp12']"));
			enterText(prob,"10");
			Select selectsource = new Select(driver.findElement(By.xpath("//select[@id='opp6']")));
			selectsource.selectByValue("Employee Referral");
			WebElement save = driver.findElement(By.xpath("//td[@id='bottomButtonRow']//input[@title='Save']"));
			clickElement(save);
			WebElement opppage = driver.findElement(By.xpath("//h1[contains(text(),'Opportunity')]"));
			pageLoad(opppage,"Opportunity");
			
			WebElement oppnametext = driver.findElement(By.xpath("//div[@id='opp3_ileinner']"));
			WebElement accnametext = driver.findElement(By.xpath("//div[@id=\"opp4_ileinner\"]/a"));
			WebElement campaigntext = driver.findElement(By.xpath("//div[@id=\"opp17_ileinner\"]/a"));
	
			textCheck(accnametext,"Acct");
			//elementTextCheck(accnametext,"Acct");
			textCheck(oppnametext,"opp");
			textCheck(campaigntext,"Cust");
			//System.out.println("Opp text" +oppnametext);
			//elementTextCheck(oppnametext,"Bag");
			//elementTextCheck(campaigntext,"Cust");
			//softassert.assertAll();
			
			Logout();
			closeAll();
			System.out.println("Pass: Create Account Report Test Case -16");
	} catch (Exception e)
		{
			System.out.println("Fail: Create Account Report Test Case -16");
			
		}
		}
	
	@Test(enabled=true)
	public static void TestOpportunityPipelineReport() throws IOException, InterruptedException  
	{
		try {
			System.out.println("Start of Test Opportunity Pipeline Report Test Case -17");
			setUp();
			login(userName,password);
			HomeMenu();
			Opportuniites();
			WebElement opppipeline =driver.findElement(By.xpath("//a[contains(text(),'Opportunity Pipeline')]"));
			clickElement(opppipeline);
			WebElement opppipelinepage = driver.findElement(By.xpath("//h1[contains(text(),'Opportunity Pipeline')]"));
			pageLoad(opppipelinepage,"Opportunity Pipeline");
			Logout();
			closeAll();
			System.out.println("Pass: Test Opportunity Pipeline Report Test Case -17");
	} catch (Exception e)
		{
			System.out.println("Fail: Test Opportunity Pipeline Report Test Case -17");
			
		}
		}
	
	@Test(enabled=true)
	public static void TestStuckOpportunitiesReport() throws IOException, InterruptedException  
	{
		try {
			System.out.println("Start of Test Stuck Opportunities Report Test Case -18");
			setUp();
			login(userName,password);
			HomeMenu();
			Opportuniites();
			WebElement oppstuck =driver.findElement(By.xpath("//a[contains(text(),'Stuck Opportunities')]"));
			clickElement(oppstuck);
			WebElement oppstuckpage = driver.findElement(By.xpath("//h1[contains(text(),'Stuck Opportunities')]"));
			pageLoad(oppstuckpage,"Stuck Opportunities");
			Logout();
			closeAll();
			System.out.println("Pass: Test Stuck Opportunities Report Test Case -18");
		} catch (Exception e)
			{
				System.out.println("Fail: Test Stuck Opportunities Report Test Case -18");
				
			}
			}
			
	@Test(enabled=true)
	public static void TestQuaterlySummaryReport() throws IOException, InterruptedException  
	{
		try {
			System.out.println("Start of Test Quaterly Summary Report Test Case -19");
			setUp();
			login(userName,password);
			HomeMenu();
			Opportuniites();
			WebElement interval = driver.findElement(By.xpath("//select[@id='quarter_q']"));
			Select selectin = new Select(interval);
			selectin.selectByValue("prev1");
			WebElement include = driver.findElement(By.xpath("//select[@id='open']"));
			Select selectclude = new Select(include);
			selectclude.selectByValue("closed");
			WebElement runreport = driver.findElement(By.xpath(" //input[@title='Run Report']"));
			clickElement(runreport);
			WebElement oppreport = driver.findElement(By.xpath("//h1[contains(text(),'Opportunity Report')]"));
			pageLoad(oppreport,"Opportunity Report");
			WebElement includecheck = driver.findElement(By.xpath("//select[@id='open']/option[@selected='selected']"));
			textCheck(includecheck,"Closed");
			WebElement intervalcheck = driver.findElement(By.xpath("//select[@id='quarter_q']//optgroup[@label='Fiscal Quarter']//option[@selected='selected']"));
			textCheck(intervalcheck,"Previous FQ");
			Logout();
			closeAll();
			System.out.println("Pass: Test Quaterly Summary Report Test Case -19");
		} catch (Exception e)
			{
				System.out.println("Fail: Test Quaterly Summary Report Test Case -19");
				
			}
			}		
	
	public static void Leads()
	{
		WebElement leadstab = driver.findElement(By.xpath("//a[normalize-space()='Leads']"));
		clickElement(leadstab);
		WebElement leadpage = driver.findElement(By.xpath("//h1[contains(text(),'Leads')]"));
		pageLoad(leadpage,"Leads");
	}
	@Test(enabled=true)
	public static void LeadsTab() throws IOException, InterruptedException  
	{
		try {
			System.out.println("Start of Leads Tab Test Case -20");
			setUp();
			login(userName,password);
			HomeMenu();		
			Leads();
			Logout();
			closeAll();
			System.out.println("Pass: Leads Tab Test Case -20");
		} catch (Exception e)
			{
				System.out.println("Fail: Test Quaterly Summary Report Test Case -19");
				
			}
			}		
	
	@Test(enabled=true)
	public static void LeadsSelectView() throws IOException, InterruptedException  
	{
		try {
			System.out.println("Start of Leads Select View Test Case -21");
			setUp();
			login(userName,password);
			HomeMenu();		
			Leads();
			String[] exp= {"All Open Leads","My Leads","Recently Viewed Leads","Today's Leads"};
			WebElement leadsview = driver.findElement(By.xpath("//select[@id='fcf']"));
			dropDownValidation(exp,leadsview);
			Logout();
			closeAll();
			System.out.println("Pass: Leads Select View Test Case -21");
		} catch (Exception e)
			{
				System.out.println("Fail: Leads Select View Test Case -21");
				
			}
			}	
	
	@Test(enabled=true)
	public static void DefaultView() throws IOException, InterruptedException  
	{
		try {
			System.out.println("Start of Default View Test Case -22 and List item \"Today's leads\" Test case -23");
			setUp();
			login(userName,password);
			HomeMenu();		
			Leads();
			WebElement leadsview = driver.findElement(By.xpath("//select[@id='fcf']"));
			selectElement(leadsview,"Today's Leads");
			WebElement go = driver.findElement(By.xpath("//input[@title='Go!']"));
			clickElement(go);
			WebElement leadsviewoption = driver.findElement(By.xpath("//select[@id='is:islv:inlineSchedulerListView:enhancedList_listSelect']/option[@selected='selected']"));
			textCheck(leadsviewoption,"Today's Leads");
			Logout();
			Login(userName,password);
			Leads();
			WebElement gogo = driver.findElement(By.xpath("//input[@title='Go!']"));
			clickElement(gogo);
			WebElement leadsviewoptions = driver.findElement(By.xpath("//select[@id='is:islv:inlineSchedulerListView:enhancedList_listSelect']/option[@selected='selected']"));	
			textCheck(leadsviewoptions,"Today's Leads");
			Logout();
			closeAll();
			System.out.println("Pass: Default View Test Case -22 and List item \"Today's leads\" Test case -23");
		} catch (Exception e)
			{
				System.out.println("Fail: Default View Test Case -22 and List item \"Today's leads\" Test case -23");
				
			}
			}	
	
	@Test(enabled=true)
	public static void CheckNewLead() throws IOException, InterruptedException  
	{
		try {
			System.out.println("Start of check new button on Lead's page Test Case -24");
			setUp();
			login(userName,password);
			HomeMenu();		
			Leads();
			WebElement newbutton = driver.findElement(By.xpath("//input[@title='New']"));
			clickElement(newbutton);
			WebElement leadpage = driver.findElement(By.xpath("//h2[normalize-space()='New Lead']"));
			pageLoad(leadpage,"New Lead");
			WebElement lastname = driver.findElement(By.xpath("//input[@id='name_lastlea2']"));
			enterText(lastname,"ABCD");
			textCheckValue(lastname,"ABCD");
			WebElement company = driver.findElement(By.xpath("//input[@id='lea3']"));
			enterText(company,"ABCD");
			textCheckValue(company,"ABCD");
			WebElement save = driver.findElement(By.xpath("//td[@id='topButtonRow']//input[@title='Save']"));
			clickElement(save);
			WebElement newleadpage = driver.findElement(By.xpath("//h2[contains(text(),'ABCD')]"));
			pageLoad(newleadpage,"ABCD");
			
			Logout();
			closeAll();
			System.out.println("Pass: check new button on Lead's page Test Case -24");
		} catch (Exception e)
			{
				System.out.println("Fail: check new button on Lead's page Test Case -24");
				
			}
			}	
	
	public static void Contacts()
	{
		WebElement contacttab = driver.findElement(By.cssSelector("a[title='Contacts Tab']"));
		clickElement(contacttab);
		WebElement contactpage = driver.findElement(By.xpath("//h1[contains(text(),'Contacts')]"));
		pageLoad(contactpage,"Contacts");
	}
	
	@Test(enabled=true)
	public static void CreateContact() throws IOException, InterruptedException  
	{
		try {
			System.out.println("Start of create new contact Test Case -25");
			setUp();
			login(userName,password);
			HomeMenu();	
			Contacts();
			WebElement newcontact = driver.findElement(By.xpath("//input[@title='New']"));
			clickElement(newcontact);
			WebElement newcontactpage = driver.findElement(By.xpath("//h2[normalize-space()='New Contact']"));
			pageLoad(newcontactpage,"New Contact");
			WebElement lastname = driver.findElement(By.xpath("//input[@id='name_lastcon2']"));
			enterText(lastname,"lastContact");
			textCheckValue(lastname,"lastContact");
			WebElement accsearch = driver.findElement(By.xpath("//img[@title='Account Name Lookup (New Window)']"));
			String currentwindow=driver.getWindowHandle();
			multipleWindows(accsearch);
			driver.switchTo().frame(0);
			WebElement acctsearch = driver.findElement(By.xpath("//input[@id='lksrch']"));
			mouseOverClick(acctsearch);
			enterText(acctsearch,"Acct");
			WebElement go = driver.findElement(By.cssSelector("input[name*='go']"));
			mouseOverClick(go);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("resultsFrame");
			WebElement acctpick = driver.findElement(By.xpath("//a[contains(text(),'Acct')]"));
			clickElement(acctpick);
			driver.switchTo().window(currentwindow);
			WebElement accname = driver.findElement(By.xpath("//input[@id='con4']"));
			clickElement(accname);		
			textCheckValue(accname,"AcctTesting");
			WebElement save = driver.findElement(By.xpath("//td[@id='topButtonRow']//input[@title='Save']"));
			clickElement(save);
			WebElement newcontactcheck= driver.findElement(By.xpath("//h2[contains(text(),'lastContact')]"));
			pageLoad(newcontactcheck,"lastContact");
			Logout();
			closeAll();
			System.out.println("Pass: create new contact Test Case -25");
			
		} catch (Exception e)
			{
				System.out.println("Fail: create new contact Test Case -25");
				
			}
			}
	
	@Test(enabled=true)
	public static void CreateNewViewContact() throws IOException, InterruptedException  
	{
		try {
			System.out.println("Start of create new view in contact page Test Case -26");
			setUp();
			login(userName,password);
			HomeMenu();	
			Contacts();
			WebElement newview = driver.findElement(By.xpath("//a[contains(text(),'Create New View')]"));
			clickElement(newview);
			WebElement newviewpage = driver.findElement(By.xpath("//h2[contains(text(),'Create New View')]"));
			pageLoad(newviewpage,"Create New View");
			WebElement viewname = driver.findElement(By.xpath("//input[@id='fname']"));
			enterText(viewname,"ViewTesting"+getcurrenttime());
			textCheckValue(viewname,"ViewTesting");
			WebElement univiewname =driver.findElement(By.xpath("//input[@id='devname']"));
			clickElement(univiewname);
			WebElement save = driver.findElement(By.xpath("(//input[@title='Save'])[1]"));
			clickElement(save);
			WebElement view = driver.findElement(By.xpath("//select[@id='is:islv:inlineSchedulerListView:enhancedList_listSelect']//option[@selected='selected']"));
			textCheck(view,"ViewTesting");
			softassert.assertTrue(driver.getTitle().contains("Contacts"), "Homepageloaded");
			Logout();
			closeAll();
			softassert.assertAll();
			System.out.println("Pass: create new view in contact page Test Case -26");
		} catch (Exception e)
			{
				System.out.println("Fail: create new view in contact page Test Case -26");
				
			}
			}
	@Test(enabled=true)
	public static void CheckRecentlyCreatedContact() throws IOException, InterruptedException  
	{
		try {
			System.out.println("Start of check recently created contact Test Case -27");
			setUp();
			login(userName,password);
			HomeMenu();	
			Contacts();
			WebElement recent = driver.findElement(By.xpath("//select[@id='hotlist_mode']"));
			mouseOverClick(recent);
			selectElement(recent, "Recently Created");
			WebElement recentcheck = driver.findElement(By.xpath("//select[@id='hotlist_mode']"));
			textCheck(recentcheck,"Recently Created");
			Logout();
			closeAll();

			System.out.println("Pass: check recently created contact Test Case -27");
		}  catch (Exception e)
			{
				System.out.println("Fail: check recently created contact Test Case -27");
				
			}
			}
	
	
	@Test(enabled=true)
	public static void CheckMyContactsView() throws IOException, InterruptedException  
	{
		try {
			System.out.println("Start of check my contacts view Test Case -28");
			setUp();
			login(userName,password);
			HomeMenu();	
			Contacts();
			
			WebElement mycontacts = driver.findElement(By.xpath("//select[@id='fcf']"));
			selectElement(mycontacts,"My Contacts");
			if(driver.findElement(By.xpath("//input[@title='Go!']")).isDisplayed())
			{
				driver.findElement(By.xpath("//input[@title='Go!']")).click();
			}
			WebElement mycontactscheck = driver.findElement(By.xpath("//select[@id='is:islv:inlineSchedulerListView:enhancedList_listSelect']"));
			textCheck(mycontactscheck,"My Contacts");
			
		Logout();
		closeAll();
		System.out.println("Pass: check my contacts view Test Case -28");
			
		} catch (Exception e)
			{
				System.out.println("Fail: check my contacts view Test Case -28");
				
			}
			}
	
	@Test(enabled=true)
	public static void ViewContact() throws IOException, InterruptedException  
	{
		try {
			System.out.println("Start of view contact Test Case -29");
			setUp();
			login(userName,password);
			HomeMenu();	
			Contacts();
			
			WebElement contact = driver.findElement(By.xpath("//tbody/tr[2]/th[1]/a[1]"));
			String contactinfo = contact.getText();
			mouseOverClick(contact);
			WebElement contactcheck = driver.findElement(By.xpath("//h2[@class='topName']"));
			String checkcontactinfo = contactcheck.getText();
			softassert.assertEquals(contactinfo, checkcontactinfo,"Slected contact info dispalyed");
			
			Logout();
		closeAll();
			softassert.assertAll();
			System.out.println("Pass: view contact Test Case -29");
		} catch (Exception e)
			{
				System.out.println("Fail: check view contact Test Case -29");
				
			}
	}
	
	public  static void NewContactView()
	{
		WebElement newcontactivew = driver.findElement(By.xpath("//a[contains(text(),'Create New View')]"));
		mouseOverClick(newcontactivew);
		WebElement newviewcheck = driver.findElement(By.xpath("//h2[contains(text(),'Create New View')]"));
		pageLoad(newviewcheck,"Create New View");
	}
	
	@Test(enabled=true)
	public static void NewViewContactError() throws IOException, InterruptedException  
	{
		try {
			System.out.println("Start of new view contact error Test Case -30");
			setUp();
			login(userName,password);
			HomeMenu();	
			Contacts();
			NewContactView();
			WebElement uniquename = driver.findElement(By.xpath("//input[@id='devname']"));
			enterText(uniquename,"EFGH");
			WebElement save = driver.findElement(By.xpath("(//input[@title='Save'])[1]"));
			clickElement(save);
			WebElement errormsg = driver.findElement(By.xpath("//div[@class='requiredInput']//div[@class='errorMsg']"));
			//textAssert(errormsg,"Should");
			errorCheck(errormsg," You must enter a value");
			Logout();
			closeAll();
			System.out.println("Pass: new view contact error Test Case -30");
		} catch (Exception e)
			{
				System.out.println("Fail: new view contact error Test Case -30");
				
			}
			}
	
	@Test(enabled=true)
	public static void CancelNewView() throws IOException, InterruptedException  
	{
		try {
			System.out.println("Start of cancel new view Test Case -31");
			setUp();
			login(userName,password);
			HomeMenu();	
			Contacts();
			NewContactView();
			WebElement  viewname = driver.findElement(By.xpath("//input[@id='fname']"));
			enterText(viewname, "ABCD");
			textCheckValue(viewname,"ABCD");
			WebElement  uniqwname = driver.findElement(By.xpath("//input[@id='devname']"));
			enterText(uniqwname,"EFGH");
			clearElement(uniqwname);
			enterText(uniqwname,"EFGH");
			textCheckValue(uniqwname,"EFGH");
			WebElement cancel = driver.findElement(By.xpath("(//input[@title='Cancel'])[1]"));
			mouseOverClick(cancel);
			
				Select select = new Select(driver.findElement(By.xpath("//select[@id='fcf']")));  

			    List<WebElement> options = select.getOptions();  
			    boolean check=true;
			    for(WebElement we:options)  
			    {  
			         if (we.getText().equals("ABCD")){
			        	 check =false;
			        	 System.out.println(we.getText()+" view is created - error");
			        	 break;
			         } 
			     } 
			    if(check==true)
			    	 System.out.println("view is not created");
		
		Logout();
		closeAll();
		System.out.println("Pass: cancel new view Test Case -31");	
		} catch (Exception e)
			{
				System.out.println("Fail: cancel new view Test Case -31");
				
			}
	}
	
	@Test(enabled=true)
	public static void SaveAndNewView() throws IOException, InterruptedException  
	{
		try {
			System.out.println("Start of save and new view Test Case -32");
			setUp();
			login(userName,password);
			HomeMenu();	
			Contacts();
			
			WebElement newcontact = driver.findElement(By.xpath("//input[@title='New']"));
			mouseOverClick(newcontact);
			WebElement newcontactcheck = driver.findElement(By.xpath("//h2[contains(text(),'New Contact')]"));
			pageLoad(newcontactcheck," New Contact");
			WebElement lastname = driver.findElement(By.xpath("//input[@id='name_lastcon2']"));
			enterText(lastname,"Indian");
			textCheckValue(lastname,"Indian");
			WebElement accname = driver.findElement(By.xpath("//input[@id='con4']"));
			enterText(accname,"Global Media");
			clickElement(accname);
			mouseOverClick(accname);
		
			/*textCheck(accname,"Global Media");
			System.out.println("value"+accname.getAttribute("Value"));
			String accnamecheck=accname.getAttribute("Value");
					
			textCheckValue(accname,"Gloabal Media");*/
			WebElement saveandnew = driver.findElement(By.xpath("//td[@id='topButtonRow']//input[@title='Save & New']"));
			clickElement(saveandnew);
			WebElement contactedit = driver.findElement(By.xpath("//h1[@class='pageType']"));
			pageLoad(contactedit,"Contact Edit");
			System.out.println("Pass: save and new view Test Case -32");
			Logout();
		closeAll();
			
		} catch (Exception e)
			{
				System.out.println("Fail: save and new view Test Case -32");
				
			}
			}
	
	
	public static void HomeTab()
	{
		WebElement loggeduser =driver.findElement(By.xpath("//h1[@class='currentStatusUserName']/a"));
		textCheck(loggeduser,"praveena");
		clickElement(loggeduser);
		WebElement user =driver.findElement(By.xpath("//span[@id='tailBreadcrumbNode']"));
		pageLoad(user,"praveena");
		//textCheck();
	}
	
	@Test(enabled=true)
	public static void VerifyLoggedUser() throws IOException, InterruptedException  
	{
		try {
			System.out.println("Start of verify first name, lastname of logged user Test Case -33");
			setUp();
			login(userName,password);
			HomeMenu();
			HomeTab();
			//WebElement prof = driver.findElement(By.xpath("//a[contains(text(),'My Profile')]")); 
			//clickElement(prof);
			Logout();
			closeAll();
			System.out.println("Pass: verify first name, lastname of logged user Test Case -33");
			} catch (Exception e)
				{
					System.out.println("Fail: verify first name, lastname of logged user Test Case -33");
					
				}
	}
	
	
	
	@Test(enabled=true)
	public static void VerifyUpdatedLast() throws IOException, InterruptedException  
	{
		try {
			System.out.println("Start of verify updated last name Test Case -34");
			setUp();
			login(userName,password);
			HomeTab();
			ProfileLastNameChange("Abcd");
			WebElement userMenu = driver.findElement(By.xpath("//span[@id='userNavLabel']"));
			waitUntilVisible(userMenu);
			mouseOverClick(userMenu);
			textCheck(userMenu,"praveena Abcd");
			WebElement home = driver.findElement(By.xpath("//a[contains(text(),'Home')]"));
			mouseOverClick(home);
			WebElement username = driver.findElement(By.cssSelector("h1[class='currentStatusUserName'] a"));
			textCheck(username,"praveena Abcd");
			Logout();
			closeAll();
			System.out.println("Pass: verify updated last name Test Case -34");
			} catch (Exception e)
				{
					System.out.println("Fail: verify updated last name Test Case -34");
					
				}
	}
	
	@Test(enabled=true)
	public static void VerifyTabCustomization() throws IOException, InterruptedException  
	{
		try {
			System.out.println("Start of verify tab customization Test Case -35");
			setUp();
			login(userName,password);
			WebElement tab = driver.findElement(By.xpath("//img[@title='All Tabs']"));
			clickElement(tab);
			WebElement alltabs = driver.findElement(By.xpath("//h1[contains(text(),'All Tabs')]"));
			pageLoad(alltabs,"All Tabs");
			WebElement customizetab = driver.findElement(By.xpath("//input[@title='Customize My Tabs']"));
			clickElement(customizetab);
			WebElement mytab = driver.findElement(By.xpath("//h1[contains(text(),'Customize My Tabs')]"));
			pageLoad(mytab,"Customize My Tabs");
			WebElement seltab = driver.findElement(By.xpath("//select[@id='duel_select_1']"));
			WebElement remove = driver.findElement(By.xpath("//img[@title='Remove']"));
			selectElementClick(seltab,remove,"Forecasts");
			WebElement availtab = driver.findElement(By.xpath("//select[@id='duel_select_0']"));
			selectElementCheck(availtab,"Forecasts");
			WebElement save = driver.findElement(By.xpath("//input[@title='Save']"));
			clickElement(save);
			WebElement alltabspage = driver.findElement(By.xpath("//h1[contains(text(),'All Tabs')]"));
			pageLoad(alltabspage,"All Tabs");
			List <WebElement> tabs = driver.findElements(By.xpath("//div[@id='tabContainer']//nav/ul/li/a"));
			verifyOption(tabs,"Forecast");
			
			//Below code trying to rerun to add forecast option back
			WebElement customizetab2 = driver.findElement(By.xpath("//input[@title='Customize My Tabs']"));
			clickElement(customizetab2);
			WebElement add = driver.findElement(By.xpath("//img[@title='Add']"));
			WebElement availtab2 = driver.findElement(By.xpath("//select[@id='duel_select_0']"));
			selectElementClick(availtab2,add,"Forecasts");
			WebElement save2 = driver.findElement(By.xpath("//input[@title='Save']"));
			clickElement(save2);
			Logout();
			closeAll();
			System.out.println("Pass: verify tab customization Test Case -35");
			} catch (Exception e)
				{
					System.out.println("Fail: verify tab customization Test Case -35");
					
				}
	}
	
	public static void HomeCalendar() throws ParseException
	{
		WebElement homedate = driver.findElement(By.xpath("//span[@class='pageDescription']/a"));
		dateFormatCheck(homedate);
		clickElement(homedate);
		WebElement cal = driver.findElement(By.xpath("//h1[@class=\"pageType\"]"));
		pageLoad(cal,"Calendar for praveena");
	}
	
	
	public static void NewEvent()
	{
		WebElement newevent = driver.findElement(By.xpath("//h2[contains(text(),'New Event')]"));
		pageLoad(newevent,"New Event");
		WebElement subject = driver.findElement(By.xpath("//input[@id='evt5']"));
		waitUntilVisible(subject);
		elementFocusCheck(subject,"Subject");
		WebElement search = driver.findElement(By.xpath("//img[@title='Subject Combo (New Window)']"));
		String currentwindow=driver.getWindowHandle();
		clickElement(search);
		Set<String> windows= driver.getWindowHandles();
		System.out.println("num of windows currently opned="+windows.size());
		for(String windowHandle:windows) {
			if(!windowHandle.equals(currentwindow)) {
				driver.switchTo().window(windowHandle);
				break;
			}
			
		}
		WebElement subjectwindow = driver.findElement(By.xpath("//h1[contains(text(),'Select a Subject below.')]"));
		pageLoad(subjectwindow,"Select a Subject below.");
		WebElement other = driver.findElement(By.xpath("//li[@class='listItem4']//a[1]"));
		mouseOverClick(other);
		driver.switchTo().window(currentwindow);
		WebElement subject2 = driver.findElement(By.xpath("//input[@id='evt5']"));
		textCheckValue(subject2,"Other");
	}
	
	public static void EndDate()
	{
		WebElement endtime = driver.findElement(By.xpath("//input[@id='EndDateTime_time']"));
		clickElement(endtime);
		WebElement timepicker = driver.findElement(By.xpath("//div[@id='simpleTimePicker']"));
		assertTrue(timepicker.isDisplayed());
	}
	
	@Test(enabled=true)
	public static void BlockingAnEvent() throws IOException, InterruptedException, ParseException  
	{
		//try {
			System.out.println("Start of blocking an event Test Case -36");
			setUp();
			login(userName,password);
			HomeCalendar();
			WebElement eight = driver.findElement(By.xpath("//a[contains(text(),'8:00 PM')]"));
			clickElement(eight);
			NewEvent();
			EndDate();
			WebElement nine = driver.findElement(By.xpath("//div[@id='timePickerItem_42']"));
			waitUntilVisible(nine);
			clickElement(nine);
			WebElement endtime = driver.findElement(By.xpath("//input[@id='EndDateTime_time']"));
			textCheckValue(endtime,"9:00 PM");
			WebElement save = driver.findElement(By.xpath("//td[@id='bottomButtonRow']//input[@title='Save']"));
			clickElement(save);
			WebElement cal2 = driver.findElement(By.xpath("//h1[@class=\"pageType\"]"));
			pageLoad(cal2,"Calendar for praveena");
						WebElement otherevent = driver.findElement(By.xpath("//a[@title='Busy - Other']"));
			assertTrue(otherevent.isDisplayed(),"Otherevent is displayed");
			mouseOver(otherevent);
			WebElement hoverpage = driver.findElement(By.xpath("//div[@id='EventHoverPage_00U8c00000fql4i_page']"));
			//WebElement hoverpage = driver.findElement(By.cssSelector("#EventHoverPage_00U8c00000fqky2_page"));
			waitUntilVisible(hoverpage);
			WebElement eightevent = driver.findElement(By.xpath("//td[contains(text(),'8:00 PM')]"));
			assertTrue(eightevent.isDisplayed(),"Blocked for 8pm");
			WebElement nineevent = driver.findElement(By.xpath("//td[contains(text(),'9:00 PM')]"));
			assertTrue(nineevent.isDisplayed(),"Blocked for 9pm");
			
			Logout();
			closeAll();
			System.out.println("Pass: blocking an event Test Case -36");
		/*	} catch (Exception e)
				{
					System.out.println("Fail: blocking an event Test Case -36");
					
				}*/
	}
	
	@Test(enabled=true)
	public static void CalendarWeeklyRecurrence() throws IOException, InterruptedException, ParseException  
	{
		try {
			System.out.println("Start of blocking weekly recurrence event Test Case -37");
			setUp();
			login(userName,password);
			HomeCalendar();
			WebElement four = driver.findElement(By.xpath("//a[contains(text(),'4:00 PM')]"));
			clickElement(four);
			NewEvent();
			EndDate();
			WebElement seven = driver.findElement(By.xpath("//div[@id='timePickerItem_38']"));
			waitUntilVisible(seven);
			clickElement(seven);
			WebElement endtime = driver.findElement(By.xpath("//input[@id='EndDateTime_time']"));
			textCheckValue(endtime,"7:00 PM");
			WebElement recurrence = driver.findElement(By.xpath("//input[@id='IsRecurrence']"));
			assertTrue(recurrence.isDisplayed(),"Recurrence checkbox");
			isCheckboxChecked(recurrence,"Recurrence");
			WebElement recurrfrequency = driver.findElement(By.xpath("//table[@class='recurrenceTable']"));
			assertTrue(recurrfrequency.isDisplayed(),"Recurrence frequency");
			WebElement startdate = driver.findElement(By.xpath("//input[@id='RecurrenceStartDateTime']"));
			assertTrue(startdate.isDisplayed(),"Start date");
			WebElement enddate = driver.findElement(By.xpath("//input[@id='RecurrenceEndDateOnly']"));
			assertTrue(enddate.isDisplayed(),"End date");
			WebElement weekly = driver.findElement(By.xpath("//input[@id='rectypeftw']"));
			waitUntilVisible(weekly);
			weekly.click();
			assertTrue(weekly.isSelected(),"Weekly");
			WebElement recursevery = driver.findElement(By.xpath("//div[contains(text(),'Recurs every')]"));
			assertTrue(recursevery.isDisplayed(),"Recurs every");
			WebElement day = driver.findElement(By.xpath("//input[@id='wi']"));
			textCheckValue(day,"1");
			Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		    Date date=calendar.getTime();
		    String weekday = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime());
			List <WebElement> weekdays = driver.findElements(By.xpath("//div[@id='w']//div[2]/label"));
			for(WebElement weekdayoption:weekdays)
			{
				if(weekdayoption.getText().contains(weekday))
				{
					String foroption = weekdayoption.getAttribute("for");
					WebElement wday = driver.findElement(By.xpath("//input[@id='"+foroption+"']"));
					{
						assertTrue(wday.isSelected(),"Weekday");
						break;
					}
				}
			}
		
			String startdt = driver.findElement(By.xpath("//input[@id='RecurrenceStartDateTime']")).getAttribute("value");
			
			//Adding 2 weeks to current date
			 SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			 Calendar c = Calendar.getInstance();
			 c.setTime(sdf.parse(startdt));
			 c.add(Calendar.DATE, 14);  // number of days to add
			 startdt = sdf.format(c.getTime());  // dt is now the new date
			 
			 WebElement enddt = driver.findElement(By.xpath("//input[@id='RecurrenceEndDateOnly']"));
			 enterText(enddt, startdt);
			 WebElement save =driver.findElement(By.xpath("//td[@id='bottomButtonRow']//input[@title='Save']"));
			 clickElement(save);
			 WebElement cal2 = driver.findElement(By.xpath("//h1[@class=\"pageType\"]"));
			pageLoad(cal2,"Calendar for praveena");
			WebElement otherevent = driver.findElement(By.xpath("//span[@id='p:f:j_id25:j_id69:20:j_id71:0:j_id72:calendarEvent:j_id84']//a[@title='Busy - Other']"));
			assertTrue(otherevent.isDisplayed(),"Otherevent is displayed");
			mouseOver(otherevent);
			WebElement hoverpage = driver.findElement(By.xpath("(//h2[contains(text(),'Event Detail')])[1]"));
				waitUntilVisible(hoverpage);
				WebElement eightevent = driver.findElement(By.xpath("//td[contains(text(),'4:00 PM')]"));
				assertTrue(eightevent.isDisplayed(),"Blocked for 4pm");
				WebElement nineevent = driver.findElement(By.xpath("//td[contains(text(),'7:00 PM')]"));
				assertTrue(nineevent.isDisplayed(),"Blocked for 7pm");
				//div[@id='p:f:j_id25:j_id69:28:j_id71:0:j_id72:calendarEvent:i']//div[@class='multiLineEventBlock dragContentPointer']
			Logout();
			closeAll();
				
			System.out.println("Pass: blocking weekly recurrence event Test Case -37");
			
			} catch (Exception e)
				{
					System.out.println("Fail: blocking weekly recurrence event Test Case -37");
					
				}
	}
		
	
	public static void Logout() throws InterruptedException, IOException
	{
		//System.out.println("Start of Logout Test Case -09");
		WebElement userMenu = driver.findElement(By.xpath("//span[@id='userNavLabel']"));
		waitUntilVisible(userMenu);
		mouseOverClick(userMenu);
		WebElement logout = driver.findElement(By.cssSelector("a[title='Logout']"));
		clickElement(logout);
		WebElement login = driver.findElement(By.xpath("//form[@id='login_form']"));
		if(login.isDisplayed())
			System.out.println("Successfully logged out");
		else
			System.out.println("Failed to logout");	
	}

	
	
	
}

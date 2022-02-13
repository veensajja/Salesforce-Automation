package com.salesforce.Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.salesforce.utility.CommonUtilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseReusable {

	protected static WebDriver driver;
	protected static WebDriverWait wait;
//	protected static String userName = CommonUtilities.getApplicationProperty("username");
//	protected static String password = CommonUtilities.getApplicationProperty("passwordname");
	
	static SoftAssert softassert = new SoftAssert();
	
	//Not using this method, created commonutilities and constants class where the property file is being loaded
public static String readProperty(String key) throws IOException
{
	
	FileInputStream fs = new FileInputStream(new File("/Users/praveenastandard/eclipse-workspace/SalesforceAutomation/src/main/resources/SalesForceData.properties"));
	Properties p = new Properties();
	p.load(fs);
	String value = p.getProperty(key);
	return value;
}

/*public static String getUrl() throws IOException
{
	String url =readProperty("url");
	return url;
}
public static String getUserName() throws IOException
{
	String username = CommonUtilities.getApplicationProperty("username");
	//String username =readProperty("username");
	return username;
}

public static String getPassword() throws IOException
{
	String password = CommonUtilities.getApplicationProperty("passwordname");
	//String password =readProperty("passwordname");;
	return password;
}

public static String getUserMenuName() throws IOException
{
	String usermenu = readProperty("usermenuname");
	return usermenu;
}*/

public static void setUp() throws IOException
{
	String url = CommonUtilities.getApplicationProperty("url");
	//String url =getUrl();
	WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver();
	driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
	driver.get(url);
	driver.manage().window().maximize();
	
	
}
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


public static void Login(String userName,String password) {
	WebElement email= driver.findElement(By.xpath("//input[@id='username']"));
	waitUntilVisible(email);
	enterText(email, userName);
	WebElement passwordElement=driver.findElement(By.xpath("//input[@id='password']"));
	enterText(passwordElement, password);
	WebElement login=driver.findElement(By.xpath("//input[@id='Login']"));
	clickElement(login);
}

public static void dropDownValidation(String[] exp,WebElement dropdown)
{
	Select select = new Select(dropdown);  

    List<WebElement> options = select.getOptions();  
    boolean check=false;
    for(WebElement we:options)  
    {  
     for (int i=0; i<exp.length; i++){
         if (we.getText().equals(exp[i])){
        	 check =true;
        	 System.out.println(we.getText()+" exists");
         } 
        
       }
     if(check==false)
    	 System.out.println(we.getText()+" Option does not exist");
     } 
    
}

public static void multipleWindows(WebElement element)
{
	String currentwindow=driver.getWindowHandle();
	clickElement(element);
	Set<String> windows= driver.getWindowHandles();
	System.out.println("num of windows currently opned="+windows.size());
	for(String windowHandle:windows) {
		if(!windowHandle.equals(currentwindow)) {
			driver.switchTo().window(windowHandle);
			break;
		}	
	}
}

public static void elementTextCheck(String string, WebElement element)
{
	String expmsg = string;
	String actualmsg = element.getAttribute("value");
	softassert.assertEquals(actualmsg,expmsg,"text saved");
	//softAssert.assertTrue("Hello".equals("hello"), "Second soft assert failed");
//	softassert.assertTrue(string1.contains(string2),"Assert passed");
}

public static void clearElement(WebElement element) {
	if(element.isDisplayed()) {
		element.clear();
		System.out.println("pass: element cleared");
	}
	else {
		System.out.println("fail: element not displayed ");
	}
}

public static void waitUntilVisible(WebElement element) {
	wait=new WebDriverWait(driver,30);
	wait.until(ExpectedConditions.visibilityOf(element));
}
public static int getcurrenttime() {
	long currentDateMS = new Date().getTime();
	int seconds = (int) ((currentDateMS / 1000) % 3600);
	return seconds;
}
//Not correct and not being used
public static void waitUntilVisibleLoc(WebElement element, String path) 
{
	wait=new WebDriverWait(driver,30);
	wait.until(ExpectedConditions.visibilityOf(element));
	WebElement forgot = driver.findElement(By.xpath("//a[@id='forgot_password_link']"));
}

public static void waitUntilAlertIsPresent() {
	wait=new WebDriverWait(driver,30);
	wait.until(ExpectedConditions.alertIsPresent());
}
public static void dissmisAlert() {
	waitUntilAlertIsPresent();
	Alert alert=driver.switchTo().alert();
	alert.dismiss();
}
public static void enterText(WebElement element, String textvalue)
{
	
	if(element.isDisplayed()) {
		clearElement(element);
		element.sendKeys(textvalue);
		System.out.println("pass: text entered in element");
	}
	else {
		System.out.println("fail: element not displayed");
	}
		
}
public static void frameElementSwitch(WebElement element)
{
	driver.switchTo().frame(element);
}

public static void frameIndexSwitch(int index)
{
	driver.switchTo().frame(index); 
}
public static void textCheck(WebElement element,String string)
{
	if(element.getText().contains(string))
		System.out.println(string+ " text saved");
	else
		System.out.println(string+" text not saved");
}
public static void errorCheck(WebElement element,String string)
{
	if(element.getText().contains(string))
		System.out.println(string+" error message displayed");
	else
		System.out.println(string+" error message not dispalyed");
}
public static void textCheckValue(WebElement element,String string)
{
	if(element.getAttribute("value").contains(string))
		System.out.println(string+" text saved");
	else
		System.out.println(string+" text not saved");
}
public static void textAssert(WebElement element,String string)
{
	String stringact =element.getText();
	System.out.println("Error msg "+stringact);
	softassert.assertTrue(stringact.contains(string),"Text assert Success");
	//softassert.assertAll();
}

public static void pageLoad(WebElement element,String string)
{
	if(element.getText().contains(string))
		System.out.println(string+" Page is loaded");
	else
		System.out.println(string+" Page is not loaded");
	
}

public static void assertText(WebElement element,String string)
{
	String expmsg = string;
	String actualmsg = element.getText();
	Assert.assertEquals(actualmsg,expmsg);
	
}
public static void dialogBoxCheck(WebElement element,String string)
{
	if(element.getText().contains(string))
		System.out.println(string+" Window is open");
	else
		System.out.println(string+" Window is not open");
}
public static void enableCheck(WebElement element)
{
	if(element.isEnabled())
		System.out.println(element.getText()+" is dispalyed");
	else
		System.out.println(element.getText()+" is not dispalyed");
}

public static void uploadCheck(WebElement element)
{
if(element.isDisplayed())
	System.out.println("Element uploaded");
else
	System.out.println("Element Not uploaded");
}
public static void checkImgAttribute(String actual,String expected)
{
	if(actual.contains(expected))
		System.out.println("Img uploaded");
		else
			System.out.println("Img not uploaded");
}

public static void clickElement(WebElement element) {
	if(element.isDisplayed()) {
		element.click();
		System.out.println("pass: element clicked");
	}
	else {
		System.out.println("fail: element not displayed");
	}
}
public static void selectElement(WebElement element,String string) {
	if(element.isDisplayed()) {
		Select select = new Select(element);
		select.selectByVisibleText(string);
		System.out.println("pass: element selected");
	}
	else {
		System.out.println("fail: element not selected");
	}
}
public static void selectElementClick(WebElement element1, WebElement element2, String string)
{
	Select select = new Select(element1);
	List <WebElement> selectedtab = select.getOptions();
	for(WebElement seloptions:selectedtab)
	{
		if(seloptions.getText().contains(string))
		{
			clickElement(seloptions);
			element2.click();
			System.out.println(string+" clicked");
			break;
		}
	}
	System.out.println(string+" not clicked");
}

public static void selectElementCheck(WebElement element1,String string)
{
Select selectavail = new Select(element1);
List <WebElement> availabletab = selectavail.getOptions();
for(WebElement availoptions:availabletab)
{
	if(availoptions.getText().contains(string))
	{
		textCheck(availoptions,string);
		break;
	}
}

}

public static void selectTextData(WebElement element, String text)
{
	Select selectCity = new Select(element);
	selectCity.selectByVisibleText(text);
}

public static void selectByIndexData(WebElement element, int index)
{
	Select selectCity = new Select(element);
	selectCity.selectByIndex(index);
}

public static void verifyOption(List <WebElement> tabs,  String string)
{
	
	for(WebElement opelement:tabs)
	{
		if(opelement.getText().contains(string))
		{
			System.out.println(string +" option not removed");
			break;
		}
		
	}
	System.out.println(string+" option removed");
}
public static void dateFormatCheck(WebElement element) throws ParseException
{
	String datestring = element.getText();
	SimpleDateFormat sdf = new SimpleDateFormat("EEEE MMMM dd, yyyy");
    Date date = sdf.parse(datestring);
    System.out.println(sdf.format(date));
   if (!datestring.equals(sdf.format(date))) 
      System.out.println("The date is not in format");
   else
   	System.out.println("The date is in format");  
}
public static void getTitle(String string) throws InterruptedException
{
	String homeTitle=driver.getTitle();
	Thread.sleep(1000);
	if(homeTitle.contains(string))
		System.out.println(string+" Home page loaded");
	else
		System.out.println(string+" Home page not loaded");
	/*if (homeTitle.contains("Salesforce - Professional Edition"))
		System.out.println("Logged into SalesForce");
	else
		System.out.println("Not Logged into SalesForce");*/
	
}
public static void elementFocusCheck(WebElement element, String string)
{
	if(element.equals(driver.switchTo().activeElement()))
		System.out.println("Focus is on "+string);
	else
		System.out.println("Focus is not on "+string);	
}

public static void jsExecutor(WebElement element)
{
	JavascriptExecutor executor = (JavascriptExecutor)driver;
	executor.executeScript("arguments[0].click();", element);
}
public static void isCheckboxChecked(WebElement element, String string)
{
	clickElement(element);
	if(element.isSelected())
		System.out.println(string+ " Checkbox is checked");
	else
		System.out.println(string+ " Checkbox is not checked");
}
public static void mouseOver(WebElement element) {
	waitUntilVisible(element);
	Actions action=new Actions(driver);
	action.moveToElement(element).build().perform();
}

public static void mouseOverClick(WebElement element) {
	waitUntilVisible(element);
	Actions action=new Actions(driver);
	action.click(element).perform();
	
}

public static void dropDownElements(String path) {
	List <WebElement> umenu = driver.findElements(By.xpath(path));
	for(WebElement e: umenu)
	{
		System.out.println("Drop down menu options are \n"+e.getText());
	}
}

public static void tearDown()
{
	driver.close();
}

public static void closeAll() 
{
	driver.quit();
}

}

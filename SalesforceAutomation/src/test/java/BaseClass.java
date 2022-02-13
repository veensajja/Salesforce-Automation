import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

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

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static WebDriver driver;


	public BaseClass()
	{
	}

public static String readProperty(String key) throws IOException {
		
		FileInputStream fs = new FileInputStream(new File(".//src//test/resources//SalesForceData.properties"));
		Properties p = new Properties();
		p.load(fs);
		String value = p.getProperty(key);
		//System.out.println(value);
		return value;
	}

	
	public static void setUp() {
		WebDriverManager.chromedriver().setup();
		  driver = new ChromeDriver();
		}
public static void salesLogin(String url)
{
	setUp();
	driver.get(url);
}

public static void SalesLoginFull(String url, String uname, String pname) throws InterruptedException
{
	
	setUp();
	driver.get(url);
	WebElement username = driver.findElement(By.xpath("//input[@id='username']"));
	username.clear();
	username.sendKeys(uname);
	WebElement pwd = driver.findElement(By.xpath("//input[@id='password']"));
	pwd.clear();
	pwd.sendKeys(pname);
	WebElement loginButton = driver.findElement(By.xpath("//input[@id='Login']"));
	loginButton.click();

	Thread.sleep(3000);
	driver.findElement(By.className("dialogClose")).click();
	
	String homeTitle=driver.getTitle();
	if (homeTitle.contains("Home"))
		System.out.println("Logged into SalesForce");
	
}
	
public static void RememberMe(String url, String uname, String pname) throws InterruptedException
{
	setUp();
	driver.get(url);
	WebElement username = driver.findElement(By.xpath("//input[@id='username']"));
	username.clear();
	username.sendKeys(uname);
	WebElement pwd = driver.findElement(By.xpath("//input[@id='password']"));
	pwd.clear();
	pwd.sendKeys(pname);

	WebElement rem = driver.findElement(By.xpath("//input[@id='rememberUn']"));
	rem.click();
	WebElement loginButton = driver.findElement(By.xpath("//input[@id='Login']"));
	loginButton.click();
	
	WebDriverWait wait = new WebDriverWait(driver, 30);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("dialogClose")));
	driver.findElement(By.className("dialogClose")).click();

	String homeTitle=driver.getTitle();
	if (homeTitle.contains("Home"))
		System.out.println("Logged into SalesForce");
	else
		System.out.println("Not Logged into SalesForce");
	
	
	Actions action = new Actions(driver);
	WebElement userMenu = driver.findElement(By.xpath("//span[@id='userNavLabel']"));
	action.click(userMenu).perform();
	WebElement logout = driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));    
	logout.click();
			
	
	Thread.sleep(1000);
	WebElement rem1 = driver.findElement(By.xpath("//input[@id='rememberUn']"));
	if(rem1.isSelected())
		System.out.println("Remember Me box is checked");
	else
		System.out.println("Remember Me box is not checked");
			
	WebElement username1 = driver.findElement(By.xpath("//span[@id='idcard-identity']"));
	if (username1.getText().equalsIgnoreCase("veensajja-hpwj@force.com"))
		System.out.println("Username is remembered");
	else
		System.out.println("Username is not remembered");		
	
}

public static void ForgotPass(String url, String uname) throws InterruptedException
{
	setUp();
	driver.get(url);
	WebElement forgot = driver.findElement(By.xpath("//a[@id='forgot_password_link']"));
	forgot.click();
	
	WebDriverWait wait = new WebDriverWait(driver, 30);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@id='header']")));
	WebElement forgotPage = driver.findElement(By.xpath("//h2[@id='header']"));
			
	if(forgotPage.getText().equalsIgnoreCase("Forgot Your Password"))
		System.out.println("Forgot Your Password Page is loaded");
	else
		System.out.println("Forgot Your Password Page is not loaded");
	
	WebElement username = driver.findElement(By.xpath("//input[@id='un']\n"));
	username.sendKeys(uname);
	WebElement cont = driver.findElement(By.xpath("//input[@id='continue']"));
	cont.click();
	
	WebElement reset = driver.findElement(By.xpath("//h2[@id='header']\n"));
	if(reset.getText().equalsIgnoreCase("Check Your Email"))
		System.out.println("Email is sent with a password reset link");
	else
		System.out.println("Email is not sent with a password reset link");

}

public static void Wrong(String url)
{
	setUp();
	driver.get(url);
	WebElement username = driver.findElement(By.xpath("//input[@id='username']"));
	username.clear();
	username.sendKeys("123");
	WebElement pwd = driver.findElement(By.xpath("//input[@id='password']"));
	pwd.clear();
	pwd.sendKeys("22131");
	WebElement loginButton = driver.findElement(By.xpath("//input[@id='Login']"));
	loginButton.click();
	
	WebElement wrong = driver.findElement(By.xpath("//div[@id='error']"));
	if (wrong.getText().contains("Please check your username and password."))
		System.out.println("Login attempt failed error is dispalyed");
	else
		System.out.println("Login attempt failed error is not dispalyed");	
	
}

public static void UserMenu(String url,String uname,String pname, String mname) throws InterruptedException
{
	
	SalesLoginFull(url,uname,pname);
	Actions action = new Actions(driver);
	WebElement userMenu = driver.findElement(By.xpath("//span[@id='userNavLabel']"));
	if(userMenu.getText().contains(mname))
		System.out.println("Usermenu is dispalyed");
	else
		System.out.println("Usermenu is not dispalyed");	
	action.click(userMenu).perform();
	
	
	List <WebElement> umenu = driver.findElements(By.xpath("//div[@id='userNavMenu']"));
	for(WebElement e: umenu)
	{
		System.out.println(e.getText());
	}
			
}

public static void Profile(String url,String uname,String pname, String mname) throws InterruptedException, AWTException
{
	UserMenu(url,uname,pname,mname);
	
	Actions action = new Actions(driver);
	
	WebElement userMenu = driver.findElement(By.xpath("//span[@id='userNavLabel']"));
	userMenu.click();
	action.click(userMenu).perform();
	WebElement prof = driver.findElement(By.xpath("//a[contains(text(),'My Profile')]"));    
	prof.click();
	
	
	WebDriverWait wait = new WebDriverWait(driver, 30);
	//WebElement About = driver.findElement(By.xpath("//tbody/tr[1]/td[1]/div[1]/div[2]/li[1]/div[1]/div[1]/a[1]"));
	//wait.until(ExpectedConditions.visibilityOfElementLocated(About)));
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody/tr[1]/td[1]/div[1]/div[2]/li[1]/div[1]/div[1]/a[1]")));
	WebElement About = driver.findElement(By.xpath("//tbody/tr[1]/td[1]/div[1]/div[2]/li[1]/div[1]/div[1]/a[1]"));
	//wait.until(ExpectedConditions.visibilityOf(About));
	if(About.getText().contains("About Me"))
		System.out.println("Profile Page is loaded");
	else
		System.out.println("Profile Page is not loaded");

	Thread.sleep(1000);
	WebElement edit = driver.findElement(By.xpath("//tbody/tr[1]/td[1]/div[1]/div[2]/div[2]/div[1]/h3[1]/div[1]/div[1]/a[1]/img[1]"));
	edit.click();

	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@id='contactInfoTitle']")));
	WebElement profDiag = driver.findElement(By.xpath("//h2[@id='contactInfoTitle']"));
	if (profDiag.getText().contains("Edit Profile"))
		System.out.println("Edit Profile Dialog Box is open");
	else
		System.out.println("Edit Profile Dialog Box is not open");
	
	WebElement editProfileFrame = driver.findElement(By.xpath("//*[@id='contactInfoContentId']"));
			driver.switchTo().frame(editProfileFrame);

		
	WebElement contact = driver.findElement(By.xpath("//a[contains(text(),'Contact')]"));
	if(contact.isEnabled())
		System.out.println("Contact Tab is dispalyed");
	else
		System.out.println("Contact Tab is not dispalyed");
		
	WebElement about = driver.findElement(By.xpath("//a[contains(text(),'About')]"));

	if(about.isEnabled())
		System.out.println("About Tab is dispalyed");
	else
		System.out.println("About Tab is not dispalyed");
	
	about.click();
	
	WebElement lastname = driver.findElement(By.xpath("//input[@id='lastName']"));
	lastname.clear();
	lastname.sendKeys("last");
	
	WebElement save = driver.findElement(By.xpath("//body/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/input[1]"));
	save.click();
	Thread.sleep(1000);
	WebElement changeUsername = driver.findElement(By.xpath("//span[@id='tailBreadcrumbNode']"));
	//wait.until(ExpectedConditions.visibilityOf(changeUsername));
	if(changeUsername.getText().contains("last"))
		System.out.println("THe last name changed on user profile page");
	else
		System.out.println("THe last name hasn't changed on user profile page");
		
	WebElement post = driver.findElement(By.xpath("//a[@id='publisherAttachTextPost']"));	
	post.click();
	
	driver.switchTo().frame(0); 
	WebElement postFrameText = driver.findElement(By.xpath("//body"));
	postFrameText.sendKeys("FrameText");
	
	driver.switchTo().defaultContent();
	WebElement shareAll = driver.findElement(By.xpath("//input[@id='publishersharebutton']"));
	shareAll.click();
	
	WebElement postText = driver.findElement(By.xpath("//span/p"));

	if (postText.getText().contains("FrameText"))
		System.out.println("The posted text is saved");
	else
		System.out.println("The posted text is not saved");
	
	Thread.sleep(1000);
	WebElement file = driver.findElement(By.xpath("//span[normalize-space()='File']"));
	file.click();

	
	Thread.sleep(1000);
	WebElement uploadButton = driver.findElement(By.xpath("//a[@id='chatterUploadFileAction']"));
	JavascriptExecutor executor = (JavascriptExecutor)driver;
	executor.executeScript("arguments[0].click();", uploadButton);
	

	Thread.sleep(1000);
	WebElement chooseFile = driver.findElement(By.xpath("//input[@id='chatterFile']"));
	action.build().perform();
	//action.click(chooseFile).perform();
	Thread.sleep(1000);
	chooseFile.sendKeys("/Users/praveenastandard/Downloads/1.jpg");
	/*Robot robot = new Robot();
    robot.keyPress(KeyEvent.VK_CANCEL);
    robot.keyRelease(KeyEvent.VK_CANCEL);*/
	Thread.sleep(1000);
	WebElement share = driver.findElement(By.xpath("//input[@id='publishersharebutton']"));
	share.click();
	
	WebElement ImageFile = driver.findElement(By.xpath("//span[contains(text(),'Download')]"));
	if(ImageFile.isDisplayed())
		System.out.println("File is uploaded");
	else
		System.out.println("File is not uploaded");
	Thread.sleep(5000);
	WebElement addPhoto1 = driver.findElement(By.xpath("//span[@id='displayBadge']"));
	action.build().perform();
	WebElement addPhoto2 = driver.findElement(By.xpath("//a[@id='uploadLink']"));
	action.click(addPhoto2).perform();
	Thread.sleep(1000);
	driver.switchTo().frame(1);
	WebElement photoUpload = driver.findElement(By.xpath("//input[@id='j_id0:uploadFileForm:uploadInputFile']"));
	//action.click(photoUpload).perform();
	action.build().perform();
	Thread.sleep(1000);
	photoUpload.sendKeys("/Users/praveenastandard/Downloads/1.jpg");
	Thread.sleep(1000);
	
	
	WebElement savePhoto = driver.findElement(By.id("j_id0:uploadFileForm:uploadBtn"));
	savePhoto.click();
	
	//div[@class='imgCrop_selArea']//div[@class='imgCrop_clickArea']
	

  /*  action.clickAndHold(driver.findElement(By.xpath("//div[@class='imgCrop_selArea']//div[@class='imgCrop_clickArea']")));
    action.moveToElement(driver.findElement(By.xpath("//div[@class='imgCrop_selArea']//div[@class='imgCrop_clickArea']")), 5, 5);
    action.perform();
    Thread.sleep(1000); //note the sleep here. magic sleep.
    action.release(driver.findElement(By.xpath("//div[@class='imgCrop_selArea']//div[@class='imgCrop_clickArea']")));
    action.perform();*/
    
    Thread.sleep(5000);
    
    
   WebElement savePhoto1 = driver.findElement(By.xpath("//input[@id='j_id0:j_id7:save']"));
	savePhoto1.click();
	Thread.sleep(5000);
	WebElement image = driver.findElement(By.xpath("//span[@class='profileImage chatter-avatarFull chatter-avatar']//img[@title='praveena last']"));
	String imgVerify = image.getAttribute("src");
	
	if(imgVerify.contains("7298"))
		System.out.println("The profile photo has been uploaded");
		else
			System.out.println("The profile photo has not been uploaded");
			
}

public static void tearDown() {
  driver.quit();
}

}

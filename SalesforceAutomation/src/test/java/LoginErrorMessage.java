//import static Login.*;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginErrorMessage extends BaseClass {
	
	//private static final String url = "https://login.salesforce.com";
	public LoginErrorMessage() {
		//this.webDriver = d;
		
	}

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub

		String url =readProperty("url");
		salesLogin(url);
	Thread.sleep(3000);
	WebElement username = driver.findElement(By.xpath("//input[@id='username']"));
	username.clear();
		username.sendKeys("veensajja-hpwj@force.com");
		WebElement pwd = driver.findElement(By.xpath("//input[@id='password']"));
	pwd.clear();
	WebElement loginButton = driver.findElement(By.xpath("//input[@id='Login']"));
	loginButton.click();
	String errMsg = driver.findElement(By.xpath("//div[@id='error']")).getText();
	String expMsg = "Please enter your password.";
	
	if (errMsg.equalsIgnoreCase(expMsg))
			System.out.println("TestScript has passed");
	else
		System.out.println("TestScript has failed");
	
	tearDown();
	}

}

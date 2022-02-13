
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SalesForceLogin extends BaseClass{
	public static WebDriver driver;
	
public static void main(String[] args) throws InterruptedException, IOException {
	// TODO Auto-generated method stub

	String url =readProperty("url");
	String uname =readProperty("uname");
	String pname =readProperty("pname");

	SalesLoginFull(url,uname,pname);
	
	tearDown();
	
}
}








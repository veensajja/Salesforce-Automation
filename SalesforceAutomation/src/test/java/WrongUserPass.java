import java.io.IOException;

import org.openqa.selenium.By;


public class WrongUserPass extends BaseClass {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String url =readProperty("url");
		Wrong(url);
		tearDown();
	}

}

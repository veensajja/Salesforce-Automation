import java.io.IOException;

public class CheckRememberMe extends BaseClass{

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		String url =readProperty("url");
		String uname =readProperty("uname");
		String pname =readProperty("pname");

		RememberMe(url,uname,pname);
		tearDown();
		
		
	}

}

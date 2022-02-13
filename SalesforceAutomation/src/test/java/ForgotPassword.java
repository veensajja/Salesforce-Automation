import java.io.IOException;

public class ForgotPassword extends BaseClass{

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		String url =readProperty("url");
		String uname =readProperty("uname");
		
		ForgotPass(url,uname);
		tearDown();
		
	}

}

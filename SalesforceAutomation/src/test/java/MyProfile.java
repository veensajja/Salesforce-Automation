import java.awt.AWTException;
import java.io.IOException;

public class MyProfile extends BaseClass{

	public static void main(String[] args) throws IOException, InterruptedException, AWTException {
		// TODO Auto-generated method stub

		String url =readProperty("url");
		String uname =readProperty("uname");
		String pname =readProperty("pname");
		String mname =readProperty("mname");
		
		Profile(url,uname,pname,mname);
		
		tearDown();
	}

}

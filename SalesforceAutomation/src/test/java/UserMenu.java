import java.io.IOException;

public class UserMenu extends BaseClass{

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String url =readProperty("url");
		String uname =readProperty("uname");
		String pname =readProperty("pname");
		String mname =readProperty("mname");
		
		UserMenu(url,uname,pname,mname);
		
		tearDown();
		
	}

}

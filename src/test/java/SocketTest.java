import com.chiho.itchat4java.Shell;
import org.junit.Assert;
import org.junit.Test;

public class SocketTest {

	Shell shell = Shell.getInstance();

	@Test
	public void socketTest() {
		shell.run();
		try {
			Assert.assertEquals(true, shell.login());
			while(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

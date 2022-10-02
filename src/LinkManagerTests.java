import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;

public class LinkManagerTests {
	
	@Test
	public void linkManagerSetup() {
		LinkManager lm = new LinkManager();
		assertEquals(true, lm.getHashMap().containsKey("Frontline Evaluations"));
		assertEquals(true, lm.getHashMap().containsKey("Frontline Absence Management"));
		assertEquals(true, lm.getHashMap().containsKey("Skyward Grades"));
		assertEquals(true, lm.getHashMap().containsKey("Skyward Attendance"));
	}
	
	@Test
	public void linkManagerSetupExtraLink() {
		ArrayList<String> newParams = new ArrayList<String>();
		newParams.add("hello world");
		newParams.add("helloword.com");
		newParams.add("hello");
		newParams.add("world");
		FileManager.writeToFile(newParams);
		LinkManager lm = new LinkManager();
		assertEquals(true, lm.getHashMap().containsKey("hello world"));
		
	}
	
//	Only works if the file is new and the previous 2 tests are not run
	@Test
	public void linkManagerDeleteLink() {
		LinkManager lm = new LinkManager();
		lm.deleteLink("Skyward Grades");
		assertEquals(null, lm.getLink("Skyward Grades"));
	}
}
import org.junit.*;
import java.util.Scanner;
import static org.junit.Assert.fail;
import java.util.ArrayList;

public class NewQuickLinkTest {
	@Test
	// purpose: Check if the code adds a particular quickLink to the QuickLink HashMap
	// input: username = gaming, password = gaming, url = gaming, name = gaming
	// output: There is a quickLink stored in the HashMap.
	public void doesCodeAddQuickLink() {
		// create quickLink
		LinkManager manager = new LinkManager();
		NewQuickLink.createNewQuickLink("gaming", "gaming", "gaming", "gaming", manager);
		QuickLinks link = manager.getLink("gaming");
		//retrieve
		if (link == null) {
			Assert.fail("QuickLink wasn't even in linkManager Hashmap");
		} else {
			String[] expected = {"gaming", "gaming", "gaming", "gaming"};
			String[] results = new String[4];
			results[0] = link.getUsername();
			results[1] = link.getPassword();
			results[2] = link.getUrl();
			results[3] = link.getName();
			Assert.assertEquals(expected, results);
		}

	}
	
	@Test
	// purpose: Tests if write onto file
	// input: username = gaming, password = gaming, url = gaming, name = gaming
	// output: gaming, gaming, gaming.com, and gaming is written onto file
	public void doesCodeWriteOntoFile() {
		// createNewLink
		LinkManager manager = new LinkManager();
		NewQuickLink.createNewQuickLink("gaming", "gaming", "gaming", "gaming", manager);
		// go though each set on the file and find one with name gaming
		ArrayList<String> set = FileManager.getNextSet();
		while (set != null && !set.get(0).contentEquals("gaming")) {
			set = FileManager.getNextSet();
		}
		// check if the set is in there, if not wasn't written to file
		if (set == null) {
			Assert.fail("The QuickLink wasn't in the file in the first place");
		}
		
		// If it's in file return true
		String[] expected = {"gaming", "gaming", "gaming", "gaming"};
		String[] actual = {set.get(0), set.get(1), set.get(2), set.get(3)};
		Assert.assertArrayEquals("wrong values inputted", expected, actual);
	}
}

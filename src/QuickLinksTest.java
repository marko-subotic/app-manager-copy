import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.lang.StringBuilder;

//tests the QuickLinks class.

public class QuickLinksTest {

	//the other get methods are the same
	//so i'm just making one test method
	//for all of the get methods  ((: <3
	@Test	
	void testGetUsername() {
		String username = stringGen(16);		
		System.out.println(username);
		
		QuickLinks test = new QuickLinks(username, "", "", "");
		
		assertTrue(test.getUsername() == username);
	}
	
	
	//the other set methods are the same
	//so i'm just making one test method
	//for all of the set methods  ((: <3
	@Test
	void testSetUsername() {		
		String username = stringGen(16);
		System.out.println("\n" + username);
		
		QuickLinks test = new QuickLinks("ilovebees", "", "", "");
		System.out.println(test.getUsername());
		
		test.setUsername(username);
		
		assertTrue(test.getUsername() == username);
	}
	
	private static String stringGen(int length) {
		StringBuilder tester = new StringBuilder(length);
		
		for (int i = 0; i < length; i++) {
			tester.append((char) (Math.random() * 94) + 20);
		}
		
		return tester.toString();
	}
}


import org.junit.*;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class LinkManagerCredentialsTests {
	@Test
	// purpose: checks if add credentials replaces clickLink and adds new credentials
	// input: run addLink with QuickLink containing name = gaming, user = gaming,
	// password = gaming, and url = gaming. Then run addCredentials with name = gaming,
	// user = array, password = array
	// output: HashMap link with name gaming, has username of array and password of array
	public void doesAddCedentialsChangeLink() {
		// set up link
		LinkManager manager = new LinkManager();
		QuickLinks link = new QuickLinks("gaming", "gaming", "gaming", "gaming");
		manager.addLink(link);
		// run addCedentials
		manager.addCredentials("gaming", "array", "array");
		// test
		QuickLinks outputLink = manager.getLink("gaming");
		String[] output = {outputLink.getUsername(), outputLink.getPassword()};
		String[] expected = {"array", "array"};
		Assert.assertArrayEquals(output, expected);
	}
	
	
	@Test
	// purpose: checks if the method returns true if the username and password were input
	// input: run addlink with QuickLink containing name = gaming, useranme = gaming, 
	// password = gaming, and url gaming. THen run addCredentials with name = gaming, user = bruh,
	// password = bruh.
	// output: returns true the changes
	public void doesAddCedentialsReturnTrueIfUserPassInputted() {
		// set up link
		LinkManager manager = new LinkManager();
		QuickLinks link = new QuickLinks("gaming", "gaming", "gaming", "gaming");
		manager.addLink(link);
		// test
		Assert.assertTrue(manager.addCredentials("gaming", "bruh", "bruh"));
	}
	

	@Test
	// purpose: check if URL is not changed when QuickLink is changed
	// input: run addlink with QuickLink containing name = gaming, username = gaming, 
	// password = gaming, and url gaming.com. THen run addCredentials with name = gaming, user = go,
	// password = go.
	// output: the URL is still "gaming"
	public void doesAddCedentialsKeepTheUrl() {
		// set up link
		LinkManager manager = new LinkManager();
		QuickLinks link = new QuickLinks("gaming", "gaming", "gaming.com", "games");
		manager.addLink(link);
		// test
		manager.addCredentials("games", "go", "go");
		QuickLinks changedLink = manager.getLink("games");
		Assert.assertEquals("gaming.com", changedLink.getUrl());
	}
	
	@Test 
	// purpose: check if the username and file is written onto file 
	// input: addlink with name = "jargon", username = "gaming", password = "gaming", url = "gaming.com"
	// Runs addCredentials with name = "gaming", user = "go", pass = "go"
	// output: in file "jargon"'s  username and password are both changed to go, go
	public void doesAddCedentialsChangeFile() {
		// set up QuickLink, LinkManager, and File
		FileManager.createDefaultFile(new File("infoFile"));
		LinkManager manager = new LinkManager();
		QuickLinks link = new QuickLinks("gaming", "gaming", "gaming", "jargon");
		manager.addLink(link);
		ArrayList<String> parameters = new ArrayList<String>();
		parameters.add("jargon");
		for (int x = 0; x < 3; x++) {
			parameters.add("gaming");
		}
		FileManager.writeToFile(parameters);
		// run addCredentials
		manager.addCredentials("jargon", "go", "go");
		// look though file to see find the QuickLink
		ArrayList<String> set = FileManager.getNextSet();
		while (set != null && !set.get(0).equals("jargon")) {  // <-- need something to handle if Jargon doesn't exist
			set = FileManager.getNextSet();
		}
		// check if on file and if addCredentials actually change password and username
		if (set == null) {
			Assert.fail("QuickLink wasn't in file in the first place");
		}
		// if in file did it change
		String[] expected = {"go", "go"};
		String[] actual = {set.get(2), set.get(3)};
		String displayString = "expected user: go and pass: go but got, pass:" + actual[0] + "user: " + actual[1];
		Assert.assertArrayEquals(displayString, expected, actual);	
	}
}

import java.util.ArrayList;

public class NewQuickLink {
	
	// purpose: createNewQuickLink takes in a Name, URL, usernames, and password and
	// both returns a quickLink with those parameters and uses the textfilemanager to write
	// the quick link into files. 
	// input: Name, URL, username, and password
	// output: returns a QuickLinks with the inputted Name, URL, username, and password
	public static boolean createNewQuickLink(String username, String password, String url, String name, LinkManager linkmanager) {
		// create array with parameters 
		ArrayList<String> parameters = new ArrayList<String>();
		parameters.add(name);
		parameters.add(url);
		parameters.add(username);
		parameters.add(password);
		
		// put QuickLink into LinkManager quick links hashMap
		QuickLinks link = new QuickLinks(username, password, url, name);
		boolean rtrn = linkmanager.addLink(link);
		
		// input into manager
		FileManager.writeToFile(parameters);
		
		return rtrn;
	}

}

// make and save links
public class QuickLinks {
	//i'm not sure if we're doing encryption, 
	//so username and password will be plaintext for now.
	private String username;
	private String password;
	private String url;
	private String name;
	
	public QuickLinks (String username, 
					   String password, 
					   String url, 
					   String name) {
		this.username = username;
		this.password = password;
		this.url = url;
		this.name = name;
	}
	
	public String getPassword () {
		return password;
	}
	
	public String getUrl() {
		return url;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getName() {
		return name;
	}
	
	public void setUsername(String newUsername) {
		username = newUsername;
	}
	
	public void setPassword(String newPassword) {
		password = newPassword;
	}
	
	public void setUrl(String newUrl) {
		url = newUrl;
	}
	
	public void setName(String newName) {
		name = newName;
	}	
}

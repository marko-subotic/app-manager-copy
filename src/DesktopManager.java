import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;


//This entire class uses Selenium, a library for automating browsers.
// The Selenium website and library can be found here: https://www.selenium.dev/
public class DesktopManager {

	private WebDriver driver;
	private String originalWindow;
	private String popUpWindowHandle;
	private final String[] pearsonIDs = {"username", "password"};
	private final String[] APIDs = {"username", "password"};
	private final int GRADEBOOK_INDEX = 1;
	private final int ATTENDANCE_INDEX = 2;
	
	//Initializes the webdriver and finds where chromedriver is in the local library, opens up
	//A home tab of lwsd.org
	public DesktopManager() {
		System.setProperty("webdriver.chrome.driver", ".\\libs\\chromedriver.exe");
		driver = new ChromeDriver();
		originalWindow = driver.getWindowHandle();
		popUpWindowHandle = "DNE";
		driver.get("https://lwsd.org");
	}
	
	//This opens a single link in the home window
	public void openLink(QuickLinks customLink) {
//		this is to ensure the driver is not on the skyward pop-up window
		if(popUpWindowHandle.contentEquals(driver.getWindowHandle())) {
			try {
				switchFromPopUpWindow();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		driver.switchTo().newWindow(WindowType.TAB);
		
		//		driver.switchTo().newTAB(WindowType.TAB);
		driver.get(customLink.getUrl());
	}
	
	//This method should only ever be called once
	//opens skyward login and enters the credentials after finding the html ids for each login box
	private void loginSkyward(QuickLinks skyward) throws InterruptedException{
		//stores how many tabs are before pop-up is created
		int numTabs = driver.getWindowHandles().size();
		
		openLink(skyward);
		WebElement loginUser = driver.findElement(By.id("login"));
		loginUser.sendKeys(skyward.getUsername());
		WebElement loginPass = driver.findElement(By.id("password"));
		loginPass.sendKeys(skyward.getPassword()+ Keys.ENTER);
		
		//waits until a new window has been created
		while(numTabs+1==driver.getWindowHandles().size()) {
			Thread.sleep(500);
		}
		//this loops through all window handles (including tabs) and should end up with popUpWindow
		//being set to the final windowHandle (which is skyward pop-up's windowHandle)
		for (String windowHandle : driver.getWindowHandles()) {
		    popUpWindowHandle = windowHandle;
		}
	}

	
//	This switches the active window away from the current window, meant to switch between
//	skyward popup window and the home window where all other logins occur
	private void switchToPopUpWindow() throws InterruptedException{
		//loops through to a new window (where the skyward pop-up will be
		for (String windowHandle : driver.getWindowHandles()) {
		    if(popUpWindowHandle.contentEquals(windowHandle)) {
		        driver.switchTo().window(windowHandle);
		        break;
		    }
		}
	}
	
//	This switches the active window away from the current window, meant to switch between
//	skyward popup window and the home window where all other logins occur
	private void switchFromPopUpWindow() throws InterruptedException{
		//waits until a new window has been created
		while(driver.getWindowHandles().size()==1) {
			Thread.sleep(50);
		}
		driver.switchTo().window(originalWindow);
	}
	
	//finds skyward menu and selects the menu item given the index
	private void selectSkywardMenu(QuickLinks skywardLink, int itemIndex) throws InterruptedException {
		//makes sure not to log into skyward more than once
		if(popUpWindowHandle.equals("DNE")) {
			loginSkyward(skywardLink);
		}
		if(!popUpWindowHandle.contentEquals(driver.getWindowHandle())) {
			System.out.print("switching to popup");
			switchToPopUpWindow();
		}
		
//		loops until the page loads and finds the menu webelement, then I create the list and choose
//		the wanted index
		WebElement menu;
		while(true) {
			try {
				menu = driver.findElement(By.id("sf_navMenu"));
				break;
			} catch(org.openqa.selenium.NoSuchElementException noSuchElementException) {
				Thread.sleep(50);
			}
		}
		List<WebElement> menuList = menu.findElements(By.tagName("li"));;
		WebElement menuItem =  menuList.get(itemIndex);
		int attempts = 0;
	    while(attempts < 4) {
	        try {
	        	menuItem.click();
	        	Thread.sleep(500);
	        } catch(org.openqa.selenium.StaleElementReferenceException e) {
	            break;
	        }
	        attempts++;
	    }

	}
	
	//logs into skyward and opens the student gradebook
	public void openSkywardGrading(QuickLinks skywardLink)  {
		try {
			selectSkywardMenu(skywardLink, GRADEBOOK_INDEX);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	//logs into skyward and opens student attendance
	public void openSkywardAttendance(QuickLinks skywardLink) {
		try {
			selectSkywardMenu(skywardLink, ATTENDANCE_INDEX);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	

//	This opens the login and uses finding by id to login, provided the html IDs by parameters
	private void loginCustomQuicklink(QuickLinks customLink, String usernameID, String passwordID) throws InterruptedException {
//		logs in with html IDs
		openLink(customLink);
		WebElement username;
		while(true) {
			try {
				username = driver.findElement(By.id(usernameID));
				break;
			} catch(org.openqa.selenium.NoSuchElementException noSuchElementException) {
				Thread.sleep(50);
			}
		}
		WebElement password = driver.findElement(By.id(passwordID));
		username.sendKeys(customLink.getUsername());
		password.sendKeys(customLink.getPassword() + Keys.ENTER);


	}
	
//	This is a method on its own to ensure modularity. if something changes, I can fix this
//	method and the user interface will work for this class
	public void loginPearson(QuickLinks pearsonLink) {
		try {
			loginCustomQuicklink(pearsonLink, pearsonIDs[0],pearsonIDs[1]);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	This is a method on its own to ensure modularity. if something changes, I can fix this
//	method and the user interface will work for this class
	public void loginAPClassroom(QuickLinks APLink) {
		try {
			loginCustomQuicklink(APLink, APIDs[0], APIDs[1]);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//Closes chromedriver
	public void killDesktopManager() {
		driver.quit();
	}
	
}
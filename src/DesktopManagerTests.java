import static org.junit.Assert.*;


import org.junit.Test;

public class DesktopManagerTests {
	DesktopManager runner = new DesktopManager();
	QuickLinks lwsdLink = new QuickLinks("yes", "yes", "https://lwsd.org", "lwsd website");
	QuickLinks skywardLink = new QuickLinks("subotmar000", "no seeing my password", "https://www2.saas.wa-k12.net/scripts/cgiip.exe/WService=wlkwashs71/fwemnu01.w", "lwsd website");
	QuickLinks APLink = new QuickLinks("yes", "yes", "https://myap.collegeboard.org/login", "AP Login");
	QuickLinks PearsonLink = new QuickLinks("yes", "yes", "https://login.pearson.com/v1/piapi/piui/signin?client_id=dN4bOBG0sGO9c9HADrifwQeqma5vjREy&okurl=https:%2F%2Fportal.mypearson.com%2Fcourse-home&siteid=8313", "Pearson");
	QuickLinks FrontlineLink = new QuickLinks("yes", "yes", "https://login.frontlineeducation.com/login?signin=bbda8029cdfe3c1be1c600742fa5c142&clientId=superSuit#/login", "Frontline");
//	@Test
//	public void openBrowser() {
//		runner.openLink(test1);
//		assertTrue("this shouldn't fail", true);
//	}
//	

	
//	@Test
//	public void gradingSkyward() {
//		runner.openSkywardGrading(skywardLink);
//		assertTrue("this shouldn't fail", true);
//	}
//	
//	@Test
//	public void attendanceSkyward() {
//		runner.openSkywardAttendance(skywardLink);
//		assertTrue("this shouldn't fail", true);
//	}
//	
//	@Test
//	public void loginAP() {
//		runner.loginAPClassroom(APLink);
//		assertTrue("this should never fail", true);
//	}
//	
//	@Test
//	public void loginPearson() {
//		runner.loginPearson(PearsonLink);
//		assertTrue("this should never fail", true);
//	}
//	
//	@Test
//	public void loginSkywardthenOpenLWSD() {
//		runner.openSkywardAttendance(skywardLink);
//		runner.openLink(lwsdLink);
//		assertTrue("this shouldn't fail", true);
//	}
}
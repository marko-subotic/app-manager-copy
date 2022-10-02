import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {
	private static final int LINES_PER_SET = 4;
	private static Scanner sc;
	private static File info;
	
	// Purpose: Gets the next set of LINES_PER_SET lines and returns them in an ArrayList to
	// LinkManager
	// Input: None
	// Output: ArrayList of the next LINES_PER_SET lines
	public static ArrayList<String> getNextSet() {
		try {
			if(info == null) {
				info = new File("infoFile.txt");
				createDefaultFile(info);
			}
			if(sc == null) {
				sc = new Scanner(info);
			}
			ArrayList<String> set = new ArrayList<String>();
			for(int i = 0 ; i < LINES_PER_SET ; i++) {
				if (sc.hasNextLine()) {
					set.add(sc.nextLine());
				} else {
					return null;
				}
			}
			return set;
		} catch (IOException e) {
			return null;
		}
	}
	
	// Purpose: Checks if there is a file in the folder already, and if there is not it will create
	// a file and load it with the default links
	// Input: None
	// Output: If there is no file, create file, returns true if a new file was created
	public static boolean createDefaultFile(File f)
	{
		try {
//			createNewFile tries to create a new file, if it does, returns true, if not, returns
//			false
			if(f.createNewFile()) {
				FileWriter fw = new FileWriter(f, true);
//				name
				fw.write("Skyward Grades\n");
//				URL
				fw.write("https://www2.saas.wa-k12.net/scripts/cgiip.exe/WService=wlkwashs71/fwemnu01.w\n");
//				username
				fw.write("\n");
//				password
				fw.write("\n");
				
				fw.write("Skyward Attendance\n");
				fw.write("https://www2.saas.wa-k12.net/scripts/cgiip.exe/WService=wlkwashs71/fwemnu01.w\n");
				fw.write("\n");
				fw.write("\n");
				
				fw.write("AP Classroom\n");
				fw.write("https://myap.collegeboard.org/login\n");
				fw.write("\n");
				fw.write("\n");
				
				fw.write("Pearson's Mastering Biology\n");
				fw.write("https://login.pearson.com/v1/piapi/piui/signin?client_id=dN4bOBG0sGO9c9HADrifwQeqma5vjREy&okurl=https:%2F%2Fportal.mypearson.com%2Fcourse-home&siteid=8313\n");
				fw.write("\n");
				fw.write("\n");
				
				fw.close();
				return true;
			} else {
				return false;
			}
		} catch (IOException e) {
			return false;
		}
	}
	
	// Purpose: use writeToFile but with a QuickLink instead of an ArrayList
	// Input: Quicklink
	// Output: call writeToFile (below)
	public static boolean writeToFile(QuickLinks ql) {
		ArrayList<String> arr = new ArrayList<String>();
		arr.add(ql.getName());
		arr.add(ql.getUrl());
		arr.add(ql.getUsername());
		arr.add(ql.getPassword());
		return writeToFile(arr);
	}
	
	// Purpose: Write a new set of information at the bottom of the file or where 1st parameter
	// (name) is found
	// Input: ArrayList of parameters to be added
	// Output: Returns false if no data was overridden, true if any data was overridden
	public static boolean writeToFile(ArrayList<String> parameters)
	{
		// create scanner and writer vars
		Scanner editingScanner;
		FileWriter fw;
		String name = parameters.get(0);
		
		try {
			// if no file already, make it
			if(info == null) {
				info = new File("infoFile.txt");
				createDefaultFile(info);
			}
			
			// create a temp file
			File temp = new File("tempFile.txt");
			
			// instantiate the scanner and writer
			editingScanner = new Scanner(info);
			fw = new FileWriter(temp);
			
			// create vars for upcoming loop
			int i = 0;
			boolean edited = false;
			
			while(editingScanner.hasNextLine()) {
				String nextLine = editingScanner.nextLine();
				
				if(i % LINES_PER_SET == 0 && nextLine.equals(name)) {
					
					edited = true;
					
					fw.write(nextLine + "\n");
					
					for(int j = 1 ; j < LINES_PER_SET ; j++) {
						if(editingScanner.hasNextLine()) {
							nextLine = editingScanner.nextLine();
							
							if(parameters.get(j).equals("")) {
								fw.write(nextLine + "\n");
							} else {
								fw.write(parameters.get(j) + "\n");
							}
							
							i++;
						} else {
							break;
						}
					}
				} else {
					fw.write(nextLine + "\n");
				}
			}
			
			if(!edited) {
				for(int j = 0 ; j < LINES_PER_SET ; j++) {
					fw.write(parameters.get(j) + "\n");
				}
			}
			
			editingScanner.close();
			fw.close();
			sc.close();
			
			info.delete();
			temp.renameTo(info);
			
			sc = new Scanner(info);
			
			return edited;
			
		} catch (FileNotFoundException e) {
			return false;
		} catch (IOException e) {
			return false;
		}
	}
	
	// Mostly copied from last method, does the same thing, except skips over lines after the
	// matching name
	// Input: Name
	// Output: Edited file
	public static boolean deleteSet(String name) {
		// create scanner and writer vars
		Scanner editingScanner;
		FileWriter fw;
		
		try {
			// if no file already, make it
			if(info == null) {
				info = new File("infoFile.txt");
				createDefaultFile(info);
			}
			
			// create a temp file
			File temp = new File("tempFile.txt");
			
			// instantiate the scanner and writer
			editingScanner = new Scanner(info);
			fw = new FileWriter(temp);
			
			// create vars for upcoming loop
			int i = 0;
			boolean edited = false;
			
			while(editingScanner.hasNextLine()) {
				String nextLine = editingScanner.nextLine();
				
				if(i % LINES_PER_SET == 0 && nextLine.equals(name)) {
					
					edited = true;
					i += LINES_PER_SET; // because we skip over several lines here
					for(int j = 0 ; j < LINES_PER_SET - 1 ; j++) { // advances the scanner to the next set
						nextLine = editingScanner.nextLine();
					}
					
				} else {
					fw.write(nextLine + "\n");
					i++;
				}
			}
			
			editingScanner.close();
			fw.close();
			sc.close();
			
			info.delete();
			temp.renameTo(info);
			
			sc = new Scanner(info);
			
			return edited;
			
		} catch (FileNotFoundException e) {
			return false;
		} catch (IOException e) {
			return false;
		}
	}
}

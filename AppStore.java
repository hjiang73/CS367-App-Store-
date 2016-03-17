import java.time.Instant;
import java.util.*;
import java.io.*;
///////////////////////////////////////////////////////////////////////////////
//ALL STUDENTS COMPLETE THESE SECTIONS
//Title:            Program1
//Files:            App.java, AppRating.java, User.java
//                  AppStore.java
//                  AppScoreComparator.java
//Semester:         CS367 Fall 2015
//
//Author:           Han Jiang
//Email:            hjiang73@wisc.edu
//CS Login:         hjiang
//Lecturer's Name:  Jim Skrentny
////////////////////////////80 columns wide //////////////////////////////////

/**
 * The application program, AppStore, creates and uses an AppStore to 
 * represent and process information. 
 * The user and app information is read from four input text files 
 * and then the program continues to process a set of user commands until
 * terminated.
 * <p>Bugs: None known
 * @author Han Jiang
 */

public class AppStore {
	//Data Field
	private static AppStoreDB appStoreDB = new AppStoreDB();
	private static User appUser = null;
	private static Scanner scanner = null;
	/**
	 *1.Checks whether four command-line arguments have been provided; 
	 *if not then quit
	 *2.Checks whether the input files exist and is readable; 
	 *if not, displays: “File <Filename> not found” and quit.
	 *3.Loads the data from the input files and uses it 
	 *to construct the database.
	 *4.Prompts for the user to enter command options and continues to 
	 *process them until the user enters the ‘q’ command. 
	 *Prompts should be as [anonymous@AppStore]$ 
	 *if the user is not logged in or as [<email>@AppStore]$ 
	 *if the user is logged in.
	 * 
	 */
	public static void main(String args[]) {
		if (args.length < 4) {			
			System.err.println("Bad invocation! Correct usage: "
					+ "java AppStore <UserDataFile> <CategoryListFile> "
					+ "<AppDataFile> <AppActivityFile>");
			System.exit(1);
		}

		boolean didInitialize = 
				initializeFromInputFiles(args[0], args[1], args[2], args[3]);

		if(!didInitialize) {
			System.err.println("Failed to initialize the application!");
			System.exit(1);
		}

		System.out.println("Welcome to the App Store!\n"
				+ "Start by browsing the top free and the top paid apps "
				+ "today on the App Store.\n"
				+ "Login to download or upload your favorite apps.\n");

		processUserCommands();
	}

	/**
	 * This method is to read file by using FileReader.First, it reads text from 
	 * sample files and store them into the arrays of Strings and them split them 
	 * into several data information that we need.
	 * @param userDataFile
	 * @param categoryListFile
	 * @param appDataFile
	 * @param appActivityFile
	 * return boolean if initialized
	 */
	private static boolean initializeFromInputFiles(String userDataFile, String 
			categoryListFile, String appDataFile, String appActivityFile){
		//Data Field
		String userLine;
		String catLine;
		String appLine;
		String appActLine;
		String s;
		String c;
		String a;
		String aa;
		String file1=userDataFile;
		String file2=categoryListFile;
		String file3=appDataFile;
		String file4=appActivityFile;
		List<String> userData = new ArrayList<String>();
		List<String> catData = new ArrayList<String>();
		List<String> appData = new ArrayList<String>();
		List<String> appActData= new ArrayList<String>();

		try{	
			FileReader fr1 = new FileReader(file1);//read text from file1 userdata
			BufferedReader br1 = new BufferedReader(fr1);
			userLine =br1.readLine();//read each line
			if (userLine==null){
				br1.close();
				throw new IllegalArgumentException();
				//if can not read,throw an exception and close filereader
			}
			while (userLine!=null){
				userData.add(userLine);//add the line to the array
				userLine =br1.readLine();//continue to read
			}
			br1.close();
		}catch(IOException exp1){
			return false;
		}catch(IllegalArgumentException exp2){
			return false; //catch checked exceptions
		}

		try{
			FileReader fr2=new FileReader(file2);//read text from file2 catergoriesdata
			BufferedReader br2 = new BufferedReader(fr2);
			catLine =br2.readLine();//read each line
			if (catLine==null){
				br2.close();
				throw new IllegalArgumentException();
				//if can not read,throw an exception and close filereader
			}
			while (catLine!=null){
				catData.add(catLine);//add the line to the array
				catLine=br2.readLine();//continue to read
			}
			br2.close();
		}catch(IOException exp1){
			return false;
		}catch(IllegalArgumentException exp2){
			return false;//catch checked exceptions
		}

		try{
			FileReader fr3=new FileReader(file3);//read text from file3 appdata
			BufferedReader br3 = new BufferedReader(fr3);
			appLine=br3.readLine();//read each line
			if (appLine==null){
				br3.close();
				throw new IllegalArgumentException();
				//if can not read,throw an exception and close filereader
			}
			while (appLine!=null){
				appData.add(appLine);//add the line to the array
				appLine=br3.readLine();//continue to read
			}
			br3.close();
		}catch(IOException exp1){
			return false;
		}catch(IllegalArgumentException exp2){
			return false;//catch checked exceptions
		}

		try{
			FileReader fr4=new FileReader(file4);//read text from file3 appdata
			BufferedReader br4 = new BufferedReader(fr4);
			appActLine=br4.readLine();//read each line
			if (appActLine==null){
				br4.close();
				throw new IllegalArgumentException();
				//if can not read,throw an exception and close filereader
			}
			while (appActLine!=null){
				appActData.add(appActLine);
				appActLine=br4.readLine();
			}
			br4.close();
		}catch(IOException exp1){
			return false;
		}catch(IllegalArgumentException exp2){
			return false;//catch checked exceptions
		}

		//split the string and get data to create database
		Iterator<String> itU = userData.iterator();//initialize iterator
		while (itU.hasNext()){
			s =itU.next();
			if (!s.equals(null)){
				String[] userdata = s.split(",");
				//split string
				String e=userdata[0];
				String pw=userdata[1];
				String fn=userdata[2];
				String ln=userdata[3];
				String ct=userdata[4];
				String t=userdata[5];
				//create a new user
				appStoreDB.addUser(e,pw,fn,ln,ct,t);
			}
		}	    

		Iterator<String> itC=catData.iterator();//initialize iterator
		while(itC.hasNext()){
			c =itC.next();
			if (!c.equals(null))
				appStoreDB.addCategory(c);//add a new category
		}     

		Iterator<String> itA=appData.iterator();//initialize iterator
		while(itA.hasNext()){
			a = itA.next();
			if (!a.equals(null)){
				//split string
				String[] appdata = a.split(",");
				User developer = appStoreDB.findUserByEmail(appdata[0]);
				String id = appdata[1];
				String name=appdata[2];
				String cat=appdata[3];
				Double p = Double.parseDouble(appdata[4]);
				Long ut = Long.parseLong(appdata[5]);
				//create a new app
				try{appStoreDB.uploadApp(developer,id,name,cat,p,ut);}
				catch(IllegalArgumentException ex){};
			}
		}
		// App has two kinds of activities: download and rate
		Iterator<String> itAA=appActData.iterator();//initialize iterator
		while(itAA.hasNext()){
			aa=itAA.next();
			if (!aa.equals(null)){
				//split string
				String[] actdata=aa.split(",");
				if (actdata[0].equals("d")){
					User u = appStoreDB.findUserByEmail(actdata[1]);
					App a1 = appStoreDB.findAppByAppId(actdata[2]);
					appStoreDB.downloadApp(u,a1);
					
					
					//download app
				}
				else if (actdata[0].equals("r")){
					User u = appStoreDB.findUserByEmail(actdata[1]);
					App a2 = appStoreDB.findAppByAppId(actdata[2]);
					Short r = Short.parseShort(actdata[3]);
					appStoreDB.hasUserDownloadedApp(u,a2);
					try{appStoreDB.rateApp(u,a2,r);}
					catch(IllegalArgumentException ex){
					}
				}

				//rate app
			}
		}


		return true;//if initialized, return true
	}


	private static void processUserCommands() {
		scanner = new Scanner(System.in);
		String command = null;		
		do {
			if (appUser == null) {
				System.out.print("[anonymous@AppStore]$ ");
			} else {
				System.out.print("[" + appUser.getEmail().toLowerCase() 
						+ "@AppStore]$ ");
			}
			command = scanner.next();
			switch(command.toLowerCase()) {
			case "l":
				processLoginCommand();
				break;

			case "x": 
				processLogoutCommand();
				break;

			case "s":
				processSubscribeCommand();
				break;

			case "v":
				processViewCommand();
				break;

			case "d":
				processDownloadCommand();
				break;

			case "r":
				processRateCommand();
				break;

			case "u":
				processUploadCommand();
				break;

			case "p":
				processProfileViewCommand();
				break;								

			case "q":
				System.out.println("Quit");
				break;
			default:
				System.out.println("Unrecognized Command!");
				break;
			}
		} while (!command.equalsIgnoreCase("q"));
		scanner.close();
	}


	private static void processLoginCommand() {
		if (appUser != null) {
			System.out.println("You are already logged in!");
		} else {
			String email = scanner.next();
			String password = scanner.next();
			appUser = appStoreDB.loginUser(email, password);
			if (appUser == null) {
				System.out.println("Wrong username / password");
			}
		}
	}


	private static void processLogoutCommand() {
		if (appUser == null) {
			System.out.println("You are already logged out!");
		} else {
			appUser = null;
			System.out.println("You have been logged out.");
		}
	}

	private static void processSubscribeCommand() {
		if (appUser == null) {
			System.out.println("You need to log in "
					+ "to perform this action!");
		} else {
			if (appUser.isDeveloper()) {
				System.out.println("You are already a developer!");
			} else {
				appUser.subscribeAsDeveloper();
				System.out.println("You have been promoted as developer");
			}
		}
	}

	private static void processViewCommand() {
		String restOfLine = scanner.nextLine();
		Scanner in = new Scanner(restOfLine);
		String subCommand = in.next();
		int count;
		String category;
		switch(subCommand.toLowerCase()) {
		case "categories":
			System.out.println("Displaying list of categories...");
			List<String> categories = appStoreDB.getCategories();
			count = 1;
			for (String categoryName : categories) {
				System.out.println(count++ + ". " + categoryName);
			}
			break;
		case "recent":				
			category = null;
			if (in.hasNext()) {
				category = in.next();
			} 
			displayAppList(appStoreDB.getMostRecentApps(category));				
			break;
		case "free":
			category = null;
			if (in.hasNext()) {
				category = in.next();
			}
			displayAppList(appStoreDB.getTopFreeApps(category));
			break;
		case "paid":
			category = null;
			if (in.hasNext()) {
				category = in.next();
			}
			displayAppList(appStoreDB.getTopPaidApps(category));
			break;
		case "app":
			String appId = in.next();
			App app = appStoreDB.findAppByAppId(appId);
			if (app == null) {
				System.out.println("No such app found with the given app id!");
			} else {
				displayAppDetails(app);
			}
			break;
		default: 
			System.out.println("Unrecognized Command!");
		}
		in.close();
	}

	private static void processDownloadCommand() {
		if (appUser == null) {
			System.out.println("You need to log in "
					+ "to perform this action!");
		} else {
			String appId = scanner.next();
			App app = appStoreDB.findAppByAppId(appId);
			if (app == null) {
				System.out.println("No such app with the given id exists. "
						+ "Download command failed!");
			} else {
				try {
					appStoreDB.downloadApp(appUser, app);
					System.out.println("Downloaded App " + app.getAppName());
				} catch (Exception e) {				
					System.out.println("Something went wrong. "
							+ "Download command failed!");
				}
			}
		}

	}

	private static void processRateCommand() {
		if (appUser == null) {
			System.out.println("You need to log in "
					+ "to perform this action!");
		} else {
			String appId = scanner.next();
			App app = appStoreDB.findAppByAppId(appId);
			if (app == null) {
				System.out.println("No such app with the given id exists. "
						+ "Rating command failed!");
			} else {
				try {
					short rating = scanner.nextShort();
					appStoreDB.rateApp(appUser, app, rating);
					System.out.println("Rated app " + app.getAppName());
				} catch (Exception e) {
					System.out.println("Something went wrong. "
							+ "Rating command failed!");
				}
			}
		}

	}

	private static void processUploadCommand() {
		if (appUser == null) {
			System.out.println("You need to log in "
					+ "to perform this action!");
		} else {
			String appName = scanner.next();
			String appId = scanner.next();
			String category = scanner.next();
			double price = scanner.nextDouble();
			long uploadTimestamp = Instant.now().toEpochMilli();
			try {
				appStoreDB.uploadApp(appUser, appId, appName, category, 
						price, uploadTimestamp);
			} catch (Exception e) {
				System.out.println("Something went wrong. "
						+ "Upload command failed!");
			}
		}
	}


	private static void processProfileViewCommand() {		
		String restOfLine = scanner.nextLine();
		Scanner in = new Scanner(restOfLine);
		String email = null;
		if (in.hasNext()) {
			email = in.next();
		}
		if (email != null) {
			displayUserDetails(appStoreDB.findUserByEmail(email));
		} else {
			displayUserDetails(appUser);
		}
		in.close();

	}


	private static void displayAppList(List<App> apps) {
		if (apps.size() == 0) {
			System.out.println("No apps to display!");
		} else {
			int count = 1;
			for(App app : apps) {
				System.out.println(count++ + ". " 
						+ "App: " + app.getAppName() + "\t" 
						+ "Id: " + app.getAppId() + "\t" 
						+ "Developer: " + app.getDeveloper().getEmail());
			}	
		}
	}

	private static void displayAppDetails(App app) {
		if (app == null) {
			System.out.println("App not found!");
		} else {
			System.out.println("App name: " + app.getAppName());
			System.out.println("App id: " + app.getAppId());
			System.out.println("Category: " + app.getCategory());
			System.out.println("Developer Name: " 
					+ app.getDeveloper().getFirstName() + " " 
					+ app.getDeveloper().getLastName());
			System.out.println("Developer Email: " 
					+ app.getDeveloper().getEmail());
			System.out.println("Total downloads: " + app.getTotalDownloads());
			System.out.println("Average Rating: " + app.getAverageRating());

			// show revenue from app if the logged-in user is the app developer
			if (appUser != null && 
					appUser.getEmail()
					.equalsIgnoreCase(app.getDeveloper().getEmail())) {
				System.out.println("Your Revenue from this app: $" 
						+ app.getRevenueForApp());
			}

		}		
	}

	private static void displayUserDetails(User user) {		
		if (user == null) {
			System.out.println("User not found!");
		} else {
			System.out.println("User name: " + user.getFirstName() + " "
					+ user.getLastName());
			System.out.println("User email: " + user.getEmail());
			System.out.println("User country: " + user.getCountry());

			// print the list of downloaded apps
			System.out.println("List of downloaded apps: ");			
			List<App> downloadedApps = user.getAllDownloadedApps();
			displayAppList(downloadedApps);

			// print the list of uploaded app
			System.out.println("List of uploaded apps: ");
			List<App> uploadedApps = user.getAllUploadedApps();
			displayAppList(uploadedApps);

			// show the revenue earned, if current user is developer
			if (appUser!= null 
					&& user.getEmail().equalsIgnoreCase(appUser.getEmail()) 
					&& appUser.isDeveloper()) {
				double totalRevenue = 0.0;
				for (App app : uploadedApps) {
					totalRevenue += app.getRevenueForApp();
				}
				System.out.println("Your total earnings: $" + totalRevenue);
			}

		}
	}
}


///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  AppStore.java
// File:             AppStoreDB.java
// Semester:         CS367 Fall 2015
//
// Author:           Han Jiang hjiang73@wisc.edu
// CS Login:         hjiang
// Lecturer's Name:  Jim Skrentny
//////////////////////////// 80 columns wide //////////////////////////////////
/**
 * This AppStoreDB class is used to create a database to store the data of all
 * apps,users and categories.It is the main data provider class for your app 
 * store application.It stores the list of all the apps and the users at 
 * the app store. It also implements additional functionalities to display
 *  various rankings of top apps in different categories.
 * <p>Bugs: None known
 *
 * @author Han Jiang
 */

import java.util.*;
/**
 * Create an object named AppStoreDB
 * Bugs: none known
 * @author Han Jiang
 */
public class AppStoreDB {

	//Data Field
	public List<User> users; 
	public List<App> apps;
	private List<String> categories;
	//Constructs an empty database.
	public AppStoreDB() {
		this.users = new ArrayList<User>();
		this.apps = new ArrayList<App>();
		this.categories = new ArrayList<String>();

	}

	/**
	 * Only adds a user to the database if the given user is 
	 * not present in the database, otherwise it throws an exception.
	 * @param email
	 * @param password
	 * @param firstName
	 * @param lastName
	 * @param country
	 * @param type
	 * @return User newUser
	 */
	public User addUser(String email, String password, String firstName,
			String lastName, String country, String type)
					throws IllegalArgumentException {
		User newUser = new User(email,password,firstName,lastName,country,type);
		//the user is validly created.
		if(users.contains(newUser)){throw new IllegalArgumentException();}
		//the given user is not present in the database
		else{users.add(newUser);//add the user to the database
		return newUser;}//return the user
	}

	/**
	 * Adds a category to the list of categories maintained.
	 * @param category
	 */
	public void addCategory(String category) {
		categories.add(category);
	}

	/**
	 * Returns the list of categories
	 * @return List categories
	 */
	public List<String> getCategories() {
		return categories;
	}

	/**
	 * Returns the user object for a given email. If not found, returns null.
	 * @param email
	 * @return User
	 */
	public User findUserByEmail(String email) {
		User founduser = null;
		Iterator<User> itr = users.iterator();
		//use iterator to find the item
		while(itr.hasNext()){
			User a = itr.next();
			if(a.getEmail().equals(email)){
				founduser = a;
			}
		}
		return founduser;	
	}

	/**
	 * Returns the app object for a given app id. If not found, returns null.
	 * @param appId
	 * @return App
	 */
	public App findAppByAppId(String appId) {
		App foundapp = null;
		Iterator<App> itr = apps.iterator();
		//use iterator to find the item
		while(itr.hasNext()){
			App b = itr.next();
			if(b.getAppId().equals(appId)){
				foundapp = b;
			}
		}
		return foundapp;

	}

	/**
	 * Logs in a user with a given email id and returns a user object for it, 
	 * only if the password given matches the given email id. Otherwise returns null.
	 * @param email
	 * @param password
	 * @return User
	 */
	public User loginUser(String email, String password) {
		User founduser = findUserByEmail(email);//to find the user
		if(founduser==null){return null;}//not find the user in the list
		else if(founduser.verifyPassword(password)){//verify the password
			return founduser;//log in
		}
		else {return null;}
	}

	/**
	 * Only adds valid apps to the database, otherwise it throws an exception.
	 * @param uploader
	 * @param appId
	 * @param appName
	 * @param category
	 * @param price
	 * @return App newApp
	 */
	public App uploadApp(User uploader, String appId, String appName,
			String category, double price, 
			long timestamp) throws IllegalArgumentException {
		if (uploader.equals(null)||appId.equals(null)||appName.equals(null)
				||category.equals(null))
			throw new IllegalArgumentException();
		if (!uploader.isDeveloper()){
			throw new IllegalArgumentException();
		}
		if (price < 0.0){
			throw new IllegalArgumentException();
		}
		//the app is validly created.
		else{App newApp =  new App(uploader,appId,appName,category,price,timestamp);
		//create app
		apps.add(newApp);//add to the list of app
		uploader.getAllUploadedApps().add(newApp);//add it to the upload list of user
		return newApp;}//return newApp
	}


	/**
	 * Allows a user to download an app.
	 * @param user
	 * @param app
	 */
	public void downloadApp(User user, App app) {		
		if(!(user.getAllDownloadedApps().contains(app))){
			user.download(app);}
		else throw new IllegalArgumentException();//add the app to the download list
	}

	/**
	 * Allows a user to rate an app.
	 * @param user
	 * @param app
	 * @param rating
	 */
	public void rateApp(User user, App app, short rating) {
		if(!(rating==1&&rating==2&&rating==3&&rating==4&&rating==5)){
			throw new IllegalArgumentException();
		}
		
		if(user.getAllDownloadedApps().contains(app)){
			app.rate(user, rating);}
		else throw new IllegalArgumentException();
		//only the user who downloaded the app can rate,
		//otherwise throw one exception
	}

	/**
	 * only if a given user has download the app, return true,else returns false
	 * @param user
	 * @param app
	 * @return boolean
	 */
	public boolean hasUserDownloadedApp(User user, App app) {		
		if(user.getAllDownloadedApps().contains(app)){
			return true;
		}
		else {return false;}
	}

	/**
	 * Returns a list of top free apps in a given category. 
	 * If no category is passed, then it returns top free apps across all categories.
	 * by using AppScoreComparator class to calculate the top free apps
	 * @param category
	 * @return List 
	 */
	public List<App> getTopFreeApps(String category) {
		List<App> freeApps = new ArrayList<App>();
		List<App> topFreeApps = new ArrayList<App>();
		Iterator <App> itr = apps.iterator();//initialize the iterators
		while(itr.hasNext()){
			App a = itr.next();
			if(a.getPrice() == 0.0){
				freeApps.add(a);// get a list of all free apps
			}
		}
		Iterator <App> itr2 = freeApps.iterator();
		if(category == null){
			topFreeApps = freeApps;
		}	
		else{
			while(itr2.hasNext()){
				App b = itr2.next();
				if(b.getCategory().equals(category)){
					topFreeApps.add(b);
					//get a list of free apps belonging to the category
				}
			}
		}
		Collections.sort(topFreeApps,new AppScoreComparator());
		//sort the freeapp list
		return topFreeApps;//return top free apps
	}

	/**
	 * Returns a list of top paid apps in a given category. 
	 * If no category is passed, 
	 * then it returns top paid apps across all categories. 
	 * It uses the same algorithm as the top free, 
	 * however it considers only the paid apps.
	 * @param category
	 * @return List 
	 */

	public List<App> getTopPaidApps(String category) {
		List<App> paidApps = new ArrayList<App>();
		List<App> topPaidApps = new ArrayList<App>();
		Iterator <App> itr = apps.iterator();
		//initialize the iterators
		while(itr.hasNext()){
			App a = itr.next();
			if(a.getPrice() != 0.0){
				paidApps.add(a);// get a list of all paid apps
			}
		}
		Iterator <App> itr2 = paidApps.iterator();
		//initialize the iterators
		if(category == null){
			topPaidApps = paidApps;
		}	
		else{
			while(itr2.hasNext()){
				App b = itr2.next();
				if(b.getCategory().equals(category)){
					topPaidApps.add(b);
					//get a list of paid apps belonging to the category
				}
			}
		}
		Collections.sort(topPaidApps,new AppScoreComparator());
		//sort the freeapp list
		return topPaidApps;//return top free apps
	}

	/**
	 * Returns a list of apps ordered by their launch dates
	 * by using the comparator method implemented in App class 
	 * If no category is specified, 
	 * return most recent apps across all categories.
	 * @param category
	 * @return List 
	 */
	public List<App> getMostRecentApps(String category) {
		List<App> mostRecentApps = new ArrayList<App>();
		//initialize iterator
		Iterator <App> itr = apps.iterator();
		if(category == null){
			mostRecentApps = apps;
		}
		else{
			while(itr.hasNext()){
				App c = itr.next();
				if(c.getCategory().equals(category)){
					mostRecentApps.add(c);
					//get the list of apps belonging to the category
				}
			}
		}
		Collections.sort(mostRecentApps);
		//sort the app list
		return mostRecentApps;
		//return app list by time order
	}
}


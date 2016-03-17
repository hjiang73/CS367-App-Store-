///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  AppStore.java
// File:             User.java
// Semester:         CS367 Fall 2015
//
// Author:           Han Jiang hjiang73@wisc.edu
// CS Login:         hjiang
// Lecturer's Name:  Jim Skrentny
//////////////////////////// 80 columns wide //////////////////////////////////
/**
 * This User class is used to create an Object named User, including several 
 * constructors such as the email, password, Name, country, type, of the user, 
 * and several methods such as to subscribeAsDeveloper, download,upload,etc.
 * <p>Bugs: None known
 *
 * @author Han Jiang
 */

import java.util.*;

/**
 * Create an object named App
 * Bugs: none known
 * @author Han Jiang
 */
public class User {
    /*Data Field*/
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String country;
	private String type;//user or developer
	private List<App> downlist;//add the app to this list when user downloads
	private List<App> uplist;////add the app to this list when user uploads
    
	public User(String email, String password, String firstName,
			String lastName, String country, String type)
			throws IllegalArgumentException {
		if (email.equals(null)||password.equals(null)||firstName.equals(null)
				||lastName.equals(null)||
				country.equals(null) || type.equals(null))
			throw new IllegalArgumentException();
		//ensure the user is validly created, otherwise throw an exception
	    /*Constructors*/ 
		this.email=email;
		this.password=password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.country = country;
	    this.type = type;
	    this.downlist = new ArrayList<App>();
	    this.uplist = new ArrayList<App>(); }

	/**
	 * get the email of the user
	 * @return String email
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Returns true if the password matches the password set for this user.
	 * @param testPassword
	 * @return boolean
	 */
	public boolean verifyPassword(String testPassword) {
		if(testPassword.equals(this.password))
			return true;
		else{ return false;}
	}

	/**
	 * get the first name of the user
	 * @return String firstName
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * get the last name of the user
	 * @return String lastName
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * get the country of the user
	 * @return String country
	 */
	public String getCountry() {
		return this.country;
	}
   
	/**
	 * Returns true only if (type == developer)
	 * @return boolean
	 */
	public boolean isDeveloper() {
		if(this.type.equals("developer")){
			return true;
		}
		else 
			{return false;}
	}

	/**
	 * Registers a user as a developer at the app store
	 * turn type from "user" to "developer"
	 */
	public void subscribeAsDeveloper() {
		this.type = "developer";
	}
    
	/**
	 * Downloads an app and adds it to the 
	 * downloaded list of apps for this user.
	 * @param app
	 */
	public void download(App app) { 
	  downlist.add(app);
	}

	/**
	 * Uploads an app and adds it to the 
	 * uploaded list of apps for this user.
	 * @param app
	 */
	public void upload(App app) {
		uplist.add(app);
	}
	
	/**
	 * Returns a list of apps installed by a user.
	 * @return List downlist
	 */
	public List<App> getAllDownloadedApps() {
		return downlist;
	}
	
	/**
	 * Returns a list of apps uploaded to 
	 * app store by a given developer.
	 * @return List downlist
	 */
	public List<App> getAllUploadedApps() {
		return uplist;
	}
		
}


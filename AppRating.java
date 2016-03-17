///////////////////////////////////////////////////////////////////////////////
// Main Class File:  AppStore.java
// File:             AppRating.java
// Semester:         CS367 Fall 2015
//
// Author:           Jim Skrentny
// Lecturer's Name:  Jim Skrentny
//////////////////////////// 80 columns wide //////////////////////////////////
/**
 * Constructs an app rating object with the given attributes. 
 * Rating can only be an integer between 1-5 (inclusive).
 * <p>Bugs: None known
 * @author Jim Skrentny
 */
public class AppRating {	
	//data fields
	private App app;
	private User user;
	private short rating;
	
	public AppRating(App app, User user, short rating) {
	//Constructors
		this.app = app;
		this.user = user;
		this.rating = rating;
	}
	//Returns the app for which the rating was given.
	public App getApp() {
		return this.app;
	}
	
	//Returns the user who rated the app.
	public User getUser() {
		return this.user;
	}
	
	//Returns the rating given by the user.
	public short getRating() {
		return this.rating;
	}
}


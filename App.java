///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  AppStore.java
// File:             App.java
// Semester:         CS367 Fall 2015
//
// Author:           Han Jiang hjiang73@wisc.edu
// CS Login:         hjiang
// Lecturer's Name:  Jim Skrentny
//////////////////////////// 80 columns wide //////////////////////////////////
/**
 * This App class is used to create an Object named App, including several 
 * constructors such as the developer, appId, appName, category, price, 
 * uploadTimestamp of the App, and several methods such as to get the average 
 * rating, total revenue of this app, Finally, it overrides comparator which is 
 * used to compare two apps by their uploatime.
 * <p>Bugs: None known
 *
 * @author Han Jiang
 */

import java.util.*;
/**
 * Create an object named App
 * Bugs: none known
 * @author       Han Jiang
 */
public class App implements Comparable<App> {
	//data field
	private User developer;//the developer of the app
	private String appId;//the ID of the app
	private String appName;//the name of app
	private String category;//the category of the app
	private Double price;//the price of the app
	private Long uploadTimestamp;//the uploadtime of the app
	private long countofapp;//the download times of app
	private int rateNum;//the rated times of the app
	private List<User> downloaded;//the users who downloaded the app
	private short totalrate;//the total rating of app
	private double avg_rating;//the average rating of app
	private List<User> rated;//the users who have rated the app
	private double revenue;//the revenue of the app
	private double score;//the score of app

	public App(User developer, String appId, String appName, String category,
			double price, long uploadTimestamp) throws IllegalArgumentException {
		if (developer.equals(null)||appId.equals(null)||appName.equals(null)
				||category.equals(null))
			throw new IllegalArgumentException();
		if (price<0.0)
			throw new IllegalArgumentException();
		//ensure the app is valid, otherwise throw an exception
		//Constructors 
		this.developer = developer;
		this.appId = appId;
		this.appName = appName;
		this.category = category;
		this.price = price;
		this.uploadTimestamp = uploadTimestamp;
		this.downloaded = new ArrayList<User>();
		this.rated = new ArrayList<User>();
		this.countofapp=0;
		this.rateNum=0;
		this.totalrate=0;
		this.avg_rating=0.0;
		this.revenue = 0.0;
		this.score = 0.0;

	}

	/**
	 * get the developer
	 * @param developer
	 * @return one User object named developer
	 */
	public User getDeveloper() {
		return this.developer;
	}
	/**
	 * get the AppId
	 * @param appId
	 * @return a string variable named appId
	 */
	public String getAppId() {
		return this.appId;//TODO Remove this exception and implement the method
		//throw new RuntimeException("getAppId() not implemented.");
	}
	/**
	 * get the name of the app
	 * @param name
	 * @return a string-appName
	 */
	public String getAppName() {
		return this.appName;//TODO Remove this exception and implement the method
		//throw new RuntimeException("getAppName() not implemented.");
	}
	/**
	 * get the category of the app
	 * @param category 
	 * @return a string-category
	 */
	public String getCategory() {
		return this.category;
	}
	
	/**
	 * get the price of the app
	 * @param price 
	 * @return a double-price
	 */
	public double getPrice() {
		return this.price;
	}
	
	/**
	 * get the uploadtimestamp of the app
	 * @param uploadTimestamp
	 * @return a long-uploadTimestamp
	 */
	public long getUploadTimestamp() {
		return this.uploadTimestamp;
	}
	
	/**
	 * Increase the download count of the app by 1 and add the user to
	 * the downloaded list of the app
	 * @param User -the developer of the app
	 */
	public void download(User user) {		
		downloaded.add(user);
		countofapp++;
	}
	
	/**
	 * Assigns a rating to an app by the given user.
	 * Throws an exception, if the user has already rated the app.
	 * @param User-who has downloaded the app
	 * @param rating given by the user
	 */
	public void rate(User user, short rating) 
			throws IllegalArgumentException {
		if (rated.contains(user)){

			throw new IllegalArgumentException();
		}//if the user has already rated the app.
		totalrate +=rating;//add rating to total rating
		rateNum++;//increment rate times
		rated.add(user);/*add user to the rated list,which means
		the user has already rated the app.*/
	}

	/**
	 * get the total donwload times of the app
     * @return long countofapp
	 */
	public long getTotalDownloads() {
		return countofapp;
	}
	
	/**
	 * get the average rating of the app,which equals to 
	 * the total rating divided by the rating times
	 * @return double avg_rating
	 */
	public double getAverageRating() {
   //if no one rates the app, return the avg_rating = 0.0
		if(rateNum != 0){ 
			avg_rating = totalrate/rateNum;}
		return avg_rating;
	}
	
	/**
	 * Returns the total revenue generated for a given app. 
	 * Total revenue for an app is the 
	 * (total number of downloads * price of the app) – 30% 
	 * cut imposed by your app store
	 * @return double avg_rating
	 */
	public double getRevenueForApp() {
		revenue = (countofapp*this.price*0.7);//Calculate revenue
		return revenue;
	}

	/**
	 * Returns an app score computed by the following function: 
     *score = avg_rating * Math.log(1 + total_downloads)
	 * @return double score
	 */
	public double getAppScore() {
		score=avg_rating * Math.log(1 + countofapp);//Calculate score
		return score;
	}

	/**
	 *Provides an implementation of the compare() method 
	 *for the Comparable Interface implemented by the App class. 
	 *It compares two app objects based on their age at the app store. 
	 *It returns -1 if the app object has higher uploadTimestamp value 
	 *than the compared otherApp object and vice-versa for value 1.
	 * @param App otherApp
	 * @return integer 1 or -1
	 */
	@Override
	public int compareTo(App otherApp) {
		if(this.uploadTimestamp>otherApp.uploadTimestamp){
			//compare uploadTimestamp of two apps
			return -1;
		}
		else return 1;
		
	}
}


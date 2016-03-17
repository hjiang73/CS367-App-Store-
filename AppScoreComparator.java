///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  AppStore.java
// File:             AppScoreComparator.java
// Semester:         CS367 Fall 2015
//
// Author:           Han Jiang hjiang73@wisc.edu
// CS Login:         hjiang
// Lecturer's Name:  Jim Skrentny
//////////////////////////// 80 columns wide //////////////////////////////////
/**
 * The AppScoreComparator implements the Comparator interface and provides 
 * an implementation for the compare() function that compares 
 * the scores of two app objects and decides their ordering. 
 * the following algorithm is used:
 *(1)Each app is given a score by following formula:
 *score = average rating * Math.log(1 + number of downloads)
 *(2)Apps are then sorted by their scores and the top apps are computed
 *(3)Apps with same scores are ordered by their timestamp.
 * An older app is ranked higher than a new app.
 * <p>Bugs: None known
 * @author Han Jiang
 */

import java.util.Comparator;

public class AppScoreComparator implements Comparator<App> {
	
	@Override
	public int compare(App app1, App app2) {
	if(app1.getAppScore() > app2.getAppScore())	{
		return 1;
	}
	else if (app1.getAppScore() < app2.getAppScore()){
		return -1;//Compare Appscore
	}
	    else
	    if(app1.getUploadTimestamp()>app2.getUploadTimestamp()){
	    	return 1;
	    }
	    else return -1;
	}//Apps with same scores are ordered by their timestamp

}


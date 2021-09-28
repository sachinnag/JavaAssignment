package com.web.scraper.service;

public class ScraperConstants {
	
	// Constant that holds the Initial Site Url
	public static final String SITE_URL = "https://swayam.gov.in/explorer";
	
	// Constants for extracting data from the Polymer WebComponent
	public static final String POLYMER_MODULE = "global-module";
	public static final String POLY_BEGIN_JSON = "edges";
	public static final String POLY_END_JSON = "pageInfo";
	public static final String JSON_COURSE_NODE = "node";
	public static final String MODIFY_COURSE_NODE = "course";
	
	// Constants that holds the XPATHs to get the HtmlUnit Elements
	public static final String COURSE_XPATH_PREVIEWCONTENT = "//*[contains(@class, 'previewContent')]";
	public static final String COURSE_XPATH_LEARNERSENROLL = "//*[contains(@class, 'learnerEnrolled')]";
	public static final String COURSE_XPATH_INSTRUCTORNAME = "//*[contains(@class, 'instructorName')]";
	public static final String COURSE_XPATH_INSTRUCTORPIC = "//*[contains(@class, 'profilePhoto')]";
	public static final String COURSE_XPATH_COURSETITLE = "//*[contains(@class, 'courseTitle')]";
	public static final String COURSE_XPATH_PREVIEWTITLE = "//*[contains(@class, 'previewTitle')]";
	public static final String COURSE_XPATH_CHILDTABLE = ".//table";
	
	// Constants for matching the String to get the HtmlUnit Elements
	public static final String INSTRUCTOR_BIO = "Instructor bio";
	public static final String HEADING1_TAG = "h3";
	public static final String COURSE_LAYOUT = "Course Layout";
	public static final String VIDEO_SRC_PREFIX = "https://www.youtube.com/embed";
	
	// Constants for naming the keys for storing the scraped data
	public static final String KEY_TITLE = "title";
	public static final String KEY_INSTRUCTOR = "instructor";
	public static final String KEY_LEARNERS_ENROL = "learnersEnrolled";
	public static final String KEY_DESCRIPTION = "description";
	public static final String KEY_VIDEO_URL = "videoUrl";
	public static final String KEY_PROFILE_IMG = "profileImage";
	public static final String KEY_PROFILE_NAME = "profileName";
	public static final String KEY_PROFILE_COL = "profileCollege";
	public static final String KEY_PROFILE_EXP = "profileExpertise";
	public static final String KEY_INSTRUCTOR_INFO = "profileExpertise";
	
	// Constants that holds the escape characters
	public static final String EMPTY_SPACE = "";
	public static final String SEMI_COLON = ":";
	public static final String NEW_LINE = "\n";
	
	// Constants for MongoDB credentials, Database and Collection
	public static final String MONGO_USER = "localhost";
	public static final int MONGO_PORT = 27017;
	public static final String MONGO_DB = "swayam";
	public static final String MONGO_COLLECTION = "courses";
	
	// Constants for returning message as the response from the Service depending upon the condition
	public static final String SCRAPING_INIT_MSG = "The scraping process is initiated and data will be ready in sometime.";
	public static final String NO_RESULT_MSG = "No result found";
}

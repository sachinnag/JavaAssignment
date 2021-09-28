package com.web.scraper.service;

import java.util.List;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.FrameWindow;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlHeading1;
import com.gargoylesoftware.htmlunit.html.HtmlImage;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import com.web.scraper.entity.Course;
import com.web.scraper.helper.Utils;

public class CourseDetailParser {

	/**
	 * The method continues scraping for each Course and captures the data.
	 */
	public static void parseCourseDetail(Course course) {
		
		WebClient client = null;
		HtmlPage page = null;
		
		try {
			client = Utils.getWebClient();
			
			// Gets the complete individual Course Page elements.
			page = client.getPage(course.getUrl());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			client.close();
		}
	
		if(Utils.isNotNull(page)) {
			// Scrape and captures the data of the Summary section if available
			parseCourseSummary(page, course);
			
			// Scrape and captures the data of the Course video if available
			parseCourseVideo(page, course);
			
			// Scrape and captures the data of other course related info if available
			courseInfo(page, course);
			
			// Scrape and captures the data of the Course layout section if available
			parseCourseLayout(page, course);
			
			// Scrape and captures the data of the Instructor Bio section if available
			parseInstructorBio(page, course);
		}
	}
	
	/**
	 * The method scrapes and captures the data of other course related info if available using the XPATH and HtmlUnit Elements.
	 */
	private static void courseInfo(HtmlPage page, Course course) {
		try {
			HtmlHeading1 coursetitle = page.getFirstByXPath(ScraperConstants.COURSE_XPATH_COURSETITLE);
			if(Utils.isNotNull(coursetitle))
				course.getCourseDetail().getCourseInfo().put(ScraperConstants.KEY_TITLE, coursetitle.asNormalizedText());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			HtmlDivision courseInstructor = page.getFirstByXPath(ScraperConstants.COURSE_XPATH_INSTRUCTORNAME);
			if(Utils.isNotNull(courseInstructor))
				course.getCourseDetail().getCourseInfo().put(ScraperConstants.KEY_INSTRUCTOR, courseInstructor.asNormalizedText());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			HtmlDivision learnersEnrolled = page.getFirstByXPath(ScraperConstants.COURSE_XPATH_LEARNERSENROLL);
			if(Utils.isNotNull(learnersEnrolled))
				course.getCourseDetail().getCourseInfo().put(ScraperConstants.KEY_LEARNERS_ENROL, learnersEnrolled.asNormalizedText());
			}
			catch(Exception e) {
				e.printStackTrace();
		}
		
		try {
			List<Object> previewElems = (List<Object>) page.getByXPath(ScraperConstants.COURSE_XPATH_PREVIEWCONTENT);
			for(Object obj : previewElems) {
				if(obj instanceof HtmlDivision) {
					HtmlDivision div = (HtmlDivision) obj;
					if(!div.getPreviousElementSibling().getTagName().equalsIgnoreCase(ScraperConstants.HEADING1_TAG)) {
						course.getCourseDetail().getCourseInfo().put(ScraperConstants.KEY_DESCRIPTION, div.asNormalizedText());
						break;
					}
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * The method scrapes and captures the data of the Summary section if available using the XPATH and HtmlUnit Elements.
	 */
	private static void parseCourseSummary(HtmlPage page, Course course) {
		try {
			HtmlDivision summaryDiv = page.getFirstByXPath(ScraperConstants.COURSE_XPATH_PREVIEWTITLE);
			if(Utils.isNotNull(summaryDiv)) {			
				HtmlTable summaryTable = (HtmlTable) summaryDiv.getNextElementSibling();
				if(Utils.isNotNull(summaryTable)) {
					for(HtmlTableRow row : summaryTable.getBodies().get(0).getRows()) {
						String key = row.getCell(0).asNormalizedText().replace(ScraperConstants.SEMI_COLON, ScraperConstants.EMPTY_SPACE).trim();
						String value = row.getCell(1).asNormalizedText().trim();
						course.getCourseDetail().getSummary().put(key, value);
					}
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * The method scrapes and captures the data of the Course video Url if available using the HtmlUnit Elements.
	 */
	private static void parseCourseVideo(HtmlPage page, Course course) {
		try {
		List<FrameWindow> frames = page.getFrames();
		if(Utils.isNotEmpty(frames))
		for(FrameWindow frame : frames) {
			String srcAttr = frame.getFrameElement().getSrcAttribute();
			if(Utils.isNotEmpty(srcAttr) && srcAttr.contains(ScraperConstants.VIDEO_SRC_PREFIX)) {
				course.getCourseDetail().getCourseVideo().put(ScraperConstants.KEY_VIDEO_URL, srcAttr);
			}
		}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * The method scrapes and captures the data of the Course layout section if available using XPATH and HtmlUnit Elements.
	 */
	private static void parseCourseLayout(HtmlPage page, Course course) {
		try {
			List<Object> previewElems = (List<Object>) page.getByXPath(ScraperConstants.COURSE_XPATH_PREVIEWCONTENT);
			for(Object obj : previewElems) {
				if(obj instanceof HtmlDivision) {
					HtmlDivision div = (HtmlDivision) obj;
					if(div.getPreviousElementSibling().getTagName().equalsIgnoreCase(ScraperConstants.HEADING1_TAG)
							&& div.getPreviousElementSibling().asNormalizedText().equalsIgnoreCase(ScraperConstants.COURSE_LAYOUT)) {
						HtmlTable courseTable = div.getFirstByXPath(ScraperConstants.COURSE_XPATH_CHILDTABLE);
						if(Utils.isNotNull(courseTable)) {
							for(HtmlTableRow row : courseTable.getBodies().get(0).getRows()) {
								String key = row.getCell(0).asNormalizedText()
										.replace(ScraperConstants.SEMI_COLON, ScraperConstants.EMPTY_SPACE)
										.replace(".", "")
										.trim();
								String value = "";
								try {
									value = row.getCell(1).asNormalizedText().trim();
									course.getCourseDetail().getCourseLayout().put(key, value);
								}
								catch(IndexOutOfBoundsException e) {
									course.getCourseDetail().getCourseLayout().put(key, key);
								}
								
							}
						}
					}
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * The method scrape and captures the data of the Instructor Bio section if available using XPATH and HtmlUnit Elements.
	 */
	private static void parseInstructorBio(HtmlPage page, Course course) {
		try {
			HtmlImage profileImg = (HtmlImage) page.getFirstByXPath(ScraperConstants.COURSE_XPATH_INSTRUCTORPIC);
			if(Utils.isNotNull(profileImg)) {
				course.getCourseDetail().getInstructorBio().put(ScraperConstants.KEY_PROFILE_IMG, profileImg.getSrcAttribute());
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			List<Object> previewElems = (List<Object>) page.getByXPath(ScraperConstants.COURSE_XPATH_PREVIEWCONTENT);
			for(Object obj : previewElems) {
				if(obj instanceof HtmlDivision) {
					HtmlDivision div = (HtmlDivision) obj;
					if(div.getPreviousElementSibling().getTagName().equalsIgnoreCase(ScraperConstants.HEADING1_TAG) 
							&& div.getPreviousElementSibling().asNormalizedText().equalsIgnoreCase(ScraperConstants.INSTRUCTOR_BIO)) {
						String[] profileInfo = div.asNormalizedText().trim().split(ScraperConstants.NEW_LINE);
						if(profileInfo.length == 3) {
							course.getCourseDetail().getInstructorBio().put(ScraperConstants.KEY_PROFILE_NAME, profileInfo[0]);
							course.getCourseDetail().getInstructorBio().put(ScraperConstants.KEY_PROFILE_COL, profileInfo[1]);
							course.getCourseDetail().getInstructorBio().put(ScraperConstants.KEY_PROFILE_EXP, profileInfo[2]);
						}
						else {
							course.getCourseDetail().getInstructorBio().put(ScraperConstants.KEY_INSTRUCTOR_INFO, div.asNormalizedText().trim());
						}
						break;
					}
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}

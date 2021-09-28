package com.web.scraper.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlScript;
import com.gargoylesoftware.htmlunit.html.HtmlUnknownElement;
import com.web.scraper.dao.CoursesDAO;
import com.web.scraper.dao.CoursesDAOImpl;
import com.web.scraper.entity.Courses;
import com.web.scraper.helper.Utils;

public class CoursesParser {
	
	/**
	 * The method initiates the web scraping process in asynchronous fashion.
	 */
	public void init() {
			
		try {
			// Starts scraping the initial page, scrape all the courses node as JSON String.
			String jsonStr = getCoursesJsonFromUrl(ScraperConstants.SITE_URL);
			
			if(Utils.isNotEmpty(jsonStr)) {
				// Converts the courses JSON String to Java Object.
				List<Courses> courses = (List<Courses>) Utils.convertJsonToPOJO(jsonStr);
				
				if(Utils.isNotEmpty(courses)) {
					// Continues the scraping process further for the Courses scraped and captured.
					parseCourseDetail(courses);
					
					// And finally it stores the entire scraped data to the MongoDB.
					CoursesDAO courseDAO = new CoursesDAOImpl();
					courseDAO.saveCourses(courses);
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * The method uses the WebClient of HtmlUnit (which is an open source API) to extract the courses information from the web site.
	 */
	private String getCoursesJsonFromUrl(String url)
			throws FailingHttpStatusCodeException, MalformedURLException, IOException {

		String jsonStr = null;
		WebClient client = null;
		HtmlPage page = null;
		
		try {
			client = Utils.getWebClient();
			
			// Gets the complete Page elements.
			page = client.getPage(url);
		}
		catch(Exception e ) {
			e.printStackTrace();
		}
		finally {
			if(Utils.isNotNull(client))
				client.close();
		}
		
		if(Utils.isNotNull(page)) {
			// The course nodes information was only available in the Polymer WebComponent as in the form of JSON inside HtmlUnknownElement.
			HtmlUnknownElement elem = page.getElementByName(ScraperConstants.POLYMER_MODULE);
	
			List<DomNode> children = elem.getChildNodes();
			
			// Iterating all the child nodes to get the actual <Script> with CDATA section that contains the information about the courses nodes.
			for (DomNode domNode : children) {
				if (domNode instanceof HtmlScript) {
					String content = domNode.getTextContent();
				
					// Since, that courses data contents resides in the CDATA section, here extracted the JSON String while manipulating the String.
					int startIndex = content.indexOf(ScraperConstants.POLY_BEGIN_JSON) + 7;
					int endIndex = content.indexOf(ScraperConstants.POLY_END_JSON) - 2;
					jsonStr = content.substring(startIndex, endIndex);
					jsonStr = jsonStr.replaceAll(ScraperConstants.JSON_COURSE_NODE, ScraperConstants.MODIFY_COURSE_NODE);
					break;
				}
			}
		}

		return jsonStr;
	}
	
	/**
	 * The method iterates the Courses concurrently using ParallelStream to scrape and capture the information of each Course.
	 */
	private void parseCourseDetail(List<Courses> courses) {
		courses.parallelStream().forEach(c -> CourseDetailParser.parseCourseDetail(c.getCourse()));
	}
	 
}

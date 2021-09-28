package com.web.scraper.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.scraper.dao.CoursesDAO;
import com.web.scraper.entity.Courses;
import com.web.scraper.helper.Utils;

@RestController
@RequestMapping("/api")
public class CoursesRestController {
	
	@Autowired
	private CoursesDAO coursesDAO;
	
	/** 
	 * The method initiates the web scrape processing asynchronously and returns the process initiation message.
	 */
	@PostMapping("/scrape")
	public ResponseBean startScraping() {
		
		new ScrapeService().initiateScrapeSerivce();
		
		return new ResponseBean(ScraperConstants.SCRAPING_INIT_MSG, HttpStatus.OK.toString());
	}
	
	/**
	 * The method returns all the list of scraped Courses of the web site.
	 */
	@GetMapping("/courses")
	public ResponseBean getAllCourses() {
		
		List<Courses> courses = coursesDAO.getAllCourses();
		
		if(Utils.isNotEmpty(courses)) {
			return new ResponseBean(courses, HttpStatus.OK.toString());
		}
		
		return new ResponseBean(ScraperConstants.NO_RESULT_MSG, HttpStatus.NO_CONTENT.toString());
	}

}

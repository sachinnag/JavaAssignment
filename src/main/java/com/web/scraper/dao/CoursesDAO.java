package com.web.scraper.dao;

import java.util.List;

import com.web.scraper.entity.Courses;

public interface CoursesDAO {

		public void saveCourses(List<Courses> courses) throws Exception;
		
		List<Courses> getAllCourses();
		
}

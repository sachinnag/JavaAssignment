package com.web.scraper.entity;

import java.util.LinkedHashMap;
import java.util.Map;

import com.web.scraper.helper.Utils;

public class CourseDetail {

	private Map<String, String> courseInfo;
	private Map<String, String> summary;
	private Map<String, String> courseLayout;
	private Map<String, String> courseVideo;
	private Map<String, String> instructorBio;
	
	public Map<String, String> getCourseInfo() {
		if(!Utils.isNotNull(courseInfo))
			courseInfo = new LinkedHashMap<>();
		return courseInfo;
	}
	
	public void setCourseInfo(Map<String, String> courseInfo) {
		this.courseInfo = courseInfo;
	}
	
	public Map<String, String> getSummary() {
		if(!Utils.isNotNull(summary))
			summary = new LinkedHashMap<>();
		return summary;
	}
	
	public void setSummary(Map<String, String> summary) {
		this.summary = summary;
	}
	
	public Map<String, String> getCourseLayout() {
		if(!Utils.isNotNull(courseLayout))
			courseLayout = new LinkedHashMap<>();
		return courseLayout;
	}
	
	public void setCourseLayout(Map<String, String> courseLayout) {
		this.courseLayout = courseLayout;
	}
	
	public Map<String, String> getCourseVideo() {
		if(!Utils.isNotNull(courseVideo))
			courseVideo = new LinkedHashMap<>();
		return courseVideo;
	}
	
	public void setCourseVideo(Map<String, String> courseVideo) {
		this.courseVideo = courseVideo;
	}

	public Map<String, String> getInstructorBio() {
		if(!Utils.isNotNull(instructorBio))
			instructorBio = new LinkedHashMap<>();
		return instructorBio;
	}

	public void setInstructorBio(Map<String, String> instructorBio) {
		this.instructorBio = instructorBio;
	}
	
}

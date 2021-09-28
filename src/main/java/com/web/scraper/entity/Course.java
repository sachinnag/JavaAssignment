package com.web.scraper.entity;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.web.scraper.helper.Utils;

public class Course {
    private String id;
    private String title;
    private String url;
    private String explorerSummary;
    private String explorerInstructorName;
    private Enrollment enrollment;
    private boolean openForRegistration;
    private String availability;
    private boolean showInExplorer;
    private String startDate;
    private String endDate;
    private String examDate;
    private String enrollmentEndDate;
    private String estimatedWorkload;
    private List<Category> category;
    private String tags;
    private String featured;
    private String coursePictureUrl;
    private int credits;
    private int weeks;
    private String courseCode;
    private String instructorInstitute;
    private String ncCode;
    
    private CourseDetail courseDetail;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getExplorerSummary() {
		return explorerSummary;
	}
	public void setExplorerSummary(String explorerSummary) {
		this.explorerSummary = explorerSummary;
	}
	public String getExplorerInstructorName() {
		return explorerInstructorName;
	}
	public void setExplorerInstructorName(String explorerInstructorName) {
		this.explorerInstructorName = explorerInstructorName;
	}
	public Enrollment getEnrollment() {
		return enrollment;
	}
	public void setEnrollment(Enrollment enrollment) {
		this.enrollment = enrollment;
	}
	public boolean isOpenForRegistration() {
		return openForRegistration;
	}
	public void setOpenForRegistration(boolean openForRegistration) {
		this.openForRegistration = openForRegistration;
	}
	public String getAvailability() {
		return availability;
	}
	public void setAvailability(String availability) {
		this.availability = availability;
	}
	public boolean isShowInExplorer() {
		return showInExplorer;
	}
	public void setShowInExplorer(boolean showInExplorer) {
		this.showInExplorer = showInExplorer;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getExamDate() {
		return examDate;
	}
	public void setExamDate(String examDate) {
		this.examDate = examDate;
	}
	public String getEnrollmentEndDate() {
		return enrollmentEndDate;
	}
	public void setEnrollmentEndDate(String enrollmentEndDate) {
		this.enrollmentEndDate = enrollmentEndDate;
	}
	public String getEstimatedWorkload() {
		return estimatedWorkload;
	}
	public void setEstimatedWorkload(String estimatedWorkload) {
		this.estimatedWorkload = estimatedWorkload;
	}
	public List<Category> getCategory() {
		return category;
	}
	public void setCategory(List<Category> category) {
		this.category = category;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getFeatured() {
		return featured;
	}
	public void setFeatured(String featured) {
		this.featured = featured;
	}
	public String getCoursePictureUrl() {
		return coursePictureUrl;
	}
	public void setCoursePictureUrl(String coursePictureUrl) {
		this.coursePictureUrl = coursePictureUrl;
	}
	public int getCredits() {
		return credits;
	}
	public void setCredits(int credits) {
		this.credits = credits;
	}
	public int getWeeks() {
		return weeks;
	}
	public void setWeeks(int weeks) {
		this.weeks = weeks;
	}
	public String getourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public String getInstructorInstitute() {
		return instructorInstitute;
	}
	public void setInstructorInstitute(String instructorInstitute) {
		this.instructorInstitute = instructorInstitute;
	}
	public String getNcCode() {
		return ncCode;
	}
	public void setNcCode(String ncCode) {
		this.ncCode = ncCode;
	}
	public CourseDetail getCourseDetail() {
		if(!Utils.isNotNull(courseDetail))
			courseDetail = new CourseDetail();
		return courseDetail;
	}
	public void setCourseDetail(CourseDetail courseDetail) {
		this.courseDetail = courseDetail;
	}
 
}

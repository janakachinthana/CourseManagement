package KDU.IS.Services;

import java.util.ArrayList;
import java.util.logging.Logger;

import KDU.IS.Models.Course;

public interface ICourseService {
	/** Initialize logger */
	public static final Logger log = Logger.getLogger(ICourseService.class.getName());


	/**
	 * Add courses for course table
	 * @param course
	 */
	public void addCourse(Course course);

	/**
	 * Get a particular Course
	 * 
	 * @param courseID
	 * @return Course
	 */
	public Course getCourseByID(String courseID);
	
	/**
	 * Get a particular Course
	 * 
	 * @param email
	 * @return Course
	 */
	public Course getCourseByEmail(String email);
	/**
	 * Get all list of courses
	 * 
	 * @return ArrayList<Course>
	 */
	
	public ArrayList<Course> getCourses();
	
	/**
	 * Update existing course
	 * @param courseID
	 * @param course
	 * 
	 * @return
	 */
	public Course updateCourse(String courseID, Course course);


	/**
	 * Remove existing course
	 * 
	 * @param courseID
	 */
	public void removeCourse(String courseID);


}

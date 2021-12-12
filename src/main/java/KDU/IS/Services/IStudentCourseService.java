package KDU.IS.Services;

import java.util.ArrayList;
import java.util.logging.Logger;

import KDU.IS.Models.StudentCourse;

public interface IStudentCourseService {

	/** Initialize logger */
	public static final Logger log = Logger.getLogger(IStudentCourseService.class.getName());


	/**
	 * Add studentCourses for studentCourse table
	 * @param studentCourse
	 */
	public void addStudentCourse(StudentCourse studentCourse);

	/**
	 * Get a particular StudentCourse
	 * 
	 * @param studentCourseID
	 * @return StudentCourse
	 */
	public StudentCourse getStudentCourseByID(String studentCourseID);
	
	/**
	 * Get a particular StudentCourse
	 * 
	 * @param email
	 * @return StudentCourse
	 */
	public StudentCourse getStudentCourseByEmail(String email);
	/**
	 * Get all list of studentCourses
	 * 
	 * @return ArrayList<StudentCourse>
	 */
	
	public ArrayList<StudentCourse> getStudentCourses();
	
	/**
	 * Update existing studentCourse
	 * @param studentCourseID
	 * @param studentCourse
	 * 
	 * @return
	 */
	public StudentCourse updateStudentCourse(String studentCourseID, StudentCourse studentCourse);


	/**
	 * Remove existing studentCourse
	 * 
	 * @param studentCourseID
	 */
	public void removeStudentCourse(String studentCourseID);


}

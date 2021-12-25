package KDU.IS.Services;

import java.util.ArrayList;
import java.util.logging.Logger;

import KDU.IS.Models.StudentLecture;

public interface IStudentLectureService {

	/** Initialize logger */
	public static final Logger log = Logger.getLogger(IStudentLectureService.class.getName());


	/**
	 * Add studentLectures for studentLecture table
	 * @param studentLecture
	 */
	public void addStudentLecture(StudentLecture studentLecture);

	/**
	 * Get a particular StudentLecture
	 * 
	 * @param studentLectureID
	 * @return StudentLecture
	 */
	public StudentLecture getStudentLectureByID(String studentLectureID);
	
	/**
	 * Get a particular StudentLecture
	 * 
	 * @param email
	 * @return StudentLecture
	 */

	public ArrayList<StudentLecture> getStudentLectures();
	
	/**
	 * Update existing studentLecture
	 * @param studentLectureID
	 * @param studentLecture
	 * 
	 * @return
	 */
	public StudentLecture updateStudentLecture(String studentLectureID, StudentLecture studentLecture);


	/**
	 * Remove existing studentLecture
	 * 
	 * @param studentLectureID
	 */
	public void removeStudentLecture(String studentLectureID);


}

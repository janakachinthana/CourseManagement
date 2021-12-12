package KDU.IS.Services;

import java.util.ArrayList;
import java.util.logging.Logger;

import KDU.IS.Models.Lecture;

public interface ILectureService {

	/** Initialize logger */
	public static final Logger log = Logger.getLogger(ILectureService.class.getName());


	/**
	 * Add lectures for lecture table
	 * @param lecture
	 */
	public void addLecture(Lecture lecture);

	/**
	 * Get a particular Lecture
	 * 
	 * @param lectureID
	 * @return Lecture
	 */
	public Lecture getLectureByID(String lectureID);
	
	/**
	 * Get a particular Lecture
	 * 
	 * @param email
	 * @return Lecture
	 */
	public Lecture getLectureByEmail(String email);
	/**
	 * Get all list of lectures
	 * 
	 * @return ArrayList<Lecture>
	 */
	
	public ArrayList<Lecture> getLectures();
	
	/**
	 * Update existing lecture
	 * @param lectureID
	 * @param lecture
	 * 
	 * @return
	 */
	public Lecture updateLecture(String lectureID, Lecture lecture);


	/**
	 * Remove existing lecture
	 * 
	 * @param lectureID
	 */
	public void removeLecture(String lectureID);

}

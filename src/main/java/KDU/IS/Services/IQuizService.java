package KDU.IS.Services;

import java.util.ArrayList;
import java.util.logging.Logger;

import KDU.IS.Models.Quiz;

public interface IQuizService {

	/** Initialize logger */
	public static final Logger log = Logger.getLogger(IQuizService.class.getName());


	/**
	 * Add quizs for quiz table
	 * @param quiz
	 */
	public void addQuiz(Quiz quiz);

	/**
	 * Get a particular Quiz
	 * 
	 * @param quizID
	 * @return Quiz
	 */
	public Quiz getQuizByID(String quizID);
	
	/**
	 * Get a particular Quiz
	 * 
	 * @param email
	 * @return Quiz
	 */

	public ArrayList<Quiz> getQuizs();
	
	/**
	 * Update existing quiz
	 * @param quizID
	 * @param quiz
	 * 
	 * @return
	 */
	public Quiz updateQuiz(String quizID, Quiz quiz);


	/**
	 * Remove existing quiz
	 * 
	 * @param quizID
	 */
	public void removeQuiz(String quizID);


}

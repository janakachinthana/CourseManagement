package KDU.IS.Services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import KDU.IS.Models.Quiz;
import KDU.IS.Utils.CommonConstants;
import KDU.IS.Utils.CommonUtil;
import KDU.IS.Utils.DBConnectionUtil;
import KDU.IS.Utils.QueryUtil;

public class QuizServiceImpl implements IQuizService {
	

	/** Initialize logger */
	public static final Logger log = Logger.getLogger(QuizServiceImpl.class.getName());

	private static Connection connection;

	private static Statement statement;

	static{
		//create table or drop if exist
		/* createQuizTable(); */
	}

	private PreparedStatement preparedStatement;

	/**
	 * This method initially drop existing Quizs table in the database and
	 * recreate table structure to insert quiz entries
	 * 
	 * @throws SQLException
	 *             - Thrown when database access error occurs or this method is
	 *             called on a closed connection
	 * @throws ClassNotFoundException
	 *             - Thrown when an application tries to load in a class through
	 *             its string name using
	 * @throws SAXException
	 *             - Encapsulate a general SAX error or warning
	 * @throws IOException
	 *             - Exception produced by failed or interrupted I/O operations.
	 * @throws ParserConfigurationException
	 *             - Indicates a serious configuration error
	 * @throws NullPointerException
	 *             - Service is not available
	 * 
	 */
	public static void createQuizTable() {

		try {
			connection = DBConnectionUtil.getDBConnection();
			statement = connection.createStatement();
			// Drop table if already exists and as per SQL query available in
			// Query.xml
			statement.executeUpdate(QueryUtil.queryByID(CommonConstants.QUERY_ID_DROP_QUIZ_TABLE));
			// Create new quizs table as per SQL query available in
			// Query.xml
			statement.executeUpdate(QueryUtil.queryByID(CommonConstants.QUERY_ID_CREATE_QUIZ_TABLE));
			
		} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
	}

	/**
	 * Add set of quizs for as a batch from the selected quiz List
	 * 
	 * @throws SQLException
	 *             - Thrown when database access error occurs or this method is
	 *             called on a closed connection
	 * @throws SAXException
	 *             - Encapsulate a general SAX error or warning
	 * @throws IOException
	 *             - Exception produced by failed or interrupted I/O operations.
	 * @throws ParserConfigurationException
	 *             - Indicates a serious configuration error.
	 * 
	 */
	@Override
	public void addQuiz(Quiz quiz) {

		String quizID = CommonUtil.generateQuizIDs(getQuizIDs());
		
		try {
			connection = DBConnectionUtil.getDBConnection();
			/*
			 * Query is available in QuizQuery.xml file and use
			 * insert_quiz key to extract value of it
			 */
			preparedStatement = connection
					.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_INSERT_QUIZ));
			connection.setAutoCommit(false);
			
			//Generate quiz IDs
			quiz.setQuizID(quizID);
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, quiz.getQuizID());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_TWO, quiz.getLectureID());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_THREE, quiz.getQuestion());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_FOUR, quiz.getAns1());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_FIVE, quiz.getAns2());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_SIX, quiz.getAns3());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_SEVEN, quiz.getAns4());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_EIGHT, quiz.getAns5());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_NINE, quiz.getAnswer());
			// Add quiz
			preparedStatement.execute();
			connection.commit();
		} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
			log.log(Level.SEVERE, e.getMessage());
		} finally {
			/*
			 * Close prepared statement and database connectivity at the end of
			 * transaction
			 */
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
	}

	/**
	 * Quiz details can be retrieved based on the provided quiz ID
	 * 
	 * @param quizID
	 *            - Quiz details are filtered based on the ID
	 * 
	 * @see #actionOnQuiz()
	 */
	@Override
	public Quiz getQuizByID(String quizID) {

		return actionOnQuiz(quizID).get(0);
	}
	
	
	/**
	 * Get all list of quizs
	 * 
	 * @return ArrayList<Quiz> 
	 * 						- Array of quiz list will be return
	 * 
	 * @see #actionOnQuiz()
	 */
	@Override
	public ArrayList<Quiz> getQuizs() {
		
		return actionOnQuiz(null);
	}

	/**
	 * This method delete an quiz based on the provided ID
	 * 
	 * @param quizID
	 *            - Delete quiz according to the filtered quiz details
	 * @throws SQLException
	 *             - Thrown when database access error occurs or this method is
	 *             called on a closed connection
	 * @throws ClassNotFoundException
	 *             - Thrown when an application tries to load in a class through
	 *             its string name using
	 * @throws SAXException
	 *             - Encapsulate a general SAX error or warning
	 * @throws IOException
	 *             - Exception produced by failed or interrupted I/O operations.
	 * @throws ParserConfigurationException
	 *             - Indicates a serious configuration error.
	 * @throws NullPointerException
	 *             - Service is not available
	 */
	@Override
	public void removeQuiz(String quizID) {

		// Before deleting check whether quiz ID is available
		if (quizID != null && !quizID.isEmpty()) {
			/*
			 * Remove quiz query will be retrieved from QuizQuery.xml
			 */
			try {
				connection = DBConnectionUtil.getDBConnection();
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_REMOVE_QUIZ));
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, quizID);
				preparedStatement.executeUpdate();
			} catch (SQLException | SAXException | IOException | ParserConfigurationException
					| ClassNotFoundException e) {
				log.log(Level.SEVERE, e.getMessage());
			} finally {
				/*
				 * Close prepared statement and database connectivity at the end
				 * of transaction
				 */
				try {
					if (preparedStatement != null) {
						preparedStatement.close();
					}
					if (connection != null) {
						connection.close();
					}
				} catch (SQLException e) {
					log.log(Level.SEVERE, e.getMessage());
				}
			}
		}
	}

	/**
	 * This performs GET quiz by ID and Display all quizs
	 * 
	 * @param quizID
	 *            ID of the quiz to remove or select from the list

	 * @return ArrayList<Quiz> Array of quiz list will be return
	 * 
	 * @throws SQLException
	 *             - Thrown when database access error occurs or this method is
	 *             called on a closed connection
	 * @throws ClassNotFoundException
	 *             - Thrown when an application tries to load in a class through
	 *             its string name using
	 * @throws SAXException
	 *             - Encapsulate a general SAX error or warning
	 * @throws IOException
	 *             - Exception produced by failed or interrupted I/O operations.
	 * @throws ParserConfigurationException
	 *             - Indicates a serious configuration error.
	 * @throws NullPointerException
	 *             - Service is not available
	 * 
	 * @see #getQuizs()
	 * @see #getQuizByID(String)
	 */
	private ArrayList<Quiz> actionOnQuiz(String quizID) {

		ArrayList<Quiz> quizList = new ArrayList<Quiz>();
		try {
			connection = DBConnectionUtil.getDBConnection();
			/*
			 * Before fetching quiz it checks whether quiz ID is
			 * available
			 */
			if (quizID != null && !quizID.isEmpty()) {
				/*
				 * Get quiz by ID query will be retrieved from
				 * QuizQuery.xml
				 */
					preparedStatement = connection
							.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_GET_QUIZ));
					preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, quizID);
			}
			/*
			 * If quiz ID is not provided for get quiz option it display
			 * all quizs
			 */
			else {
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_ALL_QUIZS));
			}
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Quiz quiz = new Quiz();
				quiz.setQuizID(resultSet.getString(CommonConstants.COLUMN_INDEX_ONE));
				quiz.setLectureID(resultSet.getString(CommonConstants.COLUMN_INDEX_TWO));
				quiz.setQuestion(resultSet.getString(CommonConstants.COLUMN_INDEX_THREE));
				quiz.setAns1(resultSet.getString(CommonConstants.COLUMN_INDEX_FOUR));
				quiz.setAns2(resultSet.getString(CommonConstants.COLUMN_INDEX_FIVE));
				quiz.setAns3(resultSet.getString(CommonConstants.COLUMN_INDEX_SIX));
				quiz.setAns4(resultSet.getString(CommonConstants.COLUMN_INDEX_SEVEN));
				quiz.setAns5(resultSet.getString(CommonConstants.COLUMN_INDEX_EIGHT));
				quiz.setAnswer(resultSet.getString(CommonConstants.COLUMN_INDEX_NINE));
				quizList.add(quiz);
			}

		} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
			log.log(Level.SEVERE, e.getMessage());
		} finally {
			/*
			 * Close prepared statement and database connectivity at the end of
			 * transaction
			 */
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		return quizList;
	}

	/**
	 * Get the updated quiz
	 * 
	 * @param quizID
	 *            ID of the quiz to remove or select from the list
	 * 
	 * @return return the Quiz object
	 * 
	 */
	@Override
	public Quiz updateQuiz(String quizID, Quiz quiz) {

		/*
		 * Before fetching quiz it checks whether quiz ID is available
		 */
		if (quizID != null && !quizID.isEmpty()) {
			/*
			 * Update quiz query will be retrieved from QuizQuery.xml
			 */
			try {
				connection = DBConnectionUtil.getDBConnection();
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_UPDATE_QUIZ));
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_TWO, quiz.getQuizID());
				preparedStatement.executeUpdate();

			} catch (SQLException | SAXException | IOException | ParserConfigurationException
					| ClassNotFoundException e) {
				log.log(Level.SEVERE, e.getMessage());
			} finally {
				/*
				 * Close prepared statement and database connectivity at the end
				 * of transaction
				 */
				try {
					if (preparedStatement != null) {
						preparedStatement.close();
					}
					if (connection != null) {
						connection.close();
					}
				} catch (SQLException e) {
					log.log(Level.SEVERE, e.getMessage());
				}
			}
		}
		// Get the updated quiz
		return getQuizByID(quizID);
	}
	
	
	/**
	 *
	 * @return ArrayList<String> Array of quiz id list will be return
	 * 
	 * @throws SQLException
	 *             - Thrown when database access error occurs or this method is
	 *             called on a closed connection
	 * @throws ClassNotFoundException
	 *             - Thrown when an application tries to load in a class through
	 *             its string name using
	 * @throws SAXException
	 *             - Encapsulate a general SAX error or warning
	 * @throws IOException
	 *             - Exception produced by failed or interrupted I/O operations.
	 * @throws ParserConfigurationException
	 *             - Indicates a serious configuration error.
	 * @throws NullPointerException
	 *             - Service is not available
	 */
	private ArrayList<String> getQuizIDs(){
		
		ArrayList<String> arrayList = new ArrayList<String>();
		/*
		 * List of quiz IDs will be retrieved from QuizQuery.xml
		 */
		try {
			connection = DBConnectionUtil.getDBConnection();
			preparedStatement = connection
					.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_GET_QUIZ_IDS));
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				arrayList.add(resultSet.getString(CommonConstants.COLUMN_INDEX_ONE));
			}
		} catch (SQLException | SAXException | IOException | ParserConfigurationException
				| ClassNotFoundException e) {
			log.log(Level.SEVERE, e.getMessage());
		} finally {
			/*
			 * Close prepared statement and database connectivity at the end of
			 * transaction
			 */
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		return arrayList;
	}

}

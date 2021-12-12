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

import KDU.IS.Models.Lecture;
import KDU.IS.Utils.CommonConstants;
import KDU.IS.Utils.CommonUtil;
import KDU.IS.Utils.DBConnectionUtil;
import KDU.IS.Utils.QueryUtil;

public class LectureServiceImpl implements ILectureService{

	/** Initialize logger */
	public static final Logger log = Logger.getLogger(LectureServiceImpl.class.getName());

	private static Connection connection;

	private static Statement statement;

	static{
		//create table or drop if exist
		createLectureTable();
	}

	private PreparedStatement preparedStatement;

	/**
	 * This method initially drop existing Lectures table in the database and
	 * recreate table structure to insert lecture entries
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
	public static void createLectureTable() {

		try {
			connection = DBConnectionUtil.getDBConnection();
			statement = connection.createStatement();
			// Drop table if already exists and as per SQL query available in
			// Query.xml
			statement.executeUpdate(QueryUtil.queryByID(CommonConstants.QUERY_ID_DROP_LECTURE_TABLE));
			// Create new lectures table as per SQL query available in
			// Query.xml
			statement.executeUpdate(QueryUtil.queryByID(CommonConstants.QUERY_ID_CREATE_LECTURE_TABLE));
			
		} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
	}

	/**
	 * Add set of lectures for as a batch from the selected lecture List
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
	public void addLecture(Lecture lecture) {

		String lectureID = CommonUtil.generateLectureIDs(getLectureIDs());
		
		try {
			connection = DBConnectionUtil.getDBConnection();
			/*
			 * Query is available in LectureQuery.xml file and use
			 * insert_lecture key to extract value of it
			 */
			preparedStatement = connection
					.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_INSERT_LECTURE));
			connection.setAutoCommit(false);
			
			//Generate lecture IDs
			lecture.setLectureID(lectureID);
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, lecture.getLectureID());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_TWO, lecture.getCourseID());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_THREE, lecture.getLectureName());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_FOUR, lecture.getDescription());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_FIVE, lecture.getFileUrl());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_SIX, lecture.getStatus());
			// Add lecture
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
	 * Lecture details can be retrieved based on the provided lecture ID
	 * 
	 * @param lectureID
	 *            - Lecture details are filtered based on the ID
	 * 
	 * @see #actionOnLecture()
	 */
	@Override
	public Lecture getLectureByID(String lectureID) {

		return actionOnLecture(lectureID).get(0);
	}
	
	/**
	 * Lecture details can be retrieved based on the provided lecture email
	 * 
	 * @param lectureID
	 *            - Lecture details are filtered based on the email
	 * 
	 * @see #actionOnLecture()
	 */
	@Override
	public Lecture getLectureByEmail(String email) {

		return actionOnLecture(email).get(0);
	}
	
	/**
	 * Get all list of lectures
	 * 
	 * @return ArrayList<Lecture> 
	 * 						- Array of lecture list will be return
	 * 
	 * @see #actionOnLecture()
	 */
	@Override
	public ArrayList<Lecture> getLectures() {
		
		return actionOnLecture(null);
	}

	/**
	 * This method delete an lecture based on the provided ID
	 * 
	 * @param lectureID
	 *            - Delete lecture according to the filtered lecture details
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
	public void removeLecture(String lectureID) {

		// Before deleting check whether lecture ID is available
		if (lectureID != null && !lectureID.isEmpty()) {
			/*
			 * Remove lecture query will be retrieved from LectureQuery.xml
			 */
			try {
				connection = DBConnectionUtil.getDBConnection();
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_REMOVE_LECTURE));
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, lectureID);
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
	 * This performs GET lecture by ID and Display all lectures
	 * 
	 * @param lectureID
	 *            ID of the lecture to remove or select from the list

	 * @return ArrayList<Lecture> Array of lecture list will be return
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
	 * @see #getLectures()
	 * @see #getLectureByID(String)
	 */
	private ArrayList<Lecture> actionOnLecture(String lectureID) {

		ArrayList<Lecture> lectureList = new ArrayList<Lecture>();
		try {
			connection = DBConnectionUtil.getDBConnection();
			/*
			 * Before fetching lecture it checks whether lecture ID is
			 * available
			 */
			if (lectureID != null && !lectureID.isEmpty()) {
				/*
				 * Get lecture by ID query will be retrieved from
				 * LectureQuery.xml
				 */
					preparedStatement = connection
							.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_GET_LECTURE));
					preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, lectureID);
			}
			/*
			 * If lecture ID is not provided for get lecture option it display
			 * all lectures
			 */
			else {
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_ALL_LECTURES));
			}
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Lecture lecture = new Lecture();
				lecture.setLectureID(resultSet.getString(CommonConstants.COLUMN_INDEX_ONE));
				lecture.setCourseID(resultSet.getString(CommonConstants.COLUMN_INDEX_TWO));
				lecture.setLectureName(resultSet.getString(CommonConstants.COLUMN_INDEX_THREE));
				lecture.setDescription(resultSet.getString(CommonConstants.COLUMN_INDEX_FOUR));
				lecture.setFileUrl(resultSet.getString(CommonConstants.COLUMN_INDEX_FIVE));
				lecture.setStatus(resultSet.getString(CommonConstants.COLUMN_INDEX_SIX));
				lectureList.add(lecture);
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
		return lectureList;
	}

	/**
	 * Get the updated lecture
	 * 
	 * @param lectureID
	 *            ID of the lecture to remove or select from the list
	 * 
	 * @return return the Lecture object
	 * 
	 */
	@Override
	public Lecture updateLecture(String lectureID, Lecture lecture) {

		/*
		 * Before fetching lecture it checks whether lecture ID is available
		 */
		if (lectureID != null && !lectureID.isEmpty()) {
			/*
			 * Update lecture query will be retrieved from LectureQuery.xml
			 */
			try {
				connection = DBConnectionUtil.getDBConnection();
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_UPDATE_LECTURE));
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_TWO, lecture.getLectureID());
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
		// Get the updated lecture
		return getLectureByID(lectureID);
	}
	
	
	/**
	 *
	 * @return ArrayList<String> Array of lecture id list will be return
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
	private ArrayList<String> getLectureIDs(){
		
		ArrayList<String> arrayList = new ArrayList<String>();
		/*
		 * List of lecture IDs will be retrieved from LectureQuery.xml
		 */
		try {
			connection = DBConnectionUtil.getDBConnection();
			preparedStatement = connection
					.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_GET_LECTURE_IDS));
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

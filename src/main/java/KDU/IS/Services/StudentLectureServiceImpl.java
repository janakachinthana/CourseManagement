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

import KDU.IS.Models.StudentLecture;
import KDU.IS.Utils.CommonConstants;
import KDU.IS.Utils.CommonUtil;
import KDU.IS.Utils.DBConnectionUtil;
import KDU.IS.Utils.QueryUtil;

public class StudentLectureServiceImpl implements IStudentLectureService {


	/** Initialize logger */
	public static final Logger log = Logger.getLogger(StudentLectureServiceImpl.class.getName());

	private static Connection connection;

	private static Statement statement;

	static{
		//create table or drop if exist
		/* createStudentLectureTable(); */
	}

	private PreparedStatement preparedStatement;

	/**
	 * This method initially drop existing StudentLectures table in the database and
	 * recreate table structure to insert studentLecture entries
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
	public static void createStudentLectureTable() {

		try {
			connection = DBConnectionUtil.getDBConnection();
			statement = connection.createStatement();
			// Drop table if already exists and as per SQL query available in
			// Query.xml
			statement.executeUpdate(QueryUtil.queryByID(CommonConstants.QUERY_ID_DROP_STUDENTLECTURE_TABLE));
			// Create new studentLectures table as per SQL query available in
			// Query.xml
			statement.executeUpdate(QueryUtil.queryByID(CommonConstants.QUERY_ID_CREATE_STUDENTLECTURE_TABLE));
			
		} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
	}

	/**
	 * Add set of studentLectures for as a batch from the selected studentLecture List
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
	public void addStudentLecture(StudentLecture studentLecture) {

		String studentLectureID = CommonUtil.generateStudentLectureIDs(getStudentLectureIDs());
		
		try {
			connection = DBConnectionUtil.getDBConnection();
			/*
			 * Query is available in StudentLectureQuery.xml file and use
			 * insert_studentLecture key to extract value of it
			 */
			preparedStatement = connection
					.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_INSERT_STUDENTLECTURE));
			connection.setAutoCommit(false);
			
			//Generate studentLecture IDs
			studentLecture.setStudentLectureID(studentLectureID);
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, studentLecture.getStudentLectureID());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_TWO, studentLecture.getLectureID());
			System.out.println(studentLecture.getUserID());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_THREE, studentLecture.getUserID());
			preparedStatement.setDouble(CommonConstants.COLUMN_INDEX_FOUR, studentLecture.getMarks());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_FIVE, studentLecture.getStatus());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_SIX, studentLecture.getOther());
			// Add studentLecture
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
	 * StudentLecture details can be retrieved based on the provided studentLecture ID
	 * 
	 * @param studentLectureID
	 *            - StudentLecture details are filtered based on the ID
	 * 
	 * @see #actionOnStudentLecture()
	 */
	@Override
	public StudentLecture getStudentLectureByID(String studentLectureID) {

		return actionOnStudentLecture(studentLectureID).get(0);
	}
	
	
	/**
	 * Get all list of studentLectures
	 * 
	 * @return ArrayList<StudentLecture> 
	 * 						- Array of studentLecture list will be return
	 * 
	 * @see #actionOnStudentLecture()
	 */
	@Override
	public ArrayList<StudentLecture> getStudentLectures() {
		
		return actionOnStudentLecture(null);
	}

	/**
	 * This method delete an studentLecture based on the provided ID
	 * 
	 * @param studentLectureID
	 *            - Delete studentLecture according to the filtered studentLecture details
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
	public void removeStudentLecture(String studentLectureID) {

		// Before deleting check whether studentLecture ID is available
		if (studentLectureID != null && !studentLectureID.isEmpty()) {
			/*
			 * Remove studentLecture query will be retrieved from StudentLectureQuery.xml
			 */
			try {
				connection = DBConnectionUtil.getDBConnection();
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_REMOVE_STUDENTLECTURE));
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, studentLectureID);
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
	 * This performs GET studentLecture by ID and Display all studentLectures
	 * 
	 * @param studentLectureID
	 *            ID of the studentLecture to remove or select from the list

	 * @return ArrayList<StudentLecture> Array of studentLecture list will be return
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
	 * @see #getStudentLectures()
	 * @see #getStudentLectureByID(String)
	 */
	private ArrayList<StudentLecture> actionOnStudentLecture(String studentLectureID) {

		ArrayList<StudentLecture> studentLectureList = new ArrayList<StudentLecture>();
		try {
			connection = DBConnectionUtil.getDBConnection();
			/*
			 * Before fetching studentLecture it checks whether studentLecture ID is
			 * available
			 */
			if (studentLectureID != null && !studentLectureID.isEmpty()) {
				/*
				 * Get studentLecture by ID query will be retrieved from
				 * StudentLectureQuery.xml
				 */
					preparedStatement = connection
							.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_GET_STUDENTLECTURE));
					preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, studentLectureID);
			}
			/*
			 * If studentLecture ID is not provided for get studentLecture option it display
			 * all studentLectures
			 */
			else {
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_ALL_STUDENTLECTURES));
			}
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				StudentLecture studentLecture = new StudentLecture();
				studentLecture.setStudentLectureID(resultSet.getString(CommonConstants.COLUMN_INDEX_ONE));
				studentLecture.setLectureID(resultSet.getString(CommonConstants.COLUMN_INDEX_TWO));
				studentLecture.setUserID(resultSet.getString(CommonConstants.COLUMN_INDEX_THREE));
				studentLecture.setMarks(resultSet.getDouble(CommonConstants.COLUMN_INDEX_FOUR));
				studentLecture.setStatus(resultSet.getString(CommonConstants.COLUMN_INDEX_FIVE));
				studentLecture.setOther(resultSet.getString(CommonConstants.COLUMN_INDEX_SIX));
				studentLectureList.add(studentLecture);
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
		return studentLectureList;
	}

	/**
	 * Get the updated studentLecture
	 * 
	 * @param studentLectureID
	 *            ID of the studentLecture to remove or select from the list
	 * 
	 * @return return the StudentLecture object
	 * 
	 */
	@Override
	public StudentLecture updateStudentLecture(String studentLectureID, StudentLecture studentLecture) {

		/*
		 * Before fetching studentLecture it checks whether studentLecture ID is available
		 */
		if (studentLectureID != null && !studentLectureID.isEmpty()) {
			/*
			 * Update studentLecture query will be retrieved from StudentLectureQuery.xml
			 */
			try {
				connection = DBConnectionUtil.getDBConnection();
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_UPDATE_STUDENTLECTURE));
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_TWO, studentLecture.getStudentLectureID());
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
		// Get the updated studentLecture
		return getStudentLectureByID(studentLectureID);
	}
	
	
	/**
	 *
	 * @return ArrayList<String> Array of studentLecture id list will be return
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
	private ArrayList<String> getStudentLectureIDs(){
		
		ArrayList<String> arrayList = new ArrayList<String>();
		/*
		 * List of studentLecture IDs will be retrieved from StudentLectureQuery.xml
		 */
		try {
			connection = DBConnectionUtil.getDBConnection();
			preparedStatement = connection
					.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_GET_STUDENTLECTURE_IDS));
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

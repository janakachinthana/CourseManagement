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

import KDU.IS.Models.StudentCourse;
import KDU.IS.Utils.CommonConstants;
import KDU.IS.Utils.CommonUtil;
import KDU.IS.Utils.DBConnectionUtil;
import KDU.IS.Utils.QueryUtil;

public class StudentCourseServiceImpl implements IStudentCourseService{

	/** Initialize logger */
	public static final Logger log = Logger.getLogger(StudentCourseServiceImpl.class.getName());

	private static Connection connection;

	private static Statement statement;

	static{
		//create table or drop if exist
		createStudentCourseTable();
	}

	private PreparedStatement preparedStatement;

	/**
	 * This method initially drop existing StudentCourses table in the database and
	 * recreate table structure to insert studentCourse entries
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
	public static void createStudentCourseTable() {

		try {
			connection = DBConnectionUtil.getDBConnection();
			statement = connection.createStatement();
			// Drop table if already exists and as per SQL query available in
			// Query.xml
			statement.executeUpdate(QueryUtil.queryByID(CommonConstants.QUERY_ID_DROP_STUDENTCOURSE_TABLE));
			// Create new studentCourses table as per SQL query available in
			// Query.xml
			statement.executeUpdate(QueryUtil.queryByID(CommonConstants.QUERY_ID_CREATE_STUDENTCOURSE_TABLE));
			
		} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
	}

	/**
	 * Add set of studentCourses for as a batch from the selected studentCourse List
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
	
	public void addStudentCourse(StudentCourse studentCourse) {

		String studentCourseID = CommonUtil.generateStudentCourseIDs(getStudentCourseIDs());
		
		try {
			connection = DBConnectionUtil.getDBConnection();
			/*
			 * Query is available in StudentCourseQuery.xml file and use
			 * insert_studentCourse key to extract value of it
			 */
			preparedStatement = connection
					.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_INSERT_STUDENTCOURSE));
			connection.setAutoCommit(false);
			
			//Generate studentCourse IDs
			studentCourse.setId(studentCourseID);
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, studentCourse.getId());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_TWO, studentCourse.getStudentID());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_THREE, studentCourse.getCourseID());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_FOUR, studentCourse.getProgress());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_FIVE, studentCourse.getStatus());
			// Add studentCourse
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
	 * StudentCourse details can be retrieved based on the provided studentCourse ID
	 * 
	 * @param studentCourseID
	 *            - StudentCourse details are filtered based on the ID
	 * 
	 * @see #actionOnStudentCourse()
	 */
	
	public StudentCourse getStudentCourseByID(String studentCourseID) {

		return actionOnStudentCourse(studentCourseID).get(0);
	}
	
	/**
	 * StudentCourse details can be retrieved based on the provided studentCourse email
	 * 
	 * @param studentCourseID
	 *            - StudentCourse details are filtered based on the email
	 * 
	 * @see #actionOnStudentCourse()
	 */
	
	public StudentCourse getStudentCourseByEmail(String email) {

		return actionOnStudentCourse(email).get(0);
	}
	
	/**
	 * Get all list of studentCourses
	 * 
	 * @return ArrayList<StudentCourse> 
	 * 						- Array of studentCourse list will be return
	 * 
	 * @see #actionOnStudentCourse()
	 */


	public ArrayList<StudentCourse> getStudentCourses() {
		
		return actionOnStudentCourse(null);
	}

	/**
	 * This method delete an studentCourse based on the provided ID
	 * 
	 * @param studentCourseID
	 *            - Delete studentCourse according to the filtered studentCourse details
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
	
	public void removeStudentCourse(String studentCourseID) {

		// Before deleting check whether studentCourse ID is available
		if (studentCourseID != null && !studentCourseID.isEmpty()) {
			/*
			 * Remove studentCourse query will be retrieved from StudentCourseQuery.xml
			 */
			try {
				connection = DBConnectionUtil.getDBConnection();
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_REMOVE_STUDENTCOURSE));
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, studentCourseID);
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
	 * This performs GET studentCourse by ID and Display all studentCourses
	 * 
	 * @param studentCourseID
	 *            ID of the studentCourse to remove or select from the list

	 * @return ArrayList<StudentCourse> Array of studentCourse list will be return
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
	 * @see #getStudentCourses()
	 * @see #getStudentCourseByID(String)
	 */
	private ArrayList<StudentCourse> actionOnStudentCourse(String studentCourseID) {

		ArrayList<StudentCourse> studentCourseList = new ArrayList<StudentCourse>();
		try {
			connection = DBConnectionUtil.getDBConnection();
			/*
			 * Before fetching studentCourse it checks whether studentCourse ID is
			 * available
			 */
			if (studentCourseID != null && !studentCourseID.isEmpty()) {
				/*
				 * Get studentCourse by ID query will be retrieved from
				 * StudentCourseQuery.xml
				 */
					preparedStatement = connection
							.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_GET_STUDENTCOURSE));
					preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, studentCourseID);
			}
			/*
			 * If studentCourse ID is not provided for get studentCourse option it display
			 * all studentCourses
			 */
			else {
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_ALL_STUDENTCOURSES));
			}
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				StudentCourse studentCourse = new StudentCourse();
				studentCourse.setId(resultSet.getString(CommonConstants.COLUMN_INDEX_ONE));
				studentCourse.setStudentID(resultSet.getString(CommonConstants.COLUMN_INDEX_TWO));
				studentCourse.setCourseID(resultSet.getString(CommonConstants.COLUMN_INDEX_THREE));
				studentCourse.setProgress(resultSet.getString(CommonConstants.COLUMN_INDEX_FOUR));
				studentCourse.setStatus(resultSet.getString(CommonConstants.COLUMN_INDEX_FIVE));
				studentCourseList.add(studentCourse);
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
		return studentCourseList;
	}

	/**
	 * Get the updated studentCourse
	 * 
	 * @param studentCourseID
	 *            ID of the studentCourse to remove or select from the list
	 * 
	 * @return return the StudentCourse object
	 * 
	 */
	
	public StudentCourse updateStudentCourse(String studentCourseID, StudentCourse studentCourse) {

		/*
		 * Before fetching studentCourse it checks whether studentCourse ID is available
		 */
		if (studentCourseID != null && !studentCourseID.isEmpty()) {
			/*
			 * Update studentCourse query will be retrieved from StudentCourseQuery.xml
			 */
			try {
				connection = DBConnectionUtil.getDBConnection();
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_UPDATE_STUDENTCOURSE));
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_TWO, studentCourse.getId());
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
		// Get the updated studentCourse
		return getStudentCourseByID(studentCourseID);
	}
	
	
	/**
	 *
	 * @return ArrayList<String> Array of studentCourse id list will be return
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
	private ArrayList<String> getStudentCourseIDs(){
		
		ArrayList<String> arrayList = new ArrayList<String>();
		/*
		 * List of studentCourse IDs will be retrieved from StudentCourseQuery.xml
		 */
		try {
			connection = DBConnectionUtil.getDBConnection();
			preparedStatement = connection
					.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_GET_STUDENTCOURSE_IDS));
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

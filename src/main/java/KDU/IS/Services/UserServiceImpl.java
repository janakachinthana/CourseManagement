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

import KDU.IS.Models.User;
import KDU.IS.Utils.CommonConstants;
import KDU.IS.Utils.CommonUtil;
import KDU.IS.Utils.DBConnectionUtil;
import KDU.IS.Utils.QueryUtil;


public class UserServiceImpl implements IUserService{


	/** Initialize logger */
	public static final Logger log = Logger.getLogger(UserServiceImpl.class.getName());

	private static Connection connection;

	private static Statement statement;

	static{
		//create table or drop if exist
//		createUserTable();
	}

	private PreparedStatement preparedStatement;

	/**
	 * This method initially drop existing Users table in the database and
	 * recreate table structure to insert user entries
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
	public static void createUserTable() {

		try {
			connection = DBConnectionUtil.getDBConnection();
			statement = connection.createStatement();
			// Drop table if already exists and as per SQL query available in
			// Query.xml
			statement.executeUpdate(QueryUtil.queryByID(CommonConstants.QUERY_ID_DROP_USER_TABLE));
			// Create new users table as per SQL query available in
			// Query.xml
			statement.executeUpdate(QueryUtil.queryByID(CommonConstants.QUERY_ID_CREATE_USER_TABLE));
			
		} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
			log.log(Level.SEVERE, e.getMessage());
		}
	}

	/**
	 * Add set of users for as a batch from the selected user List
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
	public void addUser(User user) {

		String userID = CommonUtil.generateIDs(getUserIDs());
		
		try {
			connection = DBConnectionUtil.getDBConnection();
			/*
			 * Query is available in UserQuery.xml file and use
			 * insert_user key to extract value of it
			 */
			preparedStatement = connection
					.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_INSERT_USER));
			connection.setAutoCommit(false);
			
			//Generate user IDs
			user.setUserID(userID);
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, user.getUserID());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_TWO, user.getFullName());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_THREE, user.getEmail());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_FOUR, user.getType());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_FIVE, user.getStatus());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_SIX, user.getContact());
			preparedStatement.setString(CommonConstants.COLUMN_INDEX_SEVEN, user.getPassword());
			// Add user
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
	 * User details can be retrieved based on the provided user ID
	 * 
	 * @param userID
	 *            - User details are filtered based on the ID
	 * 
	 * @see #actionOnUser()
	 */
	@Override
	public User getUserByID(String userID) {

		return actionOnUser(userID).get(0);
	}
	
	/**
	 * User details can be retrieved based on the provided user email
	 * 
	 * @param userID
	 *            - User details are filtered based on the email
	 * 
	 * @see #actionOnUser()
	 */
	@Override
	public User getUserByEmail(String email) {

		return actionOnUser(email).get(0);
	}
	
	/**
	 * Get all list of users
	 * 
	 * @return ArrayList<User> 
	 * 						- Array of user list will be return
	 * 
	 * @see #actionOnUser()
	 */
	@Override
	public ArrayList<User> getUsers() {
		
		return actionOnUser(null);
	}

	/**
	 * This method delete an user based on the provided ID
	 * 
	 * @param userID
	 *            - Delete user according to the filtered user details
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
	public void removeUser(String userID) {

		// Before deleting check whether user ID is available
		if (userID != null && !userID.isEmpty()) {
			/*
			 * Remove user query will be retrieved from UserQuery.xml
			 */
			try {
				connection = DBConnectionUtil.getDBConnection();
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_REMOVE_USER));
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, userID);
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
	 * This performs GET user by ID and Display all users
	 * 
	 * @param userID
	 *            ID of the user to remove or select from the list

	 * @return ArrayList<User> Array of user list will be return
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
	 * @see #getUsers()
	 * @see #getUserByID(String)
	 */
	private ArrayList<User> actionOnUser(String userID) {

		ArrayList<User> userList = new ArrayList<User>();
		try {
			connection = DBConnectionUtil.getDBConnection();
			/*
			 * Before fetching user it checks whether user ID is
			 * available
			 */
			if (userID != null && !userID.isEmpty()) {
				/*
				 * Get user by ID query will be retrieved from
				 * UserQuery.xml
				 */
				if(userID.contains("@")) {
					preparedStatement = connection
							.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_EMAIL_GET_USER));
					preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, userID);
					
				}
				else {
					preparedStatement = connection
							.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_GET_USER));
					preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, userID);
				}
			}
			/*
			 * If user ID is not provided for get user option it display
			 * all users
			 */
			else {
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_ALL_USERS));
			}
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				User user = new User();
				user.setUserID(resultSet.getString(CommonConstants.COLUMN_INDEX_ONE));
				user.setFullName(resultSet.getString(CommonConstants.COLUMN_INDEX_TWO));
				user.setEmail(resultSet.getString(CommonConstants.COLUMN_INDEX_THREE));
				user.setType(resultSet.getString(CommonConstants.COLUMN_INDEX_FOUR));
				user.setStatus(resultSet.getString(CommonConstants.COLUMN_INDEX_FIVE));
				user.setContact(resultSet.getString(CommonConstants.COLUMN_INDEX_SIX));
				user.setPassword(resultSet.getString(CommonConstants.COLUMN_INDEX_SEVEN));
				userList.add(user);
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
		return userList;
	}

	/**
	 * Get the updated user
	 * 
	 * @param userID
	 *            ID of the user to remove or select from the list
	 * 
	 * @return return the User object
	 * 
	 */
	@Override
	public User updateUser(String userID, User user) {

		/*
		 * Before fetching user it checks whether user ID is available
		 */
		if (userID != null && !userID.isEmpty()) {
			/*
			 * Update user query will be retrieved from UserQuery.xml
			 */
			try {
				connection = DBConnectionUtil.getDBConnection();
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_UPDATE_USER));
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_TWO, user.getUserID());
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
		// Get the updated user
		return getUserByID(userID);
	}
	
	/**
	 * Get the updated user
	 * 
	 * @param userID
	 *            ID of the user to remove or select from the list
	 * 
	 * @return return the User object
	 * 
	 */
	@Override
	public User updateUserStatus(String userID, User user) {

		/*
		 * Before fetching user it checks whether user ID is available
		 */
		if (userID != null && !userID.isEmpty()) {
			/*
			 * Update user query will be retrieved from UserQuery.xml
			 */
			try {
				System.out.println(user.getStatus()+ "hhh");
				connection = DBConnectionUtil.getDBConnection();
				preparedStatement = connection
						.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_UPDATE_USER_STATUS));
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_ONE, user.getStatus());
				preparedStatement.setString(CommonConstants.COLUMN_INDEX_TWO, userID);
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
		// Get the updated user
		return getUserByID(userID);
	}
	
	/**
	 *
	 * @return ArrayList<String> Array of user id list will be return
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
	private ArrayList<String> getUserIDs(){
		
		ArrayList<String> arrayList = new ArrayList<String>();
		/*
		 * List of user IDs will be retrieved from UserQuery.xml
		 */
		try {
			connection = DBConnectionUtil.getDBConnection();
			preparedStatement = connection
					.prepareStatement(QueryUtil.queryByID(CommonConstants.QUERY_ID_GET_USER_IDS));
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


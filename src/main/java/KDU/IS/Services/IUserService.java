package KDU.IS.Services;

import java.util.ArrayList;
import java.util.logging.Logger;

import KDU.IS.Models.User;


public interface IUserService {

	/** Initialize logger */
	public static final Logger log = Logger.getLogger(IUserService.class.getName());


	/**
	 * Add users for user table
	 * @param user
	 */
	public void addUser(User user);

	/**
	 * Get a particular User
	 * 
	 * @param userID
	 * @return User
	 */
	public User getUserByID(String userID);
	
	/**
	 * Get a particular User
	 * 
	 * @param email
	 * @return User
	 */
	public User getUserByEmail(String email);
	/**
	 * Get all list of users
	 * 
	 * @return ArrayList<User>
	 */
	
	public ArrayList<User> getUsers();
	
	/**
	 * Update existing user
	 * @param userID
	 * @param user
	 * 
	 * @return
	 */
	public User updateUser(String userID, User user);

	/**
	 * Update existing user Status
	 * @param userID
	 * @param User
	 * 
	 * @return
	 */
	public User updateUserStatus(String userID, User user);
	
	/**
	 * Remove existing user
	 * 
	 * @param userID
	 */
	public void removeUser(String userID);


}

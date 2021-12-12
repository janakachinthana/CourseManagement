package KDU.IS.Models;

public class User {

	private String userID;
	private String fullName;
	private String email;
	private String type;
	private String status;
	private String contact;
    private String password;

	public User() {
		super();
	}

	public User(String userID, String fullName, String email, String type, String status, String contact,
			String password) {
		super();
		this.userID = userID;
		this.fullName = fullName;
		this.email = email;
		this.type = type;
		this.status = status;
		this.contact = contact;
		this.password = password;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [userID=" + userID + ", fullName=" + fullName + ", email=" + email + ", type=" + type + ", status="
				+ status + ", contact=" + contact + ", password=" + password + "]";
	}

	
    
}

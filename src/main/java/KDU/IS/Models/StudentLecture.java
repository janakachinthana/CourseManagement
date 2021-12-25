package KDU.IS.Models;

public class StudentLecture {
	
	private String StudentLectureID;
	private String LectureID;
	private String UserID;
	private double Marks;
	private String Status;
	private String Other;
	
	public StudentLecture() {
		super();
	}

	public StudentLecture(String studentLecturID, String lectureID, String userID, double marks, String status,
			String other) {
		super();
		StudentLectureID = studentLecturID;
		LectureID = lectureID;
		UserID = userID;
		Marks = marks;
		Status = status;
		Other = other;
	}

	public String getStudentLectureID() {
		return StudentLectureID;
	}

	public void setStudentLectureID(String studentLecturID) {
		StudentLectureID = studentLecturID;
	}

	public String getLectureID() {
		return LectureID;
	}

	public void setLectureID(String lectureID) {
		LectureID = lectureID;
	}

	public String getUserID() {
		return UserID;
	}

	public void setUserID(String userID) {
		UserID = userID;
	}

	public double getMarks() {
		return Marks;
	}

	public void setMarks(double userScore) {
		Marks = userScore;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getOther() {
		return Other;
	}

	public void setOther(String other) {
		Other = other;
	}

	@Override
	public String toString() {
		return "StudentLecture [StudentLecturID=" + StudentLectureID + ", LectureID=" + LectureID + ", UserID=" + UserID
				+ ", Marks=" + Marks + ", Status=" + Status + ", Other=" + Other + "]";
	}

	
}

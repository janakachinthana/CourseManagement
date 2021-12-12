package KDU.IS.Models;

public class StudentCourse {
	
	private String Id;
	private String StudentID;
	private String CourseID;
	private String Progress;
	private String Status;
	
	public StudentCourse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StudentCourse(String id, String studentID, String courseID, String progress, String status) {
		super();
		Id = id;
		StudentID = studentID;
		CourseID = courseID;
		Progress = progress;
		Status = status;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getStudentID() {
		return StudentID;
	}

	public void setStudentID(String studentID) {
		StudentID = studentID;
	}

	public String getCourseID() {
		return CourseID;
	}

	public void setCourseID(String courseID) {
		CourseID = courseID;
	}

	public String getProgress() {
		return Progress;
	}

	public void setProgress(String progress) {
		Progress = progress;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	@Override
	public String toString() {
		return "StudentCourse [Id=" + Id + ", StudentID=" + StudentID + ", CourseID=" + CourseID + ", Progress="
				+ Progress + ", Status=" + Status + "]";
	}
	
	

}

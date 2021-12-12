package KDU.IS.Models;

public class Lecture {

	private String LectureID;
	private String CourseID;
	private String LectureName;
	private String Description;
	private String FileUrl;
	private String Status;
	
	public Lecture() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Lecture(String lectureID, String courseID, String lectureName, String description, String fileUrl,
			String status) {
		super();
		LectureID = lectureID;
		CourseID = courseID;
		LectureName = lectureName;
		Description = description;
		FileUrl = fileUrl;
		Status = status;
	}

	public String getLectureID() {
		return LectureID;
	}

	public void setLectureID(String lectureID) {
		LectureID = lectureID;
	}

	public String getCourseID() {
		return CourseID;
	}

	public void setCourseID(String courseID) {
		CourseID = courseID;
	}

	public String getLectureName() {
		return LectureName;
	}

	public void setLectureName(String lectureName) {
		LectureName = lectureName;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getFileUrl() {
		return FileUrl;
	}

	public void setFileUrl(String fileUrl) {
		FileUrl = fileUrl;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	@Override
	public String toString() {
		return "Lecture [LectureID=" + LectureID + ", CourseID=" + CourseID + ", LectureName=" + LectureName
				+ ", Description=" + Description + ", FileUrl=" + FileUrl + ", Status=" + Status + "]";
	}
	
	
}

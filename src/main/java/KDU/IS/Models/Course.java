package KDU.IS.Models;

public class Course {

	private String CourseID;
	private String CourseName;
	private String Specification;
	private String Description;
	private String LIC;
	private String ImgUrl;
	private String Status;
	
	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Course(String courseID, String courseName, String specification, String description, String lIC,
			String imgUrl, String status) {
		super();
		CourseID = courseID;
		CourseName = courseName;
		Specification = specification;
		Description = description;
		LIC = lIC;
		ImgUrl = imgUrl;
		Status = status;
	}

	public String getCourseID() {
		return CourseID;
	}

	public void setCourseID(String courseID) {
		CourseID = courseID;
	}

	public String getCourseName() {
		return CourseName;
	}

	public void setCourseName(String courseName) {
		CourseName = courseName;
	}

	public String getSpecification() {
		return Specification;
	}

	public void setSpecification(String specification) {
		Specification = specification;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getLIC() {
		return LIC;
	}

	public void setLIC(String lIC) {
		LIC = lIC;
	}

	public String getImgUrl() {
		return ImgUrl;
	}

	public void setImgUrl(String imgUrl) {
		ImgUrl = imgUrl;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	@Override
	public String toString() {
		return "Course [CourseID=" + CourseID + ", CourseName=" + CourseName + ", Specification=" + Specification
				+ ", Description=" + Description + ", LIC=" + LIC + ", ImgUrl=" + ImgUrl + ", Status=" + Status + "]";
	}

	
}

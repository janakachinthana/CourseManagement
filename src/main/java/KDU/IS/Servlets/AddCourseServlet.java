package KDU.IS.Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import KDU.IS.Models.Course;
import KDU.IS.Services.ICourseService;
import KDU.IS.Services.CourseServiceImpl;

/**
 * Servlet implementation class AddCourseServlet
 */
@WebServlet("/AddCourseServlet")
public class AddCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCourseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	Course course = new Course();
		
		course.setCourseName(request.getParameter("courseName"));
		course.setSpecification(request.getParameter("specification"));
		course.setDescription(request.getParameter("description"));
		course.setLIC(request.getParameter("lic"));
		course.setImgUrl(request.getParameter("imgUrl"));
		course.setStatus("Active");
		
		ICourseService courseService = new CourseServiceImpl();
		
		courseService.addCourse(course);
	
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/signUp.html");
		dispatcher.forward(request, response);
	}

}

package KDU.IS.Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import KDU.IS.Models.StudentCourse;
import KDU.IS.Services.IStudentCourseService;
import KDU.IS.Services.StudentCourseServiceImpl;


/**
 * Servlet implementation class AddStudentCourseServlet
 */
@WebServlet("/AddStudentCourseServlet")
public class AddStudentCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddStudentCourseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		StudentCourse studentCourse = new StudentCourse();
		
		studentCourse.setStudentID(request.getParameter("studentID"));
		studentCourse.setCourseID(request.getParameter("courseID"));
		studentCourse.setProgress(request.getParameter("progress"));
		studentCourse.setStatus(request.getParameter("status"));
		
		IStudentCourseService studentCourseService = new StudentCourseServiceImpl();
		
		studentCourseService.addStudentCourse(studentCourse);
	
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
	}

}

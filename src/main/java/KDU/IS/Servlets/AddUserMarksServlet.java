package KDU.IS.Servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import KDU.IS.Models.LoginUser;
import KDU.IS.Models.Quiz;
import KDU.IS.Models.StudentLecture;
import KDU.IS.Services.IQuizService;
import KDU.IS.Services.IStudentLectureService;
import KDU.IS.Services.QuizServiceImpl;
import KDU.IS.Services.StudentLectureServiceImpl;

/**
 * Servlet implementation class AddUserMarksServlet
 */
@WebServlet("/AddUserMarksServlet")
public class AddUserMarksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUserMarksServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String LectureID = request.getParameter("lectureID");
		double TempScore = 0;
		double UserScore= 0;
		double QuizCount =0;
		IQuizService quizService = new QuizServiceImpl();
		
		ArrayList<Quiz> quizList = quizService.getQuizs();
		
		for (Quiz quiz : quizList) {
			if (quiz.getLectureID().equals(LectureID)) {
				QuizCount ++;
				
				if(quiz.getAnswer().equals(request.getParameter(quiz.getQuizID()))) {
					TempScore ++;
				}
			}
				
				
		
		}
		UserScore = TempScore/QuizCount * 100;
		
		
		StudentLecture lectureStudent = new StudentLecture();
		
		
		lectureStudent.setLectureID(request.getParameter("lectureID"));
		lectureStudent.setUserID(LoginUser.LoginUserID);
		lectureStudent.setMarks(UserScore);
		lectureStudent.setStatus("True");
		lectureStudent.setOther("1");
		
		 IStudentLectureService studentLecture = new StudentLectureServiceImpl();
		  
		 studentLecture.addStudentLecture(lectureStudent);
		  
		 RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/signUp.html");
		 dispatcher.forward(request, response);
		 
	}

}

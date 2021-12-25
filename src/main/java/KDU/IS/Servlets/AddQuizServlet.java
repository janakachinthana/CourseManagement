package KDU.IS.Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import KDU.IS.Models.Quiz;
import KDU.IS.Services.CourseServiceImpl;
import KDU.IS.Services.ICourseService;
import KDU.IS.Services.IQuizService;
import KDU.IS.Services.QuizServiceImpl;

/**
 * Servlet implementation class AddQuizServlet
 */
@WebServlet("/AddQuizServlet")
public class AddQuizServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddQuizServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Quiz quiz = new Quiz();

		quiz.setLectureID(request.getParameter("lectureID"));
		quiz.setQuestion(request.getParameter("question"));
		quiz.setAns1(request.getParameter("ans1"));
		quiz.setAns2(request.getParameter("ans2"));
		quiz.setAns3(request.getParameter("ans3"));
		quiz.setAns4(request.getParameter("ans4"));
		quiz.setAns5(request.getParameter("ans5"));
		quiz.setAnswer(request.getParameter("answer"));

		System.out.println(quiz);
		
		IQuizService quizService = new QuizServiceImpl();

		quizService.addQuiz(quiz);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/signUp.html");
		dispatcher.forward(request, response);
	}

}

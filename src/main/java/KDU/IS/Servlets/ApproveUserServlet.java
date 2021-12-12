package KDU.IS.Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import KDU.IS.Models.User;
import KDU.IS.Services.IUserService;
import KDU.IS.Services.UserServiceImpl;

/**
 * Servlet implementation class ApproveUserServlet
 */
@WebServlet("/ApproveUserServlet")
public class ApproveUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApproveUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		User user = new User();
		
		user.setUserID(request.getParameter("userID"));
		user.setStatus("Approved");
		System.out.println(user.getStatus()+ "aaa");
		IUserService userService = new UserServiceImpl();
		
		userService.updateUserStatus(user.getUserID(), user);
	
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/signUp.html");
		dispatcher.forward(request, response);
	}

}

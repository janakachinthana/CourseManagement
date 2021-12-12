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
 * Servlet implementation class AddUserServlet
 */
@WebServlet("/AddUserServlet")
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		User user = new User();
		
		user.setFullName(request.getParameter("fullName"));
		user.setEmail(request.getParameter("email"));
		user.setContact(request.getParameter("contact"));
		user.setType(request.getParameter("type"));
		System.out.println(request.getParameter("type"));
		if(user.getType().equals("TEACHER")) {
			user.setStatus("Not Approved");
		}else {
			user.setStatus("Approved");
		}
		user.setPassword(request.getParameter("password"));
		
		IUserService userService = new UserServiceImpl();
		
		userService.addUser(user);
	
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/signUp.html");
		dispatcher.forward(request, response);
	}

}

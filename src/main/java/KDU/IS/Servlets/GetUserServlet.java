package KDU.IS.Servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import KDU.IS.Models.LoginUser;
import KDU.IS.Models.User;
import KDU.IS.Services.IUserService;
import KDU.IS.Services.UserServiceImpl;

/**
 * Servlet implementation class GetUserServlet
 */
@WebServlet("/GetUserServlet")
public class GetUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetUserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String Email = request.getParameter("email");
		String Password = request.getParameter("password");

	

		if ((Email.equals("ADMIN") && Password.equals("ADMIN"))) {
			LoginUser.Email = "ADMIN";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/indexAdmin.jsp");
			dispatcher.forward(request, response);
			
		} else{
			
			IUserService userService = new UserServiceImpl();
			User user = userService.getUserByEmail(Email);
			LoginUser.LoginUserID = user.getUserID();
			LoginUser.LoginUserName = user.getFullName();
			LoginUser.Email = user.getEmail();
			LoginUser.Contact = user.getContact();
			
			if(user.getPassword().equals(Password)) {
				
				if (user.getType().equals("STUDENT")) {
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
					dispatcher.forward(request, response);
					
				} else if (user.getType().equals("TEACHER")) {
					
					if (user.getStatus().equals("Approved")) {
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
						dispatcher.forward(request, response);
						
					} else {
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/signUp.html");
						dispatcher.forward(request, response);
					}
				}

			} else {
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/signUp.html");
				dispatcher.forward(request, response);
			}
		} 

	}

}

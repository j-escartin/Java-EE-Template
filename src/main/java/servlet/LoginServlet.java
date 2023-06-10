package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ApplicationDao;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Sample code for dispatcher.include demo
		String html = "<html><h3>Hello Pleas Login</h3></html>";
		resp.getWriter().write(html);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/html/login.jsp");
		//dispatcher.forward(req, resp);
		
		dispatcher.include(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//get the username from the login form
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		//Call Dao for Validation logi
		ApplicationDao dao = new ApplicationDao();
		boolean isValidUser = dao.validateUser(username, password);
		
		//Check the user if invalid
		if(isValidUser) {
			//set up the HTTP Session
			HttpSession session = req.getSession();
			
			//set the username as an attribute
			session.setAttribute("username", username );
			
			//foreward to home jsp
			req.getRequestDispatcher("/html/home.jsp").forward(req, resp);
		} else {
			String errorMessage="Invalid Credentials, Please login again!";
			req.setAttribute("error", errorMessage);
			req.getRequestDispatcher("/html/login.jsp").forward(req, resp);
		}
		
		
		
		
		
	}
	
	
	
}

package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.User;
import dao.ApplicationDao;

@WebServlet("/getProfileDetails")
public class ViewProfileServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		//get the username from the session
		System.out.println("username in profile servlet " + req.getSession().getAttribute("username"));
		String username = (String) req.getSession().getAttribute("username");
		
		//Call dao and get profile details
		ApplicationDao dao = new ApplicationDao();
		User user = dao.getProfileDetails(username);
		
		Map<String, Double> weightSummary = new HashMap<>();
		weightSummary.put("January", 67.9);
		weightSummary.put("February", 65.9);
		weightSummary.put("March", 64.8);
		
		//store all information in request object
		req.setAttribute("user", user);
		req.setAttribute("weightSummary", weightSummary);
		
		//forward Control to profile jsp
		req.getRequestDispatcher("/html/profile.jsp").forward(req, resp);
	}
	
	
}

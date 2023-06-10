package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Product;
import dao.ApplicationDao;

@WebServlet("/addProduct")
public class AddProduct extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//get HTTP Session Object
		HttpSession session = req.getSession();
		
		//create a cart as arraylist for the user
		List<String> cart = (ArrayList<String>)session.getAttribute("cart");
		
		if(cart == null) {
			cart = new ArrayList<>();
		}
		
		//add the selected  product to the cart
		if(req.getParameter("product") != null) {
			cart.add(req.getParameter("product"));
		}
		
		session.setAttribute("cart", cart);
		
		//get search criteria from search servlet
		String search = (String) session.getAttribute("search");
		
		//get the search result from dao
		ApplicationDao dao = new ApplicationDao();
		List<Product> products = dao.searchProducts(search);
		
		//set the search results in request scope
		req.setAttribute("products", products);
		
		//forward to searchResults.jsp
		req.getRequestDispatcher("/html/searchMyPractice.jsp").forward(req, resp);
	}
	
	

}

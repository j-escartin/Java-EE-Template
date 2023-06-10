package listener;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import dao.DBConnection;

@WebListener
public class ApplicationClassListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("in contextDestroyed method");
		Connection connection = (Connection) sce.getServletContext().getAttribute("dbConnection");
		
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("in context initialized method");
		Connection connection = DBConnection.getConnectionToDatabase();
		sce.getServletContext().setAttribute("dbConnection", connection);
		
	}
	
}

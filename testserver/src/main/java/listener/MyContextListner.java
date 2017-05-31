package listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import mysqlDB.DBConnection;
import services.SamplePost;

public class MyContextListner implements ServletContextListener {
	
	private static final Logger log = Logger.getLogger(MyContextListner.class);
	
	public void contextDestroyed(ServletContextEvent arg0) {
		
		try {
			DBConnection.getInstance().releasePool();
		} catch (Exception e) {
			log.error("", e);
		}

	}
	
	public void contextInitialized(ServletContextEvent event) {
		System.out.println("###############......Initializing context....##########################");
		
		ServletContext ctx = event.getServletContext();
		String log4jFile = ctx.getRealPath("/");
		System.out.println(log4jFile);
		
		
		String classFolder = ctx.getRealPath("/") + "WEB-INF"
				+ System.getProperty("file.separator") + "classes" + System.getProperty("file.separator");

		String sPath = classFolder + "log4j.properties";
		System.out.println(sPath);
		PropertyConfigurator.configure(sPath);
		
	}
}

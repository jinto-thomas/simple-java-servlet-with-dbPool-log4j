package listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.PropertyConfigurator;

public class MyContextListner implements ServletContextListener {
	
	public void contextDestroyed(ServletContextEvent arg0) {
		//release DB Pool
		
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

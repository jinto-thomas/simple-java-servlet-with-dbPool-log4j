package mysqlDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;

public class DBConnection {
	private static Logger log = Logger.getLogger(DBConnection.class);

	private static DBConnection instance = null;
	private InitialContext context;
	private BasicDataSource datasource;

	private DBConnection() {

		try {
			context = new InitialContext();
			datasource = (BasicDataSource) context.lookup("java:comp/env/jdbc/samco");
			
		} catch (NamingException e) {
			log.error("", e);
		}
	}

	public static DBConnection getInstance() {
		if (instance == null)
			instance = new DBConnection();

		return instance;
	}
	
	public Connection getConnection() throws SQLException {
		
		Connection c = datasource.getConnection();
		
		log.debug("Opening datasource connection : "
				+ System.identityHashCode(c));

		return c;
	}
	
	public void releasePool() {

		try {

			if (datasource != null)
				datasource.close();
			log.info("Closing datasource " + datasource);

		} catch (SQLException e) {
			log.warn("Closing datasource ", e);
		}

	}
	
	public static void closeConnection(Connection connection) {

		if (connection != null) {

			try {

				log.debug("Closing connection "
						+ System.identityHashCode(connection));
				connection.close();

			} catch (SQLException e) {
				log.warn(e);
			}
			connection = null;
		}
	}
	
	public static void closeResultSet(ResultSet rs) {

		if (rs != null) {

			try {

				log.debug("Closing resultset " + System.identityHashCode(rs));
				rs.close();
			} catch (SQLException e) {
				log.warn(e);
			}
			rs = null;
		}
	}
	
	public static void closePreparedStatement(PreparedStatement pstmt) {
		
		if ( pstmt != null) {

			try {

				log.debug("Closing prepared statement " + System.identityHashCode(pstmt));
				pstmt.close();
			} catch (SQLException e) {
				log.warn(e);
			}
			pstmt = null;
		}
	}

}

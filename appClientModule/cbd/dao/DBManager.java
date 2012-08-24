package cbd.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBManager {

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		String driver = "com.mysql.jdbc.Driver";

		String dbUrl = "jdbc:mysql://localhost/test";
		String user = "root";
		String pw = "yang4909!!";
				
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(dbUrl,user,pw);
		
		return conn;
	}

	public static void closeConnection(ResultSet rs) throws SQLException {
		rs.close();
		
	}

	public static void closeConnection(Connection conn, PreparedStatement pstmt, ResultSet rs) throws SQLException {
		conn.close();
		pstmt.close();
		rs.close();
		
	}

	public static void closeConnection(Connection conn, PreparedStatement pstmt) throws SQLException {
		conn.close();
		pstmt.close();
		
	}

	public static void execute(String string) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		conn = getConnection();
		pstmt = conn.prepareStatement(string);
		pstmt.executeUpdate();
		
		closeConnection(conn, pstmt);
		
	}

}

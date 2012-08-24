package cbd.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import cbd.dao.DBManager;

public class QuerySource {
	String sql;
	Object[] parameters;
	Connection conn;
	PreparedStatement pstmt;

	public QuerySource(String sql) {
		this.sql = sql;
	}

	public QuerySource(String sql, Object[] parameters) {
		this.sql = sql;
		this.parameters = parameters;
	}

	public int getParameterSize() {
		return parameters == null ? 0 : parameters.length;
	}

	public Object getParameter(int index) throws Exception {
		if (index >= getParameterSize()) {
			throw new Exception("INDEX 값이 파라미터의 갯수보다 많습니다.");
		}
		return parameters[index];
	}
	public PreparedStatement getPreparedStatement() throws Exception {
		if (conn == null) {
			conn = DBManager.getConnection();	
		}
		if ( pstmt == null) {		
			pstmt = conn.prepareStatement(sql);
		}
		return pstmt;
	}

	public void close() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
		if ( pstmt != null) {		
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

}

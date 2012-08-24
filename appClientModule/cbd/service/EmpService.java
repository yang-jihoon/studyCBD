package cbd.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cbd.dao.DBManager;
import cbd.domain.Dept;
import cbd.domain.Emp;

public class EmpService {
	public static final String selectSql = "select t_emp.emp_no, t_emp.emp_name, t_emp.position, t_dept.dept_code, t_dept.dept_name, t_dept.address " +
			"from t_emp, t_dept " +
			"where t_emp.dept_code = t_dept.dept_code ";
	
	protected Emp load(ResultSet rs) throws Exception {
		Dept dept = new Dept( rs.getString("dept_code"), rs.getString("dept_name"), rs.getString("address"));
		Emp emp = new Emp( rs.getString("emp_no"), rs.getString("emp_name"), rs.getString("position"), dept);
		
		return emp;
	}
	
	protected List<Emp> find(String sql, Object[] params) throws Exception {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Emp> result = new ArrayList<Emp>();
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			int paramSize = params != null ? params.length : 0;
			for (int i = 0; i<paramSize; i++) {
				pstmt.setObject((i+1), params[i]);
			}
			rs = pstmt.executeQuery();
			
			while (rs.next()) {				
				result.add(load(rs));
			}
		} catch(Exception e) {
			throw e;
		} finally {
			DBManager.closeConnection(conn, pstmt, rs);
		}
		
		return result;
	}
	
	public List<Emp> findEmpsByDept(Dept dept) throws Exception {
		String findByDeptSql = selectSql +
				"and t_emp.dept_code = ?";
		
		return find(findByDeptSql, new Object[]{dept.getDeptCode()});
	}
	
	public Emp createNewEmp( String empNo, String empName, String position, Dept dept) {
		Emp emp = new Emp(empNo, empName, position, dept);
		return emp;
	}
	
	public List<Emp> findAllEmp() throws Exception {
		String findAllSql = selectSql;

		return find(findAllSql, null);		
	}
}

package cbd.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cbd.dao.DBManager;
import cbd.domain.Dept;
import cbd.domain.Emp;

public class EmpService {
	public List<Emp> findEmpsByDept(Dept dept) throws Exception {
		List<Emp> result = new ArrayList<Emp>();
		List<Emp> allEmps = findAllEmp();
		for (Iterator<Emp> it = allEmps.iterator(); it.hasNext(); ) {
			Emp emp = (Emp)it.next();
			if (emp.isBelongTo(dept)) {
				result.add(emp);
			}
		}
		return result;
	}
	
	public Emp createNewEmp( String empNo, String empName, String position, Dept dept) {
		Emp emp = new Emp(empNo, empName, position, dept);
		return emp;
	}
	
	public List<Emp> findAllEmp() throws Exception {
		String findAllSql = "select t_emp.emp_no, t_emp.emp_name, t_emp.position, t_dept.dept_code, t_dept.dept_name, t_dept.address " +
				"from t_emp, t_dept " +
				"where t_emp.dept_code = t_dept.dept_code";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Emp> result = new ArrayList<Emp>();
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(findAllSql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Dept dept = new Dept( rs.getString("dept_code"), rs.getString("dept_name"), rs.getString("address"));
				Emp emp = new Emp( rs.getString("emp_no"), rs.getString("emp_name"), rs.getString("position"), dept);
				result.add(emp);
			}
		} catch(Exception e) {
			throw e;
		} finally {
			DBManager.closeConnection(conn, pstmt, rs);
		}
		
		return result;
		
	}
}

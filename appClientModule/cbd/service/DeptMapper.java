package cbd.service;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cbd.dao.DBManager;
import cbd.dao.DBMapper;
import cbd.domain.Dept;
import cbd.domain.DeptKey;
import cbd.domain.DomainKey;
import cbd.domain.DomainObject;
import cbd.domain.EmpKey;

public class DeptMapper extends DBMapper{
	protected static final String selectSql = "select " +
			"t_emp.emp_no as t_emp_emp_no" +
			", t_emp.emp_name as t_emp_emp_name" +
			", t_emp.position as t_emp_position" +
			", t_dept.dept_code as t_dept_dept_code" +
			", t_dept.dept_name as t_dept_dept_name" +
			", t_dept.address as t_dept_address " +
			"from t_emp, t_dept " +
			"where t_emp.dept_code = t_dept.dept_code ";
	
	protected String getFindAllSql() {
		return selectSql;
	}
	
	protected String getFindByKeySql() {
		return selectSql +
				" and t_dept.dept_code = ?";
	}
	
	public Dept doLoad(ResultSet rs) throws Exception {		
		return new Dept( rs.getString("t_dept_dept_code"), rs.getString("t_dept_dept_name"), rs.getString("t_dept_address"), null);
	}
	
	public DomainKey getKey(ResultSet rs) throws Exception {
		return new DeptKey(rs.getString("t_dept_dept_code"));
	}


	public DomainObject findByKey(DomainKey key) throws Exception {
		
		QuerySource source = new QuerySource(getFindByKeySql(), key.getKeyFields());
		ResultSet rs = null;
		
		try {
			rs = executeQuery(source);
			DeptEmpLoader loader = new DeptEmpLoader(this, new EmpMapper());
			List depts = loader.load(rs);
			
			if(depts.size() == 0) {
				throw new Exception("해당 객체가 존재하지 않습니다.");
			}				
			return (Dept)depts.get(0);
		} catch (Exception e) {
			throw e;
		} finally {
			source.close();
			DBManager.closeConnection(rs);
		}
	}
	

	protected List find(QuerySource source) throws Exception {

		ResultSet rs = null;
		
		try {
			
			rs = executeQuery(source);
			
			DeptEmpLoader loader = new DeptEmpLoader(this, new EmpMapper());
			return loader.load(rs);
		} catch(Exception e) {
			throw e;
		} finally {
			source.close();
			DBManager.closeConnection(rs);
		}
	}
	
}

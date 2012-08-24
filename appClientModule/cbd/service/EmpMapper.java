package cbd.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import cbd.dao.DBMapper;
import cbd.domain.Dept;
import cbd.domain.DomainKey;
import cbd.domain.DomainObject;
import cbd.domain.Emp;
import cbd.domain.EmpKey;

public class EmpMapper extends DBMapper {
	protected static final String selectSql = "select " +
			"t_emp.emp_no as t_emp_emp_no" +
			", t_emp.emp_name as t_emp_emp_name" +
			", t_emp.position as t_emp_position" +
			"from t_emp";
	
	protected String getFindAllSql() {
		return selectSql;
	}
	
	protected String getFindByKeySql() {
		return selectSql +
				"where t_emp.emp_no = ?";
	}

	public DomainObject doLoad(ResultSet rs) throws Exception {			

			Emp emp = new Emp( rs.getString("t_emp_emp_no"), rs.getString("t_emp_emp_name"), rs.getString("t_emp_position"));
			
			return emp;		
	}	
	
	public DomainKey getKey(ResultSet rs) throws Exception {
		return new EmpKey(rs.getString("t_emp_emp_no"));
	}

	public List<Emp> findEmpsByDept(Dept dept) throws Exception {
		String findByDeptSql = selectSql +
				"and t_emp.dept_code = ?";
		
		return find(new QuerySource(findByDeptSql, new Object[]{dept.getDeptCode()}));
	}
	
}

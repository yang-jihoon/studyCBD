package cbd.service;

import java.sql.ResultSet;
import java.util.List;

import cbd.dao.DBMapper;
import cbd.domain.Dept;
import cbd.domain.DomainObject;
import cbd.domain.Emp;

public class EmpMapper extends DBMapper {
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
				"and t_emp.dept_code = ?";
	}

	public DomainObject load(ResultSet rs) throws Exception {
		DeptMapper deptMapper = new DeptMapper();
		
		Dept dept = deptMapper.load(rs);
		Emp emp = new Emp( rs.getString("t_emp_emp_no"), rs.getString("t_emp_emp_name"), rs.getString("t_emp_position"), dept);
		
		return emp;
	}
	
	
	public List<Emp> findEmpsByDept(Dept dept) throws Exception {
		String findByDeptSql = selectSql +
				"and t_emp.dept_code = ?";
		
		return find(new QuerySource(findByDeptSql, new Object[]{dept.getDeptCode()}));
	}
	
}

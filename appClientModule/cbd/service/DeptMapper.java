package cbd.service;

import java.sql.ResultSet;
import cbd.dao.DBMapper;
import cbd.domain.Dept;

public class DeptMapper extends DBMapper{
	protected static final String selectSql = "select " +
			"t_dept.dept_code as t_dept_dept_code" +
			", t_dept.dept_name as t_dept_dept_name" +
			", t_dept.address as t_dept_address " +
			"from t_dept ";
	
	protected String getFindAllSql() {
		return selectSql;
	}
	
	protected String getFindByKeySql() {
		return selectSql +
				"and t_dept.dept_code = ?";
	}
	
	public Dept load(ResultSet rs) throws Exception {		
		return new Dept( rs.getString("t_dept_dept_code"), rs.getString("t_dept_dept_name"), rs.getString("t_dept_address"));
	}

}

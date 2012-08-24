package cbd.service;

import java.util.List;
import cbd.domain.Dept;
import cbd.domain.Emp;

public class EmpService {
	
	public List<Emp> findEmpsByDept(Dept dept) throws Exception {
		EmpMapper mapper = new EmpMapper();
		return mapper.findEmpsByDept(dept);
	}
	
	public List<Emp> findAllEmp() throws Exception {
		EmpMapper mapper = new EmpMapper();
		return mapper.findAll();
	}
}

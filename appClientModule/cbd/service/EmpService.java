package cbd.service;

import java.util.Iterator;
import java.util.List;
import cbd.domain.Dept;
import cbd.domain.Emp;
import cbd.domain.EmpKey;

public class EmpService {
	
	public List<Emp> findEmpsByDept(Dept dept) throws Exception {
		EmpMapper mapper = new EmpMapper();
		return mapper.findEmpsByDept(dept);
	}
	
	public List<Emp> findAllEmp() throws Exception {
		EmpMapper mapper = new EmpMapper();
		return mapper.findAll();
	}

	public void printForMove(List emps) {
		for (Iterator it = emps.iterator(); it.hasNext();) {
			Emp emp = (Emp)it.next();
			emp.printForMove();
		}
		
	}

	public Emp findEmpByKey(EmpKey empKey) throws Exception {
		EmpMapper mapper = new EmpMapper();
		return (Emp) mapper.findByKey(empKey);
	}
}

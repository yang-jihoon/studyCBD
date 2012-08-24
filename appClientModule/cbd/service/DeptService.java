package cbd.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cbd.domain.Dept;
import cbd.domain.DeptKey;

public class DeptService {
	public List moveDept() throws Exception {
		List result = new ArrayList();
		DeptMapper deptMapper = new DeptMapper();
		
		List depts = deptMapper.findAll();
		
		for (Iterator it = depts.iterator(); it.hasNext();) {
			Dept dept = (Dept)it.next();
			if (dept.move(2,"º°°ü3Ãþ")) {
				result.add(dept);
			}
		}
		
		return result;
	}

	public Dept findDeptByKey(DeptKey deptKey) throws Exception {
		DeptMapper deptMapper = new DeptMapper();
		return (Dept) deptMapper.findByKey(deptKey);
	}
}

package cbd.domain;

import java.util.ArrayList;
import java.util.List;

import cbd.service.EmpService;

public class Dept extends DomainObject {
	String deptCode;
	String deptName;
	String address;
	
	List emps;
	
	public Dept(String deptCode, String deptName, String address, List emps) {
		super(new DeptKey(deptCode));
		this.deptCode = deptCode;
		this.deptName = deptName;
		this.address = address;
		this.emps = emps;
	}

	public String getDeptCode() {
		return this.deptCode;
	}

	public boolean move(int limitsEmpCount, String newAddress) throws Exception {
		EmpService empService = new EmpService();
		
		List emps = empService.findEmpsByDept(this);
		
		if (emps.size() < limitsEmpCount +1) {
			this.setAddress(newAddress);
			empService.printForMove(emps);
			return true;
		}
		return false;
	}

	public void setAddress(String address) {
		this.address = address;
		
	}
	
	public String getAddress() {
		return this.address;
	}

	public String getDeptName() {
		return this.deptName;
	}

	public void addEmp(Emp emp) {
		if(emps == null) {
			emps = new ArrayList();
			if (emps.contains(emp)) {
				return;
			}				
		}
		emps.add(emp);		
	}

	public int countEmps() {
		return (this.emps == null ) ? 0 : this.emps.size();
	}
}

package cbd.domain;

import java.util.List;

import cbd.service.EmpService;

public class Dept extends DomainObject {
	String deptCode;
	String deptName;
	String address;
	
	public Dept(String deptCode, String deptName, String address) {
		super(new DeptKey(deptCode));
		this.deptCode = deptCode;
		this.deptName = deptName;
		this.address = address;
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
}

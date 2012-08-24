package cbd.domain;

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
}

package cbd.domain;

public class Emp extends DomainObject{
	String empNo;
	String empName;
	String position;
	
	Dept dept;
	
	public Emp(String empNo, String empName, String position, Dept dept) {
		super(new EmpKey(empNo));
		this.empNo = empNo;
		this.empName = empName;
		this.position = position;
		this.dept = dept;
	}
	
	public boolean isBelongTo(Dept dept) {
		return this.dept.equals(dept);
	}
	
	public void setDept(Dept dept) {
		this.dept = dept;
	}
}

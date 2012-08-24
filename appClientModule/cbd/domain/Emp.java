package cbd.domain;

public class Emp extends DomainObject{
	String empNo;
	String empName;
	String position;
		
	public Emp(String empNo, String empName, String position) {
		super(new EmpKey(empNo));
		this.empNo = empNo;
		this.empName = empName;
		this.position = position;
	}

	public String printForMove() {
		System.out.println(this.toString());
		return this.toString();		
	}

	@Override
	public String toString() {
		return "Emp [empNo=" + empNo + ", empName=" + empName + ", position="
				+ position + "]";
	}
	
}

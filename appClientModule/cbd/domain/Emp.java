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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((empNo == null) ? 0 : empNo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Emp other = (Emp) obj;
		if (empNo == null) {
			if (other.empNo != null)
				return false;
		} else if (!empNo.equals(other.empNo))
			return false;
		return true;
	}
}

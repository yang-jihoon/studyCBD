package cbd.domain;

public class Dept {
	String deptCode;
	String deptName;
	String address;
	
	public Dept(String deptCode, String deptName, String address) {
		this.deptCode = deptCode;
		this.deptName = deptName;
		this.address = address;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((deptCode == null) ? 0 : deptCode.hashCode());
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
		Dept other = (Dept) obj;
		if (deptCode == null) {
			if (other.deptCode != null)
				return false;
		} else if (!deptCode.equals(other.deptCode))
			return false;
		return true;
	}

	public String getDeptCode() {
		return this.deptCode;
	}
}

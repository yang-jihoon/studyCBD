package cbd.domain;

public class DeptKey extends DomainKey {
	public DeptKey(String deptCode) {
		super(new String[]{deptCode});
	}
}

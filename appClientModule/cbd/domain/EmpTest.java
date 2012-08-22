package cbd.domain;

import org.junit.Test;

import junit.framework.Assert;

public class EmpTest {
	Dept dept = new Dept("D001", "�渮��", "����11��");
	
	@Test
	public void testIsBelongTo() {
		Dept empDept = new Dept("D001", "�渮��", "����11��");
		
		Emp emp = new Emp("E0001", "ȫ�浿", "����", empDept);
		
		Assert.assertTrue(emp.isBelongTo(dept));
	}
}

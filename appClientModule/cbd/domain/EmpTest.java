package cbd.domain;

import org.junit.Test;

import junit.framework.Assert;

public class EmpTest {
	Dept dept = new Dept("D001", "�渮��", "����11��",null);
	
	@Test
	public void testIsBelongTo() {
		Dept empDept = new Dept("D001", "�渮��", "����11��",null);
		
		Emp emp = new Emp("E0001", "ȫ�浿", "����");
		
//		Assert.assertTrue(emp.isBelongTo(dept));
	}
}

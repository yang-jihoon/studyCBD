package cbd.domain;

import org.junit.Test;

import junit.framework.Assert;

public class EmpTest {
	Dept dept = new Dept("D001", "경리과", "본사11층",null);
	
	@Test
	public void testIsBelongTo() {
		Dept empDept = new Dept("D001", "경리과", "본사11층",null);
		
		Emp emp = new Emp("E0001", "홍길동", "과장");
		
//		Assert.assertTrue(emp.isBelongTo(dept));
	}
}

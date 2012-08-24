package cbd.service;

import java.util.List;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import cbd.domain.Dept;
import cbd.domain.DeptKey;
import cbd.domain.Emp;
import cbd.domain.EmpKey;


public class DeptServiceTest {
	
	@BeforeClass
	public static void beforeTest() throws Exception {
		EmpServiceTest.beforeTest();
	}
	
	@AfterClass
	public static void afterTest() throws Exception {
		EmpServiceTest.afterTest();
	}
	@Test
	public void testMoveDept() throws Exception {
		DeptService deptService = new DeptService();
		List movedDepts = deptService.moveDept();
		
		Assert.assertEquals(1, movedDepts.size());
		Assert.assertEquals("ÃÑ¹«°ú", ((Dept) movedDepts.get(0)).getDeptName());
	}
	
	@Test
	public void testSetAddress() throws Exception {
		EmpService empService = new EmpService();
		Emp emp = empService.findEmpByKey(new EmpKey("E00001"));
		DeptService deptService = new DeptService();
		Dept dept = deptService.findDeptByKey((DeptKey)(emp.getDept().getKey()));
		dept.setAddress("º°°ü3Ãþ");
		
		Assert.assertEquals("º°°ü3Ãþ", emp.getDept().getAddress());		
	}
}

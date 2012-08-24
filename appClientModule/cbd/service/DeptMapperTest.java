package cbd.service;

import java.util.List;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import cbd.domain.Dept;
import cbd.domain.DeptKey;

public class DeptMapperTest {

	@BeforeClass
	public static void beforeTest() throws Exception {
		EmpServiceTest.beforeTest();
	}
	
	@AfterClass
	public static void afterTest() throws Exception {
		EmpServiceTest.afterTest();
	}
	
	@Test
	public void testFindByKey () throws Exception {
		DeptMapper deptMapper = new DeptMapper();
		Dept dept = (Dept) deptMapper.findByKey(new DeptKey("D001"));
		Assert.assertNotNull(dept);
		Assert.assertEquals("°æ¸®°ú", dept.getDeptName());
		Assert.assertEquals(3, dept.countEmps());
	}
	
	@Test
	public void testFindAll() throws Exception {
		DeptMapper deptMapper = new DeptMapper();
		List depts = deptMapper.findAll();
		
		Assert.assertEquals(2, depts.size());
		
	}
}

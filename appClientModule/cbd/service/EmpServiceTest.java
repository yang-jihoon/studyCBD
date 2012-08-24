package cbd.service;

import java.util.List;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import cbd.dao.DBManager;
import cbd.domain.Dept;
import cbd.domain.Emp;

public class EmpServiceTest {
	
	@BeforeClass
	public static void beforeTest() throws Exception {
		DBManager.execute("INSERT INTO T_DEPT VALUES ('D001', '경리과', '본사12층')");
		DBManager.execute("INSERT INTO T_DEPT VALUES ('D002', '총무과', '본사11층')");
		
		DBManager.execute("INSERT INTO T_EMP VALUES ('E00001', '홍길동', '과장', 'D001')");
		DBManager.execute("INSERT INTO T_EMP VALUES ('E00002', '박문수', '대리', 'D001')");
		DBManager.execute("INSERT INTO T_EMP VALUES ('E00003', '이순신', '대리', 'D001')");
		DBManager.execute("INSERT INTO T_EMP VALUES ('E00004', '강감찬', '사원', 'D002')");
		DBManager.execute("INSERT INTO T_EMP VALUES ('E00005', '장길산', '사원', 'D002')");
	}
	
	@AfterClass
	public static void afterTest() throws Exception {

		DBManager.execute("DELETE FROM T_DEPT");
		DBManager.execute("DELETE FROM T_EMP");
	}
	
	@Test
	public void testFindAllEmp() throws Exception {
		EmpService empService = new EmpService();
		
		List<Emp> emps = empService.findAllEmp();
		
		Assert.assertEquals("전체 사원 5명", 5, emps.size());		
	}
	
	@Test
	public void testFindEmpsByDept() throws Exception {
		EmpService empService = new EmpService();
		Dept dept = new Dept("D001", "경리과", "본사11층",null);
		List<Emp> emps = empService.findEmpsByDept(dept);
		
		Assert.assertEquals(3, emps.size());
	}
	
}

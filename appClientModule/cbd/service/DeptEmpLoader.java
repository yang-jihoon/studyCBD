package cbd.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cbd.domain.Dept;
import cbd.domain.DeptKey;
import cbd.domain.DomainKey;
import cbd.domain.DomainObject;
import cbd.domain.Emp;

public class DeptEmpLoader {
	DeptMapper deptMapper;
	EmpMapper empMapper;
	
	protected Map inProgress = new HashMap();
	protected List resultKeys = new ArrayList();

	public DeptEmpLoader(DeptMapper deptMapper, EmpMapper empMapper) {
		this.deptMapper = deptMapper;
		this.empMapper = empMapper;				
	}

	public List load(ResultSet rs) throws Exception {
		while ( rs.next() ) {
			loadRow(rs);
		}
		addAllNewObjectToPool();
		return makeResult();
	}

	private void loadRow(ResultSet rs) throws Exception {
		DeptKey key = (DeptKey)deptMapper.getKey(rs);
		if(!resultKeys.contains(key)) {
			resultKeys.add(key);
		}
		if(!TxManager.getTxManager().containsInPool(key)){
			if(!inProgress.keySet().contains(key)){
				inProgress.put(key, deptMapper.doLoad(rs));
			}
			Dept dept = (Dept)inProgress.get(key);
			dept.addEmp((Emp)empMapper.load(rs));
		}
	}

	private List makeResult() {
		List result = new ArrayList();
		
		for (Iterator it = resultKeys.iterator() ; it.hasNext();) {
			result.add(TxManager.getTxManager().getObjectFromPool((DomainKey)it.next()));
		}
		return result;
	}

	private void addAllNewObjectToPool() {
		DomainKey key;
		DomainObject domainObject;
		for (Iterator it = inProgress.keySet().iterator() ; it.hasNext();) {
			key = (DomainKey)it.next();
			if(!TxManager.getTxManager().containsInPool(key)) {
				domainObject = (DomainObject) inProgress.get(key);
				TxManager.getTxManager().addObjectToPool(domainObject);
			}
		}
	}

}

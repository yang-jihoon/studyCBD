package cbd.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import cbd.domain.DomainKey;
import cbd.domain.DomainObject;
import cbd.service.Mapper;
import cbd.service.QuerySource;
import cbd.service.TxManager;

public abstract class DBMapper implements Mapper {
	public abstract DomainObject doLoad(ResultSet rs) throws Exception;
	public abstract DomainKey getKey(ResultSet rs) throws Exception;
	protected abstract String getFindAllSql();
	protected abstract String getFindByKeySql();
	
	public ResultSet executeQuery(QuerySource source) throws Exception {
		PreparedStatement pstmt = source.getPreparedStatement();
		
		for (int i = 0; i<source.getParameterSize(); i++) {
			pstmt.setObject((i+1), source.getParameter(i));
		}
		return pstmt.executeQuery();
	}
	
	public DomainObject load(ResultSet rs) throws Exception {
		DomainKey key = getKey(rs);
		
		if (TxManager.getTxManager().containsInPool(key)) {
			return TxManager.getTxManager().getObjectFromPool(key);
		} else {			
			DomainObject result = doLoad(rs);
			TxManager.getTxManager().addObjectToPool(result);
			return result;
		}		
	}

	protected List find(QuerySource source) throws Exception {

		List result = new ArrayList();
		ResultSet rs = null;
		
		try {
			
			rs = executeQuery(source);
			
			while (rs.next()) {				
				result.add(load(rs));
			}
		} catch(Exception e) {
			throw e;
		} finally {
			source.close();
			DBManager.closeConnection(rs);
		}
		
		return result;
	}
	

	public DomainObject findByKey(DomainKey key) throws Exception {
		
		QuerySource source = new QuerySource(getFindByKeySql(), key.getKeyFields());
		ResultSet rs = null;
		
		try {
			rs = executeQuery(source);
			if (rs.next()) {
				return load(rs);
			} else {
				throw new Exception("해당 객체가 존재하지 않습니다.");
			}				
		} catch (Exception e) {
			throw e;
		} finally {
			source.close();
			DBManager.closeConnection(rs);
		}
	}

	
	public List findAll() throws Exception {
		return find(new QuerySource(getFindAllSql()));	
	}

	
	public void insert(DomainObject domainObject) throws Exception {
		// TODO Auto-generated method stub
		
	}


	public int update(DomainObject domainObject) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}


	public int delete(DomainObject domainObject) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}
}

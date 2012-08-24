package cbd.service;

import java.util.List;

import cbd.domain.DomainKey;
import cbd.domain.DomainObject;

public interface Mapper {
	public DomainObject findByKey(DomainKey key) throws Exception;
	public <T> List<T> findAll() throws Exception;
	public void insert(DomainObject domainObject) throws Exception;
	public int update (DomainObject domainObject) throws Exception;
	public int delete (DomainObject domainObject) throws Exception;
}

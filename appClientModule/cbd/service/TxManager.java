package cbd.service;

import java.util.HashMap;

import cbd.domain.DomainKey;
import cbd.domain.DomainObject;

public class TxManager {
	HashMap objectPool;
	
	public void intPool() {
		objectPool = new HashMap();
	}
	
	public void addObjectToPool(DomainObject obj) {
		objectPool.put(obj.getKey(), obj);
	}
	
	public DomainObject getObjectFromPool(DomainKey key) {
		return (DomainObject)objectPool.get(key);
	}
	
	public boolean containsInPool(DomainKey key) {
		return objectPool.containsKey(key);
	}
}

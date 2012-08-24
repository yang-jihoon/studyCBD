package cbd.service;

import java.util.HashMap;

import cbd.domain.DomainKey;
import cbd.domain.DomainObject;

public class TxManager {

	private static ThreadLocal threadLocal = new ThreadLocal();
	HashMap<DomainKey,DomainObject> objectPool;
	
	public static TxManager getTxManager() {
		TxManager txManager =  (TxManager)threadLocal.get();
		if (txManager == null) {
			txManager = new TxManager();
			txManager.intPool();
			threadLocal.set(txManager);
		}
		return txManager;
	}
	
	public void intPool() {
		objectPool = new HashMap<DomainKey,DomainObject>();
	}
	
	public void addObjectToPool(DomainObject obj) {
		objectPool.put(obj.getKey(), obj);
	}
	
	public DomainObject getObjectFromPool(DomainKey key) {
		return objectPool.get(key);
	}
	
	public boolean containsInPool(DomainKey key) {
		return objectPool.containsKey(key);
	}
}

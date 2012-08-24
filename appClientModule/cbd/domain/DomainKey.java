package cbd.domain;

import java.util.Arrays;

public abstract class DomainKey {
	Object[] keyFields;
	
	public DomainKey(Object[] keyField) {
		this.keyFields = keyField;
	}
	
	public int getKeyFieldSize() {
		return keyFields == null ? 0 : keyFields.length;
	}
	
	public Object getKeyField(int index) {
		if (keyFields == null || index >= keyFields.length) {
			return null;
		}
		return keyFields[index];
	}
	
	public Object[] getKeyFields() {
		return keyFields;
	}
	
	public boolean isNull() {
		if (keyFields == null) {
			return true;
		}
		
		for (Object obj : keyFields) {
			if ( obj == null) {
				return true;
			}
		}
		
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(keyFields);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		DomainKey other = (DomainKey) obj;
		if (!Arrays.equals(keyFields, other.keyFields))
			return false;
		return true;
	}

	@Override
	public String toString() {
		if (keyFields == null) {
			return getClass().getName() + " : Empty Key Field";
		}
		String result = "[" + getClass().getName() + "]key value[";

		for (Object obj : keyFields) {
			result += obj.toString()+",";
		}
		return result.substring(0, result.lastIndexOf(",")) +"]";
	}
	
	
}

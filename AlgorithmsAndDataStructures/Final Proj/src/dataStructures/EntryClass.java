package dataStructures;

public class EntryClass<K,V> implements Entry<K, V> {

	private static final long serialVersionUID = 1L;
	
	K key;
	V value;
	
	EntryClass(K k, V v){
		key = k;
		value = v;
	}
	
	public K getKey() {
		return key;
	}

	public V getValue() {
		return value;
	}
	
	public void setKey(K k){
		key = k;
	}
	
	public void setValue(V v){
		value = v;
	}

}

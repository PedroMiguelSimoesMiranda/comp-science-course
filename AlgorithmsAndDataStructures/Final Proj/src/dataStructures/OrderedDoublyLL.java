package dataStructures;

public class OrderedDoublyLL<K extends Comparable<K>, V> implements OrderedDictionary<K, V> {

	static final long serialVersionUID = 1L;


    // Node at the head of the list.
    protected DListNode<Entry<K,V>> head;

    // Node at the tail of the list.
    protected DListNode<Entry<K,V>> tail;

    // Number of elements in the list.
    protected int currentSize;
    
	
	public Entry<K, V> maxEntry() throws EmptyDictionaryException {
		if ( this.isEmpty() )
            throw new EmptyDictionaryException();
		
		return tail.getElement();
	}

	public Entry<K, V> minEntry() throws EmptyDictionaryException {
		if ( this.isEmpty() )
            throw new EmptyDictionaryException();
		
		return head.getElement();
	}

	public V find(K key) {
		DListNode<Entry<K,V>> auxNode = this.findNode(key);
		
		if(auxNode==null || auxNode.getElement().getKey().compareTo(key)!=0)
			return null;
		else
			return auxNode.getElement().getValue();
	}

	public V insert(K key, V value) {
		//if this is empty
		if(this.isEmpty()){
			DListNode<Entry<K,V>> newNode = new DListNode<Entry<K,V>> (new EntryClass<K,V>(key,value),null,null);
			head = newNode;
			tail = newNode;
			currentSize++;
		}
		
		DListNode<Entry<K,V>> auxNode = this.findNode(key);
		
		//if auxNode = null, insert in tail
		if(auxNode==null){
			DListNode<Entry<K,V>> newNode = new DListNode<Entry<K,V>> (new EntryClass<K,V>(key,value),tail,null);
			tail.setNext(newNode);
			tail = newNode;
			currentSize++;
			return null;
		}
		//if key already exists, replace value and return old value
		else if(auxNode.getElement().getKey().compareTo(key)==0){
			Entry<K,V> retval = auxNode.getElement();
			auxNode.setElement(new EntryClass<K,V>(key,value));
			return retval.getValue();
		}
		
		//key does not exist and previous is null, insert into head
		else if (auxNode.getPrevious()==null){
			DListNode<Entry<K,V>> newNode = new DListNode<Entry<K,V>> (new EntryClass<K,V>(key,value),null,head);
			head.setPrevious(newNode);
			head = newNode;
			currentSize++;
			return null;
		}
		
		//key does not exist and next is null, insert into tail
		/*else if(auxNode.getNext()==null){
			DListNode<Entry<K,V>> newNode = new DListNode<Entry<K,V>> (new EntryClass<K,V>(key,value),tail,null);
			tail.setNext(newNode);
			tail = newNode;
			currentSize++;
			return null;
		}*/
		
		//key does not exist, insert between here and previous
		else{
			DListNode<Entry<K,V>> newNode = 
				new DListNode<Entry<K,V>> (new EntryClass<K,V>(key,value),auxNode.getPrevious(),auxNode);
			auxNode.getPrevious().setNext(newNode);
			auxNode.setPrevious(newNode);
			currentSize++;
			return null;
		}
		
	}

	public boolean isEmpty() {
		return currentSize==0;
	}

	public Iterator<Entry<K, V>> iterator() {
		return new DoublyLLIterator<Entry<K,V>>(head, tail);
	}

	public V remove(K key) {
		DListNode<Entry<K,V>> auxNode = this.findNode(key);
		if(auxNode.getElement().getKey().compareTo(key)==0){
			V auxV = auxNode.getElement().getValue();
			//if only 1 element
			if (head.equals(tail)){
				head=null;
				tail=head;
			}
			//if Node=head
			else if(head.equals(auxNode)){
				head = auxNode.getNext();
				head.setPrevious(null);
			}
			//if Node=tail
			else if(tail.equals(auxNode)){
				tail = auxNode.getPrevious();
				tail.setNext(null);
			}
			//if in middle
			else {
				auxNode.getNext().setPrevious(auxNode.getPrevious());
				auxNode.getPrevious().setNext(auxNode.getNext());
			}
			currentSize--;
			return auxV;
		}
		return null;
	}

	public int size() {
		return currentSize;
	}
	
	private DListNode<Entry<K,V>> findNode(K key){
		DListNode<Entry<K,V>> auxNode = head;
		while(auxNode!=null && auxNode.getElement().getKey().compareTo(key)<0){
			auxNode = auxNode.getNext();
		}
		return auxNode;
	}

}

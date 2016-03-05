package dataStructures;

public class InvertibleQueueInList<Object> extends QueueInList<Object> implements InvertibleQueue<Object>{
	
	protected boolean isInverted;
	
	InvertibleQueueInList(){
		super();
		isInverted = false;
	}
	
	public void invert() {
		isInverted = !isInverted;
	}

	public Object dequeue() throws EmptyQueueException {
		if(list.isEmpty())
			throw new EmptyQueueException("Empty queue.");
		if (isInverted)
			return list.getFirst();
		return list.getLast();
	}

	public void enqueue(Object element) {
		if (isInverted)
			list.addLast(element);
		else
			list.addFirst(element);
	}

}

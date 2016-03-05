package dataStructures;

public class ConcatenableQueueInList<E> extends QueueInList<E> implements ConcatenableQueue<E>{

	public void append(ConcatenableQueue<E> queue) {
		if (queue instanceof ConcatenableQueueInList){
			ConcatenableQueueInList<E> qaux = (ConcatenableQueueInList<E>)queue;
			DoublyLinkedList<E> l1 = (DoublyLinkedList<E>)this.list;
			DoublyLinkedList<E> l2 = (DoublyLinkedList<E>)qaux.list;
			l1.append(l2);
		}
		else
			while(!queue.isEmpty())
				this.enqueue(queue.dequeue());
	}
}

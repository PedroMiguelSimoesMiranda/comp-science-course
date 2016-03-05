package dataStructures;

public class BSTInorderIterator<K,V> {

	BSTNode<K,V> root;
	Stack<BSTNode<K,V>> pilha;
	
	BSTInorderIterator(BSTNode<K,V> r){
		root = r;
		rewind();
	}
	
	void rewind(){
		pilha = new StackInList<BSTNode<K,V>>();//Stack
		pilha.push(root);
		stackAllLeft(root);
	}
	
	boolean hasNext(){
		return !pilha.isEmpty();
	}
	
	BSTNode<K,V> next() throws EmptyStackException{
		if (!this.hasNext())
			throw new EmptyStackException();
		BSTNode<K,V> auxnode = pilha.pop();
		if(auxnode.getRight()!=null)
			stackAllLeft(auxnode.getRight());
		return auxnode;
	}
	
	void stackAllLeft(BSTNode<K,V> node){
		BSTNode<K,V> auxnode = node;
		while(auxnode.getLeft()!=null)
			pilha.push(auxnode.getLeft());
	}
	
}

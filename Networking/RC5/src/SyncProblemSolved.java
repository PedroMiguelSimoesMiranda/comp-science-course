import java.util.*;

public class SyncProblemSolved implements Runnable {
	List<Integer> values;
	
	SyncProblemSolved() {
		values = new LinkedList<Integer>();
	}

	synchronized void insert( int v ) {
		if( ! values.contains( v ) )
			values.add( v ) ;
	}

	void checkSize() {
		System.out.println("Comprimento da lista: " + values.size() ) ; 
 	}
	
	void doit() throws Exception {
		Thread t1 = new Thread( this) ;
		Thread t2 = new Thread( this) ;

		t1.start(); t2.start();
		
		t1.join() ; t2.join() ;
	
		checkSize() ;
	}
	
	public void run() {
		for( int i = 0; i < 10000; i++)
			insert( i ) ;
	}

	public static void main( String[] args) throws Exception {
		new SyncProblemSolved().doit();
	}

}

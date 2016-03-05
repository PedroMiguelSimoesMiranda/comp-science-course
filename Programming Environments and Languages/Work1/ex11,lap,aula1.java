import java.util.*;

// Este e' um tipo-objecto:

interface IntStack {
    int Capacity() ;
    int Size() ;
    boolean Empty() ;
    boolean Full() ;
    int Top() ;
    void Push(int v) ;
    void PushN(int... vs) ;
    void Pop() ;
    void PopN(int n) ;
}

// Esta e' uma implementacao do tipo-objecto anterior:

class IntStackClass implements IntStack {
    private final static int defaultCapacity = 100 ;
    private int[] elems ;
    private int top ;

    IntStackClass(int capacity) {
        elems = new int[capacity] ;
        top = 0 ;
    }
    IntStackClass() {
        this(defaultCapacity) ;
    }

    public int Capacity() {
        return elems.length ;
    }
    public int Size() {
        return top ;
    }
    public boolean Empty() {
        return Size() == 0 ;
    }
    public boolean Full() {
        return Size() == Capacity() ;
    }
    public int Top() {
        if( !Empty() )
		return elems[top-1] ;
	else(
		System.out.println("Stack Underflow!") ;
            System.out.println("Bye bye!") ;
            System.exit(1) ;
	)
    }
    public void Push(int v) {
        if( !Full() )
        	elems[top++] = v ;
	else(
		System.out.println("Stack Overflow!") ;
            System.out.println("Bye bye!") ;
            System.exit(1) ;
	)
    }
    public void PushN(int... vs) {
        for(int v : vs)
            Push(v) ;
    }
    public void Pop() {
        if( !Empty() )
        	top-- ;
	else(
		System.out.println("Stack Underflow!") ;
            System.out.println("Bye bye!") ;
            System.exit(1) ;
	)
    }
    public void PopN(int n) {
        for( int i = 0 ; i < n ; i++ )
            Pop() ;
    }


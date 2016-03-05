package dataStructures;  

public class SepChainHashTable<K extends Comparable<K>, V> 
    extends HashTable<K,V> 
{ 

    static final long serialVersionUID = 0L;


    // The array of dictionaries.
    protected Dictionary<K,V>[] table;


    public SepChainHashTable( )
    {
        this(DEFAULT_CAPACITY);
    }                                                                


    public SepChainHashTable( int capacity )
    {
        int arraySize = HashTable.nextPrime((int) 1.1 * capacity);
        // Compiler gives a warning.
        table = (Dictionary<K,V>[]) new Dictionary[arraySize];
        for ( int i = 0; i < arraySize; i++ )
            // Original comentado para nao dar erro de compilacao.
            // table[i] = new OrderedDoublyLL<K,V>();
            table[i] = null;
        maxSize = capacity;
        currentSize = 0;
    }                                      


    // Returns the hash value of the specified key.
    protected int hash( K key )
    {
        return Math.abs( key.hashCode() ) % table.length;
    }


    // Returns the value in the dictionary whose key is the specified key;
    // or null if no such entry exists.                                
    public V find( K key )
    {
        return table[ this.hash(key) ].find(key);
    }


    // Inserts the entry (key, value) in the dictionary.
    // If the dictionary already contained an entry with the specified key,
    // returns the old value (which is replaced by the specified value);
    // otherwise, returns null.
    public V insert( K key, V value )
    {
        if ( this.isFull() )
            // Original comentado para nao dar erro de compilacao.
            // this.rehash();
            return null;

        // Left as an exercise.
        return null;
    }


    // Removes the entry whose key is the specified key from the dictionary
    // and returns the associated value, if such entry exists.
    // Otherwise, returns null.
    public V remove( K key )
    {
        // Left as an exercise.
        return null;
    }


    // Returns an iterator of the entries in the dictionary.
    public Iterator<Entry<K,V>> iterator( )
    {
        // Left as an exercise.
        return null;
    } 


}

































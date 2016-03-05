package dataStructures; 

import java.io.Serializable;

public interface Dictionary<K,V> extends Serializable
{                                                                   

    // Returs true iff the dictionary contains no entries.
    boolean isEmpty( );                                           

    // Returns the number of entries in the dictionary.
    int size( );                                                  

    // Returns the value in the dictionary whose key is the specified key;
    // or null if no such entry exists.                                
    V find( K key );                                      

    // Inserts the entry (key, value) in the dictionary.
    // If the dictionary already contained an entry with the specified key,
    // returns the old value (which is replaced by the specified value);
    // otherwise, returns null.
    V insert( K key, V value );

    // Removes the entry whose key is the specified key from the dictionary
    // and returns the associated value, if such entry exists.
    // Otherwise, returns null.
    V remove( K key );                                

    // Returns an iterator of the entries in the dictionary.
    Iterator<Entry<K,V>> iterator( );  

} 



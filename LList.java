/*****************************************************
 * class LList
 * Implements a linked list of DLLNode<T>s, each containing T data
 * new in v2: add-at-index, remove
 *****************************************************/

public class LList<T> implements List<T> { //your List interface must be in same dir

    //instance vars
    private DLLNode<T> _head;
    private DLLNode<T> _tail;
    private int _size;

    // constructor -- initializes instance vars
    public LList( ) {
	_head = null; //at birth, a list has no elements
	_tail = null;
	_size = 0;
    }


    //--------------v  List interface methods  v--------------
    public boolean add( T newVal ) {
	if(_size == 0) {
	    _tail = new DLLNode<T>( newVal, null, null);
	    _head = _tail;
	    _size++;
	    return true;
	}
	link(_tail, new DLLNode<T>( newVal, _tail, null ));
	_tail = _tail.getNext();
	_size++;
	return true;
    } 


    public T get( int index ) { 

	if ( index < 0 || index >= size() )
	    throw new IndexOutOfBoundsException();

	T retVal;
	DLLNode<T> tmp = _head; //create alias to head

	//walk to desired node
	for( int i=0; i < index; i++ )
	    tmp = tmp.getNext();

	//check target node's cargo hold
	retVal = tmp.getCargo();
	return retVal;
    } 


    public T set( int index, T newVal ) { 

	if ( index < 0 || index >= size() )
	    throw new IndexOutOfBoundsException();

	DLLNode<T> tmp = _head; //create alias to head

	//walk to desired node
	for( int i=0; i < index; i++ )
	    tmp = tmp.getNext();

	//store target node's cargo
	T oldVal = tmp.getCargo();
	
	//modify target node's cargo
	tmp.setCargo( newVal );
	
	return oldVal;
    } 


    //return number of nodes in list
    public int size() { return _size; } 

    //--------------^  List interface methods  ^--------------


    //insert a node containing newVal at position index
    public void add( int index, T newVal ) {

	if ( index < 0 || index >= size() )
	    throw new IndexOutOfBoundsException();
	if( index == 0 ) {
	    link(new DLLNode<T>(newVal,null,_head), _head);
	    _head = _head.getPrevious();
	    _size++;
	    return;
	}
	else {
	    DLLNode<T> tmp = _head; //create alias to head

	    //walk to one before the node
	    for( int i=1; i < index; i++ )
		tmp = tmp.getNext();

	    //create node for insertion
	    DLLNode<T> newNode = new DLLNode<T>(newVal, tmp, tmp.getNext());

	    // establish linkage
	    link(newNode.getPrevious(), newNode);
	    link(newNode, newNode.getNext());

	    //increment size attribute
	    _size++;
	}
    }


    //remove node at pos index, return its cargo
    public T remove( int index ) {

	if ( index < 0 || index >= size() )
	    throw new IndexOutOfBoundsException();

	T retVal;
	DLLNode<T> tmp = _head; //create alias to head

	//if index==0, remove head node
	if ( index == 0 ) {
	    //check target node's cargo hold
	    retVal = _head.getCargo();

	    //remove target node
	    _head = _head.getNext();	    
	    _head.setPrevious(null);
	}
	else {
	    //walk to node
	    for( int i=0; i < index; i++ )
		tmp = tmp.getNext();

	    //check target node's cargo hold
	    retVal = tmp.getCargo();
	    
	    //disconnect target node
	    link(tmp.getPrevious(), tmp.getNext());
	}

	//decrement size attribute
	_size--;

	return retVal;
    }

    // helper method to link two DLLNode<T>s
    public boolean link(DLLNode<T> left, DLLNode<T> right) {
	if (left != null)
	    left.setNext(right);
	if (right != null)
	    right.setPrevious(left);
	return true;
    }

    // override inherited toString
    public String toString() { 
	String retStr = "HEAD->";
	DLLNode<T> tmp = _head; //init tr
	while( tmp != null ) {
	    retStr += tmp.getCargo() + "->";
	    tmp = tmp.getNext();
	}
	retStr += "NULL";
	return retStr;
    }


    //main method for testing
    public static void main( String[] args ) {

	LList<String> james = new LList<String>();

	System.out.println( james );
	System.out.println( "size: " + james.size() );

	james.add("beat");
	System.out.println( james );
	System.out.println( "size: " + james.size() );

	james.add("a");
	System.out.println( james );
	System.out.println( "size: " + james.size() );

	james.add("need");
	System.out.println( james );
	System.out.println( "size: " + james.size() );

	james.add("I");
	System.out.println( james );
	System.out.println( "size: " + james.size() );

	System.out.println( "2nd item is: " + james.get(1) );

	System.out.println( "...and now 2nd item is: " + james.set(1,"got") );
	System.out.println( james );

	james.add(0,"whut");
	System.out.println( "...after add(0,whut): " );
	System.out.println( james );

	james.add(4,"phat");
	System.out.println( "...after add(4,phat): " );
	System.out.println( james );
	//System.out.println( james.size() );

	System.out.println( "...after remove last: " 
			    + james.remove( james.size()-1) );
	System.out.println( james );

	System.out.println( "...after remove(0): " + james.remove(0) );
	System.out.println( james );

	System.out.println( "...after remove(0): " + james.remove(0) );
	System.out.println( james );

	System.out.println( "...after remove(0): " + james.remove(0) );
	System.out.println( james );
    }//end main

}//end class LList




/*****************************************************
 * class DLLNode
 * Implements a DOUBLE node, for use in lists and other container classes.
 * Stores its data as a String
 *****************************************************/

public class DLLNode <T> {

    private T _cargo;    //cargo may only be of type T
    private DLLNode _nextNode; //pointer to next DLLNode
    private DLLNode _previousNode; //pointer to previous DLLNode


    // constructor -- initializes instance vars
    public DLLNode() {
	_cargo = null;
	_nextNode = null;
	_previousNode = null;
    }

    public DLLNode( T value,  DLLNode previous, DLLNode next) {
	_cargo = value;
	_nextNode = next;
	_previousNode = previous;
    }


    //--------------v  ACCESSORS  v--------------
    public T getCargo() { return _cargo; }

    public DLLNode getNext() { return _nextNode; }

    public DLLNode getPrevious() { return _previousNode; }
    //--------------^  ACCESSORS  ^--------------


    //--------------v  MUTATORS  v--------------
    public T setCargo( T newCargo ) {
	T foo = getCargo();
	_cargo = newCargo;
	return foo;
    }

    public DLLNode setNext( DLLNode newNext ) {
	DLLNode foo = getNext();
	_nextNode = newNext;
	return foo;
    }

    public DLLNode setPrevious( DLLNode newPrevious ) {
	DLLNode foo = getPrevious();
	_previousNode = newPrevious;
	return foo;
    }
    //--------------^  MUTATORS  ^--------------


    // override inherited toString
    public String toString() { return _cargo.toString(); }


    //main method for testing
    public static void main( String[] args ) {

	//Below is an exercise in creating a linked list...

	//Create a node
	DLLNode<String> first = new DLLNode();
	first.setCargo("cat");
	System.out.println(first);

	//Create a new node after the first
	first.setNext( new DLLNode<Integer>( 12, first, null ) );

	//Create a third node after the second (loops are fun)
	//first.getNext().setNext( new DLLNode( "cow", first.getNext(), first ) );
	first.getNext().setNext( new DLLNode<Double>( 3.14, first.getNext(), null ) );
	DLLNode temp = first; //create ptr to first so JGC doesn't take it
	while( temp != null ) {
	    System.out.println( temp );
	    temp = temp.getNext();
	}

    }//end main

}//end class DLLNode

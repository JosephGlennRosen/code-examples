
public class Node<Item> {
		private Item element;
	    private Node<Item> next;
	    private Node<Item> prev;
	 
	    /*  Constructor  */
	    public Node() {
	        next = null;
	        prev =  null;
	        element = null;
	    }    
	   
	    /* sets next to n  */
	    public void setNext(Node<Item> n) {
	        next = n;
	    }    
	    
	    /*  sets element to elem*/
	    public void setElement(Item elem) {
	        element = elem;
	    }    
	    
	    /* sets prev to p */
	    public void setPrev(Node<Item> p) {
	        prev = p;
	    }    
	    
	    /* returns prev */
	    public Node<Item> getPrev() {
	    	return prev;
	    }
	    
	    /* returns next */
	    public Node<Item> getNext()
	    {
	        return next;
	    }    
	    
	    /* returns element */
	    public Item getElement() {
	        return element;
	    }
}

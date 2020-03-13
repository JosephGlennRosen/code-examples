
public class DoubleNode<T> {
	
	private DoubleNode<T> next;
	private DoubleNode<T> prev;
	private T element;
	
	/*  Constructor  */
	public DoubleNode() {
		next = null;
		prev =  null;
		element = null;
	}    
	
	/*  Constructor  */
	public DoubleNode(T elem) {
		next = null;
		prev =  null;
		element = elem;
	}    

	/* sets next to n  */
	public void setNext(DoubleNode<T> node) {
		next = node;
	}    

	/* returns next */
	public DoubleNode<T> getNext()
	{
		return next;
	}    
	
	/*  sets element to elem*/
	public void setElement(T elem) {
		element = elem;
	}    
	
	/* returns element */
	public T getElement() {
		return element;
	}

	/* sets prev to p */
	public void setPrev(DoubleNode<T> node) {
		prev = node;
	}    

	/* returns prev */
	public DoubleNode<T> getPrev() {
		return prev;
	}
}

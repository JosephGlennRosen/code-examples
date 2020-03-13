/**
 * This program creates a doubly linked list without the functionality to add elements.
 * 
 * @author Joseph Rosen
 * @version (1.0)
 */

public class DoubleList<T> implements ListADT<T> {

	protected int count;
	protected DoubleNode<T> first;
	protected DoubleNode<T> last;

	DoubleList() {
		first = last = null;
		count = 0;
	}

	/**  
	 * Removes and returns the first element from this list. 
	 * 
	 * @return the first element from this list
	 */
	@Override
	public T removeFirst() throws EmptyCollectionException{
		if (isEmpty()) {
			throw new EmptyCollectionException("OrderedList");
		}
		DoubleNode<T> tmpNode = first.getNext();
		if(tmpNode != null) tmpNode.setPrev(null);
		if(tmpNode == null) last = null;
		T returnValue = first.getElement();
		first = tmpNode;
		count--;
		return returnValue;
	}

	/**  
	 * Removes and returns the last element from this list. 
	 *
	 * @return the last element from this list
	 */
	@Override
	public T removeLast() throws EmptyCollectionException{
		
		if (isEmpty()) {
			throw new EmptyCollectionException("OrderedList");
		}
		
		DoubleNode<T> tmpNode = last.getPrev();
		if(tmpNode != null) tmpNode.setNext(null);
		if(tmpNode == null) first = null;
		T returnValue = last.getElement();
		last = tmpNode;
		count--;
		return returnValue;
	}

	/**  
	 * Removes and returns the specified element from this list. 
	 *
	 * @param element the element to be removed from the list
	 */
	@Override
	public T remove(T element) throws EmptyCollectionException, 
	ElementNotFoundException {

		if(isEmpty())
			throw new EmptyCollectionException("LinkedList");

		boolean found = false;
		DoubleNode<T> previous = null;
		DoubleNode<T> current = first;

		while (current != null && !found) {
			
			if (element.equals(current.getElement())) 
				found = true;
			
			else {
				previous = current;
				current = current.getNext();
			}
		}
		
		if(!found) 
			throw new ElementNotFoundException("LinkedList");

		if(size() == 1)
			first = last = null;
		
		else if(current.equals(first))
			first = current.getNext();
		
		else if(current.equals(last)) {
			last = previous;
			last.setNext(null);
		}
		
		else
			previous.setNext(current.getNext());

		count--;

		return current.getElement();
	}

	/**  
	 * Returns a reference to the first element in this list. 
	 *
	 * @return a reference to the first element in this list
	 */
	@Override
	public T first() throws EmptyCollectionException{
		
		if (isEmpty()) {
			throw new EmptyCollectionException("OrderedList");
		}
		
		return first.getElement();
	}

	/**  
	 * Returns a reference to the last element in this list. 
	 *
	 * @return a reference to the last element in this list
	 */
	@Override
	public T last() throws EmptyCollectionException{
		
		if (isEmpty()) {
			throw new EmptyCollectionException("OrderedList");
		}
		return last.getElement();
	}

	/**  
	 * Returns true if this list contains the specified target element. 
	 *
	 * @param target the target that is being sought in the list
	 * @return true if the list contains this element
	 */
	@Override
	public boolean contains(T target) {
		
		DoubleNode<T> current = first;

		while (current != null) {
			if (target.equals(current.getElement())) 
				return true;
			else 
				current = current.getNext();
		}
		
		return false;
	}

	/**  
	 * Returns true if this list contains no elements. 
	 *
	 * @return true if this list contains no elements
	 */
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	/**  
	 * Returns the number of elements in this list. 
	 *
	 * @return the integer representation of number of elements in this list
	 */
	@Override
	public int size() {
		return count;
	}

	/**  
	 * Returns a string representation of this list. 
	 *
	 * @return a string representation of this list
	 */
	@Override
	public String toString() {
		DoubleNode<T> current = first;
		String list = "";

		while(current != null) {
			list += current.getElement() + " ";
			current = current.getNext();
		}
		return list;
	}

}

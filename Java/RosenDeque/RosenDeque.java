/**
 * This program provides an implementation of the Deque interface
 * and demonstrates it.
 * 
 * @author (Joseph Rosen), Acuna
 * @version (1.1)
 */
import java.util.NoSuchElementException;
import java.util.Scanner;

//TODO: implement.
public class RosenDeque<Item> implements Deque<Item> {

	private Node<Item> front;
	private Node<Item> rear;
	int count;

	//Constructor 
	RosenDeque() {
		front = rear = null;
		count = 0;
	}

	/**  
	 * Adds one element to the front of this deque. 
	 * @param element the element to be added to the front of the deque  
	 */
	@Override
	public void enqueueFront(Item element) {

		Node<Item> newNode = new Node<Item>();
		newNode.setElement(element);
		newNode.setNext(front);

		if(front!= null) front.setPrev(newNode);
		if(front == null) rear = newNode;
		front = newNode;
		count++;
	}

	/**  
	 * Adds one element to the back of this deque. 
	 * @param element the element to be added to the back of the deque  
	 */
	@Override
	public void enqueueBack(Item element) {
		Node<Item> newNode = new Node<Item>();
		newNode.setElement(element);
		newNode.setPrev(rear);

		if(rear!= null) rear.setNext(newNode);
		if(rear == null) front = newNode;
		rear = newNode;
		count++;
	}

	/**  
	 * Removes and returns the element at the front of this deque.
	 * Throws an exception if the deque is empty.
	 * @return the element at the front of this deque
	 * @throws NoSuchElementException if the deque is empty
	 */
	@Override
	public Item dequeueFront() throws NoSuchElementException {

		if(isEmpty()) {
			throw new NoSuchElementException("Deque");
		}

		Node<Item> tmpNode = front.getNext();
		if(tmpNode != null) tmpNode.setPrev(null);
		if(tmpNode == null) rear = null;
		Item returnValue = front.getElement();
		front = tmpNode;
		count--;
		return returnValue;
	}

	/**  
	 * Removes and returns the element at the back of this deque.
	 * Throw an exception if the deque is empty.
	 * @return the element at the back of the deque. 
	 * @throws NoSuchElementException if the deque is empty
	 */
	@Override
	public Item dequeueBack() throws NoSuchElementException {
		if(isEmpty()) {
			throw new NoSuchElementException("Deque");
		}

		Node<Item> tmpNode = rear.getPrev();
		if(tmpNode != null) tmpNode.setNext(null);
		if(tmpNode == null) front = null;
		Item returnValue = rear.getElement();
		rear = tmpNode;
		count--;
		return returnValue;
	}

	/**  
	 * Returns, without removing, the element at the front of this deque.
	 * Should throw an exception if the deque is empty.
	 * @return the first element in the deque
	 * @throws NoSuchElementException if the deque is empty
	 */
	@Override
	public Item first() throws NoSuchElementException {
		if(isEmpty()) {
			throw new NoSuchElementException("Deque");
		}

		return front.getElement();
	}

	/**  
	 * Returns, without removing, the element at the back of this deque.
	 * Should throw an exception if the deque is empty.
	 * @return the last element in the deque
	 * @throws NoSuchElementException if the deque is empty
	 */
	@Override
	public Item last() throws NoSuchElementException {
		if(isEmpty()) {
			throw new NoSuchElementException("Deque");
		}

		return rear.getElement();
	}

	/**  
	 * Returns true if this deque is empty and false otherwise.
	 * @return if deque empty
	 */
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	/**  
	 * Returns the number of elements in this deque. 
	 * @return the number of elements in the deque
	 */
	@Override
	public int size() {
		return count;
	}

	/**  
	 * Returns a string representation of this deque. The back element
	 * occurs first, and each element is separated by a space. If the
	 * deque is empty, returns "empty".
	 * @return the string representation of the deque
	 */
	@Override
	public String toString() {
		if(isEmpty()){
			return "empty";
		}

		Node<Item> current = rear;
		String dequeContent = "";

		while(current != null) {
			dequeContent += current.getElement() + " ";
			current = current.getPrev();
		}
		return dequeContent;
	}


	/**
	 * Program entry point for deque. 
	 * @param args command line arguments
	 */    
	public static void main(String[] args) {

		RosenDeque<Integer> deque = new RosenDeque<Integer>();
		int choice = 0;
		final int SENTINEL = 10;

		while (choice != SENTINEL) {

			choice = display();
			
			if (choice != SENTINEL) {
				dequeDriver(deque, choice);
			}
		}
	}

	public static int display() {
		Scanner scan = new Scanner(System.in);
		int choice;

		System.out.println("1) enqueueFront");
		System.out.println("2) enqueueBack");
		System.out.println("3) dequeueFront");
		System.out.println("4) dequeueBack");
		System.out.println("5) first");
		System.out.println("6) last");
		System.out.println("7) isEmpty");
		System.out.println("8) size");
		System.out.println("9) toString");
		System.out.println("10) quit");
		System.out.println();
		choice = scan.nextInt();

		while(choice < 1 || choice > 10) {
			System.out.println("Invalid input! Choose a number between 1-9.");
			choice = scan.nextInt();
		}
	
		return choice;
	}
	
	public static void dequeDriver(RosenDeque<Integer> d, int choice) {
		
		Scanner scan = new Scanner(System.in);
		System.out.println();
		switch (choice)
		{
		case 1 : 
			System.out.println("Enter an integer to insert at the front");
			d.enqueueFront( scan.nextInt() );                     
			break;                          
		case 2 : 
			System.out.println("Enter an integer to insert at the back");
			d.enqueueBack( scan.nextInt() );                     
			break;                         
		case 3 : 
			System.out.println("The value at the front of the list was removed");
			d.dequeueFront();
			break;                                          
		case 4 : 
			System.out.println("The value at the back of the list was removed");
			d.dequeueFront();
			break;     
		case 5 : 
			System.out.println("The value at the front of the list: " + d.first());
			break;            
		case 6 : 
			System.out.println("The value at the back of the list: " + d.last());
			break;                         
		case 7 :
			System.out.println("Is the list empty? " + d.isEmpty());
			break;
		case 8 :
			System.out.println("The size of the list: " + d.size());
			break;
		case 9 :
			System.out.println("The content of the list: " + d.toString());
			break;
		default : 
			System.out.println("Wrong Entry \n ");
			break;   
		}    
		System.out.println();
	}
} 
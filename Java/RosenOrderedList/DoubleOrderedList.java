/**
 * This program extends DoubleList and adds the functionality to add elements to the list.
 * With each addition the list maintains the elements in a ascending order.
 * 
 * @author Joseph Rosen
 * @version (1.0)
 */
public class DoubleOrderedList<T extends Comparable<T>> extends DoubleList<T> implements OrderedListADT<T>{


	/**  
	 *Places element into the proper place in the list to maintain
	 *the natural order of the list.
	 *
	 * @param element is the element that is placed in the proper
	 * place in the ordered list.
	 */
	@Override
	public void add(T element) {

		if (size() == 0)
		{
			first = new DoubleNode<T>(element);
			last = first;
			count++;
			return;
		}
		else
		{
			if (element.compareTo(first.getElement()) < 0)
			{
				DoubleNode<T> newNode = new DoubleNode<T>(element);
				newNode.setNext(first);
				first.setPrev(newNode);
				first = newNode;
				count++;
				return;
			}
			else
			{
				DoubleNode<T> current = first.getNext();
				while (current != null)
				{
					if (element.compareTo(current.getElement()) <= 0)
					{
						DoubleNode<T> newNode = new DoubleNode<T>(element);
						newNode.setNext(current);
						newNode.setPrev(current.getPrev());
						current.getPrev().setNext(newNode);
						current.setPrev(newNode);
						count++;
						return;
					}
					current = current.getNext();
				}

				DoubleNode<T> newNode = new DoubleNode<T>(element);
				newNode.setPrev(last);
				last.setNext(newNode);
				last = newNode;
				count++;
				return;
			}
		}
	}
}
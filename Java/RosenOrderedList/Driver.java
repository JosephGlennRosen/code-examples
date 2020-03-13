/**
 * This program demonstrates the functionality of the other clases.
 * 
 * @author Joseph Rosen
 * @version (1.0)
 */

import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		
		DoubleOrderedList<Integer> list = new DoubleOrderedList<Integer>();
		int choice = 0;
		final int SENTINEL = 11;

		while (choice != SENTINEL) {

			choice = display();
			
			if (choice != SENTINEL) {
				listDriver(list, choice);
			}
		}
	}

	public static int display() {
		Scanner scan = new Scanner(System.in);
		int choice;

		System.out.println("1) Add");
		System.out.println("2) RemoveFirst");
		System.out.println("3) RemoveLast");
		System.out.println("4) Remove");
		System.out.println("5) First");
		System.out.println("6) Last");
		System.out.println("7) Contains");
		System.out.println("8) IsEmpty");
		System.out.println("9) Size");
		System.out.println("10) ToString");
		System.out.println("11) Quit");
		System.out.println();
		choice = scan.nextInt();

		while(choice < 1 || choice > 11) {
			System.out.println("Invalid input! Choose a number between 1-9.");
			choice = scan.nextInt();
		}

		return choice;
	}

	public static void listDriver(DoubleOrderedList<Integer> l, int choice) {

		Scanner scan = new Scanner(System.in);
		System.out.println();
		switch (choice)
		{
		case 1 : 
			System.out.println("Enter an integer to add to the list");
			l.add( scan.nextInt() );                     
			break;                          
		case 2 : 
			System.out.println(l.removeFirst() + " Was removed from the list");                     
			break;                         
		case 3 : 
			System.out.println(l.removeLast() + " Was removed from the list");
			break;                                          
		case 4 : 
			System.out.println("The list contains: " + l.toString() + 
					" Which element would you like to remove? ");
			l.remove(scan.nextInt());
			break;     
		case 5 : 
			System.out.println("The value at the front of the list: " + l.first());
			break;            
		case 6 : 
			System.out.println("The value at the back of the list: " + l.last());
			break;
		case 7 :
			System.out.println("Enter the value to check the list ");
			System.out.println("Contains? " + l.contains(scan.nextInt()));
			break;
		case 8 :
			System.out.println("Is the list empty? " + l.isEmpty());
			break;
		case 9 :
			System.out.println("The size of the list: " + l.size());
			break;
		case 10 :
			System.out.println("The content of the list: " + l.toString());
			break;
		default : 
			System.out.println("Wrong Entry \n ");
			break;   
		}    
		System.out.println();
	}
}

//File name: simpleVector.cpp
//Author: Joseph Rosen
//Assignment number: 4, problem 2.
//Description: This Demenstrates a very simple vector template. The data values we will consider 
//are: int, double and string.

#include <iostream>
#include <string>
using namespace std;

//Start of the SimpleVector class
template <class T>
class SimpleVector
{
	public:
		SimpleVector();
		SimpleVector(int newArraySize);
		SimpleVector(SimpleVector<T>& newVector);
		~SimpleVector();
		T& operator[] (int index){return list[index];}
		int getArraySize(){return arraySize;}
		T  getElementAt(int index);
	private:
		T *list;
		int arraySize;
};
//End of the SimpleVector class


//Handles the driver program if the data type is a string
void dataTypeString();
//Handles the driver program if the data type is a double
void dataTypeDouble();
//Handles the driver program if the data type is a int
void dataTypeInt();

int main () 
{
	string pause;
	int userChoice = 0;
	char again = 'y';
	while(again == 'Y' || again == 'y')
	{
		cout << "Choose between one of the following three data types: " << endl;
		cout << "1 for an integer" << endl;
		cout << "2 for a double" << endl;
		cout << "3 for a string" << endl;
		cin >> userChoice;

		//controls which function is called based on an integer 1-3
		switch (userChoice)
		{
		case(1):
			{
				dataTypeInt();
				break;
			}
		case(2):
			{
				dataTypeDouble();
				break;
			}
			case(3):
			{
				dataTypeString();
				break;
			}
		}
		cout << "Would you like to enter more data? (Y/y) for yes, (N/n) for no." << endl;
		cin >> again;
	}
	
	return 0;
}

//Handles the driver program if the data type is a string
void dataTypeString()
{
	int numberOfEntries = 0;
	string userInput = "";
	int index;
	//gets data from the user
	cout << "Enter how many entries you are going to enter" << endl;
	cin >> numberOfEntries;
	cout << "Enter the data entries" << endl;
	//constructs the SimpleVector object
	SimpleVector<string> list(numberOfEntries);
	cin.ignore();
	//populates the vector
	for (int i = 0; i < numberOfEntries; i++)
	{
		getline(cin, userInput);
		list[i] = userInput;
	}
	//the following lines show the functionality of the SimpleVector class
	cout << "Enter an index between 0-" << numberOfEntries-1 << " to display" << endl;
	cin >> index;
	cout << "Displaying using the getElementAt function.\nthe value at index " << index <<
		": " << list.getElementAt(index) << endl;
	cout << "Enter an index between 0-" << numberOfEntries-1 << " to display" << endl;
	cin >> index;
	cout << "Displaying using the overloaded [] operator.\nthe value at index " << index <<
		": " << list[index] << endl;
	cout << "Now creating a new list based on the old list of values" << endl;
	SimpleVector<string> newList(list);
	cout << "The values contained in the new list: " << endl;
	for (int i = 0; i < numberOfEntries; i++)
	{
		cout << "Index " << i << " " << newList[i] << endl;
	}
}

void dataTypeDouble() 
{
	int numberOfEntries = 0;
	double userInput = 0.0;
	int index;
	//gets data from the user
	cout << "Enter how many entries you are going to enter" << endl;
	cin >> numberOfEntries;
	cout << "Enter the data entries" << endl;
	//constructs the SimpleVector object
	SimpleVector<double> list(numberOfEntries);
	//populates the vector
	for (int i = 0; i < numberOfEntries; i++)
	{
		cin >> userInput;
		list[i] = userInput;
	}
	//the following lines show the functionality of the SimpleVector class
	cout << "Enter an index between 0-" << numberOfEntries-1 << " to display" << endl;
	cin >> index;
	cout << "Displaying using the getElementAt function.\nthe value at index " << index <<
		": " << list.getElementAt(index) << endl;
	cout << "Enter an index between 0-" << numberOfEntries-1 << " to display" << endl;
	cin >> index;
	cout << "Displaying using the overloaded [] operator.\nthe value at index " << index <<
		": " << list[index] << endl;
	cout << "Now creating a new list based on the old list of values" << endl;
	SimpleVector<double> newList(list);
	cout << "The values contained in the new list: " << endl;
	for (int i = 0; i < numberOfEntries; i++)
	{
		cout << "Index " << i << " " << newList[i] << endl;
	}
}

void dataTypeInt()
{
	int numberOfEntries = 0;
	int userInput = 0.0;
	int index;
	//gets data from the user
	cout << "Enter how many entries you are going to enter" << endl;
	cin >> numberOfEntries;
	cout << "Enter the data entries" << endl;
	//constructs the SimpleVector object
	SimpleVector<int> list(numberOfEntries);
	//populates the vector
	for (int i = 0; i < numberOfEntries; i++)
	{
		cin >> userInput;
		list[i] = userInput;
	}
	//the following lines show the functionality of the SimpleVector class
	cout << "Enter an index between 0-" << numberOfEntries-1 << " to display" << endl;
	cin >> index;
	cout << "Displaying using the getElementAt function.\nthe value at index " << index <<
		": " << list.getElementAt(index) << endl;
	cout << "Enter an index between 0-" << numberOfEntries-1 << " to display" << endl;
	cin >> index;
	cout << "Displaying using the overloaded [] operator.\nthe value at index " << index <<
		": " << list[index] << endl;
	cout << "Now creating a new list based on the old list of values" << endl;
	SimpleVector<int> newList(list);
	cout << "The values contained in the new list: " << endl;
	for (int i = 0; i < numberOfEntries; i++)
	{
		cout << "Index " << i << " " << newList[i] << endl;
	}
}

//Default constructor
template <class T>
SimpleVector<T>::SimpleVector()
{
	arraySize = 0;
	list = NULL;
}

//Constructor that sets the list to the specified size
template <class T>
SimpleVector<T>::SimpleVector(int newArraySize)
{
	arraySize = newArraySize;
	list = new T[newArraySize];
}

//Copy constructor
template <class T>
SimpleVector<T>::SimpleVector(SimpleVector<T>& newVector)
{
	arraySize = newVector.arraySize;
	list = new T[arraySize];
	for (int i = 0; i < newVector.arraySize; i ++)
	{
		list[i] = newVector[i];
	}
}

//Destructor
template <class T>
SimpleVector<T>::~SimpleVector()
{
	delete [] list;
}

//Returns the element at the specified index
template <class T>
T SimpleVector<T>::getElementAt(int index)

{
	return list[index];
}
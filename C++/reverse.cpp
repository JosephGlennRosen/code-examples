//File name: reverse.cpp
//Author: Joseph Rosen
//Assignment number: 3, problem 2.
//Description: This program reverses a string by copying the string into a char array. 
//Using pointers to swap the chars at varying indices in so doing reversing the string.
#include <iostream>
#include <string>
using namespace std;

//reverse creates a char array matching the length of the string variable "line" 
//and copies the chars contained in line, into the newly created char array.
//the char array is then reversed using pointers to swap chars at varying indices
//in so doing reversing the string. Finally reverse prints out the char array 
//with the newly ordered chars.
void reverse(string line);

int main () 
{
	string line;
	char consolePause;
	// gets the user input and stores it in line
	cout << "Enter a string to be reversed: ";
	getline(cin,line);
	cout <<"\nThe reverse of " << line << " is: ";
	//calls reverse on the inputted string
	reverse(line);
	cout << "\n\nPress any key to continue ";
	cin >> consolePause;
	return 0;
}

void reverse(string line)
{
	//creates a char array named data matching the size 
	//of line and copies the chars contained in line into data
	char * data = new char[line.size() + 1];
	copy(line.begin(), line.end(), data);
	data[line.size()] = '\0'; 
	
	//Creates pointers to reverse the chars contained in the data array
	int nChars = line.size();
	char *head = data;
	char *tail = data + nChars -1;
	while (head < tail)
	{
		char save = *head;
		*head = *tail;
		*tail = save;
		head++;
		tail--;
	}
	//Prints the newly reversed char array
	cout << data;
}
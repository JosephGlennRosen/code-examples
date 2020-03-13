//File name: date.cpp
//Author: Joseph Rosen
//Assignment number: 5, problem 1.
//Description: Program models a date class and demonstrates the creation of exception classes and use.

#include <iostream>
#include <string>
using namespace std;
const int NUMBER_OF_MONTHS = 12;

//Exception class thrown when an invalid day (<1 or > 31) is passed to the class
class InvalidDay 
{};

//Exception class thrown when an invalid month (<1 or > 12) is passed to the class
class InvalidMonth
{};

//Start of Date class
class Date
{
	public:
		Date();
		Date(int newDay, int newMonth, int newYear) throw(InvalidDay, InvalidMonth);
		void setDay(int newDay) throw(InvalidDay);
		void setMonth(int newMonth) throw(InvalidMonth);
		void setYear(int newYear);
		void populateMonthNames();
		int getDay();
		int getMonth();
		int getYear();
		void printBasic();
		void printStringFirst();
		void printStringSecond();

	private:
		int day;
		int month;
		int year;
		string monthNames[NUMBER_OF_MONTHS];
};
//End of Date class

int main ()
{
	//Variable declarations to their arbitrary values
	string pause = "";
	int day = 0 , month = 0, year = 0;
	Date date(1,1,2000);

	//Tests the InvalidDay exception
	try 
	{
		cout << "Enter the day of the month" << endl;
		cin >> day;
		date.setDay(day);
	}
	
	catch(InvalidDay)
	{
		cout << "Invalid day of the month! Enter a day between 1-31" << endl;
		cin >> day;
		date.setDay(day);
	}

	//Tests the InvalidMonth exception
	try 
	{
		cout << "Enter the month" << endl;
		cin >> month;
		date.setMonth(month);
	}

	catch(InvalidMonth)
	{
		cout << "Invalid month! Enter a month between 1-12" << endl;
		cin >> month;
		date.setMonth(month);
	}
	
	cout << "Enter the year" << endl;
	cin >> year;
	date.setYear(year);
	
	//Populates the monthNames array
	date.populateMonthNames();

	//Displays the date object with its varied print functions
	date.printBasic();
	date.printStringFirst();
	date.printStringSecond();
	cout << "Enter any string to end the program" << endl;
	cin.ignore();
	getline(cin, pause);
	return 0;
}

//Start of Date implementation

//Default constructor setting day, month, and year all to zero
Date::Date()
{
	day = 0;
	month = 0;
	year = 0;
}

//Constructor with arguments
Date::Date(int newDay, int newMonth, int newYear) throw(InvalidDay, InvalidMonth)
{
	if (newDay < 1 || newDay > 31)
	{
		throw InvalidDay();
	}
	if (newMonth < 1 || newMonth > 12)
	{
		throw InvalidMonth();
	}
	day = newDay;
	month = newMonth;
	year = newYear;
}

//Returns day
int Date::getDay()
{
	return day;
}

//Returns month
int Date::getMonth()
{
	return month;
}

//Returns year
int Date::getYear()
{
	return year;
}

//Sets day to newDay
void Date::setDay(int newDay) throw(InvalidDay)
{
	if (newDay < 1 || newDay > 31)
	{
		throw InvalidDay();
	}

	day = newDay;
}

//Sets month to newMonth
void Date::setMonth(int newMonth) throw(InvalidMonth)
{
	if (newMonth < 1 || newMonth > 12)
	{
		throw InvalidMonth();
	}

	month = newMonth;
}

//Sets year to newYear
void Date::setYear(int newYear)
{
	year = newYear;
}

//Populates monthNames with the name of the months for displaying in the print functions
void Date::populateMonthNames()
{
	monthNames[0] = "January";
	monthNames[1] = "February";
	monthNames[2] = "March";
	monthNames[3] = "April";
	monthNames[4] = "May";
	monthNames[5] = "June";
	monthNames[6] = "July";
	monthNames[7] = "August";
	monthNames[8] = "September";
	monthNames[9] = "October";
	monthNames[10] = "November";
	monthNames[11] = "December";
}

//Displays the member variables of the Date object.
void Date::printBasic()
{
	//12/25/13
	cout << month << "\/" << day << "\/" << year << endl;
}

//Displays the member variables of the Date object. The month variable is printed out first as the name of the 
//month instead of its integer representaion
void Date::printStringFirst()
{
	cout << monthNames[month - 1] << " " << day << ", " << year << endl;
}

//Displays the member variables of the Date object. The month variable is printed out second as the name of the 
//month instead of its integer representaion
void Date::printStringSecond()
{
	//25 December 2013
	cout << day << " " <<  monthNames[month - 1]<< " " << year << endl;
}
//End of Date implementation
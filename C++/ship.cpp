//File name: ship.cpp
//Author: Joseph Rosen
//Assignment number: 3, problem 1.
//Description: This program creates a base class named Ship and 
//two(CruiseShip, CargoShip) classes derived from Ship. The program demonstrates polymorphism
//and inheritance.

#include<iostream>
#include<string>
using namespace std;

//Start of Ship class
class Ship 
{
	public:
		Ship();
		Ship(string newName, int newYear);
		void setName(string newName);
		void setYear(int newYear);
		string getName();
		int getYear();
		void print();
    private:
		string name;
		int year;
};
//End of Ship class

//Start of CruiseShip class
class CruiseShip : public Ship
{
	public:
		CruiseShip();
		CruiseShip(string newName, int newYear,int newMaxNumberOfPassengers);
		void print();
		void setMaxNumberOfPassengers(int newMaxNumberOfPassengers);
		int getMaxNumberOfPassengers();

	private:
		int maxNumberOfPassengers;
};
//End of CruiseShip class

//Start of the CargoShip class
class CargoShip : public Ship
{
	public:
		CargoShip();
		CargoShip(string newName, int newYear, int newCargoCapcity);
		void setCargoCapacity(int newCargoCapacity);
		int getCargoCapacity();
		void print();

	private:
		int cargoCapacity;
};
//End of the CargoShip class


int main () 
{
	string pause;
	string shipName, cargoShipName, cruiseShipName;
	int shipYearBuilt, cargoShipYearBuilt, cruiseShipYearBuilt;
	int cargoCapacity;
	int maxNumberOfPassengers;
	Ship mutatorShip;
	CruiseShip mutatedCruiseShip;
	CargoShip mutatedCargoShip;

	//Gets input for the user and stores the entered values
	cout << "Enter the name of the ship:" << endl;
	getline(cin, shipName);

	cout << "Enter the year the ship was built" << endl;
	cin >> shipYearBuilt;

	cout << "Enter the name of the cruise ship:" << endl;
	cin.ignore();
	getline(cin, cruiseShipName);

	cout << "Enter the year the cruise ship was built:" << endl;
	cin >> cruiseShipYearBuilt;

	cout << "Enter the capacity of the cruise ship (in persons):" << endl;
	cin >> maxNumberOfPassengers;

	cout << "Enter the name of the cargo ship:" << endl;
	cin.ignore();
	getline(cin, cargoShipName);

	cout << "Enter the year the cargo ship was built:" << endl;
	cin >> cargoShipYearBuilt;

	cout << "Enter the capacity of the cargo ship (in tonnage):" << endl;
	cin >> cargoCapacity;

	//Constructs objects using their constructors
	Ship constructedShip(shipName, shipYearBuilt);
	CruiseShip constuctedCruiseShip(cruiseShipName, cruiseShipYearBuilt, maxNumberOfPassengers);
	CargoShip construcedCargoShip(cargoShipName, cargoShipYearBuilt, cargoCapacity);

	//prints the objects created using constructor and arguments
	cout << "\nNow printing the objects created using constructor and arguments" << endl;
	constructedShip.print();
	constuctedCruiseShip.print();
	construcedCargoShip.print();

	//Populates objects using their mutator functions
	mutatorShip.setName(shipName);
	mutatorShip.setYear(shipYearBuilt);

	mutatedCruiseShip.setName(cruiseShipName);
	mutatedCruiseShip.setYear(cruiseShipYearBuilt);
	mutatedCruiseShip.setMaxNumberOfPassengers(maxNumberOfPassengers);

	mutatedCargoShip.setName(cargoShipName);
	mutatedCargoShip.setYear(cargoShipYearBuilt);
	mutatedCargoShip.setCargoCapacity(cargoCapacity);

	//Prints the objects created using default constructors and mutators
	cout << "\nNow printing objects created using default constructors and mutators" << endl;
	mutatorShip.print();
	mutatedCruiseShip.print();
	mutatedCargoShip.print();

	cout << "\n\nEnter any key and then enter to end the program" << endl;
	cin.ignore();
	getline(cin,pause);
	return 0;
}

//Start of Programming of the Ship class

//Default construtor
Ship::Ship()
{
	name = "";
	year = 0;
}

//Populated constructor
Ship::Ship(string newName, int newYear)
{
	name = newName;
	year = newYear;
}

//Sets name to newName
void Ship::setName(string newName)
{
	name = newName;
}

//Sets year to newYear
void Ship::setYear(int newYear)
{
	year = newYear;
}

//Returns name
string Ship::getName()
{
	return name;
}

//Returns year
int Ship::getYear()
{
	return year;
}

//Prints the values of the ship object
void Ship::print()
{
	cout << "The name of the ship: " << name << endl;
	cout << "The year the ship was built: " << year << endl;
}
//End of Programming of the Ship class

//Start of programming of the CruiseShip class

//Default constructor
CruiseShip::CruiseShip() : Ship(), maxNumberOfPassengers(0)
{
	// Intentionally left blank
}

//Populated constructor
CruiseShip::CruiseShip(string newName, int newYear,int newMaxNumberOfPassengers)
	: Ship(newName, newYear), maxNumberOfPassengers(newMaxNumberOfPassengers)
{
	// Intentionally left blank
}

//Sets maxNumberOfPassengers to newMaxNumberOfPassengers
void CruiseShip::setMaxNumberOfPassengers(int newMaxNumberOfPassengers)
{
	maxNumberOfPassengers = newMaxNumberOfPassengers;
}

//Returns maxNumberOfPassengers
int CruiseShip::getMaxNumberOfPassengers()
{
	return maxNumberOfPassengers;
}

//Prints the name and max number of passengers of the CruiseShip 
void CruiseShip::print()
{
	cout << "The name of the cruise ship: " << getName() << endl;
	cout << "The maximum passengers of this cruise ship is: " << maxNumberOfPassengers << endl;
}
//end of programming of the CruiseShip class

//Start of programming of the CargoShip class
CargoShip::CargoShip() : Ship(), cargoCapacity(0)
{
	//Intentionally blank
}

//Populated constructor
CargoShip::CargoShip(string newName, int newYear, int newCargoCapcity)
	: Ship(newName, newYear), cargoCapacity(newCargoCapcity)
{
	//Intentionally blank
}

//Sets cargoCapacity to newCargoCapacity
void CargoShip::setCargoCapacity(int newCargoCapacity)
{
	cargoCapacity = newCargoCapacity;
}

//Returns cargoCapacity
int CargoShip::getCargoCapacity()
{
	return cargoCapacity;
}

//Prints the name and cargo capacity of this CargoShip
void CargoShip::print()
{
	cout << "The name of this cargo ship: " << getName() << endl;
	cout << "The cargo capacity of this cargo ship is: " << cargoCapacity << endl;
}
//end of programming of the CargoShip class
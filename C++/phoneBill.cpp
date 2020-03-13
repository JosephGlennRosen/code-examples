//File name: phoneBill.cpp
//Author: Joseph Rosen
//Assignment number: 4, problem 1.
//Description: This program simulates varied phone bill plans and compares the monthly
//bill based on the current data set

#include <iostream>
#include <string>
#include <cstdlib>
using namespace std;
const int HIGH = 8;
const int LOW = 2;

//Start of Costumer class
class Customer
{
	public:
		Customer();
		Customer(string newCustomerName, int newNumberOfCalls);
		~Customer();
		virtual double computeBill();
		int getNumberOfCalls();
		void setNumberOfCalls(int newNumberOfCalls);
		string getCustomerName();
		void setCustomrerName(string newCustomerName);

	private:
		string name;
		int numberOfCalls;
};

//End of customrer class

//Start of PremiumCustomer class

class PremiumCustomer : public Customer
{
	public:
		double computeBill();
		double computeBill(int minsPerCall);
		PremiumCustomer();
		PremiumCustomer(string newCustomerName, int newNumberOfCalls);
		~PremiumCustomer();
};

//End of PremiumCustomer class


//Handles the extra credit for this assignment. The funtion will create five Customer objects,
//five PremiumCustomers and compare their rates based upon calls used per month(10-100).
void extraCredit();

//This is a helper function for the extra credit question. The function compares the Customer and 
//PremiumCustomer's objects monthly bill. If the the PremiumCustomer object experiences a lesser 
//rate than the Customer object, the amount of savings is returned to the extraCredit function 
//for further computation.
double averageSavings(Customer *customer, PremiumCustomer *premiumCustomer, int minsPerCall);

int main ()
{
	string pause = "";
	Customer* list[6] ;
	list[0] = new Customer("Captain America", 50);
	list[1] = new Customer("Ironman", 60);
	list[2] = new Customer("Wolverine",70);
	list[3] = new PremiumCustomer("Hawk Eye", 50);
	list[4] = new PremiumCustomer("Juggernaut", 60);
	list[5] = new PremiumCustomer("Storm",70);

	cout << "Starting base program\n\n\n\n" << endl;
	for (int i = 0; i < 6; i++)
	{
		cout << "Customer: " << list[i]->getCustomerName() << " Owes";
		cout << " " << list[i]->computeBill() << " Dolllars" << endl;
	}
	extraCredit();
	//dealocating memory 
	for (int i = 0; i < 6; i++)
	{
		delete list[i];
	}

	cout << "\n\nEnter any string to end the program... " << endl;
	getline(cin, pause);
	return 0;
}

//Handles the extra credit for this assignment. The funtion will create five Customer objects,
//five PremiumCustomers and compare their rates based upon calls used per month(10-100).
void extraCredit()
{
	cout << "\n\n\n\nStarting extra credit simulations..." << endl;

	double premiumCustomerAverageSavings = 0.0;
	double customerSum = 0.0;
	double PremiumSum = 0.0;

	//Instantiating test data
	Customer *customerList[10];
	PremiumCustomer *premiumList[10];
	customerList[0] = new Customer("Customer 1", 10);
	customerList[1] = new Customer("Customer 2", 20);
	customerList[2] = new Customer("Customer 3", 30);
	customerList[3] = new Customer("Customer 4", 40);
	customerList[4] = new Customer("Customer 5", 50);
	customerList[5] = new Customer("Customer 6", 60);
	customerList[6] = new Customer("Customer 7", 70);
	customerList[7] = new Customer("Customer 8", 80);
	customerList[8] = new Customer("Customer 9", 90);
	customerList[9] = new Customer("Customer 10", 100);
	premiumList[0] = new PremiumCustomer("PremiumCustomer 1", 10);
	premiumList[1] = new PremiumCustomer("PremiumCustomer 2", 20);
	premiumList[2] = new PremiumCustomer("PremiumCustomer 3", 30);
	premiumList[3] = new PremiumCustomer("PremiumCustomer 4", 40);
	premiumList[4] = new PremiumCustomer("PremiumCustomer 5", 50);
	premiumList[5] = new PremiumCustomer("PremiumCustomer 6", 60);
	premiumList[6] = new PremiumCustomer("PremiumCustomer 7", 70);
	premiumList[7] = new PremiumCustomer("PremiumCustomer 8", 80);
	premiumList[8] = new PremiumCustomer("PremiumCustomer 9", 90);
	premiumList[9] = new PremiumCustomer("PremiumCustomer 10", 100);
	//Instantiation finished

	//generates the random number of minutes used by the PremiumCustomer objects
	int randomNumbers[10];
	for (int i = 0; i < 10; i++)
	{
		randomNumbers[i] = ((rand() % (HIGH-LOW+1)) + LOW);
	}

	for (int i = 0; i < 10; i++)
	{
		cout << customerList[i]->getCustomerName() << " Owes " << customerList[i]->computeBill() << endl;
		cout << premiumList[i]->getCustomerName() << " owes " << premiumList[i]->computeBill() << endl;
		premiumCustomerAverageSavings += averageSavings(customerList[i], premiumList[i], randomNumbers[i]);
		customerSum += customerList[i]->computeBill();
		PremiumSum += premiumList[i]->computeBill(randomNumbers[i]);
	}
	cout << "The total amount spent for the customer objects: " << customerSum << endl;
	cout << "The total amount spent for the premium customer objects: " << PremiumSum << endl;
	cout << "The premium customer plan provides a better deal for this data set with an average savings of: " << endl;
	cout << premiumCustomerAverageSavings << endl;
}

//This is a helper function for the extra credit question. The function compares the Customer and 
//PremiumCustomer's objects monthly bill. If the the PremiumCustomer object experiences a lesser 
//rate than the Customer object, the amount of savings is returned to the extraCredit function 
//for further computation.
double averageSavings(Customer *customer, PremiumCustomer *premiumCustomer, int minsPerCall)
{
	double averageSavings = 0.0;

	if (customer->computeBill() > premiumCustomer->computeBill(minsPerCall))
	{
		averageSavings = (customer->computeBill() - premiumCustomer->computeBill(minsPerCall));
		return averageSavings;
	}
	else 
	{
		return 0;
	}
}

//Start of Customrer class implementation

//Default constructor
Customer::Customer(): name(""), numberOfCalls(0)
{}

//Constructor with arguments
Customer::Customer(string newCustomerName, int newNumberOfCalls):
	name(newCustomerName), numberOfCalls(newNumberOfCalls)
{}

//Deconstructor
Customer::~Customer()
{}

//Returns the customer name
string Customer::getCustomerName()
{
	return name;
}
//Sets the customer name to newCustomerName
void Customer::setCustomrerName(string newCustomerName)
{
	name = newCustomerName;
}

//Returns the number of calls the customer has made
int Customer::getNumberOfCalls()
{
	return numberOfCalls;
}

//Sets numberOfCalls to newNumberOfCalls
void Customer::setNumberOfCalls(int newNumberOfCalls)
{
	numberOfCalls = newNumberOfCalls;
}

//Computes the customer's monthly bill based on how many calls they make, their monthly fee,
//and the charge per call.
double Customer::computeBill()
{
	int monthlyFee = 10;
	double bill = 0.0;
	double perCall = 0.50;
	bill = (monthlyFee + (getNumberOfCalls() * perCall));
	return bill;
}
//End of Customrer class implementation

//Start of PremiumCustomer class implementation

//Default constructor
PremiumCustomer::PremiumCustomer(): Customer("", 0)
{}

//Constructor with arguments
PremiumCustomer::PremiumCustomer(string newCustomerName, int newNumberOfCalls):
	Customer(newCustomerName, newNumberOfCalls)
{}

//Deconstructor
PremiumCustomer::~PremiumCustomer()
{}

//Computes the premium customer's monthly bill based on how many calls they make, their monthly fee,
//charge per call, as well as a per minute call rate.
double PremiumCustomer::computeBill()
{
	int monthlyFee = 20;
	double perCall = 0.05;
	double perMin = 0.10;
	double bill = 0.0;
	int numberOfMinutes = 3;
	bill = (monthlyFee + (perCall * getNumberOfCalls()) + (perMin * numberOfMinutes));
	return bill;
}

//This overloaded version of computeBill is used in the extra credit question for this assignment.
//This version is the same as the other PremiumCustomer computeBill the only difference is
//The number of minutes per call is passed by value into the function as minsPerCall.
double PremiumCustomer::computeBill(int minsPerCall)
{
	int monthlyFee = 20;
	double perCall = 0.05;
	double perMin = 0.10;
	double bill = 0.0;
	int numberOfMinutes = minsPerCall;
	bill = (monthlyFee + (perCall * getNumberOfCalls()) + (perMin * numberOfMinutes));
	return bill;
}

//End of PremiumCustomer class implementation
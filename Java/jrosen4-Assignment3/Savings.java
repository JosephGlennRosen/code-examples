package assignment3;

public class Savings extends Account{
	
	//Default constructor
	Savings()
	{
		super();
	}
	
	//Constructor with values
	Savings(int newAccountId, double newBalance, double newAnnualInerestRate)
	{
		super(newAccountId, newBalance, newAnnualInerestRate);
	}
	
	//Overrides the withdraw function to handle if the user tries to withdraw more than
	//their current balance.
	@Override
	public void withdraw(double amount)
	{
		double currentBalance = getBalance();
		
		if ((currentBalance - amount) >= 0)
		{
			setBalance(currentBalance - amount);
			System.out.println("Your balance after withdrawing " + amount + " is: " + 
			getBalance());
		}
		
		else if ((currentBalance - amount) < 0)
		{
			System.out.println("There is insufficient funds to complete your request.");
		}
	}
}

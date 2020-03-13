package assignment3;

public class Checking extends Account{
	//Default constructor
		Checking()
		{
			super();
		}
		
		//Constructor with values
		Checking(int newAccountId, double newBalance, double newAnnualInerestRate)
		{
			super(newAccountId, newBalance, newAnnualInerestRate);
		}
		
		//Overrides the withdraw function to handle if the user tries to withdraw more than
		//their overdraft limit.
		@Override
		public void withdraw(double amount)
		{
			double currentBalance = getBalance();
			
			if ((currentBalance - amount) >= -5000)
			{
				setBalance(currentBalance - amount);
				System.out.println("Your balance after withdrawing " + amount + " is: " + 
				getBalance());
			}
			
			else if ((currentBalance - amount) < -5000)
			{
				System.out.println("There is insufficient funds to complete your request.");
			}
		}
}

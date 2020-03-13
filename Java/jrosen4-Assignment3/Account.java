package assignment3;

import java.util.Date;

public class Account {
	
	private int accountId;
	private double balance;
	private double annualInterestRate;
	private Date date;
	
	Account()
	{
		accountId = 0;
		balance = 0.0;
		annualInterestRate = 0.0;
		date = new java.util.Date();
	}
	
	Account(int newAccountId, double newBalance, double newAnnualInerestRate)
	{
		//If the interest rate is a whole number this block properly converts it to a percent
		//value.
		if(newAnnualInerestRate >= 1.0)
		{
			newAnnualInerestRate *= 0.01;
		}
		accountId = newAccountId;
		balance = newBalance;
		annualInterestRate = newAnnualInerestRate;
		date = new java.util.Date();
	}
	
	//Returns date
	public Date getDate()
	{
		return date;
	}
	
	//returns accountId
	public int getAccountId()
	{
		return accountId;
	}
	
	//sets accountId to newAccountId
	public void setAccountId(int newAccountId)
	{
		accountId = newAccountId;
	}
	
	//returns balance
	public double getBalance()
	{
		return balance;
	}
	
	//sets balance to newBalance
	public void setBalance(double newBalance)
	{
		balance = newBalance;
	}
	
	//returns annualInterestRate
	public double getAnnualInterestRate()
	{
		return annualInterestRate;
	}
	
	//sets annualInterestRate to newAnnualInterestRate
	public void setAnnualInterestRate(double newAnnualInterestRate)
	{
		//If the interest rate is a whole number this block properly converts it to a percent
		//value.
		if(newAnnualInterestRate >= 1.0)
		{
			newAnnualInterestRate *= 0.01;
		}
		annualInterestRate = newAnnualInterestRate;
	}
	
	//returns monthly interest rate
	public double getMonthlyInterestRate()
	{
		double interest = 0;
		interest = annualInterestRate/12;
		
		//If the interest rate is a whole number this block properly converts it to a percent
		//value.
		if(getAnnualInterestRate() >= 1)
		{
			interest *= 0.01;
		}
		
		return balance * interest;
	}
	
	//subtracts amount from balance
	public void withdraw(double amount)
	{
		balance -= amount;
	}
	
	//adds amount to balance
	public void deposit(int amount)
	{
		balance += amount;
	}
	
	//adds amount to balance
	public void deposit(double amount)
	{
		balance += amount;
	}
	
	//Overrides the Object toString method
	@Override
	public String toString()
	{
		String balanceFormatted = String.format("%.2f",balance);
		String AnnualInterestRateFormatted = String.format("%.2f",annualInterestRate);
		return "Account ID: " + accountId + "\nBalance: " + balanceFormatted +
				"\nAnnual Interest Rate: " + AnnualInterestRateFormatted + 
				"\nDate created: " + date;
	}
	
	
	
	
	
	
	
	
	
}

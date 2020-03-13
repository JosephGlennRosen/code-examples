package assignment3;
import java.util.Scanner;

public class TestAccount {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		int savingsId = 0;
		double savingsBalance = 0.0;
		double savingsInterestRate = 0.0;
		double savingsWithdrawAmount = 0.0;
		double savingsDepositAmount = 0.0;
		
		int checkingId = 0;
		double checkingBalance = 0.0;
		double checkingInterestRate = 0.0;
		double checkingwithdrawAmount = 0.0;
		double checkingDepositAmount = 0.0;
		
		System.out.println("Enter the savings account ID > ");
		savingsId = in.nextInt();
		
		System.out.println("Enter the savings account balance > ");
		savingsBalance = in.nextDouble();
		
		System.out.println("Enter the savings account annual interest rate > ");
		savingsInterestRate = in.nextDouble();
		
		Savings sAccount = new Savings(savingsId, savingsBalance, savingsInterestRate);
		
		System.out.println("Enter the amount you would like to deposit into savings > ");
		savingsDepositAmount = in.nextDouble();
		sAccount.deposit(savingsDepositAmount);
		
		System.out.println("\nYour account information after the deposit ");
		System.out.println(sAccount.toString());
		System.out.println("Monthly interest: " + sAccount.getMonthlyInterestRate());
		
		System.out.println("\nEnter the amount you would like to withdraw from savings > ");
		savingsWithdrawAmount = in.nextDouble();
		sAccount.withdraw(savingsWithdrawAmount);
		
		System.out.println("\nYour account information after the withdraw ");
		System.out.println(sAccount.toString());
		System.out.println("Monthly interest: " + sAccount.getMonthlyInterestRate());
		
		//start here
		System.out.println("Enter the checking account ID > ");
		checkingId = in.nextInt();
		
		System.out.println("Enter the checking account balance > ");
		checkingBalance = in.nextDouble();
		
		System.out.println("Enter the checking account annual interest rate > ");
		checkingInterestRate = in.nextDouble();
		
		Checking cAccount = new Checking(checkingId, checkingBalance, checkingInterestRate);
		
		System.out.println("Enter the amount you would like to deposit into checking > ");
		checkingDepositAmount = in.nextDouble();
		cAccount.deposit(checkingDepositAmount);
		
		System.out.println("\nYour account information after the deposit ");
		System.out.println(cAccount.toString());
		System.out.println("Monthly interest: " + cAccount.getMonthlyInterestRate());
		
		System.out.println("\nEnter the amount you would like to withdraw from checking > ");
		checkingwithdrawAmount = in.nextDouble();
		cAccount.withdraw(checkingwithdrawAmount);
		
		System.out.println("\nYour account information after the withdraw ");
		System.out.println(cAccount.toString());
		System.out.println("Monthly interest: " + cAccount.getMonthlyInterestRate());
	}
}

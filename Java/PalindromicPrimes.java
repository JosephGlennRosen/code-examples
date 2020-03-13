import java.util.Scanner;
/**
 * The program asks the user how many palindromic primes between 1-100 they would like to be 
 * displayed. 
 * @author Joseph Rosen
 *
 */
public class PalindromicPrimes {
	public static void main(String[] args) 
	{
		Scanner in = new Scanner(System.in);
		int input = 0;

		System.out.println("Enter the number of palindromic primes you " +
				"wish to see. The number must be between 1-100. ");

		input = in.nextInt();

		//Validates the input
		while(input < 1 || input > 100)
		{
			System.out.println("The input must be between 1-100. Please enter a valid number. ");
			input = in.nextInt();
		}
		print(input);
	}

	/**
	 * Test the value if it is palindrome
	 * @param n is tested if it is a palindrome.
	 * @return true if n is a palindrome
	 */
	static boolean isPalindrome(Integer n)
	{
		String str = n.toString();
		StringBuilder number = new StringBuilder(str);
		StringBuilder reversedNumber = new StringBuilder(number);
		reversedNumber.reverse();
		Integer firstNumber = Integer.parseInt(number.toString());
		Integer secondNumber = Integer.parseInt(reversedNumber.toString());
		return (firstNumber.equals(secondNumber));

	}

	/**
	 * Test the value if it is prime
	 * @param n is the test value to find out if n is prime and returns true if n is prime.
	 * @return true if n is prime
	 */
	static boolean isPrime(int n) {
		if (n == 1) 
		{
			return false;
		}
		else if (n == 2)
		{
			return true;
		}

		else if (n % 2 == 0) return false;

		for(int i = 3; i * i <= n; i += 2)
		{
			if(n % i == 0)
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * Just a simple print method.
	 * @return nothing it is used to print the value
	 * @param n is used as the value to print.
	 */
	static void print(int n)
	{
		int lineCount = 0;
		int palindromePrimeCount = 0;
		int testValue = 2;

		while (palindromePrimeCount < n)
		{
			if (isPrime(testValue) && isPalindrome(testValue))
			{
				System.out.print(testValue + " ");
				lineCount++;

				if (lineCount == 10)
				{
					System.out.println("\n");
					lineCount = 0;
				}
				palindromePrimeCount++;
			}
			testValue++;
		}
	}
}

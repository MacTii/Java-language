import java.util.*;

public class Gra 
{
	private int N;
	private int attempts = 0;
	//Zad3 object = new Zad3();
	public void parseToInteger(String args[]) throws Exception
	{
		if(args.length!=1) // more or less than 1 argument
			throw new Exception("Zla liczba argumentow!");
		
		N = Integer.parseInt(args[0]);
		if (N < 1) // 0 and negative argument
			throw new Exception("Bledny argument!");
	}
	public int randomNumber()
	{
		Random rand = new Random();
		int r = rand.nextInt(N + 1);
		return r;
	}
	public int getAttempts()
	{
		return attempts;
	}
	public void numberOfAttempts()
	{
		attempts++;
	}
	public void resetNumberOfAttempts()
	{
		attempts = 0;
	}
	public int inputNumber()
	{
		Scanner input = new Scanner(System.in);
		int number;
		do 
		{
			System.out.println("Jaka zostala wylosowana liczba?");
			while (!input.hasNextInt()) 
			{
				System.out.println("Podaj prawidlowa liczbe!");
				input.next();
			}
			// while(number >= 0)
			// {
				// System.out.println("Podaj prawidlowa liczbe!");
				// input.next();
			// }
			number = input.nextInt();
			while(number < 0 || number > N)
			{
				System.out.println("Podaj prawidlowa liczbe!");
				number = input.nextInt();
			}
		} while (number < 0);
		
		return number;
	}
	public int inputString()
	{
		Scanner input = new Scanner(System.in);
		String check = "";
		check = input.next();
		if(check.equalsIgnoreCase("N"))
		{
			input.close();
			return 0;
		};
		return 1;
	}
	public static void main(String[] args) 
	{
		// if (args.length != 1)
			// throw new IllegalArgumentException("Podaj tylko 1 argument!");
		try
		{
			Gra object = new Gra();
			object.parseToInteger(args);
			//int N = Integer.parseInt(args[0]);
			
			// Random rand = new Random();
			// int r = rand.nextInt(N + 1);
			int random_number = object.randomNumber();
			
			//Scanner input = new Scanner(System.in); TU ZOSTALO
			object.resetNumberOfAttempts();
			
			while(true)
			{
				// System.out.println("Jaka zostala wylosowana liczba?"); TU ZOSTALO
				// number = input.nextInt();
				int number = object.inputNumber();
				
				object.numberOfAttempts();
				
				if(number == random_number)
				{
					System.out.println("Poprawna liczba. Ilosc prob: " + object.getAttempts());
					object.resetNumberOfAttempts();
					
					int error;
					do
					{
						System.out.println("Czy chcesz grac dalej? [T/N]");
						error = object.inputString();
						if(error == 0)
						{
							System.out.println("Dziekuje za skorzystanie z gry :)");
							return;
						}
						else if(error == 1)
						{
							random_number = object.randomNumber();
							object.resetNumberOfAttempts();
						}
					}while(error != 1);
					// String check = "";
					// do
					// {
						// System.out.println("Czy chcesz grac dalej? [T/N]");
						// check = input.next();
						// if(check.equalsIgnoreCase("N"))
						// {
							// input.close();
							// System.out.println("Dziekuje za skorzystanie z gry :)");
							// return;
						// }
						// if(check.equalsIgnoreCase("T"))
						// {
							// random_number = object.randomNumber();
							// object.resetNumberOfAttempts();
						// }
					// }while(!check.equalsIgnoreCase("T"));
				}
				else if(number > random_number)
					System.out.println("Wylosowana liczba jest mniejsza od podanej.");
				else
					System.out.println("Wylosowana liczba jest wieksza od podanej.");
			}
			
		}catch(IllegalArgumentException e) // invalid number of arguments
		{
			System.out.println("Zly argument!");
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		// catch(InputMismatchException e) // invalid input
		// {
			// throw new InputMismatchException("Podano zla liczbe!");
		// }
		
	}
}
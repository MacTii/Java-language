import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Fkwadratowa
{
	double a,b,c;
	double x1,x2;
	public void readParameters(String args[])
	{
		if(args.length!=3)
		{
			throw new RuntimeException("Zla liczba argumentow");
		}
		a=Double.parseDouble(args[0]);
		b=Double.parseDouble(args[1]);
		c=Double.parseDouble(args[2]);
	}
	// double readParametr() throws java.io.IOException
	// {
		// BufferedReader _read = new BufferedReader(new InputStreamReader(System.in));
		// double parameter = Double.parseDouble(_read.readLine());
		// return parametr;
	// }
	public static void printParametr(double parameter)
	{
		System.out.print(parameter);
	}
	public int calculate()
	{
		double delta = (b*b-4*a*c);
		//system.out.println("pierwiastki rownania to: " + x1 + " i " + x2);
		//printParametr(x1);
		if(delta < 0)
		{
			//system.out.println("rownanie nie posiada zadnych pierwiastkow");
			return -1;
		}
		else if(delta == 0)
		{
			x1 = -b/(2*a);
			return 1;
			//system.out.println("rownanie posiada jeden pierwiastek to " + x0);
		}
		x1 = (-b - Math.sqrt(delta))/(2*a);
		x2 = (-b + Math.sqrt(delta))/(2*a);
		return 2;
		//return 0;
	}
    public static void main(String[] args)
	{
		//System.out.println("Podaj parametry rownania kwadratowego ax^2+bx+c");
		//BufferedReader odczyt = new BufferedReader(new InputStreamReader(System.in));
		try
		{
			Fkwadratowa obiekt = new Fkwadratowa();
			obiekt.readParameters(args);
			int error = obiekt.calculate();
			switch (error)
			{
				case 1:
					System.out.print("Pierwiastek rownania to: ");
					obiekt.printParametr(obiekt.x1);
					break;
				case 2:
					System.out.print("Pierwiastki rownania to: ");
					obiekt.printParametr(obiekt.x1);
					System.out.print(" i ");
					obiekt.printParametr(obiekt.x2);
					break;
				default:
					System.out.print("Brak miejsc zerowych");
					break;
			}
			//double a,b,c,delta;
			//System.out.print("Podaj parametr a: ");
			//a = Double.parseDouble(odczyt.readLine());
			//readParametr(a);
		
			//System.out.print("Podaj parametr b: ");
			//b = Double.parseDouble(odczyt.readLine());
			//readParametr(b);
		
			//System.out.print("Podaj parametr c: ");
			//c = Double.parseDouble(odczyt.readLine());
			//readParametr(c);
			
			// delta = (b*b-4*a*c);
			// if(delta > 0)
			// {
				// delta = math.sqrt(delta);
				// double x1 = (-b - delta)/(2*a);
				// double x2 = (-b + delta)/(2*a);
				// System.out.println("pierwiastki rownania to: " + x1 + " i " + x2);
			// }
			// else if(delta < 0)
			// {
				// system.out.println("rownanie nie posiada zadnych pierwiastkow");
			// }
			// else
			// {
				// double x0 = -b/(2*a);
				// System.out.println("rownanie posiada jeden pierwiastek to " + x0);
			// }
			
		}catch(NumberFormatException n)
		{
			System.out.print("Zle parametry");
		}
    }
}
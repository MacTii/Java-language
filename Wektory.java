import java.util.*;
import java.io.*;

public class Wektory
{
	private double[] vector1;
	private double[] vector2;
	
	boolean isDouble(String str)
	{
        try
		{
            Double.parseDouble(str);
            return true;
			
        } catch (NumberFormatException e)
		{
            return false;
        }
    }
	
	public void putNumbersIntoVectors(String message, Scanner scan)
	{
		
		//Scanner scan = new Scanner(System.in); // stworzenie obiektu Scanner
		boolean flag = true;
		
		String inputNumbers;
		String[] splittedStrings;
		
		do
		{
			System.out.println(message + " 1:"); // Podaj wektor 1:
			inputNumbers = scan.nextLine(); // wczytanie liczb jako string
		
			splittedStrings =  inputNumbers.split(" "); // podzial stringa na liczby 
		
			vector1 = new double[splittedStrings.length]; // zaalokowanie pamieci na wektor 1
		
			for(int i = 0; i < splittedStrings.length; i++)
			{
				flag = isDouble(splittedStrings[i]);
				if(flag == false)
				{
					System.out.println("Zly wektor!");
					break;
				}
				
				vector1[i] = Double.parseDouble(splittedStrings[i]); // wpisanie liczb do wektora 1
			}
				
		}while(flag != true);
		
		do
		{
			System.out.println(message + " 2:"); // Podaj wektor 2:
			inputNumbers = scan.nextLine(); // wczytanie liczb jako string
				
			splittedStrings =  inputNumbers.split(" "); // podzial stringa na liczby
			
			vector2 = new double[splittedStrings.length]; // zaalokowanie pamieci na wektor 2
			
			for(int i = 0; i < splittedStrings.length; i++)
			{
				flag = isDouble(splittedStrings[i]);
				if(flag == false)
				{
					System.out.println("Zly wektor!");
					break;
				}
				
				vector2[i] = Double.parseDouble(splittedStrings[i]); // wpisanie liczb do wektora 2
			}
				
		}while(flag != true);
		
		//scan.close()
	}
	
	public double[] getVector1()
	{
		return vector1;
	}
	
	public int getVector1Length()
	{
		return vector1.length;
	}
	
	public double[] getVector2()
	{
		return vector2;
	}
	
	public int getVector2Length()
	{
		return vector2.length;
	}
	
	public void addVectorsToFile() throws WektoryRoznejDlugosciException, FileNotFoundException
	{
			
		if(getVector1Length() != getVector2Length())
			throw new WektoryRoznejDlugosciException("Podano wektory roznej dlugosci!", getVector1Length(), getVector2Length());
		
		double[] result = new double[vector1.length];
		
		for(int i = 0; i < vector1.length; i++)
			result[i] = vector1[i] + vector2[i];
			
		PrintWriter writer = new PrintWriter("vector.txt");

		for(int i=0; i < result.length; i++)
			writer.print(result[i] + " ");

		writer.close();
			
	}
	
	public static void main(String args[])
	{
		Wektory object = new Wektory();
		Scanner scan = new Scanner(System.in); // stworzenie obiektu Scanner
		
		do
		{
			try
			{
			
				String msg = "Podaj wektor";
				object.putNumbersIntoVectors(msg, scan);
				
				object.addVectorsToFile();
			
			}catch(WektoryRoznejDlugosciException e)
			{
				System.err.println(e.getMessage());
				System.out.println("Wektor 1 ma dlugosc: " + e.getV1Length());
				System.out.println("Wektor 2 ma dlugosc: " + e.getV2Length());
			}catch(FileNotFoundException e)
			{
				System.err.println("Blad! Nie znaleziono pliku! Wyjatek: " + e);
			}

		}while(object.getVector1Length() != object.getVector2Length());
		
		System.out.println("Pomyslnie zapisano wektory :)");
		scan.close(); // zamkniecie scannera
	}
}


class WektoryRoznejDlugosciException extends Exception 
{
	private int v1Length;
	private int v2Length;
	
	public WektoryRoznejDlugosciException(String errorMessage, int v1LengthParameter, int v2LengthParameter)
	{
		super(errorMessage);
		v1Length = v1LengthParameter;
		v2Length = v2LengthParameter;
	}
	
	int getV1Length()
	{
		return v1Length;
	}
	
	int getV2Length()
	{
		return v2Length;
	}
}
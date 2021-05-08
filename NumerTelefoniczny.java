import java.util.*;

public class NumerTelefoniczny
{	
	public static void main(String args[])
	{	
		NrTelefoniczny numer1 = new NrTelefoniczny("+11", "123-456-789");
		Adres adres1 = new Adres("XYZ", 24, "12-134", "Warszawa");
		Osoba osoba1 = new Osoba ("Mariusz", "Iksinski", adres1, numer1);
		
		NrTelefoniczny numer2 = new NrTelefoniczny("+22", "987-654-321");
		Adres adres2 = new Adres("ZYZ-Q", 54, "32-543", "Lodz"); 
		Osoba osoba2 = new Osoba ("Pawel", "Ygreksinki", adres2, numer2);
		
		NrTelefoniczny numer3 = new NrTelefoniczny("+42", "111-231-123");
		Adres adres3 = new Adres("ZYZ-Q", 43, "88-312", "Wroclaw");
        	Osoba osoba3 = new Osoba ("Dariusz", "Peksinski", adres3, numer3); // same address
		//System.out.println("Adres :" + osoba5.getAddress());
		
		//Osoba osoba5 = new Osoba("Konrad", "Tesinski", "ul. BNM 123", "+77", "444-333-222"); // person constructor with phone number
		
        	NrTelefoniczny numerFirma1 = new NrTelefoniczny("+55", "777-888-999");
		Adres adresFirma1 = new Adres("ASD-E", 107, "11-345", "Olchow");
        	Firma firma1 = new Firma("Company QWE", adresFirma1, numerFirma1);	
		
		NrTelefoniczny numerFirma2 = new NrTelefoniczny("+12", "311-374-323");
		Adres adresFirma2 = new Adres("ZXC", 1, "77-123", "Tomaszow");
        	Firma firma2 = new Firma("CoMpNY LOPJK", adresFirma2, numerFirma2);
		
		//Firma firma3 = new Firma("CompanY ZXC", "ul. CVB 3", "+88", "666-555-444"); // firm constructor with phone number 

        	TreeMap<NrTelefoniczny, Wpis> ksiazkaTelNumery = new TreeMap <NrTelefoniczny, Wpis>();
		
		ksiazkaTelNumery.put(numer1, osoba1);
        	ksiazkaTelNumery.put(numer2, osoba2);
        	ksiazkaTelNumery.put(numer3, osoba3);
		
        	ksiazkaTelNumery.put(numerFirma1, firma1);
        	ksiazkaTelNumery.put(numerFirma2, firma2);
		
		Set<Adres> set = new TreeSet<Adres>();
		
		set.add(adres1);
		set.add(adres2);
		set.add(adres3);
		set.add(adresFirma1);
		set.add(adresFirma2);
		
		for(Adres adres:set)
			System.out.println(adres.getStreet());    
		
		System.out.println();
		System.out.println("Ksiazka telefoniczna (wzgledem numerow telefonu) :");
        	for(Map.Entry<NrTelefoniczny, Wpis> entry : ksiazkaTelNumery.entrySet())
			entry.getValue().opis();
	}
}

class NrTelefoniczny implements Comparable<NrTelefoniczny> 
{
	private String nrKierunkowy;
	private String nrTelefonu;
	
	NrTelefoniczny(String nrKierunkowyParameter, String nrTelefonuParameter)
	{
		nrKierunkowy = nrKierunkowyParameter;
		nrTelefonu = nrTelefonuParameter;
	}
	
	// void setAreaCode(String nrKierunkowy)
	// {
		// this.nrKierunkowy = nrKierunkowy;
	// }
	
	// void setPhoneNumber(String nrTelefonu)
	// {
		// this.nrTelefonu = nrTelefonu;
	// }
	
	String getAreaCode()
	{
		return nrKierunkowy;
	}
	
	String getPhoneNumber()
	{
		return nrTelefonu;
	}
	
	public int compareTo(NrTelefoniczny nrTelefoniczny)
	{
		int compare = nrKierunkowy.compareTo(nrTelefoniczny.nrKierunkowy);
		if(compare == 0)
			return nrTelefonu.compareTo(nrTelefoniczny.nrTelefonu);
		else
			return compare;
	}
}

abstract class Wpis
{
	abstract void opis();
	abstract NrTelefoniczny getPhoneNumber();
	abstract Adres getAddress();
}

class Adres implements Comparable<Adres>
{
	private String ulica;
	private int numer;
	private String kodPocztowy;
	private String miejscowosc;
	
	Adres(String ulicaParameter, int numerParameter, String kodPocztowyParameter, String miejscowoscParameter)
	{
		ulica = ulicaParameter;
		numer = numerParameter;
		kodPocztowy = kodPocztowyParameter;
		miejscowosc = miejscowoscParameter;
	}
	
	String getStreet()
	{
		return ulica;
	}
	
	int getNumber()
	{
		return numer;
	}
	
	String getPostCode()
	{
		return kodPocztowy;
	}
	
	String getTown()
	{
		return miejscowosc;
	}
	
	public int compareTo(Adres adres)
	{
		return ulica.compareTo(adres.ulica);
	}
	
	void opisAdres()
	{
		System.out.print("ul. " + getStreet() + " " + getNumber() + " " + getPostCode() + " " + getTown());
	}
}

class Osoba extends Wpis
{
	private String imie;
	private String nazwisko;
	
	Adres adres;
	NrTelefoniczny nrTelefoniczny;
	
	// Osoba(String imieParameter, String nazwiskoParameter, String adresParameter, String nrKierunkowyParameter, String nrTelefonuParameter)
	// {
		// imie = imieParameter;
		// nazwisko = nazwiskoParameter;
		// adres = adresParameter;
		
		// //nrTelefoniczny.nrKierunkowy = nrKierunkowyParameter;
		// //nrTelefoniczny.nrTelefonu = nrTelefonuParameter;
	// }
	
	Osoba(String imieParameter, String nazwiskoParameter, Adres adresParameter, NrTelefoniczny nrTelefonicznyParameter)
	{
		imie = imieParameter;
		nazwisko = nazwiskoParameter;
		
		adres = adresParameter;
		nrTelefoniczny = nrTelefonicznyParameter;
	}
	
	String getFirstName()
	{
		return imie;
	}
	
	String getLastName()
	{
		return nazwisko;
	}
	
	NrTelefoniczny getPhoneNumber()
	{
		return nrTelefoniczny;
	}
	
	Adres getAddress()
	{
		return adres;
	}
	
	void opis()
	{
		System.out.print(getFirstName() + " " + getLastName() + " ");
		adres.opisAdres();
		System.out.println(" " + nrTelefoniczny.getAreaCode() + " " + nrTelefoniczny.getPhoneNumber());
	}
}

class Firma extends Wpis
{
	private String nazwa;
	
	Adres adres;
	NrTelefoniczny nrTelefoniczny;
	
	// Firma(String nazwaParameter, String adresParameter, String nrKierunkowyParameter, String nrTelefonuParameter)
	// {
		// nazwa = nazwaParameter;
		// adres = adresParameter;
		
		// nrTelefoniczny.getAreaCode() = nrKierunkowyParameter;
		// nrTelefoniczny.getPhoneNumber() = nrTelefonuParameter;
		
		// //nrTelefoniczny.nrKierunkowy = nrKierunkowyParameter;
		// nrTelefoniczny.nrTelefonu = nrTelefonuParameter;
	// }
	
	Firma(String nazwaParameter, Adres adresParameter, NrTelefoniczny nrTelefonicznyParameter)
	{
		nazwa = nazwaParameter;
		
		adres = adresParameter;
		nrTelefoniczny = nrTelefonicznyParameter;
	}
	
	String getName()
	{
		return nazwa;
	}
	
	NrTelefoniczny getPhoneNumber()
	{
		return nrTelefoniczny;
	}
	
	Adres getAddress()
	{
		return adres;
	}
	
	void opis()
	{
		System.out.print(getName() + " ");
		adres.opisAdres();
		System.out.println(" " + nrTelefoniczny.getAreaCode() + " " + nrTelefoniczny.getPhoneNumber());
	}
}

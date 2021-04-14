import java.io.*;
import java.util.*;
import java.lang.*;

public class ListaZakupow
{
	private static String filename;
	private int count = 0;
	//ListaZakupow object = new ListaZakupow();
	public void parseToString(String args[]) throws ArrayIndexOutOfBoundsException
	{
		if(args.length != 1)
			throw new ArrayIndexOutOfBoundsException("Zla liczba argumentow!");
			
		filename = args[0];
	}
	public String getFilename()
	{
		return filename;
	}
	public int getCountOfProducts()
	{
		return count;
	}
	public void resetCountOfProducts()
	{
		count = 0;
	}
	public void addCountOfProduct()
	{
		count++;
	}
	public void removeCountOfProduct()
	{
		count--;
	}
	// public File createFileObject(String args[])
	// {
		// object.parseToString(args);
		// File fileObject = new File(object.getFilename());
		// return fileObject;
	// }
	public File createFileObject(String args[])
	{
		//ListaZakupow ob = new ListaZakupow();
		this.parseToString(args);
		File fileObject = new File(this.getFilename());
		return fileObject;
	}
	public Scanner createScannerObject(String args[]) throws FileNotFoundException
	{
		//ListaZakupow ob = new ListaZakupow();
		//ob.parseToString(args);
		//File fileObject = new File(ob.getFilename());
		File file = this.createFileObject(args);
		Scanner reader = new Scanner(file);
		return reader;
	}
	public void writeToFile(ArrayList<String> list) throws IOException
	{
		//ListaZakupow ob = new ListaZakupow();
		FileWriter writer = new FileWriter(this.getFilename()); 
		for(String str: list)
			writer.write(str + System.lineSeparator());
		writer.close();
	}
	public void optionsOfList()
	{
		System.out.println("Co chcesz zrobic ?");
		System.out.println("1-dodaj produkt do listy zakupow");
		System.out.println("2-usun produkt z listy zakupow");
		System.out.println("3-usun cala liste zakupow");
		System.out.println("4-pokaz liste zakupow");
		System.out.println("5-zapisz liste zakupow na dysku");
	}
	public void optionsOfAddingToList()
	{
		System.out.println("Co chcesz zrobic ?");
		System.out.println("1-dodaj produkt do wybranej kategorii");
		System.out.println("2-dodaj produkt z nowa kategoria");
	}
	public void showListOfProducts(ArrayList<String> list)
	{
		if(list.size() == 0)
		{
			System.out.println("Lista zakupow jest pusta");
			return;
		}
		for (String str : list)	      
			System.out.println(str); 		
	}
	public void firstSaveToArrayList(Scanner reader, ArrayList<String> list) //throws FileNotFoundException
	{
		//File file = object.createFileObject(args);
		//Scanner myReader = new Scanner(file);
		//ArrayList<String> arrayList = new ArrayList<String>();
		//ListaZakupow ob = new ListaZakupow();
		
		while (reader.hasNextLine())
		{
			String data = reader.nextLine();

			// char c = data.charAt(0);
			// if(c == '-') //&& isCategory == true)
			// {
				// tmp = data.substring(1);
				// object.resetCountOfProducts();
				//isCategory = false;
			// }
			// else //if(c != '-')
			// {
				// object.countOfProducts();
				// listaKategorii.put(tmp);
			// }
			list.add(this.getCountOfProducts(),data);
			this.addCountOfProduct();
			//System.out.println(data);
		}
	}
	public void removeProduct(ArrayList<String> list, String p)
	{
		while(!list.contains(p))
		{
			p = this.inputProductToRemove();
			if(!list.contains(p))
				System.out.println("Podales zly produkt!");
		}
		
		int iterator = list.indexOf(p);
		list.remove(iterator);
		
		this.removeCountOfProduct();
	}
	public void addProductWithCorrectCategory(ArrayList<String> list, String category, String product)
	{
		while(!list.contains("-" + category))
		{
			category = this.inputCategory();
			//object.addCountOfProduct();
			if(list.contains("-" + category))
			{
				//int count = object.findIndexOfCategory(arrayList,category);
				//System.out.println(arrayList);
				//System.out.println(category);
				//System.out.println(count);
				product = this.inputProduct();
				
				int count = this.findIndexOfCategory(list,category);
				//System.out.println(object.getCountOfProducts());
				this.addCountOfProduct();
				//System.out.println(this.getCountOfProducts());
				list.add(count+1,product);
			}
			if(!list.contains(product))
				System.out.println("Podales zla kategorie!");
		}
	}
	public int inputOption()
	{
		Scanner input = new Scanner(System.in);
		System.out.print("Podaj opcje: ");
		int chOp = input.nextInt();
		return chOp;
	}
	public String inputProduct()
	{
		Scanner input = new Scanner(System.in);
		System.out.print("Podaj produkt: ");
		String p = input.nextLine();
		return p;
	}
	public String inputCategory()
	{
		Scanner input = new Scanner(System.in);
		System.out.print("Podaj kategorie: ");
		String c = input.nextLine();
		return c;
	}
	public String inputProductToRemove()
	{
		Scanner input = new Scanner(System.in);
		System.out.print("Podaj produkt do usuniecia: ");
		String p = input.nextLine();
		return p;
	}
	public String inputYesOrNot()
	{
		Scanner input = new Scanner(System.in);
		System.out.println("Czy chcesz usunac cala liste? [T/N]");
		String tmp = input.next();
		return tmp;
	}
	public void checkToDeleteList(ArrayList<String> list) throws IOException
	{
		//ListaZakupow ob = new ListaZakupow();
		String c = "";
		do 
		{
			c = this.inputYesOrNot();
			// System.out.println("Czy chcesz usunac cala liste? [T/N]");
			// c = input.next();
			if(c.equalsIgnoreCase("N"))
				break;
			if(c.equalsIgnoreCase("T"))
			{
				list.clear();
				this.resetCountOfProducts();
				
				//this.writeToFile(list); CHYBA BEZ
				
				// writer = new FileWriter(object.getFilename()); 
				// for(String str: arrayList)
				// {
					// writer.write(str + System.lineSeparator());
				// }
				// writer.close();
				
				//System.out.println(list);
				//System.out.println(this.getCountOfProducts());
			}
		}while(!c.equalsIgnoreCase("T"));
	}
	public int findIndexOfCategory(ArrayList<String> list, String category)
	{
		//this.resetCountOfProducts();
		int n = list.indexOf("-" + category);
		
		// for(int i = 0; i<list.size(); i++)
		// {
			// String data = list.get(i);
			//System.out.println(data + "1");
			//System.out.println(category + "2");
			// if(data.equals("-" + category))
				// break;
			// this.addCountOfProduct();
		// }
		//this.addCountOfProduct();
		return n;
	}
	public void countOfProducts(ArrayList<String> list)
	{
		this.resetCountOfProducts();
		for(int i=0;i<list.size();i++)
			this.addCountOfProduct();
	}
	public static void main(String args[])
	{
		try
		{
			ListaZakupow object = new ListaZakupow();
			
			// object.parseToString(args);
			// File fileObject = new File(object.getFilename());
			// Scanner myReader = new Scanner(fileObject);
			Scanner myReader = object.createScannerObject(args);
			
			ArrayList<String> arrayList = new ArrayList<String>();
			
			//boolean isCategory = true;
			object.resetCountOfProducts();
			
			System.out.println("Lista zakupow:");
			object.firstSaveToArrayList(myReader,arrayList);
			object.showListOfProducts(arrayList);
			
			//String tmp = "";
			// while (myReader.hasNextLine())
			// {
				// String data = myReader.nextLine();

				// char c = data.charAt(0);
				// if(c == '-') //&& isCategory == true)
				// {
					// tmp = data.substring(1);
					// object.resetCountOfProducts();
					//isCategory = false;
				// }
				// else //if(c != '-')
				// {
					// object.countOfProducts();
					// listaKategorii.put(tmp);
				// }
				// arrayList.add(object.getCountOfProducts(),data);
				// object.addProduct();
				// System.out.println(data);
			// }
			
			System.out.println();
			
			//int N = arrayList.size(); // size of elements in map
			//int tab[] = new int[N]; // table of elements
			
			//System.out.println(N);
			
			//int k = N;
			
			// for (String key : listaKategorii.keySet())
			// {
				// System.out.println(key + " - " + listaKategorii.get(key));
				// tab[--k] = listaKategorii.get(key);
			// }
			
			// for(int i=0;i<N;i++)
				// System.out.println(tab[i]);
			
			//System.out.println(arrayList);
			//System.out.println();
			
			while(true)
			{
				object.optionsOfList();
				// System.out.println("Co chcesz zrobic ?");
				// System.out.println("1-dodaj produkt do listy zakupow");
				// System.out.println("2-usun produkt z listy zakupow");
				// System.out.println("3-usun cala liste zakupow");
				// System.out.println("4-zapisz liste zakupow na dysku");
				
				int checkOption = object.inputOption();
				// System.out.print("Podaj opcje: ");
				// Scanner input = new Scanner(System.in);
				// int checkOption = input.nextInt();
				
				System.out.println();
				
				String category = "";
				String product = "";
				
				switch(checkOption)
				{
					case 1:
						object.optionsOfAddingToList();
						// System.out.println("Co chcesz zrobic ?");
						// System.out.println("1-dodaj produkt do wybranej kategorii");
						// System.out.println("2-dodaj produkt z nowa kategoria");
						
						int check = object.inputOption();
						// System.out.print("Podaj opcje: ");
						// int check  = input.nextInt();
						
						//input.nextLine(); // clear buffer
						System.out.println();
						
						category = "";
						product = "";
						
						if(check == 1)
						{
							
							//object.resetCountOfProducts();
							
							//System.out.println(args[0]);
							
							// myReader.close();
							// File fileObject = object.createFileObject(args);
							// myReader = new Scanner(fileObject);
							
							//object.modifyArrayList(myReader,arrayList);
							object.addProductWithCorrectCategory(arrayList,category,product);
							// while(!arrayList.contains("-" + category))
							// {
								// category = object.inputCategory();
								//object.addCountOfProduct();
								// if(arrayList.contains("-" + category))
								// {
									// int count = object.findIndexOfCategory(arrayList,category);
									// System.out.println(arrayList);
									// System.out.println(category);
									// System.out.println(count);
									// product = object.inputProduct();
									
									// int count = object.findIndexOfCategory(arrayList,category);
									//System.out.println(object.getCountOfProducts());
									// object.addCountOfProduct();
									// arrayList.add(count+1,product);
								// }
								// if(!arrayList.contains(product))
									// System.out.println("Podales zla kategorie!");
							// }
							
							// while(myReader.hasNextLine())
							// {
								//System.out.println("1");
								// String data = myReader.nextLine();
								// char c = data.charAt(0);
								//System.out.println(c);
								// object.addCountOfProduct();
								// if(c == '-')
								// {
									//System.out.println("2");
									// data = data.substring(1);
									// if(category.equalsIgnoreCase(data))
									// {
										//System.out.println("3");
										//System.out.print("Podaj produkt: ");
										//product = input.nextLine();
										// product = object.inputProduct();
										
										// arrayList.add(object.getCountOfProducts(),product);
										
										// System.out.println(arrayList);
										
										// System.out.println(args[0]);
										// object.parseToString(args);
										
										// System.out.println(object.getFilename()); // czemu null ?
										
										// object.writeToFile(arrayList); CHYBA BEZ
										
										// FileWriter writer = new FileWriter(object.getFilename()); 
										// for(String str: arrayList)
										// {
											// writer.write(str + System.lineSeparator());
										// }
										// writer.close();
										
										// p.println(product);

									// }
								// }
								
								//System.out.println(data);
							  //}
							
							// if (listaKategorii.containsKey(category))
							// {
								// System.out.print("Podaj produkt: ");
								// String product = input.nextLine();
							// }
							
						}
						else
						{
							//System.out.print("Podaj nowa kategorie: ");
							
							object.countOfProducts(arrayList);
							
							category = object.inputCategory();
							
							arrayList.add(object.getCountOfProducts(),"-" + category);
							
							object.addCountOfProduct();
							
							product = object.inputProduct();
							// System.out.print("Podaj produkt: ");
							// product = input.nextLine();
							
							arrayList.add(object.getCountOfProducts(), product);
							
							//object.writeToFile(arrayList); CHYBA BEZ
							
							// FileWriter writer = new FileWriter(object.getFilename()); 
							// for(String str: arrayList)
							// {
								// writer.write(str + System.lineSeparator());
							// }
							// writer.close();
							
							//System.out.println(arrayList);
						}
						break;
					case 2:
						//input.nextLine();
						// System.out.print("Podaj produkt do usuniecia: ");
						// product = input.nextLine();
						object.removeProduct(arrayList,product);
						// while(!arrayList.contains(product))
						// {
							// product = object.inputProductToRemove();
							// if(!arrayList.contains(product))
								// System.out.println("Podales zly produkt!");
						// }
						
						// int iterator = arrayList.indexOf(product);
						// arrayList.remove(iterator);
						
						// object.removeCountOfProduct();
						
						//object.countOfProducts();
						//System.out.println(object.getCountOfProducts());
						
						//object.writeToFile(arrayList); CHYBA BEZ
						
						// FileWriter writer = new FileWriter(object.getFilename()); 
						// for(String str: arrayList)
						// {
							// writer.write(str + System.lineSeparator());
						// }
						// writer.close();
						
						//System.out.println(arrayList);
						//System.out.println(object.getCountOfProducts());
						break;
						
					case 3:
						object.checkToDeleteList(arrayList);
						// String c = "";
						// do 
						// {
							// c = object.inputYesOrNot();
							// System.out.println("Czy chcesz usunac cala liste? [T/N]");
							// c = input.next();
							// if(c.equalsIgnoreCase("N"))
								// break;
							// if(c.equalsIgnoreCase("T"))
							// {
								// arrayList.clear();
								// object.resetCountOfProducts();
								
								// object.writeToFile(arrayList);
								
								// writer = new FileWriter(object.getFilename()); 
								// for(String str: arrayList)
								// {
									// writer.write(str + System.lineSeparator());
								// }
								// writer.close();
								
								// System.out.println(arrayList);
								// System.out.println(object.getCountOfProducts());
							// }
						// }while(!c.equalsIgnoreCase("T"));
						break;
					case 4:
						System.out.println("Lista zakupow:");
						object.showListOfProducts(arrayList);
						System.out.println();
						break;
					case 5:
						object.writeToFile(arrayList);
						// writer = new FileWriter(object.getFilename()); 
						// for(String str: arrayList)
						// {
							// writer.write(str + System.lineSeparator());
						// }
						// writer.close();
						System.out.println("Lista zakupow zostala poprawnie zapisana :)");
						return;
					default:
						System.out.println("Podaj poprawna opcje!");
						break;
				}
			}
			
	
		} catch (ArrayIndexOutOfBoundsException e)
		{
			System.out.println(e.getMessage());
			
		}catch (FileNotFoundException e)
		{
			System.out.println("Zly plik!");
		}catch (IOException e)
		{
			System.out.println("Blad pliku!");
			//e.printStackTrace();
		}
	}
}
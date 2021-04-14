public class Main{

    public static void main(String[] args) {
	//System.out.println("Hello world!");
	int suma = 0;
	for(int i = 0; i < 2; i++) {
           suma += Integer.parseInt(args[i]);
		}
		System.out.println("Wynik " +suma );
    }
}
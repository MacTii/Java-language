import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

class ClientEcho {
	
	private String host;
	private int port;
	private PrintWriter out;
	private BufferedReader in;
	
	ClientEcho(String h, int p)
	{
		host = h;
		port = p;
		out = null;
		in = null;
	}
	
	void initBuffers(Socket s) throws IOException
	{
		out = new PrintWriter(s.getOutputStream(), true);
		InputStreamReader ins = new InputStreamReader(s.getInputStream());
		in = new BufferedReader(ins);		
	}
	
	void communicateWithServer(PrintWriter out, BufferedReader in) throws IOException
	{
		Scanner scan = new Scanner(System.in);
		String line = null;
		while("exit".equalsIgnoreCase(line) == false)
		{
			line = scan.nextLine();
			out.println(line);
			out.flush();
			System.out.println("Odpowiedz od serwera: " + in.readLine());
		}
		scan.close();
	}
	
	void connectWithServer()
	{
		try{
			Socket s = new Socket(host, port);
			
			this.initBuffers(s);

			System.out.println("Wpisz 'exit' aby wy³¹czyæ klienta");
			System.out.println("Podaj tekst: ");
			
			this.communicateWithServer(out, in);
			
			s.close();
		}
		catch(UnknownHostException e1)
		{
			System.out.println("Nieznany host - b³¹d");
		} 
		catch (IOException e2) 
		{
			System.out.println("IO - b³¹d");
		}
	}
}

public class Client 
{
	public static void main(String[] args)
	{
		String host = "192.168.1.2"; //standardowy lokalny adres
		int port = 4900;
		ClientEcho var = new ClientEcho(host,port);
		var.connectWithServer();
		System.out.println("Wy³¹czanie klienta");
	}
}

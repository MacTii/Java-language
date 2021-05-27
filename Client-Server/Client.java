import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

class ClientEcho
{
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
	
	void checkDate(String t) throws ParseException, Exception
	{
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");  
		Date inputDate = formatter.parse(t);
			
		String[] splittedTime = t.split(":");
		int hour = Integer.parseInt(splittedTime[0]);
		int minute = Integer.parseInt(splittedTime[1]);
			
		if(hour < 0 || hour > 24)
			throw new Exception("Zly format daty");
			
		if(minute < 0 || minute > 59)
			throw new Exception("Zly format daty");
	}
	
	void communicateWithServer(PrintWriter out, BufferedReader in) throws IOException, ParseException, Exception
	{
		Scanner scan = new Scanner(System.in);
		String text = null;
		String time = null;
		while(true)
		{
			text = scan.nextLine();
			if("exit".equalsIgnoreCase(text) == true)
				break;
			time = scan.nextLine();
			
			this.checkDate(time);
			
			out.println(text);
			out.println(time);
			//out.flush();
		}
		scan.close();
	}
	
	void connectWithServer() 
	{
		try{
			Socket s = new Socket(host, port);
			this.initBuffers(s);

			System.out.println("Wpisz 'exit' aby wylaczyc klienta");
			System.out.println("Podaj tekst i godzine: ");
			
			ClientPrinter clientThread = new ClientPrinter(s);
			new Thread(clientThread).start();
			this.communicateWithServer(out, in);
			s.close();
			clientThread.stop();
			
		}
		catch(UnknownHostException e1)
		{
			System.out.println("Nieznany host - blad");
		} 
		catch (IOException e2) 
		{
			System.out.println("IO - blad");
		}
		catch (ParseException e3)
		{
			System.out.println("Zla data");
		}
		catch(Exception e4)
		{
			System.out.println(e4.getMessage());
		}
		finally
		{
			try {
				in.close();
				out.close();
				
			}
			catch(IOException e)
			{
				System.out.println("Blad IO po stronie klienta/ przy zamykaniu");
			}
		}
	}
}

class ClientPrinter implements Runnable
{
	private Socket socket;
	private BufferedReader in;
	private boolean exit;
	
	ClientPrinter(Socket s) throws IOException
	{
		socket = s;
		InputStreamReader ins = new InputStreamReader(s.getInputStream());
		in = new BufferedReader(ins);
		exit = false;
	}
	
	public void run()
	{
		try
		{
			while(!exit)
			{
				if(in.ready())
					System.out.println("Odpowiedz od serwera: " + in.readLine());
			}
		}
		catch(IOException e)
		{
			System.out.println("Błąd");
		}
		finally
		{
			try
			{
				socket.close();
				in.close();
			}
			catch(IOException e)
			{
				System.out.println("Blad IO");
			}
		}
	}
	
	public void stop()
    {
        exit = true;
    }
	
	boolean isThreadRunning()
	{
		return exit;
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
		System.out.println("Wylaczanie klienta");
	}
}

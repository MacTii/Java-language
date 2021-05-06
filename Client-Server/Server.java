import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

class ClientHandler implements Runnable
{
	private final Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;
	
	ClientHandler(Socket socket)
	{
		clientSocket = socket;
		out = null;
		in = null;
	}
	
	void initBuffers() throws IOException
	{
		out = new PrintWriter(clientSocket.getOutputStream(), true);
		InputStreamReader ins = new InputStreamReader(clientSocket.getInputStream());
		in = new BufferedReader(ins);		
	}
	
	void takeAndSendDatas() throws IOException, ParseException, InterruptedException
	{
		String text = null;
		String time = null;
		while((text = in.readLine()) != null && (time = in.readLine()) != null)
		{
			System.out.print("Wiadomoœæ od klienta: ");
			System.out.print(text + " ");
			System.out.println(time);
			
			SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");  
			
			Date inputDate = formatter.parse(time);
			Date date;
			
			int result;
			while(true)
			{
				date = new Date();
				result = formatter.format(date).compareTo(formatter.format(inputDate));
				//System.out.println(result);
				Thread.sleep(3000);
				if(result == 0 || result > 0)
					break;
			}
			
			if(result == 0)
			{
				out.println(text);
				out.println(time);
			}
		}
	}
	
	public void run()
	{
		try {
			
			this.initBuffers();
			
			this.takeAndSendDatas();
		}
		catch(IOException e1)
		{
			System.out.println("B³¹d IO");
		}
		catch(ParseException e2)
		{
			System.out.println("B³¹d");
		}
		catch(InterruptedException e3)
		{
			System.out.println("B³¹d");
		}
		finally
		{
			try {
				if(out != null) out.close();
				if(in  != null) in.close();
				clientSocket.close();
			}
			catch(IOException e1)
			{
				System.out.println("B³¹d IO");
			}
		}
	}
}

class ServerHandler
{
	private int port;
	private ServerSocket server;
	ServerHandler(int p)
	{
		port = p;
		server = null;
	}
	
	void serverLaunch()
	{
		try {
			server = new ServerSocket(port);
			while(true)
			{
				Socket client = server.accept();
				System.out.println("Nowy klient do³¹czy³ do serwera: " 
									+ client.getInetAddress().getHostAddress());
				
				ClientHandler clientThread = new ClientHandler(client);
				new Thread(clientThread).start();
			}
		}
		catch(IOException e)
		{
			System.out.println("B³¹d IO po stronie serwera");
		}
		finally 
		{
			if(server != null)
			{
				try {
					server.close();
				}
				catch(IOException e1)
				{
					System.out.println("B³¹d IO po stronie serwera/ przy zamykaniu");
				}
			}
		}
	}
}

public class Server {
	public static void main(String[] args)
	{
		
		System.out.println("Serwer - start");
		int port = 4900;
		ServerHandler var = new ServerHandler(port);
		var.serverLaunch();
		
	}
}

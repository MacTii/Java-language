import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

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
	
	void takeAndSendDatas() throws IOException
	{
		String line = null;
		while((line = in.readLine()) != null)
		{
			System.out.println("Wiadomosc od klienta: " + line);
			out.println(line);
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
		finally
		{
			try {
				if(out != null) out.close();
				if(in  != null) in.close();
				clientSocket.close();
			}
			catch(IOException e1)
			{
				System.out.println("Blad IO");
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
				System.out.println("Nowy klient dolaczyl do serwera: " 
									+ client.getInetAddress().getHostAddress());
				
				ClientHandler clientThread = new ClientHandler(client);
				new Thread(clientThread).start();
			}
		}
		catch(IOException e)
		{
			System.out.println("Blad IO po stronie serwera");
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
					System.out.println("Blad IO po stronie serwera/ przy zamykaniu");
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

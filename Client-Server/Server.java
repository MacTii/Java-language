import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import java.util.*;

class ClientHandler implements Runnable
{
	private final Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;
	private int numberOfClient;
	QueueHandler queue;
	
	ClientHandler(Socket socket, int number, QueueHandler q)
	{
		clientSocket = socket;
		numberOfClient = number;
		queue = q;
		out = null;
		in = null;
	}
	
	int getNumberOfClient()
	{
		return numberOfClient;
	}
	
	void initBuffers() throws IOException
	{
		out = new PrintWriter(clientSocket.getOutputStream(), true);
		InputStreamReader ins = new InputStreamReader(clientSocket.getInputStream());
		in = new BufferedReader(ins);
	}
	
	int checkDate(String t) throws ParseException, InterruptedException, Exception
	{
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");  
			
		Date inputDate = formatter.parse(t);
		Date date = new Date();
		
		String[] splittedTime = t.split(":");
		int hour = Integer.parseInt(splittedTime[0]);
		int minute = Integer.parseInt(splittedTime[1]);
			
		if(hour < 0 || hour > 24)
			throw new Exception("Zly format daty");
			
		if(minute < 0 || minute > 59)
			throw new Exception("Zly format daty");
		
		int r = formatter.format(date).compareTo(formatter.format(inputDate));
		return r;
	}
	
	void takeAndSendDatas() throws ParseException, InterruptedException, Exception
	{
		String text = null;
		String time = null;

		while((text = in.readLine()) != null && (time = in.readLine()) != null)
		{
			System.out.print("Wiadomość od klienta " + this.getNumberOfClient() + ": ");
			System.out.print(text + " ");
			System.out.println(time);
			
			int result = this.checkDate(time);
			Notify n = new Notify(clientSocket, time, text);
			
			queue.addToQueue(n);
			if(queue.isThreadRunning() == false)
			{
				new Thread(queue).start();
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
			e1.printStackTrace();
			System.out.println("Błąd IO");
		}
		catch(ParseException e2)
		{
			System.out.println("Błąd");
		}
		catch(InterruptedException e3)
		{
			System.out.println("Błąd");
		}
		catch(Exception e4)
		{
			System.out.println(e4.getMessage());
		}
		// finally
		// {
			// try {
				//if(out != null) out.close();
				//if(in  != null) in.close();
				// clientSocket.close();
			// }
			// catch(IOException e1)
			// {
				// System.out.println("Błąd IO");
			// }
		// }
	}
}

class Notify implements Comparable<Notify>
{
	private Socket clientSocket;
	private String time;
	private String message;
	
	Notify(Socket client, String t, String m)
	{
		clientSocket = client;
		time = t;
		message = m;
	}
	
	Socket getSocket()
	{
		return clientSocket;
	}
	
	String getTime()
	{
		return time;
	}
	
	String getMessage()
	{
		return message;
	}
	
	public int compareTo(Notify n)
	{
		int compare = time.compareTo(n.time);
		return compare;
	}
}

class QueueHandler implements Runnable
{
	PriorityQueue<Notify> pQueue;
	private PrintWriter out;
	private final Socket clientSocket;
	private boolean check;
	
	QueueHandler(Socket socket, PriorityQueue<Notify> q)
	{
		clientSocket = socket;
		pQueue = q;
		out = null;
		check = false;
	}
	
	void addToQueue(Notify n)
	{
		pQueue.add(n);
	}
	
	void init(Notify n) throws IOException
	{
		out = new PrintWriter(n.getSocket().getOutputStream(), true);
	}
	
	boolean isThreadRunning()
	{
		return check;
	}
	
	void checkQueue() throws ParseException, IOException, InterruptedException
	{
		while(true)
		{
			Notify tmp = pQueue.peek();
			if(tmp == null)
			{
				check = false;
				break;
			}
			
			SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
			Date inputDate = formatter.parse(tmp.getTime());
			Date date = new Date();
			int r = formatter.format(date).compareTo(formatter.format(inputDate));
			Thread.sleep(3000);
			
			if(r == 0)
			{
				init(tmp);
				//out.flush();
				out.println(tmp.getMessage());
				out.println(tmp.getTime());
				pQueue.poll();
				
			}
		}
	}
	
	public void run()
	{
		try
		{
			check = true;
			checkQueue();
		}
		catch(ParseException e1)
		{
			System.out.println("Błąd");
		}
		catch(IOException e2)
		{
			System.out.println("Błąd IO");
		}
		catch(InterruptedException e3)
		{
			System.out.println("Błąd");
		}
		// finally
		// {
			// try {
				//if(out != null) out.close();
				//clientSocket.close();
				check = false;
			// }
			// catch(IOException e1)
			// {
				// System.out.println("Błąd IO");
			// }
		// }
	}
	
	
}

class ServerHandler
{
	private int port;
	private ServerSocket server;
	private int numberOfClient;
	PriorityQueue<Notify> pQueue;
	
	ServerHandler(int p)
	{
		port = p;
		pQueue = new PriorityQueue<>();
		numberOfClient = 0;
		server = null;
	}
	
	void addNewClient()
	{
		numberOfClient++;
	}
	
	int getNumberOfClient()
	{
		return numberOfClient;
	}
	
	void serverLaunch()
	{
		try {
			server = new ServerSocket(port);
			while(true)
			{
				Socket client = server.accept();
				this.addNewClient();
				System.out.println("Nowy klient dołączył do serwera: " + "Klient " + this.getNumberOfClient());
				
				QueueHandler queueThread = new QueueHandler(client, pQueue);
				ClientHandler clientThread = new ClientHandler(client, this.getNumberOfClient(), queueThread);
				
				new Thread(clientThread).start();
			}
		}
		catch(IOException e)
		{
			System.out.println("Błąd IO po stronie serwera");
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
					System.out.println("Błąd IO po stronie serwera/ przy zamykaniu");
				}
			}
		}
	}
}

public class Server
{
	public static void main(String[] args)
	{
		
		System.out.println("Serwer - start");
		int port = 4900;
		ServerHandler var = new ServerHandler(port);
		var.serverLaunch();
		
	}
}

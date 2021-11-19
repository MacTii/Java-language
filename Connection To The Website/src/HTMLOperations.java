import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.nio.charset.MalformedInputException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.net.InetAddress;
import java.util.Map;
import java.util.HashMap;

public class HTMLOperations {
    private int numberOfURL = 0;
    private int numberOfEmails = 0;
    URLConnection createURL(String arg) throws MalformedInputException, IOException
    {
        URL myURL = new URL(arg); //https://stackoverflow.com/questions/8204680/java-regex-email
        URLConnection myURLConnection = myURL.openConnection();
        myURLConnection.connect();
        return myURLConnection;
    }

    PrintWriter createPrintWriter(String filename) throws FileNotFoundException
    {
        PrintWriter writer = new PrintWriter(filename);
        return writer;
    }

    BufferedReader createBufferedReader(URLConnection myURLConnection) throws IOException
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
        return in;
    }

    Pattern createPatternString(String regex)
    {
        Pattern pattern = Pattern.compile(regex);
        return pattern;
    }

    StringBuilder stringBuilderFunction(String txt)
    {
        StringBuilder sB = new StringBuilder(txt);
        return sB;
    }

    Matcher createMatcherString(String txt, Pattern pattern)
    {
        Matcher matcher = pattern.matcher(txt);
        return matcher;
    }

    void countURLs()
    {
        numberOfURL++;
    }

    void countEmails()
    {
        numberOfEmails++;
    }

    int getNumberOfURLs()
    {
        return numberOfURL;
    }

    int getNumberOfEmails()
    {
        return numberOfEmails;
    }

    void writeToFile(PrintWriter writer, Matcher matcher)
    {
        writer.println(matcher.group());
    }

    void getOutput(Matcher matcher)
    {
        System.out.println(matcher.group());
    }

    void writeIPAddress(PrintWriter writer) throws UnknownHostException
    {
        InetAddress addr = InetAddress.getLocalHost();

        //String hostname = addr.getHostName();
        //System.out.println("Local host name: "+hostname);

        writer.println(addr.getHostAddress());
        //System.out.println("Local HostAddress: " + addr.getHostAddress());
    }

    void writeToFileHead(PrintWriter writer, Matcher matcher)
    {
        writer.println(matcher.group().replaceAll("> ", ">\n"));
    }

    public String writeParameters(String arg)
    {
        String[] params = arg.split("&");
        if(params.length < 2)
            return null;
            
        Map<String, String> map = new HashMap<>();

        for (String param : params) {
            String name = param.split("=")[0];
            String value = param.split("=")[1];
            map.put(name, value);
        }

        return map.toString();
    }
}

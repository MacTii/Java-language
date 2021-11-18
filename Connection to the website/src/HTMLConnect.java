import java.net.*;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.BufferedReader;
import java.io.PrintWriter;

public class HTMLConnect {
    public static void main(String[] args) {
        try {
            HTMLOperations htmlop = new HTMLOperations();
            if(args.length!=1)
            {
                System.out.println("Wrong number of arguments");
                return;
            }
            URLConnection cnct = htmlop.createURL(args[0]);

            String filename = "kod_strony.txt";
            PrintWriter writer = htmlop.createPrintWriter(filename);

            BufferedReader in = htmlop.createBufferedReader(cnct);
            String inputLine = "";

            String urlRegex = "\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
            Pattern urlPattern = htmlop.createPatternString(urlRegex);

            String emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
            Pattern emailPattern = htmlop.createPatternString(emailRegex);

            String headRegex = "<head>(.+?)</head>";
            Pattern headPattern = htmlop.createPatternString(headRegex);

            PrintWriter urlWriter = htmlop.createPrintWriter("url.txt");
            PrintWriter emailWriter = htmlop.createPrintWriter("email.txt");
            PrintWriter headWriter = htmlop.createPrintWriter("head.txt");

            StringBuilder sB = htmlop.stringBuilderFunction(inputLine);

            while ((inputLine = in.readLine()) != null) {

                sB.append(inputLine);

                Matcher urlMatcher = htmlop.createMatcherString(inputLine, urlPattern);
                Matcher emailMatcher = htmlop.createMatcherString(inputLine, emailPattern);

                if (urlMatcher.find()) {
                    htmlop.countURLs();
                    htmlop.writeToFile(urlWriter, urlMatcher);
                    htmlop.getOutput(urlMatcher);
                }
                if (emailMatcher.find()) {
                    htmlop.countEmails();
                    htmlop.writeToFile(emailWriter, emailMatcher);
                    htmlop.getOutput(emailMatcher);
                }
                writer.println(inputLine);
            }
            inputLine = sB.toString();

            htmlop.writeIPAddress(headWriter);
            String put = htmlop.writeParameters(args[0]);
            headWriter.println(put);

            Matcher headMatcher = htmlop.createMatcherString(inputLine, headPattern);
            if (headMatcher.find())
                htmlop.writeToFileHead(headWriter, headMatcher);

            writer.close();
            in.close();

            urlWriter.close();
            emailWriter.close();
            headWriter.close();

            System.out.println(htmlop.getNumberOfURLs() + " - liczba linkow");
            System.out.println(htmlop.getNumberOfEmails() + " - liczba emaili");

        } catch (MalformedURLException e1) {
            System.out.println("New URL() failed");
        } catch (IOException e2) {
            System.out.println("openConnection() failed");
        }
    }
}

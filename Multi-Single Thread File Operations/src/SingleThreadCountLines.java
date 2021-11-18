import java.io.FileNotFoundException;
import java.util.Scanner;

class SingleThreadCountLines {
    public static void main(String[] args) {

        try {
            long startTime = System.currentTimeMillis();

            FileOperations fo = new FileOperations();
            int[] numberOfLines = new int[args.length];
            fo.parseToString(args);
            
            for(int i=0; i<args.length;i++)
            {
                Scanner reader;

                fo.createFileObject(args[i]);
                reader = fo.createScannerObject(args[i]);

                fo.countNumberOfLines();
                numberOfLines[i] = fo.getNumberOfLines();
                fo.resetNumberOfLines();

                reader.close();
            }

            System.out.println("Total number of lines in each file");
            for(int i : numberOfLines)
                System.out.println(i);
            
            //System.out.println(fo.getFilenames());

            long stopTime = System.currentTimeMillis();
            System.out.println("Total execution time is " + (stopTime - startTime) + " ms");     

        } catch (ArrayIndexOutOfBoundsException e1) {
            System.out.println("Wrong number of arguments");
        } catch (FileNotFoundException e2) {
            System.out.println("File corrupted");
        }
    }
}

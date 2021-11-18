import java.io.*;
import java.util.*;

public class FileOperations {

    private static String[] filename;
    private int numberOfLines = 0;
    private Scanner sc;

    public void parseToString(String args[]) throws ArrayIndexOutOfBoundsException {
        if (args.length < 1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int length = args.length;
        filename = new String[length];
        for (int i = 0; i < length; i++)
            filename[i] = args[i];
    }

    public String[] getFilenames() {
        return filename;
    }

    public int getNumberOfLines() {
        return numberOfLines;
    }

    public void resetNumberOfLines() {
        numberOfLines = 0;
    }

    public File createFileObject(String fname) {
        File fileObject = new File(fname);
        return fileObject;
    }

    public Scanner createScannerObject(String fname) throws FileNotFoundException {
        File file = this.createFileObject(fname);
        sc = new Scanner(file);
        return sc;
    }

    public void countNumberOfLines() {
        while (sc.hasNextLine()) {
            sc.nextLine();
            numberOfLines++;
        }
    }
}

class MultiThread implements Runnable {
    private String filename;
    FileOperations fo = new FileOperations();

    public MultiThread(String fname) {
        filename = fname;
    }

    @Override
    public void run() {
        // System.out.println("Total number of lines in each file");
        try {
            fo.createFileObject(filename);
            fo.createScannerObject(filename);
            fo.countNumberOfLines();
            System.out.println(fo.getNumberOfLines());
            fo.resetNumberOfLines();
        } catch (FileNotFoundException e) {
            System.out.println("File not founded");
        }

    }

}

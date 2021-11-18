import java.util.ArrayList;
import java.util.List;

public class MultiThreadCountLines {
    public static void main(String[] args) {
        try {

            long startTime = System.currentTimeMillis();
            //int[] numberOfLines = new int[args.length]; //useless
            //FileOperations fo = new FileOperations(); //useless
            List<Thread> threadList = new ArrayList<>();

            for (int i = 0; i < args.length; i++) {
                MultiThread mt = new MultiThread(args[i]);
                Thread t = new Thread(mt);
                t.start();
                threadList.add(t);
                //numberOfLines[i] = fo.getNumberOfLines(); //useless
                //fo.resetNumberOfLines(); //useless
            }

            System.out.println("Total number of lines in each file");
            for (int i = 0; i < threadList.size(); i++) {
                threadList.get(i).join();
            }
            //System.out.println("Total number of lines in each file"); //useless
            // for(int i : numberOfLines)
            // System.out.println(i);

            long stopTime = System.currentTimeMillis();
            System.out.println("Total execution time is " + (stopTime - startTime) + " ms");
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
        }
    }
}

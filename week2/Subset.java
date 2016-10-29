import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Random;

public class Subset {
   
   public static void main(String[] args) {
       RandomizedQueue<String> q = new RandomizedQueue<String>();
       int k = Integer.parseInt(args[0]);
       int count = k;
       while (!StdIn.isEmpty() && count > 0) {
           String str = StdIn.readString();
           q.enqueue(str);
       }
       while (k>0) {
           StdOut.println(q.dequeue());
           k --;
       }
       return;
     }
        
}

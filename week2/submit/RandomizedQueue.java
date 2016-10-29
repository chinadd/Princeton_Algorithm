import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class RandomizedQueue<Item> implements Iterable<Item> {
    
   private Item[] q;
   private int N = 0;
   private Random rand = new Random();
   private int first = 0;
   private int last = 0;
   
   public RandomizedQueue() {
       // construct an empty randomized queue
       q = (Item[]) new Object[2];

   }
   
   public boolean isEmpty() {
       // is the queue empty?
       return (N == 0);
   }
   
   public int size() {
       // return the number of items on the queue
       return N;
   }
   
   private void resize(int capacity) {
       Item[] temp = (Item[]) new Object[capacity];
       
       for (int i = 0 ; i < N; i++)
           temp[i] = q[i+first];
       
       q = temp;
       first = 0;
       last = N;
   }
       
   public void enqueue(Item item) { 
       // add the item
       if (item == null) throw new NullPointerException();
       if (N == q.length) resize(2*q.length);
       q[last++] = item;
       N += 1;
   }
   
   public Item dequeue() {
       
       // remove and return a random item
       if (isEmpty()) {
           throw new NoSuchElementException();
       }
       Random randomGenerator = new Random();
       int randomIndex = rand.nextInt(N);
       Item item = q[randomIndex];
       if (randomIndex == N-1) {
           q[randomIndex] = null;
       } else {
           q[randomIndex] = q[N-1];
           q[N-1] = null;
       }
       
       N--;
       last--;
       if (N > 0 && N <= q.length/4) resize(q.length/2);
       return item;
   } 
                        
   public Item sample()  {
       // return (but do not remove) a random item
       if (isEmpty()) {
           throw new NoSuchElementException();
       }
       Random randomGenerator = new Random();
       int randomIndex = rand.nextInt(N);
       Item item = q[randomIndex];
       return item;
   }
   
   
   public Iterator<Item> iterator() {
       // return an iterator over items in order from front to end
       return new ArrayIterator();
   }

   private class ArrayIterator  implements Iterator<Item>
   {
      private int i = 0;

      public boolean hasNext()
      {
         return i < N;
      }

      public Item next()
      {
         if (!hasNext()) throw new NoSuchElementException();
         Random randomGenerator = new Random();
         int randomIndex = rand.nextInt(N);
         Item item = q[(i+randomIndex)%q.length];
         i++;
         return item;
      }

      public void remove() { throw new UnsupportedOperationException(); }
   }
   public static void main(String[] args) {
       // unit testing
       RandomizedQueue<String> list = new RandomizedQueue <String>();
       //System.out.println(list.isEmpty());
       list.enqueue("p");
       //list.dequeue();
       list.enqueue("a");
       //System.out.println(list.size());
       list.enqueue("e");
       list.enqueue("h");
       
       System.out.println(list);
       
/*
       Iterator itr = list.iterator();
       while(itr.hasNext())
           System.out.print(itr.next() + " ");
       System.out.println();
 */      
       list.dequeue();
       System.out.println(list.size());
       for(Object x : list)
           System.out.print(x + " ");
       System.out.println();
   }
}
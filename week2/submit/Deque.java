import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
 
   private Node<Item> first,last,Nfirst, Nlast;
   private int N;
   
   public Deque() {
   // construct an empty deque
      first = null;
      last = null;
      N = 0;
   }
       
   public boolean isEmpty() {
       // is the deque empty?
       return (first == null);
   }
   
   public int size(){
       // return the number of items on the deque
       return N;
   }
   
   public void addFirst(Item item) {
       // add the item to the front
       if (item == null) {
           throw new java.lang.NullPointerException();
       } 
       
       Nfirst = new Node<Item>(item,first,null);
       if (first != null) first.prev = Nfirst;
       first = Nfirst;
       if (isEmpty()) first.next = null;
       N += 1;
       if (N==1) last = first;
   }
       
   public void addLast(Item item) {
       // add the item to the end
       
       if (first == null) {
           addFirst(item);
       } else if (item == null) {
           throw new java.lang.NullPointerException();
       } else {
           Node<Item> oldLast = last;
           Nlast = new Node<Item>(item,null,last);
           last = Nlast;
           if (isEmpty()) last.prev = null;
           else {
               oldLast.next = last;
           }
           
           N += 1;
           if (N==1) last = first;
       }     
   }
       
   public Item removeFirst() {
       // remove and return the item from the front
       if (isEmpty()){
           throw new NoSuchElementException();
       }
       else {
           Item item = first.data;
           
           if (N > 1) {         
               first = first.next;
               first.prev = null;
           } else {
               first = null;
               last = null;
           }
           N -= 1;
           return item;
       }
   }
           
   public Item removeLast() {
       // remove and return the item from the end
       if (isEmpty()) {
           throw new NoSuchElementException();
       } else {
           Item item = last.data;
           if (N >1) {
               last = last.prev;
               last.next = null;
           } else {
               last = null;
               first = null;
           }
           
           N -= 1;
           return item;
       }     
   }
   
   private static class Node<Item>
   {
      private Item data;
      private Node<Item> next;
      private Node<Item> prev;

      public Node(Item data, Node<Item> next, Node<Item> prev)
      {
         this.data = data;
         this.next = next;
         this.prev = prev;
      }
   }
       
   public Iterator<Item> iterator() {
       // return an iterator over items in order from front to end
       return new LinkedListIterator();
   }

   private class LinkedListIterator  implements Iterator<Item>
   {
      private Node<Item> nextNode;

      public LinkedListIterator()
      {
         nextNode = first;
      }

      public boolean hasNext()
      {
         return nextNode != null;
      }

      public Item next()
      {
         if (!hasNext()) throw new NoSuchElementException();
         Item res = nextNode.data;
         nextNode = nextNode.next;
         return res;
      }

      public void remove() { throw new UnsupportedOperationException(); }
   }
   
   public static void main(String[] args) {
       // unit testing
       Deque<String> list = new Deque <String>();
       
       System.out.println(list.isEmpty());
       
       list.addFirst("p");
       list.addFirst("a");
       list.addFirst("e");
       list.addFirst("h");
       list.addLast("s");
       list.removeFirst();
       System.out.println(list);
 
       /*
       Iterator itr = list.iterator();
       while(itr.hasNext())
           System.out.print(itr.next() + " ");
       System.out.println();
       */
       
       list.addLast("s");
       System.out.println(list.removeLast());
          
       for(Object x : list)
           System.out.print(x + " ");
       System.out.println();
       
   }
}
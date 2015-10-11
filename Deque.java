

import java.util.Iterator;
import java.util.NoSuchElementException;


public class Deque<Item> implements Iterable<Item> {
  private int N;            // size of deque
  private Node first;       // first of deque
  private Node last;        // last of deque
   // helper linked list class
   private class Node
   {
     private Item item;
     private Node next;
     private Node prev;
   }

   // construct an empty deque
   public Deque()
   {
     first = null;
     last = null;
     N = 0;
   }

   public boolean isEmpty()                 // is the deque empty?
   {
     return first == null;
   }

   public int size()                        // return the number of items on the deque
   {
     return N;
   }

   public void addFirst(Item item)          // add the item to the front
   {
     if (item == null) throw new NullPointerException();
     if (N == 0)
     {
       first = new Node();
       first.item = item;
       last = first;
       N++;

     }
     else
     {
       Node newfirst = new Node();
       newfirst.item = item;
       newfirst.next = first;
       first.prev = newfirst;
       first = newfirst;
       N++;
     }
     assert check();
   }
   public void addLast(Item item)           // add the item to the end
   {
     if (item == null) throw new NullPointerException();
     if (N == 0)
     {
       first = new Node();
       first.item = item;
       last = first;
       N++;

     }
     else
     {
       Node newlast = new Node();
       newlast.item = item;
       last.next = newlast;
       newlast.prev = last;
       last = newlast;
       N++;
     }
     assert check();

   }
   public Item removeFirst()                // remove and return the item from the front
   {
     if (isEmpty()) throw new NoSuchElementException("Stack underflow");
     Item item = first.item;
     first = first.next;
     if (first != null)
     {
       first.prev = null;
     }
     else
     {
       first = null;
       last = null;
     }
     N--;
     assert check();
     return item;

   }
   public Item removeLast()                 // remove and return the item from the end
   {
     if (isEmpty()) throw new NoSuchElementException("Stack underflow");
     Item item = last.item;
     last = last.prev;
     if (last != null)
     {
       last.next = null;
     }
     else
     {
       first = null;
       last = null;
     }
     N--;
     assert check();
     return item;
   }
   public Iterator<Item> iterator()         // return an iterator over items in order from front to end
   {
     return new ListIterator();
   }

   // an iterator, doesn't implement remove() since it's optional
   private class ListIterator implements Iterator<Item> {
       private Node current = first;
       public boolean hasNext()  { return current != null;                     }
       public void remove()      { throw new UnsupportedOperationException();  }

       public Item next() {
           if (!hasNext()) throw new NoSuchElementException();
           Item item = current.item;
           current = current.next;
           return item;
       }
   }

   private boolean check()
   {
     if (N == 0)
     {
       if (first != null) return false;
     }
     else if (N == 1)
     {
       if (first == null) return false;
       if (first.next != null) return false;
     }
     else {
       if (first.next == null) return false;
     }

     int numberOfNodes = 0;
     for (Node x = first; x != null; x = x.next)
     {
       numberOfNodes++;
     }
     if (numberOfNodes != N) return false;

     return true;

   }
   public static void main(String[] args)   // unit testing
   {
      Deque<String> s = new Deque<String>();
      /*
      while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) s.addFirst(item);
            else if (!s.isEmpty()) StdOut.print(s.removeFirst() + " ");
      }
      */
      s.addFirst("a");
      s.addFirst("b");
      s.addLast("c");
      s.addLast("d");
      StdOut.print(s.removeFirst() + " ");
      StdOut.print(s.removeLast() + " ");
      StdOut.print(s.removeFirst() + " ");
      StdOut.print(s.removeLast() + " ");
      // bacd -> bdac
      StdOut.println("(" + s.size() + " left on deque)");
   }
}

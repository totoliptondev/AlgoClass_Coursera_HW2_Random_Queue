
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
   private Item[] queue;
   private int N;
   public RandomizedQueue()                 // construct an empty randomized queue
   {
     queue = (Item[]) new Object[4];
   }
   public boolean isEmpty()                 // is the queue empty?
   {
     return N == 0;
   }
   public int size()                        // return the number of items on the queue
   {
     return N;
   }
   public void enqueue(Item item)           // add the item
   {
     if (item == null) throw new NullPointerException();
     if (N == queue.length) resize(2*queue.length);    // double size of array if necessary
     int randIndex = StdRandom.uniform(N+1);
     queue[N++] = queue[randIndex];
     queue[randIndex] = item;

   }

   public Item dequeue()                    // remove and return a random item
   {
     if (isEmpty()) throw new NoSuchElementException();
     int randIndex = StdRandom.uniform(N);
     Item item = queue[randIndex];
     queue[randIndex] = queue[--N];
     queue[N] = null;
     // shrink size of array if necessary
     if (N > 0 && N == queue.length/4) resize(queue.length/2);
     return item;
   }


   // resize the underlying array holding the elements
    private void resize(int capacity) {
        assert capacity >= N;
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            temp[i] = queue[i];
        }
        queue = temp;
    }

   public Item sample()                     // return (but do not remove) a random item
   {
     if (isEmpty()) throw new NoSuchElementException();
     return queue[StdRandom.uniform(N)];
   }
   public Iterator<Item> iterator()    // return an independent iterator over items in random order
   {
     return new ArrayIterator();
   }

   // an iterator, doesn't implement remove() since it's optional
   private class ArrayIterator implements Iterator<Item> {
       private int i;
       private int[] order;
       public ArrayIterator()
       {
          i = 0;
          order = new int[N];
          for (int j = 0; j < N; j++)
              order[j] = j;
          StdRandom.shuffle(order);
       }
       public boolean hasNext()  { return i != N;                     }
       public void remove()      { throw new UnsupportedOperationException();  }
       public Item next() {
           if (!hasNext()) throw new NoSuchElementException();
           return queue[order[i++]];
       }
   }

   public static void main(String[] args)   // unit testing
   {
     RandomizedQueue<Integer> s = new RandomizedQueue<Integer>();

     for (int i = 0; i < 10; i++)
     {
       s.enqueue(i);
     }
     StdOut.print(s.size() + " ");

     for (int i = 0; i < 20; i++)
     {
       StdOut.print(s.sample() + " ");
     }
   }
}

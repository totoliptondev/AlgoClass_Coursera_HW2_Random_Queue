import java.util.Iterator;

public class Subset {

   public static void main(String[] args)
   {
     RandomizedQueue<String> s = new RandomizedQueue<String>();
     int k = Integer.parseInt(args[0]);
     int totalN = 0;
     if (k == 0) return;
     double threshold = (k)/(k+1.0);
     double weight = 1.0/(k+1.0);
     String temp;
     while (!StdIn.isEmpty())
     {
           String item = StdIn.readString();
           //s.enqueue(item);

           if (s.size() > k)
           {
             double chance = StdRandom.uniform(0.0, 1.0);
             if (chance >= weight ) // 1 in k+1 chance, keep the new item
             {
               s.dequeue();
               s.enqueue(item);
             }
             else
             {
               // ignore the new item
             }
             weight *= threshold;


           }
           else
           {
             /*
             if (s.size() == k)
             {
               temp = item;
             }
             else
             {
             */
             s.enqueue(item);

           }

     }
     //StdOut.println("size is " + s.size());
     Iterator<String> rqIterator = s.iterator();
     for (int i = 0; i < k; i++)
     {
       if (rqIterator.hasNext())
       {
         StdOut.println(rqIterator.next());
       }
     }

   }
}

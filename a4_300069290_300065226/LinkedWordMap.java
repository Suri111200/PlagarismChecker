/**
 * An implementation of the interface WordMap using linked elements.
 *
 * @author Marcel Turcotte (marcel.turcotte@uottawa.ca)
 */

public class LinkedWordMap implements WordMap {

    private static class Node {

        private String key;
        private int counter;
        private Node previous;
        private Node next;

        private Node (String key, int counter, Node previous, Node next ) {
            this.key = key;
            this.counter = counter;
            this.previous = previous;
            this.next = next;
        }
    }

    // Instance variables

    private Node head;
    private Node tail;
    private int size;

    // Representation of the empty list.

    public LinkedWordMap() {
      // Your code here.
      head = new Node (null, 0, null, null);
      tail = new Node (null, 0, null, null);
      head.next = tail;
      head.previous = tail;
      tail.next = head;
      tail.previous = head;
      size = 0;
    }


    /**
     * Returns the logical size of this WordMap. That is the number of
     * associations currently stored in it.
     *
     * @return the logical size of this WordMap
     */
    
    public int size() {

        return size;
        
    }

    /**
     * Returns true if and only if this WordMap contains the specified
     * word.
     *
     * @param key the specified word
     * @return true if and only if this WordMap contains the specified word
     * @throws NullPointerException if the value of the parameter is null
     */

    public boolean contains(String key) {

        if (key == null)
        {
            throw new NullPointerException("key is null");
        }

        Node temp = tail.next;
        for (int i = 0; i < size; i++)
        {
            if (temp.key.equals(key))
            {
              return true;
            }
            temp = temp.next;
        }
        return false;
    }
    
    /**
     * Returns the count associated with the specified word or 0 if
     * the word is absent.
     *
     * @param key the specified word
     * @return the count associated with the specified word or 0 if absent
     * @throws NullPointerException if the value of the parameter is null
     */
    
    public int get(String key) {

        if (key == null)
        {
            throw new NullPointerException("key is null");
        }

        //System.out.println(!(this.contains(key)));

        if (!(this.contains(key)))
        {
            //System.out.println("does not contain");
            return 0;
        }

        Node temp = tail.next;
        int totalCount = 0;
        for (int i = 0; i < size; i++)
        {
            if (temp.key.equals(key))
            {
              //System.out.print(temp.key + " ");
             // System.out.print(key + " ");
             //System.out.println (temp.counter);
              totalCount = temp.counter;
              break;
            }
            temp = temp.next;
        }
        //System.out.println(totalCount);
        return totalCount;
        
    }
    
    /**
     * Increments by 1 the counter associated with the specified
     * word. If the specified word is absent from the data structure,
     * a new association is created.
     *
     * @param key the specified word
     * @throws NullPointerException if the value of the parameter is null
     */

    public void update(String key) {

        if (key == null)
        {
            throw new NullPointerException("key is null");
        }

        //System.out.println (key);
       // System.out.println(this.contains(key));

        Node temp; 

        if (this.contains(key))
        {
            temp = tail.next;
            for (int i = 0; i < size; i++)
            {
                if (temp.key.equals(key))
                {
                  temp.counter++;
                  break;
                }
                temp = temp.next;
            }
        }
        else
        {
            //System.out.println("else");
            if (size == 0)
            {

                temp = new Node (key, 1, tail, head);
                head.previous = temp;
                tail.next = temp;
            }
            else
            {
                Node pos = head.previous;
                while(true)
                {  
                      //System.out.print(o);
                      //System.out.println(o.compareTo(pos.value));
                      if (pos.key == null || key.compareTo(pos.key) >= 0) 
                      {
                        temp = new Node(key, 1, pos, pos.next);
                        pos.next = temp;
                        pos.next.next.previous = temp;
                        break;
                      }
                      pos = pos.previous;
                }
            }
            size++;
            //System.out.println("size:" + size);
        }




    }
    
    /**
     * Returns all the keys (words) of this WordMap using their
     * natural order.
     *
     * @return all the keys (words)
     */
    
    public String[] keys() {
        
        String [] keys = new String [size];

        Node temp = tail.next;
        for (int i = 0; i < size; i++)
        {
            keys[i] = temp.key;
            temp = temp.next;
        }
        return keys;
    }

    /**
     * Returns all the counts associated with keys in this
     * WordMap. The counts are in the same order as that of the keys
     * returned by the method keys().
     *
     * @return all the counts
     */
    
    public Integer[] counts() {
        
        Integer [] counts = new Integer [size];

        Node temp = tail.next;
        for (int i = 0; i < size; i++)
        {
            counts[i] = temp.counter;
            temp = temp.next;
        }
        return counts;
        
    }

    
}

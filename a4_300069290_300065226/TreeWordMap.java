/**
 * A Binary Search Tree implementation of the interface WordMap.
 *
 * @author Marcel Turcotte (marcel.turcotte@uottawa.ca)
 */

public class TreeWordMap implements WordMap {

    private static class Elem {

        private String key;
        private int count;
        private Elem left, right;

        private Elem(String key) {
            this.key = key;
            count = 1;
        }

    }

    private Elem root;
    private int size;
    private String [] keys;
    private Integer [] counts;

    /**
     * Returns true if and only if this WordMap contains the specified
     * word.
     *
     * @param key the specified word
     * @return true if and only if this WordMap contains the specified key
     * @throws NullPointerException if the value of the parameter is null
     */
    
    public boolean contains(String key) {

        
        if (key == null) {
            throw new NullPointerException();
        }
        
        boolean found = false;
        Elem current = root;
        while (! found && current != null) {
            int test = key.compareTo(current.key);
            if (test == 0) {
                found = true;
            } else if (test < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        //System.out.print(key);
       // System.out.println(found);
        return found;
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

        Elem temp; 

        if (this.contains(key))
        {
            temp = root;
            while (temp != null) {
                int test = key.compareTo(temp.key);
                if (test == 0) {
                    temp.count++;
                    break;
                } else if (test < 0) {
                    temp = temp.left;
                } else {
                    temp = temp.right;
                }
            }
        }
        else
        {
            //System.out.println("else");
            if (root == null)
                root = new Elem(key);
            else
                add (key, root);
            size++;
            //System.out.println("size:" + size);
        }

    }

    private void add (String key, Elem current)
    {
        int test = key.compareTo(current.key);

        if (test == 0) {
            return;
        } else if (test < 0) {
            if (current.left == null) {
                current.left = new Elem(key);
            } else {
                add(key, current.left);
            }
        } else {
            if (current.right == null) {
                current.right = new Elem(key);
            } else {
                add(key, current.right);
            }
        }

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

        if (key == null) {
            throw new NullPointerException();
        }
        
        boolean found = false;
        Elem current = root;
        while (current != null) {
            int test = key.compareTo(current.key);
            if (test == 0) {
                return current.count;
            } else if (test < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return 0;
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
     * Returns all the keys (words) of this WordMap using their
     * natural order.
     *
     * @return all the keys (words)
     */
    
    public String[] keys() { 

        keys = new String [size];

        inOrderTraversal ("key", root);

        return keys;
    }

    private void inOrderTraversal (String option, Elem current)
    {
        if (option.equals("key"))
        {
            if (current == null)
                return;

            inOrderTraversal("key", current.left);

            //System.out.print(current.key);
            for (int i = 0; i < keys.length; i++)
            {
                if (keys[i] == null)
                {
                    keys[i] = current.key;
                    break;
                }
            }
            
            inOrderTraversal("key", current.right);
        }
        else
        {
            if (current == null)
                return;

            inOrderTraversal("count", current.left);

            for (int i = 0; i < counts.length; i++)
            {
                if (counts[i] == null)
                {
                    counts[i] = current.count;
                    break;
                }
            }
      
            inOrderTraversal("count", current.right);
        }
    }
    
    /**
     * Returns all the counts associated with keys in this
     * WordMap. The counts are in the same order as that of the keys
     * returned by the method keys().
     *
     * @return all the counts
     */
    
    public Integer[] counts() {

        counts = new Integer [size];

        inOrderTraversal ("count", root);

        return counts;
        
    }

}

/**
 * An objet that reads and stores the content of file. It posses a
 * method that returns an iterator on the content (a String).
 *
 * @author Marcel Turcotte (marcel.turcotte@uottawa.ca)
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class WordReader {


    // The content of the file that was read
    
    private String content;

    /**
     * When an object of the class WordReader is created, this
     * constructor reads the content the file specified by the
     * parameter fileName.
     *
     * @param fileName the specified file
     * @throws FileNotFoundException if the file could not be found
     * @throws IOException if there is an error reading the content of the file
     */
    
    public WordReader(String fileName) throws FileNotFoundException, IOException {
        this(fileName, true);
    }
    
    /**
     * When an object of the class WordReader is created, this
     * constructor reads the content the file specified by the
     * parameter fileName.
     *
     * @param fileName the specified file
     * @param caseSensitive if the value is false, the content is transformed to lower case letters
     * @throws FileNotFoundException if the file could not be found
     * @throws IOException if there is an error reading the content of the file
     */
    
    public WordReader(String fileName, boolean caseSensitive) throws FileNotFoundException, IOException {

        BufferedReader reader;
        reader = new BufferedReader(new FileReader(fileName));

        StringBuilder buffer;
        buffer = new StringBuilder();

        String line;

        while ((line = reader.readLine()) != null) {
            if (! caseSensitive) {
                line = line.toLowerCase();
            }
            buffer.append(line);
        }
        
        reader.close();

        content = buffer.toString();
    }

    /**
     * Removes all the blank spaces from the content of the text.
     */
    
    public void removeAllBlankCharacters() {
        content = content.replaceAll("\\p{Blank}","");
    }

    /**
     * Returns an iterator over the content in the text.
     *
     * @param size the size of the n-grams to be returned by the method of the iterator
     * @return an iterator over the content in the text
     */

    String [] letters;
    public Iterator<String> iterator(int size) {
        letters = new String[content.length()];

        for (int i = 0; i < content.length(); i++)
            letters[i] = Character.toString(content.charAt(i));

        return (new WordReaderIterator(size));
    }


    private class WordReaderIterator implements Iterator<String> {
        
        int pos;
        int size;
        

        public WordReaderIterator (int size)
        {
            this.size = size;
            this.pos = 0;
        }

        public boolean hasNext()
        {
            //System.out.println("hi");
            //System.out.println(pos+(size-1) < letters.length);
            if (pos + (size-1) < letters.length)
                return true;
            return false;
        }

        public String next()
        {
            String s = "";

            for (int i = 0; i < size; i++)
            {
                s += letters[pos+i];
            }
            //System.out.println(pos);
            pos++;

            return s;
        }
        
    }

}




/**
 * @Author: Patryk Kamiński
 */

package onlineBookstoreServiceSystem.entities;

import onlineBookstoreServiceSystem.utils.ObjectPlusPlus;
import java.util.HashMap;
import java.util.Map;


/**
 * This class stores data about book copy.
 *
 * Linked with:
 * 1. Book with cardinality 0..* - 1
 * 2. Order with cardinality 1..* - 0..1
 *
 * @see Order
 * @see Book
 */
public class BookCopy extends ObjectPlusPlus
{
    private int numberISBN; // unique value
    private String language;
    private static Map<Integer, BookCopy> bookCopyMap = new HashMap<>(); // map for storing book copies with unique ISBN numbers

    /**
     * Book copy constructor
     *
     * @param numberISBN
     * @param language
     * @throws Exception
     */
    public BookCopy(int numberISBN, String language) throws Exception
    {
        setNumberISBN(numberISBN);
        this.language = language;
    }

    public int getNumberISBN() {
        return numberISBN;
    }

    // set unique value of ISBN number
    public void setNumberISBN(int numberISBN) throws Exception
    {
        if (!bookCopyMap.containsKey(numberISBN))
        {
            if (String.valueOf(numberISBN).length() == 10)
            {
                bookCopyMap.put(numberISBN, this);
                this.numberISBN = numberISBN;
            }
            else
                throw new Exception("ISBN number must have 10 digits!");
        }
        else
            throw new Exception("Book with this ISBN number is already exist!");
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString()
    {
        return getNumberISBN() + ", language: " + getLanguage();
    }

}
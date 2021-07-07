/**
 * @Author: Patryk Kamiński
 */

package s18610.unique;


import java.util.HashMap;
import java.util.Map;

public class Book
{
    private String title;
    private String author;
    private String language;
    private int numberISBN; // unique attribute

    private static Map<Integer, Book> bookMap = new HashMap<>();

    public Book(String title, String author, String language, int numberISBN) throws Exception
    {
        this.title = title;
        this.author = author;
        this.language = language;
        setNumberISBN(numberISBN);
    }

    public String getTitle()
    {
        return title;
    }
    public String getAuthor()
    {
        return author;
    }
    public String getLanguage()
    {
        return language;
    }
    public int getNumberISBN()
    {
        return numberISBN;
    }

    public void setNumberISBN(int numberISBN) throws Exception
    {
        if (!bookMap.containsKey(numberISBN))
        {
            if (this.numberISBN != 0)
                bookMap.remove(this.numberISBN);

            if (String.valueOf(numberISBN).length() == 10)
            {
                bookMap.put(numberISBN, this);
                this.numberISBN = numberISBN;
            }
            else
                throw new Exception("ISBN number must have 9 digits!");
        }
        else
            throw new Exception("Book with ISBN number: " + numberISBN + " already exists!");
    }

    @Override
    public String toString()
    {
        return getTitle() + ", author: " + getAuthor() + ", language: " + getLanguage() + ", ISBN number: " + getNumberISBN();
    }

}

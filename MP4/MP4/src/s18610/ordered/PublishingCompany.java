/**
 * @Author: Patryk Kamiński
 */

package s18610.ordered;

import java.util.Comparator;
import java.util.TreeSet;

public class PublishingCompany
{
    private String name;
    private String address;

    private TreeSet<Book> bookSet;
    private static Comparator<Book> bookComparator = Comparator.comparing(Book::getTitle);

    public PublishingCompany(String name, String address) throws Exception
    {
        setName(name);
        this.address = address;

        this.bookSet = new TreeSet<>(bookComparator);
    }

    public String getName()
    {
        return name;
    }
    public String getAddress()
    {
        return address;
    }
    public TreeSet<Book> getBookSet()
    {
        return bookSet;
    }


    public void setName(String name) throws Exception
    {
        if (name != null)
            this.name = name;
        else
            throw new Exception("Name cannot be null!");
    }

    public void changeName(String newName) throws Exception
    {
        if (!this.name.equals(newName))
            this.name = newName;
        else
            throw new Exception("Name must be different than previous name!");
    }

    public void addBook(Book newBook)
    {
        if (!bookSet.contains(newBook))
            bookSet.add(newBook);
    }

}

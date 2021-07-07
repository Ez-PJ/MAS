/**
 * @Author: Patryk Kamiński
 */

package onlineBookstoreServiceSystem.entities;

import onlineBookstoreServiceSystem.enums.Genre;
import onlineBookstoreServiceSystem.utils.ObjectPlusPlus;
import onlineBookstoreServiceSystem.users.Storekeeper;


/**
 * This class stores data about book.
 *
 * Linked with:
 * 1. Storekeeper with cardinality 1..* - 1
 * 2. BookCopy with cardinality 1 - 0..*
 *
 * @see Storekeeper
 * @see BookCopy
 */
public class Book extends ObjectPlusPlus
{
    private String title;
    private String author;
    private int quantity = 0; // quantity of book copies
    private int price;
    private Genre genre;

    /**
     * Book constructor
     *
     * @param title
     * @param author
     * @param price
     * @param genre
     * @throws Exception
     */
    public Book(String title, String author, int price, Genre genre) throws Exception
    {
        this.title = title;
        this.author = author;
        this.price = price;
        setGenre(genre);
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    public Genre getGenre() {
        return genre;
    }

    // set genre from available genres
    public void setGenre(Genre genre) throws Exception
    {
        if (genre.equals(Genre.Thriller) || genre.equals(Genre.Fantasy) || genre.equals(Genre.Romance) || genre.equals(Genre.Horror))
            this.genre = genre;
        else
            throw new Exception("There is no such genre!");
    }

    @Override
    public String toString()
    {
        return getTitle() + " ," + getAuthor() + "\n"
                + "quantity: " + getQuantity() + ", price: " + getPrice() + ", genre: " + getGenre();
    }

    /**
     * This method links book with book copy
     *
     * @param bookCopy
     */
    public void addLinkBookCopy(BookCopy bookCopy)
    {
        this.addLink("posiada","zawiera sie",  bookCopy);

        quantity += 1;
    }

}
/**
 * @Author: Patryk Kamiński
 */

package association;

import inheritance.Customer;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;


@Entity(name = "Book")
public class Book
{
    private long id;
    private String title;
    private String author;
    private String language;
    private int price;
    private Zamowienie zamowienie;

    /** Required by Hibernate **/
    public Book() {}

    public Book(String title, String author, String language, int price)
    {
        this.title = title;
        this.author = author;
        this.language = language;
        this.price = price;
    }

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(unique = true, nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Basic
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Basic
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    public Zamowienie getZamowienie() {
        return zamowienie;
    }

    public void setZamowienie(Zamowienie zamowienie) {
        this.zamowienie = zamowienie;
    }

    @Override
    public String toString()
    {
        return "Book: " +
                getTitle() + ", author: " + getAuthor() +
                ", language: " + getLanguage() + ", price: " + getPrice();
    }

}

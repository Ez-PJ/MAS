/**
 * @Author: Patryk Kamiński
 */

package association;

import inheritance.Customer;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity(name = "Zamowienie")
public class Zamowienie
{
    public enum Status {Paid, Sent, Completed}

    private long id;
    private String orderNumber;
    private LocalDate orderPlacementDate;
    private String deliveryAddress;
    private Status status;
    private List<Book> bookList = new ArrayList<>();
    private Customer customer;

    /** Required by Hibernate **/
    public Zamowienie() {}

    public Zamowienie(LocalDate orderPlacementDate, String deliveryAddress, Status status, List<Book> bookList) throws Exception
    {
        this.orderNumber = UUID.randomUUID().toString();
        this.orderPlacementDate = orderPlacementDate;
        this.deliveryAddress = deliveryAddress;
        setStatus(status);
        for (Book book : bookList)
        {
            this.bookList.add(book);
            book.setZamowienie(this);
        }
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
    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber)
    {
        this.orderNumber = orderNumber;
    }

    @Basic
    public LocalDate getOrderPlacementDate() {
        return orderPlacementDate;
    }

    public void setOrderPlacementDate(LocalDate orderPlacementDate) {
        this.orderPlacementDate = orderPlacementDate;
    }

    @Basic
    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    @Enumerated(EnumType.STRING)
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status newStatus) throws Exception
    {
        if (newStatus == Status.Paid || newStatus == Status.Sent || newStatus == Status.Completed)
            this.status = newStatus;
        else
            throw new Exception("Wrong status!");
    }

    @OneToMany(mappedBy = "zamowienie", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Book> getBookList()
    {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString()
    {
        String books = "";

        for (Book book : bookList)
            books += book.getTitle() + " ";


        return "Zamowienie: " +
                getOrderNumber() + ", placement date: " + getOrderPlacementDate() + ", delivery address: " + getDeliveryAddress() +
                ", order status: " + getStatus() + ", books: " + books;
    }

}

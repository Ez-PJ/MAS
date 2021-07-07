/**
 * @Author: Patryk Kamiński
 */

package inheritance;

import association.Book;
import association.Zamowienie;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity(name = "Customer")
public class Customer extends Person
{
    private long id;
    private String phoneNumber;
    private String email;
    private List<Zamowienie> orderList;

    /** Required by Hibernate **/
    public Customer() {}

    public Customer(String name, String surname, String address, String phoneNumber, String email)
    {
        super();
        setName(name);
        setSurname(surname);
        setAddress(address);

        this.phoneNumber = phoneNumber;
        this.email = email;
        this.orderList = new ArrayList<>();
    }

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Basic
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<Zamowienie> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Zamowienie> orderList) {
        this.orderList = orderList;
    }

    public void makeOrder(Zamowienie zamowienie)
    {
        if (!orderList.contains(zamowienie))
        {
            orderList.add(zamowienie);
            zamowienie.setCustomer(this);
        }
    }

    @Override
    public String toString() {

        String orders = "";

        for (Zamowienie zamowienie : orderList)
            orders += zamowienie.getOrderNumber() + " ";

        return "Customer: " +
                super.toString() +
                ", phone number: " + getPhoneNumber() + ", email: " + getEmail() + ", list of orders: " + orders;
    }

}

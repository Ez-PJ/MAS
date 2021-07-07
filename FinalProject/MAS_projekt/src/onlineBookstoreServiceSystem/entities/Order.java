/**
 * @Author: Patryk Kamiński
 */

package onlineBookstoreServiceSystem.entities;

import onlineBookstoreServiceSystem.enums.OrderStatus;
import onlineBookstoreServiceSystem.users.Person;
import onlineBookstoreServiceSystem.users.Support;
import onlineBookstoreServiceSystem.utils.ObjectPlusPlus;
import java.time.LocalDate;
import java.util.UUID;

/**
 * This class stores data about order.
 *
 * Linked with:
 * 1. Customer with cardinality 0..* - 0..1
 * 2. Payment with cardinality 1 - *
 * 3. Book copy with cardinality 0..1 - 1..*
 * 4. Delivery with cardinality 1 - *
 *
 * @see Person
 * @see Support
 */
public class Order extends ObjectPlusPlus
{
    private String orderNumber; // unique
    private LocalDate orderCreationDate;
    private int finalPrice;
    private OrderStatus orderStatus;

    /**
     * Order constructor
     *
     * @param orderCreationDate
     * @param orderStatus
     * @throws Exception
     */
    public Order(LocalDate orderCreationDate, OrderStatus orderStatus) throws Exception
    {
        this.orderNumber = UUID.randomUUID().toString();
        this.orderCreationDate = orderCreationDate;
        setFinalPrice(finalPrice);
        setOrderStatus(orderStatus);
    }

    public String getOrderNumber() {
        return orderNumber;
    }
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public LocalDate getOrderCreationDate() {
        return orderCreationDate;
    }
    public void setOrderCreationDate(LocalDate orderCreationDate) {
        this.orderCreationDate = orderCreationDate;
    }

    public int getFinalPrice() {
        return finalPrice;
    }
    public void setFinalPrice(int finalPrice) {
        this.finalPrice = finalPrice;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    // set order status from available order statuses
    public void setOrderStatus(OrderStatus orderStatus) throws Exception
    {
        if (orderStatus.equals(OrderStatus.Created) || orderStatus.equals(OrderStatus.Paid) || orderStatus.equals(OrderStatus.Sent) || orderStatus.equals(OrderStatus.Completed))
            this.orderStatus = orderStatus;
        else
            throw new Exception("There is no such order status!");
    }

    @Override
    public String toString()
    {
        return getOrderNumber() + ", creation date: " + getOrderCreationDate() + ", status: " + getOrderStatus();
    }

    /**
     * This method links order with book copy
     *
     * @param bookCopy
     */
    public void addLinkBookCopy(BookCopy bookCopy)
    {
        this.addLink("includes","is included",  bookCopy);
    }

    /**
     * This method links order with payment
     *
     * @param payment
     */
    public void addLinkPayment(Payment payment)
    {
        this.addLink("dotyczy", "zakonczone jest", payment);
    }

}
/**
 * @Author: Patryk Kamiński
 */

package onlineBookstoreServiceSystem.entities;

import onlineBookstoreServiceSystem.users.Customer;
import onlineBookstoreServiceSystem.utils.ObjectPlusPlus;
import java.time.LocalDate;


/**
 * This class stores data about payment.
 *
 * Linked with:
 * 1. Customer with cardinality * - 1
 * 2. Order with cardinality * - 1
 *
 * @see Customer
 * @see Order
 */
public class Payment extends ObjectPlusPlus
{
    private int price;
    private LocalDate paymentDate;

    /**
     * Payment constructor
     *
     * @param price
     * @param paymentDate
     */
    public Payment(int price, LocalDate paymentDate)
    {
        this.price = price;
        this.paymentDate = paymentDate;
    }

    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }
    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Override
    public String toString()
    {
        return getPrice() + ", payment Date: " + getPaymentDate();
    }

}
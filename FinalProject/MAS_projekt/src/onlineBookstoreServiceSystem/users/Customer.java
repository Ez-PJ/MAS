/**
 * @Author: Patryk Kamiński
 */

package onlineBookstoreServiceSystem.users;

import onlineBookstoreServiceSystem.entities.Address;
import onlineBookstoreServiceSystem.entities.Notification;
import onlineBookstoreServiceSystem.entities.Order;
import onlineBookstoreServiceSystem.entities.Payment;
import onlineBookstoreServiceSystem.enums.NotificationStatus;
import onlineBookstoreServiceSystem.enums.OrderStatus;
import onlineBookstoreServiceSystem.utils.ObjectPlusPlus;
import onlineBookstoreServiceSystem.entities.Delivery;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * This class stores data about customer.
 *
 * Inherits from Employee

 * Linked with:
 * 1. Notification with cardinality 1 - 0..*
 * 2. Credit Card with cardinality 1 - 0..1
 * 3. Payment with cardinality 1 - *
 *
 * @see Employee
 * @see Delivery
 * @see Payment
 */
public class Customer extends Person
{
    private String phoneNumber;
    private String emailAddress;
    private static Map<String, CreditCard> creditCardMap = new HashMap<>(); // map for storing credit cards with unique card numbers

    /**
     * Customer constructor
     *
     * @param name
     * @param surname
     * @param address
     * @param phoneNumber
     * @param emailAddress
     */
    public Customer(String name, String surname, Address address,
                    String phoneNumber, String emailAddress)
    {
        super(name, surname, address);
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString()
    {
        String personToString = super.toString();

        return personToString + "\n"
                + "phone number: " + getPhoneNumber() + ", email address: " + getEmailAddress();
    }

    /**
     * This method creates new order
     *
     * @param orderToCreate
     * @param localDate
     * @param orderStatus
     * @return newly created order
     * @throws Exception
     */
    public Order createOrder(Order orderToCreate, LocalDate localDate, OrderStatus orderStatus) throws Exception
    {
        orderToCreate = new Order(localDate, orderStatus);
        addLinkOrder(orderToCreate);
        return orderToCreate;
    }

    /**
     * This method creates new notification
     *
     * @param title
     * @param description
     * @return newly created notification
     */
    public Notification createNotification(String title, String description)
    {
        Notification notification = new Notification(title, description, LocalDate.now(), NotificationStatus.Opened);
        addLinkNotification(notification);
        return notification;
    }

    /**
     * This method links customer with selected order
     *
     * @param order
     */
    public void addLinkOrder(Order order)
    {
        this.addLink("sklada","jest skladane",  order);
    }

    /**
     * this method links customer with selected payment
     *
     * @param payment
     */
    public void addLinkPayment(Payment payment)
    {
        this.addLink("pays", "is paid", payment);
    }

    /**
     *
     * @param notification
     */
    public void addLinkNotification(Notification notification)
    {
        this.addLink("opens", "is opened", notification);
    }

    /**
     * This method adds credit card
     *
     * @param cardNumber
     * @param codeCVC
     * @param expirationDate
     * @throws Exception
     */
    public void addCreditCard(String cardNumber, int codeCVC, String expirationDate) throws Exception
    {
        CreditCard creditCard = new CreditCard(cardNumber, codeCVC, expirationDate);
        this.addPart("has got", "is in possesion", creditCard);
    }

    /**
     * This method checks if customer has got credit card or not
     *
     * @return true when user has got credit card or false, when not
     */
    public boolean hasCreditCard()
    {
        if (!this.isLink("has got"))
            return true;
        else
            return false;
    }

    /**
     * This method returns credit card's number
     *
     * @return credit card number
     * @throws Exception
     */
    public String returnCreditCardNumber() throws Exception
    {
        ArrayList<CreditCard> creditCards = returnLinks("has got");

        return creditCards.get(0).getCardNumber();
    }

    /**
     * This method returns credit card's CVC code
     *
     * @return credit card CVC code
     * @throws Exception
     */
    public int returnCreditCardCodeCVC() throws Exception
    {
        ArrayList<CreditCard> creditCards = returnLinks("has got");

        return creditCards.get(0).getCodeCVC();
    }

    /**
     * This method returns credit card's expiration date
     *
     * @return credit card expiration date
     * @throws Exception
     */
    public String returnCreditCardExpirationDate() throws Exception
    {
        ArrayList<CreditCard> creditCards = returnLinks("has got");

        return creditCards.get(0).getExpirationDate();
    }

    /**
     * This class stored data about credit card.
     * The class inner class of customer.
     *
     * Linked with:
     * 1. Customer with cardinality 0..1 - 1
     */
    class CreditCard extends ObjectPlusPlus
    {
        private String cardNumber; // unique
        private int codeCVC;
        private String expirationDate;

        private CreditCard(String cardNumber, int codeCVC, String expirationDate)
        {
            this.cardNumber = cardNumber;
            this.codeCVC = codeCVC;
            this.expirationDate = expirationDate;
        }

        public String getCardNumber() {
            return cardNumber;
        }

        // set unique value of credit card number
        public void setCardNumber(String cardNumber) throws Exception
        {
            if (!creditCardMap.containsKey(cardNumber))
            {
                if (String.valueOf(cardNumber).length() == 16)
                {
                    creditCardMap.put(cardNumber, this);
                    this.cardNumber = cardNumber;
                }
                else
                    throw new Exception("card number must have 16 digits!");
            }
            else
                throw new Exception("Card with this number already exists!");
        }

        public int getCodeCVC() {
            return codeCVC;
        }
        public void setCodeCVC(int codeCVC) {
            this.codeCVC = codeCVC;
        }

        public String getExpirationDate() {
            return expirationDate;
        }
        public void setExpirationDate(String expirationDate) {
            this.expirationDate = expirationDate;
        }

        @Override
        public String toString()
        {
            return cardNumber + ", CVC: " + codeCVC + ", expiration date: " + expirationDate;
        }
    }

}
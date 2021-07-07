/**
 * @Author: Patryk Kamiński
 */

package onlineBookstoreServiceSystem.users;

import onlineBookstoreServiceSystem.entities.Address;
import onlineBookstoreServiceSystem.entities.Notification;
import onlineBookstoreServiceSystem.enums.FormOfEmployment;
import onlineBookstoreServiceSystem.enums.NotificationStatus;
import java.time.LocalDate;


/**
 * This class stores data about support worker.
 * Support worker can support notifications created by customer
 *
 * Inherits from Employee
 *
 * Linked with:
 * 1. Notification with cardinality 1 - 0..*
 *
 * @see Employee
 * @see onlineBookstoreServiceSystem.entities.Notification
 */
public class Support extends Employee
{
    /**
     * Support worker constructor
     *
     * @param name
     * @param surname
     * @param address
     * @param employmentDate
     * @param baseSalary
     * @param formOfEmployment
     * @throws Exception
     */
    public Support(String name, String surname, Address address,
                   LocalDate employmentDate, int baseSalary, FormOfEmployment formOfEmployment) throws Exception
    {
        super(name, surname, address, employmentDate, baseSalary, formOfEmployment);
    }

    @Override
    public String toString()
    {
        return super.toString();
    }

    /**
     * This method proceeds selected order
     *
     * @param notification
     * @throws Exception
     */
    public void proceedNotification(Notification notification) throws Exception
    {
        if (notification.getNotificationStatus().equals(NotificationStatus.Opened))
        {
            // TODO: notification proceed functionality

            System.out.println("Notification: " + notification.getTitle() + " has been proceeded");
            notification.setNotificationStatus(NotificationStatus.Closed);
        }
        else
            throw new Exception("This notification has been already closed!");
    }

}
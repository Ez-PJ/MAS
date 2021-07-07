/**
 * @Author: Patryk Kamiński
 */

package onlineBookstoreServiceSystem.entities;

import onlineBookstoreServiceSystem.enums.NotificationStatus;
import onlineBookstoreServiceSystem.users.Customer;
import onlineBookstoreServiceSystem.users.Support;
import onlineBookstoreServiceSystem.utils.ObjectPlusPlus;
import java.time.LocalDate;


/**
 * This class stores data about notification.
 *
 * Linked with:
 * 1. Customer with cardinality 0..* - 1
 * 2. Support with cardinality 0..* - 1
 *
 * @see Customer
 * @see Support
 */
public class Notification extends ObjectPlusPlus
{
    private String title;
    private String description;
    private LocalDate creationDate;
    private NotificationStatus notificationStatus;

    /**
     * Notification constructor
     *
     * @param title
     * @param description
     * @param creationDate
     * @param notificationStatus
     */
    public Notification(String title, String description, LocalDate creationDate, NotificationStatus notificationStatus)
    {
        this.title = title;
        this.description = description;
        this.creationDate = creationDate;
        this.notificationStatus = notificationStatus;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public NotificationStatus getNotificationStatus() {
        return notificationStatus;
    }
    public void setNotificationStatus(NotificationStatus notificationStatus) {
        this.notificationStatus = notificationStatus;
    }

    @Override
    public String toString()
    {
        return "Title: " + getTitle() + ", created at: " + getCreationDate() + ", status: " + getNotificationStatus() + "\n"
                + getDescription();
    }

}
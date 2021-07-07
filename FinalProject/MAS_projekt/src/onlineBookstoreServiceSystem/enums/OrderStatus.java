/**
 * @Author: Patryk Kamiński
 */

package onlineBookstoreServiceSystem.enums;

import onlineBookstoreServiceSystem.entities.Order;


/**
 * Enum to set Order class status
 *
 * @see Order
 */
public enum OrderStatus
{
    Created,
    Paid,
    Sent,
    Completed
}

/**
 * @Author: Patryk Kamiński
 */

package s18610.subset;

import s18610.utils.ObjectPlus4;


public class Order extends ObjectPlus4
{
    private String orderNumber;

    public Order(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderNumber()
    {
        return orderNumber;
    }

    public static final String isPrepared = "is prepared by";
    public static final String isProceeded = "is proceeded by";

    @Override
    public String toString()
    {
        return getOrderNumber();
    }

}

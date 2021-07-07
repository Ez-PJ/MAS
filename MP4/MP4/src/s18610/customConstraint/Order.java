/**
 * @Author: Patryk Kamiński
 */

package s18610.customConstraint;

public class Order
{
    private int orderNumber;
    private String status;

    public Order(int orderNumber, String status) throws Exception
    {
        this.orderNumber = orderNumber;
        setStatus(status);
    }

    public int getOrderNumber()
    {
        return orderNumber;
    }
    public String getStatus()
    {
        return status;
    }


    public void setStatus(String status) throws Exception
    {
        if (status.equals(OrderStatus.Paid.toString()) || status.equals(OrderStatus.Sent.toString()) ||
                status.equals(OrderStatus.Delivered.toString()) || status.equals(OrderStatus.Collected.toString()))
            this.status = status;
        else
            throw new Exception("Wrong status!");
    }

    public void changeStatus(String newStatus) throws Exception
    {
        if (newStatus.equals(OrderStatus.Paid.toString()) || newStatus.equals(OrderStatus.Sent.toString()) ||
                newStatus.equals(OrderStatus.Delivered.toString()) || newStatus.equals(OrderStatus.Collected.toString()))
        {
            if (!this.status.equals(newStatus))
            {
                this.status = newStatus;
            }
            else
                throw new Exception("New status must be different from previous status!");
        }
        else
            throw new Exception("Wrong status!");

    }

    @Override
    public String toString()
    {
        return "Order: " + getOrderNumber() + " status: " + getStatus();
    }

}

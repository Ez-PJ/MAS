/**
 * @Author: Patryk Kamiński
 */

package s18610.bag;

import java.util.ArrayList;
import java.util.List;

public class Clinic
{
    private String name;
    private String address;

    private List<Visit> visitList;

    public Clinic(String name, String address)
    {
        this.name = name;
        this.address = address;

        this.visitList = new ArrayList<>();
    }

    public String getName()
    {
        return name;
    }
    public String getAddress()
    {
        return address;
    }


    public void addVisit(Visit newVisit)
    {
        if (newVisit != null && newVisit.getClinic() == this)
            visitList.add(newVisit);
    }

    @Override
    public String toString()
    {
        return getName() + ", address: " + getAddress();
    }

}



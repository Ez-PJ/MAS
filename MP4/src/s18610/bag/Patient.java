/**
 * @Author: Patryk Kamiński
 */

package s18610.bag;

import java.util.ArrayList;
import java.util.List;


public class Patient
{
    private String name;
    private String surname;
    private int age;
    private String phoneNumber;

    private List<Visit> visitList;

    public Patient(String name, String surname, int age, String phoneNumber)
    {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.phoneNumber = phoneNumber;

        this.visitList = new ArrayList<>();
    }

    public String getName()
    {
        return name;
    }
    public String getSurname()
    {
        return surname;
    }
    public int getAge()
    {
        return age;
    }
    public String getPhoneNumber()
    {
        return phoneNumber;
    }


    public void addVisit(Visit newVisit)
    {
        if (newVisit != null && newVisit.getPatient() == this)
            visitList.add(newVisit);
    }

    @Override
    public String toString()
    {
        return getName() + " " + getSurname() + ", age: " + getAge() + ", phone number: " + getPhoneNumber();
    }

}

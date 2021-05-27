/**
 * @Author: Patryk Kamiński
 */

package s18610.attribute;

public class Customer
{
    private String name;
    private String surname;
    private int age;

    public Customer(String name, String surname, int age) throws Exception
    {
        this.name = name;
        this.surname = surname;

        setAge(age);
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


    public void setAge(int age) throws Exception
    {
        int legalAge = 18;

        if (age >= legalAge)
            this.age = age;
        else
            throw new Exception("Customer has to be at least 18 years old to buy drinks!");
    }

    public void buyDrink()
    {
        if (this.age < 18)
            System.out.println("Customer " + getName() + " " + getSurname() + " is not old enough to buy drinks!");
        else
            System.out.println("Customer " + getName() + " " + getSurname() + " is allowed to buy drinks");
    }

    @Override
    public String toString()
    {
        return getName() + " " + getSurname() + ", age: " + getAge();
    }

}


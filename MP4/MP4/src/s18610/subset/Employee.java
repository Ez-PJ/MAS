/**
 * @Author: Patryk Kamiński
 */

package s18610.subset;

import s18610.utils.ObjectPlus4;


public class Employee extends ObjectPlus4
{
    private String name;
    private String surname;

    public Employee(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName()
    {
        return name;
    }
    public String getSurname()
    {
        return surname;
    }

    public static final String prepares = "prepares";
    public static final String proceeds = "proceeds";

    @Override
    public String toString()
    {
        return getName() + " " + getSurname();
    }
}

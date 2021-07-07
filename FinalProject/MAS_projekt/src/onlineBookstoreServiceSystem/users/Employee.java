/**
 * @Author: Patryk Kamiński
 */

package onlineBookstoreServiceSystem.users;

import onlineBookstoreServiceSystem.entities.Address;
import onlineBookstoreServiceSystem.enums.FormOfEmployment;
import java.time.LocalDate;


/**
 * This is abstract class, which stores data about employee.
 * Class created to inherit data from here.
 *
 * Inherits from Person
 *
 * @see Person
 */
public abstract class Employee extends Person
{
    private LocalDate employmentDate;
    private int baseSalary;
    private FormOfEmployment formOfEmployment;

    /**
     * Employee constructor
     *
     * @param name
     * @param surname
     * @param address
     * @param employmentDate
     * @param baseSalary
     * @param formOfEmployment
     * @throws Exception
     */
    public Employee(String name, String surname, Address address,
                    LocalDate employmentDate, int baseSalary, FormOfEmployment formOfEmployment) throws Exception
    {
        super(name, surname, address);
        this.employmentDate = employmentDate;
        this.baseSalary = baseSalary;
        setFormOfEmployment(formOfEmployment);
    }

    public LocalDate getEmploymentDate() {
        return employmentDate;
    }
    public void setEmploymentDate(LocalDate employmentDate) {
        this.employmentDate = employmentDate;
    }

    public int getBaseSalary() {
        return baseSalary;
    }
    public void setBaseSalary(int baseSalary) {
        this.baseSalary = baseSalary;
    }

    public FormOfEmployment getFormOfEmployment() {
        return formOfEmployment;
    }

    // set form of employment from available forms of employment
    public void setFormOfEmployment(FormOfEmployment formOfEmployment) throws Exception
    {
        if (formOfEmployment.equals(FormOfEmployment.UOP) || formOfEmployment.equals(FormOfEmployment.UZ))
            this.formOfEmployment = formOfEmployment;
        else
            throw new Exception("There is no such form of employment!");
    }

    @Override
    public String toString()
    {
        String personToString = super.toString();

        return personToString + "\n"
                + "hired in: " + getEmploymentDate() + ", salary: " + getBaseSalary() + ", form of employment: " + getFormOfEmployment();
    }

    /**
     * This method calculates employee's final salary
     *
     * @return salary
     */
    public int calculateSalary()
    {
        return getBaseSalary();
    }

}

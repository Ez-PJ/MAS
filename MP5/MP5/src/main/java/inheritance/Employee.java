/**
 * @Author: Patryk Kamiński
 */

package inheritance;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.time.LocalDate;


@Entity(name = "Employee")
public class Employee extends Person
{
    private long id;
    private LocalDate employmentDate;
    private int baseSalary;

    /** Required by Hibernate **/
    public Employee() {}

    public Employee(String name, String surname, String address, LocalDate employmentDate, int baseSalary)
    {
        super();
        setName(name);
        setSurname(surname);
        setAddress(address);

        this.employmentDate = employmentDate;
        this.baseSalary = baseSalary;
    }

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    public LocalDate getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(LocalDate employmentDate) {
        this.employmentDate = employmentDate;
    }

    @Basic
    public int getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(int baseSalary) {
        this.baseSalary = baseSalary;
    }

    @Override
    public String toString() {
        return "Employee: " +
                super.toString() +
                ", employment date: " + getEmploymentDate() + ", base salary: " + getBaseSalary();
    }

}

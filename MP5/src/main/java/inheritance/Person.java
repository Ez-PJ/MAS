 /**
 * @Author: Patryk Kamiński
 */

package inheritance;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;


@Entity(name = "Person")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Person
{
    private long id;
    private String name;
    private String surname;
    private String address;

    /** Required by Hibernate **/
    public Person() {}

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
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString()
    {
        return getName() + " " + getSurname() + ", address: " + getAddress();
    }

}

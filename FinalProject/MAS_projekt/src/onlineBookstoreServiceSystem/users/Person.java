/**
 * @Author: Patryk Kamiński
 */

package onlineBookstoreServiceSystem.users;

import onlineBookstoreServiceSystem.entities.Address;
import onlineBookstoreServiceSystem.utils.ObjectPlusPlus;


/**
 * This is abstract class, which stores data about person.
 * Class created to inherit data from here.
 *
 * Linked with:
 * 1. Address with cardinality 1 - 1
 *
 * @see Address
 */
public abstract class Person extends ObjectPlusPlus
{
    private String name;
    private String surname;
    private Address address;

    public Person(String name, String surname, Address address)
    {
        this.name = name;
        this.surname = surname;
        this.address = address;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString()
    {
        return getName() + " " + getSurname() + "\n"
                + "Address: " + getAddress().toString();
    }

    /**
     * This method changes person's current address
     *
     * @param street
     * @param buildingNumber
     * @param zipCode
     * @param city
     * @param voivodeship
     */
    public void changeAddress(String street, String buildingNumber, String zipCode, String city, String voivodeship)
    {
        this.address.changeAddress(street, buildingNumber, zipCode, city, voivodeship);
    }

}

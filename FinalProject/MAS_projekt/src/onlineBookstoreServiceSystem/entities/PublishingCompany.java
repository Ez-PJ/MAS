/**
 * @Author: Patryk Kamiński
 */

package onlineBookstoreServiceSystem.entities;

import onlineBookstoreServiceSystem.utils.ObjectPlusPlus;


/**
 * This class stores data about publishing company.
 *
 * Linked with:
 * 1. Book copy with cardinality 1 - 1..*
 *
 * @see BookCopy
 */
public class PublishingCompany extends ObjectPlusPlus
{
    private String name;
    private Address address;

    /**
     * Publishing company constructor
     *
     * @param name
     * @param address
     */
    public PublishingCompany(String name, Address address)
    {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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
        return getName() + "\n"
                + "Address: " + getAddress().toString();
    }

}
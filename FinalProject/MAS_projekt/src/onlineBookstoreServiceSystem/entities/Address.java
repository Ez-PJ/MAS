/**
 * @Author: Patryk Kamiński
 */

package onlineBookstoreServiceSystem.entities;

import onlineBookstoreServiceSystem.utils.ObjectPlusPlus;
import onlineBookstoreServiceSystem.users.Person;


/**
 * This class stores data about address.
 *
 * Linked with:
 * 1. Person with cardinality 1 - 1
 * 2. Delivery with cardinality 1 - 1
 *
 * @see Person
 * @see Delivery
 */
public class Address extends ObjectPlusPlus
{
    private String street;
    private String buildingNumber;
    private String zipCode;
    private String city;
    private String voivodeship;

    /**
     * Address constructor
     *
     * @param street
     * @param buildingNumber
     * @param zipCode
     * @param city
     * @param voivodeship
     */
    public Address(String street, String buildingNumber, String zipCode, String city, String voivodeship)
    {
        this.street = street;
        this.buildingNumber = buildingNumber;
        this.zipCode = zipCode;
        this.city = city;
        this.voivodeship = voivodeship;
    }

    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }
    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getZipCode() {
        return zipCode;
    }
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getVoivodeship() {
        return voivodeship;
    }
    public void setVoivodeship(String voivodeship) {
        this.voivodeship = voivodeship;
    }

    @Override
    public String toString()
    {
        return getStreet() + " " + getBuildingNumber() + ", " + getZipCode() + ", " + getCity() + ", " + getVoivodeship();
    }

    /**
     * This method changes current address
     *
     * @param street
     * @param buildingNumber
     * @param zipCode
     * @param city
     * @param voivodeship
     */
    public void changeAddress(String street, String buildingNumber, String zipCode, String city, String voivodeship)
    {
        setStreet(street);
        setBuildingNumber(buildingNumber);
        setZipCode(zipCode);
        setCity(city);
        setVoivodeship(voivodeship);
    }

}
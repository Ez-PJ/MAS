/**
 * @Author: Patryk Kamiński
 */

package onlineBookstoreServiceSystem.users;

import onlineBookstoreServiceSystem.entities.Address;
import onlineBookstoreServiceSystem.entities.Order;
import onlineBookstoreServiceSystem.enums.FormOfEmployment;
import onlineBookstoreServiceSystem.entities.Delivery;
import java.time.LocalDate;
import java.util.List;


/**
 * This class stores data about salesman.
 *
 * Inherits from Employee
 *
 * Linked with:
 * 1. Delivery with cardinality 1 - *
 *
 * @see Employee
 * @see Delivery
 */
public class Salesman extends Employee
{
    private List<String> certificates;

    /**
     * Salesman constructor
     *
     * @param name
     * @param surname
     * @param address
     * @param employmentDate
     * @param baseSalary
     * @param formOfEmployment
     * @param certificates
     * @throws Exception
     */
    public Salesman(String name, String surname, Address address,
                    LocalDate employmentDate, int baseSalary, FormOfEmployment formOfEmployment,
                    List<String> certificates) throws Exception
    {
        super(name, surname, address, employmentDate, baseSalary, formOfEmployment);
        this.certificates = certificates;
    }

    public List<String> getCertificates() {
        return certificates;
    }
    public void setCertificates(List<String> certificates) {
        this.certificates = certificates;
    }

    @Override
    public String toString()
    {
        String employeeToString = super.toString();

        return employeeToString + "\n"
                + "certificates: " + getCertificates();
    }

    /**
     * This method adds certificate to the certificates list
     *
     * @param newCertificate
     * @throws Exception
     */
    public void addCertificate(String newCertificate) throws Exception
    {
        if (!certificates.contains(newCertificate))
            certificates.add(newCertificate);
        else
            throw new Exception("This Salesman has already got such certificate!");
    }

    /**
     * This method proceeds selected order
     *
     * @param order
     */
    public void proceedOrder(Order order)
    {

    }

    /**
     * TThis method calculates employee's final salary
     *
     * @return salary with all bonuses
     */
    @Override
    public int calculateSalary()
    {
        int bonusToSalary = getCertificates().size() * 200;
        return getBaseSalary() + bonusToSalary;
    }

}
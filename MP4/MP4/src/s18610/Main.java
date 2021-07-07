package s18610;

import s18610.attribute.Customer;
import s18610.bag.Clinic;
import s18610.bag.Patient;
import s18610.bag.Visit;
import s18610.ordered.PublishingCompany;
import s18610.subset.Employee;
import static s18610.subset.Employee.*;
import static s18610.subset.Order.*;


public class Main
{

    public static void main(String[] args) throws Exception
    {
        // attribute constraint example
        System.out.println("ATTRIBUTE CONSTRAINT EXAMPLE: ");
        attributeConstraintExample();
        System.out.println();

        // unique constraint example
        System.out.println("UNIQUE CONSTRAINT EXAMPLE: ");
        uniqueConstraintExample();
        System.out.println();

        // subset constraint example
        System.out.println("SUBSET CONSTRAINT EXAMPLE:");
        subsetConstraintExample();
        System.out.println();

        // ordered constraint example
        System.out.println("ORDERED CONSTRAINT EXAMPLE: ");
        orderedConstraintExample();
        System.out.println();

        //bag constraint example
        System.out.println("BAG CONSTRAINT EXAMPLE: ");
        bagConstraintExample();
        System.out.println();

        //XOR constraint example
        System.out.println("XOR CONSTRAINT EXAMPLE: ");
        xorConstraintExample();
        System.out.println();

        //custom constraint example
        System.out.println("CUSTOM CONSTRAINT EXAMPLE: ");
        customConstraintExample();
        System.out.println();
    }

    public static void attributeConstraintExample() throws Exception
    {
        Customer customer1 = new Customer("Stachu", "Jones", 65);
        Customer customer2 = new Customer("Jurek", "Kiler", 18);

        System.out.println("Customer 1: " + customer1);
        customer1.buyDrink();
        System.out.println();

        System.out.println("Customer 2: " + customer2);
        customer2.buyDrink();
        System.out.println();
    }

    public static void uniqueConstraintExample() throws Exception
    {
        s18610.unique.Book book1 = new s18610.unique.Book("Harry Potter: the Philosopher's Stone", "J. K. Rowling", "English", 1234567890);
        s18610.unique.Book book2 = new s18610.unique.Book("Harry Potter: the Chamber of Secrets", "J. K. Rowling", "English", 1234567891);

        System.out.println("Book 1: " + book1);
        System.out.println("Book 2: " + book2);
        System.out.println();

        book1.setNumberISBN(1111111111);
        System.out.println("Book 1: " + book1);

        book2.setNumberISBN(1234567890);
        System.out.println("Book 2: " + book2);
        System.out.println();
    }

    public static void subsetConstraintExample() throws Exception {
        Employee employee1 = new Employee("Stachu", "Jones");
        s18610.subset.Order order1 = new s18610.subset.Order("SKU1234");

        employee1.addLink(prepares, isPrepared, order1);
        employee1.addLink_subset(proceeds, isProceeded, prepares, order1);

        employee1.showLinks(proceeds, System.out);
        employee1.showLinks(prepares, System.out);
        System.out.println();
    }

    public static void orderedConstraintExample() throws Exception
    {
        s18610.ordered.Book book1 = new s18610.ordered.Book("Harry Potter: the Philosopher's Stone", "J. K. Rowling");
        s18610.ordered.Book book2 = new s18610.ordered.Book("Harry Potter: the Chamber of Secrets", "J. K. Rowling");
        s18610.ordered.Book book3 = new s18610.ordered.Book("Harry Potter: the Goblet of Fire", "J. K. Rowling");

        PublishingCompany publishingCompany1 = new PublishingCompany("Best Books", "Popękanych Bębenków 14A");

        publishingCompany1.addBook(book1);
        publishingCompany1.addBook(book2);
        publishingCompany1.addBook(book3);

        System.out.println(publishingCompany1.getBookSet());
        System.out.println();
    }

    public static void bagConstraintExample()
    {
        Clinic clinic = new Clinic("Medix", "Popękanych Bębenków 27A");
        Patient patient = new Patient("Stachu", "Jones", 65, "123 456 789");

        Visit visit1 = new Visit("11.05.2021", patient, clinic);
        Visit visit2 = new Visit("21.04.2021", patient, clinic);

        System.out.println(visit1);
        System.out.println();

        System.out.println(visit2);
        System.out.println();
    }

    public static void xorConstraintExample()
    {
        // TODO: implement
        System.out.println();
    }

    public static void customConstraintExample() throws Exception
    {
        s18610.customConstraint.Order order1 = new s18610.customConstraint.Order(123456, "Paid");
        System.out.println(order1);
        System.out.println();

        order1.changeStatus("Sent");
        System.out.println(order1);
        System.out.println();
    }

}

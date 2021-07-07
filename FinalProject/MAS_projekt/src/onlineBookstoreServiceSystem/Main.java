/**
 * @Author: Patryk Kamiński
 */

package onlineBookstoreServiceSystem;

import onlineBookstoreServiceSystem.entities.*;
import onlineBookstoreServiceSystem.enums.FormOfEmployment;
import onlineBookstoreServiceSystem.enums.Genre;
import onlineBookstoreServiceSystem.enums.OrderStatus;
import onlineBookstoreServiceSystem.users.Customer;
import onlineBookstoreServiceSystem.users.Salesman;
import onlineBookstoreServiceSystem.users.Storekeeper;
import onlineBookstoreServiceSystem.users.Support;
import onlineBookstoreServiceSystem.utils.ObjectPlus;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * This class is the starting class
 * This class shows other functionalities and associations in presented system, which are not presented in the graphical version
 *
 */
public class Main
{
    public final static String extentFile = "C:/Users/Skallu/Desktop/MAS_projekt/src/data.bin"; // path to file, which stores all extents

    public static Customer customer1;
    public static Order order1;

    public static List<Book> bookList = new ArrayList<>();
    public static List<BookCopy> bookCopyList = new ArrayList<>();

    /**
     * This method is the main method
     *
     * @param args
     */
    public static void main(String[] args) throws Exception
    {
        readExtent(); // read from extent file


        // list of certificates
        List<String> certificates = new ArrayList<>();
        certificates.add("Certificate 1");
        certificates.add("Certificate 2");
        certificates.add("Certificate 3");

        // create salesman
        Salesman salesman1 = new Salesman("Stefan", "Siarzewski", new Address("Długa", "14B", "12-345", "Warszawa", "Mazowieckie"),
                LocalDate.of(2020, 01, 01), 2000, FormOfEmployment.UOP, certificates);

        System.out.println(salesman1);
        System.out.println("full salary: " + salesman1.calculateSalary());

        System.out.println("----------------\n------------------");

        // create support worker
        Support support1 = new Support("Jurek", "Killer", new Address("Długa", "14B", "12-345", "Warszawa", "Mazowieckie"),
                LocalDate.of(2020, 10, 10), 2500, FormOfEmployment.UZ);


        Notification notification = customer1.createNotification("title1", "something doesnt work");

        System.out.println(notification);
        System.out.println();
        support1.proceedNotification(notification);
        System.out.println();
        System.out.println(notification);

        System.out.println("----------------\n------------------");

        // create storekeeper
        Storekeeper storekeeper1 = new Storekeeper("Wacław", "Wac", new Address("Krótka", "12A", "12-345", "Warszawa", "Mazowieckie"),
                LocalDate.of(2020, 07, 22), 3000, FormOfEmployment.UOP);

        System.out.println(storekeeper1);

        Book book4 = storekeeper1.addBook("Harry Potter and the Goblet of Fire", "J. K. Rowling", 50, Genre.Fantasy);
        BookCopy bookCopy4 = storekeeper1.addBookCopy(1000000004, "french", book4);

        System.out.println();
        System.out.println(book4);

        System.out.println();
        book4.showLinks("posiada", System.out);


        saveExtent(); // save to extent file
    }

    /**
     * This method saves extents in file
     *
     * @throws Exception
     */
    public static void saveExtent() throws Exception
    {
        System.out.println("\n" + "SAVE EXTENT" + "\n");
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(extentFile));
        ObjectPlus.writeExtents(out);
        out.close();
    }

    /**
     * This method reads extents from file or creates sample data if file does not exist
     *
     * @throws Exception
     */
    public static void readExtent() throws Exception
    {
        File data = new File(extentFile);

        if (data.exists() && data.length() != 0)
        {
            // create sample customer
            customer1 = new Customer("Stachu", "Jones", new Address("Popękanych Bębenków", "14A", "12-345", "Warszawa", "Mazowieckie"),
                    "123-456-789", "jones@stachu.com");

            // create sample order for customer
            order1 = customer1.createOrder(order1, LocalDate.now(), OrderStatus.Created);

            // optional - add credit card to customer
            customer1.addCreditCard("1234567890123456", 123, "02/22");

            System.out.println("\n" + "READ EXTENT" + "\n");
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(extentFile));
            ObjectPlus.readExtents(in);
            in.close();
        }
        else
        {
            System.out.println("\n" + "CREATE SAMPLE DATA" + "\n") ;

            // create sample books
            Book book1 = new Book("Harry Potter and the Philosopher's Stone", "J. K. Rowling", 50, Genre.Fantasy);
            Book book2 = new Book("Harry Potter and the Chamber of Secrets", "J. K. Rowling", 50, Genre.Fantasy);

            // add books to book list
            bookList.add(book1);
            bookList.add(book2);

            // create sample book copies
            BookCopy bookCopy1 = new BookCopy(1000000000, "polish");
            BookCopy bookCopy2 = new BookCopy(1000000001, "german");
            BookCopy bookCopy3 = new BookCopy(1000000002, "english");

            // link book copies with books
            book1.addLinkBookCopy(bookCopy1);
            book1.addLinkBookCopy(bookCopy2);
            book2.addLinkBookCopy(bookCopy3);

            // create sample customer
            customer1 = new Customer("Stachu", "Jones", new Address("Popękanych Bębenków", "14A", "12-345", "Warszawa", "Mazowieckie"),
                    "123-456-789", "jones@stachu.com");

            // create sample order for customer
            order1 = customer1.createOrder(order1, LocalDate.now(), OrderStatus.Created);

            // optional - add credit card to customer
            customer1.addCreditCard("1234567890123456", 123, "02/22");
        }
    }

}
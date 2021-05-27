/**
 * @Author: Patryk Kamiński
 */

import association.Book;
import association.Zamowienie;
import association.Zamowienie.Status;
import inheritance.Customer;
import inheritance.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;


public class Main
{
    public static void main(String[] args)
    {
        StandardServiceRegistry registry = null;
        SessionFactory sessionFactory = null;

        try
        {
            registry = new StandardServiceRegistryBuilder().configure().build();
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

            System.out.println("Created entities:");
            // class: Customer
            Customer customer1 = new Customer("Stachu", "Jones",
                    "Popękanych Bębenków 14A", "123-456-789", "stachu@jones.com");
            System.out.println(customer1);

            // inheritance: Employee extends inheritance.Person
            Employee employee1 = new Employee("Stefan", "Siarzewski",
                    "Połamanych Kości 36", LocalDate.of(2021, Month.MAY, 22), 3500);
            System.out.println(employee1);

            //association Order - Book [1..*]
            Book book1 = new Book("Harry Potter: The Philosopher's Stone", "J.K. Rowling",
                    "English", 50);
            System.out.println(book1);

            List<Book> bookList = new ArrayList<>();
            bookList.add(book1);

            Zamowienie zamowienie1 = new Zamowienie(LocalDate.of(2021, Month.MAY, 22),
                    "Popękanych Bębenków 14A", Status.Paid, bookList);
            System.out.println(zamowienie1);

            // business context
            customer1.makeOrder(zamowienie1);
            zamowienie1.setStatus(Status.Sent);

            Session session = sessionFactory.openSession();
            session.beginTransaction();

            // creates and saves entities
            session.save(customer1);
            session.save(employee1);
            session.save(zamowienie1);
            session.save(book1);

            session.getTransaction().commit();
            session.close();

            System.out.println("Entities from database:");

            session = sessionFactory.openSession();
            session.beginTransaction();

            // loads entities from database
            List<Customer> customersfromDatabaseList = session.createQuery("from Customer").list();
            for (Customer customer : customersfromDatabaseList)
                System.out.println(customer);

            List<Employee> employeesfromDatabaseList = session.createQuery("from Employee").list();
            for (Employee employee : employeesfromDatabaseList)
                System.out.println(employee);

            List<Book> booksfromDatabaseList = session.createQuery("from Book").list();
            for (Book book : booksfromDatabaseList)
                System.out.println(book);

            List<Zamowienie> zamowieniesfromDatabaseList = session.createQuery("from Zamowienie").list();
            for (Zamowienie zamowienie : zamowieniesfromDatabaseList)
                System.out.println(zamowienie);

            session.getTransaction().commit();
            session.close();

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            StandardServiceRegistryBuilder.destroy(registry);
        }
        finally
        {
            if (sessionFactory != null)
            {
                sessionFactory.close();
                sessionFactory = null;
            }
        }

    }


}
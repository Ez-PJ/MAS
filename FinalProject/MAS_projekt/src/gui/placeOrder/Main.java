/**
 * @Author: Patryk Kamiński
 */

package gui.placeOrder;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import onlineBookstoreServiceSystem.entities.Address;
import onlineBookstoreServiceSystem.entities.Book;
import onlineBookstoreServiceSystem.entities.BookCopy;
import onlineBookstoreServiceSystem.entities.Order;
import onlineBookstoreServiceSystem.enums.Genre;
import onlineBookstoreServiceSystem.enums.OrderStatus;
import onlineBookstoreServiceSystem.users.Customer;
import onlineBookstoreServiceSystem.utils.ObjectPlus;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * This class is the main starting class.
 * The class launches application with graphical interface.
 *
 * @see Application
 */
public class Main extends Application
{
    // public variables, which are transferred between scenes
    public static Customer customer;
    public static Order order;

    public static List<Book> bookList = new ArrayList<>();
    public static List<BookCopy> bookCopyList = new ArrayList<>();

    public final static String extentFile = "C:/Users/Skallu/Desktop/MAS_projekt/src/data.bin"; // path to file, which stores all extents

    /**
     * This method opens the main window
     *
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/booksView.fxml")); // load scene from fxml
        primaryStage.setTitle("Online Bookstore Service System");
        Scene scene = new Scene(root);
        primaryStage.setMinWidth(600);
        primaryStage.setMaxWidth(600);
        primaryStage.setMinHeight(438);
        primaryStage.setMaxHeight(438);
        primaryStage.setScene(scene);
        primaryStage.show();

        // end program by closing window
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>()
        {
            @Override
            public void handle(WindowEvent event)
            {
                Platform.exit();
                try
                {
                    saveExtent();
                }
                catch (Exception exception)
                {
                    exception.printStackTrace();
                }
                System.exit(0);
            }
        });
    }

    /**
     * This method is the main method
     *
     * @param args
     */
    public static void main(String[] args)
    {
        try
        {
            readExtent();
            //ObjectPlus.showExtent(Order.class);
            launch(args);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
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
            customer = new Customer("Stachu", "Jones", new Address("Popękanych Bębenków", "14A", "12-345", "Warszawa", "Mazowieckie"),
                    "123-456-789", "jones@stachu.com");

            // create sample order for customer
            order = customer.createOrder(order, LocalDate.now(), OrderStatus.Created);

            // optional - add credit card to customer
            customer.addCreditCard("1234567890123456", 123, "02/22");

            System.out.println("\n" + "READ EXTENT" + "\n");
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(extentFile));
            ObjectPlus.readExtents(in);
            in.close();
        }
        else
        {
            System.out.println("\n" + "CREATE SAMPLE DATA" + "\n");

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
            customer = new Customer("Stachu", "Jones", new Address("Popękanych Bębenków", "14A", "12-345", "Warszawa", "Mazowieckie"),
                    "123-456-789", "jones@stachu.com");

            // create sample order for customer
            order = customer.createOrder(order, LocalDate.now(), OrderStatus.Created);

            // optional - add credit card to customer
            customer.addCreditCard("1234567890123456", 123, "02/22");
        }
    }

}
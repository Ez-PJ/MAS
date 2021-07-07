/**
 * @Author: Patryk Kamiński
 */

package gui.placeOrder;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import onlineBookstoreServiceSystem.entities.Book;
import onlineBookstoreServiceSystem.entities.BookCopy;
import onlineBookstoreServiceSystem.entities.Order;
import java.util.ArrayList;
import java.util.List;

/**
 * This class shows list of added book copies to order
 * The class manages "Order View" scene.
 *
 */
public class OrderViewController extends BooksViewController
{
    // fxml controls
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Button buttonPlaceOrder;
    @FXML
    private Button buttonBack;
    @FXML
    private Label labelItemsInOrder; // label, which stores data about items in order
    @FXML
    private Label labelFinalPrice; // label, which stores data about final price
    @FXML
    private ListView<BookCopy> listViewFromOrder = new ListView<BookCopy>();
    private ObservableList<BookCopy> bookCopiesFromOrder = FXCollections.observableArrayList(); // supporting list which contains book copies and it is stored inside list view

    /**
     * This method handles button "Place Order"
     * The method changes scene to "Payment View"
     *
     * @param actionEvent
     */
    public void buttonPlaceOrderOnAction(ActionEvent actionEvent)
    {
        if (actionEvent.getSource().equals(buttonPlaceOrder))
            fadeOut("fxml/paymentView.fxml", rootPane);
    }

    /**
     * This method handles button "Back"
     * The method changes scene back to "Books View"
     *
     * @param actionEvent
     */
    public void buttonBackOnAction(ActionEvent actionEvent)
    {
        if (actionEvent.getSource().equals(buttonBack))
        {
            fadeOut("fxml/booksView.fxml", rootPane);
            chosenCopiesList.clear();
        }
    }

    /**
     * This method adds chosen copies to specific order
     *
     * @param order
     * @throws Exception
     */
    public void addChosenCopiesToOrder(Order order) throws Exception
    {
        int finalPrice = 0;
        List<Book> books = new ArrayList<Book>();

        for (BookCopy bookCopy : chosenCopiesList)
        {
            order.addLinkBookCopy(bookCopy);
            books.addAll(bookCopy.returnLinks("zawiera sie"));
        }

        for (Book book : books)
            finalPrice += book.getPrice();

        order.setFinalPrice(finalPrice);
    }

    /**
     * This method is the main method for this scene
     *
     * @throws Exception
     */
    @FXML
    public void initialize() throws Exception
    {
        fadeIn(rootPane); // animation, which is played when scene loads

        addChosenCopiesToOrder(Main.order);

        // show copies in current order
        bookCopiesFromOrder.addAll(chosenCopiesList);
        listViewFromOrder.setItems(bookCopiesFromOrder);

        // set value of labels
        labelItemsInOrder.setText(Integer.toString(chosenCopiesList.size()));
        labelFinalPrice.setText(Integer.toString(Main.order.getFinalPrice()));
    }

}
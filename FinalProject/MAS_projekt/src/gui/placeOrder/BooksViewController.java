/**
 * @Author: Patryk Kamiński
 */

package gui.placeOrder;

import javafx.animation.FadeTransition;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import onlineBookstoreServiceSystem.entities.Book;
import onlineBookstoreServiceSystem.entities.BookCopy;
import onlineBookstoreServiceSystem.utils.ObjectPlus;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class shows list of available books and list of linked book copies
 * The class manages "Books View" scene.
 *
 */
public class BooksViewController
{
    // fxml controls
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Button buttonShowOrder;
    @FXML
    private Button buttonAddToOrder;
    @FXML
    private Button buttonRemoveFromOrder;
    @FXML
    private Label labelItemsInOrder; // label, which stores data about items in order
    @FXML
    private ListView<Book> bookListView = new ListView<Book>();
    private ObservableList<Book> bookObservableList = FXCollections.observableArrayList(); // supporting list which contains books and it is stored inside list view
    @FXML
    private ListView<BookCopy> bookCopyListView = new ListView<BookCopy>();
    private ObservableList<BookCopy> bookCopyObservableList = FXCollections.observableArrayList(); // supporting list which contains book copies and it is stored inside list view

    public static List<BookCopy> chosenCopiesList = new ArrayList<>();

    /**
     * This method handles button "Add To Order"
     * The method adds selected book copy to order
     *
     * @param actionEvent
     */
    public void buttonAddToOrderOnAction(ActionEvent actionEvent)
    {
        if (actionEvent.getSource().equals(buttonAddToOrder))
        {
            BookCopy selectedCopy = bookCopyListView.getSelectionModel().getSelectedItem();

            if (selectedCopy != null)
            {
                if (!chosenCopiesList.contains(selectedCopy))
                {
                    chosenCopiesList.add(selectedCopy);
                    labelItemsInOrder.setText(Integer.toString(chosenCopiesList.size()));
                }
            }

            if (chosenCopiesList.size() >= 1)
                buttonShowOrder.setDisable(false);
            else
                buttonShowOrder.setDisable(true);
        }
    }

    /**
     * This method handles button "Remove from Order"
     * The method removes selected book copy from order
     *
     * @param actionEvent
     */
    public void buttonRemoveFromOrderOnAction(ActionEvent actionEvent)
    {
        if (actionEvent.getSource().equals(buttonRemoveFromOrder))
        {
            BookCopy selectedCopy = bookCopyListView.getSelectionModel().getSelectedItem();

            if (selectedCopy != null)
            {
                if (chosenCopiesList.contains(selectedCopy))
                {
                    chosenCopiesList.remove(selectedCopy);
                    labelItemsInOrder.setText(Integer.toString(chosenCopiesList.size()));
                }
            }

            if (chosenCopiesList.size() >= 1)
                buttonShowOrder.setDisable(false);
            else
                buttonShowOrder.setDisable(true);
        }
    }

    /**
     * This method handles button "Show Order"
     * The method changes scene to "Order View", when order has at least one copy
     *
     * @param actionEvent
     */
    public void buttonShowOrderOnAction(ActionEvent actionEvent)
    {
        if (actionEvent.getSource().equals(buttonShowOrder))
        {
            if (chosenCopiesList.size() >= 1)
                fadeOut("fxml/orderView.fxml", rootPane);
        }
    }

    /**
     * This method shows list of all books from extent
     *
     * @throws Exception
     */
    public void showBookList() throws Exception
    {
        ArrayList<Book> books = ObjectPlus.returnExtentList(Book.class);
        bookObservableList.addAll(books);
        bookListView.setItems(bookObservableList);

        /*
        System.out.println(bookList);
        System.out.println();
        System.out.println(bookListView);
        */

        // show linked copies for selected book
        bookListView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Book> observableValue, Book oldValue, Book newValue) ->
        {
            Book selectedItem = bookListView.getSelectionModel().getSelectedItem();

            /*
            System.out.println("selected: " + selectedItemIndex + "\n" + selectedItem);
            System.out.println();
            */

            try
            {
                bookCopyListView.getItems().clear(); // clear current state of books list view, when next book is triggered
                showBookCopyList(selectedItem); // show linked book copies
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        });
    }

    /**
     * This method shows list of linked copies for specific book
     *
     * @param triggeredBook
     * @throws Exception
     */
    public void showBookCopyList(Book triggeredBook) throws Exception
    {
        ArrayList<BookCopy> bookCopies = triggeredBook.returnLinks("posiada");

        for (BookCopy bookCopy : bookCopies)
            bookCopyObservableList.add(bookCopy);

        bookCopyListView.setItems(bookCopyObservableList);
    }

    /**
     * This method deals with scene switching
     *
     * @param nextSceneFXML
     * @param rootPane
     */
    public void switchScene(String nextSceneFXML, AnchorPane rootPane)
    {
        try
        {
            Parent nextView = FXMLLoader.load(getClass().getResource(nextSceneFXML));
            Scene nextScene = new Scene(nextView);

            Stage currentStage = (Stage) rootPane.getScene().getWindow();
            currentStage.setScene(nextScene);

            System.out.println("fxml loaded: " + nextSceneFXML);
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    /**
     * This method adds "fade in" effect to scene switch
     *
     * @param rootPane
     */
    public void fadeIn(AnchorPane rootPane)
    {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500), rootPane);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);

        fadeTransition.play();
    }

    /**
     * This method adds "fade out" effect to scene switch
     *
     * @param nextSceneFXML
     * @param rootPane
     */
    public void fadeOut(String nextSceneFXML, AnchorPane rootPane)
    {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500), rootPane);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);

        fadeTransition.setOnFinished((ActionEvent actionEvent) ->
        {
            switchScene(nextSceneFXML, rootPane);
        });
        fadeTransition.play();
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

        showBookList();

        // block "Show Order" button, when order is empty or unblock, when not
        if (chosenCopiesList.size() >= 1)
            buttonShowOrder.setDisable(false);
        else
            buttonShowOrder.setDisable(true);
    }

}
/**
 * @Author: Patryk Kamiński
 */

package gui.placeOrder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * This class shows confirmation of successful payment
 * The class manages "Payment Confirmation" scene.
 *
 */
public class PaymentConfirmationController extends BooksViewController
{
    // fxml controls
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Button buttonOk;

    /**
     * This method handles button "OK"
     * The method closes application
     *
     * @param actionEvent
     */
    public void buttonOkOnAction(ActionEvent actionEvent) throws Exception
    {
        if (actionEvent.getSource().equals(buttonOk))
        {
            System.out.println("closing application...");

            Main.saveExtent();
            System.exit(0);
        }
    }

    /**
     * This method is the main method for this scene
     *
     */
    @FXML
    public void initialize()
    {
        fadeIn(rootPane); // animation, which is played when scene loads
    }

}
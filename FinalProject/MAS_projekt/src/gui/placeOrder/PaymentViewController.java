/**
 * @Author: Patryk Kamiński
 */

package gui.placeOrder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import onlineBookstoreServiceSystem.entities.Payment;
import onlineBookstoreServiceSystem.enums.OrderStatus;
import java.time.LocalDate;

/**
 * This class shows payment form
 * The class manages "Payment View" scene.
 *
 */
public class PaymentViewController extends BooksViewController
{
    // fxml controls
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Button buttonPay;
    @FXML
    public TextField textFieldCodeCVC;
    @FXML
    public TextField textFieldCreditCardNumber;
    @FXML
    public TextField textFieldExpirationDate;
    @FXML
    public Label labelValidation1;
    @FXML
    public Label labelValidation2;
    @FXML
    public Label labelValidation3;

    /**
     * This method handles button "Pay"
     * The method checks if text fields are filled with correct data and changes scene to "Payment Confirmation"
     *
     * @param actionEvent
     * @throws Exception
     */
    public void buttonPayOnAction(ActionEvent actionEvent) throws Exception
    {
        if (actionEvent.getSource().equals(buttonPay))
        {
            if (String.valueOf(textFieldCreditCardNumber.getText()).length() == 16)
            {
                labelValidation1.setVisible(false); // hide validation label
                if (String.valueOf(textFieldCodeCVC.getText()).length() == 3)
                {
                    labelValidation1.setVisible(false); // hide validation labels
                    labelValidation2.setVisible(false);
                    if (String.valueOf(textFieldExpirationDate.getText()).length() == 5)
                    {
                        labelValidation1.setVisible(false); // hide validation labels
                        labelValidation2.setVisible(false);
                        labelValidation3.setVisible(false);

                        // change order status to "Paid" and then switch scene
                        Main.order.setOrderStatus(OrderStatus.Paid);
                        fadeOut("fxml/paymentConfirmation.fxml", rootPane);
                    }
                    else
                        {
                            // show validation label and message
                            labelValidation3.setVisible(true);
                            labelValidation3.setText("Expiration date must have 5 digits");
                        }
                }
                else
                    {
                        // show validation label and message
                        labelValidation2.setVisible(true);
                        labelValidation2.setText("CVC code must have 3 digits");
                    }
            }
            else
                {
                    // show validation label and message
                    labelValidation1.setVisible(true);
                    labelValidation1.setText("Card number must have 16 digits");
                }
        }
    }

    /**
     * This method creates payment object
     *
     */
    public void createPayment()
    {
        Payment payment = new Payment(Main.order.getFinalPrice(), LocalDate.now());

    }

    /**
     * This method is the main method for this scene
     *
     */
    @FXML
    public void initialize() throws Exception
    {
        fadeIn(rootPane); // animation, which is played when scene loads

        //Main.customer.showLinks("has got", System.out);

        // fil text field whit data from credit card, if customer has got credit card
        if (Main.customer.hasCreditCard())
        {
            textFieldCreditCardNumber.setText(Main.customer.returnCreditCardNumber());
            textFieldCodeCVC.setText(Integer.toString(Main.customer.returnCreditCardCodeCVC()));
            textFieldExpirationDate.setText(Main.customer.returnCreditCardExpirationDate());
        }

        // hide validation labels
        labelValidation1.setVisible(false);
        labelValidation2.setVisible(false);
        labelValidation3.setVisible(false);
    }

}
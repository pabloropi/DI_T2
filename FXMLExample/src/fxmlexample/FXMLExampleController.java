package fxmlexample;
 
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
 
/**
 *
 * @author Pablo Rodríguez 2DAM
 */

public class FXMLExampleController {
    @FXML private Text actiontarget;
    
    @FXML protected void handleSubmitButtonAction(ActionEvent event) {
        actiontarget.setText("Sign in button pressed");
    }

}
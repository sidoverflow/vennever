package venn;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class ExtendedDescriptionController {

    @FXML
    public TextArea descriptionArea;

    @FXML
    private Button ok;
    
    DemoController firstController;
    
    @FXML
    void okAction(ActionEvent event) {
    	Stage stage = (Stage) ok.getScene().getWindow();
        stage.close();
        firstController.currentText.extendedDescription = descriptionArea.getText();
    }
    
    public void setFirstController(DemoController controller) {
        firstController = controller;
    }

}

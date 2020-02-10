package venn;


import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.sun.javafx.tk.FontMetrics;
import com.sun.javafx.tk.Toolkit;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.*;

public class DemoController {
	@FXML
	private MenuItem lButton = new MenuItem();
	@FXML
	private MenuItem rButton = new MenuItem();
	@FXML
    private SplitMenuButton splitMenuButton = new SplitMenuButton();
	@FXML
	public AnchorPane canvas = new AnchorPane();
	@FXML
    public Circle lCircle = new Circle();
	@FXML
	public Circle rCircle = new Circle();
    @FXML
    private AnchorPane lPane = new AnchorPane();
    @FXML
    private AnchorPane rPane = new AnchorPane();
    
    @FXML
    public void handleButtonAction(ActionEvent event) throws IOException{
    	loadScene(event, "AddData.fxml", "Add Data");

    }
    
    void loadScene(ActionEvent event, String loc, String title) throws IOException{
//    	try {
//    		Parent parent = FXMLLoader.load(getClass().getResource(loc));
//    		Stage stage = new Stage(StageStyle.DECORATED);
//    		stage.setTitle(title);
//    		stage.setScene(new Scene(parent));
//    		stage.show();
//    		
//    	} catch (IOException ex) {
//    		Logger.getLogger(DemoController.class.getName()).log(Level.SEVERE, null, ex);    		
//    	}
    	
    	Parent parent = FXMLLoader.load(getClass().getResource(loc));
    	Scene scene = new Scene(parent);
    	
    	//Get stage information
    	Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
    	window.setTitle(title);
    	window.setScene(scene);
    	window.show();
    }
  //initializing the split menu button
  	public void menuClick() {
  		splitMenuButton.getItems().add(lButton);
  		splitMenuButton.getItems().add(rButton);
  	}
    
    public void inflateCircle(List<String> first, List<String> second) {
    	lPane.getChildren().addAll(
                new EditableDraggableText(0, 0, first.get(0))
        );
    	rPane.getChildren().addAll(
                new EditableDraggableText(0, 0, second.get(0))
        );
    }
    
  //to change the colour of the left circle
  	public void leftCircleColour() {
  		  ColorPicker colorPicker = new ColorPicker();
          VBox box = new VBox(colorPicker);
          canvas.getChildren().add(box);
          lCircle.setFill(colorPicker.getValue());
          lCircle.setOpacity(0.30);
          colorPicker.setOnAction(new EventHandler() {
          public void handle(Event t) {
              lCircle.setFill(colorPicker.getValue());           
              lCircle.setOpacity(0.51);
          }
      });
          
          
  	}
  	//to change the colour of the right circle;
  	public void rightCircleColour() {
  		  ColorPicker colorPicker = new ColorPicker();
          VBox vBox = new VBox(colorPicker);
          canvas.getChildren().add(vBox);
          rCircle.setFill(colorPicker.getValue());
          rCircle.setOpacity(0.30);
          colorPicker.setOnAction(new EventHandler() {
              public void handle(Event t) {
                  rCircle.setFill(colorPicker.getValue());      
                  rCircle.setOpacity(0.51);
                  
              }
          });
          
          
  	}
  	
  	
  	/**
     * A text field which has no special decorations like background, border or focus ring.
     *   i.e. the EditableText just looks like a vanilla Text node or a Label node.
     */
    class EditableText extends TextField {
        // The right margin allows a little bit of space
        // to the right of the text for the editor caret.
        private final double RIGHT_MARGIN = 5;

        EditableText(double x, double y) {
            relocate(x, y);
            getStyleClass().add("editable-text");

            //** CAUTION: this uses a non-public API (FontMetrics) to calculate the field size
            //            the non-public API may be removed in a future JavaFX version.
            // see: https://bugs.openjdk.java.net/browse/JDK-8090775
            //      Need font/text measurement API
            FontMetrics metrics = Toolkit.getToolkit().getFontLoader().getFontMetrics(getFont());
            setPrefWidth(RIGHT_MARGIN);
            textProperty().addListener((observable, oldTextString, newTextString) ->
                setPrefWidth(metrics.computeStringWidth(newTextString) + RIGHT_MARGIN)
            );

            Platform.runLater(this::requestFocus);
        }
    }

    /**
     * An EditableText (a text field which looks like a label), which can be dragged around
     * the screen to reposition it.
     */
    class EditableDraggableText extends StackPane {
        private final double PADDING = 5;
        private EditableText text = new EditableText(PADDING, PADDING);

        EditableDraggableText(double x, double y) {
            relocate(x - PADDING, y - PADDING);
            getChildren().add(text);
            getStyleClass().add("editable-draggable-text");

            // if the text is empty when we lose focus,
            // the node has no purpose anymore
            // just remove it from the scene.
            text.focusedProperty().addListener((observable, hadFocus, hasFocus) -> {
                if (!hasFocus && getParent() != null && getParent() instanceof Pane &&
                    (text.getText() == null || text.getText().trim().isEmpty())) {
                    ((Pane) getParent()).getChildren().remove(this);
                }
            });

            enableDrag();
        }

        public EditableDraggableText(int x, int y, String text) {
            this(x, y);
            this.text.setText(text);
        }

        // make a node movable by dragging it around with the mouse.
        private void enableDrag() {
            final Delta dragDelta = new Delta();
            setOnMousePressed(mouseEvent -> {
                this.toFront();
                // record a delta distance for the drag and drop operation.
                dragDelta.x = mouseEvent.getX();
                dragDelta.y = mouseEvent.getY();
                getScene().setCursor(Cursor.MOVE);
            });
            setOnMouseReleased(mouseEvent -> getScene().setCursor(Cursor.HAND));
            setOnMouseDragged(mouseEvent -> {
                double newX = getLayoutX() + mouseEvent.getX() - dragDelta.x;
                if (newX > 0 && newX < getScene().getWidth()) {
                    setLayoutX(newX);
                }
                double newY = getLayoutY() + mouseEvent.getY() - dragDelta.y;
                if (newY > 0 && newY < getScene().getHeight()) {
                    setLayoutY(newY);
                }
            });
            setOnMouseEntered(mouseEvent -> {
                if (!mouseEvent.isPrimaryButtonDown()) {
                    getScene().setCursor(Cursor.HAND);
                }
            });
            setOnMouseExited(mouseEvent -> {
                if (!mouseEvent.isPrimaryButtonDown()) {
                    getScene().setCursor(Cursor.DEFAULT);
                }
            });
        }

        // records relative x and y co-ordinates.
        private class Delta {
            double x, y;
        }
    } 
    
    
   
    
    
    
    
    
    
}

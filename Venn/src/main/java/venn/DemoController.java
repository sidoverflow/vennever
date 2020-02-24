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
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.*;

public class DemoController {
	@FXML
    private Button addData = new Button();
	@FXML
    private Button addLabel = new Button();
	@FXML
	private MenuItem lButton = new MenuItem();
	@FXML
	private MenuItem rButton = new MenuItem();
	@FXML
	private Button undoButton = new Button();
	@FXML
	private Button redoButton = new Button();
	@FXML
	private Button circleSize = new Button();
	@FXML
    private SplitMenuButton splitMenuButton = new SplitMenuButton();
	@FXML
	public AnchorPane canvas = new AnchorPane();
	@FXML
    public Circle lCircle = new Circle();
	@FXML
	public Circle rCircle = new Circle();
    @FXML
    private AnchorPane pane = new AnchorPane();
    @FXML
    private Button bold = new Button();
    @FXML
    private Button italics = new Button();
    @FXML
    private Slider slider = new Slider();
    
    private EditableDraggableText currentText;
    
    public Stack undoStack = new Stack();
	
	public Stack redoStack = new Stack();
	

	
	

  
    
    
    public double xCoord;
    int yCoordI = 150;
    int yCoordL = 100;
	int yCoordR = 100;
    
    
    private Scene secondScene;

    public void setSecondScene(Scene scene) {
        secondScene = scene;
    }

    public void openSecondScene(ActionEvent actionEvent) {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(secondScene);
    }
   
    
    @FXML
    public void textButton(ActionEvent event) throws IOException{
    	canvas.getChildren().addAll(
                new EditableDraggableText(0, 0, "type here")
        );
    
    }
    
  //initializing the split menu button
  	public void menuClick() {
  		splitMenuButton.getItems().add(lButton);
  		splitMenuButton.getItems().add(rButton);
  	}
    
    public void inflateCircle(List<String> first, List<String> second) {
    	
    
    	
    	//find the elements of the intersection set of the two user-input data sets and add them to an array called intersection
    	List<String> intersection = new ArrayList<String>();
    	for (int i = 0; i < first.size(); i++) {
    		for (int j = 0; j < second.size(); j++) {
    			if (first.get(i).equals(second.get(j))) {
    				intersection.add(first.get(i));
    			}
    		}
    	}
    
    	
    	
    	//remove the intersecting elements from the respective arrays
    	for (int i = 0; i < intersection.size(); i++) {
    		first.remove(intersection.get(i));
    		second.remove(intersection.get(i));
    	}
    	
    	//display the elements of the intersection set one below the other by incrementing the y coordinate of the draggable textbox in a loop
    	
    	for (int i = 0; i < intersection.size(); i++) {
    		
    		EditableDraggableText text = new EditableDraggableText(520, yCoordI, intersection.get(i));
    		
    		text.setOnMouseClicked(mouseEvent -> {
            	currentText = text;
            });
			canvas.getChildren().addAll(text);
			yCoordI += 40;
		}
    	
    	//display the elements of the first set one below the other by incrementing the y coordinate of the draggable textbox in a loop
    	
    	for (int i = 0; i < first.size(); i++) {
    		EditableDraggableText text = new EditableDraggableText(400, yCoordL, first.get(i));
    		text.setOnMouseClicked(mouseEvent -> {
            	currentText = text;
            });
			canvas.getChildren().addAll(text);
			yCoordL += 40;
		}
    	
    	//display the elements of the second set one below the other by incrementing the y coordinate of the draggable textbox in a loop
    	
    	for (int i = 0; i < second.size(); i++) {
    		
			EditableDraggableText text = new EditableDraggableText(660, yCoordR, second.get(i));
    		text.setOnMouseClicked(mouseEvent -> {
            	currentText = text;
            });
			canvas.getChildren().addAll(text);
			yCoordR += 40;
		}
    	

    	
    }
    
    public void inflateCircle(List<String> first, List<String> second, List<String> third) {
    	
    	
    	
    	//display the elements of the intersection set one below the other by incrementing the y coordinate of the draggable textbox in a loop
    	
    	for (int i = 0; i < third.size(); i++) {
    		
    		EditableDraggableText text = new EditableDraggableText(520, yCoordI, third.get(i));
    		
    		text.setOnMouseClicked(mouseEvent -> {
            	currentText = text;
            });
			canvas.getChildren().addAll(text);
			yCoordI += 40;
		}
    	
    	//display the elements of the first set one below the other by incrementing the y coordinate of the draggable textbox in a loop
    	
    	for (int i = 0; i < first.size(); i++) {
    		EditableDraggableText text = new EditableDraggableText(400, yCoordL, first.get(i));
    		text.setOnMouseClicked(mouseEvent -> {
            	currentText = text;
            });
			canvas.getChildren().addAll(text);
			yCoordL += 40;
		}
    	
    	//display the elements of the second set one below the other by incrementing the y coordinate of the draggable textbox in a loop
    	
    	for (int i = 0; i < second.size(); i++) {
    		
			EditableDraggableText text = new EditableDraggableText(660, yCoordR, second.get(i));
    		text.setOnMouseClicked(mouseEvent -> {
            	currentText = text;
            });
			canvas.getChildren().addAll(text);
			yCoordR += 40;
		}
    	
    	
    }
    
    @FXML
    void boldButton(ActionEvent event) {
    	currentText.getStyleClass().add("text-style");
    	currentText.setStyle("-fx-font-weight: bold");
    }
    
    @FXML
    void italicsButton(ActionEvent event) {
    	
    	currentText.getStyleClass().add("text-style");
    	currentText.setStyle("-fx-font-style: italic");
    }
    
    
  //to change the colour of the left circle
  	@SuppressWarnings("unchecked")
	public void leftCircleColour() {
  		  ColorPicker colorPicker = new ColorPicker();
          VBox box = new VBox(colorPicker);
          VBox.setMargin(colorPicker, new Insets(50, 10, 10, 10));
          canvas.getChildren().add(box);
          //lCircle.setFill(colorPicker.getValue());
          //lCircle.setOpacity(0.30);
          colorPicker.setOnAction(new EventHandler() {
          public void handle(Event t) {
        	  Color myColor = (Color) lCircle.getFill();
	  			Operation change = new Operation();
	  			change.setOperation("color");
	  			change.setCircle(lCircle);
	  			change.setCurrentColor(myColor);
        	  
        	  
              lCircle.setFill(colorPicker.getValue());           
              lCircle.setOpacity(0.51);
              
              change.setNewColor((Color) lCircle.getFill()); 
           		undoStack.push(change);
          }
      });
          
          
  	}
  	//to change the colour of the right circle;
  	@SuppressWarnings("unchecked")
	public void rightCircleColour() {
  		  ColorPicker colorPicker = new ColorPicker();
          VBox vBox = new VBox(colorPicker);
          VBox.setMargin(colorPicker, new Insets(50, 10, 10, 10));
          canvas.getChildren().add(vBox);
          //rCircle.setFill(colorPicker.getValue());
          //rCircle.setOpacity(0.30);
          colorPicker.setOnAction(new EventHandler() {
              public void handle(Event t) {
            	  Color myColor = (Color) rCircle.getFill();
  	  			Operation change = new Operation();
  	  			change.setOperation("color");
  	  			change.setCircle(rCircle);
  	  			change.setCurrentColor(myColor);
            	  
                  rCircle.setFill(colorPicker.getValue());      
                  rCircle.setOpacity(0.51);
                  
                  change.setNewColor((Color) rCircle.getFill()); 
             		undoStack.push(change);
                  
              }
          });
          
          
  	}
  	
  	@FXML
    void circleSizeAction(ActionEvent event) {
  		
  		slider.setId("slider");
  		slider.setOnDragDetected(mouseEvent -> {
        	sliderMethod();
        });
  		VBox box = new VBox(slider);
        VBox.setMargin(slider, new Insets(10, 10, 10, 10));
  		canvas.getChildren().addAll(box);
    }
  	
  	public void sliderMethod(){
  		
  		
  		Operation change = new Operation();
    	change.setOperation("size");
    	change.setCurrentCircleSize(lCircle.getRadius());
    
  		
  		slider.toFront();
  		slider.setValue(lCircle.getRadius());
  		slider.setMin(160);
  		slider.setMax(300);
  		Bindings.bindBidirectional(slider.valueProperty(),lCircle.radiusProperty());
  		Bindings.bindBidirectional(slider.valueProperty(),rCircle.radiusProperty());
  		slider.valueProperty().addListener(new ChangeListener<Number>()  {

			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				// TODO Auto-generated method stub
				
			if ((double)arg2 <0) {
				xCoord += lCircle.getCenterX()+ ((double)arg2/4);
				
				lCircle.setTranslateX(xCoord);
				rCircle.setTranslateX(-xCoord/3);
				pane.setPrefSize(2*Math.PI*(double)arg2, 2*Math.PI*(double)arg2);

				
			}
			else {
				xCoord = lCircle.getCenterX()- ((double)arg2/4);
				pane.setPrefSize(2*Math.PI*(double)arg2, 2*Math.PI*(double)arg2);
			lCircle.setTranslateX(xCoord);
			rCircle.setTranslateX(-xCoord/3);
			}

			
			change.setNewCircleSize(lCircle.getRadius());
	    	undoStack.push(change);
			
			
			}
  		});
  	}
  	
  	@FXML
  	public void handleUndoButtonAction(ActionEvent e) {
  	
  		Operation undo = undoStack.pop();
  		redoStack.push(undo);
  		String operation = undo.getOperation();
  		if (operation.equals("color")) {
  		
  			Color oldColor = undo.getCurrentColor();
  			undo.getCircle().setFill(oldColor);
  			
  		}
  		else if (operation.equals("size")) {
  			double oldSize = undo.getCurrentSize();
  			lCircle.setRadius(oldSize);
  			rCircle.setRadius(oldSize);
  		}
  		 
  	}
  	
  	@FXML
  	public void handleRedoButtonAction(ActionEvent e) {
  	
  		Operation redo = redoStack.pop();
  		undoStack.push(redo);
  		String operation = redo.getOperation();
  		if ( operation.equals("color")) {
  			Color undoneColor = redo.getNewColor();
  			redo.getCircle().setFill(undoneColor);
  		}
  		else if (operation.equals("size")) {
  			double undoneSize = redo.getNewCircleSize();
  			lCircle.setRadius(undoneSize);
  			rCircle.setRadius(undoneSize);
  		}
  		
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
        
        

        public EditableText getText() {
			return this.text;
		}


		public void setText(EditableText text) {
			this.text = text;
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

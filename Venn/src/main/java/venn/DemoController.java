package venn;

import java.util.List;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;



import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.SetChangeListener;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
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
import javafx.scene.control.skin.TextFieldSkin;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
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

	private EditableLabel currentText;
    @FXML
    private Group circleGroup;
    @FXML
    Label dragInfo  = new Label();
    @FXML
    private Pane formatPaneColour = new Pane();
    @FXML
    private Pane formatPaneSize = new Pane();
    @FXML
    private AnchorPane mainPane = new AnchorPane();
	
	@FXML
	private AnchorPane sidebar = new AnchorPane();

	public Stack undoStack = new Stack();

	public Stack redoStack = new Stack();
	private List<EditableLabel> selectedText = new ArrayList<EditableLabel>();

	public double xCoord;
	int yCoordI = 180;
	int yCoordL = 135;
	int yCoordR = 135;
	
	double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;


	private Scene secondScene;

	public void setSecondScene(Scene scene) {
		secondScene = scene;
	}

	public void openSecondScene(ActionEvent actionEvent) {
		formatPaneColour.setVisible(false);
		formatPaneSize.setVisible(false);
		sidebar.getChildren().remove(dragInfo);
		Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
		primaryStage.setScene(secondScene);
	}

	@FXML
	public void textBoxAction(ActionEvent event) throws IOException {
		formatPaneColour.setVisible(false);
		formatPaneSize.setVisible(false);
		sidebar.getChildren().remove(dragInfo);
		
		EditableLabel newLABEL = new EditableLabel(350, 480, "type here");
		canvas.getChildren().addAll(newLABEL);
		Operation change = new Operation();
		change.setOperation("label");
		change.setLabel(newLABEL);
		undoStack.push(change);
		
	}
	public void diagramTitleAction(ActionEvent event) throws IOException {
		formatPaneColour.setVisible(false);
		formatPaneSize.setVisible(false);
		sidebar.getChildren().remove(dragInfo);
		EditableLabel newLABEL = new EditableLabel(330, 15, "enter diagram title");
		canvas.getChildren().addAll(newLABEL);
		Operation change = new Operation();
		change.setOperation("label");
		change.setLabel(newLABEL);
		undoStack.push(change);
		
	}
	public void setTitleAction(ActionEvent event) throws IOException {
		formatPaneColour.setVisible(false);
		formatPaneSize.setVisible(false);
		sidebar.getChildren().remove(dragInfo);

		
		EditableLabel labelA = new EditableLabel(220, 70, "enter set-A title");
		canvas.getChildren().addAll(labelA);
		Operation change = new Operation();
		change.setOperation("label");
		change.setLabel(labelA);
		undoStack.push(change);
		
		EditableLabel labelB = new EditableLabel(480, 70, "enter set-B title");
		canvas.getChildren().addAll(labelB);
		Operation change1 = new Operation();
		change1.setOperation("label");
		change1.setLabel(labelB);
		undoStack.push(change1);
		
	}

//	// initializing the split menu button
//	public void menuClick() {
//		splitMenuButton.getItems().add(lButton);
//		splitMenuButton.getItems().add(rButton);
//	}

	public void inflateCircle(List<String> first, List<String> second) {

		// find the elements of the intersection set of the two user-input data sets and
		// add them to an array called intersection
		List<String> intersection = new ArrayList<String>();
		for (int i = 0; i < first.size(); i++) {
			for (int j = 0; j < second.size(); j++) {
				if (first.get(i).equals(second.get(j))) {
					intersection.add(first.get(i));
				}
			}
		}

		// remove the intersecting elements from the respective arrays
		for (int i = 0; i < intersection.size(); i++) {
			first.remove(intersection.get(i));
			second.remove(intersection.get(i));
		}

		// display the elements of the intersection set one below the other by
		// incrementing the y coordinate of the draggable textbox in a loop

		for (int i = 0; i < intersection.size(); i++) {

			canvas.getChildren().addAll(new EditableLabel(380, yCoordI, intersection.get(i)));
			yCoordI += 40;
		}

		// display the elements of the first set one below the other by incrementing the
		// y coordinate of the draggable textbox in a loop

		for (int i = 0; i < first.size(); i++) {

			canvas.getChildren().addAll(new EditableLabel(220, yCoordL, first.get(i)));
			yCoordL += 40;
		}

		// display the elements of the second set one below the other by incrementing
		// the y coordinate of the draggable textbox in a loop

		for (int i = 0; i < second.size(); i++) {

			canvas.getChildren().addAll(new EditableLabel(520, yCoordR, second.get(i)));
			yCoordR += 40;
		}

	}

	public void inflateCircle(List<String> first, List<String> second, List<String> third) {

		// display the elements of the intersection set one below the other by
		// incrementing the y coordinate of the draggable textbox in a loop

		for (int i = 0; i < third.size(); i++) {

			EditableLabel text = new EditableLabel(380, yCoordI, third.get(i));
			canvas.getChildren().addAll(text);
			yCoordI += 40;
		}

		// display the elements of the first set one below the other by incrementing the
		// y coordinate of the draggable textbox in a loop

		for (int i = 0; i < first.size(); i++) {
			EditableLabel text = new EditableLabel(220, yCoordL, first.get(i));
			canvas.getChildren().addAll(text);

			yCoordL += 40;
		}

		// display the elements of the second set one below the other by incrementing
		// the y coordinate of the draggable textbox in a loop

		for (int i = 0; i < second.size(); i++) {

			EditableLabel text = new EditableLabel(520, yCoordR, second.get(i));
			canvas.getChildren().addAll(text);

			yCoordR += 40;
		}

	}

	@FXML
	void boldButton(ActionEvent event) {
		if (selectedText.size() > 0) {
			for (int i = 0; i < selectedText.size(); i++) {
				selectedText.get(i).getStyleClass().add("text-style");
				selectedText.get(i).setStyle("-fx-font-weight: bold");
			}
			selectedText.clear();
		}
		else {
			currentText.getStyleClass().add("text-style");
			currentText.setStyle("-fx-font-weight: bold");
		}
	}

	@FXML
	void italicsButton(ActionEvent event) {

		if (selectedText.size() > 0) {
			for (int i = 0; i < selectedText.size(); i++) {
				selectedText.get(i).getStyleClass().add("text-style");
				selectedText.get(i).setStyle("-fx-font-style: italic");
			}
			selectedText.clear();
		}
		else {
			currentText.getStyleClass().add("text-style");
			currentText.setStyle("-fx-font-style: italic");
		}
	}

	
	@FXML
	@SuppressWarnings("unchecked")
	public void customizeCircleAction(ActionEvent event) {
		formatPaneColour.setVisible(true);
		
		ColorPicker colorPicker1 = new ColorPicker((Color) lCircle.getFill());
		VBox colorBox1 = new VBox(colorPicker1);
		colorBox1.setCursor(Cursor.HAND);
		colorBox1.setLayoutX(35);
		colorBox1.setLayoutY(19);
		formatPaneColour.getChildren().add(colorBox1);
		colorPicker1.setOnAction(new EventHandler() {
			public void handle(Event t) {
				Color myColor = (Color) lCircle.getFill();
				Operation change = new Operation();
				change.setOperation("color");
				change.setCircle(lCircle);
				change.setCurrentColor(myColor);

				lCircle.setFill(colorPicker1.getValue());
				lCircle.setOpacity(0.51);

				change.setNewColor((Color) lCircle.getFill());
				undoStack.push(change);
			}
		});
		
		ColorPicker colorPicker2 = new ColorPicker((Color) rCircle.getFill());
		VBox colorBox2 = new VBox(colorPicker2);
		colorBox2.setCursor(Cursor.HAND);
		
		formatPaneColour.getChildren().add(colorBox2);
		colorBox2.setLayoutX(250);
		colorBox2.setLayoutY(19);
		colorPicker2.setOnAction(new EventHandler() {
			public void handle(Event t) {
				Color myColor = (Color) rCircle.getFill();
				Operation change = new Operation();
				change.setOperation("color");
				change.setCircle(rCircle);
				change.setCurrentColor(myColor);

				rCircle.setFill(colorPicker2.getValue());
				rCircle.setOpacity(0.51);

				change.setNewColor((Color) rCircle.getFill());
				undoStack.push(change);
			}
		});
		
		formatPaneSize.setVisible(true);
		
		slider.setId("slider");
		slider.setOnDragDetected(mouseEvent -> {
			sliderMethod();
		});
		VBox sliderBox = new VBox(slider);
		VBox.setMargin(slider, new Insets(10, 10, 10, 10));
		sliderBox.setLayoutX(55);
		sliderBox.setLayoutY(5);
		slider.setCursor(Cursor.HAND);
		formatPaneSize.getChildren().addAll(sliderBox);
		dragInfo = new Label("drag the circles to reposition on the editor");
		dragInfo.setLayoutX(52);
		dragInfo.setLayoutY(590);
		dragInfo.getStyleClass().add("infoLabel");
		sidebar.getChildren().addAll(dragInfo);
		
		
	}
	
	


	
	@FXML
	void clickCanvas(MouseEvent event) {
		formatPaneColour.setVisible(false);
		formatPaneSize.setVisible(false);
		sidebar.getChildren().remove(dragInfo);
	}
	

	
	@FXML
    void circleOnMousePressedEventHandler(MouseEvent event) {
		
		
		((Circle) event.getSource()).toFront();
		orgSceneX = event.getSceneX();
        orgSceneY = event.getSceneY();
        orgTranslateX = ((Circle)(event.getSource())).getTranslateX();
        orgTranslateY = ((Circle)(event.getSource())).getTranslateY();
        
    }

    @FXML
    void circleOnMouseDraggedEventHandler(MouseEvent event) {
    	
		
    	double offsetX = event.getSceneX() - orgSceneX;
        double offsetY = event.getSceneY() - orgSceneY;
        double newTranslateX = orgTranslateX + offsetX;
        double newTranslateY = orgTranslateY + offsetY;
         
        ((Circle)(event.getSource())).setTranslateX(newTranslateX);
        ((Circle)(event.getSource())).setTranslateY(newTranslateY);
    }
	

	public void sliderMethod() {

		Operation change = new Operation();
		change.setOperation("size");
		change.setCurrentCircleSize(lCircle.getRadius());

		slider.toFront();
		slider.setValue(lCircle.getRadius());
		slider.setMax(220);
		slider.setMin(50);
		Bindings.bindBidirectional(slider.valueProperty(), lCircle.radiusProperty());
		Bindings.bindBidirectional(slider.valueProperty(), rCircle.radiusProperty());
		slider.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				// TODO Auto-generated method stub

				if ((double) arg2 < 0) {
					xCoord = lCircle.getCenterX() + ((double) arg2 / 4);
					lCircle.setTranslateX(xCoord);
					rCircle.setLayoutX(lCircle.getLayoutX() + lCircle.getRadius() + (0.15 * lCircle.getRadius()));
					

				} else {
					xCoord = lCircle.getCenterX() - ((double) arg2 / 4);
					lCircle.setTranslateX(xCoord);
					rCircle.setLayoutX(lCircle.getLayoutX() + lCircle.getRadius() + (0.15 * lCircle.getRadius()));
				}

				change.setNewCircleSize(lCircle.getRadius());
				undoStack.push(change);

			}
		});
		
	}
	
	@FXML
	private void formatButtonAction(ActionEvent e) {
		
	
	}

	@FXML
	public void handleUndoButtonAction(ActionEvent e) {
		
		formatPaneColour.setVisible(false);
		formatPaneSize.setVisible(false);
		sidebar.getChildren().remove(dragInfo);
		Operation undo = undoStack.pop();
		redoStack.push(undo);
		String operation = undo.getOperation();
		if (operation.equals("color")) {

			Color oldColor = undo.getCurrentColor();
			undo.getCircle().setFill(oldColor);

		} else if (operation.equals("size")) {
			double oldSize = undo.getCurrentSize();
			lCircle.setRadius(oldSize);
			rCircle.setRadius(oldSize);
		}
		else if (operation.equals("label")) {
			EditableLabel oldLabel = undo.getlabel();
			canvas.getChildren().removeAll(oldLabel);
		}

	}

	@FXML
	public void handleRedoButtonAction(ActionEvent e) {
		
		formatPaneColour.setVisible(false);
		formatPaneSize.setVisible(false);
		sidebar.getChildren().remove(dragInfo);
		Operation redo = redoStack.pop();
		undoStack.push(redo);
		String operation = redo.getOperation();
		if (operation.equals("color")) {
			
			Color undoneColor = redo.getNewColor();
			redo.getCircle().setFill(undoneColor);
			
		} else if (operation.equals("size")) {
			
			double undoneSize = redo.getNewCircleSize();
			lCircle.setRadius(undoneSize);
			rCircle.setRadius(undoneSize);
			
		}else if ( operation.equals("label")) {
			EditableLabel undoneLabel = redo.getlabel();
			canvas.getChildren().addAll(undoneLabel);
		}

	}
	public void openBrowser(ActionEvent actionEvent) throws Exception {

		try {
		    Desktop.getDesktop().browse(new URL("https://www.github.com/sidoverflow/VennProject").toURI());
		} catch (IOException e) {
		    e.printStackTrace();
		} catch (URISyntaxException e) {
		    e.printStackTrace();
		}

    }
	public void quitButton(ActionEvent actionEvent) {
		Stage stage = (Stage) addData.getScene().getWindow();
	    stage.close();
	}

	class EditableLabel extends Label {
		TextField tf = new TextField();

		/***
		 * backup is used to cancel when press ESC...
		 */
//  	    String backup = "";
		public EditableLabel(double x, double y, String str) {

			this(str);
			relocate(x, y);
			getChildren().add(tf);
			getStyleClass().add("editable-text");
			enableDrag();
			
		}

		public EditableLabel(String str) {
			super(str);

			this.setOnMouseClicked(e -> {
				if (e.getClickCount() == 2) {
					tf.setText(this.getText());

					this.setGraphic(tf);
					this.setText(tf.getText());

					tf.requestFocus();
					
				} else if (e.isControlDown()) {
					selectedText.add(this);
				}
				else {
					currentText = this;
				}
			});

			tf.focusedProperty().addListener((prop, o, n) -> {
				if (!n) {
					toLabel();
				}
			});
			tf.setOnKeyReleased(e -> {
				if (e.getCode().equals(KeyCode.ENTER)) {
					toLabel();
				} else if (e.getCode().equals(KeyCode.ESCAPE)) {
					tf.setText("");
					toLabel();
				}
			});
			tf.focusedProperty().addListener((observable, hadFocus, hasFocus) -> {
				if (!hasFocus && getParent() != null && getParent() instanceof Pane
						&& (tf.getText() == null || tf.getText().trim().isEmpty())) {
					((Pane) getParent()).getChildren().remove(this);
				}
			});

		}

		void toLabel() {
			this.setGraphic(null);
			this.setText(tf.getText());
		}

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



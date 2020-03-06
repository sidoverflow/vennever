package venn;

import java.util.List;
import java.io.IOException;
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
		Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
		primaryStage.setScene(secondScene);
	}

	@FXML
	public void textButton(ActionEvent event) throws IOException {
		canvas.getChildren().addAll(new EditableLabel(0, 0, "type here"));

	}

	// initializing the split menu button
	public void menuClick() {
		splitMenuButton.getItems().add(lButton);
		splitMenuButton.getItems().add(rButton);
	}

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

			canvas.getChildren().addAll(new EditableLabel(520, yCoordI, intersection.get(i)));
			yCoordI += 40;
		}

		// display the elements of the first set one below the other by incrementing the
		// y coordinate of the draggable textbox in a loop

		for (int i = 0; i < first.size(); i++) {

			canvas.getChildren().addAll(new EditableLabel(400, yCoordL, first.get(i)));
			yCoordL += 40;
		}

		// display the elements of the second set one below the other by incrementing
		// the y coordinate of the draggable textbox in a loop

		for (int i = 0; i < second.size(); i++) {

			canvas.getChildren().addAll(new EditableLabel(660, yCoordR, second.get(i)));
			yCoordR += 40;
		}

	}

	public void inflateCircle(List<String> first, List<String> second, List<String> third) {

		// display the elements of the intersection set one below the other by
		// incrementing the y coordinate of the draggable textbox in a loop

		for (int i = 0; i < third.size(); i++) {

			EditableLabel text = new EditableLabel(520, yCoordI, third.get(i));
			canvas.getChildren().addAll(text);
			yCoordI += 40;
		}

		// display the elements of the first set one below the other by incrementing the
		// y coordinate of the draggable textbox in a loop

		for (int i = 0; i < first.size(); i++) {
			EditableLabel text = new EditableLabel(400, yCoordL, first.get(i));
			canvas.getChildren().addAll(text);

			yCoordL += 40;
		}

		// display the elements of the second set one below the other by incrementing
		// the y coordinate of the draggable textbox in a loop

		for (int i = 0; i < second.size(); i++) {

			EditableLabel text = new EditableLabel(660, yCoordR, second.get(i));
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

	// to change the colour of the left circle
	@SuppressWarnings("unchecked")
	public void leftCircleColour() {
		ColorPicker colorPicker = new ColorPicker();
		VBox box = new VBox(colorPicker);
		VBox.setMargin(colorPicker, new Insets(50, 10, 10, 10));
		canvas.getChildren().add(box);
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

	// to change the colour of the right circle;
	@SuppressWarnings("unchecked")
	public void rightCircleColour() {
		ColorPicker colorPicker = new ColorPicker();
		VBox vBox = new VBox(colorPicker);
		VBox.setMargin(colorPicker, new Insets(50, 10, 10, 10));
		canvas.getChildren().add(vBox);
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

	public void sliderMethod() {

		Operation change = new Operation();
		change.setOperation("size");
		change.setCurrentCircleSize(lCircle.getRadius());

		slider.toFront();
		slider.setValue(lCircle.getRadius());
		slider.setMin(220);
		slider.setMax(300);
		Bindings.bindBidirectional(slider.valueProperty(), lCircle.radiusProperty());
		Bindings.bindBidirectional(slider.valueProperty(), rCircle.radiusProperty());
		slider.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				// TODO Auto-generated method stub

				if ((double) arg2 < 0) {
					xCoord += lCircle.getCenterX() + ((double) arg2 / 4);

					lCircle.setTranslateX(xCoord);
					rCircle.setTranslateX(-xCoord / 3);
					pane.setPrefSize(2 * Math.PI * (double) arg2, 2 * Math.PI * (double) arg2);

				} else {
					xCoord = lCircle.getCenterX() - ((double) arg2 / 4);
					pane.setPrefSize(2 * Math.PI * (double) arg2, 2 * Math.PI * (double) arg2);
					lCircle.setTranslateX(xCoord);
					rCircle.setTranslateX(-xCoord / 3);
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

		} else if (operation.equals("size")) {
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
		if (operation.equals("color")) {
			Color undoneColor = redo.getNewColor();
			redo.getCircle().setFill(undoneColor);
		} else if (operation.equals("size")) {
			double undoneSize = redo.getNewCircleSize();
			lCircle.setRadius(undoneSize);
			rCircle.setRadius(undoneSize);
		}

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
			getStyleClass().add("editable-draggable-text");
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
				} else {
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
package venn;

import java.util.List;
import java.awt.Desktop;
import java.awt.image.RenderedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import javax.imageio.ImageIO;

import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.SetChangeListener;
import javafx.css.PseudoClass;
import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.SnapshotParameters;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.skin.TextFieldSkin;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

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
	public Button format = new Button();
	@FXML
	private Button customizeCircle = new Button();
	public int cT = 1, cC = 1, cB = 0, cIt = 0;
	@FXML
	private Slider slider = new Slider();

	public EditableLabel currentText;
	
    private Group circleGroup;
    @FXML
    Label dragInfo  = new Label();
    @FXML
    Label selectInfo = new Label();
    @FXML
    private Pane formatPaneColour = new Pane();
    @FXML
    private ImageView ctrl;
    @FXML
    private ImageView click;
    @FXML
    private Pane formatPaneSize = new Pane();
    @FXML
    public Pane formatPaneText = new Pane();
    @FXML
    private AnchorPane mainPane = new AnchorPane();
    @FXML
    private Button vennTest = new Button();
    @FXML
    MenuBar menuBar = new MenuBar();
    
    String font[] = {"choose your font","Arial","Comic Sans MS","Courier","Montserrat","Proxima Nova","Times New Roman","Verdana",};
	@FXML
	private ComboBox fontComboBox;
	String fontSize[] = {"size","10","12","14","16","18","20","22","24","26","28","30"};
	@FXML
	private ComboBox sizeComboBox;
	@FXML
	public AnchorPane sidebar = new AnchorPane();

	public Stack undoStack = new Stack();

	public Stack redoStack = new Stack();
	private List<EditableLabel> selectedText = new ArrayList<EditableLabel>();
	
	
	
	

	public double xCoord;
	int yCoordI = 180;
	int yCoordL = 135;
	int yCoordR = 135;
	
	int xCoordI = 380;
	int xCoordL = 220;
	int xCoordR = 520;
	
	double orgSceneX, orgSceneY;
    double orgTranslateX, orgTranslateY;


	private Scene secondScene;
	private Scene fourthScene;
	private AddDataController secondController;
	
	private static ObservableList<EditableLabel> itemList = FXCollections.observableArrayList();

	@FXML
	Alert a = new Alert(AlertType.NONE);

	private File openFile = null;
	
	public void setSecondController(AddDataController controller) {
        secondController = controller;
    }
	
	public void setSecondScene(Scene scene) {
		secondScene = scene;
	}

	public void openSecondScene(ActionEvent actionEvent) {
		if (cC % 2 == 0) {
			cC++;
			formatPaneColour.setVisible(false);
			formatPaneSize.setVisible(false);
			customizeCircle.setStyle("-fx-background-color: #222831");
			
		}
		else if (cT % 2 == 0) {
			cT++;
			formatPaneText.setVisible(false);
			format.setStyle("-fx-background-color: #222831");
			selectInfo.setVisible(false);
			ctrl.setVisible(false);
			click.setVisible(false);
			
		}

		formatPaneColour.setVisible(false);
		formatPaneSize.setVisible(false);
		formatPaneText.setVisible(false);
		sidebar.getChildren().remove(dragInfo);
		Stage primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
		secondScene.getStylesheets().add(getClass().getResource("editable-text.css").toExternalForm());
		
		secondController.toggle = new ToggleSwitch();
		
        secondController.toggle.setTranslateX(910);
        secondController.toggle.setTranslateY(70);

        secondController.text.setStyle("-fx-text-fill: #ffffff");
        secondController.text.textProperty().bind(Bindings.when(secondController.toggle.switchedOnProperty()).then("Count View").otherwise("Text View"));

        secondController.mainPane.getChildren().add(secondController.toggle);
		primaryStage.setScene(secondScene);
	}

	@FXML
	public void textBoxAction(ActionEvent event) throws IOException {
		if (cC % 2 == 0) {
			cC++;
			formatPaneColour.setVisible(false);
			formatPaneSize.setVisible(false);
			sidebar.getChildren().remove(dragInfo);
			customizeCircle.setStyle("-fx-background-color: #222831");
			
		}
		else if (cT % 2 == 0) {
			cT++;
			formatPaneText.setVisible(false);
			sidebar.getChildren().remove(dragInfo);
			format.setStyle("-fx-background-color: #222831");
			selectInfo.setVisible(false);
			ctrl.setVisible(false);
			click.setVisible(false);
			
		}

		formatPaneText.setVisible(false);
		formatPaneColour.setVisible(false);
		formatPaneSize.setVisible(false);
		
		EditableLabel newLABEL = new EditableLabel(350, 480, "type here");
		canvas.getChildren().addAll(newLABEL);
		Operation change = new Operation();
		change.setOperation("label");
		change.setLabel(newLABEL);
		undoStack.push(change);
		
	}
	public void diagramTitleAction(ActionEvent event) throws IOException {
		if (cC % 2 == 0) {
			cC++;
			formatPaneColour.setVisible(false);
			sidebar.getChildren().remove(dragInfo);
			formatPaneSize.setVisible(false);
			customizeCircle.setStyle("-fx-background-color: #222831");
			
		}
		else if (cT % 2 == 0) {
			cT++;
			formatPaneText.setVisible(false);
			sidebar.getChildren().remove(dragInfo);
			format.setStyle("-fx-background-color: #222831");
			selectInfo.setVisible(false);
			ctrl.setVisible(false);
			click.setVisible(false);
			
			
		}

		
		EditableLabel newLABEL = new EditableLabel(330, 15, "enter diagram title");
		canvas.getChildren().addAll(newLABEL);
		Operation change = new Operation();
		change.setOperation("label");
		change.setLabel(newLABEL);
		undoStack.push(change);
		
	}
	public void setTitleAction(ActionEvent event) throws IOException {
		if (cC % 2 == 0) {
			cC++;
			formatPaneColour.setVisible(false);
			formatPaneSize.setVisible(false);
			sidebar.getChildren().remove(dragInfo);
			customizeCircle.setStyle("-fx-background-color: #222831");
			
		}
		else if (cT % 2 == 0) {
			cT++;
			formatPaneText.setVisible(false);
			sidebar.getChildren().remove(dragInfo);
			format.setStyle("-fx-background-color: #222831");
			selectInfo.setVisible(false);
			ctrl.setVisible(false);
			click.setVisible(false);
			
			
		}

		formatPaneText.setVisible(false);
		formatPaneColour.setVisible(false);
		formatPaneSize.setVisible(false);

		
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
	public void inflateCircle(int left, int intersection, int right) {
		canvas.getChildren().addAll(new EditableLabel(220, 135, Integer.toString(left)));
		canvas.getChildren().addAll(new EditableLabel(380, 180, Integer.toString(intersection)));
		canvas.getChildren().addAll(new EditableLabel(520, 135, Integer.toString(right)));
	}

	

	public void inflateCircle(List<String> first, List<String> second, List<String> third) {

		// display the elements of the intersection set one below the other by
		// incrementing the y coordinate of the draggable textbox in a loop

		for (int i = 0; i < third.size(); i++) {
			if ((i + 1) % 8 == 0) {
				yCoordI = 180;
				xCoordI += 50;
			}
			EditableLabel text = new EditableLabel(xCoordI, yCoordI, third.get(i));
			canvas.getChildren().addAll(text);
			yCoordI += 40;
		}

		// display the elements of the first set one below the other by incrementing the
		// y coordinate of the draggable textbox in a loop

		for (int i = 0; i < first.size(); i++) {
			if ((i + 1) % 8 == 0) {
				yCoordL = 135;
				xCoordL += 50;
			}
			EditableLabel text = new EditableLabel(xCoordL, yCoordL, first.get(i));
			canvas.getChildren().addAll(text);

			yCoordL += 40;
		}

		// display the elements of the second set one below the other by incrementing
		// the y coordinate of the draggable textbox in a loop

		for (int i = 0; i < second.size(); i++) {
			if ((i + 1) % 8 == 0) {
				yCoordR = 135;
				xCoordR += 50;
			}
			EditableLabel text = new EditableLabel(xCoordR, yCoordR, second.get(i));
			canvas.getChildren().addAll(text);

			yCoordR += 40;
		}
	

	}

	
	@FXML
	@SuppressWarnings("unchecked")
	public void customizeCircleAction(ActionEvent event) {
		sidebar.getChildren().remove(dragInfo);
		cC++;
		if (cC % 2 == 0) {
			
			if (cT % 2 == 0) {
				cT++;
				format.setStyle("-fx-background-color: #222831");
			}
			
			customizeCircle.setStyle("-fx-background-color: #FA2C56");
			selectInfo.setVisible(false);
			ctrl.setVisible(false);
			click.setVisible(false);
			formatPaneText.setVisible(false);
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
			dragInfo.setLayoutX(40);
			dragInfo.setLayoutY(590);
			dragInfo.getStyleClass().add("infoLabel");
			sidebar.getChildren().addAll(dragInfo);
		}
		else {
			customizeCircle.setStyle("-fx-background-color: #222831");
			
			formatPaneColour.setVisible(false);
			formatPaneSize.setVisible(false);
			sidebar.getChildren().remove(dragInfo);
			selectInfo.setVisible(false);
			ctrl.setVisible(false);
			click.setVisible(false);
		}
		
		
	}
	
	@FXML
	@SuppressWarnings("unchecked")
	public void circleClick(MouseEvent event) {
		sidebar.getChildren().remove(dragInfo);
		cC++;
		if (cC % 2 == 0) {
			
			if (cT % 2 == 0) {
				cT++;
				format.setStyle("-fx-background-color: #222831");
			}
			
			customizeCircle.setStyle("-fx-background-color: #FA2C56");
			
			formatPaneText.setVisible(false);
			selectInfo.setVisible(false);
			ctrl.setVisible(false);
			click.setVisible(false);
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
			dragInfo.setLayoutX(40);
			dragInfo.setLayoutY(590);
			dragInfo.getStyleClass().add("infoLabel");
			sidebar.getChildren().addAll(dragInfo);
		}
		else {
			customizeCircle.setStyle("-fx-background-color: #222831");
			
			formatPaneColour.setVisible(false);
			formatPaneSize.setVisible(false);
			sidebar.getChildren().remove(dragInfo);
			selectInfo.setVisible(false);
			ctrl.setVisible(false);
			click.setVisible(false);
		}
		
		
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
		
		
	if (((Color) format.getBackground().getFills().get(0).getFill()).toString().equals("0x222831ff")) {
		
		if (cT == 1) {
			fontComboBox.getItems().addAll(font);
			sizeComboBox.getItems().addAll(fontSize);
		}
		//		if (cT % 2 == 0) {
		if (cC % 2 == 0) {
			cC++;
			customizeCircle.setStyle("-fx-background-color: #222831");
			formatPaneColour.setVisible(false);
			formatPaneSize.setVisible(false);
			sidebar.getChildren().remove(dragInfo);
		}
		format.setStyle("-fx-background-color: #FA2C56");
		formatPaneText.setVisible(true);
		dragInfo = new Label("select the text box to edit by clicking on it \n              click again to deselect");
		dragInfo.setLayoutX(40);
		dragInfo.setLayoutY(575);
		dragInfo.getStyleClass().add("infoLabel");
		sidebar.getChildren().add(dragInfo);
		//		}
		//		else {
		//			
		//			format.setStyle("-fx-background-color: #222831");
		//			formatPaneText.setVisible(false);
		//		}
//		cT++;
	}
		
	
	}

	@FXML
	void boldButton(ActionEvent event) {
		
		if (cB % 2 == 0) {
			
				if (selectedText.size() > 0) {
					for (int i = 0; i < selectedText.size(); i++) {
						selectedText.get(i).setStyle(selectedText.get(i).getStyle() + ";" + "-fx-font-weight: bold");
					}
//					selectedText.clear();
				} else {
					currentText.setStyle(currentText.getStyle() + ";" + "-fx-font-weight: bold");
				}
			
		}
		else {
		
				if (selectedText.size() > 0) {
					for (int i = 0; i < selectedText.size(); i++) {
						selectedText.get(i).setStyle(selectedText.get(i).getStyle() + ";" + "-fx-font-weight: normal");
					}
//					selectedText.clear();
				} else {
					currentText.setStyle(currentText.getStyle() + ";" + "-fx-font-weight: normal");
				}
			
		}
		cB++;
	}

	@FXML
	void italicsButton(ActionEvent event) {

		if (cIt % 2 == 0) {
			if (selectedText.size() > 0) {
				for (int i = 0; i < selectedText.size(); i++) {
					selectedText.get(i).setStyle(selectedText.get(i).getStyle() + ";" + "-fx-font-style: italic");
				}
//				selectedText.clear();
			} else {
				currentText.setStyle(currentText.getStyle() + ";" + "-fx-font-style: italic");
			} 
		}
		else {
			if (selectedText.size() > 0) {
				for (int i = 0; i < selectedText.size(); i++) {
					selectedText.get(i).setStyle(selectedText.get(i).getStyle() + ";" + "-fx-font-style: normal");
				}
//				selectedText.clear();
			} else {
				currentText.setStyle(currentText.getStyle() + ";" + "-fx-font-style: normal");
			} 
		}
		cIt++;
	}

	
	
	@FXML
	public void descriptionAction(ActionEvent event) {
		try {
        	
        	FXMLLoader descriptionLoader = new FXMLLoader();
        	descriptionLoader.setLocation(getClass().getResource("/venn/ExtendedDescription.fxml"));
	        Parent descriptionPane = descriptionLoader.load();
	        Scene descriptionscene = new Scene(descriptionPane);
	        
	        ExtendedDescriptionController descriptionController = (ExtendedDescriptionController) descriptionLoader.getController();
	        descriptionController.setFirstController(this);
            Stage stage = new Stage();
            stage.setScene(descriptionscene);
            stage.initStyle(StageStyle.TRANSPARENT);
            
            stage.show();
            
            descriptionController.descriptionArea.setText(currentText.extendedDescription);
            
        }
        catch (IOException e) {
            e.printStackTrace();
        }
	
	}
	
	@FXML
	public void removeAction(ActionEvent event) {
		if (selectedText.size() > 0) {
			for (int i = 0; i < selectedText.size(); i++) {
				canvas.getChildren().remove(selectedText.get(i));
				DemoController.itemList.remove(selectedText.get(i));
			}
			selectedText.clear();
		}
		else {
			canvas.getChildren().remove(currentText);
			DemoController.itemList.remove(currentText);
		}
		
	}
	
	@FXML
	public void changeFont(ActionEvent e) {
		if (fontComboBox.getValue().toString() != "choose your font") {
			if (selectedText.size() > 0) {
				for (int i = 0; i < selectedText.size(); i++) {
					selectedText.get(i).setStyle(selectedText.get(i).getStyle() + ";" + "-fx-font-family: " + '"'
							+ fontComboBox.getValue().toString() + '"');
				}

			} else {
				currentText.setStyle(currentText.getStyle() + ";" + "-fx-font-family: " + '"'
						+ fontComboBox.getValue().toString() + '"');

			} 
		} 
		
	}
	
	@FXML
	public void changeFontSize(ActionEvent e) {
		if (fontComboBox.getValue().toString() != "size") {
			if (selectedText.size() > 0) {
				for (int i = 0; i < selectedText.size(); i++) {
					selectedText.get(i).setStyle(selectedText.get(i).getStyle() + ";" + "-fx-font-size: " + sizeComboBox.getValue().toString() );
				}
			} else {
				currentText.setStyle(currentText.getStyle() + ";" + "-fx-font-size: " + sizeComboBox.getValue().toString() );
			}
		}
		
	}

	@FXML
	public void handleUndoButtonAction(ActionEvent e) {
		if (cC % 2 == 0) {
			cC++;
			formatPaneColour.setVisible(false);
			formatPaneSize.setVisible(false);
			sidebar.getChildren().remove(dragInfo);
			customizeCircle.setStyle("-fx-background-color: #222831");
			
		}
		else if (cT % 2 == 0) {
			cT++;
			formatPaneText.setVisible(false);
			sidebar.getChildren().remove(dragInfo);
			selectInfo.setVisible(false);
			ctrl.setVisible(false);
			click.setVisible(false);
			format.setStyle("-fx-background-color: #222831");
			
			
		}

		
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
		if (cC % 2 == 0) {
			cC++;
			formatPaneColour.setVisible(false);
			formatPaneSize.setVisible(false);
			sidebar.getChildren().remove(dragInfo);
			customizeCircle.setStyle("-fx-background-color: #222831");
			
		}
		else if (cT % 2 == 0) {
			cT++;
			formatPaneText.setVisible(false);
			sidebar.getChildren().remove(dragInfo);
			selectInfo.setVisible(false);
			ctrl.setVisible(false);
			click.setVisible(false);
			format.setStyle("-fx-background-color: #222831");
			
			
		}

		
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
//		Stage stage = (Stage) addData.getScene().getWindow();
//	    stage.close();
	    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to quit VennEver?", ButtonType.YES, ButtonType.NO);
	    DialogPane dialogPane = alert.getDialogPane();
	    alert.setTitle("VennEver");
	    alert.setHeaderText("Quit VennEver");
		dialogPane.getStylesheets().add(
		   getClass().getResource("editable-text.css").toExternalForm());
		dialogPane.getStyleClass().add("dialog-pane-close");
		dialogPane.getStyleClass().add("alert-pane-close");
		
        // clicking X also means no
        ButtonType result = alert.showAndWait().orElse(ButtonType.NO);
        
        if (ButtonType.NO.equals(result)) {
            // consume event i.e. ignore close request 
            actionEvent.consume();
        }
        else {
        	   //close all windows and terminate
        	System.exit(0);
        }
	}

	class EditableLabel extends Label {
		TextField tf = new TextField();
		String extendedDescription = "please type the description here";

		/***
		 * backup is used to cancel when press ESC...
		 */
//  	    String backup = "";
		public EditableLabel(double x, double y, String str) {

			this(str);
			relocate(x, y);
			getChildren().add(tf);
			getStyleClass().add("editable-text");
			DemoController.itemList.add(this);
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
//					if (cT == 1) {
//						fontComboBox.getItems().addAll(font);
//						sizeComboBox.getItems().addAll(fontSize);
//					}
//					
//					cT++;
//					
//					if (currentText != this) {
//						cT++;
//					}
//					
//					
//					if (cT % 2 == 0 || currentText != this) {
//						if (cC % 2 == 0) {
//							cC++;
//							customizeCircle.setStyle("-fx-background-color: #222831");
//							formatPaneColour.setVisible(false);
//							formatPaneSize.setVisible(false);
//							sidebar.getChildren().remove(dragInfo);
//						}
//						format.setStyle("-fx-background-color: #FA2C56");
//						formatPaneText.setVisible(true);
//						dragInfo = new Label("select the text box to edit by clicking on it");
//						dragInfo.setLayoutX(40);
//						dragInfo.setLayoutY(590);
//						dragInfo.getStyleClass().add("infoLabel");
//						sidebar.getChildren().addAll(dragInfo);
//						selectedText.add(this);
//						currentText = new EditableLabel(0,0,"");
//						
//					}
//					else {
//						
//						format.setStyle("-fx-background-color: #222831");
//						formatPaneText.setVisible(false);
//						sidebar.getChildren().remove(dragInfo);
//						selectedText.clear();
//					}
					selectedText.add(currentText);
					selectedText.add(this);
					
					
					
				}
				else {
					cB++;
					cIt++;
					
					if (cT == 1) {
						fontComboBox.getItems().addAll(font);
						sizeComboBox.getItems().addAll(fontSize);
					}
					
					cT++;
						
					if (currentText != this && currentText != null) {
						cT++;
					}
					currentText = this;
					if (cT % 2 == 0) {
						if (cC % 2 == 0) {
							cC++;
							customizeCircle.setStyle("-fx-background-color: #222831");
							formatPaneColour.setVisible(false);
							formatPaneSize.setVisible(false);
							sidebar.getChildren().remove(dragInfo);
						}
						
						fontComboBox.getSelectionModel().select(0);
						sizeComboBox.getSelectionModel().select(0);
						format.setStyle("-fx-background-color: #FA2C56");
						
						
						formatPaneText.setVisible(true);
						
						click.setVisible(true);
						ctrl.setVisible(true);
						sidebar.getChildren().remove(dragInfo);
						selectInfo.setVisible(true);
						selectedText.clear();
					}
					else {
						format.setStyle("-fx-background-color: #222831");
						formatPaneText.setVisible(false);
						sidebar.getChildren().remove(dragInfo);
						selectInfo.setVisible(false);
						ctrl.setVisible(false);
						click.setVisible(false);
						currentText = null;
					}
					
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
					DemoController.itemList.remove(this);
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
		public class Delta {
			double x, y;
		}
	}
	
	public void clearAllAction(ActionEvent e) {
		ObservableList<Node> boxes = canvas.getChildren();
		List<EditableLabel> toBeDeleted = new ArrayList<EditableLabel>();
		for (int i = 0 ; i < boxes.size(); i++) { 
			if (boxes.get(i).getClass().toString().equals("class venn.DemoController$EditableLabel")) {
				toBeDeleted.add((EditableLabel) boxes.get(i)); 
			}
		}
		for (EditableLabel l: toBeDeleted) {
			canvas.getChildren().remove(l);
			DemoController.itemList.remove(l);
		}
		yCoordI = 180;
		yCoordL = 135;
		yCoordR = 135;
		
		xCoordI = 380;
		xCoordL = 220;
		xCoordR = 520;
	}

	public void setFourthScene(Scene scene) {
		fourthScene = scene;
	}
	
	@FXML
    void openFourthScene(ActionEvent event) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Do you really want to close this application?", ButtonType.OK);
		alert.setTitle("VennTest");
	    alert.setHeaderText("Launching VennTest mode");
	    alert.setContentText("In VennTest mode, you can upload a .csv file in a specific format containing data for the tags "
	    		+ "and the correct answer. \n \nOn uploading the file, the tags will be populated on the side for you to arrange on the diagram. Once you are finished, click Done to compare your attempt with "
	    		+ "the correct answer. \n \nThe format for the .csv file is as follows: \n \nLine 1 must be all the tags"
	    		+ " separated by commas. \n \nLine 2, 3 and 4 will have information about the correct answer sorted by "
	    		+ "the diagram sets. \n \nLine 2 must be the tags for the LEFT set of the diagram separated by "
	    		+ "commas. \n \nLine 3 must be the tags for the INTERSECTION set of the diagram separated by commas. "
	    		+ "\n \nLine 4 must be the tags for the RIGHT set of the diagram in double quotes and separated by commas. "
	    		+ "\n \nExample Apples&Oranges.csv file: \n \norange,thin peel,grow on trees,red,fruit,thick peel"
	    		+ "\nred,thin peel"
	    		+ "\nfruit,grow on trees"
	    		+ "\norange,thick peel");
		
		DialogPane dialogPane = alert.getDialogPane();
		dialogPane.setPrefHeight(550);
		dialogPane.setPrefWidth(800);
		dialogPane.getStylesheets().add(
		   getClass().getResource("editable-text.css").toExternalForm());
		dialogPane.getStyleClass().add("alert-pane");
		
		// clicking X also means no
		ButtonType result = alert.showAndWait().orElse(ButtonType.OK);

		
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		fourthScene.getStylesheets().add(getClass().getResource("editable-text.css").toExternalForm());
		primaryStage.setScene(fourthScene);
		

    }
	
	public static class ToggleSwitch extends Parent {

        private BooleanProperty switchedOn = new SimpleBooleanProperty(false);

        private TranslateTransition translateAnimation = new TranslateTransition(Duration.seconds(0.25));
        private FillTransition fillAnimation = new FillTransition(Duration.seconds(0.25));

        private ParallelTransition animation = new ParallelTransition(translateAnimation, fillAnimation);

        public BooleanProperty switchedOnProperty() {
            return switchedOn;
        }

        public ToggleSwitch() {
        	setScaleX(0.5);
        	setScaleY(0.5);
        	setScaleZ(0.5);
        	
        	
            Rectangle background = new Rectangle(100, 50);
            background.setArcWidth(50);
            background.setArcHeight(50);
            background.setFill(Color.web("#222831"));
            background.setStroke(Color.web("#FA2C56"));

            Circle trigger = new Circle(25);
            trigger.setCenterX(25);
            trigger.setCenterY(25);
            trigger.setFill(Color.WHITE);
            trigger.setStroke(Color.LIGHTGRAY);

            DropShadow shadow = new DropShadow();
            shadow.setRadius(2);
            trigger.setEffect(shadow);

            translateAnimation.setNode(trigger);
            fillAnimation.setShape(background);

            getChildren().addAll(background, trigger);

            switchedOn.addListener((obs, oldState, newState) -> {
                boolean isOn = newState.booleanValue();
                translateAnimation.setToX(isOn ? 100 - 50 : 0);
                fillAnimation.setFromValue(isOn ? Color.web("#222831") : Color.web("#FA2C56"));
                fillAnimation.setToValue(isOn ? Color.web("#FA2C56") : Color.web("#222831"));

                animation.play();
            });

            setOnMouseClicked(event -> {
                switchedOn.set(!switchedOn.get());
            });
        }
    }
	
	public void loadFile() {

		try {
			FileChooser fc = new FileChooser();
			List<String> extensions = new ArrayList<String>();
			extensions.add("*.venn");

			fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Venn files (*.venn)", "*.venn"));
			File file = fc.showOpenDialog(canvas.getScene().getWindow());
			String elements[];
			Color  leftColor, rightColor;
			double leftScale, rightScale;
			ObservableList<String> unassignedItems = FXCollections.observableArrayList();
			ObservableList<EditableLabel> inDiagram = FXCollections.observableArrayList();
			ZipFile vennFile = new ZipFile(file);

			// Config
			ZipEntry ze = vennFile.getEntry("Config.vlist");
			BufferedReader br = new BufferedReader(new InputStreamReader(vennFile.getInputStream(ze)));

			elements = br.readLine().split("𔓱");
			leftColor = Color.web(elements[0]);
			leftScale = Double.parseDouble(elements[1]);

			elements = br.readLine().split("𔓱");
			rightColor = Color.web(elements[0]);
			rightScale = Double.parseDouble(elements[1]);
			

			String line;
			// Unassigned
			ze = vennFile.getEntry("Unassigned.csv");
			br = new BufferedReader(new InputStreamReader(vennFile.getInputStream(ze)));
			while ((line = br.readLine()) != null) {
				if (line.contains(",")) {
					line = line.substring(1, line.length() - 1);
				}
				unassignedItems.add(line);

			}
			br.close();

			// InDiagram
			ze = vennFile.getEntry("InDiagram.vlist");
			
			br = new BufferedReader(new InputStreamReader(vennFile.getInputStream(ze)));
			while ((line = br.readLine()) != null) {
				elements = line.split("𔓱");
				EditableLabel a = new EditableLabel(Double.parseDouble(elements[1]),Double.parseDouble(elements[2]), elements[0]);
				
				inDiagram.add(a);
			}
			br.close();
			vennFile.close();

			
			
			lCircle.setFill(leftColor);
			lCircle.setRadius(leftScale);
			rCircle.setFill(rightColor);
			rCircle.setRadius(rightScale);
			
//			itemList.clear();
			
			canvas.getChildren().removeAll();
			canvas.getChildren().addAll(inDiagram);
			
			

			openFile = file;
			// FIXME: Crashes the JUnit tests because they don't have a title bar on the
			// window to change
			
			
		} catch (Exception e) {
			System.out.println("Error: File not opened.");
			System.out.println(e);
			e.printStackTrace();
			DialogPane dialogPane = a.getDialogPane();
			dialogPane.getStylesheets().add(
			   getClass().getResource("editable-text.css").toExternalForm());
			dialogPane.getStyleClass().add("alert-pane");
			a.setAlertType(AlertType.ERROR);
			a.setHeaderText("File could not be opened");
			a.setContentText("");
			a.setTitle("Error");
			a.show();
		}
	}
	
	public void saveFile(File file) {
		/*
		 * Heirarchy
		 * 1. LCircle
		 * 2. RCircle
		 * 3. textbox text, position 
		 */
		try {
			FileOutputStream fos = new FileOutputStream(file);
			ZipOutputStream zos = new ZipOutputStream(fos);

			File config = new File("Config.vlist");
			StringBuilder sb = new StringBuilder();
			sb.append( lCircle.getFill() + "𔓱" + lCircle.getRadius() + "\n"); 
			sb.append( rCircle.getFill() + "𔓱" + rCircle.getRadius() + "\n");
			sb.append(slider.getValue() + "\n");



			BufferedWriter bw = new BufferedWriter(new FileWriter(config));
			bw.write(sb.toString());
			bw.close();
			File unassigned = new File("Unassigned.csv");
			sb = new StringBuilder();
			bw = new BufferedWriter(new FileWriter(unassigned));
			if (!itemList.isEmpty()) {
				bw.write(itemList.get(0).getText());
				if (itemList.size() > 1) {
					for (int i = 1; i < itemList.size(); i++) {
						if (itemList.get(i).getText().equals(",")) {
							bw.append("\n\"" + itemList.get(i).getText() + "\"");
						} else {
							bw.append("\n" + itemList.get(i).getText());
						}
					}
				}
			}
			bw.close();

			File inDiagram = new File("InDiagram.vlist");
			sb = new StringBuilder();
			for (int i = 0; i < itemList.size(); i++) {
				EditableLabel d = itemList.get(i);
				sb.append(d.getText() + "𔓱" + d.getLayoutX() + "𔓱"
						+ d.getLayoutY() + "𔓱" );
				if (i != itemList.size() - 1) {
					sb.append("\n");
				}
			}
			bw = new BufferedWriter(new FileWriter(inDiagram));
			bw.write(sb.toString());
			bw.close();

			File[] files = { config, unassigned, inDiagram };
			byte[] buffer = new byte[128];
			for (int i = 0; i < files.length; i++) {
				File f = files[i];
				if (!f.isDirectory()) {
					ZipEntry entry = new ZipEntry(f.getName());
					FileInputStream fis = new FileInputStream(f);
					zos.putNextEntry(entry);
					int read = 0;
					while ((read = fis.read(buffer)) != -1) {
						zos.write(buffer, 0, read);
					}
					zos.closeEntry();
					fis.close();
				}
			}
			zos.close();
			fos.close();

			for (File f : files) {
				f.delete();
			}

			openFile = file;

		} 
		catch (Exception e) {
			System.out.println("Error: File not saved.");
			System.out.println(e);
			e.printStackTrace();
			DialogPane dialogPane = a.getDialogPane();
			dialogPane.getStylesheets().add(
			   getClass().getResource("editable-text.css").toExternalForm());
			dialogPane.getStyleClass().add("alert-pane");
			a.setAlertType(AlertType.ERROR);
			a.setHeaderText("File could not be saved");
			a.setContentText("");
			a.show();
		}

	}
	
	@FXML
	void save() {
		if (openFile!= null) {
			saveFile(openFile);
		}
		else {
			saveAs();
		}
		
	}
	
	
	@FXML
	void saveAs() {
		try {
			String name;
			
			
				name = "Venn Diagram.venn";
			

			FileChooser fc = new FileChooser();
			fc.setTitle("Save");
			fc.setInitialFileName(name);
			fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("VennEver files (*.venn)", "*.venn"));
			File selectedFile = fc.showSaveDialog(canvas.getScene().getWindow());

			if (!(selectedFile.getName().length() > 5 && selectedFile.getName()
					.substring(selectedFile.getName().length() - 5).toLowerCase().equals(".venn"))) {
				selectedFile.renameTo(new File(selectedFile.getAbsolutePath() + ".venn"));
			}
			
			saveFile(selectedFile);
			

		} catch (Exception e) {
			
			System.out.println("Error: File not saved.");
			System.out.println(e);
			e.printStackTrace();
			DialogPane dialogPane = a.getDialogPane();
			dialogPane.getStylesheets().add(
			   getClass().getResource("editable-text.css").toExternalForm());
			dialogPane.getStyleClass().add("alert-pane");
			a.setAlertType(AlertType.ERROR);
			a.setHeaderText("File could not be saved");
			a.setContentText("");
			a.show();
		}
	}
	
	@FXML
	public void export() {
		FileChooser fileChooser = new FileChooser();
        
        //Set extension filter
        FileChooser.ExtensionFilter extFilter = 
                new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        fileChooser.getExtensionFilters().add(extFilter);
       
        //Show save file dialog
        File file = fileChooser.showSaveDialog(canvas.getScene().getWindow());
         
        if(file != null){
            try {
                WritableImage writableImage = canvas.snapshot(new SnapshotParameters(), null);
                RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
                ImageIO.write(renderedImage, "png", file);
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
	}


}



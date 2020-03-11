package venn;


import java.util.List;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Operation {

	private Color currentColor;
	private Color newColor;
	private Circle c;
	private DemoController.EditableLabel addedLabel;
	private double currentCircleSize;
	private double newCircleSize;
	private String currentLabelData;
	private String newLabelData;
	private String operation;
	private List<String> arr1;
	private List<String> arr2;
	private List<String> arr3;
	

	public List<String> getArr1() {
		return this.arr1;
	}

	public void setArr1(List<String> newArr1) {
		this.arr1 = newArr1;
	}
	public List<String> getArr2() {
		return this.arr2;
	}

	public void setArr2(List<String> newArr2) {
		this.arr2 = newArr2;
	}
	public List<String> getArr3() {
		return this.arr3;
	}

	public void setArr3(List<String> newArr3) {
		this.arr3 = newArr3;
	}

	// ---------NEW CIRCLE SIZE GET AND SET DATA METHODS----------------

	public double getNewCircleSize() {
		return this.newCircleSize;
	}

	public void setNewCircleSize(double newCircleSize) {
		this.newCircleSize = newCircleSize;
	}

	// ---------CURRENT CIRCLE SIZE GET AND SET SIZE METHODS------------
	public double getCurrentSize() {
		return this.currentCircleSize;
	}

	public void setCurrentCircleSize(double currentCircleSize) {
		this.currentCircleSize = currentCircleSize;
	}

	// ---------GET AND SET LABEL METHOD----------------------------------------

	public void setLabel(DemoController.EditableLabel newLabel) {
			
			this.addedLabel = newLabel;
	}
		
		public DemoController.EditableLabel getlabel() {
			
			return this.addedLabel;
	}

	// ---------CIRCLE NEW COLOR GET AND SET COLOR METHODS--------------

	public void setNewColor(Color newColor) {

		this.newColor = newColor;
	}

	public Color getNewColor() {

		return this.newColor;
	}

	// ---------CIRCLE CURRENT COLOR GET AND SET METHOD------------------
	public void setCurrentColor(Color currentColor) {
		this.currentColor = currentColor;
	}

	public Color getCurrentColor() {

		return this.currentColor;
	}

	// --------OPERATION STRING SET AND GET METHOD-----------------------
	public void setOperation(String operationName) {

		this.operation = operationName;
	}

	public String getOperation() {
		return this.operation;
	}

	// --------GET AND SET CIRCLE METHOD-----------------------------------------

	public void setCircle(Circle c) {
		this.c = c;
	}
	
	public Circle getCircle() {
		return this.c;
	}

}

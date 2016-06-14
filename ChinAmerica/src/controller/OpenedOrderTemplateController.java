package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.Order;

public class OpenedOrderTemplateController implements Initializable {

	@FXML
	private Button openOrderButton;
	
	@FXML
	private Label orderValue;
	
	@FXML
	private Label orderStatus;
	
	private Order order;
	
	private RootController root;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		orderStatus.setText(order.getOrderStatus().toString());
		orderValue.setText(order.totalPrice().toString());
	}

	public RootController getRoot() {
		return root;
	}

	public void setRoot(RootController root) {
		this.root = root;
	}
	
	@FXML
	private void openOrder(){
		root.setOrderParam(order);
		root.showOrder();
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}

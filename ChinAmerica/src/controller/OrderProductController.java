package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.OrderedProduct;

public class OrderProductController implements Initializable {

	@FXML
	private Label productName;

	@FXML
	private Label productQuantity;

	@FXML
	private Label productPrice;

	private OrderedProduct orderedProduct;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		productName.setText(orderedProduct.getProduct().getName());
		productQuantity.setText(orderedProduct.getQuantity().toString());
		productPrice.setText(orderedProduct.getPrice().toString());
	}

	public OrderedProduct getOrderedProduct() {
		return orderedProduct;
	}

	public void setOrderedProduct(OrderedProduct orderedProduct) {
		this.orderedProduct = orderedProduct;
	}

}

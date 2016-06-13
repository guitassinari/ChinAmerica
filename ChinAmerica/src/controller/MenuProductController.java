package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Product;

public class MenuProductController implements Initializable {

	private Product product;
	@FXML
	private Label productName;
	@FXML
	private Label productPrice;

	@FXML
	private TextField productQuantity;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (product != null) {
			productName.setText(product.getName());
			productPrice.setText("R$ " + product.getPrice().toString());
		}
	}

	public int getProductQuantity() {
		if(productQuantity.getText().isEmpty()){
			return 0;
		}
		return Integer.parseInt(productQuantity.getText());
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}

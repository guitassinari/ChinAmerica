package controller;

import java.net.URL;
import java.util.ResourceBundle;

import database.ProductDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.NumberStringConverter;
import model.Product;
import model.ProductType;
import model.UserType;

public class ProductController implements Initializable {

	@FXML
	TextField productNameField;
	@FXML
	TextField productPriceField;
	@FXML
	TextArea productDescriptionField;
	@FXML
	Button saveChangesButton;
	@FXML
	Button editProductButton;
	@FXML
	Button deleteProductButton;
	@FXML
	RadioButton foodType;
	@FXML
	RadioButton drinkType;
	@FXML
	RadioButton dessertType;

	private ProductType productType;
	private Product product;
	private RootController root;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		productPriceField.setTextFormatter(new TextFormatter<>(new NumberStringConverter()));

		if (product != null) {
			productNameField.setText(product.getName());
			productPriceField.setText(product.getPrice().toString());
			productDescriptionField.setText(product.getDescription());
		}

		if (root.getLoggedUser() != null && root.getLoggedUser().getUserType().equals(UserType.MANAGER)) {
			disableEdition(false);
		} else {
			disableEdition(true);
		}
	}

	private void disableEdition(boolean value) {
		productNameField.setDisable(value);
		productPriceField.setDisable(value);
		productDescriptionField.setDisable(value);
		saveChangesButton.setDisable(value);
		deleteProductButton.setDisable(value);
		foodType.setDisable(value);
		drinkType.setDisable(value);
		dessertType.setDisable(value);
		
	}

	@FXML
	public void saveProduct() {
		try{
			if (product == null) {
				product = new Product();
			}

			product.setName(productNameField.getText());
			product.setPrice(Float.parseFloat(productPriceField.getText()));
			product.setDescription(productDescriptionField.getText());
			product.setProductType(productType);
			
			ProductDAO database = new ProductDAO();
			
			if(database.getProductByName(product.getName()) != null){
				database.updateProduct(product);
			} else {
				database.addProduct(product);
			}
			
			RootController.alert("Sucesso","O produto foi salvo com sucesso!", AlertType.CONFIRMATION);
		} catch (Exception e){
			RootController.alert("ERRO","Houve algo errado! Contate nossos desenvolvedores para resolver o problema",AlertType.ERROR);
			e.printStackTrace();
		}
		
		
		
	}
	
	@FXML
	public void deleteProduct(){
		
	}

	public RootController getRoot() {
		return root;
	}

	public void setRoot(RootController root) {
		this.root = root;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public ProductType getProductType() {
		return productType;
	}

	@FXML
	public void setProductType() {
		if(foodType.isSelected()){
			productType = ProductType.FOOD;
		} else if(drinkType.isSelected()){
			productType = ProductType.DRINK;
		} else {
			productType = ProductType.DESSERT;
		}
	}

}

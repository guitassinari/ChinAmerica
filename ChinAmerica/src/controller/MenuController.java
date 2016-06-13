package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import database.ProductDAO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import model.Order;
import model.OrderStatus;
import model.OrderedProduct;
import model.Product;
import model.ProductType;

public class MenuController implements Initializable {

	private List<Product> drinks, foods, desserts;
	private List<MenuProductController> controllers;

	@FXML
	private GridPane grid;

	private RootController root;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		controllers = new ArrayList<MenuProductController>();
		int rowIndex = 0;
		
		fetchProducts();
		
		Label label = new Label("Pratos");
		label.setFont(new Font("Arial", 25));
		addRowToGrid(label, rowIndex);
		rowIndex++;

		for(Product product : foods){
			createProductRow(product, rowIndex);
			rowIndex++;
		}
		
		label = new Label("Bebidas");
		label.setFont(new Font("Arial", 25));
		addRowToGrid(label, rowIndex);
		rowIndex++;
		for(Product product : drinks){
			createProductRow(product, rowIndex);
			rowIndex++;
		}
		
		
		label = new Label("Sobremesas");
		label.setFont(new Font("Arial", 25));
		addRowToGrid(label, rowIndex);
		rowIndex++;
		for(Product product : desserts){
			createProductRow(product, rowIndex);
			rowIndex++;
		}
		
		Button confirmOrderButton = new Button("Confirmar pedido");
		confirmOrderButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				confirmOrder();
				
			}
		});
		grid.addRow(rowIndex, confirmOrderButton);
		rowIndex++;
	}
	
	private void confirmOrder(){
		Order order = new Order();
		OrderedProduct orderedProduct;
		List<OrderedProduct> orderedProducts = new ArrayList<OrderedProduct>();
		for(MenuProductController productController : controllers){
			if(productController.getProductQuantity() > 0){
				orderedProduct = new OrderedProduct();
				orderedProduct.setProduct(productController.getProduct());
				orderedProduct.setPrice(productController.getProduct().getPrice());
				orderedProduct.setQuantity(productController.getProductQuantity());
				 orderedProducts.add(orderedProduct);
			}
		}
		
		order.setOrderedProducts(orderedProducts);
		order.setOrderStatus(OrderStatus.WAITING_ORDER_APPROVAL);
		order.setClient(root.getLoggedUser());
		
		root.setOrderParam(order);
		root.showOrder();
	}
	
	private void createProductRow(Product product, int rowIndex){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(RootController.class.getResource("../view/MenuProduct.fxml"));
			MenuProductController  menuProductController = new MenuProductController();
			menuProductController.setProduct(product);
			controllers.add(menuProductController);
			loader.setController(menuProductController);
			AnchorPane productRow = (AnchorPane) loader.load();
			addRowToGrid(productRow, rowIndex);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void addRowToGrid(Node node, int rowIndex){
		grid.addRow(rowIndex, node);
	}
	
	private void fetchProducts() {
		ProductDAO database = new ProductDAO();

		List<Product> allProducts = database.getAllProducts();
		separateProductsByTypes(allProducts);
	}

	private void separateProductsByTypes(List<Product> products) {
		drinks = new ArrayList<Product>();
		foods = new ArrayList<Product>();
		desserts = new ArrayList<Product>();

		for (Product product : products) {
			if (product.getProductType().equals(ProductType.FOOD)) {
				foods.add(product);
			} else if (product.getProductType().equals(ProductType.DRINK)) {
				drinks.add(product);
			} else {
				desserts.add(product);
			}
		}
	}

	public RootController getRoot() {
		return root;
	}

	public void setRoot(RootController root) {
		this.root = root;
	}

	public List<MenuProductController> getControllers() {
		return controllers;
	}

	public void setControllers(List<MenuProductController> controllers) {
		this.controllers = controllers;
	}

}

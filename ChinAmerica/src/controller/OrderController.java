package controller;

import java.net.URL;
import java.util.ResourceBundle;

import database.OrderDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import model.Order;
import model.OrderStatus;
import model.OrderedProduct;
import model.UserType;

public class OrderController implements Initializable {

	@FXML
	private Label orderClientName;

	@FXML
	private Label orderStatus;

	@FXML
	private Button approveOrder;

	@FXML
	private Button reproveOrder;

	@FXML
	private Button confirmOrder;

	@FXML
	private Button payOrder;

	@FXML
	private Button cancelOrder;

	@FXML
	private TextArea orderDescription;

	@FXML
	private Label orderTotal;

	@FXML
	private GridPane productsGrid;

	private Order order;

	private RootController root;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		updateButtons();

		orderClientName.setText(order.getClient().getName());
		orderStatus.setText(order.getOrderStatus().toString());
		int rowIndex = 0;
		
		Float total = 0f;

		for (OrderedProduct product : order.getOrderedProducts()) {
			addProductToGrid(rowIndex, product);
			total += product.getQuantity()*product.getPrice();
			rowIndex++;
		}
		
		orderTotal.setText(total.toString());
	}

	private void updateButtons() {
		if (root.getLoggedUser().getUserType().equals(UserType.MANAGER)) {
//			payOrder.setDisable(true);
//			cancelOrder.setDisable(true);
//			confirmOrder.setDisable(true);
		} else {
			approveOrder.setDisable(true);
			reproveOrder.setDisable(true);
		}
		
	}
	
	@FXML
	private void confirmOrder(){
		try{
			OrderDAO database = new OrderDAO();
			order.setDescription(orderDescription.getText());
			for(OrderedProduct orderedProduct : order.getOrderedProducts()){
				orderedProduct.setOrder(order);
			}
			database.addOrder(order);
			RootController.alert("Sucesso","Pedido salvo com sucesso! Aguarde a aprovação pelo gerente.",AlertType.CONFIRMATION);
		} catch (Exception e) {
			e.printStackTrace();
			RootController.alert("ERRO","Houve algo errado! Contate nossos desenvolvedores para resolver o problema",AlertType.ERROR);
		}

	}

	@FXML
	private void cancelOrder() {
		try {
			order.setOrderStatus(OrderStatus.CANCELLED);
			OrderDAO database = new OrderDAO();
			database.updateOrder(order);		
			RootController.alert("Sucesso","Pedido cancelado com sucesso!",AlertType.CONFIRMATION);
		} catch (Exception e) {
			RootController.alert("ERRO","Houve algo errado! Contate nossos desenvolvedores para resolver o problema",AlertType.ERROR);
			e.printStackTrace();
		}
	
	}

	@FXML
	private void reproveOrder() {
		try {
			order.setOrderStatus(OrderStatus.ORDER_REFUSED);
			OrderDAO database = new OrderDAO();
			database.updateOrder(order);
			RootController.alert("Sucesso","Pedido reprovado com sucesso!",AlertType.CONFIRMATION);
		} catch (Exception e){
			RootController.alert("ERRO","Houve algo errado! Contate nossos desenvolvedores para resolver o problema",AlertType.ERROR);
			e.printStackTrace();
		}

	}
	
	@FXML
	private void payOrder(){
		root.setOrderParam(order);
		root.showPayment();
	}

	@FXML
	private void approveOrder() {
		try {
			order.setOrderStatus(OrderStatus.ORDER_APPROVED);
			order.setApprovalManager(root.getLoggedUser());
			OrderDAO database = new OrderDAO();
			database.updateOrder(order);			
			RootController.alert("Sucesso","Pedido aprovado com sucesso!",AlertType.CONFIRMATION);
		} catch (Exception e) {
			RootController.alert("ERRO","Houve algo errado! Contate nossos desenvolvedores para resolver o problema",AlertType.ERROR);
			e.printStackTrace();
		}

	}

	private void addProductToGrid(int rowIndex, OrderedProduct orderedProduct) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(RootController.class.getResource("../view/OrderProduct.fxml"));
			OrderProductController orderProductController = new OrderProductController();
			orderProductController.setOrderedProduct(orderedProduct);
			loader.setController(orderProductController);
			AnchorPane productRow = (AnchorPane) loader.load();
			addRowToGrid(rowIndex, productRow);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addRowToGrid(int rowIndex, Node node) {
		productsGrid.addRow(rowIndex, node);
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public RootController getRoot() {
		return root;
	}

	public void setRoot(RootController root) {
		this.root = root;
	}

}

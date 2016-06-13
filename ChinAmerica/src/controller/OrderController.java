package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import model.Order;
import model.OrderedProduct;

public class OrderController implements Initializable {

	@FXML
	private Label orderClientName;

	@FXML
	private Label orderStatus;

	@FXML
	private Button approveOrder;

	@FXML
	private Button seePayment;

	@FXML
	private Button payOrder;

	@FXML
	private Button changeOrder;

	@FXML
	private Button cancelOrder;

	@FXML
	private GridPane productsGrid;

	private Order order;

	private RootController root;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		orderClientName.setText(order.getClient().getName());
		orderStatus.setText(order.getOrderStatus().toString());
		int rowIndex = 0;

		for (OrderedProduct product : order.getOrderedProducts()) {
			addProductToGrid(rowIndex, product);
			rowIndex++;
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

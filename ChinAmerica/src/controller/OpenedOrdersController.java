package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import database.OrderDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import model.Order;

public class OpenedOrdersController implements Initializable {


	@FXML
	private GridPane ordersGrid;
	
	private List<Order> orders;
	
	private RootController root;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		int rowIndex = 0;
		OrderDAO database = new OrderDAO();
		
		orders = database.getAllOrders();
		
		
		for(Order order : orders){
			addOrderRowToGrid(order,rowIndex);
			rowIndex++;
		}
	}
	
	private void addOrderRowToGrid(Order order, int rowIndex){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(RootController.class.getResource("../view/OpenedOrderTemplate.fxml"));
			OpenedOrderTemplateController openedOrderTemplateController = new OpenedOrderTemplateController();
			openedOrderTemplateController.setRoot(root);
			openedOrderTemplateController.setOrder(order);
			loader.setController(openedOrderTemplateController);
			AnchorPane openedOrderTemplate = (AnchorPane) loader.load();
			ordersGrid.addRow(rowIndex, openedOrderTemplate);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public RootController getRoot() {
		return root;
	}

	public void setRoot(RootController root) {
		this.root = root;
	}

}

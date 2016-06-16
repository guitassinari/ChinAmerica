package controller;

import java.net.URL;
import java.util.ResourceBundle;

import database.OrderDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Order;
import model.OrderStatus;
import model.Payment;

public class PaymentController implements Initializable {

	private RootController root;

	private Order order;
	
	@FXML
	private Label orderPrice;
	
	@FXML
	private TextField paymentValue;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		if(order != null ){
			if(order.getPayment() == null){
				Payment payment = new Payment();
				payment.setOrder(order);
				payment.setApprovalManager(root.getLoggedUser());
				order.setPayment(payment);
			}
			
			orderPrice.setText(order.totalPrice().toString());
			
			if(order.getPayment().getValue() == null){
				paymentValue.setText("0");
			} else {
				paymentValue.setText(order.getPayment().getValue().toString());
			}
			
			if(order.getOrderStatus().equals(OrderStatus.WAITING_PAYMENT_APPROVAL) || 
					order.getOrderStatus().equals(OrderStatus.PAYMENT_APPROVED) ||
					order.getOrderStatus().equals(OrderStatus.PAYMENT_REFUSED)){
				paymentValue.setDisable(true);
			}
		}

	}
	
	@FXML
	private void payOrder(){
		if(Float.parseFloat(paymentValue.getText()) > 0){
			order.getPayment().setValue(Float.parseFloat(paymentValue.getText()));
			order.setOrderStatus(OrderStatus.WAITING_PAYMENT_APPROVAL);
		}
		
		OrderDAO database = new OrderDAO();
		database.updateOrder(order);
		RootController.alert("Pedido pago com sucesso!", "Pagamento realizado. Aguarde aprovação do gerente.", AlertType.CONFIRMATION);
	}
	
	@FXML
	private void approvePayment(){
		order.setOrderStatus(OrderStatus.PAYMENT_APPROVED);
		OrderDAO database = new OrderDAO();
		database.updateOrder(order);
		RootController.alert("Pagamento aprovado com sucesso!", "Pagamento aprovado", AlertType.CONFIRMATION);
	}	
	
	@FXML
	private void reprovePayment(){
		order.setOrderStatus(OrderStatus.PAYMENT_REFUSED);
		OrderDAO database = new OrderDAO();
		database.updateOrder(order);
		RootController.alert("Pagamento reprovado com sucesso!", "Pagamento reprovado", AlertType.ERROR);

	}

	public RootController getRoot() {
		return root;
	}

	public void setRoot(RootController root) {
		this.root = root;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}

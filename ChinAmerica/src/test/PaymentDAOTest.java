package test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import database.OrderDAO;
import database.PaymentDAO;
import model.Order;
import model.Payment;
import model.Product;
import model.ProductType;
import model.User;
import model.UserType;

public class PaymentDAOTest {

	@Test
	public void addGetAndDeleteTest() {
			Payment payment = new Payment();
			Float value = 1f;
			
			User orderer = new User();
			String cpf = "02364001005";
			String password = "teste";
			UserType userType = UserType.CLIENT;
			orderer.setCpf(cpf);
			orderer.setPassword(password);
			orderer.setUserType(userType);
			
			List<Product> products = new ArrayList<Product>();
			
			String productName1 = "produto 1";
			Float price1 = 1f;
			ProductType productType1 = ProductType.DESSERT;
			Product product1 = new Product();
			product1.setName(productName1);
			product1.setPrice(price1);
			product1.setProductType(productType1);
			
			String productName2 = "produto 2";
			Float price2 = 2f;
			ProductType productType2 = ProductType.FOOD;
			Product product2 = new Product();
			product2.setName(productName2);
			product2.setPrice(price2);
			product2.setProductType(productType2);
			
			products.add(product1);
			products.add(product2);
		
			Order testOrder = new Order();
			testOrder.setClient(orderer);
			testOrder.setOrderedProducts(products);
			
			OrderDAO dao = new OrderDAO();
			dao.addOrder(testOrder);
			List<Order>orders = dao.getAllOrders();
			testOrder = orders.get(0);
			
			payment.setOrder(testOrder);
			payment.setValue(value);

			
			PaymentDAO database = new PaymentDAO();
			
			database.addPayment(payment);
			
			Payment retrievedPayment = database.getPaymentByOrderId(testOrder.getId());
			
			Assert.assertEquals(retrievedPayment.getValue(), value);
			
			database.deletePayment(retrievedPayment.getId());
			
			retrievedPayment = database.getPaymentByOrderId(testOrder.getId());
			
			Assert.assertNull(retrievedPayment);

			
			
	}

}

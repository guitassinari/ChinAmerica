package test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import database.OrderDAO;
import model.Order;
import model.Product;
import model.ProductType;
import model.User;
import model.UserType;

public class OrderDAOTest {

	@Test
	public void addGetAndDeleteTest() {
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
		
		OrderDAO database = new OrderDAO();
		
		database.addOrder(testOrder);
		
		List<Order> orders = database.getAllOrders();
		
		Order retrievedOrder = orders.get(0);
				
		Assert.assertEquals(orderer.getCpf(), retrievedOrder.getClient().getCpf());
		
		database.deleteOrder(retrievedOrder.getId());
		
		retrievedOrder = database.getOrderById(retrievedOrder.getId());
		
		Assert.assertNull(retrievedOrder);
	}

}

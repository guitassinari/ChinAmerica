package test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import database.OrderDAO;
import model.Order;
import model.OrderedProduct;
import model.Product;
import model.ProductType;
import model.User;
import model.UserType;

public class OrderDAOTest {

	@Test
	public void addGetAndDeleteTest() {
		Order testOrder = new Order();

		User orderer = new User();
		String cpf = "02364001005";
		String password = "teste";
		UserType userType = UserType.CLIENT;
		orderer.setCpf(cpf);
		orderer.setPassword(password);
		orderer.setUserType(userType);
		
		List<OrderedProduct> orderedProducts  = new ArrayList<OrderedProduct>();
		
		String productName1 = "produto 1";
		Float price1 = 1f;
		ProductType productType1 = ProductType.DESSERT;
		Product product1 = new Product();
		product1.setName(productName1);
		product1.setPrice(price1);
		product1.setProductType(productType1);
		
		OrderedProduct orderedProduct1 = new OrderedProduct();
		orderedProduct1.setProduct(product1);
		orderedProduct1.setOrder(testOrder);
		orderedProduct1.setPrice(1f);
		orderedProduct1.setQuantity(1);
		
		String productName2 = "produto 2";
		Float price2 = 2f;
		ProductType productType2 = ProductType.FOOD;
		Product product2 = new Product();
		product2.setName(productName2);
		product2.setPrice(price2);
		product2.setProductType(productType2);
		
		OrderedProduct orderedProduct2 = new OrderedProduct();
		orderedProduct2.setProduct(product2);
		orderedProduct2.setOrder(testOrder);
		orderedProduct2.setPrice(1f);
		orderedProduct2.setQuantity(1);
		
		orderedProducts.add(orderedProduct1);
		orderedProducts.add(orderedProduct2);
	
		testOrder.setClient(orderer);
		testOrder.setOrderedProducts(orderedProducts);
		
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

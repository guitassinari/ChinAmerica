package test;

import org.junit.Assert;
import org.junit.Test;

import database.ProductDAO;
import model.Product;
import model.ProductType;

public class ProductDAOTest {

	@Test
	public void addAndDeleteGetByNameProductTest() {
		String name = "Produto Teste";
		Float price = 1f;
		ProductType type = ProductType.FOOD;
		Product product = new Product();
		product.setName(name);
		product.setPrice(price);
		product.setProductType(type);
		
		ProductDAO database = new ProductDAO();
		
		database.addProduct(product);
		
		Product retrievedProduct = database.getProductByName(name);
		
		Assert.assertEquals(name, retrievedProduct.getName());
		
		database.deleteProduct(retrievedProduct.getId());
		
		retrievedProduct = database.getProductByName(name);
		
		Assert.assertNull(retrievedProduct);
	}

}

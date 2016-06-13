package model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ORDERED_PRODUCTS")
public class OrderedProduct {

	private Integer Id;
	private Order order;
	private Product product;
	private Integer quantity;
	private Float price;

	public OrderedProduct(){
		
	}
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="order_id", nullable=false)
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="product_id", nullable=false)
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Column(name="product_quantity", nullable=false)
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Column(name="product_price", nullable=false)
	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

}

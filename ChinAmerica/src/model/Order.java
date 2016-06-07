package model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="ORDERS")
public class Order {

	private Integer id;
	private User client;
	private String description;
	private Payment payment;
	private User approvalManager;
	private List<Product> orderedProducts;
	
	public Order(){
		
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	//TODO: Map correctly
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="client_cpf", nullable=false)
	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}

	@Column(name="description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="payment_id")
	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	@ManyToOne
	@JoinColumn(name="manager_cpf")
	public User getApprovalManager() {
		return approvalManager;
	}

	public void setApprovalManager(User approvalManager) {
		this.approvalManager = approvalManager;
	}

	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinTable(name="ordered_products",
    	joinColumns = {@JoinColumn(name="product_id",nullable=false)},
    	inverseJoinColumns = {@JoinColumn(name="order_id",nullable=false)})
	public List<Product> getOrderedProducts() {
		return orderedProducts;
	}

	public void setOrderedProducts(List<Product> orderedProducts) {
		this.orderedProducts = orderedProducts;
	}

}

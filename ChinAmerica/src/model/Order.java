package model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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

	@Id
	//TODO: Sequence Generator
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	//TODO: Map correctly
	@ManyToOne
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

	//Map correctly
	@OneToOne
	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	@ManyToOne
	public User getApprovalManager() {
		return approvalManager;
	}

	public void setApprovalManager(User approvalManager) {
		this.approvalManager = approvalManager;
	}

	@ManyToMany
	public List<Product> getOrderedProducts() {
		return orderedProducts;
	}

	public void setOrderedProducts(List<Product> orderedProducts) {
		this.orderedProducts = orderedProducts;
	}

}

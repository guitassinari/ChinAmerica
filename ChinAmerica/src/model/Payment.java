package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PAYMENTS")
public class Payment {

	private Integer id;
	private Float value;
	private Order order;
	private User approvalManager;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//TODO: Sequence generator
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name="value", nullable=false)
	public Float getValue() {
		return value;
	}

	public void setValue(Float value) {
		this.value = value;
	}

	@OneToOne
	@JoinColumn(name="order_id", nullable=false)
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@ManyToOne
	@JoinColumn(name="manager_cpf")
	public User getApprovalManager() {
		return approvalManager;
	}

	public void setApprovalManager(User approvalManager) {
		this.approvalManager = approvalManager;
	}

}

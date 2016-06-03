package model;

public enum OrderStatus {
	
	CANCELLED(1), ORDER_APPROVED(2), ORDER_REFUSED(3), PAYMENT_APPROVED(4), PAYMENT_REFUSED(5), WAITING_ORDER_APPROVAL(6), WAITING_PAYMENT_APPROVAL(7);
	
	private int id;
	
	OrderStatus(int id){
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
}

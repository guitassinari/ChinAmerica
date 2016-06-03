package model;

public enum ProductType {
	FOOD(1),DRINK(2),DESSERT(3);
	
	private int id;
	
	ProductType(int id){
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
}

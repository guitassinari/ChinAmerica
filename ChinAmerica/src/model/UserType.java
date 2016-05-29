package model;

public enum UserType {
	CLIENT(1),MANAGER(2);
	
	private int id;
	
	UserType(int id){
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
}

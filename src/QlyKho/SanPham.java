package QlyKho;

public class SanPham {
	private String ID;
	private String Name;
	private int Amount;
	private int Price;
	
	SanPham(){}
	SanPham(String id, String name, int amount , int price){
		this.ID = id;
		this.Name = name;
		this.Amount = amount;
		this.Price = price;
	}
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getAmount() {
		return Amount;
	}
	public void setAmount(int amount) {
		Amount = amount;
	}
	public int getPrice() {
		return Price;
	}
	public void setPrice(int price) {
		Price = price;
	}

}

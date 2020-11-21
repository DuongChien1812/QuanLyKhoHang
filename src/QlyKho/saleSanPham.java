package QlyKho;

public class saleSanPham {
	private String ID;
	private int Amount;
	private int Price;
	private String Date;

	saleSanPham() {
	}

	saleSanPham(String id, int amount, int price,String date) {
		this.ID = id;
		this.Amount = amount;
		this.Price = price;
		this.Date = date;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
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

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		this.Date = date;
	}
}

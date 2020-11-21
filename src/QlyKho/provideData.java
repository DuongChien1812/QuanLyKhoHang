package QlyKho;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class provideData {
	private Connection conn = null;

	provideData() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(
					"jdbc:sqlserver://ADMIN\\SQLEXPRESS:1433;databaseName=QlyKhoHang;integratedSecurity=true");
			System.out.println("thanh cong");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ------------------------------For card 1
	// -------------------------------------
	/**
	 * This method to get item from database to Table
	 * 
	 * @param query
	 * @param parameter
	 * @return list
	 */
	public ArrayList<SanPham> getListItem(String query, String parameter) {
		ArrayList<SanPham> list = new ArrayList<>();
		String sql = query + parameter;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				SanPham s = new SanPham();
				s.setID(rs.getString(1));
				s.setName(rs.getString(2));
				s.setAmount(rs.getInt(3));
				s.setPrice(rs.getInt(4));
				list.add(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * This method to check everything from something, it's really usefull
	 * 
	 * @param query
	 * @param parameter
	 * @return boolean
	 */
	public boolean checkItem(String query, String parameter) {
		String sql = query + " '" + parameter + "'";
		boolean result = false;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getRow() > 0) {
					result = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// ------------------------------For card 2
	// -------------------------------------
	/**
	 * This method to insert Item to SQL
	 * 
	 * @param s
	 * @return
	 */
	public boolean addItem(SanPham s) {
		String sql = "insert into SanPham(ID, Name, Amount, Price) values (?, ?, ?, ?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, s.getID());
			ps.setString(2, s.getName());
			ps.setInt(3, s.getAmount());
			ps.setInt(4, s.getPrice());
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * This method to update old item about amount but this is import
	 * 
	 * @param s
	 * @return true or false
	 */
	public boolean updateItem(String ID, int Amount) {
		String sql = "execute Import '" + ID + "'," + Amount;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// ------------------------------For card 3
	// -------------------------------------
	/**
	 * This method to check the amount of item
	 * 
	 * @param ID
	 * @param available amount
	 * @return
	 */
	public boolean checkExport(String ID, int Amount) {
		String sql = "execute checkExport '" + ID + "'," + Amount;
		boolean result = false;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getRow() > 0) {
					result = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * This method to find the price of item
	 * 
	 * @param ID
	 * @return the price of item
	 */
	public int checkPrice(String ID) {
		String sql = "execute checkPrice '" + ID + "'";
		int price = 0;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				price = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return price;
	}

	/**
	 * This method to update old item about amount
	 * 
	 * @param s
	 * @return true or false
	 */
	public boolean updateExport(String ID, int Amount) {
		String sql = "execute Export '" + ID + "'," + Amount;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * insert item in Xuat table
	 * 
	 * @param sale
	 * @return
	 */
	public boolean insertReceipt(saleSanPham sale) {
		String sql = "insert into Xuat(ID, Amount, Price, saleDate) values (?, ?, ?, ?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, sale.getID());
			ps.setInt(2, sale.getAmount());
			ps.setInt(3, sale.getPrice());
			ps.setString(4, sale.getDate());
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	// ------------------------------For Receipt
	// Manager-------------------------------------
	/**
	 * This method to get item from database to Table
	 * 
	 * @param query
	 * @param parameter
	 * @return list
	 */
	public ArrayList<saleSanPham> getListItemSale(String query, String parameter) {
		ArrayList<saleSanPham> listSale = new ArrayList<>();
		String sql = query + parameter;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				saleSanPham sale = new saleSanPham();
				sale.setID(rs.getString(1));
				sale.setAmount(rs.getInt(2));
				sale.setPrice(rs.getInt(3));
				sale.setDate(rs.getString(4));
				listSale.add(sale);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listSale;
	}

	/**
	 * Revenue of the day
	 * 
	 * @param date
	 * @return Revenue
	 */
	public int revenueOfDay(String date) {
		String sql = "execute revenueOfDay '" + date + "'";
		int price = 0;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				price = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return price;
	}
	
	/**
	 * Revenue of the month
	 * 
	 * @param date
	 * @return Revenue
	 */
	public int revenueOfMonth(String month, String year) {
		String sql = "execute revenueOfMonth '" + month + "' , '" + year + "'";
		int price = 0;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				price = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return price;
	}
}

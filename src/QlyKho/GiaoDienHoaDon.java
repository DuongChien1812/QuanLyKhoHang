package QlyKho;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class GiaoDienHoaDon extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JDateChooser dateChooser;
	private JLabel lblNewLabel;
	private JButton btnSearch;
	private JLabel lblSaleDate;
	private Date date = new Date();
	String dateInput;
	private JLabel lblNgyBn;
	private JLabel lblDoanhThuNgy;
	private JLabel lblDaysRevenue;
	private JLabel lblNgyBn_1_1;
	private JLabel lblMonthsRevenue;
	private JLabel lblNewLabel_1;
	private ArrayList<saleSanPham> listSale;
	DefaultTableModel model;
	private JScrollPane scrollPane;
	private JTable tableReceipt;
	byte i;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GiaoDienHoaDon frame = new GiaoDienHoaDon();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GiaoDienHoaDon() {
		setBackground(Color.WHITE);
		setForeground(Color.WHITE);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\java\\QanLyKhoHang-add\\inventory.png"));
		setTitle("Quản lý hóa đơn");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 769, 500);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getDateChooser());
		contentPane.add(getLblNewLabel());
		contentPane.add(getLblSaleDate());
		contentPane.add(getBtnSearch());
		contentPane.add(getLblNgyBn());
		contentPane.add(getLblDoanhThuNgy());
		contentPane.add(getLblDaysRevenue());
		contentPane.add(getLblNgyBn_1_1());
		contentPane.add(getLblMonthsRevenue());
		contentPane.add(getScrollPane());
		contentPane.add(getLblNewLabel_1());
	}

	private JDateChooser getDateChooser() {
		if (dateChooser == null) {
			dateChooser = new JDateChooser();
			dateChooser.getCalendarButton().setIcon(new ImageIcon("D:\\java\\QanLyKhoHang-add\\calendar.png"));
			dateChooser.getCalendarButton().setBackground(Color.WHITE);
			dateChooser.setBounds(364, 63, 203, 27);

		}
		return dateChooser;
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Chọn ngày bán:");
			lblNewLabel.setBackground(Color.WHITE);
			lblNewLabel.setForeground(Color.WHITE);
			lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
			lblNewLabel.setBounds(231, 54, 123, 36);
		}
		return lblNewLabel;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 101, 490, 359);
			scrollPane.setViewportView(getTableReceipt());
		}
		return scrollPane;
	}

	private JTable getTableReceipt() {
		if (tableReceipt == null) {
			tableReceipt = new JTable();
			model = (DefaultTableModel) tableReceipt.getModel();
			model.setColumnIdentifiers(new Object[] { "STT", "Mã sản phẩm", "Số lượng", "Giá bán" });
			dateInput = new SimpleDateFormat("yyyy-MM-dd").format(date);
			showItemSale(dateInput);
		}
		return tableReceipt;
	}

	private JLabel getLblSaleDate() {
		if (lblSaleDate == null) {
			lblSaleDate = new JLabel("New label");
			lblSaleDate.setForeground(new Color(0, 206, 209));
			lblSaleDate.setFont(new Font("Arial", Font.BOLD, 17));
			lblSaleDate.setBounds(642, 126, 111, 36);
			dateInput = new SimpleDateFormat("dd-MM-yyyy").format(date);
			lblSaleDate.setText(dateInput);
		}
		return lblSaleDate;
	}

	private JButton getBtnSearch() {
		if (btnSearch == null) {
			btnSearch = new JButton("");
			btnSearch.setBackground(new Color(72, 209, 204));
			btnSearch.setIcon(new ImageIcon("D:\\java\\QanLyKhoHang-add\\search.png"));
			btnSearch.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					model.setRowCount(0);
					date = (Date) dateChooser.getDate();
					dateInput = new SimpleDateFormat("yyyy-MM-dd").format(date);
					showItemSale(dateInput);

					String dayRevenue = String.valueOf(new provideData().revenueOfDay(dateInput));
					lblDaysRevenue.setText(dayRevenue);

					String month = new SimpleDateFormat("MM").format(date);
					String year = new SimpleDateFormat("yyyy").format(date);
					String monthRevenue = String.valueOf(new provideData().revenueOfMonth(month, year));
					lblMonthsRevenue.setText(monthRevenue);

					dateInput = new SimpleDateFormat("dd-MM-yyyy").format(date);
					lblSaleDate.setText(dateInput);
				}
			});
			btnSearch.setBounds(588, 63, 97, 27);
		}
		return btnSearch;
	}

	private JLabel getLblNgyBn() {
		if (lblNgyBn == null) {
			lblNgyBn = new JLabel("Ngày bán:");
			lblNgyBn.setForeground(Color.DARK_GRAY);
			lblNgyBn.setFont(new Font("Segoe UI", Font.BOLD, 15));
			lblNgyBn.setBounds(543, 125, 104, 36);
		}
		return lblNgyBn;
	}

	private JLabel getLblDoanhThuNgy() {
		if (lblDoanhThuNgy == null) {
			lblDoanhThuNgy = new JLabel("Doanh thu ngày:");
			lblDoanhThuNgy.setForeground(Color.DARK_GRAY);
			lblDoanhThuNgy.setFont(new Font("Segoe UI", Font.BOLD, 15));
			lblDoanhThuNgy.setBounds(505, 187, 142, 36);
		}
		return lblDoanhThuNgy;
	}

	private JLabel getLblDaysRevenue() {
		if (lblDaysRevenue == null) {
			lblDaysRevenue = new JLabel("2020-05-25");
			lblDaysRevenue.setForeground(new Color(255, 99, 71));
			lblDaysRevenue.setFont(new Font("Arial", Font.BOLD, 18));
			lblDaysRevenue.setBounds(642, 188, 111, 36);
			dateInput = new SimpleDateFormat("yyyy-MM-dd").format(date);
			String dayRevenue = String.valueOf(new provideData().revenueOfDay(dateInput));
			lblDaysRevenue.setText(dayRevenue);
		}
		return lblDaysRevenue;
	}

	private JLabel getLblNgyBn_1_1() {
		if (lblNgyBn_1_1 == null) {
			lblNgyBn_1_1 = new JLabel("Doanh thu tháng:");
			lblNgyBn_1_1.setForeground(Color.DARK_GRAY);
			lblNgyBn_1_1.setFont(new Font("Segoe UI", Font.BOLD, 15));
			lblNgyBn_1_1.setBounds(505, 252, 142, 36);
		}
		return lblNgyBn_1_1;
	}

	private JLabel getLblMonthsRevenue() {
		if (lblMonthsRevenue == null) {
			lblMonthsRevenue = new JLabel("2020-05-25");
			lblMonthsRevenue.setForeground(new Color(0, 191, 255));
			lblMonthsRevenue.setFont(new Font("Arial", Font.BOLD, 18));
			lblMonthsRevenue.setBounds(642, 253, 111, 36);
			String month = new SimpleDateFormat("MM").format(date);
			String year = new SimpleDateFormat("yyyy").format(date);
			String monthRevenue = String.valueOf(new provideData().revenueOfMonth(month, year));
			lblMonthsRevenue.setText(monthRevenue);
		}
		return lblMonthsRevenue;
	}

	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("");
			lblNewLabel_1.setIcon(new ImageIcon("D:\\java\\QanLyKhoHang-add\\Picture5.png"));
			lblNewLabel_1.setBackground(Color.DARK_GRAY);
			lblNewLabel_1.setBounds(-40, 0, 803, 144);
		}
		return lblNewLabel_1;
	}

	/**
	 * Show danh sách sản phẩm bán
	 */
	public void showItemSale(String date) {
		listSale = new provideData().getListItemSale("Select * from Xuat where saleDate = '", date + "'");
		i = 0;
		for (saleSanPham sale : listSale) {
			model.addRow(new Object[] { ++i, sale.getID(), sale.getAmount(), sale.getPrice() });
		}
	}
}

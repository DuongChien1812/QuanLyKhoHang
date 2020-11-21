package QlyKho;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.SwingConstants;
import javax.swing.ListSelectionModel;
public class giaoDienView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtSearch;
	private JTextField txtID;
	private JTextField txtName;
	private JTextField txtAmount;
	private JTextField txtPrice;
	private ArrayList<SanPham> list;
	private ArrayList<saleSanPham> listSale = new ArrayList<>();;
	DefaultTableModel model;
	DefaultTableModel model1;
	DefaultTableModel model2;
	private JTable tblShow;
	private JTextField txtSearch2;
	private JTable tblCheck;
	private JTextField txtChooseAmount;
	private JTable tblReceipt;
	private JTextField txtDiscount;
	Date date = new Date();
	String dateInput = new SimpleDateFormat("yyyy-MM-dd").format(date);
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					giaoDienView frame = new giaoDienView();
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
	public giaoDienView() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\java\\QanLyKhoHang-add\\inventory.png"));
		setTitle("Qu\u1EA3n l\u00FD kho h\u00E0ng");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1048, 594);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		/**
		 * Method to show the right
		 */
		JPanel pnlCard = new JPanel();
		pnlCard.setBounds(285, 0, 754, 568);
		contentPane.add(pnlCard);
		pnlCard.setLayout(new CardLayout(0, 0));
		
		//----------------pnlCard1-------------------
		JPanel pnlCard1 = new JPanel();
		pnlCard1.setBackground(new Color(255, 255, 255));
		pnlCard.add(pnlCard1, "pnlCard1");
		pnlCard1.setLayout(null);
		
		JButton btnSearch = new JButton("");
		btnSearch.setIcon(new ImageIcon("D:\\java\\QanLyKhoHang-add\\search.png"));
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String search = txtSearch.getText().toUpperCase();
				model.setRowCount(0);
				Finding(search);	
			}
		});
		btnSearch.setBackground(new Color(0, 128, 128));
		btnSearch.setForeground(new Color(255, 255, 255));
		btnSearch.setBounds(401, 50, 133, 27);
		pnlCard1.add(btnSearch);
		
		txtSearch = new JTextField();
		txtSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSearch.doClick();
			}
		});
		txtSearch.setBounds(50, 50, 327, 27);
		pnlCard1.add(txtSearch);
		txtSearch.setColumns(10);
		
		
		
		JLabel lblNewLabel_2 = new JLabel("Nh\u1EADp s\u1EA3n ph\u1EA9m c\u1EA7n t\u00ECm ki\u1EBFm:");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_2.setBounds(50, 11, 209, 28);
		pnlCard1.add(lblNewLabel_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 111, 734, 446);
		pnlCard1.add(scrollPane);
		
		tblShow = new JTable();
		tblShow.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		scrollPane.setViewportView(tblShow);		
		model = (DefaultTableModel) tblShow.getModel();
		model.setColumnIdentifiers(new Object[] {
			 "Mã sản phẩm", "Tên sản phẩm", "Số lượng"
		});
		showItem();
		
		
		JButton btnRefresh = new JButton("REFRESH");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);
				showItem();
			}
		});
		btnRefresh.setIcon(new ImageIcon("D:\\java\\QanLyKhoHang-add\\refresh1.png"));
		btnRefresh.setFont(new Font("Arial Black", Font.PLAIN, 13));
		btnRefresh.setForeground(new Color(0, 191, 255));
		btnRefresh.setBackground(Color.WHITE);
		btnRefresh.setBounds(604, 50, 140, 50);
		pnlCard1.add(btnRefresh);
		
		
		//----------------pnlCard2-------------------
		JPanel pnlCard2 = new JPanel();
		pnlCard2.setBackground(new Color(255, 255, 255));
		pnlCard.add(pnlCard2, "pnlCard2");
		pnlCard2.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Nhập mã sản phẩm(*): ");
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_3.setBounds(109, 127, 181, 36);
		pnlCard2.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("Tên sản phẩm(*):");
		lblNewLabel_3_1.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_3_1.setBounds(109, 211, 146, 36);
		pnlCard2.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("Số lượng(*):");
		lblNewLabel_3_1_1.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_3_1_1.setBounds(109, 271, 146, 36);
		pnlCard2.add(lblNewLabel_3_1_1);
		
		JLabel lblNewLabel_3_1_1_1 = new JLabel("Giá(*): ");
		lblNewLabel_3_1_1_1.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_3_1_1_1.setBounds(109, 329, 146, 36);
		pnlCard2.add(lblNewLabel_3_1_1_1);
		
		JLabel lblMessage = new JLabel("");
		lblMessage.setForeground(new Color(0, 153, 204));
		lblMessage.setFont(new Font("Arial", Font.BOLD, 13));
		lblMessage.setBounds(317, 174, 427, 26);
		pnlCard2.add(lblMessage);
		
		JLabel lblMessage2 = new JLabel("Thêm thành công!");
		lblMessage2.setIcon(new ImageIcon("D:\\java\\QanLyKhoHang-add\\tick.png"));
		lblMessage2.setForeground(new Color(0, 204, 102));
		lblMessage2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMessage2.setBounds(265, 478, 219, 54);
		pnlCard2.add(lblMessage2);
		lblMessage2.setVisible(false);
		
		JLabel lblMessage3 = new JLabel("Điền thiếu thông tin!");
		lblMessage3.setIcon(new ImageIcon("D:\\java\\QanLyKhoHang-add\\cross.png"));
		lblMessage3.setForeground(new Color(255, 0, 102));
		lblMessage3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMessage3.setBounds(265, 478, 219, 54);
		pnlCard2.add(lblMessage3);
		lblMessage3.setVisible(false);
		
		JButton btnCancel = new JButton("H\u1EE7y");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtID.setText(null);
				txtAmount.setText(null);
				txtName.setText(null);
				txtPrice.setText(null);
				lblMessage.setText(null);
				lblMessage2.setVisible(false);
				lblMessage3.setVisible(false);
				lblNewLabel_3_1.setVisible(true);
				lblNewLabel_3_1_1_1.setVisible(true);
				txtName.setVisible(true);
				txtPrice.setVisible(true);
			}
		});
		btnCancel.setFont(new Font("Arial", Font.BOLD, 13));
		btnCancel.setBackground(new Color(0, 128, 128));
		btnCancel.setForeground(new Color(255, 255, 255));
		btnCancel.setBounds(433, 411, 156, 36);
		pnlCard2.add(btnCancel);
		
		JButton btnConfirmNew = new JButton("Thêm sản phẩm");
		btnConfirmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkEmptyNew()) { // method below to check empty
					lblMessage3.setVisible(true);
					lblMessage2.setVisible(false);
				}else {		
					lblMessage3.setVisible(false);
					SanPham s = new SanPham();
					s.setID(txtID.getText().toUpperCase());
					s.setName(txtName.getText());
					s.setAmount(Integer.parseInt(txtAmount.getText()));
					s.setPrice(Integer.parseInt(txtPrice.getText()));
					new provideData().addItem(s);
					btnCancel.doClick();
					lblMessage2.setVisible(true);
				}	
			}
		});
		btnConfirmNew.setFont(new Font("Arial", Font.BOLD, 13));
		btnConfirmNew.setBackground(new Color(0, 128, 128));
		btnConfirmNew.setForeground(new Color(255, 255, 255));
		btnConfirmNew.setBounds(165, 411, 156, 36);
		pnlCard2.add(btnConfirmNew);
		btnConfirmNew.setVisible(false);
		
		JButton btnConfirmOld = new JButton("Xác nhận");
		btnConfirmOld.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(checkEmptyOld()) { // method below to check empty
					lblMessage3.setVisible(true);
					lblMessage2.setVisible(false);
				}else {		
					lblMessage3.setVisible(false);
					String ID = txtID.getText();
					int Amount = Integer.parseInt(txtAmount.getText());
					new provideData().updateItem(ID, Amount);
					lblMessage2.setVisible(true);
				}
			}
		});
		btnConfirmOld.setForeground(Color.WHITE);
		btnConfirmOld.setFont(new Font("Arial", Font.BOLD, 13));
		btnConfirmOld.setBackground(new Color(0, 128, 128));
		btnConfirmOld.setBounds(165, 411, 156, 36);
		pnlCard2.add(btnConfirmOld);
		btnConfirmOld.setVisible(false);
		
		txtID = new JTextField();
		txtID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				if(new provideData().checkItem("execute checkID ", txtID.getText().toString())) { // proc kiểm tra ID nếu phải  thì chỉ cần nhập số lượng còn lại là nhập thông tin
						lblMessage.setText("Bạn đang nhập sản phẩm có sẵn! Mời nhập số lượng!");
						txtName.setVisible(false);
						txtPrice.setVisible(false);
						lblNewLabel_3_1.setVisible(false);
						lblNewLabel_3_1_1_1.setVisible(false);
						btnConfirmOld.setVisible(true);
						btnConfirmNew.setVisible(false);
				}else {
						lblMessage.setText("Bạn đang nhập sản phẩm mới! Mời nhập thông tin!");
						txtName.setVisible(true);
						txtPrice.setVisible(true);
						lblNewLabel_3_1.setVisible(true);
						lblNewLabel_3_1_1_1.setVisible(true);
						btnConfirmOld.setVisible(false);
						btnConfirmNew.setVisible(true);
				}
			}
		});
		txtID.setBounds(307, 130, 268, 27);
		pnlCard2.add(txtID);
		txtID.setColumns(10);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(307, 217, 268, 27);
		pnlCard2.add(txtName);
		
		txtAmount = new JTextField();
		txtAmount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if( !(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) )) {
					e.consume();
				}
			}
		});
		txtAmount.setColumns(10);
		txtAmount.setBounds(307, 277, 268, 27);
		pnlCard2.add(txtAmount);
		
		
		txtPrice = new JTextField();
		txtPrice.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c= e.getKeyChar();
				if(!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					e.consume();
				}
			}
		});
		txtPrice.setColumns(10);
		txtPrice.setBounds(307, 335, 268, 27);
		pnlCard2.add(txtPrice);

		
			
		//----------------pnlCard3-------------------
		JPanel pnlCard3 = new JPanel();
		pnlCard.add(pnlCard3, "pnlCard3");
		pnlCard3.setLayout(null);
		
		JLabel lblNewLabel_6 = new JLabel(" Nhập mã sp:");
		lblNewLabel_6.setFont(new Font("Arial", Font.BOLD, 13));
		lblNewLabel_6.setBounds(10, 22, 92, 23);
		pnlCard3.add(lblNewLabel_6);
		
		JButton btnSearch2 = new JButton("");
		btnSearch2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model1.setRowCount(0);
				FindForChecking(txtSearch2.getText());
			}
		});
		btnSearch2.setIcon(new ImageIcon("D:\\java\\QanLyKhoHang-add\\search.png"));
		btnSearch2.setBackground(new Color(0, 128, 128));
		btnSearch2.setForeground(new Color(255, 255, 255));
		btnSearch2.setBounds(538, 22, 129, 35);
		pnlCard3.add(btnSearch2);
		
		txtSearch2 = new JTextField();
		txtSearch2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtSearch2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSearch2.doClick();
			}
		});
		txtSearch2.setBounds(127, 21, 350, 36);
		pnlCard3.add(txtSearch2);
		txtSearch2.setColumns(10);
		
		JLabel lblChoose = new JLabel("");
		lblChoose.setForeground(new Color(220, 20, 60));
		lblChoose.setFont(new Font("Arial", Font.BOLD, 15));
		lblChoose.setBounds(148, 202, 254, 35);
		pnlCard3.add(lblChoose);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(127, 58, 350, 128);
		pnlCard3.add(scrollPane_1);
		
		tblCheck = new JTable();
		tblCheck.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = tblCheck.getSelectedRow();
				SanPham s = list.get(i);	
				lblChoose.setText(s.getID());
			}
		});
		scrollPane_1.setViewportView(tblCheck);
		model1 = (DefaultTableModel) tblCheck.getModel();
		model1.setColumnIdentifiers(new Object[] {
			 "Mã sản phẩm"
		});
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 248, 490, 278);
		pnlCard3.add(scrollPane_2);
		
		tblReceipt = new JTable();
		tblReceipt.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		tblReceipt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tblReceipt.setForeground(new Color(0, 128, 128));
		scrollPane_2.setViewportView(tblReceipt);
		model2 = (DefaultTableModel) tblReceipt.getModel();
		model2.setColumnIdentifiers(new Object[] {
			"STT" , "Mã sản phẩm", "Số lượng", "Giá tiền"
		});
		tblReceipt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		TableColumn col = tblReceipt.getColumnModel().getColumn(0); // cot stt
		col.setPreferredWidth(64);
		
		col = tblReceipt.getColumnModel().getColumn(1); // cot ma san pham
		col.setPreferredWidth(204);
		
		col = tblReceipt.getColumnModel().getColumn(2);// cot so lương
		col.setPreferredWidth(75);
		
		col = tblReceipt.getColumnModel().getColumn(3); // cot gia 
		col.setPreferredWidth(144);
		
		
		JLabel lblNewLabel_7 = new JLabel("Sản phẩm đã chọn: ");
		lblNewLabel_7.setFont(new Font("Arial", Font.BOLD, 13));
		lblNewLabel_7.setBounds(10, 202, 139, 35);
		pnlCard3.add(lblNewLabel_7);
		
		
		
		JLabel lblNewLabel_7_1 = new JLabel("Số lượng:");
		lblNewLabel_7_1.setFont(new Font("Arial", Font.BOLD, 13));
		lblNewLabel_7_1.setBounds(363, 203, 84, 35);
		pnlCard3.add(lblNewLabel_7_1);
		
		txtChooseAmount = new JTextField();
		txtChooseAmount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c= e.getKeyChar();
				if(!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) )) {
					e.consume();
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				
			}
		});
		txtChooseAmount.setForeground(new Color(220, 20, 60));
		txtChooseAmount.setHorizontalAlignment(SwingConstants.CENTER);
		txtChooseAmount.setFont(new Font("Arial", Font.BOLD, 14));
		txtChooseAmount.setText("1");
		txtChooseAmount.setBounds(443, 202, 57, 35);
		pnlCard3.add(txtChooseAmount);
		txtChooseAmount.setColumns(10);
		
		JLabel lblBeforeCash = new JLabel("0");
		lblBeforeCash.setForeground(new Color(46, 139, 87));
		lblBeforeCash.setFont(new Font("Arial", Font.BOLD, 20));
		lblBeforeCash.setBounds(605, 275, 145, 35);
		pnlCard3.add(lblBeforeCash);
		
		JLabel lblAfterCash = new JLabel("0");
		lblAfterCash.setForeground(new Color(255, 0, 0));
		lblAfterCash.setFont(new Font("Arial", Font.BOLD, 20));
		lblAfterCash.setBounds(605, 373, 145, 35);
		pnlCard3.add(lblAfterCash);
		
		txtDiscount = new JTextField();
		txtDiscount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblAfterCash.setText(formatCurrency(getPrice() - (int)(getPrice()*0.01*Integer.parseInt(txtDiscount.getText()))));
			}
		});
		txtDiscount.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c= e.getKeyChar();
				if(!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					e.consume();
				}
			}
		});
		txtDiscount.setText("0");
		txtDiscount.setHorizontalAlignment(SwingConstants.CENTER);
		txtDiscount.setForeground(new Color(0, 128, 0));
		txtDiscount.setFont(new Font("Arial", Font.BOLD, 20));
		txtDiscount.setColumns(10);
		txtDiscount.setBounds(606, 321, 57, 35);
		pnlCard3.add(txtDiscount);
		
		
		
		JButton btnChoose = new JButton("");
		btnChoose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtChooseAmount.getText().trim().isEmpty() || lblChoose.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập đủ thông tin!");
				}else if(new provideData().checkExport(lblChoose.getText(),Integer.parseInt(txtChooseAmount.getText()))== false) {
					JOptionPane.showMessageDialog(rootPane, "Số lượng bán nhiều hơn tồn kho!");
				}else {
					int price =  Integer.parseInt(txtChooseAmount.getText()) * (new provideData().checkPrice(lblChoose.getText()));						
					saleSanPham sale = new saleSanPham();
					sale.setID(lblChoose.getText());
					sale.setAmount(Integer.parseInt(txtChooseAmount.getText()));
				    sale.setPrice(price);
				    sale.setDate(dateInput);
				    listSale.add(sale);
					getListSale();
					lblBeforeCash.setText(formatCurrency(getPrice()));
					lblAfterCash.setText(formatCurrency(getPrice() - (int)(getPrice()*0.01*Integer.parseInt(txtDiscount.getText()))));
				}
			}
		});
		btnChoose.setIcon(new ImageIcon("D:\\java\\QanLyKhoHang-add\\supermarket.png"));
		btnChoose.setBackground(new Color(0, 128, 128));
		btnChoose.setForeground(new Color(255, 255, 255));
		btnChoose.setBounds(555, 202, 112, 35);
		pnlCard3.add(btnChoose);
		
		
		
		JLabel lblNewLabel_8 = new JLabel("Giá niêm yết: ");
		lblNewLabel_8.setFont(new Font("Arial", Font.BOLD, 13));
		lblNewLabel_8.setBounds(510, 276, 98, 35);
		pnlCard3.add(lblNewLabel_8);
		
		
		
		JLabel lblNewLabel_7_1_1 = new JLabel("Giảm giá:");
		lblNewLabel_7_1_1.setFont(new Font("Arial", Font.BOLD, 13));
		lblNewLabel_7_1_1.setBounds(512, 322, 84, 35);
		pnlCard3.add(lblNewLabel_7_1_1);
		
		
		JLabel lblChoose_1_1 = new JLabel("%");
		lblChoose_1_1.setForeground(Color.BLACK);
		lblChoose_1_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblChoose_1_1.setBounds(668, 321, 38, 35);
		pnlCard3.add(lblChoose_1_1);
		
		JLabel lblNewLabel_8_1 = new JLabel("Thành tiền: ");
		lblNewLabel_8_1.setFont(new Font("Arial", Font.BOLD, 13));
		lblNewLabel_8_1.setBounds(510, 373, 98, 35);
		pnlCard3.add(lblNewLabel_8_1);
		
		
		JButton btnAccept = new JButton("Xác nhận đơn hàng");
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(saleSanPham sa : listSale) {
					new provideData().updateExport(sa.getID(), sa.getAmount());
					new provideData().insertReceipt(sa);
				}
				JOptionPane.showMessageDialog(rootPane, "Thanh toán thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				listSale.removeAll(listSale);
				model2.setRowCount(0);
				lblChoose.setText(null);
				txtChooseAmount.setText(""+1);
				lblAfterCash.setText(""+0);
				lblBeforeCash.setText(""+0);
				txtDiscount.setText(""+0);
			}
		});
		btnAccept.setFont(new Font("Arial", Font.BOLD, 15));
		btnAccept.setForeground(Color.WHITE);
		btnAccept.setBackground(new Color(0, 128, 128));
		btnAccept.setBounds(521, 464, 210, 35);
		pnlCard3.add(btnAccept);
		
		JButton btnDelete = new JButton("Xóa");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = tblReceipt.getSelectedRow();
				if( selectedRow == -1 ) {
					JOptionPane.showMessageDialog(rootPane, "Chưa chọn hàng nào!");
				}else if(listSale.size()==0){
					JOptionPane.showMessageDialog(rootPane, "Không có mục xóa!");
				}
				else {
					listSale.remove(selectedRow);
					model2.setRowCount(0);
					getListSale2();	
					lblBeforeCash.setText(formatCurrency(getPrice()));
					lblAfterCash.setText(formatCurrency(getPrice() - (int)(getPrice()*0.01*Integer.parseInt(txtDiscount.getText()))));
				}
				
			}
				
		});
		btnDelete.setBackground(new Color(255, 51, 51));
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnDelete.setBounds(190, 531, 112, 30);
		pnlCard3.add(btnDelete);
		
		
		
		
		
	
		
		/**
		 * Method to show the left 
		 */
		
		JPanel FlowChart = new JPanel();
		FlowChart.setBounds(0, 0, 275, 568);
		FlowChart.setBackground(Color.DARK_GRAY);
		contentPane.add(FlowChart);
		FlowChart.setLayout(null);
	
		JButton btnShow = new JButton("KHO H\u00C0NG");
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cardLayout = (CardLayout)(pnlCard.getLayout());
				cardLayout.show(pnlCard, "pnlCard1");
			}
		});
		btnShow.addMouseListener(new MouseAdapter() {			
			@Override
			public void mouseEntered(MouseEvent e) {
				btnShow.setForeground(new Color(32, 178, 170));
				btnShow.setBackground(new Color(245, 255, 250));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnShow.setBackground(new Color(32, 178, 170));
				btnShow.setForeground(new Color(245, 255, 250));
			}
		});
		btnShow.setBackground(new Color(32, 178, 170));
		btnShow.setForeground(new Color(245, 255, 250));
		btnShow.setFont(new Font("Arial", Font.BOLD, 18));
		btnShow.setBounds(10, 198, 255, 61);
		FlowChart.add(btnShow);
		
		JButton btnImport = new JButton("NH\u1EACP H\u00C0NG");
		btnImport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cardLayout = (CardLayout)(pnlCard.getLayout());
				cardLayout.show(pnlCard, "pnlCard2");
			}
		});
		btnImport.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseEntered(MouseEvent e) {
				btnImport.setForeground(new Color(32, 178, 170));
				btnImport.setBackground(new Color(255, 255, 255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnImport.setBackground(new Color(32, 178, 170));
				btnImport.setForeground(new Color(255, 255, 255));
			}
			
		});
		btnImport.setBackground(new Color(32, 178, 170));
		btnImport.setForeground(new Color(255, 255, 255));
		btnImport.setFont(new Font("Arial", Font.BOLD, 18));
		btnImport.setBounds(10, 299, 255, 61);
		FlowChart.add(btnImport);
		
		JButton btnExport = new JButton("XU\u1EA4T H\u00C0NG");
		btnExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cardLayout = (CardLayout)(pnlCard.getLayout());
				cardLayout.show(pnlCard, "pnlCard3");
			}
		});
		btnExport.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnExport.setBackground(new Color(255, 255, 255));
				btnExport.setForeground(new Color(32, 178, 170));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnExport.setForeground(new Color(255, 255, 255));
				btnExport.setBackground(new Color(32, 178, 170));
			}
		});
		btnExport.setForeground(new Color(255, 255, 255));
		btnExport.setBackground(new Color(32, 178, 170));
		btnExport.setFont(new Font("Arial", Font.BOLD, 18));
		btnExport.setBounds(10, 400, 255, 61);
		FlowChart.add(btnExport);
		
		JLabel lblNewLabel = new JLabel("  Design by Team 3");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(60, 515, 174, 29);
		FlowChart.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("@2020");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(109, 539, 134, 18);
		FlowChart.add(lblNewLabel_1);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setIcon(new ImageIcon("D:\\java\\QanLyKhoHang-add\\Screenshot (42).png"));
		lblNewLabel_4.setBounds(10, 35, 241, 120);
		FlowChart.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon("D:\\java\\QanLyKhoHang-add\\Picture2.png"));
		lblNewLabel_5.setBounds(0, 0, 275, 568);
		FlowChart.add(lblNewLabel_5);
	}
	/**
	 * Method to support
	 */
	//Card 1 
	// Method to use find some item in txtSearch
	public void Finding(String search) {
			list = new provideData().getListItem(" execute  Finding '", (search+"'"));
			for(SanPham s : list) {
				model.addRow(new Object[] {
						 s.getID(), s.getName(), s.getAmount()
				});
			}		
	}
	
	// Method to show all items
	public void showItem() {
		list = new provideData().getListItem("Select * from SanPham", "");
		for(SanPham s : list) {
			model.addRow(new Object[] {
					s.getID(), s.getName(), s.getAmount()
			});
		
		}
	}
	
	//Card 2
	// check rỗng thêm sản phẩm 
	public boolean checkEmptyNew() {
		boolean check = false;// dung trim() bo het khoang cach
		if(txtID.getText().trim().isEmpty() || txtName.getText().trim().isEmpty() || txtAmount.getText().trim().isEmpty() || txtPrice.getText().trim().isEmpty()) {
			check = true;
		}
		return check;
	}
	
	// check rỗng update sản phẩm 
		public boolean checkEmptyOld() {
			boolean check = false;// dung trim() bo het khoang cach
			if(txtID.getText().trim().isEmpty() ||  txtAmount.getText().trim().isEmpty()) {
				check = true;
			}
			return check;
		}
	// Card3
		// chiet ra ma san pham
		public void FindForChecking(String search) {
			list = new provideData().getListItem(" execute  Finding ", search);
			for(SanPham s : list) {
				model1.addRow(new Object[] {
						 s.getID()
				});
			}		
		}
		
		// lay ra danh sach ban hang
		// hien thi ra tung item ban
		public void getListSale() {
			int i= listSale.size();
			saleSanPham sa = listSale.get(listSale.size()-1);
				model2.addRow(new Object[] {
						i++, sa.getID() , sa.getAmount() , sa.getPrice()
					});				
		}
		// lay ra toan bo item
		public void getListSale2() {
			int j= 1;
				for(saleSanPham sa : listSale) {
					model2.addRow(new Object[] {
							j++, sa.getID() , sa.getAmount() , sa.getPrice()
						});	
				}
							
		}
		//
		public int getPrice() {
			int price = 0;
			for(saleSanPham sa : listSale) {
				price += sa.getPrice();
			}
			return price;
		}
		
		//Format currency
		public String formatCurrency(int price) {
			Locale locale = new Locale("vi", "VN");
			NumberFormat format = NumberFormat.getCurrencyInstance(locale);			
			return format.format(price);
		}
		
}

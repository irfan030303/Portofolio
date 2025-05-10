package UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import DAO.OrderDRepo;
import DAO.ServiceRepo;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import model.Order; 
import model.OrderDetailModel;
import model.Service;
import table.TableOrder; 

public class OrderFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable OrderTable;
	private static OrderDetail orderdetail;
	/**
	 * Launch the application.
	 */
	OrderDRepo srvr = new OrderDRepo();
	List<Order> ls;
	String id;
	
	
	public void loadTable2() {
	    ls = srvr.showOrders(); 
	    TableOrder tu = new TableOrder(ls);
	    OrderTable.setModel(tu);
	    OrderTable.getTableHeader().setVisible(true);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderFrame frame = new OrderFrame();
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
	public OrderFrame() {
		orderdetail = new OrderDetail();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 732, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDataOrderan = new JLabel("DATA ORDERAN");
		lblDataOrderan.setFont(new Font("Serif", Font.BOLD, 20));
		lblDataOrderan.setBounds(23, 42, 685, 38);
		contentPane.add(lblDataOrderan);
		
				
		JButton btnEdit = new JButton("Edit/Detail");
		btnEdit.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {		    
		        int selectedRow = OrderTable.getSelectedRow();
		        if (selectedRow == -1) {
		            JOptionPane.showMessageDialog(null, "Silakan pilih data yang ingin diedit!", "Peringatan", JOptionPane.WARNING_MESSAGE);
		            return; 
		        }
		        String orderID = OrderTable.getValueAt(selectedRow, 0).toString();
		        String customerName = OrderTable.getValueAt(selectedRow, 1).toString();
		        String tanggal = OrderTable.getValueAt(selectedRow, 2).toString();
		        String tanggalp = OrderTable.getValueAt(selectedRow, 3).toString(); 
		        String orderStatus = OrderTable.getValueAt(selectedRow, 4).toString();
		        String orderPembayaran = OrderTable.getValueAt(selectedRow, 5).toString();
		        String orderSPembayaran = OrderTable.getValueAt(selectedRow, 6).toString();
		        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		        LocalDate parsedDate = LocalDate.parse(tanggal, dateFormat);
		        LocalDate parsedTanggalPengambilan = LocalDate.parse(tanggalp, dateFormat); 
		        orderdetail.setOrderID(orderID);
		        OrderDetail.txtOrderID.setText(orderID);
		        OrderDetail.txtpelanggan.setText(customerName);
		        orderdetail.txtTanggal.setDate(java.sql.Date.valueOf(parsedDate)); 
		        orderdetail.txtPengambilan.setDate(java.sql.Date.valueOf(parsedTanggalPengambilan));
		        orderdetail.cbStatus.setSelectedItem(orderStatus);
		        orderdetail.cbPembayaran.setSelectedItem(orderPembayaran);
		        orderdetail.cbSPembayaran.setSelectedItem(orderSPembayaran);
		        OrderDetail.loadTable(); 
		        orderdetail.loadDataRp();
		        orderdetail.setVisible(true);
		        dispose();
		    }
		});
		btnEdit.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnEdit.setBounds(598, 83, 110, 21);
		contentPane.add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnDelete.setBounds(478, 84, 110, 21);
		contentPane.add(btnDelete);

		btnDelete.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        if (id !=null) {
		        	 srvr.deleteOrder(id);
		        	loadTable2();
		        }else {
		        	JOptionPane.showMessageDialog(null, "pilih data yang akan dihapus");
		        }
		        
		    }
		});

		
		JButton btnOrder = new JButton("Order");
		btnOrder.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        OrderDRepo orderDRepo = new OrderDRepo();
		        String lastOrderId = orderDRepo.getLastOrderIdFromDatabase(); 
		        String newOrderId = generateOrderID(lastOrderId); 
		        orderdetail.setOrderID(newOrderId);
		        orderdetail.setId_order(newOrderId);
		        orderdetail.setVisible(true);
		        OrderDetail.txtOrderID.setText(newOrderId);
		        OrderDetail.loadTable();
		        OrderDetail.loadTableOrderDetail();    
		        dispose();
		    }

		    private String generateOrderID(String lastOrderId) {
		        int idNumber;

		        if (lastOrderId == null || lastOrderId.length() < 4) {
		            idNumber = 1; 
		        } else {
		            idNumber = Integer.parseInt(lastOrderId.substring(4));
		            idNumber++; 
		        }

		        return String.format("TRX-%06d", idNumber); 
		    }

		    
		});

		btnOrder.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnOrder.setBounds(23, 91, 139, 21);
		contentPane.add(btnOrder);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 119, 698, 254);
		contentPane.add(scrollPane);
		
		
		OrderTable = new JTable(new DefaultTableModel(
				new Object[][] {},
				new String[] {"ID Order", "Nama Costumer", "Tanggal Order", "Tanggal Selesai", "Status Order", "Pembayaran", "Status Pembayaran"}
				));
		 loadTable2();
		 OrderTable.addMouseListener(new MouseAdapter() {
			    @Override
			    public void mouseClicked(MouseEvent e) {
			        int selectedRow = OrderTable.getSelectedRow(); 
			        if (selectedRow != -1) {
			            
			            id = OrderTable.getValueAt(selectedRow, 0).toString();
			        }
			    }
			});
		OrderTable.setToolTipText("");
		OrderTable.setFont(new Font("SansSerif", Font.PLAIN, 12));
		OrderTable.setFillsViewportHeight(true);
		OrderTable.setBackground(Color.WHITE);
		scrollPane.setViewportView(OrderTable);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame mf = new MainFrame();
				mf.setVisible(true);
				dispose();
			}
		});
		btnBack.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnBack.setBounds(598, 10, 110, 21);
		contentPane.add(btnBack);
	}
}
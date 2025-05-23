 package UI;

 import com.toedter.calendar.JDateChooser;
 import javax.swing.*;
 import java.awt.*;
 import java.util.Date;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import DAO.CostumerRepo;
import DAO.OrderDRepo;
import DAO.ServiceRepo;
import DAO.UserRepo;
import model.Order;
import model.OrderDetailModel;
import model.Service;
import model.User;
import model.costumer;
import table.TableOrderDetailModel;
import table.TableOrderDetailModel;
import table.TableService;
import table.TableUser;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.beans.PropertyChangeEvent;
import com.toedter.calendar.JCalendar;
import com.jgoodies.common.base.Objects;

public class OrderDetail extends JFrame {

	private static final long serialVersionUID = 1L;
	protected static final Component OrderFrame = null;
	private JPanel contentPane;
	private JTable tableServices;
	private static JTable tableOrderDetails;
	private JTable tableUsers;
	static JTextField txtOrderID;
	private JTextField txtTotalRp;
	private JTextField txtHargaKg;
	private JTextField txtJumlah;
	private JTextField txtTotal2;
	private JTextField txtJenis;
	private JLabel txtidservice;
	private  JTextField Txtidservice1;
	public static String id_order1;
	public static String costumer1;

	ServiceRepo srvr = new ServiceRepo();
	List<Service> ls1;
	String id;
	public static void SetCostumer(costumer Costumer) {
		txtpelanggan.setText(Costumer.getNama());				
		
	}
	public static void setOrderID(String id_order1) {
		txtOrderID.setText(id_order1);
	
	}
	public static void setcostumer(String costumer1) {
		txtpelanggan.setText(costumer1);
	
	}
	
	public void loadTableservice() {
	    ls1 = srvr.show();
	    if (ls1 != null && !ls1.isEmpty()) {
	        TableService ts1 = new TableService(ls1);
	        tableServices.setModel(ts1);
	        tableServices.getTableHeader().setVisible(true);
	    } else {
	       
	    }
	}
	public static void setId_order(String newOrderId) {
		// TODO Auto-generated method stub
		
	}
	public static JLabel txtOrderID() {
		// TODO Auto-generated method stub
		return txtOrderID();
	}
	public static void loadTableOrderDetail() {

	}
	
	private double total(double harga, double qty) {
		return harga * qty;
	}
	public double hargabagi(double harga, double qty) {
		return harga / qty;
	}
	
	static OrderDRepo odr = new OrderDRepo();
	static List<OrderDetailModel> ls_ord;
	
	CostumerRepo cotm = new CostumerRepo();
	List<OrderDetailModel> ls2;
	
	public void reset() {
		txtHargaKg.setText("");
		txtJenis.setText("");
		txtJumlah.setText("");
		txtTotal2.setText("");
	}
	public static void loadTable() {
	    String idOrder = txtOrderID.getText().trim();

	    if (!idOrder.isEmpty()) {
	        List<OrderDetailModel> filteredOrders = odr.filterByIdOrder(idOrder);
	        if (!filteredOrders.isEmpty()) {
	            TableOrderDetailModel tableModel = new TableOrderDetailModel(filteredOrders);
	            tableOrderDetails.setModel(tableModel);
	        } 
	    } else {
	       
	        List<OrderDetailModel> allOrders = odr.show();
	        TableOrderDetailModel tableModel = new TableOrderDetailModel(allOrders);
	        tableOrderDetails.setModel(tableModel);
	    }
	}

	public void loadDataRp() {
		int total = 0;
		for (int i = 0; i < tableOrderDetails.getRowCount(); i++) {
		    Object value = tableOrderDetails.getValueAt(i, 3); 

		    total += Integer.parseInt(value.toString());
		}
	    txtTotalRp.setText("Rp. " + String.valueOf(total)); 
	}
	
	

	
	LocalDate today = LocalDate.now();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	String formattedDate = today.format(formatter);
    private JTextField Txtidservice;
    static JTextField txtpelanggan;
    public static JComboBox<String> cbStatus; 
    public static JComboBox<String> cbPembayaran; 
    public static JComboBox<String> cbSPembayaran;
    public static JDateChooser txtTanggal;
    public static JDateChooser txtPengambilan;
    
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderDetail frame = new OrderDetail();
					frame.setVisible(true);
					frame.loadTableservice();
					frame.loadTable();
					frame.loadDataRp();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public OrderDetail() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 754, 677);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(261, 40, 469, 181);
		contentPane.add(scrollPane);
		

		tableServices = new JTable();
		tableServices.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        int selectedRow = tableServices.getSelectedRow();
		        if (selectedRow != -1) {
		            String id_service = tableServices.getValueAt(selectedRow, 0).toString();
		            String jenis = tableServices.getValueAt(selectedRow, 1).toString();
		            String harga = tableServices.getValueAt(selectedRow, 2).toString();
		            Txtidservice1.setText(id_service);
		            txtHargaKg.setText(harga);
		            txtJenis.setText(jenis);
		        }
		    }
		});

		loadTableservice();

		tableServices.setToolTipText("");
		tableServices.setFont(new Font("SansSerif", Font.PLAIN, 12));
		tableServices.setFillsViewportHeight(true);
		tableServices.setBackground(Color.WHITE);
		scrollPane.setViewportView(tableServices);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setBounds(261, 418, 469, 212);
		contentPane.add(scrollPane_1);
		
		
		tableOrderDetails = new JTable();
		    tableOrderDetails.addMouseListener(new MouseAdapter() {
		        @Override
		        public void mouseClicked(MouseEvent e) {
		            int selectedRowOrderDetails = tableOrderDetails.getSelectedRow();
		            int selectedRowServices = tableServices.getSelectedRow();

		            if (selectedRowOrderDetails != -1) {
		                id = getValueAt(tableOrderDetails, selectedRowOrderDetails, 0);

		                if (selectedRowServices != -1) {
		                    txtHargaKg.setText(getValueAt(tableServices, selectedRowServices, 2, "Data tidak tersedia"));
		                }

		                txtJumlah.setText(getValueAt(tableOrderDetails, selectedRowOrderDetails, 2, ""));
		                txtTotal2.setText(getValueAt(tableOrderDetails, selectedRowOrderDetails, 3, ""));
		                txtJenis.setText(getValueAt(tableOrderDetails, selectedRowOrderDetails, 1, ""));
		            }
		        }

		        private String getValueAt(JTable table, int row, int column) {
		            return getValueAt(table, row, column, "");
		        }

		        private String getValueAt(JTable table, int row, int column, String defaultValue) {
		            Object value = table.getValueAt(row, column);
		            return value != null ? value.toString() : defaultValue;
		        }
		    
	});




		tableOrderDetails.setToolTipText("");
		tableOrderDetails.setFont(new Font("SansSerif", Font.PLAIN, 12));
		tableOrderDetails.setFillsViewportHeight(true);
		tableOrderDetails.setBackground(Color.WHITE);
		scrollPane_1.setViewportView(tableOrderDetails);
		
		JPanel panel = new JPanel();
		panel.setBounds(261, 232, 469, 176);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblHargakg = new JLabel("Harga/Kg");
		lblHargakg.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblHargakg.setBounds(10, 10, 92, 25);
		panel.add(lblHargakg);
		
		txtHargaKg = new JTextField();
		txtHargaKg.setEditable(false);
		txtHargaKg.setFont(new Font("SansSerif", Font.BOLD, 12));
		txtHargaKg.setColumns(10);
		txtHargaKg.setBounds(10, 33, 199, 25);
		panel.add(txtHargaKg);
		
		JLabel lblJumlah = new JLabel("Jumlah");
		lblJumlah.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblJumlah.setBounds(10, 68, 168, 25);
		panel.add(lblJumlah);
		
		txtJumlah = new JTextField();
		txtJumlah.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyReleased(KeyEvent e) {
		        String hargaKgText = txtHargaKg.getText();
		        String jumlahText = txtJumlah.getText();
		        
		        if (jumlahText.isEmpty() || hargaKgText.isEmpty()) {
		            txtTotal2.setText(""); 
		        } else {
		            try {
		                double hargaKg = Double.parseDouble(hargaKgText);
		                double jumlah = Double.parseDouble(jumlahText);
		                txtTotal2.setText("" + total(hargaKg, jumlah));
		            } catch (NumberFormatException ex) {
		                txtTotal2.setText(""); 
		            }
		        }
		    }
		});


		txtJumlah.setFont(new Font("SansSerif", Font.BOLD, 12));
		txtJumlah.setColumns(10);
		txtJumlah.setBounds(10, 91, 199, 25);
		panel.add(txtJumlah);
		
		JLabel lblTotal2 = new JLabel("Total");
		lblTotal2.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblTotal2.setBounds(249, 68, 168, 25);
		panel.add(lblTotal2);
		
		txtTotal2 = new JTextField();
		txtTotal2.setEditable(false);
		txtTotal2.setFont(new Font("SansSerif", Font.BOLD, 12));
		txtTotal2.setColumns(10);
		txtTotal2.setBounds(249, 91, 199, 25);
		panel.add(txtTotal2);
		
		JButton btnSimpan2 = new JButton("Simpan");
		btnSimpan2.addActionListener(new ActionListener() {
		    private JLabel txtidservice;

		    public void actionPerformed(ActionEvent e) {
		        if (!txtJumlah.getText().isEmpty()) {
		        	
		            OrderDetailModel ordd = new OrderDetailModel();
		            ordd.setJenis(txtJenis.getText());
		            ordd.setQty(txtJumlah.getText());
		            ordd.setTotal(txtTotal2.getText());
		            ordd.setid_service(Txtidservice1.getText());		     
		            ordd.setid_order(txtOrderID.getText());  	            
		            odr.save(ordd); 
		            loadTable();  
		            loadDataRp(); 
		            reset();
		        } else {
		            JOptionPane.showMessageDialog(null, "Jumlah tidak boleh kosong!", "Peringatan", JOptionPane.WARNING_MESSAGE);
		        }
		    }

		});

		btnSimpan2.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnSimpan2.setBounds(16, 141, 92, 25);
		panel.add(btnSimpan2);
		
		JButton btnUbah2 = new JButton("Ubah");
		btnUbah2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderDetailModel ordd = new OrderDetailModel();
				ordd.setJenis(txtJenis.getText());
				ordd.setQty(txtJumlah.getText());
				ordd.setTotal(txtTotal2.getText());
				ordd.setId(id);
				odr.update(ordd);
				reset();
				loadTable();
				loadDataRp();
			}
		});

		btnUbah2.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnUbah2.setBounds(117, 141, 92, 25);
		panel.add(btnUbah2);
		
		JButton btnHapus2 = new JButton("Hapus");
		btnHapus2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadTable();  
				loadDataRp();
				if(id != null) {
					odr.delete(id);
					reset();
				}else {
					JOptionPane.showMessageDialog(null, "Silahkan pilih data yang akan di hapus");
				}
			}
		});
		btnHapus2.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnHapus2.setBounds(218, 141, 92, 25);
		panel.add(btnHapus2);
		
		JButton btnBatal2 = new JButton("Batal");
		btnBatal2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				reset(); 
			
			}});
		btnBatal2.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnBatal2.setBounds(320, 141, 92, 25);
		panel.add(btnBatal2);
		
		txtJenis = new JTextField();
		txtJenis.setEditable(false);
		txtJenis.setFont(new Font("SansSerif", Font.BOLD, 12));
		txtJenis.setColumns(10);
		txtJenis.setBounds(249, 33, 199, 25);
		panel.add(txtJenis);
		txtJenis.setVisible(false); 
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 10, 241, 620);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblOrderID = new JLabel("Order ID");
		lblOrderID.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblOrderID.setBounds(10, 51, 92, 25);
		panel_1.add(lblOrderID);
		
		txtOrderID = new JTextField();
		txtOrderID.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		txtOrderID.setEditable(false);
		txtOrderID.setFont(new Font("SansSerif", Font.BOLD, 12));
		txtOrderID.setColumns(10);
		txtOrderID.setBounds(10, 87, 221, 25);
		panel_1.add(txtOrderID);
		
		JLabel lblPelanggan = new JLabel("Pelanggan");
		lblPelanggan.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblPelanggan.setBounds(10, 121, 92, 25);
		panel_1.add(lblPelanggan);
		
		JLabel lblTanggal = new JLabel("Tanggal");
		lblTanggal.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblTanggal.setBounds(10, 182, 92, 25);
		panel_1.add(lblTanggal);
		
		    txtTanggal = new JDateChooser();
	        txtTanggal.setBounds(10, 224, 221, 25);
	        txtTanggal.setFont(new Font("SansSerif", Font.BOLD, 12));
	        txtTanggal.setDate(new Date());  
	        panel_1.add(txtTanggal);


	        txtPengambilan = new JDateChooser();
	        txtPengambilan.setBounds(10, 296, 221, 25);
	        txtPengambilan.setFont(new Font("SansSerif", Font.BOLD, 12));
	        txtPengambilan.setDate(new Date()); 
	        panel_1.add(txtPengambilan);
	        
		JDateChooser txtPengambilan = new JDateChooser();
		
		txtPengambilan.setFont(new Font("SansSerif", Font.BOLD, 12));
		txtPengambilan.setDate(new Date());
		panel_1.add(txtPengambilan);
		
		JLabel lblPengambilan = new JLabel("Tanggal Pengambilan");
		lblPengambilan.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblPengambilan.setBounds(10, 260, 168, 25);
		panel_1.add(lblPengambilan);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblStatus.setBounds(10, 321, 92, 25);
		panel_1.add(lblStatus);
		

		cbStatus = new JComboBox<>(new String[]{" ", "Proses", "Selesai"});
        cbStatus.setBounds(10, 347, 221, 24); 
        cbStatus.setFont(new Font("SansSerif", Font.BOLD, 12));
        panel_1.add(cbStatus); 
        
        cbPembayaran = new JComboBox<>(new String[]{" ", "Cash", "Credit"}); 
        cbPembayaran.setBounds(10, 450, 221, 24); 
        cbPembayaran.setFont(new Font("SansSerif", Font.BOLD, 12)); 
        panel_1.add(cbPembayaran);
        
        cbSPembayaran = new JComboBox<>(new String[]{" ", "Lunas", "Belum lunas"}); 
        cbSPembayaran.setBounds(10, 508, 221, 24);
        cbSPembayaran.setFont(new Font("SansSerif", Font.BOLD, 12)); 
        panel_1.add( cbSPembayaran);
		
        
		JLabel lblPembayaran = new JLabel("Pembayaran");
		lblPembayaran.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblPembayaran.setBounds(10, 426, 92, 25);
		panel_1.add(lblPembayaran);
		
		

		 
		
		JLabel lblSPembayaran = new JLabel("Status Pembayaran");
		lblSPembayaran.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblSPembayaran.setBounds(10, 484, 168, 25);
		panel_1.add(lblSPembayaran);
		
		
		
		txtTotalRp = new JTextField();
		txtTotalRp.setEditable(false);
		txtTotalRp.setFont(new Font("SansSerif", Font.BOLD, 12));
		txtTotalRp.setColumns(10);
		txtTotalRp.setBounds(10, 391, 221, 25);
		panel_1.add(txtTotalRp);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblTotal.setBounds(10, 368, 92, 25);
		panel_1.add(lblTotal);
		
		JButton btnSimpan = new JButton("Simpan");
		btnSimpan.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        Order ord = new Order();
		        ord.setId(txtOrderID.getText());
		        ord.setCostumer(txtpelanggan.getText());
		        ord.setTotal(txtTotal2.getText());
		        ord.setTanggal(new java.sql.Date(txtTanggal.getDate().getTime()));
		        ord.setTanggal_selesai(new java.sql.Date(txtPengambilan.getDate().getTime())); 
		        ord.setStatus(cbStatus.getSelectedItem().toString());
		        ord.setPembayaran(cbPembayaran.getSelectedItem().toString());
		        ord.setStatus_pembayaran(cbSPembayaran.getSelectedItem().toString());
		        ord.setTotal(txtTotalRp.getText());
		        
		        try {
		        	odr.save2(ord);
				} catch (Exception e2) {
					odr.save3(ord);
				}finally {
					OrderFrame mf = new OrderFrame();
					mf.setVisible(true);
					dispose();
					
				}
		    }
		});
		
		btnSimpan.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnSimpan.setBounds(24, 585, 92, 25);
		panel_1.add(btnSimpan);


		
		JButton btnBatal = new JButton("Batal");
		btnBatal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OrderFrame of = new OrderFrame();
				of.setVisible(true);
				dispose();
			}
		});
		btnBatal.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnBatal.setBounds(125, 585, 92, 25);
		panel_1.add(btnBatal);
		            
		Txtidservice1 = new JTextField();
		Txtidservice1.setEditable(false);
		Txtidservice1.setBounds(199, 187, 32, 20);
		panel_1.add(Txtidservice1);
		Txtidservice1.setVisible(false); 
		
		txtpelanggan = new JTextField();
		txtpelanggan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FrameminiCostumer mc = new FrameminiCostumer(null);
				mc.setVisible(true);
			}
		});
		txtpelanggan.setBounds(10, 157, 221, 20);
		panel_1.add(txtpelanggan);
		txtpelanggan.setColumns(10);
		
		
		
		
		
		
		

		
		JLabel lblServiceList = new JLabel("Service ");
		lblServiceList.setFont(new Font("SansSerif", Font.BOLD, 14));
		lblServiceList.setBounds(261, 10, 119, 25);
		contentPane.add(lblServiceList);
	}
	public void loadOrderDetails(String idOrder) {
		
		
	}	
}
	
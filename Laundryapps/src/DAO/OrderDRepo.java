package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Date; 
import model.Order;
import model.OrderDetailModel;
import confg.Database;


public class OrderDRepo implements OrderDDao {
    private Connection connection;
    final String insert = "INSERT INTO order_detail (jenis, qty, total,id_order,id_service) VALUES (?,?,?,?,?);";
    final String select = "SELECT * FROM order_detail;";
    final String delete = "DELETE FROM order_detail WHERE id=?;";
    final String update = "UPDATE order_detail SET jenis=?, qty=?, total=?,id_order=?,id_=? WHERE id=?;";
    final String selectById = "SELECT * FROM order_detail WHERE id_order = ?;";
    final String inserto = "INSERT INTO orders (Id,id_costumer,id_service,id_user,total,tanggal,tanggal_selesai,status,status_pembayaran) VALUES (?,?,?,?,?);";

    public OrderDRepo() {
        connection = Database.koneksi();
    }
    
    
    public static String id;

    @Override
    public void save(OrderDetailModel ordd) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(insert);
            st.setString(1, ordd.getJenis());
            st.setString(2, ordd.getQty());
            st.setString(3, ordd.getTotal());
            st.setString(4, ordd.getid_order());
            st.setString(5, ordd.getid_service());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
 
 

    public void save2(Order ord) {
        PreparedStatement st = null;
        try {
            String insert = "INSERT INTO orders (id, costumer, total, tanggal, tanggal_selesai, status, pembayaran, status_pembayaran) "
                            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            st = connection.prepareStatement(insert);        
            st.setString(1, ord.getId());
            st.setString(2, ord.getCostumer());
            st.setString(3, ord.getTotal());
            st.setDate(4, ord.getTanggal());
            st.setDate(5, ord.getTanggal_selesai());
            st.setString(6, ord.getStatus());
            st.setString(7, ord.getPembayaran());
            st.setString(8, ord.getStatus_pembayaran());
            int rowsAffected = st.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            save3(ord);
        } catch (SQLException e) {
           
            System.err.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (st != null) st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    
    public void save3(Order ord) {
        PreparedStatement st = null;
        try {
            String update = "UPDATE orders SET costumer = ?, total = ?, tanggal = ?, tanggal_selesai = ?, status = ?, pembayaran = ?,"
            		+ " status_pembayaran = ? WHERE id = ?";
            st = connection.prepareStatement(update);
            st.setString(1, ord.getCostumer());
            st.setString(2, ord.getTotal());
            st.setDate(3, ord.getTanggal());
            if (ord.getTanggal_selesai() == null || ord.getTanggal_selesai().toString().trim().isEmpty()) {
                st.setNull(4, java.sql.Types.DATE);
            } else {
                st.setDate(4, ord.getTanggal_selesai());
            }

            st.setString(5, ord.getStatus());
            st.setString(6, ord.getPembayaran());
            st.setString(7, ord.getStatus_pembayaran());
            st.setString(8, ord.getId());
            int rowsAffected = st.executeUpdate();
        } catch (SQLException e) {

        } finally {
            try {
                if (st != null) st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

        @Override
    public List<OrderDetailModel> show() {
        List<OrderDetailModel> ls2 = new ArrayList<>();
        try {
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(select);
            while (rs.next()) {
                OrderDetailModel ordd = new OrderDetailModel();
                ordd.setId(rs.getString("id"));
                ordd.setJenis(rs.getString("jenis"));
                ordd.setQty(rs.getString("qty"));
                ordd.setTotal(rs.getString("total"));
                ordd.setid_order(rs.getString("id_order"));
                ordd.setid_service(rs.getString("id_service"));
                ls2.add(ordd);
            }
        } catch (SQLException e) {
            Logger.getLogger(OrderDDao.class.getName()).log(Level.SEVERE, null, e);
        }
        return ls2;
    }

    @Override
    public void update(OrderDetailModel odm) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(update);
            st.setString(1, odm.getJenis());
            st.setString(2, odm.getQty());
            st.setString(3, odm.getTotal());
            st.setString(4, odm.getId());
            st.setString(5, odm.getid_service());
            st.setString(6, odm.getId()); 
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(String id) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(delete);
            st.setString(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteOrder(String id) {
        final String deleteOrderDetail = "DELETE FROM order_detail WHERE id_order = ?;";
        final String deleteOrder = "DELETE FROM orders WHERE id = ?;";

        try (Connection connection = Database.koneksi();
             PreparedStatement st1 = connection.prepareStatement(deleteOrderDetail);
             PreparedStatement st2 = connection.prepareStatement(deleteOrder)) {
            st1.setString(1, id);
            st1.executeUpdate();
            st2.setString(1, id);
            st2.executeUpdate();          
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public String total(String order_id) {
        String query_total = "SELECT sum(total) as total from order_detail WHERE id_order = ?";
        String result = "";
        try (PreparedStatement st = connection.prepareStatement(query_total)) {
            st.setString(1, order_id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                result = rs.getString("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<OrderDetailModel> showById(String id_order1) {
        List<OrderDetailModel> ls = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(selectById);
            st.setString(1, id_order1); 
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                OrderDetailModel orderDetailModel = new OrderDetailModel();
                orderDetailModel.setId(rs.getString("id"));
                orderDetailModel.setid_order(rs.getString("id_order"));
                orderDetailModel.setid_service(rs.getString("id_service"));
                orderDetailModel.setQty(rs.getString("qty"));
                orderDetailModel.setTotal(rs.getString("total"));
                ls.add(orderDetailModel);
            }
        } catch (Exception e) {
            Logger.getLogger(OrderDRepo.class.getName()).log(Level.SEVERE, null, e);
        }
        return ls;
    }

    public String getLastOrderIdFromDatabase() {
        String query = "SELECT id FROM orders ORDER BY id DESC LIMIT 1";
        try (Connection conn = Database.koneksi();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {           
            if (rs.next()) {
                return rs.getString("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<OrderDetailModel> filterByIdOrder(String idOrder) {
        List<OrderDetailModel> filteredList = new ArrayList<>();
        String query = "SELECT * FROM order_detail WHERE id_order = ?;";

        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setString(1, idOrder);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                OrderDetailModel orderDetailModel = new OrderDetailModel();
                orderDetailModel.setId(rs.getString("id"));
                orderDetailModel.setid_order(rs.getString("id_order"));
                orderDetailModel.setid_service(rs.getString("id_service"));
                orderDetailModel.setJenis(rs.getString("Jenis"));
                orderDetailModel.setQty(rs.getString("qty")); 
                orderDetailModel.setTotal(rs.getString("total")); 
                filteredList.add(orderDetailModel);
            }
        } catch (SQLException e) {
            Logger.getLogger(OrderDRepo.class.getName()).log(Level.SEVERE, "Error while filtering by ID Order", e);
        }

        return filteredList;
    }


	
	@Override
	public List<Order> showOrders() {
	    List<Order> orders = new ArrayList<>();
	    String query = "SELECT * FROM orders"; 
	    try {
	        Statement st = connection.createStatement();
	        ResultSet rs = st.executeQuery(query);
	        while (rs.next()) {
	            Order order = new Order();
	            order.setId(rs.getString("id"));
	            order.setCostumer(rs.getString("costumer"));
	            order.setTanggal(rs.getDate("tanggal"));
	            order.setTanggal_selesai(rs.getDate("tanggal_selesai"));
	            order.setStatus(rs.getString("status"));
	            order.setPembayaran(rs.getString("pembayaran"));
	            order.setStatus_pembayaran(rs.getString("status_pembayaran"));
	            orders.add(order);
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	   
	    return orders;
	}
	@Override
	public List<Order> show1() {
		// TODO Auto-generated method stub
		return null;
	}
}

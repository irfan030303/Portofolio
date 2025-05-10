package DAO;

import java.util.List;

import model.Order;
import model.OrderDetailModel;

public interface OrderDDao {
	void save(OrderDetailModel odm);
	public List<OrderDetailModel> show();
	public List<Order> show1();
	public void delete(String id);
	public void update(OrderDetailModel odm);
	public String total(String order_id);
	List<Order> showOrders();
}
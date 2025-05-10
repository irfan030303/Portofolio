package table;

import model.Order;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class TableOrder extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    private static List<Order> ls;


    private final String[] columnNames = new String[] {
        "ID", "Costumer", "Tanggal", "Tanggal Selesai", "Status", "Pembayaran", "Status Pembayaran"
    };
    public TableOrder(List<Order> ls) {
        this.ls = ls;
    }
    @Override
    public int getRowCount() {
        return ls.size(); 
    }

    @Override
    public int getColumnCount() {
        return columnNames.length; 
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column]; 
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Order order = ls.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return order.getId(); 
            case 1:
                return order.getCostumer(); 
            case 2:
                return order.getTanggal(); 
            case 3:
                return order.getTanggal_selesai(); 
            case 4:
                return order.getStatus();
            case 5:
                return order.getPembayaran(); 
            case 6:
                return order.getStatus_pembayaran(); 
            default:
                return null;
        }
    }
    public static Order getOrderAt(int rowIndex) {
        return ls.get(rowIndex);
    }
}

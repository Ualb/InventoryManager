package inv.mgr.model;

import inv.mgr.Entity.Order;

import java.util.List;

public class OrderModel {

    private final String TABLE = "CREATE TABLE IF NOT EXISTS Order(" +
            "idOrder int PRIMARY KEY AUTOINCREMENT," +
            "makeDate date NOT NULL," +
            "deliveryDate date NOT NULL," +
            "costRequest real NOT NULL)";
    private final String SELECT = "SELECT * FROM Order";
    private final String WHERE = " WHERE idOrder = ?";
    private final String INSERT = "INSERT INTO Order VALUES (?, ?, ?)";
    private final String UPDATE = "UPDATE Order SET makeDate = ?," +
            "deliveryDate = ?," +
            "costRequest = ?" + WHERE;
    private final String DELETE = "DELETE FROM Order" + WHERE;

    public static List<Order> getOrders() {
        return null;
    }

    public static Order getOrderById(int id) {
        return null;
    }

    public static void setOrder(Order order) {

    }

    public static void updateOrder(Order order) {

    }

    public static void deleteOrder(int idOrder) {

    }
}

package model;

import Entity.Demand;
import Entity.OrderProduct;

import java.util.List;

public class OrderProductModel {

    private final String TABLE = "CREATE TABLE IF NOT EXISTS OrderProduct(" +
            "idOrder int NOT NULL," +
            "idProduct int NOT NULL," +
            "cost real NOT NULL)";
    private final String SELECT = "SELECT * FROM OrderProduct";
    private final String WHERE = " WHERE idOrder = ? and idProduct = ?";
    private final String INSERT = "INSERT INTO OrderProduct VALUES (?, ?, ?)";
    private final String UPDATE = "UPDATE OrderProduct SET idOrder = ?," +
            "idProduct = ?," +
            "cost = ?" + WHERE;
    private final String DELETE = "DELETE FROM OrderProduct" + WHERE;

    public static List<OrderProduct> getOrdersProducts() {
        return null;
    }

    public static OrderProduct getOrderProductByIds(int idOrder, int idProduct) {
        return null;
    }

    public static void setOrderProduct(OrderProduct orderProduct) {

    }

    public static void updateOrderProduct(OrderProduct orderProduct) {

    }

    public static void deleteOrderProduct(int idOrder, int idProduct) {

    }
}

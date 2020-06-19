package inv.mgr.model;

import inv.mgr.Entity.Product;

import java.util.List;

public class ProductModel {

    private final String TABLE = "CREATE TABLE IF NOT EXISTS Product(" +
            "idProduct int PRIMARY KEY AUTOINCREMENT," +
            "securityStock int NOT NULL," +
            "price real NOT NULL," +
            "quantity int NOT NULL," +
            "keepCost real NOT NULL," +
            "inv.mgr.model varchar(25) NOT NULL," +
            "name varchar(25) NOT NULL)";
    private final String SELECT = "SELECT * FROM Product";
    private final String WHERE = " WHERE idProduct = ?";
    private final String INSERT = "INSERT INTO Product VALUES (?, ?, ?, ?, ?, ?)";
    private final String UPDATE = "UPDATE Product SET securityStock = ?," +
            "price = ?," +
            "quantity = ?," +
            "keepCost = ?," +
            "inv.mgr.model = ?," +
            "name = ?" + WHERE;
    private final String DELETE = "DELETE FROM Product" + WHERE;

    public static List<Product> getProducts() {
        return null;
    }

    public static Product getProductById(int id) {
        return null;
    }

    public static void setProduct(Product product) {

    }

    public static void updateProduct(Product product) {

    }

    public static void deleteProduct(int idProduct) {

    }
}

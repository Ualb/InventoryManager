package Entity;

public class Product {

    private int idProduct;
    private int securityStock;
    private double price;
    private int quantity;
    private double keepCost;
    private String model;
    private String name;

    public Product() {
    }

    public Product(int idProduct, int securityStock, double price, int quantity, double keepCost, String model, String name) {
        this.idProduct = idProduct;
        this.securityStock = securityStock;
        this.price = price;
        this.quantity = quantity;
        this.keepCost = keepCost;
        this.model = model;
        this.name = name;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getSecurityStock() {
        return securityStock;
    }

    public void setSecurityStock(int securityStock) {
        this.securityStock = securityStock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getKeepCost() {
        return keepCost;
    }

    public void setKeepCost(double keepCost) {
        this.keepCost = keepCost;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Product{" +
                "idProduct=" + idProduct +
                ", securityStock=" + securityStock +
                ", price=" + price +
                ", quantity=" + quantity +
                ", keepCost=" + keepCost +
                ", model='" + model + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return idProduct == product.idProduct &&
                securityStock == product.securityStock &&
                Double.compare(product.price, price) == 0 &&
                quantity == product.quantity &&
                Double.compare(product.keepCost, keepCost) == 0 &&
                model.equals(product.model) &&
                name.equals(product.name);
    }

}

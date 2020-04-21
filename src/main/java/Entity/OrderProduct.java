package Entity;

public class OrderProduct {

    private int idOrder;
    private int idProduct;
    private double cost;

    public OrderProduct() {
    }

    public OrderProduct(int idOrder, int idProduct, double cost) {
        this.idOrder = idOrder;
        this.idProduct = idProduct;
        this.cost = cost;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "OrderProduct{" +
                "idOrder=" + idOrder +
                ", idProduct=" + idProduct +
                ", cost=" + cost +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderProduct that = (OrderProduct) o;
        return idOrder == that.idOrder &&
                idProduct == that.idProduct &&
                Double.compare(that.cost, cost) == 0;
    }

}

package Entity;

import java.sql.Date;
import java.util.Objects;

public class Order {

    private int idOrder;
    private Date makeDate;
    private Date deliveryDate;
    private double costRequest;

    public Order() {
    }

    public Order(int idOrder, Date makeDate, Date deliveryDate, double costRequest) {
        this.idOrder = idOrder;
        this.makeDate = makeDate;
        this.deliveryDate = deliveryDate;
        this.costRequest = costRequest;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public Date getMakeDate() {
        return makeDate;
    }

    public void setMakeDate(Date makeDate) {
        this.makeDate = makeDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public double getCostRequest() {
        return costRequest;
    }

    public void setCostRequest(double costRequest) {
        this.costRequest = costRequest;
    }

    @Override
    public String toString() {
        return "Order{" +
                "idOrder=" + idOrder +
                ", makeDate=" + makeDate +
                ", deliveryDate=" + deliveryDate +
                ", costRequest=" + costRequest +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return idOrder == order.idOrder &&
                Double.compare(order.costRequest, costRequest) == 0 &&
                makeDate.equals(order.makeDate) &&
                deliveryDate.equals(order.deliveryDate);
    }

}

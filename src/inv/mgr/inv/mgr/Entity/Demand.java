package inv.mgr.Entity;

import java.sql.Date;

public class Demand {
    private int idDemand;
    private Date date;
    private int quantityPerDay;
    private int idProduct;

    public Demand() {}

    public Demand(int idDemand, Date date, int quantityPerDay, int idProduct) {
        this.idDemand = idDemand;
        this.date = date;
        this.quantityPerDay = quantityPerDay;
        this.idProduct = idProduct;
    }

    public int getIdDemand() {
        return idDemand;
    }

    public void setIdDemand(int idDemand) {
        this.idDemand = idDemand;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getQuantityPerDay() {
        return quantityPerDay;
    }

    public void setQuantityPerDay(int quantityPerDay) {
        this.quantityPerDay = quantityPerDay;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    @Override
    public String toString() {
        return "Demand{" +
                "idDemand=" + idDemand +
                ", date=" + date +
                ", quantityPerDay=" + quantityPerDay +
                ", idProduct=" + idProduct +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Demand demand = (Demand) o;
        return idDemand == demand.idDemand &&
                quantityPerDay == demand.quantityPerDay &&
                idProduct == demand.idProduct &&
                date.equals(demand.date);
    }

}

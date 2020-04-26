package controller;

public class RowExplosionMainTable {

    private Double productCost;
    private Integer weekOrMonth;
    private Integer demand;
    private Integer production;
    private Integer endInventory;
    private Double H;
    private Double S;
    private Double CT;

    public RowExplosionMainTable() {}

    public RowExplosionMainTable(Integer weekOrMonth, Integer demand, Double s) {
        this.weekOrMonth = weekOrMonth;
        this.demand = demand;
        S = s;
    }

    public RowExplosionMainTable(Double productCost, Integer weekOrMonth, Integer demand, Double h, Double s) {
        this.productCost = productCost;
        this.weekOrMonth = weekOrMonth;
        this.demand = demand;
        H = h;
        S = s;
    }

    public Double getProductCost() {
        return productCost;
    }

    public void setProductCost(Double productCost) {
        this.productCost = productCost;
    }

    public Integer getWeekOrMonth() {
        return weekOrMonth;
    }

    public void setWeekOrMonth(Integer weekOrMonth) {
        this.weekOrMonth = weekOrMonth;
    }

    public Integer getDemand() {
        return demand;
    }

    public void setDemand(Integer demand) {
        this.demand = demand;
    }

    public Integer getProduction() {
        return production;
    }

    public void setProduction(Integer production) {
        this.production = production;
    }

    public Integer getEndInventory() {
        return endInventory;
    }

    public void setEndInventory(Integer endInventory) {
        this.endInventory = endInventory;
    }

    public Double getH() {
        return H;
    }

    public void setH(Double h) {
        H = h;
    }

    public Double getS() {
        return S;
    }

    public void setS(Double s) {
        S = s;
    }

    public Double getCT() {
        return CT;
    }

    public void setCT(Double CT) {
        this.CT = CT;
    }

    @Override
    public String toString() {
        return "ExplosionTable{" +
                "productCost=" + productCost +
                ", weekOrMonth=" + weekOrMonth +
                ", demand=" + demand +
                ", production=" + production +
                ", endInventory=" + endInventory +
                ", H=" + H +
                ", S=" + S +
                ", CT=" + CT +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RowExplosionMainTable that = (RowExplosionMainTable) o;
        return productCost.equals(that.productCost) &&
                weekOrMonth.equals(that.weekOrMonth) &&
                demand.equals(that.demand) &&
                production.equals(that.production) &&
                endInventory.equals(that.endInventory) &&
                H.equals(that.H) &&
                S.equals(that.S) &&
                CT.equals(that.CT);
    }
}

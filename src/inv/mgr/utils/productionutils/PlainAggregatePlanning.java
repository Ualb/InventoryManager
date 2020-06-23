package mgr.utils.productionutils;

import java.util.List;

public class PlainAggregatePlanning {

    private double productCst; //costo del producto
    private double H; //mantenimiento
    private double Cf; //costo del faltante
    private double recruitment; //contratacion
    private double dismissal; //despido
    private double normalTime;
    private double extraTime;
    private int startInventory;
    private double requeredTime;
    private double stockSecurityPorcentual;
    private int stockSecurity;
    private double outsourcing;
    private int MOI; //mano de obra inicial
    private double cstTotal;
    private List<MonthAggregatePlanning> list;
    private int forceLeven;

    public final int HOURS_PER_DAY = 8;

    public PlainAggregatePlanning() {
    }

    public PlainAggregatePlanning(double productCst, double h, double cf, double recruitment, double dismissal, double normalTime, double extraTime, int startInventory, double requeredTime, double stockSecurityPorcentual, int stockSecurity, double outsourcing, int MOI, List<MonthAggregatePlanning> list, int forceLeven) {
        this.productCst = productCst;
        H = h;
        Cf = cf;
        this.recruitment = recruitment;
        this.dismissal = dismissal;
        this.normalTime = normalTime;
        this.extraTime = extraTime;
        this.startInventory = startInventory;
        this.requeredTime = requeredTime;
        this.stockSecurityPorcentual = stockSecurityPorcentual;
        this.stockSecurity = stockSecurity;
        this.outsourcing = outsourcing;
        this.MOI = MOI;
        this.list = list;
        this.forceLeven = forceLeven;
    }

    public double getProductCst() {
        return productCst;
    }

    public void setProductCst(double productCst) {
        this.productCst = productCst;
    }

    public double getH() {
        return H;
    }

    public void setH(double h) {
        H = h;
    }

    public double getCf() {
        return Cf;
    }

    public void setCf(double cf) {
        Cf = cf;
    }

    public double getRecruitment() {
        return recruitment;
    }

    public void setRecruitment(double recruitment) {
        this.recruitment = recruitment;
    }

    public double getDismissal() {
        return dismissal;
    }

    public void setDismissal(double dismissal) {
        this.dismissal = dismissal;
    }

    public double getNormalTime() {
        return normalTime;
    }

    public void setNormalTime(double normalTime) {
        this.normalTime = normalTime;
    }

    public double getExtraTime() {
        return extraTime;
    }

    public void setExtraTime(double extraTime) {
        this.extraTime = extraTime;
    }

    public int getStartInventory() {
        return startInventory;
    }

    public void setStartInventory(int startInventory) {
        this.startInventory = startInventory;
    }

    public double getRequeredTime() {
        return requeredTime;
    }

    public void setRequeredTime(double requeredTime) {
        this.requeredTime = requeredTime;
    }

    public double getStockSecurityPorcentual() {
        return stockSecurityPorcentual;
    }

    public void setStockSecurityPorcentual(double stockSecurityPorcentual) {
        this.stockSecurityPorcentual = stockSecurityPorcentual;
    }

    public int getStockSecurity() {
        return stockSecurity;
    }

    public void setStockSecurity(int stockSecurity) {
        this.stockSecurity = stockSecurity;
    }

    public double getOutsourcing() {
        return outsourcing;
    }

    public void setOutsourcing(double outsourcing) {
        this.outsourcing = outsourcing;
    }

    public int getMOI() {
        return MOI;
    }

    public void setMOI(int MOI) {
        this.MOI = MOI;
    }

    public List<MonthAggregatePlanning> getList() {
        return list;
    }

    public void setList(List<MonthAggregatePlanning> list) {
        this.list = list;
    }

    public double getCstTotal() {
        return cstTotal;
    }

    public void setCstTotal(double cstTotal) {
        this.cstTotal = cstTotal;
    }

    public int getForceLeven() {
        return forceLeven;
    }

    public void setForceLeven(int forceLeven) {
        this.forceLeven = forceLeven;
    }

    @Override
    public String toString() {
        return "PlainToAgreggregatePlanning{" +
                "productCst=" + productCst +
                ", H=" + H +
                ", Cf=" + Cf +
                ", recruitment=" + recruitment +
                ", dismissal=" + dismissal +
                ", normalTime=" + normalTime +
                ", extraTime=" + extraTime +
                ", startInventory=" + startInventory +
                ", requeredTime=" + requeredTime +
                ", stockSecurityPorcentual=" + stockSecurityPorcentual +
                ", stockSecurity=" + stockSecurity +
                ", outsourcing=" + outsourcing +
                ", MOI=" + MOI +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlainAggregatePlanning that = (PlainAggregatePlanning) o;
        return Double.compare(that.productCst, productCst) == 0 &&
                Double.compare(that.H, H) == 0 &&
                Double.compare(that.Cf, Cf) == 0 &&
                Double.compare(that.recruitment, recruitment) == 0 &&
                Double.compare(that.dismissal, dismissal) == 0 &&
                Double.compare(that.normalTime, normalTime) == 0 &&
                Double.compare(that.extraTime, extraTime) == 0 &&
                startInventory == that.startInventory &&
                Double.compare(that.requeredTime, requeredTime) == 0 &&
                Double.compare(that.stockSecurityPorcentual, stockSecurityPorcentual) == 0 &&
                stockSecurity == that.stockSecurity &&
                Double.compare(that.outsourcing, outsourcing) == 0 &&
                MOI == that.MOI && that.list.equals(this.list);
    }
}

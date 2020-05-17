package model;

public class MonthAggregatePlanning {

    private int number; //el numero del mes
    private int demand;
    private int daysAvaileble;
    private int hoursAvaileble; //horas disponibles
    private int productionRequered;
    private int realProduction;
    private int productsWithOutsourcing; //hechos por outsourcing
    private int startInventory;
    private int endInventory;
    private int stockSecurity;
    private double hoursRequered;
    private int numberOfEmployeesRequered;
    private double cstMissing; //faltante
    private double cstRecruitment; //contratacion
    private double cstDismissal; //despido
    private double cstOutsourcing;
    private double cstExtraHour; //hora extra
    private double cstNormal;
    private double cstMaterials;
    private double cstH;
    private double cstTotal;

    private boolean isExtraHour_NoOutsourcing = false; //true = mes de hora extra - false = mes de outsourcing

    public MonthAggregatePlanning() {
    }

    public MonthAggregatePlanning(int number, int demand, int daysAvaileble) {
        this.number = number;
        this.demand = demand;
        this.daysAvaileble = daysAvaileble;
    }

    public MonthAggregatePlanning(int number, int demand, int daysAvaileble, boolean isExtraHour_NoOutsourcing) {
        this.number = number;
        this.demand = demand;
        this.daysAvaileble = daysAvaileble;
        this.isExtraHour_NoOutsourcing = isExtraHour_NoOutsourcing;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getDemand() {
        return demand;
    }

    public void setDemand(int demand) {
        this.demand = demand;
    }

    public int getDaysAvaileble() {
        return daysAvaileble;
    }

    public void setDaysAvaileble(int daysAvaileble) {
        this.daysAvaileble = daysAvaileble;
    }

    public int getProductionRequered() {
        return productionRequered;
    }

    public void setProductionRequered(int productionRequered) {
        this.productionRequered = productionRequered;
    }

    public int getRealProduction() {
        return realProduction;
    }

    public void setRealProduction(int realProduction) {
        this.realProduction = realProduction;
    }

    public int getStartInventory() {
        return startInventory;
    }

    public void setStartInventory(int startInventory) {
        this.startInventory = startInventory;
    }

    public int getEndInventory() {
        return endInventory;
    }

    public void setEndInventory(int endInventory) {
        this.endInventory = endInventory;
    }

    public int getStockSecurity() {
        return stockSecurity;
    }

    public void setStockSecurity(int stockSecurity) {
        this.stockSecurity = stockSecurity;
    }

    public double getHoursRequered() {
        return hoursRequered;
    }

    public void setHoursRequered(int hoursRequered) {
        this.hoursRequered = hoursRequered;
    }

    public int getNumberOfEmployeesRequered() {
        return numberOfEmployeesRequered;
    }

    public void setNumberOfEmployeesRequered(int numberOfEmployeesRequered) {
        this.numberOfEmployeesRequered = numberOfEmployeesRequered;
    }

    public double getCstMissing() {
        return cstMissing;
    }

    public void setCstMissing(double cstMissing) {
        this.cstMissing = cstMissing;
    }

    public double getCstRecruitment() {
        return cstRecruitment;
    }

    public void setCstRecruitment(double cstRecruitment) {
        this.cstRecruitment = cstRecruitment;
    }

    public double getCstDismissal() {
        return cstDismissal;
    }

    public void setCstDismissal(double cstDismissal) {
        this.cstDismissal = cstDismissal;
    }

    public double getCstOutsourcing() {
        return cstOutsourcing;
    }

    public void setCstOutsourcing(double cstOutsourcing) {
        this.cstOutsourcing = cstOutsourcing;
    }

    public double getCstNormal() {
        return cstNormal;
    }

    public void setCstNormal(double cstNormal) {
        this.cstNormal = cstNormal;
    }

    public double getCstMaterials() {
        return cstMaterials;
    }

    public void setCstMaterials(double cstMaterials) {
        this.cstMaterials = cstMaterials;
    }

    public double getCstH() {
        return cstH;
    }

    public void setCstH(double cstH) {
        this.cstH = cstH;
    }

    public double getCstTotal() {
        return cstTotal;
    }

    public void setCstTotal(double cstTotal) {
        this.cstTotal = cstTotal;
    }

    public int getHoursAvaileble() {
        return hoursAvaileble;
    }

    public void setHoursAvaileble(int hoursAvaileble) {
        this.hoursAvaileble = hoursAvaileble;
    }

    public void setHoursRequered(double hoursRequered) {
        this.hoursRequered = hoursRequered;
    }

    public double getCstExtraHour() {
        return cstExtraHour;
    }

    public void setCstExtraHour(double cstExtraHour) {
        this.cstExtraHour = cstExtraHour;
    }

    public int getProductsWithOutsourcing() {
        return productsWithOutsourcing;
    }

    public void setProductsWithOutsourcing(int productsWithOutsourcing) {
        this.productsWithOutsourcing = productsWithOutsourcing;
    }

    public boolean isExtraHour_NoOutsourcing() {
        return isExtraHour_NoOutsourcing;
    }

    public void setExtraHour_NoOutsourcing(boolean extraHour_NoOutsourcing) {
        isExtraHour_NoOutsourcing = extraHour_NoOutsourcing;
    }

    @Override
    public String toString() {
        return "MothToAggregatePlanning{" +
                "number=" + number +
                ", demand=" + demand +
                ", daysAvaileble=" + daysAvaileble +
                ", productionRequered=" + productionRequered +
                ", realProduction=" + realProduction +
                ", productsWithOutsourcing=" +productsWithOutsourcing +
                ", startInventory=" + startInventory +
                ", endInventory=" + endInventory +
                ", hoursRequered=" + hoursRequered +
                ", hourAvaileble=" + hoursAvaileble +
                ", numberOfEmployeesRequered=" + numberOfEmployeesRequered +
                ", cstMissing=" + cstMissing +
                ", cstRecruitment=" + cstRecruitment +
                ", cstDismissal=" + cstDismissal +
                ", cstOutsourcing=" + cstOutsourcing +
                ", cstExtraHours=" + cstExtraHour +
                ", cstNormal=" + cstNormal +
                ", cstH=" + cstH +
                ",cstMaterials=" + cstMaterials +
                ", cstTotal=" + cstTotal +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonthAggregatePlanning that = (MonthAggregatePlanning) o;
        return number == that.number &&
                demand == that.demand &&
                daysAvaileble == that.daysAvaileble &&
                productionRequered == that.productionRequered &&
                realProduction == that.realProduction &&
                startInventory == that.startInventory &&
                endInventory == that.endInventory &&
                hoursRequered == that.hoursRequered &&
                numberOfEmployeesRequered == that.numberOfEmployeesRequered &&
                Double.compare(that.cstMissing, cstMissing) == 0 &&
                Double.compare(that.cstRecruitment, cstRecruitment) == 0 &&
                Double.compare(that.cstDismissal, cstDismissal) == 0 &&
                Double.compare(that.cstOutsourcing, cstOutsourcing) == 0 &&
                Double.compare(that.cstNormal, cstNormal) == 0 &&
                Double.compare(that.cstH, cstH) == 0 &&
                Double.compare(that.cstTotal, cstTotal) == 0;
    }
}

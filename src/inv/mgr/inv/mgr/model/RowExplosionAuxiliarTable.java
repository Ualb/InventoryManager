package inv.mgr.model;

public class RowExplosionAuxiliarTable {

    private String rang;
    private Integer lot;
    private Double H;
    private Double S;
    private Double CT;
    private Double CU;

    public RowExplosionAuxiliarTable() {
    }

    public RowExplosionAuxiliarTable(String rang, Integer lot, Double h, Double s, Double CT, Double CU) {
        this.rang = rang;
        this.lot = lot;
        H = h;
        S = s;
        this.CT = CT;
        this.CU = CU;
    }

    public String getRang() {
        return rang;
    }

    public void setRang(String rang) {
        this.rang = rang;
    }

    public Integer getLot() {
        return lot;
    }

    public void setLot(Integer lot) {
        this.lot = lot;
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

    public Double getCU() {
        return CU;
    }

    public void setCU(Double CU) {
        this.CU = CU;
    }

    @Override
    public String toString() {
        return "ExplosionAuxiliarTable{" +
                "rang='" + rang + '\'' +
                ", lot=" + lot +
                ", H=" + H +
                ", S=" + S +
                ", CT=" + CT +
                ", CU=" + CU +
                '}';
    }
}

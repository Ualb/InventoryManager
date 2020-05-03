package controller;

import java.util.List;

public class MRP {

    public static List<RowExplosionMainTable> getL4L(List<RowExplosionMainTable> list) {
        Double sum = new Double(0);
        for (RowExplosionMainTable row: list) {
            row.setProduction(row.getDemand());
            row.setEndInventory(0);
            row.setH(0.0);
            row.setCT(sum + row.getS());
            sum += row.getS();
        }
        return list;
    }

    public static List<RowExplosionMainTable> getEOQ(List<RowExplosionMainTable> list) {
        Double D = list.stream().filter(sum -> sum.getDemand() > 0).mapToDouble(sum -> sum.getDemand()).sum();
        return null;
    }

    public static ExplosionTable getLTC(List<RowExplosionMainTable> list, List<RowExplosionAuxiliarTable> auxList) {
        return null;
    }

    public static ExplosionTable getLUC(List<RowExplosionMainTable> list, List<RowExplosionAuxiliarTable> auxList) {
        return null;
    }
}



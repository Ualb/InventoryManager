package controller;

import java.util.List;

public class MRP {

    private static final int POSITION_ZERO = 0;
    private static final int ZERO_VALOR = 0;
    private static final double ZERO_COST = 0.0;
    private static final int TOTAL_WEEKS_IN_YEAR = 52;
    private static final int TOTAL_MONTHS_IN_YEAR = 12;
    private static final int FIRTS_WEEK_OR_MONTH = 1;

    public static List<RowExplosionMainTable> getL4L(List<RowExplosionMainTable> list) {
        Double sum = new Double(ZERO_VALOR);
        for (RowExplosionMainTable row: list) {
            row.setProduction(row.getDemand());
            row.setEndInventory(ZERO_VALOR);
            row.setH(ZERO_COST);
            row.setCT(sum + row.getS());
            sum += row.getS();
        }
        return list;
    }

    public static List<RowExplosionMainTable> getEOQ(List<RowExplosionMainTable> list) {
        Double sumOfDemand = list.stream().
                mapToDouble(sum -> sum.getDemand()).
                sum();
        Integer time = (list.get(POSITION_ZERO).isWeek() == true)?
                TOTAL_WEEKS_IN_YEAR: TOTAL_MONTHS_IN_YEAR;
        Double D = sumOfDemand / list.size() * time;
        Double H = list.get(POSITION_ZERO).getH();
        Integer EOQ = (int) Math.floor(Math.sqrt((2 * D * list.get(POSITION_ZERO).getS()) /
                (H * time)));
        Integer endInventory = new Integer(ZERO_VALOR);
        Double cstTotal = ZERO_COST;
        for (RowExplosionMainTable row: list) {
            if (row.getWeekOrMonth() == FIRTS_WEEK_OR_MONTH) row.setProduction(EOQ);
            if (endInventory < row.getDemand() && row.getWeekOrMonth() != FIRTS_WEEK_OR_MONTH) {
                EOQ = getNewOrder(list, row.getWeekOrMonth());
                row.setProduction(EOQ - endInventory);
            }
            if (row.getProduction() == ZERO_VALOR) row.setS(ZERO_COST);
            row.setEndInventory(EOQ - row.getDemand());
            endInventory = row.getEndInventory();
            row.setH(H * row.getEndInventory());
            cstTotal += row.getH() + row.getS();
            row.setCT(cstTotal);
            EOQ -= row.getDemand();
        }
        return list;
    }

    private static int getNewOrder(List<RowExplosionMainTable> list, final Integer position) {
        return list.stream().
                filter(post -> post.getWeekOrMonth() >= position).
                mapToInt(sum -> sum.getDemand()).
                sum();
    }

    public static ExplosionTable getLTC(List<RowExplosionMainTable> list, List<RowExplosionAuxiliarTable> auxList) {
        return null;
    }

    public static ExplosionTable getLUC(List<RowExplosionMainTable> list, List<RowExplosionAuxiliarTable> auxList) {
        return null;
    }
}



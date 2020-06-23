package mgr.utils.inventory;

import mgr.utils.productionutils.ExplosionTable;
import mgr.utils.productionutils.RowExplosionAuxiliarTable;
import mgr.utils.productionutils.RowExplosionMainTable;

import java.util.List;

/**
 * Planificador de requirimientos de materiales es un
 * algoritmo que permite obtener los componentes de un producto
 * justo a tiempo
 *
 * Los siguientes algoritmos cuentan con las partes del mismo,
 * mas no estan entrelazadas aun
 */
public class MRP {

    private static final int POSITION_ZERO = 0;
    private static final int ZERO_VALOR = 0;
    private static final double ZERO_COST = 0.0;
    private static final int TOTAL_WEEKS_IN_YEAR = 52;
    private static final int TOTAL_MONTHS_IN_YEAR = 12;
    private static final int FIRTS_WEEK_OR_MONTH = 1;
    private enum TypeMethod {
        LTC,
        LUC
    }

    /**
     * El algoritmo calcula el MRP por medio de la estrategia L4L
     *
     * @param list lista de filas pricipales de una tabla de explosion
     * @return la lista de filas con la estrategia l4l implementada
     */
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

    /**
     *  El algoritmo calcula el MRP por medio de la estrategia EOQ
     *
     * @param list lista de filas de una tabla de explocion
     * @return la lista de filas con la estrategia de eoq implementada
     */
    public static List<RowExplosionMainTable> getEOQ(List<RowExplosionMainTable> list) {
        return getEOQ(list, null);
    }

    // metodo para 3 tipos de estrategias
    private static List<RowExplosionMainTable> getEOQ(List<RowExplosionMainTable> list, Integer EOQ) {
        Double sumOfDemand = list.stream().
                mapToDouble(sum -> sum.getDemand()).
                sum();
        Integer time = (list.get(POSITION_ZERO).isWeek() == true)?
                TOTAL_WEEKS_IN_YEAR: TOTAL_MONTHS_IN_YEAR;
        Double D = sumOfDemand / list.size() * time;
        Double H = list.get(POSITION_ZERO).getH();
        if (EOQ == null)
            EOQ = (int) Math.floor(Math.sqrt((2 * D * list.get(POSITION_ZERO).getS()) /
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

    // uso interno, calculo de la nueva orden porfalta de partes
    private static int getNewOrder(List<RowExplosionMainTable> list, final Integer position) {
        return list.stream().
                filter(post -> post.getWeekOrMonth() >= position).
                mapToInt(sum -> sum.getDemand()).
                sum();
    }

    /**
     * El algoritmo calcula el MRP por la estrategia de LTC
     *
     * @param list lista de filas de una tabla principal
     * @param auxList lista de filas de una tabla auxiliar (secundaria)
     * @return una tabla de explocion a partir de todas las filas de entrada
     */
    public static ExplosionTable getLTC(List<RowExplosionMainTable> list, List<RowExplosionAuxiliarTable> auxList) {
        final Integer mayorIndex = indexSelected(list, auxList, TypeMethod.LTC);
        list = getEOQ(list, list.stream().
                filter(index -> index.getWeekOrMonth() <= mayorIndex + 1).
                mapToInt(sum -> sum.getDemand()).
                sum());
        return new ExplosionTable(auxList, list);
    }

    /**
     * El algoritmo calcula el MRP por la estrategia de LUC
     *
     * @param list lista de filas de una tabla principal
     * @param auxList lista de filas de una tabla auxiliar (secundaria)
     * @return una tabla de explocion a partir de todas las filas de entrada
     */
    public static ExplosionTable getLUC(List<RowExplosionMainTable> list, List<RowExplosionAuxiliarTable> auxList) {
        final Integer minorIndex = indexSelected(list, auxList, TypeMethod.LUC);
        list = getEOQ(list, list.stream().
                filter(index -> index.getWeekOrMonth() <= minorIndex + 1).
                mapToInt(sum -> sum.getDemand()).
                sum());
        return new ExplosionTable(auxList, list);
    }

    // algoritmo de uso interno
    private static int indexSelected (List<RowExplosionMainTable> list, List<RowExplosionAuxiliarTable> auxList, TypeMethod typeMethod) {
        Integer sumOfLots = ZERO_VALOR;
        Double sumOfH = ZERO_COST;
        Double H = list.get(POSITION_ZERO).getH();
        for (int index = 0; index < auxList.size(); ++index) {
            auxList.get(index).setLot(list.get(index).getDemand() + sumOfLots);
            auxList.get(index).setH(H * list.get(index).getDemand() * index + sumOfH);
            auxList.get(index).setS(list.get(index).getS());
            auxList.get(index).setCT(auxList.get(index).getH() + auxList.get(index).getS());
            sumOfLots = auxList.get(index).getLot();
            sumOfH = auxList.get(index).getH();
        }
        if (typeMethod == TypeMethod.LTC) {
            final Integer mayorIndex;
            Integer minorIndexBotton = POSITION_ZERO;
            Integer minorIndexUp = POSITION_ZERO;
            Double minorDiferenceBotton = ZERO_COST;
            Double minorDiferenceUp = ZERO_COST;
            for (int index = 0; index < auxList.size(); ++index) {
                if (auxList.get(index).getH() < auxList.get(index).getS()) {
                    minorIndexBotton = index;
                    minorDiferenceBotton = auxList.get(index).getS() - auxList.get(index).getH();
                } else {
                    minorIndexUp = index;
                    minorDiferenceUp = auxList.get(index).getH() - auxList.get(index).getS();
                    break;
                }
            }
            if (minorDiferenceBotton < minorDiferenceUp) mayorIndex = minorIndexBotton;
            else mayorIndex = minorIndexUp;
            return mayorIndex;
        }
        // LUC Metodo
        Integer minorIndex = ZERO_VALOR;
        Double minorCst = Double.MAX_VALUE;
        for (int index = 0; index < auxList.size(); ++index)
            if (minorCst > auxList.get(index).getCT() / auxList.get(index).getLot()) {
                minorIndex = index;
                minorCst = auxList.get(index).getCT() / auxList.get(index).getLot();
            }
        return minorIndex;
    }
}


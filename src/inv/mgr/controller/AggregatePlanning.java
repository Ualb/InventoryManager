package controller;

import model.MonthAggregatePlanning;
import model.PlainAggregatePlanning;

/**
 * La planiacion agregada es una herramienta para
 * calcular cuanto me cuesta la produccion requerida
 * a la demandas del medio
 */
public class AggregatePlanning {

    // tipos de opciones en la fuerza nivelada
    private enum Type {WITH_OVERTIME, WITHOUT_OVERTIME, PER_MONTH};

    /**
     * El algoritmo calcula mediante los datos de un plan de produccion
     * aplicandole la estrategia de persecucion de la demanda, esta estrategia
     * se adecua a la demanda, despidiendo o contratando personal
     *
     * @param plain plan de produccion
     * @return el plan de produccion con la estrategia de persecucion en ella
     */
    public static PlainAggregatePlanning getPersuitStrategy (PlainAggregatePlanning plain) {
        int endInventory = 0;
        int employeesAfter = 0;
        int employeeBefore = 0;
        double cstTotal = 0.0;
        for (MonthAggregatePlanning month: plain.getList()) {
            if (endInventory != 0) month.setStartInventory(endInventory);
            else month.setStartInventory(plain.getStartInventory());
            if ((plain.getStockSecurityPorcentual() > 0))
                month.setStockSecurity((int) (plain.getStockSecurityPorcentual() * month.getDemand()));
            else month.setStockSecurity(plain.getStockSecurity());
            if (month.getDemand() - plain.getStartInventory() >= 0)
                month.setProductionRequered(month.getDemand() - month.getStartInventory() + month.getStockSecurity());
            else month.setProductionRequered(-(month.getStartInventory()- month.getDemand() - month.getStockSecurity()));
            month.setEndInventory(month.getStockSecurity());
            endInventory = month.getEndInventory();
            month.setHoursRequered(month.getProductionRequered() * plain.getRequeredTime());
            month.setHoursAvaileble(plain.HOURS_PER_DAY * month.getDaysAvaileble());
            if (employeesAfter == 0 && plain.getMOI() != 0) employeesAfter = plain.getMOI();
            else if (plain.getMOI() == 0) {
                employeesAfter = (int) Math.round(month.getHoursRequered() / month.getHoursAvaileble());
                month.setCstRecruitment(employeesAfter * plain.getRecruitment());
            } else {
                employeeBefore = employeesAfter;
                employeesAfter = (int) Math.round(month.getHoursRequered() / month.getHoursAvaileble());
                if (employeeBefore > employeesAfter)
                    month.setCstDismissal((employeeBefore - employeesAfter) * plain.getDismissal());
                else month.setCstRecruitment((employeesAfter - employeeBefore) * plain.getRecruitment());
            }
            month.setNumberOfEmployeesRequered(employeesAfter);
            month.setCstNormal(month.getHoursRequered() * plain.getNormalTime());
            month.setCstMaterials(plain.getProductCst() * month.getProductionRequered());
            month.setCstH(plain.getH() * month.getStockSecurity());
            month.setCstTotal(month.getCstRecruitment() + month.getCstDismissal() + month.getCstNormal() + month.getCstH() + month.getCstMaterials());
            cstTotal += month.getCstTotal();
        }
        plain.setCstTotal(cstTotal);
        return plain;
    }

    /**
     * La estrategia de fuerza nivelada, es aquella que nivela
     * la cantidad de trabajadores y pierde por no tener
     *
     * @param plain plan de produccion
     * @return el plan de produccion con la estrategia de fuerza nivelada en el
     */
    public static PlainAggregatePlanning getLevelForce (PlainAggregatePlanning plain) {
        return getLevelForceWithType(plain, Type.WITHOUT_OVERTIME);
    }

    /**
     * La estrategia de fuerza nivelada puede tener horas extras de sus
     * empleados con el fin de evitar las perdidas por el faltante
     *
     * @see #getLevelForce(PlainAggregatePlanning)
     * @param plain plan de produccion
     * @return el plan de produccion con la estrategia de fuerza nivelada con horas extras
     */
    public static PlainAggregatePlanning getLevelForceWithOvertime (PlainAggregatePlanning plain) {
        return getLevelForceWithType(plain, Type.WITH_OVERTIME);
    }

    // metodo para 3 tipos de fuerza nivelada, normal, con horas extras y por tipo de mes
    private static PlainAggregatePlanning getLevelForceWithType (PlainAggregatePlanning plain, Type type) {
        int endInventory = 0;
        boolean isFirst = true;
        boolean isOver = false;
        double cstTotal = 0.0;
        if (!(plain.getForceLeven() > 0))
            plain.setForceLeven(
                    (int) ((plain.getRequeredTime() / plain.HOURS_PER_DAY) *
                            (plain.getList().stream().mapToDouble(b -> b.getDemand()).sum() /
                                    plain.getList().stream().mapToDouble(b -> b.getDaysAvaileble()).sum())));
        for (MonthAggregatePlanning month: plain.getList()) {
            month.setHoursAvaileble(plain.HOURS_PER_DAY * month.getDaysAvaileble() * plain.getForceLeven());
            month.setRealProduction((int) Math.round(month.getHoursAvaileble() / plain.getRequeredTime()));
            if (endInventory > 0) month.setStartInventory(endInventory);
            else if ((!isFirst && endInventory < 0) || isOver) {
                month.setStartInventory(0);
                isOver = false;
            }
            else {
                month.setStartInventory(plain.getStartInventory());
                isFirst = false;
            }
            if (month.getStartInventory() != 0)
                endInventory = month.getRealProduction() + month.getStartInventory() - month.getDemand();
            else
                endInventory = month.getRealProduction() + endInventory - month.getDemand();
            if (type.equals(Type.WITH_OVERTIME)) {
                if (endInventory < 0) {
                    month.setCstExtraHour((int) Math.round(-endInventory * plain.getRequeredTime()) * plain.getExtraTime());
                    endInventory = 0;
                    isOver = true;
                }
            } else if (type.equals(Type.PER_MONTH)) {
                if (endInventory < 0) {
                    if (month.isExtraHour_NoOutsourcing()) {
                        month.setCstExtraHour((int) Math.round(-endInventory * plain.getRequeredTime()) * plain.getExtraTime());
                    } else month.setCstOutsourcing(-endInventory * plain.getOutsourcing());
                    endInventory = 0;
                    isOver = true;
                }
            }
            month.setEndInventory(endInventory);
            if (endInventory >=  0) month.setCstH(endInventory * plain.getH());
            else month.setCstMissing(-endInventory * plain.getCf());
            month.setCstMaterials(month.getRealProduction() * plain.getProductCst());
            month.setCstNormal(month.getHoursAvaileble() * plain.getNormalTime());
            month.setCstTotal(month.getCstMissing() + month.getCstH() + month.getCstMaterials() + month.getCstNormal() + month.getCstExtraHour() + month.getCstOutsourcing());
            cstTotal += month.getCstTotal();
        }
        plain.setCstTotal(cstTotal);
        return plain;
    }

    /**
     * Si no se quiere pagar horas extras por el faltante en la fuerza nivelada,
     * se puede contratar a una empresa que lo haga.
     *
     * El algoritmo calcula el costo de contratar una empresa ajena para la fabricacion
     * de productos
     *
     * @param plain el plan de produccion
     * @return el plan de produccion con la estrategia de sub contratacion a una empresa
     */
    public static PlainAggregatePlanning getLevelForceWithOutsourcing (PlainAggregatePlanning plain) {
        int indexMinium = 0;
        int miniumDemand = Integer.MAX_VALUE;
        double cstTotal = 0.0;
        for (int i = 0; i < plain.getList().size(); ++i) {
            if (plain.getList().get(i).getDemand() < miniumDemand) {
                indexMinium = i;
                miniumDemand = plain.getList().get(i).getDemand();
            }
        }
        plain.setMOI((int) Math.round((plain.getList().get(indexMinium).getDemand()
                * plain.getRequeredTime()) /
                (plain.getList().get(indexMinium).getDaysAvaileble()
                        * plain.HOURS_PER_DAY)));
        for (MonthAggregatePlanning month: plain.getList()) {
            month.setHoursAvaileble(month.getDaysAvaileble() * plain.HOURS_PER_DAY * plain.getMOI());
            month.setRealProduction((int) (month.getHoursAvaileble() / plain.getRequeredTime()));
            month.setProductsWithOutsourcing(month.getDemand() - month.getRealProduction());
            month.setCstOutsourcing(month.getProductsWithOutsourcing() * plain.getOutsourcing());
            month.setCstNormal(month.getHoursAvaileble() * plain.getNormalTime());
            month.setCstMaterials(month.getRealProduction() * plain.getProductCst());
            month.setCstTotal(month.getCstMaterials() + month.getCstNormal() + month.getCstOutsourcing());
            cstTotal += month.getCstTotal();
        }
        plain.setCstTotal(cstTotal);
        return plain;
    }

    /**
     * Cuando se desea tener horas extras un mes y sub-contratar en los siguientes
     * se puede usar este método
     *
     * @param plain el plan de produccion
     * @return el plan de produccion con las dos estrategias al mismo tiempo
     */
    public static PlainAggregatePlanning getLevelForcePerMothType (PlainAggregatePlanning plain) {
        return getLevelForceWithType(plain, Type.PER_MONTH);
    }
}

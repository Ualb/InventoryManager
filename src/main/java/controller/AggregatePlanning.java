package controller;

import model.MonthAggregatePlanning;
import model.PlainAggregatePlanning;

public class AggregatePlanning {

    private enum Type {WITH_OVERTIME, WITHOUT_OVERTIME, PER_MONTH};

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

    public static PlainAggregatePlanning getLevelForce (PlainAggregatePlanning plain) {
        return getLevelForceWithType(plain, Type.WITHOUT_OVERTIME);
    }

    public static PlainAggregatePlanning getLevelForceWithOvertime (PlainAggregatePlanning plain) {
        return getLevelForceWithType(plain, Type.WITH_OVERTIME);
    }

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

    public static PlainAggregatePlanning getLevelForcePerMothType (PlainAggregatePlanning plain) {
        return getLevelForceWithType(plain, Type.PER_MONTH);
    }
}

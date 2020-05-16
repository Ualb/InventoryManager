package controller;

import model.MonthToAggregatePlanning;
import model.PlainToAgreggregatePlanning;

public class AggregatePlanning {

    public static PlainToAgreggregatePlanning getPersuitStrategy (PlainToAgreggregatePlanning plain) {
        int endInventory = 0;
        int employeesAfter = 0;
        int employeeBefore = 0;
        double cstTotal = 0.0;
        for (MonthToAggregatePlanning month: plain.getList()) {
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

    public static PlainToAgreggregatePlanning getLevelForceWithOvertime (PlainToAgreggregatePlanning plain) {
        int endInventory = 0;
        boolean isFirst = true;
        double cstTotal = 0.0;
        if (!(plain.getForceLeven() > 0))
            plain.setForceLeven(
                    (int) ((plain.getRequeredTime() / plain.HOURS_PER_DAY) *
                            (plain.getList().stream().mapToDouble(b -> b.getDemand()).sum() /
                                    plain.getList().stream().mapToDouble(b -> b.getDaysAvaileble()).sum())));
        for (MonthToAggregatePlanning month: plain.getList()) {
            month.setHoursAvaileble(plain.HOURS_PER_DAY * month.getDaysAvaileble() * plain.getForceLeven());
            month.setRealProduction((int) Math.round(month.getHoursAvaileble() / plain.getRequeredTime()));
            if (endInventory > 0) month.setStartInventory(endInventory);
            else if (!isFirst && endInventory < 0) month.setStartInventory(0);
            else {
                month.setStartInventory(plain.getStartInventory());
                isFirst = false;
            }
            if (month.getStartInventory() != 0)
                endInventory = month.getRealProduction() + month.getStartInventory() - month.getDemand();
            else
                endInventory = month.getRealProduction() + endInventory - month.getDemand();
            month.setEndInventory(endInventory);
            if (endInventory >=  0) month.setCstH(endInventory * plain.getH());
            else month.setCstMissing(-endInventory * plain.getCf());
            month.setCstMaterials(month.getRealProduction() * plain.getProductCst());
            month.setCstNormal(month.getHoursAvaileble() * plain.getNormalTime());
            month.setCstTotal(month.getCstMissing() + month.getCstH() + month.getCstMaterials() + month.getCstNormal());
            cstTotal += month.getCstTotal();
        }
        plain.setCstTotal(cstTotal);
        return plain;
    }

    public static void getLevelForceWithOutsourcing () {

    }

    public static void getPerMothType () {

    }
}

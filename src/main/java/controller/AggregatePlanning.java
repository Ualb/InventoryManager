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
            if ((plain.getStockSecurityPorcentual() > 0))
                month.setStockSecurity((int) (plain.getStockSecurityPorcentual() * month.getDemand()));
            else month.setStockSecurity(plain.getStockSecurity());
            if (month.getDemand() - plain.getStartInventory() >= 0)
                month.setProductionRequered(month.getDemand() - plain.getStartInventory() + month.getStockSecurity());
            else month.setProductionRequered(-(month.getStartInventory()- month.getDemand() - month.getStockSecurity()));
            month.setEndInventory(month.getStockSecurity());
            endInventory = month.getEndInventory();
            month.setHoursRequered(month.getProductionRequered() * plain.getRequeredTime());
            month.setHoursAvaileble(plain.HOURS_PER_DAY * month.getDaysAvaileble());
            if (employeesAfter == 0 && plain.getMOI() != 0) employeesAfter = plain.getMOI();
            else if (plain.getMOI() == 0) {
                employeesAfter = (int) Math.round(month.getHoursRequered() / month.getHoursAvaileble());
                month.setCstRecruitment(employeesAfter * plain.getDismissal());
            } else {
                employeeBefore = employeesAfter;
                employeesAfter = (int) Math.round(month.getHoursRequered() / month.getHoursAvaileble());
                if (employeeBefore > employeesAfter)
                    month.setCstDismissal((employeeBefore - employeesAfter) * plain.getDismissal());
                else month.setCstRecruitment((employeesAfter - employeeBefore) * plain.getDismissal());
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

    public static void getLevelForceWithOvertime () {

    }

    public static void getLevelForceWithOutsourcing () {

    }

    public static void getPerMothType () {

    }
}

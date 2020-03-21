package model;

public class QModel {

    private static Double D;
    private static Double H;
    private static Double S;
    private static Double L;
    private static Double C;
    private static Double dayWorkInYear;
    private static Double dayProduction;

    public static void staticInstance(Double D_, Double H_, Double S_, Double L_, Double C_,  Double dayWorkInYear_, Double dayProduction_) {
        D = (D_ == null)? Double.MAX_VALUE: new Double(D_);
        H = (H_ == null)? Double.MAX_VALUE: new Double(H_);
        S = (S_ == null)? Double.MAX_VALUE: new Double(S_);
        L = (L_ == null)? new Double(1): new Double(L_);
        C = (C_ == null)? new Double(1): new Double(C_);
        dayWorkInYear = (dayWorkInYear_ == null)? new Double(360): new Double(dayWorkInYear_);
        dayProduction = (dayProduction_ == null)? new Double(1): new Double(dayProduction_);
    }

    public static Double getQoptimal() {
        Double d = getDayDemand();
        return (dayProduction != d)?
                Math.sqrt((2 * D * S) / H) :
                Math.sqrt((2 * D * S) / (H * (1 - d / dayProduction)));
    }

    public static Double getDayDemand() {
        return D / dayWorkInYear;
    }

    public static Double getgetCostInYear() {
        Double Q = getQoptimal();
        return ((D * S) / Q + (Q * H) / 2 + D * C);
    }

    public static Double geNOrderYear() {
        return D / getQoptimal();
    }

    public static Double getROP() {
        return getDayDemand() * L;
    }

    public static Double getLevelAvarage() {
        return (getROP() + getQoptimal()) / 2;
    }

    public static Double getLevelMax() {
        return getROP() + getQoptimal();
    }
}

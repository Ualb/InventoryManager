package model;

import org.apache.commons.math3.distribution.NormalDistribution;

public class PModel {

    private static Double Cf = null;
    private static Double Cs = null;
    private static Double P = null;
    private static Double sigma = null;
    private static Double miu = null;
    private static Double d = null;
    private static Double L = null;

    public static void staticInstance(Double Cf_, Double Cs_, Double P_, Double sigma_, Double miu_, Double d_, Double L_) {
        Cf = (Cf_ == null)? null: new Double(Cf_);
        Cs = (Cs_ == null)? null: new Double(Cs_);
        if (Cf_ == null || Cs == null) P = (P_ == null)? 1.0: new Double(P_);
        else P = Cf_ / (Cs_ + Cf_);
        sigma = (sigma_ == null)? Double.MAX_VALUE: new Double(sigma_);
        miu = (miu_ == null)? 1: new Double(miu_);
        d = (d_ == null)? null: new Double(d_);
        L = (L_ == null)? null: new Double(L_);
    }

    private static Double getZ() {
        return new NormalDistribution().inverseCumulativeProbability(P);
    }

    public static Double getROP() {
        return ((d == null || L == null)? miu: d * L) + getZ() * sigma;
    }

    public static Double getSigmaAjustada() {
        return Math.sqrt(Math.pow(sigma, 2) * L);
    }
}

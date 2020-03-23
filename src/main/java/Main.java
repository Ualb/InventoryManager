import model.PModel;
import org.apache.commons.math3.distribution.NormalDistribution;

public class Main {

    public static void main(String []data) {
//        NormalDistribution normalDistribution = new NormalDistribution();
//        double inverseCumulativeProbability = normalDistribution.inverseCumulativeProbability(0.95);
//        System.out.println(inverseCumulativeProbability);
//        System.out.println(busquedaBinaria(0.95));

//        PModel.staticInstance(0.3, 0.2, null,  10.0, 90.0, null, null);
//        System.out.println(PModel.getROP());
//
//        PModel.staticInstance(null, null, 0.95, 25.0, 90.0, 4.0, 15.0);
//        System.out.println(PModel.getROP());

    }

    /*public static double FuncionNormal(double x) {
        int neg = (x < 0d) ? 1 : 0;
        if (neg == 1)
            x *= -1d;

        double k = (1d / (1d + 0.2316419 * x));
        double y = ((((1.330274429 * k - 1.821255978) * k + 1.781477937) *
                k - 0.356563782) * k + 0.319381530) * k;
        y = 1.0 - 0.398942280401 * Math.exp(-0.5 * x * x) * y;

        return (1d - neg) * y + neg * (1d - y);
    }

    public static double busquedaBinaria(double x) {
        double centro, inf = -6, sup = 6;
        while (inf <= sup) {
            centro = (sup + inf) / 2;
            if (Math.abs(inf - sup) < 0.0000000000000001) {
                return centro;
            }
            if (Math.abs(FuncionNormal(centro) - x) < 0.00000000001) {
                return centro;
            } else if (FuncionNormal(centro) > x) {
                sup = centro;
            } else {
                inf = centro;
            }
        }
        return Double.MAX_VALUE;
    }*/

}

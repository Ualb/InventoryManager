import org.apache.commons.math3.distribution.NormalDistribution;

public class Main {

    public static void main(String []data) {
        NormalDistribution normalDistribution = new NormalDistribution();
        double inverseCumulativeProbability = normalDistribution.inverseCumulativeProbability(0.95);
        System.out.println(inverseCumulativeProbability);
    }

}

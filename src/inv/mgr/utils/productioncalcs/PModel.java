package mgr.utils.productioncalcs;

import org.apache.commons.math3.distribution.NormalDistribution;

/**
 * PModel
 * Modelo de inventario probabilistico
 * Criterios
 *      <ul>
 *          <li>Demanda Real</li>
 *      </ul>
 */
public class PModel {

    private static Double Cf = null;
    private static Double Cs = null;
    private static Double P = null;
    // Varianza de la muestra
    private static Double sigma = null;
    // Media aritmetica de la muestra
    private static Double miu = null;
    // Demanda diaria
    private static Double d = null;
    // Tiempo entre ordenar y recibir
    private static Double L = null;

    /**
     * Cargar los datos a las variables estaticas
     *
     * @param Cf_ Costo del faltante
     * @param Cs_ Costo del sobrante
     * @param P_ probabilidad del faltante y el total
     * @param sigma_ varianza muestral @NoNull
     * @param miu_ media aritmetica muestral @NoNull
     * @param d_ demanda diaria @NoNuññ
     * @param L_ Tiempo entre ordenar y recibir @NoNull
     */
    public static void staticInstance(Double Cf_, Double Cs_, Double P_, Double sigma_, Double miu_, Double d_, Double L_) {
        Cf = (Cf_ == null)? null: new Double(Cf_);
        Cs = (Cs_ == null)? null: new Double(Cs_);
        // si no se recibe Cs o Cf se solicita que si sea recibido P
        if (Cf_ == null || Cs == null) P = (P_ == null)? 1.0: new Double(P_);
        // si se reciben Cs y Cf
        else P = Cf_ / (Cs_ + Cf_);
        // error no puede ser null
        sigma = (sigma_ == null)? Double.MAX_VALUE: new Double(sigma_);
        miu = (miu_ == null)? 1: new Double(miu_);
        d = (d_ == null)? null: new Double(d_);
        L = (L_ == null)? null: new Double(L_);
    }

    /**
     * El retorno de la probabilidad segun la tabla normal (teorema de Chebyshev)
     *
     * @return Z de la tabla normal
     */
    private static Double getZ() {
        return new NormalDistribution().inverseCumulativeProbability(P);
    }

    /**
     * Nivel de inventario de advertencia sobre la
     * proximidad al stock de seguridad
     *
     * @return punto de inventario donde se debe ordenar
     */
    public static Double getROP() {
        return ((d == null || L == null)? miu: d * L) + getZ() * sigma;
    }

    /**
     * El algoritmo calcula el ajuste a la varianza muestral
     * al corresponder a mas de un dia
     *
     * @return nueva varianza ajustada a la entrega
     */
    public static Double getSigmaAjustada() {
        return Math.sqrt(Math.pow(sigma, 2) * L);
    }
}

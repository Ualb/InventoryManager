package mgr.utils.productioncalcs;

/**
 * QModel (Economic Order Quantity EOQ)
 * Modelo de inventario para demanda constante
 * Criterios:
 *      <ul>
 *          <li>Tasa de demanda constante</li>
 *          <li>El tiempo de anticipacion es constante e igual o mayor que cero</li>
 *          <li>El reemplazo es inmediato</li>
 *          <li>Se conoce la demanda con certeza</li>
 *          <li>C, S, H son constantes</li>
 *      </ul>
 */
public class QModel {

    private static Double D = null;
    private static Double H = null;
    private static Double S = null;
    private static Double L = null;
    private static Double i = null;
    private static Double C = null;
    // Dias de trabajo en el anio

    private static Double dayWorkInYear = null;
    // Produccion diaria
    private static Double dayProduction = null;

    /**
     * Cargar los datos a las variables estaticas
     *
     * @param D_ Demanda anual @NoNull
     * @param H_ Costo de mantenimiento
     * @param S_ Costo de pedir o pedido @NoNull
     * @param L_ Tiempo entre ordenar y recibir @NoNull
     * @param i_ Tasa de mantenimiento
     * @param C_ Costo del articulo
     * @param dayWorkInYear_ Dias laborales en el anio
     * @param dayProduction_ Produccion por dia
     */
    public static void staticInstance(Double D_, Double H_, Double S_, Double L_, Double i_, Double C_,  Double dayWorkInYear_, Double dayProduction_) {
        D = (D_ == null)? null: new Double(D_);
        H = (H_ == null)? null: new Double(H_);
        S = (S_ == null)? null: new Double(S_);
        // plazo de entrega diario
        L = (L_ == null)? new Double(1): new Double(L_);
        i = (i_ == null)? null: new Double(i_);
        C = (C_ == null)? null: new Double(C_);
        // total de dias trabajados en el anio estandar
        dayWorkInYear = (dayWorkInYear_ == null)? new Double(360): new Double(dayWorkInYear_);
        dayProduction = (dayProduction_ == null)? null: new Double(dayProduction_);
    }

    /**
     * El algoritmo calcula la cantidad optima
     * a tener en inventario
     *
     * @return inventario optimo
     */
    public static Double getQoptimal() {
        Double d = getDayDemand();
        return (dayProduction != d)?
                Math.sqrt((2 * D * S) / ((H == null)? i * C: H)) :
                Math.sqrt((2 * D * S) / (((H == null)? i * C: H) * (1 - d / dayProduction)));
    }

    /**
     * Es el periodo de dias que transcurren para
     * poder generar el siguiente pedido
     *
     * @return el tiempo entre cada orden
     */
    public static Double getTimeBetweenOrder() {
        return dayWorkInYear / getNOrderYear();
    }

    /**
     * La demanda diaria conforme la demanda anual
     * y el total de dias trabajados en un anio
     *
     * @return demanda por dia
     */
    public static Double getDayDemand() {
        return D / dayWorkInYear;
    }

    /**
     * El algoritmo calcula el costo optimo de inventario
     *
     * @return costo anual de inventario
     */
    public static Double getCostInYear() {
        Double Q = getQoptimal();
        return ((D * S) / Q + (Q * ((H == null)? i * C: H)) / 2 + D * C);
    }

    /**
     * Numero de ordenes por anio
     *
     * @return cantidad de ordenes optimas
     */
    public static Double getNOrderYear() {
        return D / getQoptimal();
    }

    /**
     * Nivel de inventario de advertencia sobre la
     * proximidad al stock de seguridad
     *
     * @return punto de inventario donde se debe ordenar
     */
    public static Double getROP() {
        return getDayDemand() * L;
    }

    /**
     * Nivel promedio del inventario
     *
     * @return promedio entre el inventario de advertencia y el optimo
     */
    public static Double getLevelAvarage() {
        return (getROP() + getQoptimal()) / 2;
    }

    /**
     * Maximo nivel de inventario posible
     *
     * @return punto de sobre avastecimiento
     */
    public static Double getLevelMax() {
        return getROP() + getQoptimal();
    }
}

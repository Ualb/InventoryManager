package inv.mgr.utils.statutils;

import inv.mgr.model.entities.DemandaEntity;

import java.util.List;

public class Statistic {

    private List<DemandaEntity> demandas;
    private double media;

    public Statistic(List<DemandaEntity> demandas) {
        this.demandas = demandas;

        double suma = 0;
        for (DemandaEntity d : demandas){
            suma += d.getCantidad();
        }
        this.media=suma/ demandas.size();
    }

    public List<DemandaEntity> getDemandas() {
        return demandas;
    }

    public void setDemandas(List<DemandaEntity> demandas) {
        this.demandas = demandas;
    }

    public double getMedia() {
        return media;
    }

    public void setMedia(double media) {
        this.media = media;
    }

    /**
     *
     * @return desviación estandar
     */
    public double sD(){
        double var = 0;
        for (DemandaEntity d:
             this.demandas) {
            double rango;
            rango = Math.pow(d.getCantidad() - this.media, 2f);
            var += rango;
        }
        var /= this.demandas.size();
        return Math.sqrt(var);
    }
}

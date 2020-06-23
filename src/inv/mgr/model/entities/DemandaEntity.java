package mgr.model.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "demanda", schema = "xtf0qnoava02o4qj", catalog = "")
public class DemandaEntity {
    private int demandaId;
    private String uTiempo;
    private double cTiempo;
    private Double tDisponible;

    @Id
    @Column(name = "demanda_id")
    public int getDemandaId() {
        return demandaId;
    }

    public void setDemandaId(int demandaId) {
        this.demandaId = demandaId;
    }

    @Basic
    @Column(name = "u_tiempo")
    public String getuTiempo() {
        return uTiempo;
    }

    public void setuTiempo(String uTiempo) {
        this.uTiempo = uTiempo;
    }

    @Basic
    @Column(name = "c_tiempo")
    public double getcTiempo() {
        return cTiempo;
    }

    public void setcTiempo(double cTiempo) {
        this.cTiempo = cTiempo;
    }

    @Basic
    @Column(name = "t_disponible")
    public Double gettDisponible() {
        return tDisponible;
    }

    public void settDisponible(Double tDisponible) {
        this.tDisponible = tDisponible;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DemandaEntity that = (DemandaEntity) o;
        return demandaId == that.demandaId &&
                Double.compare(that.cTiempo, cTiempo) == 0 &&
                Objects.equals(uTiempo, that.uTiempo) &&
                Objects.equals(tDisponible, that.tDisponible);
    }

    @Override
    public int hashCode() {
        return Objects.hash(demandaId, uTiempo, cTiempo, tDisponible);
    }
}

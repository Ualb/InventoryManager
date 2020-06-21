package inv.mgr.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "demanda", schema = "main", catalog = "")
public class DemandaEntity {
    private Short demandaId;
    private String uTiempo;
    private Object cTiempo;
    private Object tDisponible;

    @Id
    @Column(name = "demanda_id")
    public Short getDemandaId() {
        return demandaId;
    }

    public void setDemandaId(Short demandaId) {
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
    public Object getcTiempo() {
        return cTiempo;
    }

    public void setcTiempo(Object cTiempo) {
        this.cTiempo = cTiempo;
    }

    @Basic
    @Column(name = "t_disponible")
    public Object gettDisponible() {
        return tDisponible;
    }

    public void settDisponible(Object tDisponible) {
        this.tDisponible = tDisponible;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DemandaEntity that = (DemandaEntity) o;
        return Objects.equals(demandaId, that.demandaId) &&
                Objects.equals(uTiempo, that.uTiempo) &&
                Objects.equals(cTiempo, that.cTiempo) &&
                Objects.equals(tDisponible, that.tDisponible);
    }

    @Override
    public int hashCode() {
        return Objects.hash(demandaId, uTiempo, cTiempo, tDisponible);
    }
}

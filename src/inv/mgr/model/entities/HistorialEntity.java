package inv.mgr.model.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "historial", schema = "main", catalog = "")
public class HistorialEntity {
    private Short historialId;
    private Object qOptimo;
    private Object rop;
    private Object tEspera;
    private Object fecha;

    @Id
    @Column(name = "historial_id")
    public Short getHistorialId() {
        return historialId;
    }

    public void setHistorialId(Short historialId) {
        this.historialId = historialId;
    }

    @Basic
    @Column(name = "q_optimo")
    public Object getqOptimo() {
        return qOptimo;
    }

    public void setqOptimo(Object qOptimo) {
        this.qOptimo = qOptimo;
    }

    @Basic
    @Column(name = "rop")
    public Object getRop() {
        return rop;
    }

    public void setRop(Object rop) {
        this.rop = rop;
    }

    @Basic
    @Column(name = "t_espera")
    public Object gettEspera() {
        return tEspera;
    }

    public void settEspera(Object tEspera) {
        this.tEspera = tEspera;
    }

    @Basic
    @Column(name = "fecha")
    public Object getFecha() {
        return fecha;
    }

    public void setFecha(Object fecha) {
        this.fecha = fecha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistorialEntity that = (HistorialEntity) o;
        return Objects.equals(historialId, that.historialId) &&
                Objects.equals(qOptimo, that.qOptimo) &&
                Objects.equals(rop, that.rop) &&
                Objects.equals(tEspera, that.tEspera) &&
                Objects.equals(fecha, that.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(historialId, qOptimo, rop, tEspera, fecha);
    }
}

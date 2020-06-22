package inv.mgr.model.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "historial", schema = "xtf0qnoava02o4qj", catalog = "")
public class HistorialEntity {
    private int historialId;
    private double qOptimo;
    private double rop;
    private double tEspera;
    private Date fecha;

    @Id
    @Column(name = "historial_id")
    public int getHistorialId() {
        return historialId;
    }

    public void setHistorialId(int historialId) {
        this.historialId = historialId;
    }

    @Basic
    @Column(name = "q_optimo")
    public double getqOptimo() {
        return qOptimo;
    }

    public void setqOptimo(double qOptimo) {
        this.qOptimo = qOptimo;
    }

    @Basic
    @Column(name = "rop")
    public double getRop() {
        return rop;
    }

    public void setRop(double rop) {
        this.rop = rop;
    }

    @Basic
    @Column(name = "t_espera")
    public double gettEspera() {
        return tEspera;
    }

    public void settEspera(double tEspera) {
        this.tEspera = tEspera;
    }

    @Basic
    @Column(name = "fecha")
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistorialEntity that = (HistorialEntity) o;
        return historialId == that.historialId &&
                Double.compare(that.qOptimo, qOptimo) == 0 &&
                Double.compare(that.rop, rop) == 0 &&
                Double.compare(that.tEspera, tEspera) == 0 &&
                Objects.equals(fecha, that.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(historialId, qOptimo, rop, tEspera, fecha);
    }
}

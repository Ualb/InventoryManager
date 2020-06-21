package inv.mgr.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "producto", schema = "main", catalog = "")
public class ProductoEntity {
    private Short productoId;
    private String nombre;
    private Object reserva;
    private Short nivel;
    private Object costo;
    private Object cS;
    private Object cL;
    private Object cH;
    private String tipoDemanda;

    @Id
    @Column(name = "producto_id")
    public Short getProductoId() {
        return productoId;
    }

    public void setProductoId(Short productoId) {
        this.productoId = productoId;
    }

    @Basic
    @Column(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "reserva")
    public Object getReserva() {
        return reserva;
    }

    public void setReserva(Object reserva) {
        this.reserva = reserva;
    }

    @Basic
    @Column(name = "nivel")
    public Short getNivel() {
        return nivel;
    }

    public void setNivel(Short nivel) {
        this.nivel = nivel;
    }

    @Basic
    @Column(name = "costo")
    public Object getCosto() {
        return costo;
    }

    public void setCosto(Object costo) {
        this.costo = costo;
    }

    @Basic
    @Column(name = "cS")
    public Object getcS() {
        return cS;
    }

    public void setcS(Object cS) {
        this.cS = cS;
    }

    @Basic
    @Column(name = "cL")
    public Object getcL() {
        return cL;
    }

    public void setcL(Object cL) {
        this.cL = cL;
    }

    @Basic
    @Column(name = "cH")
    public Object getcH() {
        return cH;
    }

    public void setcH(Object cH) {
        this.cH = cH;
    }

    @Basic
    @Column(name = "tipoDemanda")
    public String getTipoDemanda() {
        return tipoDemanda;
    }

    public void setTipoDemanda(String tipoDemanda) {
        this.tipoDemanda = tipoDemanda;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductoEntity that = (ProductoEntity) o;
        return Objects.equals(productoId, that.productoId) &&
                Objects.equals(nombre, that.nombre) &&
                Objects.equals(reserva, that.reserva) &&
                Objects.equals(nivel, that.nivel) &&
                Objects.equals(costo, that.costo) &&
                Objects.equals(cS, that.cS) &&
                Objects.equals(cL, that.cL) &&
                Objects.equals(cH, that.cH) &&
                Objects.equals(tipoDemanda, that.tipoDemanda);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productoId, nombre, reserva, nivel, costo, cS, cL, cH, tipoDemanda);
    }
}

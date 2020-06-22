package inv.mgr.model.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "producto", schema = "xtf0qnoava02o4qj", catalog = "")
public class ProductoEntity {
    private int productoId;
    private String nombre;
    private Double reserva;
    private Integer nivel;
    private Double costo;
    private Double cS;
    private Double cL;
    private Double cH;
    private String tipoDemanda;
    private String tipoProducto;

    @Id
    @Column(name = "producto_id")
    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
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
    public Double getReserva() {
        return reserva;
    }

    public void setReserva(Double reserva) {
        this.reserva = reserva;
    }

    @Basic
    @Column(name = "nivel")
    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    @Basic
    @Column(name = "costo")
    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    @Basic
    @Column(name = "cS")
    public Double getcS() {
        return cS;
    }

    public void setcS(Double cS) {
        this.cS = cS;
    }

    @Basic
    @Column(name = "cL")
    public Double getcL() {
        return cL;
    }

    public void setcL(Double cL) {
        this.cL = cL;
    }

    @Basic
    @Column(name = "cH")
    public Double getcH() {
        return cH;
    }

    public void setcH(Double cH) {
        this.cH = cH;
    }

    @Basic
    @Column(name = "tipo_demanda")
    public String getTipoDemanda() {
        return tipoDemanda;
    }

    public void setTipoDemanda(String tipoDemanda) {
        this.tipoDemanda = tipoDemanda;
    }

    @Basic
    @Column(name = "tipo_producto")
    public String getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductoEntity that = (ProductoEntity) o;
        return productoId == that.productoId &&
                Objects.equals(nombre, that.nombre) &&
                Objects.equals(reserva, that.reserva) &&
                Objects.equals(nivel, that.nivel) &&
                Objects.equals(costo, that.costo) &&
                Objects.equals(cS, that.cS) &&
                Objects.equals(cL, that.cL) &&
                Objects.equals(cH, that.cH) &&
                Objects.equals(tipoDemanda, that.tipoDemanda) &&
                Objects.equals(tipoProducto, that.tipoProducto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productoId, nombre, reserva, nivel, costo, cS, cL, cH, tipoDemanda, tipoProducto);
    }
}

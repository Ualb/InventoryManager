package model;

import model.RowExplosionAuxiliarTable;
import model.RowExplosionMainTable;

import java.util.List;

public class ExplosionTable {

    private List<RowExplosionAuxiliarTable> auxiliarTable;
    private  List<RowExplosionMainTable> mainTable;

    public ExplosionTable() {
    }

    public ExplosionTable(List<RowExplosionAuxiliarTable> auxiliarTable, List<RowExplosionMainTable> mainTable) {
        this.auxiliarTable = auxiliarTable;
        this.mainTable = mainTable;
    }

    public List<RowExplosionAuxiliarTable> getAuxiliarTable() {
        return auxiliarTable;
    }

    public void setAuxiliarTable(List<RowExplosionAuxiliarTable> auxiliarTable) {
        this.auxiliarTable = auxiliarTable;
    }

    public List<RowExplosionMainTable> getMainTable() {
        return mainTable;
    }

    public void setMainTable(List<RowExplosionMainTable> mainTable) {
        this.mainTable = mainTable;
    }

    @Override
    public String toString() {
        return "ExplosionTable{" +
                "auxiliarTable=" + auxiliarTable +
                ", mainTable=" + mainTable +
                '}';
    }
}

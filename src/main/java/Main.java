import controller.*;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String []data) {
        // test modelo P
        PModel.staticInstance(0.3, 0.2, null,  10.0, 90.0, null, null);
        System.out.println(PModel.getROP());

        PModel.staticInstance(null, null, 0.95, 25.0, 90.0, 4.0, 15.0);
        System.out.println(PModel.getROP());
        // test modelo Q
        QModel.staticInstance(1500000.0,null, 80.0,2.0,0.1 ,2.5, 360.0, null);
        System.out.println(QModel.getQoptimal());
        System.out.println(QModel.getNOrderYear());
        System.out.println(QModel.getTimeBetweenOrder());
        System.out.println(QModel.getROP());
        System.out.println(QModel.getLevelAvarage());
        System.out.println(QModel.getLevelMax());
        System.out.println(QModel.getCostInYear());

        //mrp test, tamanio del lote l4l
        List<RowExplosionMainTable> list = Arrays.asList(
                new RowExplosionMainTable(1, 50, 47.0, false),
                new RowExplosionMainTable(2, 60, 47.0, false),
                new RowExplosionMainTable(3, 70, 47.0, false),
                new RowExplosionMainTable(4, 60, 47.0, false),
                new RowExplosionMainTable(5, 95, 47.0, false),
                new RowExplosionMainTable(6, 75, 47.0, false),
                new RowExplosionMainTable(7, 60, 47.0, false),
                new RowExplosionMainTable(8, 55, 47.0, false));

        list = MRP.getL4L(list);
        for(RowExplosionMainTable row: list) {
            System.out.println(row);
        }

        list = Arrays.asList(
                new RowExplosionMainTable(1, 50, 47.0, true, 0.05),
                new RowExplosionMainTable(2, 60, 47.0, true, 0.05),
                new RowExplosionMainTable(3, 70, 47.0, true, 0.05),
                new RowExplosionMainTable(4, 60, 47.0, true, 0.05),
                new RowExplosionMainTable(5, 95, 47.0, true, 0.05),
                new RowExplosionMainTable(6, 75, 47.0, true, 0.05),
                new RowExplosionMainTable(7, 60, 47.0, true, 0.05),
                new RowExplosionMainTable(8, 55, 47.0, true, 0.05));

        System.out.println("--------------------------------");

        list = MRP.getEOQ(list);
        for(RowExplosionMainTable row: list) {
            System.out.println(row);
        }

        list = Arrays.asList(
                new RowExplosionMainTable(1, 50, 47.0, true, 0.05),
                new RowExplosionMainTable(2, 60, 47.0, true, 0.05),
                new RowExplosionMainTable(3, 70, 47.0, true, 0.05),
                new RowExplosionMainTable(4, 60, 47.0, true, 0.05),
                new RowExplosionMainTable(5, 95, 47.0, true, 0.05),
                new RowExplosionMainTable(6, 75, 47.0, true, 0.05),
                new RowExplosionMainTable(7, 60, 47.0, true, 0.05),
                new RowExplosionMainTable(8, 55, 47.0, true, 0.05));

        List<RowExplosionAuxiliarTable> auxiliarTables =
                Arrays.asList(
                        new RowExplosionAuxiliarTable("1", 0, 0.0, 0.0, 0.0,0.0),
                        new RowExplosionAuxiliarTable("1-2", 0, 0.0, 0.0, 0.0,0.0),
                        new RowExplosionAuxiliarTable("1-2-3", 0, 0.0, 0.0, 0.0,0.0),
                        new RowExplosionAuxiliarTable("1-2-3-4", 0, 0.0, 0.0, 0.0,0.0),
                        new RowExplosionAuxiliarTable("1-2-3-4-5", 0, 0.0, 0.0, 0.0,0.0),
                        new RowExplosionAuxiliarTable("1-2-3-4-5-6", 0, 0.0, 0.0, 0.0,0.0),
                        new RowExplosionAuxiliarTable("1-2-3-4-5-6-7", 0, 0.0, 0.0, 0.0,0.0),
                        new RowExplosionAuxiliarTable("1-2-3-4-5-6-7-8", 0, 0.0, 0.0, 0.0,0.0)
                );

        System.out.println("--------------------------------");

        ExplosionTable explosionTable = MRP.getLTC(list, auxiliarTables);
        list = explosionTable.getMainTable();
        auxiliarTables = explosionTable.getAuxiliarTable();
        for (RowExplosionAuxiliarTable row: auxiliarTables) {
            System.out.println(row);
        }
        for (RowExplosionMainTable row: list) {
            System.out.println(row);
        }

        list = Arrays.asList(
                new RowExplosionMainTable(1, 50, 47.0, true, 0.05),
                new RowExplosionMainTable(2, 60, 47.0, true, 0.05),
                new RowExplosionMainTable(3, 70, 47.0, true, 0.05),
                new RowExplosionMainTable(4, 60, 47.0, true, 0.05),
                new RowExplosionMainTable(5, 95, 47.0, true, 0.05),
                new RowExplosionMainTable(6, 75, 47.0, true, 0.05),
                new RowExplosionMainTable(7, 60, 47.0, true, 0.05),
                new RowExplosionMainTable(8, 55, 47.0, true, 0.05));

        auxiliarTables =
                Arrays.asList(
                        new RowExplosionAuxiliarTable("1", 0, 0.0, 0.0, 0.0,0.0),
                        new RowExplosionAuxiliarTable("1-2", 0, 0.0, 0.0, 0.0,0.0),
                        new RowExplosionAuxiliarTable("1-2-3", 0, 0.0, 0.0, 0.0,0.0),
                        new RowExplosionAuxiliarTable("1-2-3-4", 0, 0.0, 0.0, 0.0,0.0),
                        new RowExplosionAuxiliarTable("1-2-3-4-5", 0, 0.0, 0.0, 0.0,0.0),
                        new RowExplosionAuxiliarTable("1-2-3-4-5-6", 0, 0.0, 0.0, 0.0,0.0),
                        new RowExplosionAuxiliarTable("1-2-3-4-5-6-7", 0, 0.0, 0.0, 0.0,0.0),
                        new RowExplosionAuxiliarTable("1-2-3-4-5-6-7-8", 0, 0.0, 0.0, 0.0,0.0)
                );

        System.out.println("--------------------------------");

        explosionTable = MRP.getLUC(list, auxiliarTables);
        list = explosionTable.getMainTable();
        auxiliarTables = explosionTable.getAuxiliarTable();
        for (RowExplosionAuxiliarTable row: auxiliarTables) {
            System.out.println(row);
        }
        for (RowExplosionMainTable row: list) {
            System.out.println(row);
        }
    }
}

import controller.*;
import model.*;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String []data) {
        // test modelo P
        PModel.staticInstance(0.3, 0.2, null,  10.0, 90.0, null, null);
        System.out.println(PModel.getROP());

        PModel.staticInstance(null, null, 0.95, 25.0, 90.0, 4.0, 15.0);
        System.out.println(PModel.getROP());

        System.out.println("--------------------------------");
        // test modelo Q
        QModel.staticInstance(1500000.0,null, 80.0,2.0,0.1 ,2.5, 360.0, null);
        System.out.println(QModel.getQoptimal());
        System.out.println(QModel.getNOrderYear());
        System.out.println(QModel.getTimeBetweenOrder());
        System.out.println(QModel.getROP());
        System.out.println(QModel.getLevelAvarage());
        System.out.println(QModel.getLevelMax());
        System.out.println(QModel.getCostInYear());

        System.out.println("--------------------------------");
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

//       MRP EOQ

        System.out.println("--------------------------------");
        list = Arrays.asList(
                new RowExplosionMainTable(1, 50, 47.0, true, 0.05),
                new RowExplosionMainTable(2, 60, 47.0, true, 0.05),
                new RowExplosionMainTable(3, 70, 47.0, true, 0.05),
                new RowExplosionMainTable(4, 60, 47.0, true, 0.05),
                new RowExplosionMainTable(5, 95, 47.0, true, 0.05),
                new RowExplosionMainTable(6, 75, 47.0, true, 0.05),
                new RowExplosionMainTable(7, 60, 47.0, true, 0.05),
                new RowExplosionMainTable(8, 55, 47.0, true, 0.05));


        list = MRP.getEOQ(list);
        for(RowExplosionMainTable row: list) {
            System.out.println(row);
        }

//      MRP LTC
        System.out.println("--------------------------------");
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


        ExplosionTable explosionTable = MRP.getLTC(list, auxiliarTables);
        list = explosionTable.getMainTable();
        auxiliarTables = explosionTable.getAuxiliarTable();
        for (RowExplosionAuxiliarTable row: auxiliarTables) {
            System.out.println(row);
        }
        for (RowExplosionMainTable row: list) {
            System.out.println(row);
        }

//      MRP LUC
        System.out.println("--------------------------------");
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

        explosionTable = MRP.getLUC(list, auxiliarTables);
        list = explosionTable.getMainTable();
        auxiliarTables = explosionTable.getAuxiliarTable();
        for (RowExplosionAuxiliarTable row: auxiliarTables) {
            System.out.println(row);
        }
        for (RowExplosionMainTable row: list) {
            System.out.println(row);
        }

//       Planeacion agregada - Estrategia de persecucion
        System.out.println("---------------persecution estrategy-----------------");
        List<MonthAggregatePlanning> list2 = Arrays.asList(
                new MonthAggregatePlanning(1,1800, 22),
                new MonthAggregatePlanning(2,1500, 19),
                new MonthAggregatePlanning(3,1100, 21),
                new MonthAggregatePlanning(4,900, 21),
                new MonthAggregatePlanning(5,1100, 22),
                new MonthAggregatePlanning(6,1600, 20)
        );

        PlainAggregatePlanning plain =
                new PlainAggregatePlanning(10, 1.5, 5, 200, 250, 4.0, 6.0, 400, 5.0, 0.25, 0, 20,53, list2,0);

        plain = AggregatePlanning.getPersuitStrategy(plain);

        for (MonthAggregatePlanning month: plain.getList()) {
            System.out.println(month);
        }

//      Planeacion agregada - Fuerza nivelada
        System.out.println("---------------level force-----------------");
        List<MonthAggregatePlanning> list3 = Arrays.asList(
                new MonthAggregatePlanning(1,1800, 22),
                new MonthAggregatePlanning(2,1500, 19),
                new MonthAggregatePlanning(3,1100, 21),
                new MonthAggregatePlanning(4,900, 21),
                new MonthAggregatePlanning(5,1100, 22),
                new MonthAggregatePlanning(6,1600, 20)
        );

        PlainAggregatePlanning plain2 =
                new PlainAggregatePlanning(10, 1.5, 5, 200, 250, 4.0, 6.0, 400, 5.0, 0.25, 0, 20,53, list3,0);

        plain2 = AggregatePlanning.getLevelForce(plain2);

        for (MonthAggregatePlanning month: plain2.getList()) {
            System.out.println(month);
        }

        //      Planeacion agregada - Fuerza nivelada con horas extras
        System.out.println("-----------------extra hours---------------");
        List<MonthAggregatePlanning> list13 = Arrays.asList(
                new MonthAggregatePlanning(1,1800, 22),
                new MonthAggregatePlanning(2,1500, 19),
                new MonthAggregatePlanning(3,1100, 21),
                new MonthAggregatePlanning(4,900, 21),
                new MonthAggregatePlanning(5,1100, 22),
                new MonthAggregatePlanning(6,1600, 20)
        );

        PlainAggregatePlanning plain12 =
                new PlainAggregatePlanning(10, 1.5, 5, 200, 250, 4.0, 6.0, 400, 5.0, 0.25, 0, 20,53, list13,0);

        plain12 = AggregatePlanning.getLevelForceWithOvertime(plain12);

        for (MonthAggregatePlanning month: plain12.getList()) {
            System.out.println(month);
        }

//      Planeacion agregada - Fuerza nivelada con outsourcing
        System.out.println("----------------outsourcing----------------");
        List<MonthAggregatePlanning> list4 = Arrays.asList(
                new MonthAggregatePlanning(1,1850, 22),
                new MonthAggregatePlanning(2,1425, 19),
                new MonthAggregatePlanning(3,1000, 21),
                new MonthAggregatePlanning(4,850, 21),
                new MonthAggregatePlanning(5,1150, 22),
                new MonthAggregatePlanning(6,1725, 20)
        );

        PlainAggregatePlanning plain3 =
                new PlainAggregatePlanning(10, 1.5, 5, 200, 250, 4.0, 6.0, 400, 5.0, 0.25, 0, 20,53, list4,0);

        plain3 = AggregatePlanning.getLevelForceWithOutsourcing(plain3);

        for (MonthAggregatePlanning month: plain3.getList()) {
            System.out.println(month);
        }

//      Planeacion agregada - fuerza nivelada con outsourcin o horas extras
        System.out.println("---------------outsourcing or extra hours-----------------");
        List<MonthAggregatePlanning> list5 = Arrays.asList(
                new MonthAggregatePlanning(1,1850, 22, true),
                new MonthAggregatePlanning(2,1425, 19, false),
                new MonthAggregatePlanning(3,1000, 21, true),
                new MonthAggregatePlanning(4,850, 21, false),
                new MonthAggregatePlanning(5,1150, 22, true),
                new MonthAggregatePlanning(6,1725, 20, false)
        );

        PlainAggregatePlanning plain4 =
                new PlainAggregatePlanning(10, 1.5, 5, 200, 250, 4.0, 6.0, 400, 5.0, 0.25, 0, 20,53, list5,0);


        plain4 = AggregatePlanning.getLevelForcePerMothType(plain4);

        for (MonthAggregatePlanning month: plain4.getList()) {
            System.out.println(month);
        }
    }
}

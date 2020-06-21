package inv.mgr;

import inv.mgr.utils.inventory.*;
import inv.mgr.utils.productionutils.*;

import java.util.Arrays;
import java.util.List;

public class TestDoc {

    public static void main(String []data) {

        /*

        Para el modelo P existen dos opciones en un mismo instanciador,
        un instanciador es un metodo estatico que tiene de funcion de
        respaldar valores para metodos estaticos.

        Los metodos de todas las clases del paquete inv.mgr.controller son estaticas,
        la razon es porque todos son funciones de utileria, como Math.sqrt (similar)
        donde hay un input y un igual siempre output

        La primera forma es cuando se consta con una probabilidad P
        donde P, recordar que P = Cf / (Cs + Cf)
        tambien recordar que miu, es obligatoria a menos que se pase por
        parametro d y L donde miu = d * L

        Generalemente lo que se requiere del modelo p es el punto del
        inventario en el que se debe ordenar eso es el metodo .ROP()

         */
        // test modelo P
        PModel.staticInstance(0.3, 0.2, null,  10.0, 90.0, null, null);
        System.out.println(PModel.getROP());

        PModel.staticInstance(null, null, 0.95, 25.0, 90.0, 4.0, 15.0);
        System.out.println(PModel.getROP());

        System.out.println("--------------------------------");
        /*

        A diferencia del modelo P, el modelo Q no es estadistico, por
        ende, requiere de muchas mas cosas o indices, como la demanda anual
        y cuestiones sobre el inventario

        Los outputs mostrados a continuacion son los que generalmente se
        valen como necesarios como son
        QOptmial            - cantidad optima de inventario
        NOrderYear          - numero de ordenes en el anio
        TimeBetweenOrder    - tiempo entre ordenes
        ROP                 - el punto donde se debe ordenar (el de advertencia)
        LevelAverage        - tiempo promedio entre QOptimal y ROP
        LevelMax            - el nivel maximo del inventario
        CostInYear          - el coste de este modelo al seguirlo en un anio

         */

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
        /*

        La clase de MRP es una de las mas extensas, esto es debido a
        la cantidad de metodos que posee, aqui se debe entender que existen
        filas auxiliares (RowExplosionAuxiliarTable) y principales
        (RowExplosionMainTable), las cuales representan valga la redundancia
        las filas de la tabla de explosion, o la tabla donde se carga todos los
        datos

        Es importante saber que no todos los metodos necesitan de las filas auxiliares,
        como L4L (Lot Four Lot) y EOQ solo reciben por parametro filas principales

        Los demas metodos necesitan otra clase, la clase
        ExplosionTable { List<RowExplosionAuxiliarTable> , List<RowExplosionMainTable }
        donde la tabla de explosion tiene en si las filas auxiliares y maestras
        recordar que estos metodos son los que requieren de dos tablas, por ende,
        lo de auxiliar y principal

        Algo muy imprtante es aclarar que se deben usar los contructores adecuados
        a la casualidad, pues estan sobre cargados

        cuando se ocupen los metodos como LTC o LUC se necesita la
        ExplosionTable, pero los valores precargados para RowExplosionAuxiliarTable
        son solo para el atributo rang, pues lo demas es generado

        la propiedad isWeek en las tablas principales, es para jugar con el
        frontend, donde explica el tiempo que representa cada fila, o un mes
        o una semana

        el atributo rang de las tablas auxiliares son las semanas o meses en
        orden de prueba
         */

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

        /*

        El tercer modulo es el de planeacion agregada y se encuentra
        en la clase con metodos estaticos AggregatePlanning

        existen 5 metodos en total, los cuales son la estrategia de persecucion
        , la estrategia de fuerza nivelada, fuerza nivelada con horas extras,
        fueza nivelada con outsourcing, y por tipo de mes o PerMonthType el cual
        puede ser por horas extras o outsourcing igualmente en fuerza nivelada

        Las clases a ocupar son dos, el plan de planeacion agregada o
        PlainAggregatePlanning y los meses (filas) llamado MonthAggregatePlanning

        la clase MonthAggregatePlanning tiene contructores sobrecargados
        donde el atributo que cambia es isExtraHour_NoOutsourcing, el cual
        se utiliza solamente para diferenciar si el emes es de horas extra
        o de outsourcing en el PerMonthType

        Todos los valores que se manden como parametros en el contructor de la clase
        AggregatePlanning son los valores base del algoritmo, algunos ejercicios
        omiten algunos de estos, es importante que no omita los importantes
        como por ejemplo el costo de hora extra cuando se utiliza la fuerza agregada
        con horas extras

         */

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

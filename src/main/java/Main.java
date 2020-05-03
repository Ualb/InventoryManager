import controller.MRP;
import controller.PModel;
import controller.QModel;
import controller.RowExplosionMainTable;

import java.util.ArrayList;
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
        List<RowExplosionMainTable> list = new ArrayList<RowExplosionMainTable>();
        list.add(new RowExplosionMainTable(1, 50, 47.0, false));
        list.add(new RowExplosionMainTable(2, 60, 47.0, false));
        list.add(new RowExplosionMainTable(3, 70, 47.0, false));
        list.add(new RowExplosionMainTable(4, 60, 47.0, false));
        list.add(new RowExplosionMainTable(5, 95, 47.0, false));
        list.add(new RowExplosionMainTable(6, 75, 47.0, false));
        list.add(new RowExplosionMainTable(7, 60, 47.0, false));
        list.add(new RowExplosionMainTable(8, 95, 47.0, false));

        list = MRP.getL4L(list);
        for(RowExplosionMainTable row: list) {
            System.out.println(row);
        }

    }
}

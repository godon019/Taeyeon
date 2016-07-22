package godon.Initialize;

import godon.ColumnPackage.Columns;

/**
 * Created by Godon on 2016-07-22.
 */
public class TransferColumns {
    public static void LG_Case(Columns columnsToLoad, Columns columnsToSave){
        //Move loaded columns to to-be-saved columns
        columnsToSave.setColumn("상품명", columnsToLoad.getColumn("상품명").getValues());
        columnsToSave.setColumn("간략한설명", columnsToLoad.getColumn("모델명").getValues());
        columnsToSave.setColumn("상품공급가", columnsToLoad.getColumn("웨딩몰공급가").getValues());
        columnsToSave.setColumn("시중가격", columnsToLoad.getColumn("출하가").getValues());
        columnsToSave.setColumn("모델명", columnsToLoad.getColumn("모델명").getValues());
    }
}

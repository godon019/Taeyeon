package godon.Main;

import godon.Initialize.Directories;
import godon.Initialize.TransferColumns;
import godon.MainFlow.LoadFromExcel;
import godon.MainFlow.SaveToExcel;
import godon.Initialize.ColumnSetter;
import godon.ColumnPackage.Columns;

/**
 * Created by Godon on 2016-07-15.
 */
public class LoadAnalyzeAndSave {

    public void perform(){
        Columns columnsToLoad = new Columns();
        ColumnSetter.setForLoading(columnsToLoad);

        LoadFromExcel loadFromExcel = new LoadFromExcel();
        loadFromExcel.getColumns(columnsToLoad, Directories.samsungTest);

        Columns columnsToSave = new Columns();
        ColumnSetter.setForDeveloperSaving(columnsToSave);

        //Move loaded columns to to-be-saved columns
        TransferColumns.LG_Case(columnsToLoad, columnsToSave);

        ColumnSetter.setLogColumn(columnsToSave, columnsToLoad.getColumn("모델명"));

        SaveToExcel saveToExcel = new SaveToExcel(columnsToSave);
        saveToExcel.saveColumnsTo(Directories.saveDirectory);

    }
}

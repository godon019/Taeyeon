package godon.Main;

import godon.Analyze.AnalyzingWholeProductHtml;
import godon.Environment.Directories;
import godon.MainFlow.LoadFromExcel;
import godon.MainFlow.SaveToExcel;
import godon.ProductColumn.ColumnSetter;
import godon.ProductColumn.Columns;

/**
 * Created by Godon on 2016-07-15.
 */
public class LoadAnalyzeAndSave {

    public void perform(){
        Columns columnsToLoad = new Columns();
        ColumnSetter.setForLoading(columnsToLoad);
        LoadFromExcel loadFromExcel = new LoadFromExcel();
        loadFromExcel.getColumns(columnsToLoad, Directories.loadDirectory);

        Columns columnsToSave = new Columns();
        ColumnSetter.setForSaving(columnsToSave);

        AnalyzingWholeProductHtml analyzingWholeProductHtml = new AnalyzingWholeProductHtml();
        columnsToSave.setColumn("로그", analyzingWholeProductHtml.performAll(columnsToLoad.getColumn("모델명").getValues()));

        SaveToExcel saveToExcel = new SaveToExcel(columnsToSave);
        saveToExcel.saveColumnsTo(Directories.saveDirectory);

    }
}

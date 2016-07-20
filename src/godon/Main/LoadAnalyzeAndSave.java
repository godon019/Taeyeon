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
    Directories directories;
    public LoadAnalyzeAndSave(Directories directories){
        this.directories = directories;
    }

    public void perform(){
        Columns columnsToLoad = new Columns();
        ColumnSetter.setForLoading(columnsToLoad);
        LoadFromExcel LoadFromExcel = new LoadFromExcel(columnsToLoad);
        LoadFromExcel.getColumnsFrom(directories.getLoadDirectory());

        Columns columnsToSave = new Columns();
        ColumnSetter.setForSaving(columnsToSave);

        AnalyzingWholeProductHtml analyzingWholeProductHtml = new AnalyzingWholeProductHtml();
        try {
            columnsToSave.setColumn("로그", analyzingWholeProductHtml.performAll(columnsToLoad.getColumn("모델명").getValues()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        SaveToExcel saveToExcel = new SaveToExcel(columnsToSave);
        saveToExcel.saveColumnsTo(directories.getSaveDirectory());

    }
}

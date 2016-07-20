package godon.Main;

import godon.Analyze.AnalyzingWholeProductHtml;
import godon.Environment.Directories;
import godon.MainFlow.LoadFromExcel;
import godon.MainFlow.SaveToExcel;
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
        LoadFromExcel LoadFromExcel = new LoadFromExcel();
        Columns columns = LoadFromExcel.getProducts(directories.getLoadDirectory());



        AnalyzingWholeProductHtml analyzingWholeProductHtml = new AnalyzingWholeProductHtml();
        try {
            columns.setProductArr("로그", analyzingWholeProductHtml.performAll(columns.getProductArr("모델명").getValues()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        SaveToExcel saveToExcel = new SaveToExcel();
        saveToExcel.columns = columns;
        saveToExcel.saveProducts(directories.getSaveDirectory());

    }
}

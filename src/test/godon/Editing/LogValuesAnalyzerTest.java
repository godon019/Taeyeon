package godon.Editing;

import godon.Analyze.AnalyzingWholeProductHtml;
import godon.Environment.Directories;
import godon.MainFlow.LoadFromExcel;
import godon.MainFlow.SaveToExcel;
import godon.ProductColumn.Column;
import godon.ProductColumn.ColumnSetter;
import godon.ProductColumn.Columns;
import org.junit.Test;

/**
 * Created by Godon on 2016-07-21.
 */
public class LogValuesAnalyzerTest {
    @Test
    public void analyze() throws Exception {

        Columns columnsToLoad = new Columns();
        ColumnSetter.setForLoading(columnsToLoad);
        LoadFromExcel loadFromExcel = new LoadFromExcel();
        loadFromExcel.getColumns(columnsToLoad, Directories.allCaseExcel);

        Columns columnsToSave = new Columns();
        ColumnSetter.setForSaving(columnsToSave);

        AnalyzingWholeProductHtml analyzingWholeProductHtml = new AnalyzingWholeProductHtml();
        columnsToSave.setColumn("로그", analyzingWholeProductHtml.performAll(columnsToLoad.getColumn("모델명").getValues()));

        //DoTest
        Column lowPriceColumn = new Column("최저가 분석된거");
        LogValuesAnalyzer logValuesAnalyzer = new LogValuesAnalyzer(columnsToSave.getColumn("로그").getValues());
        lowPriceColumn.setValues(logValuesAnalyzer.getAnalyzedValues("최저가"));

        columnsToSave.addColumn(lowPriceColumn);

        SaveToExcel saveToExcel = new SaveToExcel(columnsToSave);
        saveToExcel.saveColumnsTo(Directories.logAnalyzeTest);


    }

}
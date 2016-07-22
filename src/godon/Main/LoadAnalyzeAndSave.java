package godon.Main;

import godon.Analyze.AnalyzingWholeProductHtml;
import godon.Analyze.Log.LogValuesAnalyzer;
import godon.Analyze.LogData;
import godon.Environment.Directories;
import godon.MainFlow.LoadFromExcel;
import godon.MainFlow.SaveToExcel;
import godon.ProductColumn.Column;
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

        columnsToSave.setColumn("상품명", columnsToLoad.getColumn("상품명").getValues());
        columnsToSave.setColumn("간략한설명", columnsToLoad.getColumn("모델명").getValues());
        columnsToSave.setColumn("상품공급가", columnsToLoad.getColumn("웨딩몰공급가").getValues());
        columnsToSave.setColumn("시중가격", columnsToLoad.getColumn("출하가").getValues());
        columnsToSave.setColumn("로그", analyzingWholeProductHtml.performAll(columnsToLoad.getColumn("모델명").getValues()));
        columnsToSave.setColumn("모델명", columnsToLoad.getColumn("모델명").getValues());

        //DoTest
        LogValuesAnalyzer logValuesAnalyzer = new LogValuesAnalyzer(columnsToSave.getColumn("로그").getValues());
        Column lowPriceColumn = new Column("최저가 분석된거");
        lowPriceColumn.setValues(logValuesAnalyzer.getAnalyzedValues(LogData.LAST_PRICE));
        columnsToSave.addColumn(lowPriceColumn);

        Column titleColumn = new Column("제목 분석된거");
        titleColumn.setValues(logValuesAnalyzer.getAnalyzedValues(LogData.PRODUCT_TITLE));
        columnsToSave.addColumn(titleColumn);

        Column categoryColumn = new Column("카테고리 분석된거");
        categoryColumn.setValues(logValuesAnalyzer.getAnalyzedValues(LogData.CATEGORY));
        columnsToSave.addColumn(categoryColumn);

        Column linkColumn = new Column("링크 분석된거");
        linkColumn.setValues(logValuesAnalyzer.getAnalyzedValues(LogData.LINK));
        columnsToSave.addColumn(linkColumn);

        Column firstCaseColumn = new Column("케이스1 분석된거");
        firstCaseColumn.setValues(logValuesAnalyzer.getAnalyzedValues(LogData.FIRST_PAGE_CASE));
        columnsToSave.addColumn(firstCaseColumn);

        Column secondCaseColumn = new Column("케이스2 분석된거");
        secondCaseColumn.setValues(logValuesAnalyzer.getAnalyzedValues(LogData.SECOND_PAGE_CASE));
        columnsToSave.addColumn(secondCaseColumn);


        SaveToExcel saveToExcel = new SaveToExcel(columnsToSave);
        saveToExcel.saveColumnsTo(Directories.saveDirectory);

    }
}

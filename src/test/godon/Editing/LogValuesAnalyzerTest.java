package godon.Editing;

import godon.Analyze.AnalyzingWholeProductHtml;
import godon.Analyze.Log.LogValuesAnalyzer;
import godon.Analyze.LogData;
import godon.Initialize.Directories;
import godon.MainFlow.LoadFromExcel;
import godon.MainFlow.SaveToExcel;
import godon.ColumnPackage.Column;
import godon.Initialize.ColumnSetter;
import godon.ColumnPackage.Columns;
import org.junit.Test;

/**
 * Created by Godon on 2016-07-21.
 */
public class LogValuesAnalyzerTest {
    @Test
    public void analyze() throws Exception {

        Columns columnsToLoad = new Columns();
        ColumnSetter.setForLoading(columnsToLoad);
        //samsung, 삼성꺼에는 최저가가 없으니까 지우고 테스트함
        columnsToLoad.removeColumn("최저가");

        LoadFromExcel loadFromExcel = new LoadFromExcel();
        //samsung
        loadFromExcel.getColumns(columnsToLoad, Directories.samsungTest);

        Columns columnsToSave = new Columns();
        ColumnSetter.setForSaving(columnsToSave);

        AnalyzingWholeProductHtml analyzingWholeProductHtml = new AnalyzingWholeProductHtml();
        columnsToSave.setColumn("로그", analyzingWholeProductHtml.performAll(columnsToLoad.getColumn("모델명").getValues(), columnsToLoad.getColumn("상품명").getValues()));
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
        saveToExcel.saveColumnsTo(Directories.logAnalyzeTest);


    }

}
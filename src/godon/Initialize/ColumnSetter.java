package godon.Initialize;

import godon.Analyze.AnalyzingWholeProductHtml;
import godon.Analyze.Log.LogValuesAnalyzer;
import godon.Analyze.LogData;
import godon.ColumnPackage.Column;
import godon.ColumnPackage.Columns;
import godon.ColumnPackage.ComparableColumn;

/**
 * Created by Godon on 2016-07-20.
 */
public class ColumnSetter {

    static public void setForLoading(Columns columns){
        columns.addColumn(new Column("상품명")); // ->상품명
        columns.addColumn(new ComparableColumn("모델명",ComparableColumn.ValueType.STRING));   //->간략한설명
        columns.addColumn(new ComparableColumn("웨딩몰공급가", ComparableColumn.ValueType.NUMBER));   //->상품공급가
        columns.addColumn(new ComparableColumn("출하가", ComparableColumn.ValueType.NUMBER));  //->시중가격
        columns.setPrimeProduct("모델명");
    }

    static public void setForSaving(Columns columns){
        columns.addColumn(new Column("상품명"));
        columns.addColumn(new Column("큰이미지"));
        columns.addColumn(new Column("중간이미지"));
        columns.addColumn(new Column("작은이미지"));
        columns.addColumn(new Column("재고"));
        columns.addColumn(new ComparableColumn("상품공급가", ComparableColumn.ValueType.NUMBER));
        columns.addColumn(new ComparableColumn("상품판매가", ComparableColumn.ValueType.NUMBER));
        columns.addColumn(new Column("수수료율"));
        columns.addColumn(new Column("상품내용"));
        columns.addColumn(new Column("간략한설명"));
        columns.addColumn(new Column("시중가격"));
        columns.addColumn(new Column("적립금"));
        columns.addColumn(new Column("브랜드"));
        columns.addColumn(new Column("제조사"));
        columns.addColumn(new Column("원산지"));
        columns.addColumn(new Column("배송비구분"));
        columns.addColumn(new Column("배송비"));
        columns.addColumn(new Column("배송비무료금액"));
        columns.addColumn(new Column("배송정책"));
        columns.addColumn(new Column("카테고리"));
        columns.addColumn(new Column("옵션타입"));
        columns.addColumn(new Column("옵션"));
        columns.addColumn(new ComparableColumn("모델명",ComparableColumn.ValueType.STRING));
    }

    static public void setForDeveloperSaving(Columns columns){
        columns.addColumn(new Column("간략한설명"));
        columns.addColumn(new Column("상품명"));
        columns.addColumn(new ComparableColumn("모델명",ComparableColumn.ValueType.STRING));
        columns.addColumn(new ComparableColumn("상품공급가", ComparableColumn.ValueType.NUMBER));
        columns.addColumn(new ComparableColumn("상품판매가", ComparableColumn.ValueType.NUMBER));
        columns.addColumn(new Column("시중가격"));

    }

    static public void setForSamSungLoading(Columns columns){
        columns.addColumn(new ComparableColumn("모델명",ComparableColumn.ValueType.STRING));
        columns.setPrimeProduct("모델명");
    }

    static public void setLogColumn(Columns columnsToSave, Column productModelNameColumn){

        //getLogColumn and add to column to save
        Column logColumn = new Column("로그");
        AnalyzingWholeProductHtml analyzingWholeProductHtml = new AnalyzingWholeProductHtml();
        logColumn.setValues(analyzingWholeProductHtml.performAll(productModelNameColumn.getValues()));
        columnsToSave.addColumn(logColumn);

        //get analyzed columns from log
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
    }
}

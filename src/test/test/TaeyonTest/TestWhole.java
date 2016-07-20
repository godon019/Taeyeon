package test.TaeyonTest;

import godon.Environment.Directories;
import godon.Main.LoadAnalyzeAndSave;
import godon.MainFlow.LoadFromExcel;
import godon.MainTest.CompareTest;
import godon.ProductColumn.Column;
import godon.ProductColumn.ColumnSetter;
import godon.ProductColumn.Columns;
import godon.ProductColumn.ComparableColumn;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by Godon on 2016-07-18.
 */
public class TestWhole {
    @Test
    public void whole(){

        //Get Test directory and set columns
        Directories directories = new Directories(null, null);
        directories.setLoadDirectory("E:\\LIBRARY\\Desktop\\작업\\2016-06-28 태연이 프로그램\\테스트2 - 복사본.xlsx");

        Columns columnsToLoad1 = new Columns();
        ColumnSetter.setForLoading(columnsToLoad1);
        LoadFromExcel LoadFromExcel = new LoadFromExcel(columnsToLoad1);
        LoadFromExcel.getColumnsFrom(directories.getLoadDirectory());

        //Make real excel
        directories.setLoadDirectory("E:\\LIBRARY\\Desktop\\작업\\2016-06-28 태연이 프로그램\\20160630_LG단가표(웨딩,복지몰)_(주)디에이블커머스.xlsx");
        directories.setSaveDirectory("E:\\LIBRARY\\Desktop\\작업\\2016-06-28 태연이 프로그램\\테스트2.xlsx");
        LoadAnalyzeAndSave loadAnalyzeAndSave = new LoadAnalyzeAndSave(directories);
        loadAnalyzeAndSave.perform();


        //Get Real directory and set columns
        directories.setLoadDirectory("E:\\LIBRARY\\Desktop\\작업\\2016-06-28 태연이 프로그램\\테스트2.xlsx");
        Columns columnsToLoad2 = new Columns();
        ColumnSetter.setForLoading(columnsToLoad2);
        LoadFromExcel.getColumnsFrom(directories.getLoadDirectory());


        //Test
        Column column1 = null;
        Column column2 = null;
        String nameToGet = "최저가";
        try {
            column1 = columnsToLoad1.getColumn(nameToGet);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            column2 = columnsToLoad2.getColumn(nameToGet);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertNotNull(column1);
        assertNotNull(column2);
        CompareTest compareTest = new CompareTest();
        assertTrue(compareTest.areTheySame((ComparableColumn) column1, (ComparableColumn) column2));
    }
}

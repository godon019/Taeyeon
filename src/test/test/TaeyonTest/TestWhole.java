package test.TaeyonTest;

import godon.Initialize.Directories;
import godon.Main.LoadAnalyzeAndSave;
import godon.MainFlow.LoadFromExcel;
import godon.MainTest.CompareTest;
import godon.ColumnPackage.Column;
import godon.Initialize.ColumnSetter;
import godon.ColumnPackage.Columns;
import godon.ColumnPackage.ComparableColumn;
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
        Columns columnsToLoad1 = new Columns();
        ColumnSetter.setForLoading(columnsToLoad1);
        LoadFromExcel loadFromExcel = new LoadFromExcel();
        loadFromExcel.getColumns(columnsToLoad1, Directories.loadTestExcel);

        //Make real excel
        LoadAnalyzeAndSave loadAnalyzeAndSave = new LoadAnalyzeAndSave();
        loadAnalyzeAndSave.perform();


        //Get Real directory and set columns
        Columns columnsToLoad2 = new Columns();
        ColumnSetter.setForLoading(columnsToLoad2);
        loadFromExcel.getColumns(columnsToLoad2, Directories.loadDirectory);


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

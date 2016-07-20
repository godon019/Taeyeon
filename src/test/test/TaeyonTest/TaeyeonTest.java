package test.TaeyonTest;

import godon.Environment.Directories;
import godon.MainFlow.LoadFromExcel;
import godon.MainFlow.SaveToExcel;
import godon.MainTest.CompareTest;
import godon.ProductColumn.Column;
import godon.ProductColumn.ColumnSetter;
import godon.ProductColumn.Columns;
import godon.ProductColumn.ComparableColumn;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Created by Godon on 2016-06-28.
 */
public class TaeyeonTest {


    @Test
    public void productEqualsTestForStringType(){
        //Given
        Column column = new ComparableColumn("okay", ComparableColumn.ValueType.STRING);
        Column column2 = new ComparableColumn("okay", ComparableColumn.ValueType.STRING);
        ArrayList<String> arr = new ArrayList<String>();
        ArrayList<String> arr2 = new ArrayList<String>();

        //when
        for(int i=0; i<20; i++){
            arr.add("test" + i);
            arr2.add("test" + i);
        }
        column.setValues(arr);
        column2.setValues(arr2);
        CompareTest compareTest = new CompareTest();


        //then
        assertTrue(compareTest.areTheySame((ComparableColumn) column, (ComparableColumn) column2));


        //when
        column.setName("hehe");
        //then
        assertFalse(compareTest.areTheySame((ComparableColumn) column, (ComparableColumn) column2));

        //when
        column.setName("okay");
        //then
        assertTrue(compareTest.areTheySame((ComparableColumn) column, (ComparableColumn) column2));

        //when
        arr2.add("gg");
        //then
        assertFalse(compareTest.areTheySame((ComparableColumn) column, (ComparableColumn) column2));

        //when
        arr.add("gg");
        //then
        assertTrue(compareTest.areTheySame((ComparableColumn) column, (ComparableColumn) column2));

    }

    @Test
    public void productEqualsTestForNumberType(){
        //Given
        Column column = new ComparableColumn("okay", ComparableColumn.ValueType.NUMBER);
        Column column2 = new ComparableColumn("okay", ComparableColumn.ValueType.NUMBER);
        ArrayList<String> arr = new ArrayList<String>();
        ArrayList<String> arr2 = new ArrayList<String>();

        //when
        for(int i=0; i<20; i++){
            arr.add(String.valueOf(i*1000));
            arr2.add(String.valueOf(i*1000));
        }
        column.setValues(arr);
        column2.setValues(arr2);
        CompareTest compareTest = new CompareTest();


        //then
        assertTrue(compareTest.areTheySame((ComparableColumn) column, (ComparableColumn) column2));


        //when
        column.setName("hehe");
        //then
        assertFalse(compareTest.areTheySame((ComparableColumn) column, (ComparableColumn) column2));

        //when
        column.setName("okay");
        //then
        assertTrue(compareTest.areTheySame((ComparableColumn) column, (ComparableColumn) column2));

        //when
        arr2.add("100000");
        //then
        assertFalse(compareTest.areTheySame((ComparableColumn) column, (ComparableColumn) column2));

        //when
        arr.add("100000");
        //then
        assertTrue(compareTest.areTheySame((ComparableColumn) column, (ComparableColumn) column2));

        arr.add("100000");
        arr2.add("150000");
        assertFalse(compareTest.areTheySame((ComparableColumn) column, (ComparableColumn) column2));

        arr.add("100000");
        arr2.add("100000");
        assertFalse(compareTest.areTheySame((ComparableColumn) column, (ComparableColumn) column2));

    }



    @Test
    public void productEqualsTest2(){

        //Get Test directory and set columns
        Directories directories = new Directories(null, null);
        directories.setLoadDirectory("E:\\LIBRARY\\Desktop\\작업\\2016-06-28 태연이 프로그램\\테스트2 - 복사본.xlsx");
        Columns columnsToLoad1 = new Columns();
        ColumnSetter.setForLoading(columnsToLoad1);
        LoadFromExcel LoadFromExcel = new LoadFromExcel(columnsToLoad1);
        LoadFromExcel.getColumnsFrom(directories.getLoadDirectory());

        //Get Real directory and set columns
        directories.setLoadDirectory("E:\\LIBRARY\\Desktop\\작업\\2016-06-28 태연이 프로그램\\테스트2.xlsx");
        Columns columnsToLoad2 = new Columns();
        ColumnSetter.setForLoading(columnsToLoad2);
        LoadFromExcel = new LoadFromExcel(columnsToLoad2);
        LoadFromExcel.getColumnsFrom(directories.getLoadDirectory());


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

        Columns debugColumns = new Columns();
        debugColumns.addProduct(column1);
        debugColumns.addProduct(column2);
        debugColumns.addProduct(compareTest.getComparedDebugProduct());


        directories.setSaveDirectory("E:\\LIBRARY\\Desktop\\작업\\2016-06-28 태연이 프로그램\\테스트2 - compare.xlsx");
        SaveToExcel saveToExcel = new SaveToExcel(debugColumns);
        saveToExcel.saveColumnsTo(directories.getSaveDirectory());

    }


    @Test
    public void productEqualsTest3(){

        //Get Test directory and set columns
        Directories directories = new Directories(null, null);

        directories.setLoadDirectory("E:\\LIBRARY\\Desktop\\작업\\2016-06-28 태연이 프로그램\\테스트2 - 복사본.xlsx");
        Columns columnsToLoad1 = new Columns();
        ColumnSetter.setForLoading(columnsToLoad1);
        LoadFromExcel LoadFromExcel = new LoadFromExcel(columnsToLoad1);
        LoadFromExcel.getColumnsFrom(directories.getLoadDirectory());

        //Get Real directory and set columns
        directories.setLoadDirectory("E:\\LIBRARY\\Desktop\\작업\\2016-06-28 태연이 프로그램\\테스트2 - 복사본.xlsx");
        Columns columnsToLoad2 = new Columns();
        ColumnSetter.setForLoading(columnsToLoad2);
        LoadFromExcel = new LoadFromExcel(columnsToLoad2);
        LoadFromExcel.getColumnsFrom(directories.getLoadDirectory());


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

        //When : it should be different with this number
        column1.getValues().set(0, "500");
        column2.getValues().set(0, "100");

        for(int i=0; i<5; i+=1){
            System.out.println("값 비교 전 테스트  : "+ column1.getValues().get(i)+ ", "+column2.getValues().get(i));
        }

        CompareTest compareTest = new CompareTest();
        assertFalse(compareTest.areTheySame((ComparableColumn) column1, (ComparableColumn) column2));

        Columns debugColumns = new Columns();
        debugColumns.addProduct(column1);
        debugColumns.addProduct(column2);
        debugColumns.addProduct(compareTest.getComparedDebugProduct());


        directories.setSaveDirectory("E:\\LIBRARY\\Desktop\\작업\\2016-06-28 태연이 프로그램\\테스트2 - compare.xlsx");
        SaveToExcel saveToExcel = new SaveToExcel(debugColumns);
        saveToExcel.saveColumnsTo(directories.getSaveDirectory());

    }
}
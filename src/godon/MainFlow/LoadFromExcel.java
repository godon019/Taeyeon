package godon.MainFlow;

import godon.ProductColumn.Column;
import godon.ProductColumn.Columns;
import godon.ProductColumn.ComparableColumn;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Godon on 2016-07-04.
 */
public class LoadFromExcel {

    private final int CONTENTS_START_WITHOUT_COLUMN_TITLE = 1;
    private Workbook wb;
    private Sheet sheet;
    private Columns columns;

    public Columns getProducts(String directory) {
        columns = new Columns();
        columns.addProduct(new Column("일련번호", Column.IOType.WRITE_ONLY));
        columns.addProduct(new ComparableColumn("모델명", Column.IOType.READ_AND_WRITE, ComparableColumn.ValueType.STRING));
        columns.addProduct(new ComparableColumn("최저가", Column.IOType.READ_AND_WRITE, ComparableColumn.ValueType.NUMBER));
        columns.addProduct(new ComparableColumn("갱신된 최저가", Column.IOType.WRITE_ONLY, ComparableColumn.ValueType.NUMBER));
        columns.addProduct(new Column("로그", Column.IOType.WRITE_ONLY));
        columns.setPrimitiveProduct("모델명");

        try {
            openFile(directory);
            //New
            setProductArr();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return columns;
    }


    private void openFile(String directory) throws Exception{
        InputStream inp = new FileInputStream(directory);
        wb = WorkbookFactory.create(inp);
    }

    private void setProductArr() throws Exception{
        for(Column column : columns.getProductArr()){
            if(column.isReadable()) {
                column.setValues(getListOfSpecificColumnContents(column.getName()));
            }
        }

        columns.initializeNullValuesOfProductToProperValuesWithPrimeProduct();
    }

    private ArrayList<String> getListOfSpecificColumnContents(String columnName) throws Exception{

        int specificColumnNumber = getSpecificColumnNumber(columnName);
        return getContentsOfColumn(specificColumnNumber);
    }

    private int getSpecificColumnNumber(String keyword) throws Exception{

        sheet = wb.getSheetAt(0);

        Row row = sheet.getRow(0);

        for (Cell cell : row) {
            System.out.println("row 0 " + cell.toString() );
            if(cell.toString().equals(keyword)) {
                return cell.getAddress().getColumn();
            }
        }

        throw new Exception("키워드 : " + keyword + "를 찾지 못했습니다");
    }

    private ArrayList<String> getContentsOfColumn(int columnNumber){

        ArrayList<String> arrayList =  new ArrayList<String>();

        int rowStart = CONTENTS_START_WITHOUT_COLUMN_TITLE;
        int rowEnd = sheet.getLastRowNum();

        Cell cell = null;
        for(int i = rowStart; i <= rowEnd; i++) {
            cell = sheet.getRow(i).getCell(columnNumber);
            System.out.println("value of cell " + cell);
            arrayList.add(cell.toString());
        }

        return arrayList;
    }






}

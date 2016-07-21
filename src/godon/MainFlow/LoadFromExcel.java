package godon.MainFlow;

import godon.ProductColumn.Column;
import godon.ProductColumn.Columns;
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

    public LoadFromExcel(){
    }


    public void getColumns(Columns columns, String directory) {
        this.columns = columns;
        try {
            openFile(directory);
            //New
            setColumnArr();

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }


    private void openFile(String directory) throws Exception{
        System.out.println("불러올 경로 : "+ directory);
        InputStream inp = new FileInputStream(directory);
        wb = WorkbookFactory.create(inp);
    }

    private void setColumnArr() throws Exception{
        for(Column column : columns.getProductArr()){
            column.setValues(getListOfSpecificColumnContents(column.getName()));
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
            arrayList.add(cell.toString());
        }

        return arrayList;
    }






}

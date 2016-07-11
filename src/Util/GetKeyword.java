package Util;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by Godon on 2016-06-29.
 */
public class GetKeyword {
    void getKeyword() throws Exception{
        InputStream inp = new FileInputStream("E:\\LIBRARY\\Desktop\\작업\\2016-06-28 태연이 프로그램\\20160620_웨딩몰_LG_(주)디에이블커머스.xlsx");
        //InputStream inp = new FileInputStream("workbook.xlsx");

        Workbook wb = WorkbookFactory.create(inp);
        Sheet sheet = wb.getSheetAt(0);
        Row row = sheet.getRow(2);
        Cell cell = row.getCell(3);
        if (cell == null)
            cell = row.createCell(3);
        cell.setCellType(Cell.CELL_TYPE_STRING);
        cell.setCellValue("a test");

        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream("E:\\LIBRARY\\Desktop\\작업\\2016-06-28 태연이 프로그램\\workbook.xlsx");
        wb.write(fileOut);
        fileOut.close();

    }
}

package godon.MainFlow;

import godon.Product.Product;
import godon.Product.Products;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * Created by Godon on 2016-07-06.
 */
public class SaveToExcel {
    Workbook wb;
    Sheet sheet;
    Row row;
    Cell cell;

    public Products products;

    public void saveProducts(String directory){
        //Workbook wb = new HSSFWorkbook();
        try {
            wb = new XSSFWorkbook();

            sheet = wb.createSheet("new sheet");

            //New
            writeExcelWithProducts();

            // Write the output to a file
            FileOutputStream fileOut = new FileOutputStream(directory);
            wb.write(fileOut);
            fileOut.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    void writeExcelWithProducts()throws Exception{
        int columnPosition = 0;
        for(Product product : products.getProductArr()){
            if(product.isWritable()){
                setColumnWithArray(columnPosition, product.getName(), product.getValues());
                columnPosition++;
            }
        }
    }

    void setColumnWithArray(int number, String title, ArrayList<String> arr){

        System.out.println("number = " + number);

        if(row != null) {
            row = sheet.getRow(0);
        }
        else{
            row =  sheet.createRow(0);
        }

        cell = row.createCell(number);
        cell.setCellValue(title);

        if(arr != null) {
            for (int i = 0; i < arr.size(); i++) {
                row = sheet.getRow(i + 1);
                if (row == null) {
                    row = sheet.createRow(i + 1);
                }
                cell = row.createCell(number);
                cell.setCellValue(arr.get(i));
            }
        }
    }


}

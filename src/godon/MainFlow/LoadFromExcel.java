package godon.MainFlow;

import godon.Product.ComparableProduct;
import godon.Product.Product;
import godon.Product.Products;
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
    private Products products;

    public Products getProducts(String directory) {
        products = new Products();
        products.addProduct(new Product("일련번호", Product.IOType.WRITE_ONLY));
        products.addProduct(new ComparableProduct("모델명", Product.IOType.READ_AND_WRITE, ComparableProduct.ValueType.STRING));
        products.addProduct(new ComparableProduct("최저가", Product.IOType.READ_AND_WRITE, ComparableProduct.ValueType.NUMBER));
        products.addProduct(new ComparableProduct("갱신된 최저가", Product.IOType.WRITE_ONLY, ComparableProduct.ValueType.NUMBER));
        products.addProduct(new Product("로그", Product.IOType.WRITE_ONLY));
        products.setPrimitiveProduct("모델명");

        try {
            openFile(directory);
            //New
            setProductArr();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return products;
    }


    private void openFile(String directory) throws Exception{
        InputStream inp = new FileInputStream(directory);
        wb = WorkbookFactory.create(inp);
    }

    private void setProductArr() throws Exception{
        for(Product product : products.getProductArr()){
            if(product.isReadable()) {
                product.setValues(getListOfSpecificColumnContents(product.getName()));
            }
        }

        products.initializeNullValuesOfProductToProperValuesWithPrimeProduct();
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

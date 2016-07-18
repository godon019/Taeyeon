package test.TaeyonTest;

import godon.Environment.Directories;
import godon.MainFlow.LoadFromExcel;
import godon.Product.Product;
import godon.Product.Products;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.testng.Assert.assertEquals;

/**
 * Created by Godon on 2016-06-28.
 */
public class TaeyeonTest {

    @Test
    public void sss(){
        assertEquals(3, 3);
    }

    @Test
    public void productEqualsTest(){
        //Given
        Product product = new Product("okay", Product.IOType.READ_AND_WRITE);
        Product product2 = new Product("okay", Product.IOType.READ_AND_WRITE);
        ArrayList<String> arr = new ArrayList<String>();
        ArrayList<String> arr2 = new ArrayList<String>();

        //when
        for(int i=0; i<20; i++){
            arr.add("test" + i);
            arr2.add("test" + i);
        }
        product.setValues(arr);
        product2.setValues(arr2);
        //then
        assertTrue(product.equals(product2));

        //when
        product.setName("hehe");
        //then
        assertFalse(product.equals(product2));

        //when
        product.setName("okay");
        //then
        assertTrue(product.equals(product2));

        //when
        arr2.add("gg");
        //then
        assertFalse(product.equals(product2));

        //when
        arr.add("gg");
        //then
        assertTrue(product.equals(product2));

    }

    @Test
    public void productEqualsTest2(){

        //Get Test directory and set products
        Directories directories = new Directories(null, null);
        directories.setLoadDirectory("E:\\LIBRARY\\Desktop\\작업\\2016-06-28 태연이 프로그램\\테스트2 - 복사본.xlsx");
        LoadFromExcel LoadFromExcel = new LoadFromExcel();
        Products products = LoadFromExcel.getProducts(directories.getLoadDirectory());

        //Get Real directory and set products
        directories.setLoadDirectory("E:\\LIBRARY\\Desktop\\작업\\2016-06-28 태연이 프로그램\\테스트2.xlsx");
        Products products2 = LoadFromExcel.getProducts(directories.getLoadDirectory());


        Product product1 = null;
        Product product2 = null;
        String nameToGet = "최저가";
        try {
            product1 = products.getProductArr(nameToGet);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            product2 = products.getProductArr(nameToGet);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertNotNull(product1);
        assertNotNull(product2);
        assertTrue(product1.equals(product2));



    }

}
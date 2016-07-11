package test.TaeyonTest;

import godon.MainFlow.AnalyzeHtml;
import godon.MainFlow.LoadFromExcel;
import godon.Product.Products;
import godon.MainFlow.SaveToExcel;
import org.junit.Test;

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
    public void testNewProduct(){
        Products products = new Products();

        LoadFromExcel LoadFromExcel = new LoadFromExcel();
        try {
            products = LoadFromExcel.perform("E:\\LIBRARY\\Desktop\\작업\\2016-06-28 태연이 프로그램\\20160630_LG단가표(웨딩,복지몰)_(주)디에이블커머스.xlsx");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            products.setProductArr("갱신된 최저가", AnalyzeHtml.getLeastPriceOfProdut( products.getProductArr("모델명").getValue() ) );
        } catch (Exception e) {
            e.printStackTrace();
        }


        SaveToExcel saveToExcel = new SaveToExcel();
        try {
            saveToExcel.products = products;
            saveToExcel.perform("E:\\LIBRARY\\Desktop\\작업\\2016-06-28 태연이 프로그램\\테스트2.xlsx");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
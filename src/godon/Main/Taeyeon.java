package godon.Main;

import godon.MainFlow.AnalyzeHtml;
import godon.Environment.Directories;
import godon.Environment.Frame;
import godon.MainFlow.LoadFromExcel;
import godon.Product.Products;
import godon.MainFlow.SaveToExcel;

/**
 * Created by Godon on 2016-06-28.
 */
public class Taeyeon{

    public static void main(String[] args){
        Directories directories = new Directories(null, null);
        directories.setLoadDirectory("E:\\LIBRARY\\Desktop\\작업\\2016-06-28 태연이 프로그램\\20160630_LG단가표(웨딩,복지몰)_(주)디에이블커머스 - 짧은거.xlsx");
        directories.setSaveDirectory("E:\\LIBRARY\\Desktop\\작업\\2016-06-28 태연이 프로그램\\테스트2.xlsx");

        Frame frame = new Frame(directories);

        while(frame.okayPushed == false){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(String.format("real x = %d, y = %d", 3, 4));

        Products products = new Products();

        LoadFromExcel LoadFromExcel = new LoadFromExcel();
        try {
            products = LoadFromExcel.perform(directories.getLoadDirectory());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            products = AnalyzeHtml.getAnalyzedProducts(products);
            //products.setProductArr("갱신된 최저가", AnalyzeHtml.getLeastPriceOfProdut( products.getProductArr("모델명").getValue() ) );
        } catch (Exception e) {
            e.printStackTrace();
        }


        SaveToExcel saveToExcel = new SaveToExcel();
        try {
            saveToExcel.products = products;
            saveToExcel.perform(directories.getSaveDirectory());
        } catch (Exception e) {
            e.printStackTrace();
        }




    }
}


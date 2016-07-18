package godon.Main;

import godon.Analyze.AnalyzingWholeProductHtml;
import godon.Environment.Directories;
import godon.MainFlow.LoadFromExcel;
import godon.MainFlow.SaveToExcel;
import godon.Product.Products;

/**
 * Created by Godon on 2016-07-15.
 */
public class LoadAnalyzeAndSave {
    Directories directories;
    public LoadAnalyzeAndSave(Directories directories){
        this.directories = directories;
    }

    public void perform(){
        LoadFromExcel LoadFromExcel = new LoadFromExcel();
        Products products = LoadFromExcel.getProducts(directories.getLoadDirectory());

        AnalyzingWholeProductHtml analyzingWholeProductHtml = new AnalyzingWholeProductHtml();
        try {
            products.setProductArr("로그", analyzingWholeProductHtml.performAll(products.getProductArr("모델명").getValues()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        SaveToExcel saveToExcel = new SaveToExcel();
        saveToExcel.products = products;
        saveToExcel.saveProducts(directories.getSaveDirectory());

    }
}

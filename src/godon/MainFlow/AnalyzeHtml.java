package godon.MainFlow;

import godon.Product.Product;
import godon.Product.Products;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

/**
 * Created by Godon on 2016-06-30.
 */
public class AnalyzeHtml {

    static Document doc;
    static Elements elements;
    static Element element;

    static String href;
    static String product_id ;
    static String product_imgsignature;
    static String nclickavalue;

    static String firstPriceHtmlText;

    static String lastPrice;

    static ArrayList<String> priceArr;
    static ArrayList<String> linkArr;

    public static Products getAnalyzedProducts(Products products) throws Exception{
        Product product = products.getProductArr("모델명");
        ArrayList<String> productValue = product.getValue();
        ArrayList<String> leastPriceProductValue = new ArrayList<String>();
        ArrayList<String> linkArrForProduct = new ArrayList<String>();
        ArrayList<String> priceArrForProduct = new ArrayList<String>();

        for(int i = 0; i < productValue.size(); i+=1) {
            try {
                getLowPrice(productValue.get(i));
                lastPrice = trimNumber(lastPrice);
                leastPriceProductValue.add(lastPrice);
                linkArrForProduct.add(getArrAsString(linkArr));
                priceArrForProduct.add(getArrAsString(priceArr));
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }

        products.setProductArr("갱신된 최저가", leastPriceProductValue);
        products.setProductArr("링크 배열", linkArrForProduct);
        products.setProductArr("가격 배열", priceArrForProduct);

        return products;
    }

    static String getArrAsString(ArrayList<String> arr){
        return arr.toString();
    }

    public static ArrayList<String> getLeastPriceOfProdut(ArrayList<String> productToSearch){
        ArrayList<String> lowPriceListToReturn = new ArrayList<String>();
        for(int i = 0; i < productToSearch.size(); i+=1) {
            try {
                getLowPrice(productToSearch.get(i));
                lastPrice = trimNumber(lastPrice);
                lowPriceListToReturn.add(lastPrice);
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
        return lowPriceListToReturn;
    }



    public static void setPrice(String price)throws Exception{
        lastPrice = price;
        priceArr.add(price);
    }


    static void getLowPrice(String product_name)throws Exception{
        System.out.println("검색할 이름 " + product_name);

        initializeStringsToUse();

        if(getDocFromNaverShopping(trimName(product_name))) {
            getPriceFromDoc("price");
            analyzeIfItHasComparePage();
        }

        System.out.println("최종가격 : "+ lastPrice);
    }

    static void initializeStringsToUse(){
        doc = null;
        elements = null;
        element = null;

        href = null;
        product_id  = null;
        product_imgsignature = null;
        nclickavalue = null;

        firstPriceHtmlText = null;
        lastPrice = null;

        priceArr = new ArrayList<String>();
        linkArr = new ArrayList<String>();
    }

    static String trimName(String name){
        if(!isProductNameOnly(name)) {
            return leaveProductNameOnly(name);
        }
        else
            return name;
    }

    static boolean isProductNameOnly(String name){
        if( name.contains(".")){
            System.out.println(name + " contains . ");
            return false;
        }
        else{
            return true;
        }
    }

    static String leaveProductNameOnly(String name){
        String[] names = name.split("\\.");
        System.out.println("so after split with ., the name is " + names[0]);
        String name_tmp = names[0];
        return name_tmp;
    }

    static Document getDocFromLink(String link)throws Exception{
        linkArr.add(link);
        return Jsoup.connect(link).get();
    }

    static boolean getDocFromNaverShopping(String productName) throws Exception{
        //doc = Jsoup.connect("http://shopping.naver.com/search/all.nhn?query=" + productName + "&cat_id=&frm=NVSHATC").get();
        doc = getDocFromLink("http://shopping.naver.com/search/all.nhn?query=" + productName + "&cat_id=&frm=NVSHATC");
        if(hasNoSearchResult(doc)){
            System.out.println("검색결과가 없습니다");
            setPrice("검색 결과 없음");
            return false;
        }
        return true;
    }

    static boolean hasNoSearchResult(Document doc){
        return doc.toString().contains(" 대한 네이버쇼핑 내 검색 결과가 없습니다.");
    }

    static void getPriceFromDoc(String searchString) throws Exception{
        elements = doc.getElementsByClass(searchString);

        firstPriceHtmlText = elements.first().text();
        System.out.println("element : "+ firstPriceHtmlText);
        setPrice(firstPriceHtmlText);
    }

    static void analyzeIfItHasComparePage() throws Exception{
        if(firstPriceHtmlText.contains("~")){
            String [] priceOfWhole = firstPriceHtmlText.split(" ~ ");
            setPrice(priceOfWhole[0]);
        }
        else if(firstPriceHtmlText.contains("가격비교")){
            performComparePage();
        }

    }

    static void performComparePage()throws Exception{
        analyzeCompareButton();
        if(href.equals("#")) {
            getDocFromComparePage(product_id, product_imgsignature, nclickavalue);
            getPriceFromDoc("_price_reload");
        }
        else{
            getDocFromComparePageScenario(href);
            getPriceFromDoc("price");
        }

    }

    static void analyzeCompareButton(){
        System.out.println("elements : " + elements.first().toString());
        element = elements.first().getElementsByTag("a").first();

        System.out.println("a tag element : " + element.toString());

        href = element.attr("href");
        System.out.println("child element href : " + href);

        product_id = element.attr("product_id");
        System.out.println("child element product_id : " + product_id);

        product_imgsignature = element.attr("product_imgsignature");
        System.out.println("child element product_imgsignature : " + product_imgsignature);

        nclickavalue = element.attr("nclickavalue");
        System.out.println("child element nclickavalue : " + nclickavalue);
    }

    static void getDocFromComparePage(String product_id, String product_imgsignature, String nclickavalue) throws Exception{
        doc = null;
        //doc = Jsoup.connect("http://shopping.naver.com/search/popup/same_image.nhn?nv_mid="+ product_id +"&icode=" + product_imgsignature + "&na="+ nclickavalue).get();
        doc = getDocFromLink("http://shopping.naver.com/search/popup/same_image.nhn?nv_mid="+ product_id +"&icode=" + product_imgsignature + "&na="+ nclickavalue);
    }

    static void getDocFromComparePageScenario(String href) throws Exception{
        doc = null;
        //doc = Jsoup.connect("http://shopping.naver.com" + href).get();
        doc = getDocFromLink("http://shopping.naver.com" + href);
    }

    static String trimNumber(String lowPrice){
        String tmp = null;
        /*if(lowPrice.contains(",")){
            tmp = lowPrice.replace(",", "");
        }*/
        tmp = lowPrice.replace("모바일가격 ", "");
        String tmp2 = tmp.replace(" QR코드", "");
        String tmp3 = tmp2.replace("최저가", "");
        String tmp4 = tmp3.replace("원", "");
        return tmp4;
    }

}

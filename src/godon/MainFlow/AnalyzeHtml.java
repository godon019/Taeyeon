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

    static String priceHtmlText;
    static String titleHtmlText;

    static String lastPrice;

    static ArrayList<String> priceArr;
    static ArrayList<String> linkArr;

    static StringBuilder log;

    public static Products getAnalyzedProducts(Products products) throws Exception{
        Product product = products.getProductArr("모델명");
        ArrayList<String> productValue = product.getValue();

        ArrayList<String> leastPriceProductValue = new ArrayList<String>();
        initializeArrayWithProductSizeToPreventErrorRelatedToException(productValue, leastPriceProductValue);
        ArrayList<String> logArrForProduct = new ArrayList<String>();
        initializeArrayWithProductSizeToPreventErrorRelatedToException(productValue, logArrForProduct);

        for(int i = 0; i < productValue.size(); i+=1) {
            try {
                getLowPrice(productValue.get(i));
                lastPrice = trimNumber(lastPrice);

                leastPriceProductValue.set(i, lastPrice);  //set으로 바꿔야함 그래야 exception발생시 제대로 된 곳에 적용됨
                logArrForProduct.set(i, log.toString());

            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }

        products.setProductArr("갱신된 최저가", leastPriceProductValue);
        products.setProductArr("로그", logArrForProduct);


        return products;
    }

    public static void initializeArrayWithProductSizeToPreventErrorRelatedToException(ArrayList<String> valueOfProduct, ArrayList<String> array){
        for (int i = 0; i < valueOfProduct.size(); i += 1) {
            array.add("갱신안됨");
        }
        return;
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
        log.append("Price : " + price + "\n");
        //priceArr.add(price);
    }


    static void getLowPrice(String product_name)throws Exception{
        System.out.println("검색할 이름 " + product_name);

        initializeStringsToUse();

        if(getDocFromNaverShopping(trimName(product_name))) {
            getPriceFromDoc("price");
            //Test
            getProductTitleFromDoc();
            analyzeFirstPricePage();
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

        priceHtmlText = null;
        titleHtmlText = null;
        lastPrice = null;

        priceArr = new ArrayList<String>();
        linkArr = new ArrayList<String>();

        log = new StringBuilder();
    }

    static String trimName(String name){
        log.append("검색할 이름 : " + name + "\n");
        if(!isProductModelNameOnly(name)) {
            return leaveProductModelNameOnly(name);
        }
        else
            return name;
    }

    static boolean isProductModelNameOnly(String name){
        if( name.contains(".")){
            System.out.println(name + " contains . ");
            return false;
        }
        else{
            return true;
        }
    }

    static String leaveProductModelNameOnly(String name){
        String[] names = name.split("\\.");
        System.out.println("so after split with ., the name is " + names[0]);
        String name_tmp = names[0];
        log.append("product Name Only : " + name_tmp + "\n");
        return name_tmp;
    }

    static Document getDocFromLink(String link)throws Exception{
        //linkArr.add(link);
        log.append("링크 : " + link + "\n");
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
        log.append("getPriceFromDoc ( " + searchString + " )\n");
        elements = doc.getElementsByClass(searchString);

        priceHtmlText = elements.first().text();
        System.out.println("element : "+ priceHtmlText);
        setPrice(priceHtmlText);

    }

    static void getProductTitleFromDoc() throws Exception{
        log.append("getProductTitleFromDoc( )\n");

        int cases = whichCaseOfGetProductTitleFromDoc();
        switch (cases){
            case 1:
                getProductTitleFromDocCaseOne();
                break;
            case 2:
                getProductTitleFromDocCaseTwo();
                break;
            default:
                throw new Exception("getProductTitleFromDoc() 에서 없는 케이스입니다");

        }
    }

    static int whichCaseOfGetProductTitleFromDoc()throws Exception{
        Elements elements = doc.select("li._model_list div.info a");

        if(!doc.select("li._model_list div.info a").isEmpty()){
            System.out.println("test 1" + elements.toString());
            return 1;
        }
        else if(!doc.select("li._product_list div.info a").isEmpty()){
            System.out.println("test 2" + elements.toString());
            return 2;
        }
        else{
            return 3;
        }
    }

    static void getProductTitleFromDocCaseOne()throws Exception{
        String titleHtmlText = elements.first().text();
        System.out.println("ProductTitle : "+ titleHtmlText);
        log.append("Case 1 : <li> class name is _model_list\n");
        log.append("ProductTitle : " + titleHtmlText + "\n");
    }

    static void getProductTitleFromDocCaseTwo()throws Exception{
        Elements elements = doc.select("li._product_list div.info a");
        String titleHtmlText = elements.first().text();
        log.append("Case 2 : <li> class name is _product_list\n");
        System.out.println("ProductTitle : "+ titleHtmlText);
        log.append("ProductTitle : " + titleHtmlText + "\n");
    }

    static void analyzeFirstPricePage() throws Exception{
        log.append("analyzeFirstPricePage(), 이름에 '~' 또는 '가격비교' 또는 아무것도 없는경우\n");
        if(priceHtmlText.contains("~")){
            log.append("case 1 : 이름에 ~ 있는 경우 ~제외하면 최종가격 결정\n");
            String [] priceOfWhole = priceHtmlText.split(" ~ ");
            setPrice(priceOfWhole[0]);
        }
        else if(priceHtmlText.contains("가격비교")){
            log.append("case 2 : 이름에 '가격비교' 있는 경우 compare button 분석\n");
            analyzeCompareButton();
        }
        else{
            log.append("case 3 : 이름만 있는 경우, 아무것도 안하고 최종 가격 결정\n");
        }

    }

    static void analyzeCompareButton()throws Exception{
        log.append("analyzeCompareButton ()\n");
        analyzeCompareButtonElements();
        analyzeElementsResult();

    }

    static void analyzeCompareButtonElements(){
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

    static void analyzeElementsResult()throws Exception{
        if(href.equals("#")) {
            log.append("case 1 : compare button href에 '#'이 있는 경우\n");
            getDocFromComparePageFromButtonWithSharp(product_id, product_imgsignature, nclickavalue);
            getPriceFromDoc("_price_reload");
        }
        else{
            log.append("case 2 : compare button href에 '#'이 없는 경우\n");
            getDocFromComparePageFromButtonWithoutSharp(href);
            getPriceFromDoc("price");
        }
    }

    static void getDocFromComparePageFromButtonWithSharp(String product_id, String product_imgsignature, String nclickavalue) throws Exception{
        doc = getDocFromLink("http://shopping.naver.com/search/popup/same_image.nhn?nv_mid="+ product_id +"&icode=" + product_imgsignature + "&na="+ nclickavalue);
    }

    static void getDocFromComparePageFromButtonWithoutSharp(String href) throws Exception{
        doc = getDocFromLink("http://shopping.naver.com" + href);
    }

    static String trimNumber(String lowPrice){
        log.append("trimNumber ()\n");

        if(lowPrice.contains("모바일가격")){
            log.append("모바일가격 제거하기\n");
        }
        String tmp = lowPrice.replace("모바일가격 ", "");

        if(lowPrice.contains("QR코드")){
            log.append("QR코드 제거하기\n");
        }
        String tmp2 = tmp.replace(" QR코드", "");

        if(lowPrice.contains("최저가")){
            log.append("최저가 제거하기\n");
        }
        String tmp3 = tmp2.replace("최저가", "");

        String tmp4 = tmp3.replace("원", "");
        return tmp4;
    }

}

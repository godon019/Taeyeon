package godon.MainFlow;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by Godon on 2016-06-30.
 */
public class AnalyzeHtml {

    Document doc;
    Elements elements;
    Element element;

    String href;
    String product_id ;
    String product_imgsignature;
    String nclickavalue;

    String priceHtmlText;

    String lastPrice;


    StringBuilder log;

    String productName;

    public enum FirstPageCase{
        NOT_DETERMINED_YET,
        NORMAL_PRICE,
        PRICE_HAS_WAVE_CHARACTER,
        TO_SECOND_PAGE;
    }
    AnalyzeHtml.FirstPageCase firstPageCase;

    public enum SecondPageCase{
        NOT_SECOND_CASE,
        NOT_DETERMINED_YET,
        HREF_HAS_SHARP,
        HREF_HAS_NO_SHARP;
    }
    AnalyzeHtml.SecondPageCase secondPageCase;


    public AnalyzeHtml(String productName){
        this.productName = productName;
        firstPageCase = FirstPageCase.NOT_DETERMINED_YET;
        secondPageCase = SecondPageCase.NOT_DETERMINED_YET;
    }

    StringBuilder getLog(StringBuilder log)throws Exception{

        this.log = log;
        log.append("검색할 이름 : " + productName + "\n");

        if(hasSearchResultFromFirstPage(productName)){
            getInformationFromFirstPage();
            performSecondPageIfNeeded();
        }

        appendCasesTo(log);
        return log;
    }

    void appendCasesTo(StringBuilder log){
        log.append("FirstPageCase : " + firstPageCase + "\n");
        log.append("SecondPageCase : " + secondPageCase + "\n");
    }

    void performSecondPageIfNeeded()throws Exception{

        if(analyzePriceName()==FirstPageCase.TO_SECOND_PAGE){
            performSecondPage();
        }
    }

    void performSecondPage()throws Exception{
        if(analyzeCompareButtonForSecondPage() == SecondPageCase.HREF_HAS_SHARP){
            performCaseOneOfSecondPage();
        }
        else{
            performCaseTwoOfSecondPage();
        }
    }

    void getInformationFromFirstPage(){
        try {
            getElementFromDoc("price");
            getProductTitleFromDoc();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    boolean hasSearchResultFromFirstPage(String name){
        doc = getDocFromLink("http://shopping.naver.com/search/all.nhn?query=" + name + "&cat_id=&frm=NVSHATC");
        if(hasNoSearchResult(doc)){
            setPrice("검색 결과 없음");

            return false;
        }
        return true;
    }


    Document getDocFromLink(String link){
        log.append("링크 : " + link + "\n");
        Document document = null;
        try {
            document = Jsoup.connect(link).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }


    boolean hasNoSearchResult(Document doc){
        return doc.toString().contains(" 대한 네이버쇼핑 내 검색 결과가 없습니다.");
    }

    void getElementFromDoc(String searchString) throws Exception{
        log.append("getElementFromDoc ( " + searchString + " )\n");
        elements = doc.getElementsByClass(searchString);

        priceHtmlText = elements.first().text();
        System.out.println("element : "+ priceHtmlText);
        setPrice(priceHtmlText);
    }

    void getProductTitleFromDoc() throws Exception{
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

    int whichCaseOfGetProductTitleFromDoc()throws Exception{
        Elements elements = doc.select("li._model_list div.info a");

        if(!doc.select("li._model_list div.info a").isEmpty()){
            return 1;
        }
        else if(!doc.select("li._product_list div.info a").isEmpty()){
            return 2;
        }
        else{
            return 3;
        }
    }

    void getProductTitleFromDocCaseOne()throws Exception{
        String titleHtmlText = elements.first().text();
        log.append("Case 1 : <li> class name is _model_list\n");
        log.append("ProductTitle : " + titleHtmlText + "\n");
    }

    void getProductTitleFromDocCaseTwo()throws Exception{
        Elements elements = doc.select("li._product_list div.info a");
        String titleHtmlText = elements.first().text();
        log.append("Case 2 : <li> class name is _product_list\n");
        System.out.println("ProductTitle : "+ titleHtmlText);
        log.append("ProductTitle : " + titleHtmlText + "\n");
    }

    FirstPageCase analyzePriceName() throws Exception{
        log.append("analyzePriceNameOfFirstPage(), 이름에 '~' 또는 '가격비교' 또는 아무것도 없는경우\n");

        if(priceHtmlText.contains("~")){
            log.append("case 1 : 이름에 ~ 있는 경우 ~제외하고 앞쪽 가격으로 최종가격 결정\n");
            String [] priceOfWhole = priceHtmlText.split(" ~ ");
            setPrice(priceOfWhole[0]);
            firstPageCase = FirstPageCase.PRICE_HAS_WAVE_CHARACTER;
        }
        else if(priceHtmlText.contains("가격비교") && priceHtmlText.contains("~")){
            log.append("case 1 : 이름에 ~ 있는 경우 그리고 가격비교도 있는경우 ~제외하고 앞쪽 가격으로 최종가격 결정\n");
            String [] priceOfWhole = priceHtmlText.split(" ~ ");
            setPrice(priceOfWhole[0]);
            firstPageCase = FirstPageCase.PRICE_HAS_WAVE_CHARACTER;
        }
        else if(priceHtmlText.contains("가격비교")){
            log.append("case 2 : 이름에 '가격비교' 있는 경우 그리고 ~ 없는 경우 compare button 분석하고 SecondPage로\n");
            if(priceHtmlText.contains("~")){
                throw new Exception("가격비교, ~ 가 같이 들어있으면 ~ 케이스로 먼저 가도록 해야함");
            }
            firstPageCase = FirstPageCase.TO_SECOND_PAGE;
        }
        else{
            log.append("case 3 : 이름만 있는 경우, 아무것도 안하고 최종 가격 결정\n");
            firstPageCase = FirstPageCase.NORMAL_PRICE;
        }

        return firstPageCase;
    }


    SecondPageCase analyzeCompareButtonForSecondPage()throws Exception{
        log.append("analyzeCompareButtonForSecondPage ()\n");
        analyzeCompareButtonElements();

        if(href.equals("#")) {
            log.append("case 1 : compare button href에 '#'이 있는 경우\n");
            secondPageCase = SecondPageCase.HREF_HAS_SHARP;
        }
        else{
            log.append("case 2 : compare button href에 '#'이 없는 경우\n");
            secondPageCase = SecondPageCase.HREF_HAS_NO_SHARP;
        }

        return secondPageCase;
    }

    void performCaseOneOfSecondPage()throws Exception{
        getDocFromComparePageFromButtonWithSharp(product_id, product_imgsignature, nclickavalue);
        getElementFromDoc("_price_reload");
    }

    void performCaseTwoOfSecondPage()throws Exception{
        getDocFromComparePageFromButtonWithoutSharp(href);
        getElementFromDoc("price");
    }

    void analyzeCompareButtonElements(){
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

    void getDocFromComparePageFromButtonWithSharp(String product_id, String product_imgsignature, String nclickavalue) throws Exception{
        doc = getDocFromLink("http://shopping.naver.com/search/popup/same_image.nhn?nv_mid="+ product_id +"&icode=" + product_imgsignature + "&na="+ nclickavalue);
    }

    void getDocFromComparePageFromButtonWithoutSharp(String href) throws Exception{
        doc = getDocFromLink("http://shopping.naver.com" + href);
    }

    public  void setPrice(String price){
        log.append("Price : " + price + "\n");
    }



}

package godon.Analyze;

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
    Elements priceElements;
    Element element;

    String href;
    String product_id ;
    String product_imgsignature;
    String nclickavalue;

    String priceHtmlText;

    StringBuilder log;

    String productName;

    String lastPrice;

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

        trimNameAndUpdateLog();

        if(hasSearchResultFromFirstPage(productName)){
            getInformationFromFirstPage();
            performSecondPageIfNeeded();
        }

        appendCasesTo(log);
        appendLastPriceTo(log);
        return log;
    }

    void trimNameAndUpdateLog(){
        Trimming trimming = new Trimming(log);
        productName = trimming.getTrimmedName(productName);
        this.log = trimming.getLog();
    }


    boolean hasSearchResultFromFirstPage(String name){
        doc = getDocFromLink("http://shopping.naver.com/search/all.nhn?query=" + name + "&cat_id=&frm=NVSHATC");
        if(hasNoSearchResult(doc)){
            addPriceToLog("검색 결과 없음");
            return false;
        }
        return true;
    }

    void getInformationFromFirstPage(){
        try {
            priceHtmlText = getPriceElementsFromDoc("price");
            addPriceToLog(priceHtmlText);
            log.append(LogData.CATEGORY + getCategoryDepthElementsFromDoc("depth") + "\n");
            getProductTitleFromDoc();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    String getPriceElementsFromDoc(String searchString) throws Exception{
        log.append("getPriceElementsFromDoc ( " + searchString + " )\n");

        priceElements = doc.getElementsByClass(searchString);
        String text = priceElements.first().text();
        System.out.println("element : "+ text);
        return text;
    }

    String getCategoryDepthElementsFromDoc(String searchString) throws Exception{
        Elements elements = doc.getElementsByClass(searchString);
        String text = elements.first().text();
        return text;
    }

    void getProductTitleFromDoc() throws Exception{
        log.append("getProductTitleFromDoc( )\n");
        chooseTitleFromLiTag();
    }

    void chooseTitleFromLiTag()throws Exception{
        Elements elements = null;
        if((elements = getElementsWithLiTag("_model_list")) != null){
            getProductTitleFromDocCaseOne(elements);
        }
        else if((elements = getElementsWithLiTag("_product_list")) != null){
            getProductTitleFromDocCaseTwo(elements);
        }
        else{
            throw new Exception("chooseTitleFromLiTag() 에서 없는 케이스입니다");
        }
    }

    Elements getElementsWithLiTag(String liTag){
        Elements elements = doc.select("li."+liTag+" div.info a");
        if(elements.isEmpty()){
            return null;
        }
        else {
            return elements;
        }
    }

    void getProductTitleFromDocCaseOne(Elements elements)throws Exception{
        String titleHtmlText = elements.first().text();
        log.append("Case 1 : <li> class name is _model_list\n");
        log.append(LogData.PRODUCT_TITLE + titleHtmlText + "\n");
    }

    void getProductTitleFromDocCaseTwo(Elements elements)throws Exception{
        String titleHtmlText = elements.first().text();
        log.append("Case 2 : <li> class name is _product_list\n");
        log.append(LogData.PRODUCT_TITLE  + titleHtmlText + "\n");
    }

    boolean hasNoSearchResult(Document doc){
        return doc.toString().contains(" 대한 네이버쇼핑 내 검색 결과가 없습니다.");
    }

    void appendCasesTo(StringBuilder log){
        log.append(LogData.FIRST_PAGE_CASE + firstPageCase + "\n");
        log.append(LogData.SECOND_PAGE_CASE + secondPageCase + "\n");
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

    void performCaseOneOfSecondPage()throws Exception{
        getDocFromComparePageFromButtonWithSharp(product_id, product_imgsignature, nclickavalue);
        priceHtmlText = getPriceElementsFromDoc("_price_reload");
        addPriceToLog(priceHtmlText);
    }

    void performCaseTwoOfSecondPage()throws Exception{
        getDocFromComparePageFromButtonWithoutSharp(href);
        priceHtmlText = getPriceElementsFromDoc("price");
        addPriceToLog(priceHtmlText);
    }

    Document getDocFromLink(String link){
        log.append(LogData.LINK + link + "\n");
        Document document = null;
        try {
            document = Jsoup.connect(link).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }

    FirstPageCase analyzePriceName() throws Exception{
        log.append("analyzePriceNameOfFirstPage(), 이름에 '~' 또는 '가격비교' 또는 아무것도 없는경우\n");

        if(priceHtmlText.contains("~")){
            log.append("case 1 : 이름에 ~ 있는 경우 ~제외하고 앞쪽 가격으로 최종가격 결정\n");
            String [] priceOfWhole = priceHtmlText.split(" ~ ");
            addPriceToLog(priceOfWhole[0]);
            firstPageCase = FirstPageCase.PRICE_HAS_WAVE_CHARACTER;
            secondPageCase = SecondPageCase.NOT_SECOND_CASE;
        }
        else if(priceHtmlText.contains("가격비교") && priceHtmlText.contains("~")){
            log.append("case 1 : 이름에 ~ 있는 경우 그리고 가격비교도 있는경우 ~제외하고 앞쪽 가격으로 최종가격 결정\n");
            String [] priceOfWhole = priceHtmlText.split(" ~ ");
            addPriceToLog(priceOfWhole[0]);
            firstPageCase = FirstPageCase.PRICE_HAS_WAVE_CHARACTER;
            secondPageCase = SecondPageCase.NOT_SECOND_CASE;
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
            secondPageCase = SecondPageCase.NOT_SECOND_CASE;
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


    void analyzeCompareButtonElements(){
        System.out.println("priceElements : " + priceElements.first().toString());
        //element = priceElements.first().getElementsByTag("a").first();
        element = priceElements.select("a.btn_compare").first();

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

    public  void addPriceToLog(String price){
        log.append("Price : " + price + "\n");
        lastPrice = price;
    }

    public void appendLastPriceTo(StringBuilder log){
        log.append(LogData.LAST_PRICE + lastPrice + "\n");
    }

}

package godon.Editing;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Godon on 2016-07-21.
 */
public class logAnalyzerTest {
    String testLog = "로그 갱신 전검색할 이름 : 10T360-B830K\n" +
            "검색할 이름 : 10T360-B830K\n" +
            "링크 : http://shopping.naver.com/search/all.nhn?query=10T360-B830K&cat_id=&frm=NVSHATC\n" +
            "getPriceElementsFromDoc ( price )\n" +
            "Price : 399,000원 ~ 911,960원 가격비교 27\n" +
            "Category depth : 디지털/가전 > 노트북\n" +
            "getProductTitleFromDoc( )\n" +
            "Case 1 : <li> class name is _model_list\n" +
            "ProductTitle : LG전자 탭북 듀오 10T360-B830K\n" +
            "analyzePriceNameOfFirstPage(), 이름에 '~' 또는 '가격비교' 또는 아무것도 없는경우\n" +
            "case 1 : 이름에 ~ 있는 경우 ~제외하고 앞쪽 가격으로 최종가격 결정\n" +
            "Price : 399,000원\n" +
            "FirstPageCase : PRICE_HAS_WAVE_CHARACTER\n" +
            "SecondPageCase : NOT_SECOND_CASE";
    @Test
    public void analyze() throws Exception {
        LogAnalyzer LogAnalyzer = new PriceLogAnalyzer(testLog);
        LogAnalyzer.analyze();
        assertEquals("399,000", LogAnalyzer.getResult());
    }

    @Test
    public void analyzeLine() throws Exception {


    }

    @Test
    public void throwExceptionIfIncorrectResult() throws Exception {

    }

    @Test
    public void getResult() throws Exception {

    }

}
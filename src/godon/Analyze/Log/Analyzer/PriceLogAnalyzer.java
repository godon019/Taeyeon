package godon.Analyze.Log.Analyzer;

import godon.Analyze.LogData;

/**
 * Created by Godon on 2016-07-21.
 */
public class PriceLogAnalyzer extends LogAnalyzer {

    public PriceLogAnalyzer(String log) {
        super(log);
        seperator = LogData.LAST_PRICE;
    }

    @Override
    protected String trimResult(String result){
        return trimPrice(result);
    }

    String trimPrice(String lowPrice){

        String tmp = lowPrice.replace("모바일가격 ", "");

        String tmp2 = tmp.replace(" QR코드", "");

        String tmp3 = tmp2.replace("최저가", "");

        String tmp4 = tmp3.replace("원", "");
        return tmp4;
    }
}

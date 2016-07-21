package godon.Editing;

import godon.Analyze.Trimming;

/**
 * Created by Godon on 2016-07-21.
 */
public class PriceLogAnalyzer extends LogAnalyzer {

    public PriceLogAnalyzer(String log) {
        super(log);
        seperator = "Price : ";
    }

    @Override
    protected String trimResult(String result){
        return Trimming.trimPrice(result);
    }
}

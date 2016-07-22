package godon.Analyze.Log.Analyzer;

import godon.Analyze.LogData;

/**
 * Created by Godon on 2016-07-21.
 */
public class TitleLogAnalyzer extends LogAnalyzer {
    public TitleLogAnalyzer(String log) {
        super(log);
        seperator = LogData.PRODUCT_TITLE;
    }
}

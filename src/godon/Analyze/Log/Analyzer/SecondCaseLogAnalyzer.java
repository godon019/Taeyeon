package godon.Analyze.Log.Analyzer;

import godon.Analyze.LogData;

/**
 * Created by Godon on 2016-07-21.
 */
public class SecondCaseLogAnalyzer extends LogAnalyzer {
    public SecondCaseLogAnalyzer(String log) {
        super(log);
        seperator = LogData.SECOND_PAGE_CASE;
    }
}

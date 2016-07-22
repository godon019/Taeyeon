package godon.Analyze.Log.Analyzer;

import godon.Analyze.LogData;

/**
 * Created by Godon on 2016-07-21.
 */
public class FirstCaseLogAnalyzer extends LogAnalyzer {
    public FirstCaseLogAnalyzer(String log) {
        super(log);
        seperator = LogData.FIRST_PAGE_CASE;
    }
}

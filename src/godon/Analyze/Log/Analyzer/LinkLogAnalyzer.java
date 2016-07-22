package godon.Analyze.Log.Analyzer;

import godon.Analyze.LogData;

/**
 * Created by Godon on 2016-07-21.
 */
public class LinkLogAnalyzer extends LogAnalyzer {
    public LinkLogAnalyzer(String log) {
        super(log);
        seperator = LogData.LINK;
    }
}

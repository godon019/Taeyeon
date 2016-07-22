package godon.Analyze.Log.Analyzer;

import godon.Analyze.LogData;

/**
 * Created by Godon on 2016-07-21.
 */
public class CategoryLogAnalyzer extends LogAnalyzer {
    public CategoryLogAnalyzer(String log) {
        super(log);
        seperator = LogData.CATEGORY;
    }
}

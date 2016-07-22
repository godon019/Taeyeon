package godon.Analyze.Log;

import godon.Analyze.Log.Analyzer.*;
import godon.Analyze.LogData;

/**
 * Created by Godon on 2016-07-21.
 */
public class LogAnalyzerFactory {
    static public LogAnalyzer getAnalyzeLog(String name, String log){


        if(name == LogData.LAST_PRICE){
            return new PriceLogAnalyzer(log);
        }
        else if(name == LogData.CATEGORY){
            return new CategoryLogAnalyzer(log);
        }
        else if(name == LogData.FIRST_PAGE_CASE){
            return new FirstCaseLogAnalyzer(log);
        }
        else if(name == LogData.SECOND_PAGE_CASE){
            return new SecondCaseLogAnalyzer(log);
        }
        else if(name == LogData.LINK){
            return new LinkLogAnalyzer(log);
        }
        else if(name == LogData.PRODUCT_TITLE){
            return new TitleLogAnalyzer(log);
        }
        else {
            return noNameExist();
        }
    }

    static LogAnalyzer noNameExist(){
        try {
            throw new Exception("팩토리에 없는 이름의 AnalyzeLog입니다");
        }
        catch (Exception e){
            e.printStackTrace();
            System.exit(0);
        }
        return null;
    }
}

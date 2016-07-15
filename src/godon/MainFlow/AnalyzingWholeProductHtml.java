package godon.MainFlow;

import java.util.ArrayList;

/**
 * Created by Godon on 2016-07-14.
 */
public class AnalyzingWholeProductHtml {
    public String perform(String productName){
        AnalyzeHtml analyzeHtml = new AnalyzeHtml(productName);
        StringBuilder log = new StringBuilder();
        log.append("로그 갱신 전");
        try {
            log = analyzeHtml.getLog(log);

        } catch (Exception e) {
            e.printStackTrace();
            log.append("갱신 오류");
        }
        return log.toString();
    }

    public ArrayList<String> performAll(ArrayList<String> productNames){
        ArrayList<String> logs =  new ArrayList<>();
        for(int i =0; i<productNames.size(); i++){
            logs.add(perform(productNames.get(i)));
        }
        return  logs;
    }


}
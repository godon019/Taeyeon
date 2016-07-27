package godon.Analyze;

import godon.Analyze.MallStuff.MallListTypeStuff.TypeModel.MallListByTypeModel;
import godon.Util.lg;

import java.util.ArrayList;

/**
 * Created by Godon on 2016-07-14.
 */
public class AnalyzingWholeProductHtml {
    public String perform(String productName, String wholeProductName){
        AnalyzeHtml analyzeHtml = new AnalyzeHtml(productName);
        StringBuilder log = new StringBuilder();
        log.append("로그 갱신 전");
        try {
            log = analyzeHtml.getLog_new(log, wholeProductName);

        } catch (MallListByTypeModel.UnknownResultOfMallListFromModelType e){
            log.append("=======================Critical Error======================\n");
            lg.debug(log, e.getMessage());

        } catch (Exception e) {
            e.printStackTrace();
            log.append(e.toString() + " \n갱신 오류");
            System.out.println("오류 로그 출력 : \n"+log.toString());
        }


        return log.toString();
    }

    public ArrayList<String> performAll(ArrayList<String> productNames, ArrayList<String> wholeProductName){
        ArrayList<String> logs =  new ArrayList<>();
        for(int i =0; i<productNames.size(); i++){
            logs.add(perform(productNames.get(i), wholeProductName.get(i)));
        }
        return  logs;
    }


}

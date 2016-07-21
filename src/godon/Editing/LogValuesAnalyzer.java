package godon.Editing;

import java.util.ArrayList;

/**
 * Created by Godon on 2016-07-20.
 */
public class LogValuesAnalyzer {
    ArrayList<String> tmpValues;
    ArrayList<String> logValues;


    public LogValuesAnalyzer(ArrayList<String> logValues) {
       this.logValues = logValues;
    }

    public ArrayList<String> getAnalyzedValues(String name){
        initializeTmpValues(logValues.size());

        for(int i =0; i<logValues.size(); i++){
            String logToAnalyze = logValues.get(i);
            LogAnalyzer priceLog = LogAnalyzerFactory.getAnalyzeLog(name, logToAnalyze);
            priceLog.analyze();
            tmpValues.set(i, priceLog.getResult());
        }

        return tmpValues;
    }

    void initializeTmpValues(int size){
        tmpValues = new ArrayList<>();
        for(int i =0; i<size; i++){
            tmpValues.add("로그 컬럼 갱신 전");
        }
    }

}

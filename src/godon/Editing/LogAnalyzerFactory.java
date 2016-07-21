package godon.Editing;

/**
 * Created by Godon on 2016-07-21.
 */
public class LogAnalyzerFactory {
    static public LogAnalyzer getAnalyzeLog(String name, String log){
        if(name == "최저가"){
            return new PriceLogAnalyzer(log);
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

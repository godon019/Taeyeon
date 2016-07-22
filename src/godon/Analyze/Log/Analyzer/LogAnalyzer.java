package godon.Analyze.Log.Analyzer;

/**
 * Created by Godon on 2016-07-21.
 */
public class LogAnalyzer {
    private String log;
    private String result;
    protected String seperator;
    boolean resultFound;

    public LogAnalyzer(String log){
        this.log = log;
        result = null;
        resultFound = false;
    }

    public void analyze(){
        seperateItByLinesAndAnalyze();
    }

   private  void seperateItByLinesAndAnalyze(){
        String[] lines = log.split("\n");

        for(String line : lines){
            analyzeLine(line.trim());
        }
    }

    private void analyzeLine(String line){
        if(line.startsWith(seperator)){
            resultFound = true;
            result = trimResult(getResultFromLine(line));
        }
    }

    private String getResultFromLine(String line){
        return line.replace(seperator, "");
    }

    protected String trimResult(String result){
        //do nothing or should be overrided
        return result;
    }

    public String getResult(){
        throwExceptionIfIncorrectResult();
        return result;
    }

    private void throwExceptionIfIncorrectResult(){
        try {
            if (result == null) {
                if(resultFound)
                    throw new Exception("결과가 null 입니다. 잘못된 값입니다");
                else
                    result = "결과없음";
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


}

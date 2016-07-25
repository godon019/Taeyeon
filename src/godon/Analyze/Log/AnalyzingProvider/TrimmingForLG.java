package godon.Analyze.Log.AnalyzingProvider;

/**
 * Created by Godon on 2016-07-15.
 */
public class TrimmingForLG {
    StringBuilder log;

    public TrimmingForLG(StringBuilder log){
        this.log = log;
    }

    public StringBuilder getLog(){
        return log;
    }

    public String getTrimmedName(String name){
        log.append("검색할 이름 : " + name + "\n");
        if(!hasSuffix(name)) {
            return removeSuffix(name);
        }
        else
            return name;
    }

    boolean hasSuffix(String name){
        if( name.contains(".")){
            return false;
        }
        else{
            return true;
        }
    }

    //HBS-1100.AGKRSV -> HBS-1100 and I think it should be also compared with nameOfProduct
    String removeSuffix(String name){
        String[] names = name.split("\\.");
        String name_tmp = names[0];
        log.append("product Name Only : " + name_tmp + "\n");
        return name_tmp;
    }

}

package godon.Analyze.Log;

import java.util.ArrayList;

/**
 * Created by Godon on 2016-07-27.
 */
public class MallInspector {
    ArrayList<Mall> malls = new ArrayList<>();

    ArrayList<String> badMalls = new ArrayList<>();

    public MallInspector(ArrayList<Mall> malls){
        this.malls = malls;


        addBadMall("위메프");
        addBadMall("쿠팡");
        addBadMall("티몬");
    }

    public void addBadMall(String badMall){
        badMalls.add(badMall);
    }

    public Mall getGoodMallFromTop()throws Exception{
        String goodMall;
        for(Mall mall : malls){
            if(isGoodMall(mall))
                return mall;
        }

        throw new Exception("There are no good malls");
    }

    public boolean isGoodMall(Mall mall){
        for(String badmall : badMalls){
            if(mall.name.contains(badmall))
                return false;
        }
        return true;
    }

}

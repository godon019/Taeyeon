package godon.Analyze.MallStuff;

import java.util.ArrayList;

/**
 * Created by Godon on 2016-07-27.
 */
public class BadMallList {
    ArrayList<String> badMalls = new ArrayList<>();

    BadMallList(){
        badMalls.add("위메프");
        badMalls.add("쿠팡");
        badMalls.add("티몬");
    }

    public void addBadMall(String str){
        badMalls.add(str);
    }

    ArrayList<String> getBadMalls(){
        return badMalls;
    }
}

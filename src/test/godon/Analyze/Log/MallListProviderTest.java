package godon.Analyze.Log;

import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by Godon on 2016-07-27.
 */
public class MallListProviderTest {
    @Test
    public void getAllMalls() throws Exception {
        MallListProvider mallListProvider = new MallListProvider(Link.getDocFromLink("http://shopping.naver.com/search/all.nhn?query=10T360-B830K&cat_id=&frm=NVSHATC"));

        ArrayList<Mall> malls = mallListProvider.getAllMalls();
        for(Mall mall : malls){
            System.out.println("mall : " +mall.element.text());
        }
    }

    @Test
    public void getMallList() throws Exception {
        MallListProvider mallListProvider = new MallListProvider(Link.getDocFromLink("http://shopping.naver.com/search/all.nhn?query=10T550-B83BK&cat_id=&frm=NVSHATC"));
        try {
            System.out.println("malls : " + mallListProvider.getMallList().text());
        }
        catch (Exception e){
            System.out.println("Error : " + e.getMessage());
        }

    }

}
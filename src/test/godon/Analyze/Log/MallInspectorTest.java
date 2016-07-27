package godon.Analyze.Log;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

/**
 * Created by Godon on 2016-07-27.
 */
public class MallInspectorTest {
    @Test
    public void getGoodMallFromTop() throws Exception {

    }

    @Test
    public void isGoodMall() throws Exception {
        MallListProvider mallListProvider = new MallListProvider(Link.getDocFromLink("http://shopping.naver.com/search/all.nhn?query=10T360-B830K&cat_id=&frm=NVSHATC"));
        ArrayList<Mall> malls = mallListProvider.getAllMalls();

        MallInspector mallInspector = new MallInspector(malls);
        assertTrue(mallInspector.getGoodMallFromTop().name.contains("G마켓"));

        mallInspector.addBadMall("G마켓");
        assertTrue(mallInspector.getGoodMallFromTop().name.contains("옥션"));

    }

}
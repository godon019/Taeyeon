package godon.Analyze.Log;

import godon.Analyze.MallStuff.MallListTypeStuff.Mall;
import godon.Analyze.MallStuff.MallInspector;
import godon.Analyze.MallStuff.MallListProvider;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

/**
 * Created by Godon on 2016-07-27.
 */
public class MallInspectorTest {
    @Test
    public void isGoodMall() throws Exception {
        GoodsList goodsList = new GoodsList(Link.getDocFromLink("http://shopping.naver.com/search/all.nhn?query=10T360-B830K&cat_id=&frm=NVSHATC"));
        MallListProvider mallListProvider = new MallListProvider(goodsList.getProductLists().get(0), new StringBuilder());

        ArrayList<Mall> malls = mallListProvider.getMallList();

        MallInspector mallInspector = new MallInspector(malls);
        assertTrue(mallInspector.getGoodMallFromTop().name.contains("G마켓"));

        mallInspector.addBadMall("G마켓");
        assertTrue(mallInspector.getGoodMallFromTop().name.contains("옥션"));

    }

}
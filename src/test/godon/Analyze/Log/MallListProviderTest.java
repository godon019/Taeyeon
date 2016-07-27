package godon.Analyze.Log;

import godon.Analyze.MallStuff.MallListTypeStuff.Mall;
import godon.Analyze.MallStuff.MallListProvider;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by Godon on 2016-07-27.
 */
public class MallListProviderTest {
    @Test
    public void mallCaseModel() throws Exception {
        GoodsList goodsList = new GoodsList(Link.getDocFromLink("http://shopping.naver.com/search/all.nhn?query=10T360-B83BK&cat_id=&frm=NVSHATC"));

        MallListProvider mallListProvider = new MallListProvider(goodsList.getProductLists().get(0), new StringBuilder());

        ArrayList<Mall> malls = mallListProvider.getMallList();

        System.out.println(mallListProvider.log.toString());
    }

    @Test
    public void mallCaseProduct() throws Exception {
        GoodsList goodsList = new GoodsList(Link.getDocFromLink("http://shopping.naver.com/search/all.nhn?query=24MT47DC&cat_id=&frm=NVSHATC"));
        MallListProvider mallListProvider = new MallListProvider(goodsList.getProductLists().get(0), new StringBuilder());

        ArrayList<Mall> malls = mallListProvider.getMallList();

        System.out.println(mallListProvider.log.toString());

    }

}
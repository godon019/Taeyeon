package godon.Analyze.Log;

import org.jsoup.nodes.Element;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by Godon on 2016-07-27.
 */
public class GoodsListTest {
    @Test
    public void getProductLists() throws Exception {
        GoodsList goodsList = new GoodsList(Link.getDocFromLink("http://shopping.naver.com/search/all.nhn?query=10T360-B830K&cat_id=&frm=NVSHATC"));
        System.out.println(goodsList.getProductLists().toString());
    }

    @Test
    public void getAllGoods() throws Exception{
        GoodsList goodsList = new GoodsList(Link.getDocFromLink("http://shopping.naver.com/search/all.nhn?query=10T360-B830K&cat_id=&frm=NVSHATC"));
        ArrayList<Element> goods = goodsList.getAllGoods();

        for(Element good : goods){
            System.out.println(good.text());
        }
    }

}
package godon.Analyze.Log;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

/**
 * Created by Godon on 2016-07-27.
 */
public class GoodsList {
    Document document;
    ArrayList<Element> goods = new ArrayList<>();

    GoodsList(){

    }

    public GoodsList(Document document){
        this.document = document;

    }



    public ArrayList<Element> getAllGoods(){
        Elements elements = getProductLists();
        for(Element element : elements){
            goods.add(element);
        }
        return goods;
    }

    Elements getProductLists(){
        return document.select("ul.goods_list li");
    }


}

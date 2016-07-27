package godon.Analyze.Log;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

/**
 * Created by Godon on 2016-07-27.
 */
public class MallListProvider {

    Document document;
    ArrayList<Mall> malls = new ArrayList<>();

    MallListProvider(){

    }

    public MallListProvider(Document document){
        this.document = document;

    }

    public ArrayList<Mall> getAllMalls() throws Exception{
        Elements elements = getMallList();
        for(Element element : elements){
            malls.add(new Mall(element));
        }
        return malls;
    }

    Elements getMallList()throws Exception{
        Element mallList = document.select("div.info_mall ul.mall_list").first();
        if(mallList != null)
            return mallList.select("li");
        else
            throw new Exception("No mall_list exist");
    }


}

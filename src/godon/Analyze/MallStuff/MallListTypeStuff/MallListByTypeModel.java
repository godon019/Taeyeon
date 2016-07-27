package godon.Analyze.MallStuff.MallListTypeStuff;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

/**
 * Created by Godon on 2016-07-27.
 */
public class MallListByTypeModel extends MallListByType{

    @Override
    public ArrayList<Mall> getMallList(Element productElement){
        Elements mallList = productElement.select("div.info_mall ul.mall_list li");
        assert(mallList!=null);

        ArrayList<Mall> malls = new ArrayList<>();
        for(Element element : mallList){
            malls.add(new MallTypeModel(element));
        }
        return malls;
    }
}

package godon.Analyze.MallStuff.MallListTypeStuff;

import org.jsoup.nodes.Element;

import java.util.ArrayList;

/**
 * Created by Godon on 2016-07-27.
 */
public class MallListByTypeProduct extends MallListByType{

    @Override
    public ArrayList<Mall> getMallList(Element productElement){
        Element mallElement = productElement.select("div.info_mall p.mall_txt").first();

        assert(mallElement!=null);

        ArrayList<Mall> malls = new ArrayList<>();

        malls.add(new MallTypeProduct(mallElement));

        return malls;
    }
}

package godon.Analyze.MallStuff.MallListTypeStuff.TypeException;

import godon.Analyze.MallStuff.MallListTypeStuff.Mall;
import godon.Analyze.MallStuff.MallListTypeStuff.MallListByType;
import org.jsoup.nodes.Element;

import java.util.ArrayList;

/**
 * Created by Godon on 2016-07-27.
 */
public class MallListByTypeException  extends MallListByType {

    @Override
    public ArrayList<Mall> getMallList(Element productElement){
        Element mallElement = productElement.select("div.info_mall p.mall_txt").first();

        ArrayList<Mall> malls = new ArrayList<>();
        malls.add(new MallTypeException(mallElement));

        return malls;
    }
}

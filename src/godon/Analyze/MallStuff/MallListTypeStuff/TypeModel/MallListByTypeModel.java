package godon.Analyze.MallStuff.MallListTypeStuff.TypeModel;

import godon.Analyze.MallStuff.MallListTypeStuff.Mall;
import godon.Analyze.MallStuff.MallListTypeStuff.MallListByType;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

/**
 * Created by Godon on 2016-07-27.
 */
public class MallListByTypeModel extends MallListByType {

    @Override
    public ArrayList<Mall> getMallList(Element productElement){
        ArrayList<Mall> malls = new ArrayList<>();
        Elements mallList = productElement.select("div.info_mall ul.mall_list li");

        if(mallList.isEmpty()){
            String info_mall_txt = productElement.select("div.info_mall").text();

            if(info_mall_txt.contains("현재 상품 일시 중단 또는 단종된 상품입니다.")) {
                //throw new UnknownResultOfMallListFromModelType();

            }
            else{
                throw new UnknownResultOfMallListFromModelType();
            }

        }
        else{
            for(Element element : mallList){
                malls.add(new MallTypeModel(element));
            }
        }
        return malls;

    }

    public class UnknownResultOfMallListFromModelType extends RuntimeException {
        UnknownResultOfMallListFromModelType(){
            super("Unknown result from Model Type Mall List");
        }
    }
}

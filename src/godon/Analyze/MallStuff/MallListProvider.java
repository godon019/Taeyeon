package godon.Analyze.MallStuff;

import godon.Analyze.MallStuff.MallException.NoMallTypeExistException;
import godon.Analyze.MallStuff.MallListTypeStuff.Mall;
import godon.Analyze.MallStuff.MallListTypeStuff.MallListByType;
import godon.Analyze.MallStuff.MallListTypeStuff.MallListByTypeFactory;
import godon.Analyze.MallStuff.MallListTypeStuff.MallTypeEnum;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;

/**
 * Created by Godon on 2016-07-27.
 */
public class MallListProvider {

    Document document;
    Element productElement;
    ArrayList<Mall> malls = new ArrayList<>();
    public StringBuilder log = new StringBuilder();
    MallTypeEnum mallTypeEnum;

    public MallListProvider(Element productElement, StringBuilder log)throws NoMallTypeExistException{
        this.productElement = productElement;
        mallTypeEnum = getMallListType(this.productElement);
        this.log = log;
        log.append("Mall type : " + mallTypeEnum);
    }


    public MallTypeEnum getMallListType(Element element)throws NoMallTypeExistException{
        return MallTypeEnum.getMallTypeFromElementClassName(element.attr("class"));
    }

    public ArrayList<Mall> getMallList() {
        MallListByType mallListByType = MallListByTypeFactory.getProviderByType(mallTypeEnum);
        ArrayList<Mall> malls =  mallListByType.getMallList(productElement);
        log.append(malls.toString() + "\n");
        return malls;

    }



}

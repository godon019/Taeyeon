package godon.Analyze.MallStuff.MallListTypeStuff.TypeException;

import godon.Analyze.MallStuff.MallListTypeStuff.Mall;
import org.jsoup.nodes.Element;

/**
 * Created by Godon on 2016-07-27.
 */
public class MallTypeException extends Mall {
    MallTypeException(Element element){
        System.out.println(element.text());
        String tmp[] = element.text().split(" ");
        name = tmp[0];
        price = "Not found";
        this.element = element;
    }
}

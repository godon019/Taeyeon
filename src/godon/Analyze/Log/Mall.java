package godon.Analyze.Log;

import org.jsoup.nodes.Element;

/**
 * Created by Godon on 2016-07-27.
 */
public class Mall {
    String name;
    String price;
    Element element;
    public Mall(Element element){
        this.element = element;
        String tmp = element.text();
        String tmp2[] = tmp.split(" ");
        name = tmp2[0];
        price = tmp2[1];
    }
}

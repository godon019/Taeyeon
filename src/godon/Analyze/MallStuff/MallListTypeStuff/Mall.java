package godon.Analyze.MallStuff.MallListTypeStuff;

import org.jsoup.nodes.Element;

/**
 * Created by Godon on 2016-07-27.
 */
public class Mall {
    public String name = null;
    public String price = null;
    public Element element = null;

    @Override
    public String toString() {
        return new StringBuilder().append("\nname : " + name + " / price : " + price + " / element : " + element.text()).toString()+" ";
    }
}



package godon.Analyze.MallStuff.MallListTypeStuff;

import org.jsoup.nodes.Element;

import java.util.ArrayList;

/**
 * Created by Godon on 2016-07-27.
 */
public abstract class MallListByType {
    public abstract ArrayList<Mall> getMallList(Element element);
}

package godon.Analyze;

import godon.Util.lg;
import org.jsoup.nodes.Element;

/**
 * Created by Godon on 2016-07-27.
 */
public class Category {
    StringBuilder log = new StringBuilder();

    Category(StringBuilder log){
        this.log = log;
    }

    void getFrom(Element element){
        Element categoryElement = element.select("div.info span.depth").first();
        String category;

        if(categoryElement == null){
            category = "No Category Exist";
        }
        else {
            category = categoryElement.text();
        }

        lg.debug(log, LogData.CATEGORY + category);
    }
}


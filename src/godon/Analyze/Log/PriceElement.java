package godon.Analyze.Log;

import godon.Util.lg;
import org.jsoup.nodes.Element;

/**
 * Created by Godon on 2016-07-27.
 */
public class PriceElement {

    StringBuilder log = new StringBuilder();
    Element price;

    public PriceElement(StringBuilder log){
        this.log = log;
    }

    public void getFromGood(Element good) throws Exception{
        price = good.select("div.info span.price").first();

        if(price == null){
            log.append(lg.er()+"price element from select is empty \n");
            throw new Exception("getting price element error");
        }

        System.out.println("price element : "+ price.text());
    }

    public String getPriceText(){
        return price.text();
    }


}

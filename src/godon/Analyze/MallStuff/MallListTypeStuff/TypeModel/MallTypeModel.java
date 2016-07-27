package godon.Analyze.MallStuff.MallListTypeStuff.TypeModel;

import godon.Analyze.MallStuff.MallListTypeStuff.Mall;
import org.jsoup.nodes.Element;

/**
 * Created by Godon on 2016-07-27.
 */
public class MallTypeModel extends Mall {
    public MallTypeModel(Element element){
        this.element = element;
        String tmp = element.text();
        String tmp2[] = tmp.split(" ");
        name = tmp2[0];
        price = tmp2[tmp2.length-1];
        price = price.replace("최저가", "");
        //name : 아이코다 / price : 399,000 / element : 아이코다 네이버페이 399,000
        // element 가 이런경우가 있기 때문에
        //name 은 첫번째꺼
        //price는 마지막꺼가 되야함
        //name : 11번가 / price : 389,800 / element : 11번가 최저가389,800
        //그리고 위와 같은 경우 때문에 '최저가' 제거 해줘야함
    }

}

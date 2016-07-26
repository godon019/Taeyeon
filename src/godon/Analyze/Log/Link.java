package godon.Analyze.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by Godon on 2016-07-27.
 */
public class Link {
    Document document = null;
    StringBuilder log = new StringBuilder();

    public Link(StringBuilder log){
        this.log = log;
    }

    public boolean hasSearchResultFromFirstPage(String modelName){
        document = getDocFromLink("http://shopping.naver.com/search/all.nhn?query=" + modelName + "&cat_id=&frm=NVSHATC");
        if(hasNoSearchResult(document)){
            log.append("검색 결과 없음");
            return false;
        }
        return true;
    }

    boolean hasNoSearchResult(Document doc){
        return doc.toString().contains(" 대한 네이버쇼핑 내 검색 결과가 없습니다.");
    }


    private Document getDocFromLink(String link){
        Document document = null;
        try {
            document = Jsoup.connect(link).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }

    public Document getDoc(){
        return document;
    }

}

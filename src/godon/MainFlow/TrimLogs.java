package godon.MainFlow;

/**
 * Created by Godon on 2016-07-14.
 */
public class TrimLogs {
    String trimNumber(String lowPrice){

        String tmp = lowPrice.replace("모바일가격 ", "");

        String tmp2 = tmp.replace(" QR코드", "");

        String tmp3 = tmp2.replace("최저가", "");

        String tmp4 = tmp3.replace("원", "");
        return tmp4;
    }

}

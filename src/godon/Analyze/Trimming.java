package godon.Analyze;

/**
 * Created by Godon on 2016-07-15.
 */
public class Trimming {
    StringBuilder log;

    public Trimming(StringBuilder log){
        this.log = log;
    }

    StringBuilder getLog(){
        return log;
    }

    String getTrimmedName(String name){
        log.append("검색할 이름 : " + name + "\n");
        if(!isProductNameOnly(name)) {
            return leaveProductNameOnly(name);
        }
        else
            return name;
    }

    boolean isProductNameOnly(String name){
        if( name.contains(".")){
            return false;
        }
        else{
            return true;
        }
    }

    String leaveProductNameOnly(String name){
        String[] names = name.split("\\.");
        String name_tmp = names[0];
        log.append("product Name Only : " + name_tmp + "\n");
        return name_tmp;
    }

    public static String trimPrice(String lowPrice){

        String tmp = lowPrice.replace("모바일가격 ", "");

        String tmp2 = tmp.replace(" QR코드", "");

        String tmp3 = tmp2.replace("최저가", "");

        String tmp4 = tmp3.replace("원", "");
        return tmp4;
    }

}

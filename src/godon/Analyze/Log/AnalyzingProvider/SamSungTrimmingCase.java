package godon.Analyze.Log.AnalyzingProvider;

/**
 * Created by Godon on 2016-07-25.
 */
public class SamSungTrimmingCase {
    StringBuilder log;
    String modelName;
    String productName;

    SamSungTrimmingCase(StringBuilder log, String modelName, String productName){
        this.log = log;
        this.modelName = modelName;
        this.productName = productName;
    }

    String trimNameForSamSung()throws Exception{
        String modelNumberLastSRemoved = removeLastS(modelName);
        if(modelName.contains("/B2B")){
            log.append("B2B제거\n");
            String productNameWithoutB2B = modelName.replace("/B2B", "");
            return productNameWithoutB2B;
        }
        else if(productName.contains(modelNumberLastSRemoved) && !(modelName.equals(modelNumberLastSRemoved)) && !(productName.contains(modelName))){
            log.append("마지막S 제거\n");
            return  modelNumberLastSRemoved;
        }
        else {
            return modelName;
        }

        //throw new Exception("fail to remove stuff for SAMSUNG, productName : " + productName + "wholeProductName : " +wholeProductName);
    }

    void perform(){
        if(isProductNameNull()==false) {
            if (hasB2B_AsSuffix(modelName)) {
                modelName = removeB2B(modelName);
                return;
            }

            if (hasS_AsSuffix(modelName)) {
                modelName = removeLastS(modelName);
                return;
            }
        }
        else{
            throwNothingToDoException();
        }

    }

    String removeB2B(String string){
        log.append("B2B제거\n");
        String productNameWithoutB2B = modelName.replace("/B2B", "");
        return productNameWithoutB2B;
    }

    boolean isProductNameNull(){
        return productName.trim().isEmpty();
    }

    public String removeLastS(String str) {
        if (str != null && str.length() > 0 && str.charAt(str.length()-1)=='S') {
            str = str.substring(0, str.length()-1);
        }
        return str;
    }

    public boolean hasB2B_AsSuffix(String str){
        if( str.endsWith("/B2B"))
            return true;
        else
            return false;
    }

    public boolean hasS_AsSuffix(String str){
        if( str.endsWith("S") || str.endsWith("s"))
            return true;
        else
            return false;
    }

    public boolean hasSAsSuffixExclusively(String productName, String modelNumberLastSRemoved){
        if(productName.contains(modelNumberLastSRemoved) && !(modelName.equals(modelNumberLastSRemoved)) && !(productName.contains(modelName))){
            return true;
        }
        else{
            return false;
        }
    }
}

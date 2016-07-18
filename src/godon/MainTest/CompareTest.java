package godon.MainTest;

import godon.Product.ComparableProduct;
import godon.Product.ComparedDebugProduct;
import godon.Product.Product;

import java.util.ArrayList;

import static godon.Product.ComparedDebugProduct.STANDARD_ERROR_MESSEGE;

/**
 * Created by Godon on 2016-07-18.
 */
public class CompareTest {
    //get two products
    //make excel log of comparing test
    ComparableProduct product1;
    ComparableProduct product2;
    ComparedDebugProduct comparedDebugProduct;

    String productName;
    ComparableProduct.ValueType productValueType;

    public CompareTest(){

    }

    public boolean areTheySame(ComparableProduct product1, ComparableProduct product2){
        this.product1 = product1;
        this.product2 = product2;
        initializeDebuggingProduct(product1);
        doMainInspection();
        System.out.println("결과 : " + !comparedDebugProduct.hasError());
        return !(comparedDebugProduct.hasError());
    }

    void initializeDebuggingProduct(Product product){
        ArrayList<String> arrayList = new ArrayList<>();
        for(int i =0 ; i <product.getValues().size(); i++){
            arrayList.add("Not renewed yet");
        }
        comparedDebugProduct = new ComparedDebugProduct(product);
        comparedDebugProduct.setValues(arrayList);
    }

    public ComparedDebugProduct getComparedDebugProduct(){
        return comparedDebugProduct;
    }

    void doMainInspection(){
        try {
            getValueTypeAndNameIfTheyAreSame();
            assertTwoProductsHaveSameSize();
            for (int i = 0; i < product1.getValues().size(); i++) {
                String ori = product1.getValues().get(i);
                String comp = product2.getValues().get(i);
                comparedDebugProduct.getValues().set(i, createInspectedRow(ori, comp));
            }
            comparedDebugProduct.debuggingIsDone();
        }
        catch (Exception e){
            e.printStackTrace();
            comparedDebugProduct.errorOccuredBeforeInspection();
            //System.out.println("예외 발생시 결과 (false가 되어야함) : " + !comparedDebugProduct.hasError());
        }
    }




    void getValueTypeAndNameIfTheyAreSame()throws Exception{
        assertThoseProductsHasSameNameAndType();
        productName = product1.getName();
        productValueType = product1.getValueType();
    }

    void assertThoseProductsHasSameNameAndType()throws Exception{
        assertSameName();
        assertSameType();

    }

    void assertSameName()throws Exception{
        if(product1.getName() != product2.getName()){
            throw new Exception("비교할 두 product의 이름이 다릅니다");
        }
    }

    void assertSameType()throws Exception{
        if(product1.getValueType() != product2.getValueType()){
            throw new Exception("비교할 두 product의 타입이 다릅니다");
        }
    }

    void assertTwoProductsHaveSameSize()throws Exception{
        if(product1.getValues().size() != product2.getValues().size()){
            throw new Exception("Two products have different values's size");
        }
    }

    String createInspectedRow(String ori, String comp) throws  Exception{
        return getResultOfComparingCell(ori, comp);
    }

    String getResultOfComparingCell(String ori, String comp) throws Exception{
        //go to branches of each valuetype and compare
        if(productValueType == ComparableProduct.ValueType.STRING){
            return areSameString(ori, comp);
        }
        else if(productValueType == ComparableProduct.ValueType.NUMBER){
            return areNumbersInAcceptableRange(ori, comp);
        }
        else{
            throw new Exception("없는 value type 입니다");
        }
    }

    String areSameString(String ori, String comp){
        if(ori.equals(comp)){
            return "same";
        }
        else
            return STANDARD_ERROR_MESSEGE + " : strings are different";
    }

    String areNumbersInAcceptableRange(String origin, String comparable){
        float ori = Float.parseFloat(origin);
        float comp = Float.parseFloat(comparable);
        float tmp= (float) (ori * 0.2);
        float maxRange = ori+tmp;
        float minRange = ori-tmp;
        System.out.println("string origin : "+ origin + ", string comparable : " + comparable + ", ori : " + ori + " comp : " + comp + " maxRange : " + maxRange + " minRange : " + minRange + "\n");
        if( (maxRange >= comp) && (minRange <= comp) ){
            return "In acceptable range";
        }
        else {
            return STANDARD_ERROR_MESSEGE + " : Numbers are over offset range";
        }
    }
}

package godon.MainTest;

import godon.ColumnPackage.ComparableColumn;
import godon.ColumnPackage.ComparedDebugColumn;
import godon.ColumnPackage.Column;

import java.util.ArrayList;

import static godon.ColumnPackage.ComparedDebugColumn.STANDARD_ERROR_MESSEGE;

/**
 * Created by Godon on 2016-07-18.
 */
public class CompareTest {
    //get two columns
    //make excel log of comparing test
    ComparableColumn column1;
    ComparableColumn column2;
    ComparedDebugColumn comparedDebugProduct;

    String productName;
    ComparableColumn.ValueType productValueType;

    public CompareTest(){

    }

    public boolean areTheySame(ComparableColumn column1, ComparableColumn column2){
        this.column1 = column1;
        this.column2 = column2;
        ComparableColumn standardColumn = column1;
        initializeDebuggingProductWith(standardColumn);
        doMainInspection();
        System.out.println("결과 : " + !comparedDebugProduct.hasError());
        return !(comparedDebugProduct.hasError());
    }

    void initializeDebuggingProductWith(Column column){
        ArrayList<String> arrayList = new ArrayList<>();
        for(int i = 0; i < column.getValues().size(); i++){
            arrayList.add("Not renewed yet");
        }
        comparedDebugProduct = new ComparedDebugColumn(column);
        comparedDebugProduct.setValues(arrayList);
    }

    public ComparedDebugColumn getComparedDebugProduct(){
        return comparedDebugProduct;
    }

    void doMainInspection(){
        try {
            getValueTypeAndNameIfTheyAreSame();
            assertTwoProductsHaveSameSize();
            for (int i = 0; i < column1.getValues().size(); i++) {
                String ori = column1.getValues().get(i);
                String comp = column2.getValues().get(i);
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
        productName = column1.getName();
        productValueType = column1.getValueType();
    }

    void assertThoseProductsHasSameNameAndType()throws Exception{
        assertSameName();
        assertSameType();

    }

    void assertSameName()throws Exception{
        if(column1.getName() != column2.getName()){
            throw new Exception("비교할 두 product의 이름이 다릅니다");
        }
    }

    void assertSameType()throws Exception{
        if(column1.getValueType() != column2.getValueType()){
            throw new Exception("비교할 두 product의 타입이 다릅니다");
        }
    }

    void assertTwoProductsHaveSameSize()throws Exception{
        if(column1.getValues().size() != column2.getValues().size()){
            throw new Exception("Two columns have different values's size");
        }
    }

    String createInspectedRow(String ori, String comp) throws  Exception{
        return getResultOfComparingCell(ori, comp);
    }

    String getResultOfComparingCell(String ori, String comp) throws Exception{
        //go to branches of each valuetype and compare
        if(productValueType == ComparableColumn.ValueType.STRING){
            return areSameString(ori, comp);
        }
        else if(productValueType == ComparableColumn.ValueType.NUMBER){
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
            System.out.println("In acceptable range");
            return "In acceptable range";
        }
        else {
            System.out.println(STANDARD_ERROR_MESSEGE + " : Numbers are over offset range");
            return STANDARD_ERROR_MESSEGE + " : Numbers are over offset range";
        }
    }
}

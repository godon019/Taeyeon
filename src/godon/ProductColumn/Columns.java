package godon.ProductColumn;

import java.util.ArrayList;

/**
 * Created by Godon on 2016-07-04.
 */
public class Columns extends ColumnArrayFunction {

    public ArrayList<Column> getProductArr() {
        return columnArr;
    }

    public Columns(){
        super();
    }

    public void setPrimitiveProduct(String name) {
        int hasName = setPrimitiveProductAndGetNumbersOfIt(name);
        try {
            throwExceptionIfthereareNotProperPrimitiveProductNumbers(name, hasName);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    int setPrimitiveProductAndGetNumbersOfIt(String name){
        int hasName = 0;
        for(Column column : columnArr){
            if(column.getName().equals(name)){
                hasName ++;
                column.setItAsPrimitive();
            }
        }
        return hasName;
    }

    void throwExceptionIfthereareNotProperPrimitiveProductNumbers(String name, int hasName) throws Exception{
        if(hasName == 0){
            throw new Exception("Primitive 를 설정할  Name : " + name + " 을 가진 Column 가 없습니다");
        }
        else if(hasName > 1){
            throw new Exception("Primitive 를 설정할  Name : " + name + " 을 가진 Column 가 여러개 입니다");
        }
    }

    public void initializeNullValuesOfProductToProperValuesWithPrimeProduct() throws Exception{

        int sizeOfNotNullValue = 0;
        sizeOfNotNullValue = getPrimeProduct().getValues().size();
        initializeProductsWithNotNullValue(sizeOfNotNullValue);
    }

    Column getPrimeProduct() throws Exception{
        Column columnToReturn = null;
        int primeProductCount = 0;
        for(Column column : columnArr){
            if(column.isPrimitive()){
                columnToReturn = column;
                primeProductCount++;
            }
        }

        if(primeProductCount == 0){
            throw new Exception("There is no Prime Column");
        }
        if(primeProductCount > 1){
            throw new Exception("There are more than one Prime Column");
        }

        return columnToReturn;
    }

    void initializeProductsWithNotNullValue(int sizeOfNotNullValue){
        for(Column column : columnArr){
            if(column.getValues() == null){
                ArrayList<String> values = new ArrayList<String>();
                for(int i = 0; i<sizeOfNotNullValue; i++){
                    values.add("갱신 안됨");
                }
                column.setValues(values);
            }
        }
    }


    public void setColumn(String name, ArrayList<String> value){
        for(Column column : columnArr){
            if(column.getName().equals(name)){
                column.setValues(value);
                return;
            }
        }
        try {
            throw new Exception("해당 하는 이름 :" +name+" 의 set할 Product가 존재 하지 않습니다");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public Column getColumn(String name){
        for (Column column : columnArr) {
            if (column.getName().equals(name)) {
                return column;
            }
        }
        try {
            throw new Exception("해당 하는 이름 :" + name + " 의 get할 Product가 존재 하지 않습니다");
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}

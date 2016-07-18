package godon.Product;

import java.util.ArrayList;

/**
 * Created by Godon on 2016-07-07.
 */
public class Product {
    public enum IOType{
        READ_ONLY,
        WRITE_ONLY,
        READ_AND_WRITE;
    }
    IOType iOType;
    String name;
    ArrayList<String> values;
    Boolean isPrimitive;

    public Boolean isPrimitive() {
        return isPrimitive;
    }

    public Product(String name, IOType iOType) {
        this.name = name;
        this.iOType = iOType;
        this.values = null;
        this.isPrimitive = false;
    }

    public void setItAsPrimitive(){
        isPrimitive = true;
    }

    public IOType getiOType() {
        return iOType;
    }

    public boolean isReadable() throws Exception{

        if(iOType == IOType.READ_ONLY)
            return true;
        else if(iOType == IOType.READ_AND_WRITE)
            return true;
        else if(iOType == IOType.WRITE_ONLY)
            return false;

        throw new Exception("잘못된 enum 타입입니다");
    }

    public boolean isWritable() throws Exception{
        if(iOType == IOType.READ_ONLY)
            return false;
        else if(iOType == IOType.READ_AND_WRITE)
            return true;
        else if(iOType == IOType.WRITE_ONLY)
            return true;

        throw new Exception("잘못된 enum 타입입니다");
    }

    public void setValues(ArrayList<String> values) {
        this.values = values;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {

        return name;
    }

    public ArrayList<String> getValues() {
        return values;
    }

    public boolean equals(Product product){
        if((areNameeEqual(product)==true) && (areValuesEqual(product)==true))
            return true;
        else
            return false;
    }

    public boolean areNameeEqual(Product product){
        if(this.getName().equals(product.getName())) {
            System.out.println("이름이 같습니다");
            return true;
        }
        else {
            System.out.println("이름이 다릅니다");
            return false;
        }
    }

    public boolean areValuesEqual(Product product){
        if(product.getValues().equals(this.getValues())) {
            System.out.println("values가 같습니다");
            return true;
        }
        else {
            System.out.println("values가 다릅니다");
            return false;
        }
    }

}

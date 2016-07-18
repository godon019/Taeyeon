package godon.Product;

/**
 * Created by Godon on 2016-07-18.
 */
public class ComparableProduct extends Product {
    public enum ValueType{
        NUMBER,
        STRING;
    }
    ValueType valueType;

    public ComparableProduct(String name, IOType iOType, ValueType valueType) {
        super(name, iOType);
        this.valueType = valueType;
    }

    public ValueType getValueType(){
        return  valueType;
    }

}

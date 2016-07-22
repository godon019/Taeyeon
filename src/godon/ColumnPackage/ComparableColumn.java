package godon.ColumnPackage;

/**
 * Created by Godon on 2016-07-18.
 */
public class ComparableColumn extends Column {
    public enum ValueType{
        NUMBER,
        STRING;
    }
    ValueType valueType;

    public ComparableColumn(String name, ValueType valueType) {
        super(name);
        this.valueType = valueType;
    }

    public ValueType getValueType(){
        return  valueType;
    }

}

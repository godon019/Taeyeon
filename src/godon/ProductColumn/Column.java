package godon.ProductColumn;

import java.util.ArrayList;

/**
 * Created by Godon on 2016-07-07.
 */
public class Column {
    public enum IOType{
        READ_ONLY,
        WRITE_ONLY,
        READ_AND_WRITE;
    }
    IOType iOType;

    String name;
    ArrayList<String> values;
    Boolean isPrimitive;

    public Column(){

    }

    public Column(String name, IOType iOType) {
        this.name = name;
        this.iOType = iOType;
        this.values = null;
        this.isPrimitive = false;
    }

    public Boolean isPrimitive() {
        return isPrimitive;
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



}

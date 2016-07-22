package godon.ColumnPackage;

import java.util.ArrayList;

/**
 * Created by Godon on 2016-07-07.
 */
public class Column {

    String name;
    ArrayList<String> values;
    Boolean isPrimitive;

    public Column(){
    }

    public Column(String name) {
        this.name = name;
        this.values = null;
        this.isPrimitive = false;
    }

    public Boolean isPrimitive() {
        return isPrimitive;
    }

    public void setItAsPrimitive(){
        isPrimitive = true;
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

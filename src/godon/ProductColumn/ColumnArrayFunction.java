package godon.ProductColumn;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Godon on 2016-07-19.
 */
public class ColumnArrayFunction {
    protected ArrayList<Column> columnArr;

    ColumnArrayFunction(){
        columnArr = new ArrayList<Column>();
    }

    public void addColumn(Column column){
        columnArr.add(column);
    }

    public void removeColumn(String name){
        for(Iterator<Column> iterator = columnArr.iterator(); iterator.hasNext(); ){
            if(iterator.next().getName() == name){
                iterator.remove();
            }
        }
    }

    public String toString(){
        StringBuilder productLists = new StringBuilder();
        productLists.append("Column lists : ");
        for(Column column : columnArr){
            productLists.append(column.getName() +", ");
        }

        productLists.append("\n");
        return productLists.toString().trim();
    }
}

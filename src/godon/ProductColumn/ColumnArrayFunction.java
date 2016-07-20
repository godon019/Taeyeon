package godon.ProductColumn;

import java.util.ArrayList;

/**
 * Created by Godon on 2016-07-19.
 */
public class ColumnArrayFunction {
    protected ArrayList<Column> columnArr;

    ColumnArrayFunction(){
        columnArr = new ArrayList<Column>();
    }

    public void addProduct(Column column){
        columnArr.add(column);
    }

    public void removeProduct(String name){
        for(Column column : columnArr){
            if(column.getName() == name){
                columnArr.remove(column);
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

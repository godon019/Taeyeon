package godon.ColumnPackage;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Godon on 2016-07-19.
 */
public class ColumnsPrimitiveFunction {
    protected ArrayList<Column> columnArr;

    ColumnsPrimitiveFunction(){
        columnArr = new ArrayList<Column>();
    }

    public void addColumn(Column column){
        if(hasSameNameOfColumnExist(column) == false)
            columnArr.add(column);
        else
            throwDuplicatedColumnNameError();
    }

    boolean hasSameNameOfColumnExist(Column column){
        for(Column tmp : columnArr){
            if(tmp.getName().equals(column.getName())){
                return true;
            }
        }
        return false;
    }

    void throwDuplicatedColumnNameError(){
        try{
            throw new Exception("Has same name of Column exist already in ColumnArr");
        }catch (Exception e){
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void removeColumn(String name){
        boolean hasColumn = false;

        for(Iterator<Column> iterator = columnArr.iterator(); iterator.hasNext(); ){
            if(iterator.next().getName() == name){
                iterator.remove();
                hasColumn = true;
            }
        }

        if(hasColumn == false){
            throwExceptionIfNoColumnMatches();
        }
    }

    void throwExceptionIfNoColumnMatches(){
        try {
            throw new Exception("Has no column matches");
        }
        catch (Exception e){
            e.printStackTrace();
            System.exit(0);
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
